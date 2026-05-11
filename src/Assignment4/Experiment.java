package Assignment4;

public class Experiment {
    public void runTraversals(Graph g, int startNode, boolean printOrder) {
        if (printOrder) System.out.print("BFS Order: ");
        long startBfs = System.nanoTime();
        g.bfs(startNode);
        long endBfs = System.nanoTime();
        if (printOrder) System.out.println();

        if (printOrder) System.out.print("DFS Order: ");
        long startDfs = System.nanoTime();
        g.dfs(startNode);
        long endDfs = System.nanoTime();
        if (printOrder) System.out.println();

        System.out.println("BFS Time: " + (endBfs - startBfs) + " ns");
        System.out.println("DFS Time: " + (endDfs - startDfs) + " ns");
    }

    // This would be called by Main to automate tests on 10, 30, 100 nodes [cite: 75]
}