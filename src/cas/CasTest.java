package cas;

import org.junit.Test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.concurrent.atomic.AtomicStampedReference;

/********************************************************************************
 *
 * Title: cas无锁算法
 *
 * Description:
 *
 * @author chenlm
 * create date on 2019/1/28
 *
 *******************************************************************************/
public class CasTest {

    /**
     * 并发不冲突
     *
     * @throws Exception
     */
    @Test
    public void test1() throws Exception {
        AtomicInteger atomicInteger = new AtomicInteger(0);
        Thread t1 = new Thread(() -> bfTest(atomicInteger));
        t1.start();
        Thread t2 = new Thread(() -> bfTest(atomicInteger));
        t2.start();
        while (t1.isAlive() || t2.isAlive()) {

        }


    }

    /**
     * 并发
     *
     * @param atomicInteger
     */
    private void bfTest(AtomicInteger atomicInteger) {
        for (int i = 0; i < 10; i++) {
            System.out.println(atomicInteger.getAndIncrement());
        }
    }

    /**
     * 乐观锁  解决 aba问题
     *
     * @throws Exception
     */
    @Test
    public void test3() throws Exception {
        // 构造时两个入参  一个是初始引用值，另一个是初始版本号
        AtomicStampedReference<Integer> atomicStampedReference = new AtomicStampedReference<>(100, 0);
        //定义一个线程来制造aba问题
        Thread t1 = new Thread(() -> {
            //compareAndSet(V   expectedReference,V   newReference,int expectedStamp,int newStamp)
            //getStamp() 获取版本
            boolean a = atomicStampedReference.compareAndSet(100, 101, atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1);
            boolean b = atomicStampedReference.compareAndSet(101, 100, atomicStampedReference.getStamp(), atomicStampedReference.getStamp());
            System.out.println(atomicStampedReference.getStamp());
            System.out.println(atomicStampedReference.getStamp());
            System.out.println(a);
            System.out.println(b);
        });

        //试着去修改
        Thread t2 = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (Exception e) {

            }
            boolean d = atomicStampedReference.compareAndSet(100, 101, 4, 1);
            System.out.println(d);

        });

        t1.start();
        t2.start();

        TimeUnit.SECONDS.sleep(6);


    }


}
