package test.happenBeforeTest;

public class VoliateTestApplication {


    static int x = 0;
    volatile static int y = 0;

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            write();
        });

        Thread t2 = new Thread(() -> {
            read();
        });


        t1.start();
        t2.start();
    }



    public static void write(){
        y = 2;
        x = 1;

    }


    public static void read(){
        System.out.println(x);
    }

}
