package exercise;

import java.util.concurrent.ThreadLocalRandom;

// BEGIN
public class ListThread extends Thread {

    SafetyList safetyList;

    ListThread(SafetyList safetyList) {
        this.safetyList = safetyList;
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            safetyList.add(generateRandomInRange());
            try {
                sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public int generateRandomInRange(){
        return ThreadLocalRandom.current().nextInt();
    }
}
// END
