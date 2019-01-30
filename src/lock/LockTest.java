package lock;

import org.junit.Test;

import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/********************************************************************************
 *
 * Title: 锁定测试
 *
 * Description:
 *
 * @author chenlm
 * create date on 2018/12/25
 *
 *******************************************************************************/
public class LockTest {
    private Lock lock = new ReentrantLock();

    /**
     * 获取锁，没有获得锁会一直阻塞
     * @param thread
     */
    public void dolockword(Thread thread) {
        lock.lock();
        try {
            System.out.println("线程名:"+thread.getName()+"获得了锁");
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            System.out.println("线程名:"+thread.getName() + "释放了锁");
            lock.unlock();
        }
    }

    /**
     * 试着获取锁
     * @param thread
     */
    public void doTrylockword(Thread thread) {
        if (lock.tryLock()){
            try {
                System.out.println("线程名:"+thread.getName()+"获得了锁");
            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                System.out.println("线程名:"+thread.getName() + "释放了锁");
                lock.unlock();
            }
        }else {
            System.out.println("线程名:"+thread.getName()+"有人占用了，我不用了");
        }

    }


    @Test
    public  void test()throws Exception{
        LockTest lockTest=new LockTest();
        Thread t1=new Thread(()->lockTest.doTrylockword(Thread.currentThread()));
        Thread t2=new Thread(()->lockTest.doTrylockword(Thread.currentThread()));
        t1.start();
        t2.start();
        Thread.sleep(1000);

    }

    /**
     * 自动转append
     * 锁消除
     */
    @Test
    public   void show() {
        String a="1";
        String b=a+a+a;
        System.out.println(a);

    }
}
