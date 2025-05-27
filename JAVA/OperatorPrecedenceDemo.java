public class OperatorPrecedenceDemo {
    public static void main(String[] args) {
        int result1 = 10 + 5 * 2;
        int result2 = (10 + 5) * 2;
        int result3 = 100 / 5 + 3 * 4 - 8;
        int result4 = 100 / (5 + 3) * 4;
        System.out.println("Expression 1: 10 + 5 * 2 = " + result1);
        System.out.println("\nExpression 2: (10 + 5) * 2 = " + result2);
        System.out.println("\nExpression 3: 100 / 5 + 3 * 4 - 8 = " + result3);
        System.out.println("\nExpression 4: 100 / (5 + 3) * 4 = " + result4);
    }
}
