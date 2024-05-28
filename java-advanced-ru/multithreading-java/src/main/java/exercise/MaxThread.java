package exercise;

import java.util.Arrays;

// BEGIN
public class MaxThread extends Thread {

    private int[] initData;
    private int maxValue;

    public MaxThread(int[] data) {
        this.initData = data;
    }

    @Override
    public void run() {
        this.maxValue = Arrays.stream(initData).max().getAsInt();
    }

    public int getMaxValue() {
        return maxValue;
    }
}
// END
