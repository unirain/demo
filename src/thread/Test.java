package thread;

import java.util.concurrent.*;
import java.util.stream.IntStream;

/********************************************************************************
 *
 * Title: 线程测试
 *
 * Description: callable的使用（允许异常和返回结果）
 *
 * @author chenlm
 * create date on 2019/2/20
 *
 *******************************************************************************/
public class Test {
    /**
     * 搭配executorService 使用
     *
     * @throws Exception
     */
    @org.junit.Test
    public void test1() throws Exception {
        ExecutorService executorService = Executors.newCachedThreadPool();
        //异步
        Future future = executorService.submit(new TaskTest());
        //确认异步
        System.out.println("被调用");
        TimeUnit.SECONDS.sleep(3);
        //阻塞获取
        try {

            System.out.println(future.get());
        } catch (Exception e) {
            //捕获get异常
            System.out.println(e.getMessage());
        }

    }
    @org.junit.Test
    public void test111() throws Exception {
        ExecutorService executorService = Executors.newCachedThreadPool();
        //异步
        Future future = executorService.submit(new TaskTest());

    }

    class TaskTest implements Callable<String> {
        @Override
        public String call() throws Exception {
            String a = "abc";
            System.out.println("hah");
            TimeUnit.SECONDS.sleep(3);
            //允许抛出异常
            if ("abc".equals(a)) {
                throw new RuntimeException("ces");
            }

            return a + "ef";
        }
    }

    /**
     * 使用futruetask
     *
     * @throws Exception
     */
    @org.junit.Test
    public void test2() {
        FutureTask<String> futureTask = new FutureTask<>(new TaskTest());
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.submit(futureTask);
        System.out.println(futureTask.cancel(true));

        //


    }

    /**
     * 使用futruetask 定义runnable返回值
     *
     * @throws Exception
     */
    @org.junit.Test
    public void test34() throws Exception {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Runnable runnable = () -> System.out.println("aaa");
        FutureTask<String> runnableCall = new FutureTask<>(runnable, "ok");
        executorService.execute(runnableCall);
        System.out.println(runnableCall.get());
    }


    /**
     * ExecutorService--newFixedThreadPool
     */
    @org.junit.Test
    public void test3() throws Exception {
        //创建定长的线程池
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        IntStream.range(0, 10).forEach(i -> {
            System.out.println("正在执行" + i);
            Thread thread = new Thread(() -> {
                try {
                    System.out.println("我是线程");
                    TimeUnit.SECONDS.sleep(2);
                    //发现终止对其他线程不影响
                    if (i == 5) {
                        throw new RuntimeException("stop");
                    }

                } catch (Exception e) {
                    e.printStackTrace();

                }
            });
            executorService.submit(thread);
        });
        TimeUnit.SECONDS.sleep(10);
    }

}
