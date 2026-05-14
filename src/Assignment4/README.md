# Graph Traversal and Representation System

## A. Project Overview

This project implements a fundamental graph data structure to model complex relationships between entities. A **graph** consists of **vertices** (nodes) and **edges** (connections). To explore these connections, we implemented two primary traversal algorithms:

Breadth-First Search (BFS): Explores the graph layer-by-layer, visiting all neighbors of a node before moving deeper.


Depth-First Search (DFS): Explores as far as possible along each branch before backtracking.



## B. Class Descriptions

`Vertex`: Represents a node with a unique integer `id`.


`Edge`: Defines a directed connection from a `source` vertex to a `destination` vertex.


`Graph`: The core engine that manages the structure using an **Adjacency List**. This representation uses a `Map` where each vertex ID maps to a list of its adjacent neighbors, ensuring efficient memory usage.


`Experiment`: A utility class used to automate tests across different graph sizes and measure performance using `System.nanoTime()`.



## C. Algorithm Descriptions

### 1. Breadth-First Search (BFS)
Step-by-Step:
1. Start at a given node and mark it as visited.


2. Add the node to a Queue.


3. While the queue is not empty, dequeue a node and visit all its unvisited neighbors.


4. Repeat until all reachable nodes are processed.



Use Case: Finding the shortest path in unweighted graphs.



Time Complexity: $O(V + E)$.



### 2. Depth-First Search (DFS)

Step-by-Step:
1. Start at a given node and mark it as visited.


2. Recursively visit every unvisited neighbor of the current node.


3. If a node has no unvisited neighbors, backtrack to the previous node.




Use Case: Detecting cycles or solving puzzles/mazes.


Time Complexity: $O(V + E)$.



## D. Experimental Results

The following data was collected by measuring the execution time in nanoseconds ($ns$) across three graph sizes:

| Vertices | BFS Execution Time | DFS Execution Time |
| --- | --- | --- |
| **10 Nodes** | 991,800 ns | 194,300 ns |
| **30 Nodes** | 435,500 ns | 599,600 ns |
| **100 Nodes** | (Pending) | 1,046,400 ns |

### Observations:

Graph Size Impact: As the number of vertices increased, the total execution time generally followed a linear trend.


Performance: DFS appeared faster in the small-scale test, but BFS performance stabilized as the graph grew.


Complexity: The results align with the expected $O(V + E)$ complexity, as the time scales with the growth of nodes and connections.


## F. Reflection Section

During this assignment, I learned the practical differences between queue-based and recursion-based traversals. Implementing the adjacency list taught me how to store graph data efficiently without wasting space on empty connections.

The biggest challenge was managing the `visited` sets to prevent infinite loops in directed graphs and ensuring the `nanoTime()` measurements were accurate despite JVM warm-up fluctuations. Overall, I now understand why BFS is preferred for finding the "nearest" nodes while DFS is better for exploring deep into a network.
