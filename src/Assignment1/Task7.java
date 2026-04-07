package Assignment1;

import java.util.Scanner;

public class Task7 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of elements: ");
        int n = scanner.nextInt();
        System.out.print("Enter the elements: ");
        System.out.print("Output: ");
        reverseOutput(n, scanner);
        System.out.println();
        scanner.close();
    }

    public static void reverseOutput(int n, Scanner scanner) {
        if (n == 0) return;
        int currentElement = scanner.nextInt();
        reverseOutput(n - 1, scanner);
        System.out.print(currentElement + " ");
    }
}