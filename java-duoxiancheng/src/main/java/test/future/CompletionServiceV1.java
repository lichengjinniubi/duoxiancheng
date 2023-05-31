package test.future;

import java.util.concurrent.*;

public class CompletionServiceV1 {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        CompletionService<Integer> cs = new ExecutorCompletionService<>(executorService);

        cs.submit(()->getPriceV1());
        cs.submit(()->getPriceV2());
        cs.submit(()->getPriceV3());

        Integer r = 0;
        for(int i = 0; i < 3; i++){
            r =  cs.take().get();
            if(r != null){
                System.out.println(r);
            }
        }
        System.out.println(r);

    }


    public static Integer getPriceV1() throws InterruptedException {
        Thread.sleep(10000);
        return 1;
    }

    public static Integer getPriceV2(){
        return 2;
    }

    public static Integer getPriceV3(){
        return 3;
    }
}
