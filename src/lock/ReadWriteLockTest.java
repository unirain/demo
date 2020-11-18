package lock;

import org.junit.Test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.stream.IntStream;

/********************************************************************************
 *
 * Title: 读写锁
 *
 * Description:
 *
 * @author chenlm
 * create date on 2020/10/30
 *
 *******************************************************************************/
public class ReadWriteLockTest {

    private Lock readLock = new ReentrantReadWriteLock().readLock();
    private Lock writeLock = new ReentrantReadWriteLock().writeLock();

    public void read() {
        readLock.lock();
        IntStream.range(0, 10).forEach(i -> {
            try {
                System.out.println(Thread.currentThread().getName() + "正在读" + i + "操作....");
                TimeUnit.SECONDS.sleep(1);
            } catch (Exception e) {

            }
        });
        readLock.unlock();
    }


    public void write() {
        writeLock.lock();
        IntStream.range(0, 10).forEach(i -> {
            try {
                System.out.println(Thread.currentThread().getName() + "正在写" + i + "操作....");
                TimeUnit.SECONDS.sleep(1);
            } catch (Exception e) {

            }
        });
        writeLock.unlock();
    }

    /**
     * 多线程操作只读锁，可允许进入
     *
     * @throws Exception
     */
    @Test
    public void enjoyRead() throws Exception {
        IntStream.range(0, 3).forEach(i -> {
            Thread thread = new Thread(this::read);
            thread.start();
        });
        TimeUnit.SECONDS.sleep(15);
    }


    /**
     * 独占  不允许其他线程操作
     */
    @Test
    public void operateWrite() throws Exception {
        IntStream.range(0, 3).forEach(i -> {
            Thread thread = new Thread(this::write);
            thread.start();
        });
        TimeUnit.SECONDS.sleep(15);
    }

    @Test
    public void writeAfterRead() throws Exception {
        IntStream.range(0, 3).forEach(i -> {
            Thread t1 = new Thread(this::read);
            Thread t2 = new Thread(this::write);
            t1.start();
            t2.start();
        });

        TimeUnit.SECONDS.sleep(15);
    }


}
