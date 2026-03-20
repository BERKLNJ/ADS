import java.util.Scanner;

public class Task9 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a string: ");
        String s = scanner.next();

        System.out.println("Output: " + countCharacters(s));
        scanner.close();
    }

    public static int countCharacters(String s) {
        if (s.isEmpty()) return 0;
        return 1 + countCharacters(s.substring(1));
    }
}