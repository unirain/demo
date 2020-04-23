package queue.delay;

import org.junit.Test;

import java.util.concurrent.DelayQueue;

/********************************************************************************
 *
 * Title: 延迟任务测试
 *
 * Description:
 *
 * @author chenlm
 * create date on 2019/12/31
 *
 *******************************************************************************/
public class DelayTest {

    @Test
    public void test1()throws Exception{
        DelayTask task1=new DelayTask("A1",3L);
        DelayTask task2=new DelayTask("A2",5L);
        DelayTask task3=new DelayTask("A3",6L);
        DelayTask task4=new DelayTask("A4",2L);

        DelayQueue<DelayTask> delayQueue  = new DelayQueue<>();
        delayQueue.add(task1);
        delayQueue.add(task2);
        delayQueue.add(task3);
        delayQueue.add(task4);

        while (true){
            System.out.println(delayQueue.take().toString());
        }


    }
}
