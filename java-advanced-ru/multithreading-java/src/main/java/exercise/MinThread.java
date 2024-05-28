package exercise;

import java.util.Arrays;

// BEGIN
public class MinThread extends Thread {

    private int[] initData;
    private int minValue;

    public MinThread(int[] data) {
        this.initData = data;
    }

    @Override
    public void run() {
        this.minValue = Arrays.stream(initData).min().getAsInt();
    }

    public int getMinValue() {
        return minValue;
    }

}
// END
