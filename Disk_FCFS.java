import java.util.Scanner;

public class Disk_FCFS {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("\nEnter the length of reference string- ");
        int n = scanner.nextInt();
        int[] arr = new int[n];

        System.out.println("Enter the reference string- ");
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }

        System.out.print("Enter initial head position- ");
        int head = scanner.nextInt();

        int seek = 0;
        System.out.printf("\nHead moved from %d to %d", head, arr[0]);
        seek += Math.abs(head - arr[0]);

        for (int i = 0; i < n - 1; i++) {
            System.out.printf("\nHead moved from %d to %d", arr[i], arr[i + 1]);
            seek += Math.abs(arr[i] - arr[i + 1]);
        }

        System.out.printf("\nTotal seek time= %d", seek);
    }
}
