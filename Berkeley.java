import mpi.*;

public class BerkeleyMPI {

    public static void main(String[] args) throws Exception {

        MPI.Init(args);

        int rank = MPI.COMM_WORLD.Rank();
        int size = MPI.COMM_WORLD.Size();

        int[] clockValue = new int[1];
        int[] difference = new int[1];

        // Worker Nodes generate clock values
        if (rank != 0) {

            // Example random clock time
            clockValue[0] = (int)(Math.random() * 100);

            System.out.println("Node " + rank +
                    " initial clock value: " + clockValue[0]);

            // Send clock value to master node
            MPI.COMM_WORLD.Send(clockValue, 0,
                    1, MPI.INT, 0, 0);

            // Receive adjustment difference from master
            MPI.COMM_WORLD.Recv(difference, 0,
                    1, MPI.INT, 0, 0);

            // Adjust local clock
            clockValue[0] = clockValue[0] - difference[0];

            System.out.println("Node " + rank +
                    " adjusted clock value: " + clockValue[0]);

        } else {

            // Master Node
            int total = 0;

            int[] receivedClock = new int[size];

            // Receive clock values from all worker nodes
            for (int i = 1; i < size; i++) {

                int[] temp = new int[1];

                MPI.COMM_WORLD.Recv(temp, 0,
                        1, MPI.INT, i, 0);

                receivedClock[i] = temp[0];

                total += temp[0];

                System.out.println("Master received from Node "
                        + i + ": " + temp[0]);
            }

            // Calculate average clock time
            int average = total / (size - 1);

            System.out.println("\nAverage Clock Time: " + average);

            // Send adjustment differences
            for (int i = 1; i < size; i++) {

                difference[0] =
                        receivedClock[i] - average;

                MPI.COMM_WORLD.Send(difference, 0,
                        1, MPI.INT, i, 0);
            }
        }

        MPI.Finalize();
    }
}