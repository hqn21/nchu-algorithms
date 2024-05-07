public class StopWatch {
    private final long start = System.nanoTime();

    public double elapsedTime() {
        long now = System.nanoTime();
        return now - start;
    }
}