package queue;

import org.junit.Test;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

/********************************************************************************
 *
 * Title: LinkedBlockingQueue测试
 *
 * Description:
 *
 * @author chenlm
 * create date on 2019/1/31
 *
 *******************************************************************************/
public class LinkedBlockingQueueTest {
    @Test
    public void test1() throws Exception {
        LinkedBlockingDeque<String> blockingQueue = new LinkedBlockingDeque<>();
        //阻塞
        //允许栈顶 栈尾操作
        System.out.println(blockingQueue.take());
    }
    @Test
    public void test2() throws Exception {
        LinkedBlockingDeque<String> blockingQueue = new LinkedBlockingDeque<>();
        blockingQueue.add("1");
        blockingQueue.add("2");
        blockingQueue.add("3");
        while (true){
            System.out.println(blockingQueue.poll(1, TimeUnit.SECONDS));

        }


    }

    private void produce(LinkedBlockingDeque<String> blockingQueue){

    }


}
