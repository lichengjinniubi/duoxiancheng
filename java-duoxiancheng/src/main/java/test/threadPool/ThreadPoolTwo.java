package test.threadPool;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolTwo {

    /**
     * 这个也是单个线程会被卡住，任务永远无法结束
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {

        ExecutorService pool = Executors.newSingleThreadExecutor();
         pool.submit(() -> {
             try {
                 String qq=pool.submit(()->"QQ").get();
                 System.out.println(qq);
                 } catch (Exception e) {
                 }
             });
    }
}
