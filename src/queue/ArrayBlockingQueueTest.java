package queue;


import org.junit.Test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/********************************************************************************
 *
 * Title: ArrayBlockingQueuec测试
 *
 * Description:
 *
 * @author chenlm
 * create date on 2019/1/31
 *
 *******************************************************************************/
public class ArrayBlockingQueueTest {
    //定义一个阻塞队列
    private BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(5);

    //汽车生产者
    public void produce() throws Exception {
        blockingQueue.put("a car");
    }

    //消费者
    public String consume() throws Exception {
        String car = blockingQueue.take();
        return car;
    }
    public int getCarNumber(){
        return blockingQueue.size();
    }

    public void test() {
        /**
         * 生产线程
         */
        class Producer implements Runnable {
            @Override
            public void run() {
                try {
                    while (true) {
                        System.out.println("生产者准备生产汽车：" + System.currentTimeMillis());
                        produce();
                        System.out.println("生产者生产汽车完毕："
                                + System.currentTimeMillis());
                        System.out.println("生产完后有汽车："+ getCarNumber()+"辆");
                        //一边生产
                        Thread.sleep(999);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }
        /**
         * 消费线程
         */
        class Consumer implements Runnable {
            @Override
            public void run() {
                try {
                    while (true) {
                        // 消费苹果
                        System.out.println("消费者准备拿走汽车："
                                + System.currentTimeMillis());
                        consume();
                        System.out.println("消费者消费拿走汽车完毕："
                                + System.currentTimeMillis());
                        System.out.println("消费完后有汽车："+getCarNumber()+"辆");
                        // 休眠1000ms
                        //一边消费
                        Thread.sleep(1000);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        ExecutorService executorService= Executors.newCachedThreadPool();
        executorService.submit(new Consumer());
        executorService.submit(new Producer());
        try{
            Thread.sleep(10000);
        }catch(Exception e){

        }
        executorService.shutdown();

    }

    @Test
    public void test1(){
        new ArrayBlockingQueueTest().test();
    }


}
