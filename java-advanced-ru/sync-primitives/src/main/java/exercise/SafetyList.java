package exercise;

import java.util.ArrayList;
import java.util.List;

class SafetyList {
    // BEGIN
    private List<Integer> data = new ArrayList<>();
    public synchronized void add(int num) {
        data.add(num);
    }

    public int get(int index) {
        return data.get(index);
    }

    public int getSize() {
        return data.size();
    }
    // END
}
