import java.util.Arrays;
import java.util.Scanner;

public class Disk_CSCAN {
    static int max = 199, min = 0;

    static void sort(int[] arr, int n) {
        Arrays.sort(arr, 0, n);
    }

    static void cScan(int[] arr, int head, int n) {
        int index = 0;
        sort(arr, n);

        // Calculate head position
        for (int i = 0; i < n; i++) {
            if (arr[i] == head) {
                index = i;
                break;
            }
        }

        // C-Scan:-
        for (int i = index; i < n - 1; i++) {
            System.out.printf("\nHead moved from %d to %d", arr[i], arr[i + 1]);
        }
        System.out.printf("\nHead moved from %d to %d", arr[n - 1], max);
        System.out.printf("\nHead moved from %d to %d", max, min);
        System.out.printf("\nHead moved from %d to %d", min, arr[0]);
        for (int i = 0; i < index - 1; i++) {
            System.out.printf("\nHead moved from %d to %d", arr[i], arr[i + 1]);
        }

        int seek = Math.abs(max - head) + Math.abs(max - min) + Math.abs(min - arr[index - 1]);
        System.out.printf("\nC-Scan Seek time= %d", seek);
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
        cScan(arr, head, n + 1);
    }
}