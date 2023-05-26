package test.semaphore;

import java.util.concurrent.atomic.AtomicReference;


/**
 * 引用类型的原子类解决ABA问题
 */
public class AtomicReferenceApplication {

    final static AtomicReference<WMRange> rf = new AtomicReference<>(
            new WMRange(0,0)
    );


    public static void main(String[] args) {
       Thread t1 = new Thread(() -> {
           try {
               setUpper(1, "v1");
           } catch (IllegalAccessException | InterruptedException e) {
               throw new RuntimeException(e);
           }
       });

        Thread t2 = new Thread(() -> {
            try {
                setUpper(2, "v2");
            } catch (IllegalAccessException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        t1.start();
        t2.start();

    }


    public static void setUpper(int v, String type) throws IllegalAccessException, InterruptedException {
        WMRange nr = null;
        WMRange or = null;
        if(type.equals("v1")){
            Thread.sleep(2000);
        }
        do {
            or = rf.get();
            System.out.println(or.upper);
            System.out.println(type);
            System.out.println(or);

            if(v < or.lower){
                throw  new IllegalAccessException();
            }

            nr = new WMRange(v, or.lower);
            System.out.println(nr.upper);
            System.out.println("----------------");
        }while (!rf.compareAndSet(or, nr));
    }

}



class WMRange{
   final int upper;
    final int lower;
    WMRange(int upper, int lower){
           // 省略构造函数实现
        this.upper = upper;
        this.lower = lower;
    }
 }
