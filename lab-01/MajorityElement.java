import java.util.Scanner;

public class MajorityElement {

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

        System.out.println("Enter the array elements:");

        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        long start = System.nanoTime();

        int candidate = findCandidate(arr);

        long end = System.nanoTime();

        if (isMajority(arr, candidate))
            System.out.println("Majority Element = " + candidate);
        else
            System.out.println("No Majority Element found.");

        double timeTaken = (end - start) / 1_000_000.0;

        System.out.println("Execution Time = " + timeTaken + " ms");

        sc.close();
    }
}