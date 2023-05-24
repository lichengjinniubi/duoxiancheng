package test.semaphore;

import java.util.concurrent.Semaphore;

/**
 * 使用semaphore进行并发控制
 */
public class SemaphoreOneProcess {

    static int count = 0;

    static final Semaphore s = new Semaphore(2);

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            try {
                addOne();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Thread t2 = new Thread(() -> {
            try {
                addOne();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println(count);
    }


    public static void addOne() throws InterruptedException {
        s.acquire();
        try {
            for(int i= 0; i<10000; i++){
                count +=1;
            }
        }finally {
            s.release();
        }


    }


}
