import java.util.Scanner;

public class Task2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of elements: ");
        int n = scanner.nextInt();

        int[] arr = new int[n];
        System.out.print("Enter " + n + " elements: ");
        readArray(arr, 0, scanner);

        int sum = calculateSum(arr, 0);
        double average = (double) sum / n;

        System.out.println("Output: " + average);
        scanner.close();
    }

    public static void readArray(int[] arr, int index, Scanner scanner) {
        if (index == arr.length) return;
        arr[index] = scanner.nextInt();
        readArray(arr, index + 1, scanner);
    }

    public static int calculateSum(int[] arr, int index) {
        if (index == arr.length) return 0;
        return arr[index] + calculateSum(arr, index + 1);
    }
}