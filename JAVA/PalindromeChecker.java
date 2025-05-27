import java.util.Scanner;

public class PalindromeChecker {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // Prompt the user for a string
        System.out.print("Enter a string: ");
        String input = scanner.nextLine();
        // Remove non-alphanumeric characters and convert to lowercase
        String cleaned = input.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
        // Reverse the cleaned string
        String reversed = new StringBuilder(cleaned).reverse().toString();
        if (cleaned.equals(reversed)) {
            System.out.println("The string is a palindrome.");
        } else {
            System.out.println("The string is not a palindrome.");
        }
        scanner.close();
    }
}
