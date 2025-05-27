public class MethodOverloadingDemo {
    // Method 1: add two integers
    public static int add(int a, int b) {
        return a + b;
    }
    // Method 2: add two doubles
    public static double add(double a, double b) {
        return a + b;
    }
    // Method 3: add three integers
    public static int add(int a, int b, int c) {
        return a + b + c;
    }
    public static void main(String[] args) {
        // Calling overloaded methods
        int sum1 = add(5, 10);                // Two integers
        double sum2 = add(3.5, 7.2);          // Two doubles
        int sum3 = add(2, 4, 6);              // Three integers
        System.out.println("Sum of 5 and 10 (int): " + sum1);
        System.out.println("Sum of 3.5 and 7.2 (double): " + sum2);
        System.out.println("Sum of 2, 4, and 6 (int): " + sum3);
    }
}
