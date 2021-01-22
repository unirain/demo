package queue;

import org.junit.Test;

import java.util.Timer;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/********************************************************************************
 *
 * Title: 
 *
 * Description: 单个推来推去这样，对方不获取 就会一直阻塞在那
 *
 * @author chenlm
 * create date on 2020/12/23 0023
 *
 *******************************************************************************/
public class SynchronousQueueTest {
    private SynchronousQueue<String> qName=new SynchronousQueue<>();

    @Test
    public void testSy()throws Exception{
        Thread t1=new Thread(()-> {
            try {
                int i=3;
                while (i>0){
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println("塞入");

                    qName.put("chenlm");
                    System.out.println("塞完成");
                    i--;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread t2=new Thread(()-> {
            try {
                while (true){
                    TimeUnit.SECONDS.sleep(6);
                    System.out.println("得到："+qName.take());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        t1.start();
        t2.start();
        TimeUnit.MINUTES.sleep(3L);


    }

}
