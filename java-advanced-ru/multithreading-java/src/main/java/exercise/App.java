package exercise;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
import java.util.logging.Level;

class App {
    private static final Logger LOGGER = Logger.getLogger("AppLogger");

    // BEGIN
    public static Map<String, Integer> getMinMax(int[] data) throws InterruptedException {

        Map<String, Integer> result = new HashMap<>();

        MaxThread maxThread = new MaxThread(data);
        MinThread minThread = new MinThread(data);

        maxThread.start();
        minThread.start();

        maxThread.join();
        minThread.join();

        result.put("min", minThread.getMinValue());
        result.put("max", maxThread.getMaxValue());

        return result;
    }
    // END
}
