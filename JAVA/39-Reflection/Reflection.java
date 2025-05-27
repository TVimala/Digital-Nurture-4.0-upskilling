import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

public class Reflection {
    public static void main(String[] args) throws Exception {
        // Load class dynamically
        Class<?> personClass = Class.forName("Person");
        
        // Create an instance using the no-arg constructor
        Object personInstance = personClass.getDeclaredConstructor().newInstance();
        
        // List all declared methods
        System.out.println("Declared methods in Person:");
        Method[] methods = personClass.getDeclaredMethods();
        for (Method method : methods) {
            System.out.print(method.getName() + "(");
            
            // Print parameter types
            Parameter[] params = method.getParameters();
            for (int i = 0; i < params.length; i++) {
                System.out.print(params[i].getType().getSimpleName());
                if (i < params.length - 1) System.out.print(", ");
            }
            System.out.println(")");
        }
        
        // Invoke sayHello() method dynamically
        Method sayHelloMethod = personClass.getDeclaredMethod("sayHello");
        sayHelloMethod.invoke(personInstance);
        
        // Invoke setAge(int) dynamically
        Method setAgeMethod = personClass.getDeclaredMethod("setAge", int.class);
        setAgeMethod.invoke(personInstance, 25);
        
        // Invoke getAge() and print the result
        Method getAgeMethod = personClass.getDeclaredMethod("getAge");
        int age = (int) getAgeMethod.invoke(personInstance);
        System.out.println("Age after setAge: " + age);
    }
}
