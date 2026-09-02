#include <stdio.h>
#include <stdlib.h>

long long merge(int arr[], int temp[], int left, int mid, int right) {

    int i = left;
    int j = mid + 1;
    int k = left;

    long long inversions = 0;

    while (i <= mid && j <= right) {

        if (arr[i] <= arr[j]) {
            temp[k++] = arr[i++];
        }
        else {
            temp[k++] = arr[j++];


            inversions += (mid - i + 1);
        }
    }

    while (i <= mid)
        temp[k++] = arr[i++];

    while (j <= right)
        temp[k++] = arr[j++];

    for (i = left; i <= right; i++)
        arr[i] = temp[i];

    return inversions;
}

long long mergeSort(int arr[], int temp[], int left, int right) {

    if (left >= right)
        return 0;

    int mid = left + (right - left) / 2;

    long long inversions = 0;


    inversions = inversions + mergeSort(arr, temp, left, mid);

    inversions = inversions + mergeSort(arr, temp, mid + 1, right);

    inversions = inversions + merge(arr, temp, left, mid, right);

    return inversions;
}

int main() {

    int n;

    printf("Enter the number of elements: ");
    scanf("%d", &n);

    if (n <= 0) {
        printf("Invalid array size.\n");
        return 0;
    }

    int *arr = (int *)malloc(n * sizeof(int));
    int *temp = (int *)malloc(n * sizeof(int));

    if (arr == NULL || temp == NULL) {
        printf("Memory allocation failed.\n");
        return 1;
    }

    printf("Enter the array elements:\n");

    for (int i = 0; i < n; i++)
        scanf("%d", &arr[i]);

    long long count = mergeSort(arr, temp, 0, n - 1);

    printf("Number of inversions = %lld\n", count);

    printf("Sorted Array: ");

    for (int i = 0; i < n; i++)
        printf("%d ", arr[i]);

    printf("\n");

    free(arr);
    free(temp);

    return 0;
}
