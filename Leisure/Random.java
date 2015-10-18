//package Leisure;

/**
 * @author Jacob
 * @since 8/2/2015
 */
public class Random {

    private final java.util.Random random;

    public Random() {
        random = new java.util.Random();
    }

    public int nextInt(int max) {
        return random.nextInt(max);
    }

    public int nextInt(int min, int max) {
        return min + nextInt(max - min);
    }

    public double nextDouble() {
        return random.nextDouble();
    }

    public boolean nextBoolean() {
        return random.nextBoolean();
    }
}