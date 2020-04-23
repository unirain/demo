package queue;

import org.junit.Test;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/********************************************************************************
 *
 * Title: 非阻塞队列使用
 *
 * Description:  其实和阻塞的区别就是他有一些非阻塞的方法，相对于其他阻塞类来说，ConcurrentLinkedQueue只有非阻塞方法，而其他阻塞类却有非阻塞方法和阻塞方法
 *
 * @author chenlm
 * create date on 2020/3/17
 *
 *******************************************************************************/
public class ConcurrentLinkedQueueTest {
    private ConcurrentLinkedQueue<String> concurrentLinkedQueue = new ConcurrentLinkedQueue<>();

    private void consume() {
        while (true) {
            String a = concurrentLinkedQueue.poll();
            System.out.println("消费：" + a);
        }
    }

    private void produce() {
        while (true){
            System.out.println("生产数据中");
            concurrentLinkedQueue.addAll(Stream.of("1", "33", "443").collect(Collectors.toList()));
        }

    }

    @Test
    public void ConsumeTask() throws Exception {
        Thread thread1 = new Thread(this::consume);
        Thread thread2 = new Thread(this::produce);
        Executor executor = Executors.newCachedThreadPool();
        executor.execute(thread1);
        executor.execute(thread2);

        TimeUnit.SECONDS.sleep(5);
        System.out.println(concurrentLinkedQueue.size());

    }


}
