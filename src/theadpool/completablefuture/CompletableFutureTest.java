package theadpool.completablefuture;

import cn.hutool.core.thread.ThreadUtil;
import io.netty.util.concurrent.CompleteFuture;
import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/********************************************************************************
 *
 * Title:  异步计算，主动回调
 *
 * Description:
 *
 * 最后我们注意CompletableFuture的命名规则：
 *
 * xxx()：表示该方法将继续在已有的线程中执行；
 * xxxAsync()：表示将异步在线程池中执行。
 *
 * @author chenlm
 * create date on 2021/6/22 0022
 *
 *******************************************************************************/
public class CompletableFutureTest {

    /**
     * 异步任务结束时，会自动回调某个对象的方法；
     * 异步任务出错时，会自动回调某个对象的方法；
     * 主线程设置好回调后，不再关心异步任务的执行。
     *
     * @throws Exception
     */
    @Test
    public void futureCallBack() throws Exception {
        //构建一个异步线程
        CompletableFuture<Double> cf = CompletableFuture.supplyAsync(this::fetchPrice);
        //如果执行成功
        cf.thenAccept((r) -> System.out.println("获取到得结果是：" + r));
        cf.exceptionally((e) -> {
            System.out.println("异常啦");
            e.printStackTrace();
            return null;
        });
        TimeUnit.SECONDS.sleep(5);

    }


    /**
     * 串行操作，可以从上一个任务中拿结果，然后执行
     * @throws Exception
     */
    @Test
    public void serialWork() throws Exception {
        //构建任务
        CompletableFuture<String> cfQuery = CompletableFuture.supplyAsync(() -> queryCode("石头"));
        //串行任务，cfQuery 得到得结果后运行
        CompletableFuture<Double> cfFetch = cfQuery.thenApplyAsync(this::queryPrice);

        cfFetch.thenAccept((r) -> System.out.println("获取到得结果是：" + r));
        TimeUnit.SECONDS.sleep(5);

    }

    /**
     * 并发执行
     * 股票场景
     * 从新浪、网易 利用名称 找到代码
     *
     * 利用代码 从新浪，网易从 找到价格
     *
     * 只要新浪，网易有一个返回就可以
     *
     *
     * 除了anyOf()可以实现“任意个CompletableFuture只要一个成功”，
     *  allOf()可以实现“所有CompletableFuture都必须成功”，
     *  这些组合操作可以实现非常复杂的异步流程控制。
     * @throws Exception
     */
    @Test
    public void concurrentWork()throws Exception{
        //创建两个任务
        CompletableFuture<String> sincf=CompletableFuture.supplyAsync(()-> queryCode("中国石油","https://www.sina.com.cn/"));
        CompletableFuture<String> wycf=CompletableFuture.supplyAsync(()-> queryCode("中国石油","https://www.163.com/"));
        //合并为一个任务
        //这边只能为object
        CompletableFuture<Object> joinCf=CompletableFuture.anyOf(sincf,wycf);
        //两个异步查询结果再去价格
        CompletableFuture<Double> sinPcf=  joinCf.thenApplyAsync((code)->queryPrice((String) code,"https://www.sina.com.cn/"));
        CompletableFuture<Double> wyPcf=  joinCf.thenApplyAsync((code)->queryPrice((String) code,"https://www.163.com/"));
        //合并
        CompletableFuture<Object> resultCf=CompletableFuture.anyOf(sinPcf,wyPcf);
        //输出
        resultCf.thenAccept((r) -> System.out.println("获取到得结果是：" + r));
        TimeUnit.SECONDS.sleep(5);








    }


    private Double fetchPrice() {
        ThreadUtil.safeSleep(100);

        return 10 + Math.random() * 20 * 10;
    }


    private String queryCode(String name) {
        System.out.println("name"+name);
        return "987";
    }

    private Double queryPrice(String code) {
        ThreadUtil.safeSleep(100);
        return 10 + Math.random() * 20 * 10;
    }

    private String queryCode(String name,String url){
        System.out.println("query code from " + url + "...");
        try {
            Thread.sleep((long) (Math.random() * 100));
        } catch (InterruptedException e) {
        }
        return "601857";
    }

    private Double queryPrice(String code,String url){
        System.out.println("query price from " + url + "...");
        try {
            Thread.sleep((long) (Math.random() * 100));
        } catch (InterruptedException e) {
        }
        return 5 + Math.random() * 20;
    }

    @Test
    public void should_dd(){
        System.out.println( (int) (Math.random()*100));

    }





}
