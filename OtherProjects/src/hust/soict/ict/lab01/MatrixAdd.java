import java.util.Scanner;
public class MatrixAdd {
    public static void inputMatrix(int[][] matrix, Scanner scanner, String name) {
        System.out.println("\nEnter elements for Matrix " + name + ":");
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.printf("%s[%d][%d] = ", name, i, j);
                matrix[i][j] = scanner.nextInt();
            }
        }
    }

    public static void displayMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j] + "\t");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter number of rows: ");
        int rows = scanner.nextInt();
        System.out.print("Enter number of columns: ");
        int cols = scanner.nextInt();

        int[][] matrixA = new int[rows][cols];
        int[][] matrixB = new int[rows][cols];
        int[][] sumMatrix = new int[rows][cols];

        inputMatrix(matrixA, scanner, "A");
        System.out.println("\nA =");
        displayMatrix(matrixA);
        inputMatrix(matrixB, scanner, "B");
        System.out.println("\nB =");
        displayMatrix(matrixB);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                sumMatrix[i][j] = matrixA[i][j] + matrixB[i][j];
            }
        }

        System.out.println("\nA + B =");
        displayMatrix(sumMatrix);

        scanner.close();
    }
}