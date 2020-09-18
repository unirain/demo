package rocketmq.simple;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.*;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.junit.Test;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/********************************************************************************
 *
 * Title: 消费者服务
 *
 * Description:
 *
 * @author chenlm
 * create date on 2020/9/18
 *
 *******************************************************************************/
public class CosumeWork {

    /**
     * 普通订阅
     *
     * @throws Exception
     */
    @Test
    public void consume() throws Exception {
        //定义一个消费者
        DefaultMQPushConsumer defaultMQPushConsumer = new DefaultMQPushConsumer("my-consumer");
        defaultMQPushConsumer.setNamesrvAddr("localhost:9876");
        //订阅一个top
        defaultMQPushConsumer.subscribe("UC", "*");
        //开始监听
        defaultMQPushConsumer.registerMessageListener((MessageListenerConcurrently) (msgs, consumeConcurrentlyContext) -> {
            msgs.forEach(messageExt -> System.out.println(new String(messageExt.getBody(), StandardCharsets.UTF_8)));
//            System.out.printf("%s Receive New Messages: %s %n", Thread.currentThread().getName(), msgs);
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        });
        defaultMQPushConsumer.start();
        System.out.printf("Consumer Started.%n");
        TimeUnit.SECONDS.sleep(10000L);


    }


    /**
     * 局部顺序（同一个queue）
     * <p>
     * MessageListenerOrderly 需要product端配合，需要product端同一组的顺序消息放入同一个messagequeue中
     * consumer端在消费的时候，指定MessageListenerOrderly情况，这样就可以保证在同一时间、同一个Consumer Queue的消息不被并发消费，但不同的Consumer Queue的消息可以并发处理。
     * <p>
     * <p>
     * 输出的时候只保证了自己的messageQueue的顺序，但是过程中会输出其他线程的messageQueue，不影响整体顺序
     *
     * @throws Exception
     */
    @Test
    public void ConsumerInOrder() throws Exception {
        DefaultMQPushConsumer defaultMQPushConsumer = new DefaultMQPushConsumer("my-order-consumer");
        defaultMQPushConsumer.setNamesrvAddr("localhost:9876");
        /**
         * 设置Consumer第一次启动 是从队列头部开始消费还是队列尾部开始消费
         * 如果非第一次启动，那么按照上次消费的位置继续消费
         */
        defaultMQPushConsumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
        //队列订阅
        defaultMQPushConsumer.subscribe("UC-Topic-Order", "TagA || TagC || TagD");
        defaultMQPushConsumer.registerMessageListener(new MessageListenerOrderly() {
            Random random = new Random();

            @Override
            public ConsumeOrderlyStatus consumeMessage(List<MessageExt> msgs, ConsumeOrderlyContext context) {
                for (MessageExt msg : msgs) {
                    System.out.println("consumeThread=" + Thread.currentThread().getName() + "queueId=" + msg.getQueueId() + ", content:" + new String(msg.getBody()) + msg.getKeys());
                }
//                try {
//                    //模拟业务逻辑处理中...
//                    TimeUnit.SECONDS.sleep(random.nextInt(10));
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
                return ConsumeOrderlyStatus.SUCCESS;
            }
        });
        defaultMQPushConsumer.start();
        System.out.println("consumer start..");
        TimeUnit.SECONDS.sleep(10000L);


    }
}
