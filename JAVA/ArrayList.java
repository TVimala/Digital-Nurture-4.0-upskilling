import java.util.ArrayList;
import java.util.Scanner;

public class StudentNamesList {
    public static void main(String[] args) {
        ArrayList<String> names = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        String input;

        System.out.println("Enter student names (type 'done' to finish):");
        while (true) {
            input = scanner.nextLine();
            if (input.equalsIgnoreCase("done")) {
                break;
            }
            names.add(input);
        }

        System.out.println("\nStudent names entered:");
        for (String name : names) {
            System.out.println(name);
        }
    }
}
