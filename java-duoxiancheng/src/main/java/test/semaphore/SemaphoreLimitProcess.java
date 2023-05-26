package test.semaphore;

import java.util.concurrent.*;

public class SemaphoreLimitProcess {


    public static void main(String[] args) {

        //允许最大的登录数
        int slots=10;
        ExecutorService executorService = Executors.newFixedThreadPool(slots);
        LoginQueueUsingSemaphore loginQueue = new LoginQueueUsingSemaphore(slots);
        //线程池模拟登录
        for (int i = 1; i <= slots; i++) {
            final int num=i;
            executorService.execute(()->{
                if (loginQueue.tryLogin()){
                    System.out.println("用户:"+num+"登录成功！");
                }else {
                    System.out.println("用户:"+num+"登录失败！");
                }
            });
        }
        executorService.shutdown();


        System.out.println("当前可用许可证数："+loginQueue.availableSlots());

        //此时已经登录了10个用户，再次登录的时候会返回false
        if (loginQueue.tryLogin()){
            System.out.println("登录成功！");
        }else {
            System.out.println("系统登录用户已满，登录失败！");
        }
        //有用户退出登录
        loginQueue.logout();

        //再次登录
        if (loginQueue.tryLogin()){
            System.out.println("登录成功！");
        }else {
            System.out.println("系统登录用户已满，登录失败！");
        }

    }

}
