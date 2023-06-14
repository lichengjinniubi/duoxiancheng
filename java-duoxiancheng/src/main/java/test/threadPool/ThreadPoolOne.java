package test.threadPool;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolOne {

    /**
     * 这段代码会导致线程死锁，因为线程数量不都用，导致都堵在l2.await()无法向下执行
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
         ExecutorService es = Executors.newFixedThreadPool(2);

         CountDownLatch l1 = new CountDownLatch(2);

        for (int i=0; i<2; i++){
             System.out.println("L1");
             // 执行 L1 阶段任务
             es.execute(()->{
                 //L2 阶段的闭锁
                 CountDownLatch l2=new CountDownLatch(2);
                 // 执行 L2 阶段子任务
                 for (int j=0; j<2; j++){
                     es.execute(()->{
                         System.out.println("L2");
                         l2.countDown();
                         });
                     }
                 // 等待 L2 阶段任务执行完
                 try {
                     l2.await();
                 } catch (InterruptedException e) {
                     throw new RuntimeException(e);
                 }
                 l1.countDown();
                 });
             }
         // 等着 L1 阶段任务执行完
         l1.await();
         System.out.println("end");

    }
}
