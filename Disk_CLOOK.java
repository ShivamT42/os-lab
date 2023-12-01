import java.util.Arrays;
import java.util.Scanner;

public class Disk_CLOOK {
    static void sort(int[] arr, int n) {
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (arr[i] > arr[j]) {
                    int temp = arr[j];
                    arr[j] = arr[i];
                    arr[i] = temp;
                }
            }
        }
    }

    static void CLOOK(int[] arr, int head, int n) {
        sort(arr, n);
        int index = 0;
        for (int i = 0; i < n; i++) {
            if (arr[i] == head) {
                index = i;
                break;
            }
        }
        for (int i = index; i < n - 1; i++) {
            System.out.printf("\nHead moved from %d to %d", arr[i], arr[i + 1]);
        }

        System.out.printf("\nHead moved from %d to %d", arr[n - 1], arr[0]);

        for (int i = 0; i < index - 1; i++) {
            System.out.printf("\nHead moved from %d to %d", arr[i], arr[i + 1]);
        }
        int c = arr[n - 1] - head + arr[n - 1] - arr[0] + arr[index - 1] - arr[0];
        System.out.printf("\nSeek Time: %d", c);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter no. of requests: ");
        int n = scanner.nextInt();
        int[] arr = new int[n + 1];

        System.out.println("\nEnter requests: ");
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }

        System.out.print("Enter head: ");
        int head = scanner.nextInt();
        arr[n] = head;
        CLOOK(arr, head, n + 1);
    }
}
