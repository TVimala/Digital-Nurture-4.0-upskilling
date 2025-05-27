import java.util.*;
import java.util.stream.Collectors;

public class RecordExample {
    // Define a record named Person
    public record Person(String name, int age) {}

    public static void main(String[] args) {
        // Create instances of Person
        Person p1 = new Person("Alice", 23);
        Person p2 = new Person("Bob", 17);
        Person p3 = new Person("Charlie", 30);
        // Print instances
        System.out.println(p1);
        System.out.println(p2);
        System.out.println(p3);
        List<Person> people = List.of(p1, p2, p3);
        // Filter based on age (e.g., age >= 18) using Streams
        List<Person> adults = people.stream()
                                   .filter(person -> person.age() >= 18)
                                   .collect(Collectors.toList());

        System.out.println("Adults: " + adults);
    }
}
