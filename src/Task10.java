import java.util.Scanner;

public class Task10 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter two numbers to find their GCD: ");
        int a = scanner.nextInt();
        int b = scanner.nextInt();

        System.out.println("Output: " + findGCD(a, b));
        scanner.close();
    }

    public static int findGCD(int a, int b) {
        if (b == 0) return a;
        return findGCD(b, a % b);
    }
}