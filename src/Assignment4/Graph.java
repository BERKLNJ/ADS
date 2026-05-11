package Assignment4;
import java.util.*;

public class Graph {
    private Map<Integer, List<Integer>> adjList;
    private Map<Integer, Vertex> vertices;

    public Graph() {
        this.adjList = new HashMap<>();
        this.vertices = new HashMap<>();
    }

    public void addVertex(Vertex v) {
        vertices.put(v.getId(), v);
        adjList.putIfAbsent(v.getId(), new ArrayList<>());
    }

    public void addEdge(int from, int to) {
        // Directed edge; for undirected, add both directions [cite: 63]
        if (adjList.containsKey(from) && adjList.containsKey(to)) {
            adjList.get(from).add(to);
        }
    }

    public void printGraph() {
        for (int id : adjList.keySet()) {
            System.out.println(id + " -> " + adjList.get(id));
        }
    }

    // Breadth-First Search Implementation [cite: 41]
    public void bfs(int start) {
        if (!vertices.containsKey(start)) return;

        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();

        visited.add(start);
        queue.add(start);

        while (!queue.isEmpty()) {
            int current = queue.poll();
            System.out.print(current + " "); // Printing for traversal order [cite: 71]

            for (int neighbor : adjList.get(current)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }
    }

    // Depth-First Search Implementation [cite: 42]
    public void dfs(int start) {
        if (!vertices.containsKey(start)) return;
        Set<Integer> visited = new HashSet<>();
        dfsRecursive(start, visited);
    }

    private void dfsRecursive(int current, Set<Integer> visited) {
        visited.add(current);
        System.out.print(current + " ");

        for (int neighbor : adjList.get(current)) {
            if (!visited.contains(neighbor)) {
                dfsRecursive(neighbor, visited);
            }
        }
    }
}