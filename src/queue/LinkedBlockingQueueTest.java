package queue;

import org.junit.Test;

import java.util.concurrent.LinkedBlockingDeque;

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


}
