public class TypeCastingDemo {
    public static void main(String[] args) {
        // Double to int (Explicit casting)
        double originalDouble = 9.78;
        int convertedInt = (int) originalDouble;
        System.out.println("Original double: " + originalDouble);
        System.out.println("Converted to int: " + convertedInt);
        // Int to double (Implicit casting)
        int originalInt = 25;
        double convertedDouble = originalInt;
        System.out.println("Original int: " + originalInt);
        System.out.println("Converted to double: " + convertedDouble);
    }
}
