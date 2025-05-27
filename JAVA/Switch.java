public class PatternMatchingSwitchExample {

    public static void checkObjectType(Object obj) {
        String result = switch (obj) {
            case Integer i -> "Integer with value: " + i;
            case String s -> "String with value: \"" + s + "\"";
            case Double d -> "Double with value: " + d;
            case null -> "Object is null";
            default -> "Unknown type: " + obj.getClass().getSimpleName();
        };
        System.out.println(result);
    }

    public static void main(String[] args) {
        checkObjectType(42);
        checkObjectType("Hello");
        checkObjectType(3.14);
        checkObjectType(true);
        checkObjectType(null);
    }
}
