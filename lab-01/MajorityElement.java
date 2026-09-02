import java.util.Random;
import java.util.Scanner;

public class MajorityElement {

    // Moore's Voting Algorithm
    public static int findCandidate(int[] arr) {
        int candidate = arr[0];
        int count = 1;

        for (int i = 1; i < arr.length; i++) {

            if (arr[i] == candidate) {
                count++;
            } else {
                count--;
            }

            if (count == 0) {
                candidate = arr[i];
                count = 1;
            }
        }

        return candidate;
    }

    // Verify if the candidate is actually the majority element
    public static boolean isMajority(int[] arr, int candidate) {
        int count = 0;

        for (int num : arr) {
            if (num == candidate)
                count++;
        }

        return count > arr.length / 2;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Random rand = new Random();

        int n;

        do {
            System.out.print("Enter the number of elements: ");

            while (!sc.hasNextInt()) {
                System.out.print("Invalid input. Enter an integer: ");
                sc.next();
            }

            n = sc.nextInt();

            if (n <= 0)
                System.out.println("Please enter a positive number.");

        } while (n <= 0);

        int[] arr = new int[n];

        int maxValue = 10; // Random numbers from 0 to 10

        System.out.println("Generating random array...");

        for (int i = 0; i < n; i++) {
            arr[i] = rand.nextInt(maxValue + 1);
        }

        // Print original array only if size is manageable
        if (n <= 50) {
            System.out.print("\nOriginal Array: ");

            for (int num : arr)
                System.out.print(num + " ");

            System.out.println();
        }

        long start = System.nanoTime();

        int candidate = findCandidate(arr);

        long end = System.nanoTime();

        // Print result
        if (isMajority(arr, candidate))
            System.out.println("\nMajority Element = " + candidate);
        else
            System.out.println("\nNo Majority Element found.");

        double timeTaken = (end - start) / 1_000_000.0;

        System.out.println("Execution Time = " + timeTaken + " ms");

        sc.close();
    }
}