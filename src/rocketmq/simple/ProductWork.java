package rocketmq.simple;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.junit.Test;
import org.omg.CORBA.PUBLIC_MEMBER;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/********************************************************************************
 *
 * Title:  服务提供者
 *
 * Description:
 *
 * @author chenlm
 * create date on 2020/9/18
 *
 *******************************************************************************/
public class ProductWork {
    private String NAME_SRV_ADDR = "10.71.2.27:9876";

    /**
     * 发送同步消息
     * 这种可靠性同步地发送方式使用的比较广泛，比如：重要的消息通知，短信通知。
     *
     * @throws Exception
     */
    @Test
    public void SyncProducer() throws Exception {
        //建立一个生产者
        DefaultMQProducer producer = new DefaultMQProducer("my-product");
        //绑定nameserver（类似注册中心）
        producer.setNamesrvAddr(NAME_SRV_ADDR);
        //开启
        producer.start();
        //发送消息
        for (int i = 0; i < 100; i++) {
            Message message = new Message("UC", "uc-四组", ("我来自同步提供者的消息"+i+"个").getBytes(RemotingHelper.DEFAULT_CHARSET));
            SendResult sendResult = producer.send(message);
            System.out.printf("%s%n", sendResult);

        }
        //停止不在使用
        producer.shutdown();
    }

