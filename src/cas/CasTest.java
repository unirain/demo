package cas;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

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
    @Test
    public  void test1()throws Exception{
        AtomicInteger atomicInteger=new AtomicInteger(0);
        Thread t1=new Thread(()->test2(atomicInteger));
        t1.start();
        Thread t2=new Thread(()->test2(atomicInteger));
        t2.start();
        while (t1.isAlive()||t2.isAlive()){

        }



    }

    @Test
    public  void test3()throws Exception{
        AtomicInteger atomicInteger=new AtomicInteger(0);
        atomicInteger.incrementAndGet();
    }
    public void test2(AtomicInteger atomicInteger){
        for(int i=0;i<1;i++){
            System.out.println(atomicInteger.getAndIncrement());
        }
    }
}
