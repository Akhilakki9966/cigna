import java.io.*;
import java.util.concurrent.*;

public class ComplexJavaProgram {
    private static final int THREAD_COUNT = 4;
    
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(THREAD_COUNT);
        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                executor.submit(new DataProcessor(line));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        executor.shutdown();
    }
}

class DataProcessor implements Runnable {
    private final String data;
    
    public DataProcessor(String data) {
        this.data = data;
    }
    
    @Override
    public void run() {
        // Simulate complex processing
        try {
            Thread.sleep(500);
            System.out.println(Thread.currentThread().getName() + " processed: " + processData(data));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    
    private String processData(String input) {
        return new StringBuilder(input).reverse().toString().toUpperCase();
    }
}
