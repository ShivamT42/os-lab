import java.util.Scanner;

public class Disk_SCAN {
    static int max = 199, min = 0;

    static void sort(int[] arr, int n) {
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (arr[i] > arr[j]) {
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
    }

    static void scan(int[] arr, int head, int n) {
        int index = 0;
        sort(arr, n);

        for (int i = 0; i < n; i++) {
            if (arr[i] == head) {
                index = i;
                break;
            }
        }

        for (int i = index; i < n - 1; i++) {
            System.out.printf("\nHead moved from %d to %d", arr[i], arr[i + 1]);
        }
        System.out.printf("\nHead moved from %d to %d", arr[n - 1], max);
        System.out.printf("\nHead moved from %d to %d", max, arr[index - 1]);
        for (int i = index - 1; i > 0; i--) {
            System.out.printf("\nHead moved from %d to %d", arr[i], arr[i - 1]);
        }

        int seek = Math.abs(max - head) + Math.abs(max - arr[0]);
        System.out.printf("\nScan Seek time= %d", seek);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] arr = new int[25];
        int n = 0;
        System.out.print("Enter no. of requests: ");
        n = scanner.nextInt();

        System.out.println("\nEnter requests: ");
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }

        int head = 0;
        System.out.print("\nEnter head: ");
        head = scanner.nextInt();
        arr[n] = head;
        scan(arr, head, n + 1);
    }
}
