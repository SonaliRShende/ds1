import java.util.*;

class Process {
    int id;
    boolean active;

    Process(int id) {
        this.id = id;
        this.active = true;
    }
}

public class Ring {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of processes: ");
        int n = sc.nextInt();

        Process[] p = new Process[n];

        System.out.println("Enter process IDs:");
        for (int i = 0; i < n; i++) {
            int id = sc.nextInt();
            p[i] = new Process(id);
        }

        // Sort processes by ID
        Arrays.sort(p, Comparator.comparingInt(a -> a.id));

        // Make highest ID process inactive (simulate failure)
        p[n - 1].active = false;

        System.out.print("Enter initiator process index (0 to " + (n - 1) + "): ");
        int initiator = sc.nextInt();

        System.out.println("\n--- Ring Election Process ---\n");

        int i = initiator;
        int maxId = -1;

        do {
            if (p[i].active) {
                System.out.println("Process " + p[i].id + " passes ELECTION message");
                if (p[i].id > maxId) {
                    maxId = p[i].id;
                }
            }
            i = (i + 1) % n;
        } while (i != initiator);

        System.out.println("\nProcess " + maxId + " becomes the new COORDINATOR");

        sc.close();
    }
}