import java.util.concurrent.*;
import java.util.*;

public class CallableExample {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        int numberOfTasks = 5;

        // Create a fixed thread pool with 3 threads
        ExecutorService executor = Executors.newFixedThreadPool(3);

        // List to hold Future objects
        List<Future<Integer>> futures = new ArrayList<>();

        // Submit 5 Callable tasks
        for (int i = 1; i <= numberOfTasks; i++) {
            int taskId = i;
            Callable<Integer> task = () -> {
                System.out.println("Task " + taskId + " is running on thread: " + Thread.currentThread().getName());
                Thread.sleep(1000); // Simulate delay
                return taskId * taskId; // Return square of task ID
            };
            futures.add(executor.submit(task));
        }

        // Collect results from Future
        for (int i = 0; i < futures.size(); i++) {
            Future<Integer> future = futures.get(i);
            Integer result = future.get(); // Waits for the task to complete
            System.out.println("Result of Task " + (i + 1) + ": " + result);
        }

        // Shut down the executor
        executor.shutdown();
    }
}
