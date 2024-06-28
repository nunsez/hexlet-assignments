package exercise;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
import java.util.logging.Level;

class App {
    private static final Logger LOGGER = Logger.getLogger("AppLogger");

    // BEGIN
    public static Map<String, Integer> getMinMax(int[] numbers) {
        var min = new MinThread(numbers);
        var max = new MaxThread(numbers);

        min.start();
        max.start();

        try {
            min.join();
            max.join();
        } catch (InterruptedException ignored) {}

        var result = new HashMap<String, Integer>();
        result.put("min", min.getMin());
        result.put("max", max.getMax());

        return result;
    }
    // END
}
