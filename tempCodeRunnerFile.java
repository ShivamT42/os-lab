import java.util.ArrayList;
import java.util.Scanner;

public class Priority_NP {
    public static void main(String[] args) {
        nonPreemptivePriority();
    }

    static void nonPreemptivePriority() {
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
            System.out.printf("Enter priority for process %d: ", i + 1);
            priority[i] = scanner.nextInt();
        }

        while (true) {
            int min = 999, c = -1;
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
                ct[c] = st + bt[c];
                st = ct[c];
                f[c] = 1;
                tot++;

                // Add the executed process to the sequence list
                sequenceList.add(pid[c]);
            }
        }

        for (i = 0; i < n; i++) {
            ta[i] = ct[i] - at[i];
            wt[i] = ta[i] - k[i];
        }

        System.out.println("\nPid|Arrival| Burst |Complete | Turn|Waiting");
        for (int j = 0; j < n; j++) {
            System.out.printf("%d\t%d\t%d\t%d\t%d\t%d\n", pid[j], at[j], bt[j], ct[j], ta[j], wt[j]);
        }

        // Print the sequence of processes
        System.out.println("Sequence of Processes: " + sequenceList);

        scanner.close();
    }
}
