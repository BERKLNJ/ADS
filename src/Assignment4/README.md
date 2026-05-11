How does graph size affect performance? 
As the number of vertices (V) and edges (E) increases, the execution time increases linearly, adhering to the O(V+E) complexity.

Which traversal is faster?

In small memory-local graphs, performance is often similar. However, BFS may incur slightly more overhead due to queue operations, while DFS uses the call stack.

Expected Complexity:

Yes, results match O(V+E). In an adjacency list, every vertex is visited once, and every edge is explored once.


When is BFS preferred over DFS?

BFS is preferred when looking for the shortest path in an unweighted graph. DFS is preferred for pathfinding in deep graphs or finding strongly connected components.


Limitations of DFS:

DFS can get stuck in infinite paths in infinite graphs (unless limited) and does not guarantee the shortest path. It can also lead to StackOverflowError on very deep graphs due to recursion depth.