    /**
     * 发送异步消息
     * 异步消息通常用在对响应时间敏感的业务场景，即发送端不能容忍长时间地等待Broker的响应。
     *
     * @throws Exception
     */
    @Test
    public void AsyncProducer() throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer("my-product");
        //绑定nameserver（类似注册中心）
        producer.setNamesrvAddr(NAME_SRV_ADDR);
        //开启
        producer.start();
        //发送失败重试
        producer.setRetryTimesWhenSendFailed(0);
        int messageCount = 100;
        final CountDownLatch countDownLatch = new CountDownLatch(messageCount);
        for (int i = 0; i < messageCount; i++) {
            try {
                final int index = i;
                Message message = new Message("UC", "uc-四组", "我来自异步提供者的消息".getBytes(RemotingHelper.DEFAULT_CHARSET));
                producer.send(message, new SendCallback() {
                    @Override
                    public void onSuccess(SendResult sendResult) {
                        countDownLatch.countDown();
                        System.out.printf("%-10d OK %s %n", index, sendResult.getMsgId());
                    }

                    @Override
                    public void onException(Throwable e) {
                        countDownLatch.countDown();
                        System.out.printf("%-10d Exception %s %n", index, e);
                        e.printStackTrace();

                    }
                });


            } catch (Exception e) {
                e.printStackTrace();

            }

        }
        countDownLatch.await(5, TimeUnit.SECONDS);
        producer.shutdown();
    }


    /**
     * 单向发送消息
     * 这种方式主要用在不特别关心发送结果的场景，例如日志发送。
     *
     * @throws Exception
     */
    @Test
    public void OnewayProducer() throws Exception {
        //建立一个生产者
        DefaultMQProducer producer = new DefaultMQProducer("my-product");
        //绑定nameserver（类似注册中心）
        producer.setNamesrvAddr(NAME_SRV_ADDR);
        producer.start();
        for (int i = 0; i < 100; i++) {
            // 创建消息，并指定Topic，Tag和消息体
            Message message = new Message("UC", "uc-四组", "我来自单向提供者的消息，我不关心你理不理我".getBytes(RemotingHelper.DEFAULT_CHARSET));
            // 发送单向消息，没有任何返回结果
            producer.sendOneway(message);

        }
        // 如果不再发送消息，关闭Producer实例。
        producer.shutdown();

    }


    /**
     * {@link  CosumeWork   ConsumerInOrder 消费者}
     * 分区有序，它将相同的订单号发送到了同一个队列上了，消费者在消费的时候就是有序了
     * 如果全局就一个queue，则全局有序
     * <p>
     * 官方：
     * 消息有序指的是可以按照消息的发送顺序来消费(FIFO)。RocketMQ可以严格的保证消息有序，可以分为分区有序或者全局有序。
     * 顺序消费的原理解析，在默认的情况下消息发送会采取Round Robin轮询方式把消息发送到不同的queue(分区队列)；而消费消息的时候从多个queue上拉取消息，这种情况发送和消费是不能保证顺序。
     * 但是如果控制发送的顺序消息只依次发送到同一个queue中，消费的时候只从这个queue上依次拉取，则就保证了顺序。当发送和消费参与的queue只有一个，则是全局有序；如果多个queue参与，则为分区有序，即相对每个queue，消息都是有序的。
     * <p>
     * 下面用订单进行分区有序的示例。一个订单的顺序流程是：创建、付款、推送、完成。订单号相同的消息会被先后发送到同一个队列中，
     * 消费时，同一个OrderId获取到的肯定是同一个队列。
     * <p>
     * MessageQueueSelector 接口有三个参数  final List<MessageQueue> mqs（broker中指定topic对应的队列queue）, final Message msg(消息体), final Object arg（send的时候，和MessageQueueSelector一起作为入参进来的）
     *
     * @throws Exception
     */
    @Test
    public void fifoMessage() throws Exception {
        //建立一个生产者
        DefaultMQProducer producer = new DefaultMQProducer("my-product");
        //绑定nameserver（类似注册中心）
        producer.setNamesrvAddr(NAME_SRV_ADDR);
        producer.start();
        String[] tags = {"TagA", "TagC", "TagD"};
        List<OrderStep> orderSteps = buildOrders();
        String dateStr = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        for (int i = 0; i < orderSteps.size(); i++) {
            String body = dateStr + "hello RocketMQ:" + orderSteps.get(i);
            Message message = new Message("UC-Topic-Order", tags[i % tags.length], "key" + i, body.getBytes(RemotingHelper.DEFAULT_CHARSET));
            SendResult sendResult = producer.send(message, (mqs, msg, arg) -> {
                //订单号和队列数量取模
                //多个队列轮询
                Long id = (Long) arg;  //根据订单id选择发送queue
                long index = id % mqs.size();
                return mqs.get((int) index);
            }, orderSteps.get(i).getOrderId());//订单ID
            System.out.println(String.format("SendResult status:%s, queueId:%d, body:%s", sendResult.getSendStatus(), sendResult.getMessageQueue().getQueueId(), body));

        }
        producer.shutdown();

    }

    private static class OrderStep {
        private long orderId;
        private String desc;

        public long getOrderId() {
            return orderId;
        }

        public void setOrderId(long orderId) {
            this.orderId = orderId;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        @Override
        public String toString() {
            return "OrderStep{" + "orderId=" + orderId + ", desc='" + desc + '\'' + '}';
        }
    }

    private List<OrderStep> buildOrders() {
        List<OrderStep> orderList = new ArrayList<OrderStep>();

        OrderStep orderDemo = new OrderStep();
        orderDemo.setOrderId(15103111039L);
        orderDemo.setDesc("创建");
        orderList.add(orderDemo);

        orderDemo = new OrderStep();
        orderDemo.setOrderId(15103111065L);
        orderDemo.setDesc("创建");
        orderList.add(orderDemo);

        orderDemo = new OrderStep();
        orderDemo.setOrderId(15103111039L);
        orderDemo.setDesc("付款");
        orderList.add(orderDemo);

        orderDemo = new OrderStep();
        orderDemo.setOrderId(15103117235L);
        orderDemo.setDesc("创建");
        orderList.add(orderDemo);

        orderDemo = new OrderStep();
        orderDemo.setOrderId(15103111065L);
        orderDemo.setDesc("付款");
        orderList.add(orderDemo);

        orderDemo = new OrderStep();
        orderDemo.setOrderId(15103117235L);
        orderDemo.setDesc("付款");
        orderList.add(orderDemo);

        orderDemo = new OrderStep();
        orderDemo.setOrderId(15103111065L);
        orderDemo.setDesc("完成");
        orderList.add(orderDemo);

        orderDemo = new OrderStep();
        orderDemo.setOrderId(15103111039L);
        orderDemo.setDesc("推送");
        orderList.add(orderDemo);

        orderDemo = new OrderStep();
        orderDemo.setOrderId(15103117235L);
        orderDemo.setDesc("完成");
        orderList.add(orderDemo);

        orderDemo = new OrderStep();
        orderDemo.setOrderId(15103111039L);
        orderDemo.setDesc("完成");
        orderList.add(orderDemo);

        return orderList;
    }


}
