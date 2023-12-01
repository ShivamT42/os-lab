import java.util.Scanner;

public class Disk_SSTF {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int RQ[] = new int[100], n, TotalHeadMovement = 0, initial, count = 0;

        System.out.println("Enter the number of Requests");
        n = scanner.nextInt();

        System.out.println("Enter the Requests sequence");
        for (int i = 0; i < n; i++)
            RQ[i] = scanner.nextInt();

        System.out.println("Enter initial head position");
        initial = scanner.nextInt();

        while (count != n) {
            int min = 1000, d, index = 0;
            for (int i = 0; i < n; i++) {
                d = Math.abs(RQ[i] - initial);
                if (min > d) {
                    min = d;
                    index = i;
                }
            }
            TotalHeadMovement = TotalHeadMovement + min;
            initial = RQ[index];
            RQ[index] = 1000;
            count++;
        }

        System.out.println("Total head movement is " + TotalHeadMovement);
    }
}
