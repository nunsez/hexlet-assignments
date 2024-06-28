package exercise;

import java.util.Map;
import java.util.logging.Logger;

class App {
    private static final Logger LOGGER = Logger.getLogger("AppLogger");

    // BEGIN
    public static Map<String, Integer> getMinMax(int[] numbers) {

        var minThread = new MinThread(numbers);
        var maxThread = new MaxThread(numbers);

        minThread.start();
        LOGGER.info("Thread " + minThread.getName() + " started");
        maxThread.start();
        LOGGER.info("Thread " + maxThread.getName() + " started");

        try {
            minThread.join();
            LOGGER.info("Thread " + minThread.getName() + " finished");
            maxThread.join();
            LOGGER.info("Thread " + maxThread.getName() + " finished");
        } catch (InterruptedException e) {
            LOGGER.warning("Поток был прерван");
        }

        var result = Map.of(
            "min", minThread.getMin(),
            "max", maxThread.getMax()
        );

        LOGGER.info("Result: " + result.toString());

        return result;
    }
    // END
}
