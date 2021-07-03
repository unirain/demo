package lock;

import cn.hutool.core.thread.ThreadUtil;
import org.junit.Test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.StampedLock;

/********************************************************************************
 *
 * Title:  乐观读写锁
 *
 * Description:  参考https://blog.csdn.net/qq_36094018/article/details/90575768
 *
 * @author chenlm
 * create date on 2021/7/3 0003
 *
 *******************************************************************************/
public class StampedLockTest {
    private StampedLock stampedLock = new StampedLock();

    /**
     * 定义x y 坐标点
     */
    private double x, y;

    /**
     * 移动坐标
     *
     * @param deltaX
     * @param deltaY
     * @throws Exception
     */
    public void move(double deltaX, double deltaY) throws Exception {
        //获得锁凭据
//        long stamp=stampedLock.writeLock();//阻塞
//        long stamp=stampedLock.tryWriteLock(); //无阻塞，没有获得锁则立马报错
        long stamp = stampedLock.tryWriteLock(5, TimeUnit.SECONDS); //等待一段时间，如果没有获得锁，则直接报错
        try {
            x += deltaX;
            y += deltaY;
        } finally {
            stampedLock.unlockWrite(stamp);
        }
    }


    /**
     * 写写冲突
     */
    @Test
    public void should_blocking() throws Exception {
        long stamp = stampedLock.writeLock();
        ThreadUtil.newThread(() -> {
            try {
                move(9.0, 6.0);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "读").start();
        TimeUnit.SECONDS.sleep(6);
        stampedLock.unlockWrite(stamp);

    }

    /**
     * 不可重入
     */
    @Test
    public void should_Reentry() throws Exception {
        long stamp = stampedLock.readLock();
        move(9.0, 6.0);
        stampedLock.unlockWrite(stamp);
        System.out.println("ok");
    }


    /**
     * 计算距离
     * 乐观读
     *
     * @return
     */
    public double distanceFormOrigin() {
        //尝试拿到乐观锁 凭证,这边只是拿到凭证而已，并没有去锁住
        long stamp = stampedLock.tryOptimisticRead();
        //读取x和y的值,这时候我们并不确定x和y是否是一致的 需要下一步再次判断
        double currentX = x, currentY = y;
        /**
         * 判断stamp在读过程发生期间被修改过，如果没有被修改，则这次读取有效，直接return
         * 如果stamp被修改过，则有可能其他线程改写了数据，会出现脏读，可以使用死循环使用乐观锁读，直到成功
         * 也可以使用锁的级别，将乐观锁变为悲观锁
         */
        if (!stampedLock.validate(stamp)) {
            // 使用悲观锁读 如果有写线程那么该线程会挂起
            stamp = stampedLock.readLock();
            try {
                currentX = x;
                currentY = y;
            } finally {
                stampedLock.unlockRead(stamp);
            }
        }
        double result = Math.sqrt(currentX * currentX + currentY * currentY);
        System.out.println("distanceFormOrigin is ok"+result);
        return result;
    }


    /**
     * 读的时候发现被修改，则立马使用悲观锁获取
     *
     */
    @Test
    public void should_optimis() {
        //先改后读
        long stamp = stampedLock.writeLock();
        x = 9.0;
        y = 90.6;
        ThreadUtil.newThread(this::distanceFormOrigin, "乐观锁").start();
        ThreadUtil.safeSleep(TimeUnit.SECONDS.toMillis(3));
        //放开，让其他可以使用
        stampedLock.unlockWrite(stamp);
        ThreadUtil.safeSleep(TimeUnit.SECONDS.toMillis(10));
    }

    public void moveIfAtOrigin(double newX, double newY){
        long stamp=stampedLock.readLock();
        try{
            while (x==0.0&&y==0.0){
                long ws=stampedLock.tryConvertToWriteLock(stamp);
                if (ws!=0L){
                    System.out.println("升级成功");
                    stamp=ws;
                    x=newX;
                    y=newY;
                    break;
                }else {
                    // 升级失败 释放读锁，重新获取写锁，循环重试
                    System.out.println("升级失败");
                    stampedLock.unlockRead(stamp);
                    stamp = stampedLock.writeLock();
                }
            }
        }finally {
            //释放锁,方法会自己选择 是哪种锁
            stampedLock.unlock(stamp);

        }
    }


    /**
     * 第一次升级失败，第二次升级成功
     * 第一次并不能将读锁转换为写锁，原因是还有其他线程在持有读锁
     */
    @Test
    public void should_change(){
        long stamp=stampedLock.writeLock();
        ThreadUtil.newThread(()-> moveIfAtOrigin(5.9,4.0),"change").start();
        ThreadUtil.sleep(2,TimeUnit.SECONDS);
        stampedLock.unlock(stamp);
        ThreadUtil.sleep(20,TimeUnit.SECONDS);

    }

    /**
     * 使用乐观锁读可以避免写锁处于饥饿状态，增加吞吐量，但是使用乐观锁读也是很容易犯错误的，在使用上必须保证以下顺序。
     */
    @Test
    public void should_mustOrder(){
        /**
         * //乐观读 返回stamp凭证
         *         long stamp = lock.tryOptimisticRead();
         *         // 复制变量到方法栈
         *         CopyVariablesMethodStack();
         *         // 校验stamp凭证
         *         if (!lock.validate(stamp)){
         *             // 获取读锁
         *             stamp = lock.readLock();
         *             try {
         *                 // 复制变量到方法栈
         *                 CopyVariablesMethodStack();
         *             } finally {
         *                 // 释放读锁
         *                 lock.unlockRead(stamp);
         *             }
         *         }
         *         // 操作操作复制到方法栈中的变量
         *         ManipulateVariablesCopeMethodStack();
         */

    }


}
