import java.util.Random;
import java.util.Scanner;

public class StrassenMatrixMultiplication {

    // Generate random matrix
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

    // Add two matrices
    static int[][] add(int[][] A, int[][] B) {

        int n = A.length;
        int[][] C = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                C[i][j] = A[i][j] + B[i][j];
            }
        }

        return C;
    }

    // Subtract two matrices
    static int[][] subtract(int[][] A, int[][] B) {

        int n = A.length;
        int[][] C = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                C[i][j] = A[i][j] - B[i][j];
            }
        }

        return C;
    }

    // Strassen multiplication
    static int[][] strassen(int[][] A, int[][] B) {

        int n = A.length;

        // Base case
        if (n == 1) {
            return new int[][] {
                {A[0][0] * B[0][0]}
            };
        }

        int newSize = n / 2;

        int[][] A11 = new int[newSize][newSize];
        int[][] A12 = new int[newSize][newSize];
        int[][] A21 = new int[newSize][newSize];
        int[][] A22 = new int[newSize][newSize];

        int[][] B11 = new int[newSize][newSize];
        int[][] B12 = new int[newSize][newSize];
        int[][] B21 = new int[newSize][newSize];
        int[][] B22 = new int[newSize][newSize];

        // Divide matrices
        for (int i = 0; i < newSize; i++) {

            for (int j = 0; j < newSize; j++) {

                A11[i][j] = A[i][j];
                A12[i][j] = A[i][j + newSize];
                A21[i][j] = A[i + newSize][j];
                A22[i][j] = A[i + newSize][j + newSize];

                B11[i][j] = B[i][j];
                B12[i][j] = B[i][j + newSize];
                B21[i][j] = B[i + newSize][j];
                B22[i][j] = B[i + newSize][j + newSize];
            }
        }

        // Calculate M1 to M7

        int[][] M1 = strassen(
                add(A11, A22),
                add(B11, B22)
        );

        int[][] M2 = strassen(
                add(A21, A22),
                B11
        );

        int[][] M3 = strassen(
                A11,
                subtract(B12, B22)
        );

        int[][] M4 = strassen(
                A22,
                subtract(B21, B11)
        );

        int[][] M5 = strassen(
                add(A11, A12),
                B22
        );

        int[][] M6 = strassen(
                subtract(A21, A11),
                add(B11, B12)
        );

        int[][] M7 = strassen(
                subtract(A12, A22),
                add(B21, B22)
        );

        // Calculate result submatrices

        int[][] C11 = add(
                subtract(
                        add(M1, M4),
                        M5
                ),
                M7
        );

        int[][] C12 = add(M3, M5);

        int[][] C21 = add(M2, M4);

        int[][] C22 = add(
                subtract(
                        add(M1, M3),
                        M2
                ),
                M6
        );

        // Combine result
        int[][] C = new int[n][n];

        for (int i = 0; i < newSize; i++) {

            for (int j = 0; j < newSize; j++) {

                C[i][j] = C11[i][j];

                C[i][j + newSize] = C12[i][j];

                C[i + newSize][j] = C21[i][j];

                C[i + newSize][j + newSize] = C22[i][j];
            }
        }

        return C;
    }

    // Find next power of 2
    static int nextPowerOfTwo(int n) {

        int power = 1;

        while (power < n) {
            power *= 2;
        }

        return power;
    }

    // Print matrix
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

        // Generate original matrices
        int[][] originalA = generateMatrix(n);
        int[][] originalB = generateMatrix(n);

        // Find suitable power-of-two size
        int size = nextPowerOfTwo(n);

        // Create padded matrices
        int[][] A = new int[size][size];
        int[][] B = new int[size][size];

        // Copy original matrices into padded matrices
        for (int i = 0; i < n; i++) {

            for (int j = 0; j < n; j++) {

                A[i][j] = originalA[i][j];
                B[i][j] = originalB[i][j];
            }
        }

        // Start timer
        long startTime = System.nanoTime();

        // Strassen multiplication
        int[][] paddedResult = strassen(A, B);

        // Stop timer
        long endTime = System.nanoTime();

        long executionTime = endTime - startTime;

        System.out.println("\nOriginal matrix size: "
                + n + " x " + n);

        System.out.println("Padded matrix size: "
                + size + " x " + size);

        System.out.println("Execution time: "
                + executionTime / 1_000_000.0 + " ms");

        // Print result only for small matrices
        if (n <= 5) {

            int[][] result = new int[n][n];

            for (int i = 0; i < n; i++) {

                for (int j = 0; j < n; j++) {

                    result[i][j] = paddedResult[i][j];
                }
            }

            System.out.println("\nMatrix A:");
            printMatrix(originalA, n);

            System.out.println("\nMatrix B:");
            printMatrix(originalB, n);

            System.out.println("\nResult Matrix C:");
            printMatrix(result, n);
        }

        sc.close();
    }
}