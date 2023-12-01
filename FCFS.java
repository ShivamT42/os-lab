import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

class Process {
    int id;
    int originalArrivalTime;
    int arrivalTime;
    int burstTime;
    int completionTime;
    int turnaroundTime;
    int waitingTime;
}

public class FCFS {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of processes: ");
        int n = scanner.nextInt();

        List<Process> processes = new ArrayList<>();

        // Input arrival time and burst time for each process
        for (int i = 0; i < n; i++) {
            Process process = new Process();
            process.id = i + 1;
            System.out.print("Enter Arrival Time for Process P" + process.id + ": ");
            process.arrivalTime = scanner.nextInt();
            process.originalArrivalTime = process.arrivalTime;
            System.out.print("Enter Burst Time for Process P" + process.id + ": ");
            process.burstTime = scanner.nextInt();
            processes.add(process);
        }

        // Sort processes based on arrival time
        Collections.sort(processes, Comparator.comparingInt(p -> p.originalArrivalTime));

        // Calculate finish time, turnaround time, and waiting time
        int currentTime = 0;
        for (Process process : processes) {
            if (currentTime < process.arrivalTime) {
                currentTime = process.arrivalTime;
            }
            process.completionTime = currentTime + process.burstTime;
            process.turnaroundTime = process.completionTime - process.originalArrivalTime;
            process.waitingTime = process.turnaroundTime - process.burstTime;
            currentTime = process.completionTime;
        }

        // Display results
        System.out.println("\nP_ID\t\tAT\t\tBT\t\tCT\t\tTAT\t\tWT");
        for (Process process : processes) {
            System.out.println("P" + process.id + "\t\t" + process.originalArrivalTime + "\t\t" +
                    process.burstTime + "\t\t" + process.completionTime + "\t\t" +
                    process.turnaroundTime + "\t\t" + process.waitingTime);
        }

        // Draw Gantt Chart
        System.out.println("\nGantt Chart:");
        System.out.print("|");
        for (Process process : processes) {
            for (int j = 0; j < process.burstTime; j++) {
                System.out.print(" P" + process.id + " |");
            }
            System.out.print("|");
        }
        System.out.println();

        scanner.close();
    }
}
