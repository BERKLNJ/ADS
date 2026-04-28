# Assignment 3: Sorting and Searching Algorithm Analysis System

## A. Project Overviewrstand how theoretical Big-O complexity translates to practical performance.

This project involves the implementation and performance analysis of fundamental sorting and searching algorithms. The purpose of this experiment is to measure execution times across different datasets to unde
The three algorithms selected for this analysis are:
* **Basic Sorting:** Bubble Sort
* **Advanced Sorting:** Merge Sort
* **Searching:** Binary Search

## B. Algorithm Descriptions

### 1. Bubble Sort (Basic Sort)
* **How it works:** Bubble sort is a simple comparison-based algorithm that repeatedly steps through the array, compares adjacent elements, and swaps them if they are in the wrong order. This process repeats until the array is fully sorted. Our implementation includes an optimization flag (`swapped`) that stops the algorithm early if a full pass is made without any swaps.
* **Time Complexity:** * Worst and Average Case: O(n^2)
    * Best Case (already sorted): O(n)

### 2. Merge Sort (Advanced Sort)
* **How it works:** Merge sort is a divide-and-conquer algorithm. It recursively divides the input array into two halves until each sub-array contains only a single element. It then repeatedly merges these sub-arrays to produce new sorted sub-arrays until there is only one sorted array remaining.
* **Time Complexity:** * Best, Average, and Worst Case: O(n log n)

### 3. Binary Search
* **How it works:** Binary search finds the position of a target value within a sorted array. It compares the target value to the middle element of the array. If they are unequal, the half in which the target cannot lie is eliminated, and the search continues on the remaining half, repeatedly dividing the search space in two until the target is found.
* **Time Complexity:** * Worst and Average Case: O(log n)
    * Best Case (target is the middle element): O(1)

## C. Experimental Results

*Note: The times below were measured using `System.nanoTime()` and may vary slightly between different hardware runs.*

### Execution Time Data

| Array Size | Input Type | Bubble Sort (ns) | Merge Sort (ns) | Binary Search (ns) |
|------------|------------|------------------|-----------------|--------------------|
| 10 (Small) | Random     | 7300             | 7100            | N/A                |
| 10 (Small) | Sorted     | 900              | 5400            | 6800               |
| 100 (Med)  | Random     | 415500           | 104500          | N/A                |
| 100 (Med)  | Sorted     | 5900             | 72600           | 1900               |
| 1000 (Lrg) | Random     | 8189000          | 187200          | N/A                |
| 1000 (Lrg) | Sorted     | 7900             | 107900          | 2400               |
### Performance Analysis

1. **Which sorting algorithm performed faster? Why?**
   Merge Sort performed significantly faster on the medium and large datasets. This aligns with its O(n log n) time complexity, which handles scaling much better than Bubble Sort's O(n^2) complexity, where the number of necessary comparisons grows exponentially with the array size.

2. **How does performance change with input size?**
   As the input size increases from 10 to 1000, Bubble Sort's execution time spikes dramatically. Merge Sort's execution time also increases, but at a much flatter, more efficient curve. Binary Search remains virtually instantaneous regardless of whether the array has 10 or 1000 elements.

3. **How does sorted vs unsorted data affect performance?**
   Because the Bubble Sort implementation uses an early-exit optimization flag, it performs exceptionally well (O(n)) on already sorted data. Merge Sort, however, takes roughly the same amount of time regardless of the data's initial state because it blindly divides and merges the array every time.

4. **Do the results match the expected Big-O complexity?**
   Yes. The empirical data recorded via `nanoTime()` directly mirrors the theoretical Big-O expectations, specifically highlighting the inefficiency of O(n^2) algorithms on larger datasets.

5. **Which searching algorithm is more efficient? Why?**
   Binary Search is far more efficient than a standard Linear Search because its logarithmic nature (O(log n)) means it cuts the searchable data pool in half with every single comparison, rather than checking each element one by one.

6. **Why does Binary Search require a sorted array?**
   The entire logic of Binary Search relies on knowing that all elements to the left of the midpoint are smaller and all elements to the right are larger. If the array is unsorted, eliminating half of the dataset based on a midpoint comparison is mathematically invalid.