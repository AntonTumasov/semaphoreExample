package herzen;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class SemApp {
    public static void main(String[] args) {

        final Runnable myRunnable = new Runnable() {
            int count = 0;
            final Semaphore mySemaphore = new Semaphore(2);
            final Random rand = new Random(System.currentTimeMillis());

            @Override
            public void run() {
                int time = rand.nextInt(20);
                int num = count++;

                try {
                    mySemaphore.acquire();
                    System.out.println("Запущен " +
                            "поток на  " +
                            time + " секунд... поток с номером " + num);
                    Thread.sleep(time * 1000);
                    System.out.println("Закончен номер " +
                            num + "!");

                    mySemaphore.release();
                } catch (InterruptedException e) {
                    System.out.println("Ошибочка!");
                }

            }
        };
        for (int i=0; i<10;i++) {
            new Thread(myRunnable).start();
        }
    }
}



