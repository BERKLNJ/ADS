package Assignment1;

import java.util.Scanner;

public class Task3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a number to check: ");
        int n = scanner.nextInt();
        if (n <= 1) {
            System.out.println("Composite");
        } else {
            boolean isPrime = checkPrime(n, n / 2);
            System.out.println(isPrime ? "Prime" : "Composite");
        }
        scanner.close();
    }

    public static boolean checkPrime(int n, int divisor) {
        if (divisor == 1) return true;
        if (n % divisor == 0) return false;
        return checkPrime(n, divisor - 1);
    }
}