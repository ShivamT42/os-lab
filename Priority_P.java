import java.util.Scanner;

public class Priority_P {
    public static void main(String[] args) {
        preemptivePriority();
    }

    static void preemptivePriority() {
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
        int[] k = new int[n];
        int[] priority = new int[n];
        int i, st = 0, tot = 0;

        for (i = 0; i < n; i++) {
            pid[i] = i + 1;
            System.out.printf("Enter arrival time for process %d: ", i + 1);
            at[i] = scanner.nextInt();
            System.out.printf("Enter burst time for process %d: ", i + 1);
            bt[i] = scanner.nextInt();
            k[i] = bt[i];
            f[i] = 0;
            System.out.printf("Enter priority for process %d: ", i + 1);
            priority[i] = scanner.nextInt();
        }

        System.out.println("\nProcess Execution Order:");

        while (true) {
            int min = Integer.MAX_VALUE;
            int c = -1;

            if (tot == n)
                break;

            for (i = 0; i < n; i++) {
                if (at[i] <= st && f[i] == 0 && priority[i] < min) {
                    min = priority[i];
                    c = i;
                }
            }

            if (c == -1)
                st++;
            else {
                System.out.print("P" + pid[c] + " ");
                bt[c]--;
                st++;

                if (bt[c] == 0) {
                    ct[c] = st;
                    f[c] = 1;
                    tot++;
                }
            }
        }

        System.out.println("\n\nPid|Arrival| Burst |Complete | Turn|Waiting");
        for (int j = 0; j < n; j++) {
            ta[j] = ct[j] - at[j];
            wt[j] = ta[j] - k[j];
            System.out.printf("%d\t%d\t%d\t%d\t%d\t%d\n", pid[j], at[j], k[j], ct[j], ta[j], wt[j]);
        }

        scanner.close();
    }
}
