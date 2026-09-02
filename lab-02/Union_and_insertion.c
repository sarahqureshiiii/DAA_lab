#include <stdio.h>

int main() {
    int n1, n2;

    printf("Enter the size of first array: ");
    scanf("%d", &n1);

    int A[n1];

    printf("Enter the elements of first sorted array:\n");
    for (int i = 0; i < n1; i++) {
        scanf("%d", &A[i]);
    }

    printf("Enter the size of second array: ");
    scanf("%d", &n2);

    int B[n2];

    printf("Enter the elements of second sorted array:\n");
    for (int i = 0; i < n2; i++) {
        scanf("%d", &B[i]);
    }

    int i = 0, j = 0;

    printf("\nUnion: ");

    while (i < n1 && j < n2) {

        if (A[i] < B[j]) {
            printf("%d ", A[i]);
            i++;
        }
        else if (A[i] > B[j]) {
            printf("%d ", B[j]);
            j++;
        }
        else {
            printf("%d ", A[i]);
            i++;
            j++;
        }
    }

    while (i < n1) {
        printf("%d ", A[i]);
        i++;
    }

    while (j < n2) {
        printf("%d ", B[j]);
        j++;
    }

    i = 0;
    j = 0;

    printf("\nIntersection: ");

    while (i < n1 && j < n2) {

        if (A[i] < B[j]) {
            i++;
        }
        else if (A[i] > B[j]) {
            j++;
        }
        else {
            printf("%d ", A[i]);
            i++;
            j++;
        }
    }

    return 0;
}
