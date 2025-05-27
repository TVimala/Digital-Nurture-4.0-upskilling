public class SimpleClass {
    public int add(int a, int b) {
        return a + b;
    }

    public static void main(String[] args) {
        SimpleClass sc = new SimpleClass();
        int result = sc.add(5, 3);
        System.out.println("Result: " + result);
    }
}
