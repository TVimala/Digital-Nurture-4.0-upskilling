import java.util.*;

public class LambdaSortExample {
    public static void main(String[] args) {
        List<String> names = new ArrayList<>(Arrays.asList("Alice", "Bob", "Charlie"));
        Collections.sort(names, (s1, s2) -> s1.compareTo(s2));
        // Display the sorted list
        System.out.println("Sorted names: " + names);
    }
}
