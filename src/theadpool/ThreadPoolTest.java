package theadpool;

import org.junit.Test;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/********************************************************************************
 *
 * Title: 自定义线程池ThreadPoolExecutor测试
 *
 * Description:总结：先看核心 核心满了放队列，队列满了放核心余下的线程，放不下了抛异常
 *
 * @author chenlm
 * create date on 2020/3/17
 *
 *******************************************************************************/
public class ThreadPoolTest {
    @Test
    public void test1() throws Exception {
        //核心线程大小
        int corePoolSize = 2;
        //最大线程池大小
        int maximumPoolSize = 4;
        //线程最大空闲时间，用于控制核心外线程空间时间  ，之后被回收
        long keepAliveTime = 10;
        //时间单位
        TimeUnit unit = TimeUnit.SECONDS;
        //阻塞队列
        BlockingQueue<Runnable> blockingQueue = new ArrayBlockingQueue<>(2);
        //线程工厂
        ThreadFactory threadFactory = new NameTFactory();
        //拒绝策略
        IgnorePolicy ignorePolicy = new IgnorePolicy();
        //定义一个线程池,自定义的拒绝策略
//        ThreadPoolExecutor executor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit, blockingQueue, threadFactory, ignorePolicy);
        //JAVA已定义的策略--让调用线程执行
//        ThreadPoolExecutor executor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit, blockingQueue, threadFactory, new ThreadPoolExecutor.CallerRunsPolicy());
        //JAVA已定义的策略--直接抛出异常
        ThreadPoolExecutor executor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit, blockingQueue, threadFactory, new ThreadPoolExecutor.AbortPolicy());

        /**
         * 如果有3个任务时，即 大于核心线程2  小于最大线程数4
         * 则仅当队列已满时才会创建新线程（3-2=1 1个对于blockingQueue来说没满）
         */
//        for (int i = 1; i <= 3; i++) {
//            MyTask task = new MyTask(String.valueOf(i));
//            executor.execute(task);
//        }
        /**
         * 如果有7个任务时，即 大于核心线程2 而队列只能放两个任务，
         * 最大池是4，队列只能放2个，所以有一个要被拒绝
         */
        for (int i = 1; i <= 7; i++) {
            MyTask task = new MyTask(String.valueOf(i));
            executor.execute(task);
        }
        System.in.read(); //阻塞主线程
        /**
         * 总结：先看核心 核心满了放队列，队列满了放核心余下的线程，放不下了抛异常
         */
    }

    /**
     * 线程工厂
     */
    class NameTFactory implements ThreadFactory {
        private AtomicInteger atomicInteger = new AtomicInteger(0);


        @Override
        public Thread newThread(Runnable runnable) {
            Thread thread = new Thread(runnable, "我是" + atomicInteger.incrementAndGet() + "个线程");
            System.out.println("【" + thread.getName() + "】已经被创建");
            return thread;
        }
    }

    /**
     * 线程拒绝策略
     */
    class IgnorePolicy implements RejectedExecutionHandler {
        @Override
        public void rejectedExecution(Runnable runnable, ThreadPoolExecutor threadPoolExecutor) {
            System.err.println(runnable.toString() + "被拒绝了");
        }
    }

    /**
     * 线程任务
     */
    class MyTask implements Runnable {
        String name;

        public MyTask(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            System.out.println(this.toString() + "正在运行");
            try {
                Thread.sleep(4000); //让任务执行慢点
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            final StringBuffer sb = new StringBuffer("MyTask{");
            sb.append("name='").append(name).append('\'');
            sb.append('}');
            return sb.toString();
        }
    }


}
