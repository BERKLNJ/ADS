package Assignment3;
import java.util.Arrays;

public class Expirement {
    private final Sorter sorter;
    private final Searcher searcher;

    public Expirement(Sorter sorter, Searcher searcher) {
        this.sorter = sorter;
        this.searcher = searcher;
    }

    public long measureSortTime(int[] arr, String type) {
        long startTime = System.nanoTime();
        if (type.equalsIgnoreCase("basic")) {
            sorter.basicSort(arr);
        } else if (type.equalsIgnoreCase("advanced")) {
            sorter.advancedSort(arr);
        }
        long endTime = System.nanoTime();
        return endTime - startTime;
    }

    public long measureSearchTime(int[] arr, int target) {
        long startTime = System.nanoTime();
        searcher.search(arr, target);
        long endTime = System.nanoTime();
        return endTime - startTime;
    }

    public void runAllExperiments() {
        int[] sizes = {10, 100, 1000};

        System.out.println("Sorting Performance (In nanoseconds)");

        for (int size : sizes) {
            System.out.println("\nArray Size: " + size);
            int[] randomForBasic = sorter.generateRandomArray(size);
            int[] randomForAdvanced = Arrays.copyOf(randomForBasic, size);

            int[] sortedForBasic = Arrays.copyOf(randomForBasic, size);
            sorter.advancedSort(sortedForBasic);
            int[] sortedForAdvanced = Arrays.copyOf(sortedForBasic, size);

            long basicRandomTime = measureSortTime(randomForBasic, "basic");
            long basicSortedTime = measureSortTime(sortedForBasic, "basic");

            long advRandomTime = measureSortTime(randomForAdvanced, "advanced");
            long advSortedTime = measureSortTime(sortedForAdvanced, "advanced");

            System.out.printf("Basic Sort Random: %d ns, Sorted: %d ns\n", basicRandomTime, basicSortedTime);
            System.out.printf("Advanced Sort Random: %d ns, Sorted: %d ns\n", advRandomTime, advSortedTime);

            int target = sortedForAdvanced[size / 2];
            long searchTime = measureSearchTime(sortedForAdvanced, target);
            System.out.printf("Binary Search Time: %d ns\n", searchTime);
        }
    }
}