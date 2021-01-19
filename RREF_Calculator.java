import java.util.Scanner;
import static java.lang.System.*;

public class Main {   
    //Input matrix
    private static Double[][] inputMatrix = matrixMaker();
	//Number of rows and columns
	private static int NUMROWS = inputMatrix.length;
	private static int NUMCOLS = NUMROWS + 1;

	public static void main(String[] args) {
	   Double pivot = inputMatrix[0][0];
	   Double[][] myTemMatrix = new Double[NUMROWS][NUMCOLS];
	   System.out.println("Your matrix is: ");
	   printMatrix(inputMatrix);
	    
	    //This for loop makes sure that the pivot entry is 1
	   for (int i = 0; i < NUMROWS; i++) {
	       if (pivot != 1) {
	           if (inputMatrix[i][0] == 1) {
	               switchRows(0, i);
	               System.out.println("Switch rows " + 0 + " and " + i + ": ");
	               printMatrix(inputMatrix);
	           }
	           else {
	               multiplyRow((1/pivot), 0);
	               System.out.println("Multiply row " + 0 + " by " + (1/pivot) + ": ");
	               printMatrix(inputMatrix);
	               break;
	           }
	       }
	   }
	   for (int i = 0; i < (NUMCOLS-1); i++) {
	       RREF(i);
	       if (checkRowOfZeros(i) == true) {
	           System.out.println("The matrix needs to be paramtrized, since there is a row of zeros");
	       }
	       

	   }
	   System.out.println("RREF of your matrix is: ");
	   printMatrix(inputMatrix);
	}
	
	//Multiplies a row by a number
	public static void multiplyRow (Double multiplier, int rowNum) {
	    for (int i = 0; i < NUMCOLS; i++) {
	        inputMatrix[rowNum][i] = inputMatrix[rowNum][i] * (multiplier);
	  	}
	}
	//Switches two rows
	public static void switchRows (int row1, int row2) {
	    Double tempRow1;
	    Double tempRow2;
	    for (int i = 0; i < NUMCOLS; i++) {
	        tempRow1 = inputMatrix[row1][i];
	        tempRow2 = inputMatrix[row2][i];
	        inputMatrix[row2][i] = tempRow1;
	        inputMatrix[row1][i] = tempRow2;
	    }
	}
	//Adds a row to andother row and puts the answer into the second row
	public static void addRows (int sourceRow, int targetRow) {
	    for (int i = 0; i < NUMCOLS; i++) {
	        inputMatrix[targetRow][i] += inputMatrix[sourceRow][i];
	    }
	}
	//Checks if all the values of a row are zero
	public static boolean checkRowOfZeros(int row) {
	    Double[] rowMatrix = new Double[NUMCOLS];
	    Double[] zeros = new Double[NUMCOLS];
	    //Makes a 1xNUMCOLS zero matrix and another 1xNUMCOLS matrix equal to the values of inputMatrix and sees if they are equal
	    for (int i = 0; i < NUMCOLS; i++) {
	        zeros[i] = 0.0;
	        rowMatrix[i] = inputMatrix[row][i];
	    }
	    if (zeros == rowMatrix) {
	        return true;
	    }
	    else {
	        return false;
	    }
	    
	}
	//Checks if a column is in the corect form
	public static boolean checkforRREF(int row, int column) {
	    Double[][] columnMatrix = new Double[NUMROWS][1];
	    Double[][] RREFCol = new Double[NUMROWS][1];
	    //Makes every entry in RREFCol equal to inputMatrix and RREFCol equal to the respective RREF form
	    for (int i = 0; i < NUMROWS; i++) {
	        columnMatrix[i][column] = inputMatrix[i][column];
	        if (i == column) {
	            RREFCol[i][column] = 1.0;
	        }
	        else {
	            RREFCol[i][column] = 0.0;
	        }
	    }
	    if (inputMatrix[row][column] == RREFCol[row][0]) {
	        return true;
	    }
	    else {
	        return false;
	    }
	}
	//Takes in an input column and turns it into the correct form
	public static int RREF (int column) {
	    for (int j = 0; j < (NUMCOLS-1); j++) {
	        if (inputMatrix[column][column] != 1) {
	           if (checkRowOfZeros(i) == true) {
	               return 0;
	           }
	           if (inputMatrix[column][column] == 0) {
	               for (int i = 0; i < NUMCOLS-1; i++) {
	                   if (inputMatrix[i][column] != 0) {
	                       addRows(i, column);
	                   }
	                   if (inputMatrix[column][column] == 0) {
	                       return 2;
	                   }
	               }
	           }
	           System.out.println("Multiply row " + column + " by " + (1/inputMatrix[column][column]));
	           multiplyRow(1/inputMatrix[column][column], column);
	           printMatrix(inputMatrix);
	        }
	        if (checkforRREF(j, column) == false) {
	            System.out.println("Multiply row " + column + " by " + (-inputMatrix[j][column]) + ": ");
	            multiplyRow((-inputMatrix[j][column]), column);
	            printMatrix(inputMatrix);
	            System.out.println("Add row " + column + " and row " + j + " and replace it into row " + j + ": ");
	            addRows(column, j);
	            printMatrix(inputMatrix);
	        }
	        System.out.println("Divide row " + column + " by " + (inputMatrix[column][column]) + ": ");
	        multiplyRow((1/inputMatrix[column][column]), column);
	        printMatrix(inputMatrix);
	    }
    }
	public static Double[][] matrixMaker () {
	    Scanner in = new Scanner(System.in);
        System.out.print("How many rows will the matrix have?: ");
        int m = in.nextInt();
        int n = m + 1;
        Double matrix[][] = new Double[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print("Enter the entry in column " + (j+1) + " row " + (i+1) + ": ");
                matrix[i][j] = in.nextDouble();
            }
        }
        return matrix;
	}
	//Prints a matrix
	public static void printMatrix (Double[][] outputMatrix) {
	   for (int i = 0; i < NUMROWS; i++) {
            for (int j = 0; j < NUMCOLS; j++) {
                System.out.print(outputMatrix[i][j] + " "); 
            }
            System.out.println();
        }
        System.out.println();
	}

}
