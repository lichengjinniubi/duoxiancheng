package test.happenBeforeTest;

/**
 * 自线程能够看到主线程在启动子线程前的所有操作
 */
public class VoliateTestApplicationV2 {


    static int x = 0;
    volatile static int y = 0;

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            System.out.println(x);
        });
        x = 100;

        t1.start();
    }



}
