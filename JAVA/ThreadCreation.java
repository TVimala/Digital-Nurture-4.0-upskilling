class MessagePrinter implements Runnable {
    private String message;
    private int times;

    public MessagePrinter(String message, int times) {
        this.message = message;
        this.times = times;
    }

    @Override
    public void run() {
        for (int i = 0; i < times; i++) {
            System.out.println(message + " (iteration " + (i + 1) + ")");
            try {
                Thread.sleep(500); // Pause for half a second for clearer output
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class Threads {
    public static void main(String[] args) {
        Thread thread1 = new Thread(new MessagePrinter("Thread 1 message", 5));
        Thread thread2 = new Thread(new MessagePrinter("Thread 2 message", 5));

        thread1.start();
        thread2.start();
    }
}