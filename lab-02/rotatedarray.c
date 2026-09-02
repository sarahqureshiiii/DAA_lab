#include <stdio.h>

int main()
{
    int n, target;

    printf("Enter the number of elements: ");
    scanf("%d", &n);

    int A[n];

    printf("Enter the rotated sorted array: ");
    for (int i = 0; i < n; i++)
    {
        scanf("%d", &A[i]);
    }

    printf("Enter the target element: ");
    scanf("%d", &target);

    int low = 0;
    int high = n - 1;
    int index = -1;
    while (low <= high)
    {
        int mid = (low + high) / 2;
        if (A[mid] == target)
        {
            index = mid;
            break;
        }
        if (A[low] <= A[mid])
        {
            if (A[low] <= target && target < A[mid])
            {
                high = mid - 1;
            }
            else
            {
                low = mid + 1;
            }
        }

        else
        {
            if (A[mid] < target && target <= A[high])
            {
                low = mid + 1;
            }
            else
            {
                high = mid - 1;
            }
        }
    }

    if (index != -1)
    {
        printf("Target found at index: %d\n", index);
    }
    else
    {
        printf("Target not found\n");
    }

    return 0;
}
