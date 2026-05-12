package Assignment4;

public class Main {
    public static void main(String[] args) {
        int[] sizes = {10, 30, 100};
        Experiment experiment = new Experiment();

        for (int size : sizes) {
            System.out.println("\nTesting Graph Size: " + size + " ");
            Graph g = new Graph();

            for (int i = 0; i < size; i++) {
                g.addVertex(new Vertex(i));
            }

            for (int i = 0; i < size - 1; i++) {
                g.addEdge(i, i + 1);
            }

            for (int i = 0; i < size; i += 5) {
                if (i + 2 < size) g.addEdge(i, i + 2);
            }

            experiment.runTraversals(g, 0, size == 10);
        }
    }
}