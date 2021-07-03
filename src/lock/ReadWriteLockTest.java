package lock;

import org.junit.Test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.stream.IntStream;

/********************************************************************************
 *
 * Title: 读写锁，
 *
 * 在使用ReentrantLock时，它保证当前只有一个线程获取锁，但是有时候我们实际应用中会出现读多写少的场景，读于读之间都是读取同样的数据，如果使用ReentrantLock反而效率会低下，
 * 使用ReentrantReadWriteLock会很高效，它可以实现多个读锁同时进行，但是读与写和写于写互斥，只能有一个写锁线程在进行

 *
 *
 * Description: 问：虽然这里的Read锁允许了多线程运行，但是如果我们对读操作不加锁，能不能少些一点儿代码？效果也是一样的呢？因为写操作会加锁，因此还是满足同步的要求呀！求指点
 *              答:锁的目的不是读的数据是错的，是保证连续读逻辑上一致的：
 * int x = obj.x;
 * // 这里线程可能中断
 * int y = obj.y;
 * 假设obj的x，y是[0,1]，某个写线程修改成[2,3]，你读到的要么是[0,1]，要么是[2,3]，但是没有锁，你读到的可能是[0,3]
 * @author chenlm
 * create date on 2020/10/30
 *
 *******************************************************************************/
public class ReadWriteLockTest {

    private ReentrantReadWriteLock readWriteLock=new ReentrantReadWriteLock();
    private Lock readLock = readWriteLock.readLock();
    private Lock writeLock = readWriteLock.writeLock();

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

    /**
     * 读写互斥
     * @throws Exception
     */
    @Test
    public void writeAfterRead() throws Exception {
        IntStream.range(0, 3).forEach(i -> {
            Thread t1 = new Thread(this::read);
            Thread t2 = new Thread(this::write);
            t1.start();
            t2.start();
        });

        TimeUnit.SECONDS.sleep(60);
    }

    /**
     * 读写互斥
     * @throws Exception
     */
    @Test
    public void should_3e()throws Exception{
        writeLock.lock();
        Thread t1 = new Thread(this::read);
        t1.start();
        TimeUnit.SECONDS.sleep(5);
        System.out.println("-0-------");
        writeLock.unlock();
        TimeUnit.SECONDS.sleep(60);

    }

    /**
     * 可重入
     * read/write 都可重入
     */
    @Test
    public void should_Reentry(){
        writeLock.lock();
        read();
        write();
        writeLock.unlock();
        System.out.println("ok");

    }


}
