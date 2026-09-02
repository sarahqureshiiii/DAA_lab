import java.util.Random;
import java.util.Scanner;

public class RecursiveInsertionSort {

    public static void insertionSort(int[] arr, int n) {
        // Base case
        if (n <= 1)
            return;

        insertionSort(arr, n - 1);

        int last = arr[n - 1];
        int j = n - 2;

        while (j >= 0 && arr[j] > last) {
            arr[j + 1] = arr[j];
            j--;
        }

        arr[j + 1] = last;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Random rand = new Random();
        int n = 0;

        do {
            System.out.print("Enter the number of elements: ");

            if (sc.hasNextInt()) {
                n = sc.nextInt();

                if (n <= 0) {
                    System.out.println("Please enter a number greater than 0.");
                }
            } else {
                System.out.println("Invalid input. Enter an integer.");
                sc.next();
            }

        } while (n <= 0);

        int[] arr = new int[n];

        int maxValue = 1_000_000;

        System.out.println("Generating random numbers...");

        for (int i = 0; i < n; i++) {
            arr[i] = rand.nextInt(maxValue);
        }

        if (n <= 50) {
            System.out.println("\nOriginal Array:");

            for (int num : arr) {
                System.out.print(num + " ");
            }

            System.out.println();
        }

        long start = System.nanoTime();
        insertionSort(arr, n);
        long end = System.nanoTime();

        if (n <= 50) {
            System.out.println("\nSorted Array:");

            for (int num : arr) {
                System.out.print(num + " ");
            }

            System.out.println();
        }

        double timeTaken = (end - start) / 1_000_000.0;
        System.out.println("\nTime Taken = " + timeTaken + " ms");

        sc.close();
    }
}