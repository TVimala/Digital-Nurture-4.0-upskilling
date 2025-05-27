import java.util.*;
import java.util.stream.Collectors;

public class StreamEvenNumbers {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(10, 15, 20, 25, 30, 35, 40);

        List<Integer> evenNumbers = numbers.stream()
                .filter(n -> n % 2 == 0)        // Filter even numbers
                .collect(Collectors.toList());  // Collect results into a list

        System.out.println("Even numbers: " + evenNumbers);
    }
}
