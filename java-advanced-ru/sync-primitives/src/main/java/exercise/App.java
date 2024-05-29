package exercise;

import java.util.List;

class App {

    public static void main(String[] args) {
        SafetyList resource = new SafetyList();

        ListThread thread1 = new ListThread(resource);
        ListThread thread2 = new ListThread(resource);

        thread1.start();
        thread2.start();

        try {

            thread1.join();
            thread2.join();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(resource.getSize());
    }
}

