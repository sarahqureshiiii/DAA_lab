#include <stdio.h>
#include <stdlib.h>
#include <time.h>

void merge(int arr[], int temp[], int low, int mid, int high) {

    int i = low;
    int j = mid + 1;
    int k = low;

    while (i <= mid && j <= high) {

        if (arr[i] <= arr[j]) {
            temp[k] = arr[i];
            i++;
        }
        else {
            temp[k] = arr[j];
            j++;
        }

        k++;
    }

    while (i <= mid) {
        temp[k] = arr[i];
        i++;
        k++;
    }

    while (j <= high) {
        temp[k] = arr[j];
        j++;
        k++;
    }

    for (i = low; i <= high; i++) {
        arr[i] = temp[i];
    }
}

void mergesort(int arr[], int temp[], int low, int high) {

    if (low < high) {

        int mid = low + (high - low) / 2;

        mergesort(arr, temp, low, mid);

        mergesort(arr, temp, mid + 1, high);

        merge(arr, temp, low, mid, high);
    }
}

int main() {

    int n;

    printf("Enter the number of elements: ");
    scanf("%d", &n);

    if (n <= 0) {
        printf("Please enter a positive number.\n");
        return 0;
    }

    int *arr = (int *)malloc(n * sizeof(int));
    int *temp = (int *)malloc(n * sizeof(int));

    if (arr == NULL || temp == NULL) {
        printf("Memory allocation failed.\n");
        return 1;
    }

     srand(time(NULL));

    printf("Generating random numbers...\n");

    for (int i = 0; i < n; i++) {
        arr[i] = rand() % 1000000;
    }

    // Print original array only for small inputs
    if (n <= 50) {

        printf("\nOriginal Array:\n");

        for (int i = 0; i < n; i++) {
            printf("%d ", arr[i]);
        }

        printf("\n");
    }

     clock_t start = clock();

    mergesort(arr, temp, 0, n - 1);

    clock_t end = clock();

     if (n <= 50) {

        printf("\nSorted Array:\n");

        for (int i = 0; i < n; i++) {
            printf("%d ", arr[i]);
        }

        printf("\n");
    }

     double timeTaken =
        ((double)(end - start) / CLOCKS_PER_SEC) * 1000.0;

    printf("\nExecution Time = %.6f ms\n", timeTaken);

    free(arr);
    free(temp);

    return 0;
}
