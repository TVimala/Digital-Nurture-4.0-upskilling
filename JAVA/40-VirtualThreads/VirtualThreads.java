import java.util.ArrayList;
import java.util.List;

public class VirtualThreads {
    private static final int THREAD_COUNT = 100_000;

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Starting traditional threads...");
        long traditionalStart = System.currentTimeMillis();
        List<Thread> traditionalThreads = new ArrayList<>();

        for (int i = 0; i < THREAD_COUNT; i++) {
            Thread t = new Thread(() -> {
                // For demo, just print thread info; you can comment this out to reduce console spam
                // System.out.println("Traditional thread: " + Thread.currentThread().getName());
            });
            t.start();
            traditionalThreads.add(t);
        }
        for (Thread t : traditionalThreads) {
            t.join();
        }
        long traditionalEnd = System.currentTimeMillis();
        System.out.println("Traditional threads done in " + (traditionalEnd - traditionalStart) + " ms");

        System.out.println("\nStarting virtual threads...");
        long virtualStart = System.currentTimeMillis();
        List<Thread> virtualThreads = new ArrayList<>();

        for (int i = 0; i < THREAD_COUNT; i++) {
            Thread vt = Thread.startVirtualThread(() -> {
                // For demo, just print thread info; comment out to reduce console output
                // System.out.println("Virtual thread: " + Thread.currentThread().getName());
            });
            virtualThreads.add(vt);
        }
        for (Thread vt : virtualThreads) {
            vt.join();
        }
        long virtualEnd = System.currentTimeMillis();
        System.out.println("Virtual threads done in " + (virtualEnd - virtualStart) + " ms");
    }
}
