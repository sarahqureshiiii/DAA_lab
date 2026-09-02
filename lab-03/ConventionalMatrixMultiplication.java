import java.util.Random;
import java.util.Scanner;

public class ConventionalMatrixMultiplication {
    static int[][] generateMatrix(int n) {
        Random random = new Random();
        int[][] matrix = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = random.nextInt(10);
            }
        }

        return matrix;
    }
    static int[][] multiply(int[][] A, int[][] B, int n) {

        int[][] C = new int[n][n];
        for (int i = 0; i < n; i++) {

            for (int k = 0; k < n; k++) {

                int aik = A[i][k];

                for (int j = 0; j < n; j++) {

                    C[i][j] += aik * B[k][j];

                }
            }
        }

        return C;
    }
    static void printMatrix(int[][] matrix, int n) {

        for (int i = 0; i < n; i++) {

            for (int j = 0; j < n; j++) {
                System.out.print(matrix[i][j] + " ");
            }

            System.out.println();
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter matrix size n: ");
        int n = sc.nextInt();

        int[][] A = generateMatrix(n);
        int[][] B = generateMatrix(n);

        if (n <= 1000) {
            multiply(A, B, n);
        }


        long startTime = System.nanoTime();

        int[][] C = multiply(A, B, n);

        long endTime = System.nanoTime();

        double executionTime =
                (endTime - startTime) / 1_000_000.0;

        System.out.println("\nMatrix Size: " + n + " x " + n);
        System.out.println("Execution Time: "
                + executionTime + " ms");

        if (n <= 5) {

            System.out.println("\nMatrix A:");
            printMatrix(A, n);

            System.out.println("\nMatrix B:");
            printMatrix(B, n);

            System.out.println("\nResult Matrix C:");
            printMatrix(C, n);
        }

        sc.close();
    }
}