import org.osbot.rs07.script.Script;

import java.util.Random;

public class TimeOut {
    private final int seed;
    private final Random random;

    public TimeOut(final int seed) {
        this.seed = seed;
        random = new Random();
    }

    /**
     * @return {@code int} in milliseconds to be returned in {@link Script#onLoop()}
     */
    public int generateTimeout() {
        return random.nextInt(seed * 100);
    }
}
