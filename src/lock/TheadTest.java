package lock;

import jdk.nashorn.internal.ir.CatchNode;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

/********************************************************************************
 *
 * Title: wait notify  
 *
 * Description:
 *
 * @author chenlm
 * create date on 2018/12/25
 *
 *******************************************************************************/
public class TheadTest {
    /**
     * 调用wait，之后释放锁，让出cpu资源
     * @param o
     */
    public void dowait(Object o) {
        synchronized (o) {
            try {
                System.out.println("begin wait() ThreadName=" + Thread.currentThread().getName());
                o.wait();
                System.out.println("end wait() ThreadName=" + Thread.currentThread().getName());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 唤醒等待该锁的线程，之后继续执行，直到释放锁
     * @param o
     */
    public void synNotifyMethod(Object o) {
        synchronized (o) {
            try {
                System.out.println("begin notify() ThreadName=" + Thread.currentThread().getName() + " time=" + System.currentTimeMillis());
                o.notify();
                Thread.sleep(5000);
                System.out.println("  end notify() ThreadName=" + Thread.currentThread().getName() + " time=" + System.currentTimeMillis());
            } catch (Exception e) {
                e.printStackTrace();

            }
        }
    }

    @Test
    public void test() throws Exception {
        TheadTest theadTest = new TheadTest();
        String a = "1";
        Thread t1 = new Thread(() -> theadTest.dowait(a));
        Thread t2 = new Thread(() -> theadTest.synNotifyMethod(a));
        t1.start();
        TimeUnit.SECONDS.sleep(3);
        t2.start();
        Thread.sleep(6000);

    }

    @Test
    public void test1() throws Exception {
        //在A线程调用B线程的时候才有效果
        Thread t1 = new Thread(() -> test12(), "T1");
//        Thread t2 = new Thread(() -> test12(), "T2");
        t1.start();
//        t2.start();
        t1.join();
//        test12();
        System.out.println("33eeeeeeeeeeee");

    }

    /**
     * 让他中断
     * 必须是阻塞状态下
     * @throws Exception
     */
    @Test
    public void test2() throws Exception {
        Thread t1 = new Thread(() -> test12(), "T1");
        t1.start();
        //测试线程中断
        //一定是在线程阻塞状态下
        t1.interrupt();

        Thread.sleep(10000);
    }

    public void test12() {
        try {
            for (int i = 0; i < 1000; i++) {
                System.out.println(Thread.currentThread().getName() + ":" + i);
                try {
                    if (i==10)
                    TimeUnit.SECONDS.sleep(3);
                } catch (Exception e) {
                    if (e instanceof InterruptedException) {
                        System.out.println("线程中断");
                    } else {
                        e.printStackTrace();
                    }
                    break;

                }
            }
        } catch (Exception e) {
            e.printStackTrace();

        }

    }


    /**
     * 主线程和子线程测试JOIN
     * 之后主线程让出cpu等待 子线程处理完
     * @throws Exception
     */
    @Test
    public void joinTest()throws Exception{
        Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("我是谁。。");
                try{
                    TimeUnit.SECONDS.sleep(10);
                }catch(Exception e){

                }
            }
        });
        thread.start();
        System.out.println("开始楼");
        thread.join();
        System.out.println("结束");


    }

    /**
     * 对象锁的理解
     *
     * 对象被改变，则其他线程就可以
     *
     */
    String a="1";
    @Test
    public void test98() {
        synchronized (a) {
            System.out.println(a);
            a = "2";
            try {
                System.out.println("第一次休眠");
                TimeUnit.SECONDS.sleep(3);
            } catch (Exception e) {
                e.printStackTrace();
            }
            synchronized (a) {
                System.out.println(a);
                try {
                    System.out.println("第二次休眠");
                    TimeUnit.SECONDS.sleep(3);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }
    }
    @Test
    public void testth() throws Exception {
        Thread thread = new Thread(() -> test98());
        thread.start();
        test98();
    }

}
