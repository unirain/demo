package lock;

import org.junit.Test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/********************************************************************************
 *
 * Title: 线程监视器测试
 *
 * Description:
 *
 * @author chenlm
 * create date on 2019/10/31
 *
 *******************************************************************************/
public class ConditionTest {
    private Lock lock = new ReentrantLock();
    //创建线程监视器A
    private Condition conditionA = lock.newCondition();
    //创建线程监视器B
    private Condition conditionB = lock.newCondition();
    //控制生产者继续生产
    private volatile boolean flag = false;
    //生产物
    private volatile int number = 0;

    /**
     * 等待
     */
    private void waitA() {
        lock.lock();
        try {
            System.out.println("A获得锁");
            System.out.println("A进去等待");
            conditionA.await();
            System.out.println("A被唤醒了");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }


    }

    /**
     * 等待
     */
    private void waitB() {
        lock.lock();
        try {
            System.out.println("B获得锁");
            System.out.println("B进去等待");
            conditionB.await();
            System.out.println("B被唤醒了");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }


    }

    /**
     * 通知某个有conditionA对象的等待线程
     */
    private void signalA() {
        lock.lock();
        try {
            System.out.println("A获得锁");
            System.out.println("启动唤醒A");
            conditionA.signal();
            System.out.println("A唤醒结束");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }


    }

    private void signalB() {
        lock.lock();
        try {
            System.out.println("B获得锁");
            System.out.println("启动唤醒B");
            conditionB.signal();
            System.out.println("B唤醒结束");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }


    }

    /**
     * 线程有序进行
     * 唤醒A
     *
     * @throws Exception
     */
    @Test
    public void test1() throws Exception {
        Thread tAwait = new Thread(this::waitA);
        Thread tBwait = new Thread(this::waitB);
        Thread tnotifyA = new Thread(this::signalA);
//        Thread tb=new Thread(this::waitB);
        tAwait.start();
        TimeUnit.SECONDS.sleep(3);
        tBwait.start();
        TimeUnit.SECONDS.sleep(3);
        tnotifyA.start();
        TimeUnit.SECONDS.sleep(7);


    }

    /**
     * 生产者
     */
    private void produce() {
        while (true) {
            lock.lock();
            try {
                while (flag) {
                    //等待
                    conditionA.await();
                }
                System.out.println(Thread.currentThread().getName() + "-----生产-----");
                number++;
                TimeUnit.SECONDS.sleep(1);
                System.out.println("number: " + number);
                flag = true;
                //提醒消费
                conditionA.signal();
            } catch (Exception e) {
                e.printStackTrace();

            } finally {
                lock.unlock();
            }
        }


    }

    /**
     * 消费者
     */
    private void consume() {
        while (true) {
            lock.lock();
            try {
                while (!flag) {
                    conditionA.await();
                }
                System.out.println(Thread.currentThread().getName() + "-----消费-----");
                TimeUnit.SECONDS.sleep(1);
                number--;
                System.out.println("number: " + number);
                flag = false;
                // 提醒生产者生产
                conditionA.signal();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

    }

    /**
     * 生产者消费者
     * 原理：单单靠唤醒和休眠还不够，还需要他们之前的一个共享变量来协调运行的先后顺序，共享变量加唤醒和休眠可以实现控制线程的先后运行顺序
     *
     * @throws Exception
     */
    @Test
    public void test2() throws Exception {
        Thread t1 = new Thread(this::produce);
        Thread t2 = new Thread(this::consume);
        t1.start();
        t2.start();
        TimeUnit.SECONDS.sleep(10);


    }


}
