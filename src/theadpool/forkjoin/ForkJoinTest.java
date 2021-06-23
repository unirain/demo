package theadpool.forkjoin;

import cn.hutool.core.thread.ThreadUtil;
import org.junit.Test;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/********************************************************************************
 *
 * Title: 
 *
 * Description: 将大任务划分为小任务来执行  分而治之思想
 *
 * @author chenlm
 * create date on 2021/6/22 0022
 *
 *******************************************************************************/
public class ForkJoinTest {

    @Test
    public void forkJoin(){

        long[] array=new long[2000];
        long expectedSum=0;
        //普通计算
        for (int i = 0; i < array.length; i++) {
            array[i] = random();
            expectedSum += array[i];
        }
        System.out.println("Expected sum: " + expectedSum);
        //fork/join
        ForkJoinTask<Long> task=new SumTask(array,0,array.length);
        long startTime = System.currentTimeMillis();
        Long result = ForkJoinPool.commonPool().invoke(task);
        long endTime = System.currentTimeMillis();
        System.out.println("Fork/join sum: " + result + " in " + (endTime - startTime) + " ms.");


    }

    static Random random = new Random(0);

    static long random() {
        return random.nextInt(10000);
    }

    static class  SumTask extends RecursiveTask<Long>{
        private final int THRESHOLD=500;
        private final long[] array;
        int start;
        int end;

        public SumTask(long[] array, int start, int end) {
            this.array = array;
            this.start = start;
            this.end = end;
        }

        @Override
        protected Long compute() {
            if (end-start<=THRESHOLD){
                //直接计算
                long sum=0;
                for (int i = start; i < end; i++) {
                    sum += array[i];
                    //放慢速度
                    ThreadUtil.safeSleep(1);
                }
                return sum;
            }
            //数据量大 切分下
            int middle=(end+start)/2;
            SumTask s1=new SumTask(array,start,middle);
            SumTask s2=new SumTask(array,middle,end);
            //fork子任务,这边不建议用s1.fork,原因是可能会有线程浪费
            //官方API文档是这样写到的，所以平日用invokeAll就好了。
            //invokeAll会把传入的任务的第一个交给当前线程来执行，其他的任务都fork加入工作队列，这样等于利用当前线程也执行任务了

            invokeAll(s1,s2);
            long r1=s1.join();
            long r2=s2.join();
            long result=r1+r2;
            System.out.println("result = " + r1 + " + " + r2 + " ==> " + result);
            return result;
        }
    }

}
