package test.happenBeforeTest;

public class VoliateTestApplicationV3 {


    static int x = 0;
    volatile static int y = 0;

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            x= 1;
            y = 1;
        });

        Thread t2 = new Thread(() -> {
            System.out.println(x);
            System.out.println(y);
        });


        t1.start();
        t2.start();
    }



    public static void write(){
        y = 2;
        x = 1;

    }


    public void read(){

        write();
    }

}
