import java.util.Random;
import java.util.Scanner;

public class AllocateMinimumPages {

    // Generate random pages for each book
    static int[] generateBooks(int n) {

        Random random = new Random();

        // Create an array to store the pages of n books
        int[] pages = new int[n];

        // Fill each position of the array with a random page count
        for (int i = 0; i < n; i++) {

            // Generates a random number between 100 and 1000
            pages[i] = random.nextInt(901) + 100;
        }

        return pages;
    }


    // Checks whether all books can be allocated
    // without giving any student more than maxPages
    static boolean isPossible(int[] pages, int students, long maxPages) {

        // Start with the first student
        int studentCount = 1;

        // Stores the total pages currently assigned
        // to the current student
        long currentPages = 0;

        // Visit each book one by one
        for (int page : pages) {

            // If a single book has more pages than the allowed
            // maximum, it cannot be allocated
            if (page > maxPages) {
                return false;
            }

            // Check if the current student can take this book
            // without exceeding maxPages
            if (currentPages + page <= maxPages) {

                // Add the book's pages to the current student
                currentPages += page;

            } else {

                // Current student cannot take this book,
                // so assign the book to the next student
                studentCount++;

                // The new student starts with this book
                currentPages = page;

                // If we need more students than available,
                // this maximum value is not possible
                if (studentCount > students) {
                    return false;
                }
            }
        }

        // All books were successfully allocated
        return true;
    }


    // Finds the minimum possible value of the maximum
    // pages assigned to any student
    static long allocatePages(int[] pages, int students) {

        // If there are more students than books,
        // each student cannot receive at least one book
        if (students > pages.length) {
            return -1;
        }

        // Minimum possible answer is the largest single book
        long low = 0;

        // Maximum possible answer is the total number of pages
        long high = 0;

        // Find the largest book and the total number of pages
        for (int page : pages) {

            // The answer cannot be smaller than the largest book
            low = Math.max(low, page);

            // Calculate the total number of pages
            high += page;
        }


        // Binary search for the minimum possible maximum
        while (low < high) {

            // Find the middle value of the current search range
            long mid = low + (high - low) / 2;

            // Check whether allocation is possible with mid
            // as the maximum pages per student
            if (isPossible(pages, students, mid)) {

                // mid is possible, so try to find
                // an even smaller value
                high = mid;

            } else {

                // mid is not possible, so we need
                // a larger maximum value
                low = mid + 1;
            }
        }

        // When low and high become equal,
        // that value is the minimum possible maximum
        return low;
    }


    // Prints the books array
    // Used only for small inputs
    static void printBooks(int[] pages) {

        System.out.print("Books: ");

        // Print every element in the array
        for (int page : pages) {
            System.out.print(page + " ");
        }

        System.out.println();
    }


    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Get the number of books from the user
        System.out.print("Enter number of books: ");
        int n = sc.nextInt();

        // Get the number of students from the user
        System.out.print("Enter number of students: ");
        int students = sc.nextInt();


        // Check whether the entered values are valid
        if (n <= 0 || students <= 0) {

            System.out.println("Invalid input.");

            sc.close();
            return;
        }


        // There cannot be more students than books
        // because every student must receive at least one book
        if (students > n) {

            System.out.println(
                "Allocation is not possible: " +
                "students cannot be greater than books."
            );

            sc.close();
            return;
        }


        // Generate the books array with random page values
        int[] pages = generateBooks(n);


        // Start measuring the execution time
        // The random array generation is not included
        long startTime = System.nanoTime();


        // Call the main algorithm
        // This performs the binary search and allocation checking
        long result = allocatePages(pages, students);


        // Stop measuring the execution time
        long endTime = System.nanoTime();


        // Convert nanoseconds to milliseconds
        double executionTime =
                (endTime - startTime) / 1_000_000.0;


        // Display the input size
        System.out.println("\nNumber of books: " + n);

        // Display the number of students
        System.out.println("Number of students: " + students);

        // Display the minimum possible maximum pages
        System.out.println(
            "Minimum possible maximum pages: " + result
        );

        // Display the execution time
        System.out.println(
            "Execution time: " + executionTime + " ms"
        );


        // Printing a very large array would take unnecessary time,
        // so print the books only when there are 20 or fewer
        if (n <= 20) {
            printBooks(pages);
        }


        sc.close();
    }
}