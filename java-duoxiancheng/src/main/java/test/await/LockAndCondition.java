package test.await;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockAndCondition {

    private static int count;
    private static Lock lock = new ReentrantLock();
    private static Condition notEmpty = lock.newCondition();
    private static Condition notFull = lock.newCondition();

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            try {
                add(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Thread t2 = new Thread(() -> {
            try {
                add(2);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Thread t3 = new Thread(() -> {
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            try {
                remove();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        t1.start();
        t2.start();
        t3.start();
    }



    public static void add(int value) throws InterruptedException {
        lock.lock();
        try{
            if(count == 1){
                notEmpty.await();
            }
            System.out.println("value" + value);
            ++count;
            notFull.signal();
        }finally {
            lock.unlock();
        }

    }



    public static void remove() throws InterruptedException{

        lock.lock();
        try {
            if(count == 0){
                notFull.await();
            }
            --count;
            notEmpty.signalAll();
        }  finally {
            lock.unlock();
        }
    }

}
