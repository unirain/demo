package guava.eventbus;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;
import org.junit.Test;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/********************************************************************************
 *
 * Title:  事件驱动  将没有直接联系的动作直接解耦
 *
 * 事件发布  订阅
 *
 * 事件给bus总线，然后总线分配
 *
 * Description:
 *
 * @author chenlm
 * create date on 2021/6/25 0025
 *
 *******************************************************************************/
public class EventTest {
    @Test
    public void should_sync() {
        //定义一个总线
        EventBus eventBus = new EventBus("my-test");
        //注册
//        eventBus.register(new OrderEventListener());
        //发布
        OrderMessage orderMessage = new OrderMessage();
        orderMessage.setContent("你好啊！");
        eventBus.post(orderMessage);
        System.out.println("ok end");

    }

    @Test
    public void should_async() throws Exception {
        //定义一个总线
        EventBus eventBus = new AsyncEventBus(Executors.newFixedThreadPool(3));
        //注册
        eventBus.register(new OrderEventListener());
        eventBus.register(new OrderEventListenerV2());
        //发布
        OrderMessage orderMessage = new OrderMessage();
        orderMessage.setContent("你好啊！");
        OrderMessageV2 orderMessageV2 = new OrderMessageV2();
        orderMessageV2.setContent("你好啊！v2");
        eventBus.post(orderMessage);
        eventBus.post(orderMessageV2);
        System.out.println("ok end");

        TimeUnit.SECONDS.sleep(5);


    }
}
