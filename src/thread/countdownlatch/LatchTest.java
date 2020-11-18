package thread.countdownlatch;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/********************************************************************************
 *
 * Title: 
 *
 * Description:
 *
 * @author chenlm
 * create date on 2020/11/18
 *
 *******************************************************************************/
public class LatchTest {

    /**
     * 主线await 会等  t1 t2 执行完
     * @throws Exception
     */
    @Test
    public void test1()throws Exception{
        CountDownLatch countDownLatch=new CountDownLatch(2);
        Thread t1=new Thread(()->{
           System.out.println("我是线程1");
           try{
               TimeUnit.SECONDS.sleep(1);
           }catch(Exception e){

           }
           countDownLatch.countDown();
        });
        Thread t2=new Thread(()->{
            try{
                TimeUnit.SECONDS.sleep(1);
            }catch(Exception e){

            }
            System.out.println("我是线程2");
            countDownLatch.countDown();
        });
        t1.start();
        t2.start();

        //等待
        countDownLatch.await();
        System.out.println("end");


    }
}
