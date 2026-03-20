import java.util.Scanner;

public class Task8 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a string: ");
        String s = scanner.next();

        if (isAllDigits(s, 0)) {
            System.out.println("Output: Yes");
        } else {
            System.out.println("Output: No");
        }
        scanner.close();
    }

    public static boolean isAllDigits(String s, int index) {
        if (index == s.length()) return true;

        char c = s.charAt(index);
        if (c < '0' || c > '9') return false;

        return isAllDigits(s, index + 1);
    }
}