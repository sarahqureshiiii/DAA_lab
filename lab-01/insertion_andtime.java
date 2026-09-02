import java.util.Random;
import java.util.Scanner;

public class insertion_andtime {

    public static void insertionsortt(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int key = arr[i];
            int j = i - 1;

            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }

            arr[j + 1] = key;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();
        int n = 0;

        // Input validation for array size
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

        // Define a safe upper bound for random numbers to prevent overflow issues
        int maxValue = 1_000_000;

        System.out.println("Generating random numbers...");

        for (int i = 0; i < n; i++) {
            // Generates a positive random integer between 0 (inclusive) and maxValue (exclusive)
            arr[i] = rand.nextInt(maxValue);
        }

        // Print original array only if size is manageable
        if (n <= 50) {
            System.out.println("\nOriginal Array: ");

            for (int num : arr) {
                System.out.print(num + " ");
            }

            System.out.println();
        }

        // Timing the sorting algorithm
        long start = System.nanoTime();
        insertionsortt(arr);
        long end = System.nanoTime();

        // Print sorted array only if size is manageable
        if (n <= 50) {
            System.out.println("\nSorted Array: ");

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