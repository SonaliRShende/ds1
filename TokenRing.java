import java.util.*;

class Process {
    int id;
    boolean hasToken;

    Process(int id) {
        this.id = id;
        this.hasToken = false;
    }
}

public class TokenRing {

    static void passToken(Process[] processes, int n, int start) {

        int tokenHolder = start;

        while (true) {

            Process current = processes[tokenHolder];

            // Token received
            System.out.println("\nToken received by Process " + current.id);

            // Critical Section
            System.out.println("Process " + current.id + " ENTERING Critical Section...");
            try {
                Thread.sleep(500);
            } catch (Exception e) {}

            System.out.println("Process " + current.id + " EXITING Critical Section...");

            // Pass token to next process
            tokenHolder = (tokenHolder + 1) % n;

            System.out.println("Token passed to Process " + processes[tokenHolder].id);

            // Stop condition (1 full cycle)
            if (tokenHolder == start) {
                System.out.println("\n--- One full cycle completed ---");
                break;
            }
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of processes: ");
        int n = sc.nextInt();

        Process[] processes = new Process[n];

        // Create processes with IDs
        System.out.println("Enter Process IDs:");
        for (int i = 0; i < n; i++) {
            int id = sc.nextInt();
            processes[i] = new Process(id);
        }

        System.out.print("Enter process index to start token (0 to n-1): ");
        int start = sc.nextInt();

        System.out.println("\n--- Token Ring Execution ---");

        passToken(processes, n, start);

        sc.close();
    }
}