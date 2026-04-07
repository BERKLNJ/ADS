import java.util.Scanner;

public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter base (a) and exponent (n): ");
        int a = scanner.nextInt();
        int n = scanner.nextInt();

        System.out.println("Output: " + power(a, n));
        scanner.close();
    }

    public static long power(int a, int n) {
        if (n == 0) return 1;
        return a * power(a, n - 1);
}