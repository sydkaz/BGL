/*
* @author Syed Kazmi
* */
public class Main
{
    public static void main(String[] args)
    {
        int rows = 199, columns = 199;
        int max_number_of_generatons=100; //Number of Genrations
        int[][] inputarray={{0,0},{1,3},{1,4},{2,4},{5,3},{5,4},{6,2},{6,3},{7,5},{8,4}}; // Inital State

        int[][] matrix=new int[rows][columns];
        initializeMatrix(matrix,rows,columns,inputarray);
        printStates(matrix,rows,columns,0);
        computeGenerations(matrix, rows, columns,max_number_of_generatons);
    }

    private static void initializeMatrix(int[][] matrix,int rows, int columns ,int[][] inputarray) {
        for(int i=0;i<rows; i++){
            for(int j=0;i<columns; i++){
                matrix[i][j]=0;
            }
        }
        for(int i=0;i<inputarray.length;i++){
            matrix[inputarray[i][0]][inputarray[i][1]]=1;
        }
    }

    static void printStates(int[][] array,int rows,int columns, int genNumber){
        String text= (genNumber==0) ? "Orignal Generation: " : "The Current Generation is : "+genNumber;

        System.out.printf("%s \n {",text);
        for (int i = 0; i < rows; i++)
        {
            for (int j = 0; j < columns; j++)
            {
                if (array[i][j] == 1)
                    System.out.printf("[%d,%d]",i,j);
            }

        }
        System.out.printf("} \n");
    }

    static void computeGenerations(int matrix[][], int M, int N,int numberOfGenerations)
    {
        if(numberOfGenerations ==0)
            return;

        else {
            int[][] nextGen = new int[M][N];

            for (int l = 1; l < M - 1; l++) {
                for (int m = 1; m < N - 1; m++) {
                    int aliveNeighbours = 0;
                    for (int i = -1; i <= 1; i++)
                        for (int j = -1; j <= 1; j++)
                            aliveNeighbours += matrix[l + i][m + j];

                    aliveNeighbours -= matrix[l][m];

                    if ((matrix[l][m] == 1) && (aliveNeighbours < 2))
                        nextGen[l][m] = 0;

                    else if ((matrix[l][m] == 1) && (aliveNeighbours > 3))
                        nextGen[l][m] = 0;

                    else if ((matrix[l][m] == 0) && (aliveNeighbours == 3))
                        nextGen[l][m] = 1;

                    else
                        nextGen[l][m] = matrix[l][m];
                }
            }
            printStates(nextGen,M,N,(100- --numberOfGenerations));
            computeGenerations(nextGen,M,N,numberOfGenerations-1);
        }

    }
}
