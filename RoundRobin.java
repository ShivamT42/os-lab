import java.util.*;
public class RoundRobin {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of processes:");
        int n = sc.nextInt();

        int[] AT = new int[n];
        int[] BT = new int[n];
        int[] CT = new int[n];
        int[] TAT = new int[n];
        int[] WT = new int[n];
        int[] remainingBT = new int[n];

        for (int i = 0; i < n; i++) {
            System.out.println("Enter the arrival time for process P" + (i + 1) + ":");
            AT[i] = sc.nextInt();
            System.out.println("Enter the burst time for process P" + (i + 1) + ":");
            BT[i] = sc.nextInt();
            remainingBT[i] = BT[i];
        }

        System.out.println("Enter the time quantum:");
        int quantum = sc.nextInt();

        Queue<Integer> queue = new LinkedList<>();
        int currentTime = 0;

        System.out.println("\nGantt Chart:");
        System.out.print("|");

        while (true) {
            boolean allProcessesDone = true;

            for (int i = 0; i < n; i++) {
                if (AT[i] <= currentTime && remainingBT[i] > 0) {
                    allProcessesDone = false;
                    int executionTime = Math.min(quantum, remainingBT[i]);

                    for (int j = 0; j < executionTime; j++) {
                        System.out.print(" P" + (i + 1) + " |");
                        currentTime++;
                        remainingBT[i]--;

                        if (remainingBT[i] == 0) {
                            CT[i] = currentTime;
                            TAT[i] = CT[i] - AT[i];
                            WT[i] = TAT[i] - BT[i];
                        }
                    }
                }
            }

            if (allProcessesDone)
                break;
        }

        System.out.println("|");

        System.out.println("\nP_id\tAT\tBT\tCT\tTAT\tWT");

        for (int i = 0; i < n; i++) {
            System.out.println("P" + (i + 1) + "\t" + AT[i] + "\t" + BT[i] + "\t" +
                    CT[i] + "\t" + TAT[i] + "\t" + WT[i]);
        }

        // Calculate and display average turnaround time and waiting time
        double avgTurnaroundTime = 0, avgWaitingTime = 0;
        for (int i = 0; i < n; i++) {
            avgTurnaroundTime += TAT[i];
            avgWaitingTime += WT[i];
        }
        avgTurnaroundTime /= n;
        avgWaitingTime /= n;

        System.out.println("\nAverage Turnaround Time: " + avgTurnaroundTime);
        System.out.println("Average Waiting Time: " + avgWaitingTime);
    }
}
