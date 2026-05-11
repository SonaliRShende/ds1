import mpi.*;

public class MPISum {
    public static void main(String args[]) throws Exception {

        MPI.Init(args);

        int rank = MPI.COMM_WORLD.Rank();
        int size = MPI.COMM_WORLD.Size();

        int N = 8;
        int[] data = new int[N];
        int elementsPerProcess = N / size;

        int[] subArray = new int[elementsPerProcess];

        if (rank == 0) {
            for (int i = 0; i < N; i++) {
                data[i] = i + 1;
            }

            for (int i = 1; i < size; i++) {
                MPI.COMM_WORLD.Send(data, i * elementsPerProcess,
                        elementsPerProcess, MPI.INT, i, 0);
            }

            System.arraycopy(data, 0, subArray, 0, elementsPerProcess);

        } else {
            MPI.COMM_WORLD.Recv(subArray, 0,
                    elementsPerProcess, MPI.INT, 0, 0);
        }

        int localSum = 0;
        for (int i = 0; i < elementsPerProcess; i++) {
            localSum += subArray[i];
        }

        System.out.println("Process " + rank + " partial sum = " + localSum);

        int[] total = new int[1];
        MPI.COMM_WORLD.Reduce(new int[]{localSum}, 0,
                total, 0, 1, MPI.INT, MPI.SUM, 0);

        if (rank == 0) {
            System.out.println("Final Sum = " + total[0]);
        }

        MPI.Finalize();
    }
}