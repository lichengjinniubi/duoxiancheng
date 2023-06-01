package test.forkandjoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;


/**
 * 分治任务累加数据
 */
public class TestApplicationV2 {

    private static int SPLIT_NUM = 100;
    private static int END_NUM = 1000000;
    private static int START_NUM = 0;

    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool(5);
        AddNum addNum = new AddNum(START_NUM, END_NUM, SPLIT_NUM);
        int result = forkJoinPool.invoke(addNum);
        System.out.println(result);
    }



   static class AddNum extends RecursiveTask<Integer> {

       private int startNum;
       private int endNum;
       private int splitNum;
       public AddNum(int startNum,int endNum,int splitNum){
           this.startNum = startNum;
           this.endNum = endNum;
           this.splitNum = splitNum;
       }
       @Override
       protected Integer compute() {
           int sum = 0;
           //是否还需要分割任务
           if(endNum - startNum <= splitNum){
               for(int i = startNum; i <= endNum; i++){
                   sum += i;
               }
               return sum;
           }else{
                int middleNum = (startNum+endNum)/2;
                AddNum addLeft = new AddNum(startNum, middleNum, splitNum);
                AddNum addRight = new AddNum(middleNum+1, endNum, splitNum);
                addLeft.fork();
                addRight.fork();
                return addLeft.join() + addRight.join();
           }
       }
   }
}
