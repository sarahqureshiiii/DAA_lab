import java.util.Scanner;

public class DutchNationalFlag {

    public static void sortColors(int[] arr) {
        int low = 0;
        int mid = 0;
        int high = arr.length - 1;

        while (mid <= high) {

            switch (arr[mid]) {

                case 0:
                    int temp = arr[low];
                    arr[low] = arr[mid];
                    arr[mid] = temp;
                    low++;
                    mid++;
                    break;

                case 1:
                    mid++;
                    break;

                case 2:
                    temp = arr[mid];
                    arr[mid] = arr[high];
                    arr[high] = temp;
                    high--;
                    break;

                default:
                    System.out.println("Invalid input! Array should contain only 0, 1 and 2.");
                    return;
            }
        }
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
                System.out.println("Enter a positive number.");

        } while (n <= 0);

        int[] arr = new int[n];

        System.out.println("Enter elements (Only 0, 1 and 2):");

        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        System.out.print("\nOriginal Array: ");
        for (int num : arr)
            System.out.print(num + " ");

        long start = System.nanoTime();

        sortColors(arr);

        long end = System.nanoTime();

        System.out.print("\nSorted Array: ");
        for (int num : arr)
            System.out.print(num + " ");

        double timeTaken = (end - start) / 1_000_000.0;

        System.out.println("\n\nExecution Time = " + timeTaken + " ms");

        sc.close();
    }
}