package lock;

import jdk.nashorn.internal.ir.CatchNode;
import org.junit.Test;

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
        t2.start();
        Thread.sleep(6000);

    }

    @Test
    public void test1() throws Exception {
        //在A线程调用B线程的时候才有效果
        Thread t1 = new Thread(() -> test12(), "T1");
        Thread t2 = new Thread(() -> test12(), "T2");
        t1.start();
        t2.start();
        t1.join();
        test12();


    }

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
                    Thread.sleep(10);
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
}
