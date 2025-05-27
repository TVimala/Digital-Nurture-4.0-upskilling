import java.util.HashMap;
import java.util.Scanner;

public class StudentHashMap {
    public static void main(String[] args) {
        HashMap<Integer, String> studentMap = new HashMap<>();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter student ID and name pairs (type ID 0 to stop):");
        while (true) {
            System.out.print("Enter student ID: ");
            int id = scanner.nextInt();
            scanner.nextLine(); // consume newline
            if (id == 0) {
                break;
            }

            System.out.print("Enter student name: ");
            String name = scanner.nextLine();

            studentMap.put(id, name);
        }

        System.out.print("\nEnter an ID to retrieve the student's name: ");
        int searchId = scanner.nextInt();

        if (studentMap.containsKey(searchId)) {
            System.out.println("Student name: " + studentMap.get(searchId));
        } else {
            System.out.println("No student found with ID " + searchId);
        }
    }
}
