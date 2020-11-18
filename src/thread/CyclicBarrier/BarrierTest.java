package thread.CyclicBarrier;

import org.junit.Test;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/********************************************************************************
 *
 * Title: 回环栅栏
 *
 * Description:  通过它可以实现让一组线程等待至某个状态之后再全部同时执行。
 *                叫做回环是因为当所有等待线程都被释放以后，CyclicBarrier可以被重用。我们暂且把这个状态就叫做barrier，
 *                当调用await()方法之后，线程就处于barrier了。
 *
 * @author chenlm
 * create date on 2020/11/18
 *
 *******************************************************************************/
public class BarrierTest {

    /**
     * 等待所有线程都达到这个状态，才释放
     * @throws Exception
     */
    @Test
    public void test()throws Exception{
        int n=3;
        CyclicBarrier cyclicBarrier=new CyclicBarrier(n);
        IntStream.range(0,n).forEach(i->{
            Thread t=new Thread(()->{
                System.out.println("线程"+Thread.currentThread().getName()+"正在写入数据...");
                try {
                    Thread.sleep(5000);      //以睡眠来模拟写入数据操作
                    System.out.println("线程"+Thread.currentThread().getName()+"写入数据完毕，等待其他线程写入完毕");
                    cyclicBarrier.await();
                } catch (Exception e) {
                }
                System.out.println("线程"+Thread.currentThread().getName()+"继续执行，执行完毕...");
            });
            t.start();
        });
        TimeUnit.SECONDS.sleep(30);

    }

    /**
     * 从结果可以看出，当四个线程都到达barrier状态后，会从四个线程中选择一个线程去执行Runnable。
     * @throws Exception
     */
    @Test
    public void test1()throws Exception{
        int n=3;
        CyclicBarrier cyclicBarrier=new CyclicBarrier(n,()->{System.out.println("我被触发了");});
        IntStream.range(0,n).forEach(i->{
            Thread t=new Thread(()->{
                System.out.println("线程"+Thread.currentThread().getName()+"正在写入数据...");
                try {
                    Thread.sleep(5000);      //以睡眠来模拟写入数据操作
                    System.out.println("线程"+Thread.currentThread().getName()+"写入数据完毕，等待其他线程写入完毕");
                    cyclicBarrier.await();
                } catch (Exception e) {
                }
                System.out.println("线程"+Thread.currentThread().getName()+"继续执行，执行完毕...");
            });
            t.start();
        });
        TimeUnit.SECONDS.sleep(30);
    }


    @Test
    public void tesrt()throws Exception{
        IntStream.range(0,2).forEach(i->{
            System.out.println(i);
        });
    }
}
