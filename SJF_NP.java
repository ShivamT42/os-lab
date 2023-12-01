import java.util.Scanner;

public class SJF_NP {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of processes: ");
        int n = scanner.nextInt();

        int[] pid = new int[n];
        int[] at = new int[n];
        int[] bt = new int[n];
        int[] ct = new int[n];
        int[] ta = new int[n];
        int[] wt = new int[n];
        int[] f = new int[n];
        int st = 0, tot = 0;

        // Input process details
        for (int i = 0; i < n; i++) {
            pid[i] = i + 1;
            System.out.printf("Enter arrival time for process %d: ", i + 1);
            at[i] = scanner.nextInt();
            System.out.printf("Enter burst time for process %d: ", i + 1);
            bt[i] = scanner.nextInt();
            f[i] = 0;
        }

        // Sort processes based on arrival time
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (at[j] > at[j + 1]) {
                    // Swap arrival times
                    int tempAt = at[j];
                    at[j] = at[j + 1];
                    at[j + 1] = tempAt;

                    // Swap burst times
                    int tempBt = bt[j];
                    bt[j] = bt[j + 1];
                    bt[j + 1] = tempBt;

                    // Swap process IDs
                    int tempPid = pid[j];
                    pid[j] = pid[j + 1];
                    pid[j + 1] = tempPid;
                }
            }
        }

        System.out.println("\nProcess Execution Order:");

        while (true) {
            int c = n, min = Integer.MAX_VALUE;
            if (tot == n)
                break;

            for (int i = 0; i < n; i++) {
                if (at[i] <= st && f[i] == 0 && bt[i] < min) {
                    min = bt[i];
                    c = i;
                }
            }

            if (c == n) {
                st++;
            } else {
                ct[c] = st + bt[c];
                st += bt[c];
                ta[c] = ct[c] - at[c];
                wt[c] = ta[c] - bt[c];
                f[c] = 1;
                tot++;
                System.out.print("P" + pid[c] + " ");
            }
        }

        // Display process details
        System.out.println("\n\nPid|Arrival| Burst |Complete | Turn|Waiting");
        for (int i = 0; i < n; i++) {
            System.out.printf("%d\t%d\t%d\t%d\t%d\t%d\n", pid[i], at[i], bt[i], ct[i], ta[i], wt[i]);
        }

        scanner.close();
    }
}
