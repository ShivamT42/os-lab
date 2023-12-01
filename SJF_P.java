import java.util.ArrayList;
import java.util.Scanner;

public class SJF_P {
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
        int[] k = new int[n];
        int i, st = 0, tot = 0;

        // Sequence list to store the order of process execution
        ArrayList<Integer> sequenceList = new ArrayList<>();

        for (i = 0; i < n; i++) {
            pid[i] = i + 1;
            System.out.printf("Enter arrival time for process %d: ", i + 1);
            at[i] = scanner.nextInt();
            System.out.printf("Enter burst time for process %d: ", i + 1);
            bt[i] = scanner.nextInt();
            k[i] = bt[i];
            f[i] = 0;
        }

        while (true) {
            int min = 999, c = n;
            if (tot == n)
                break;

            for (i = 0; i < n; i++) {
                if (at[i] <= st && f[i] == 0 && bt[i] < min) {
                    min = bt[i];
                    c = i;
                }
            }

            if (c == n)
                st++;
            else {
                bt[c]--;
                st++;
                sequenceList.add(pid[c]);

                if (bt[c] == 0) {
                    ct[c] = st;
                    f[c] = 1;
                    tot++;
                }
            }
        }

        for (i = 0; i < n; i++) {
            ta[i] = ct[i] - at[i];
            wt[i] = ta[i] - k[i];
        }

        System.out.println("\nPid|Arrival| Burst |Complete | Turn|Waiting");
        for (int j = 0; j < n; j++) {
            System.out.printf("%d\t%d\t%d\t%d\t%d\t%d\n", pid[j], at[j], k[j], ct[j], ta[j], wt[j]);
        }

        // Print the sequence of processes
        System.out.println("Sequence of Processes: " + sequenceList);

        scanner.close();
    }
}
