package redis;


import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

import javax.jws.Oneway;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/********************************************************************************
 *
 * Title: redis操作类
 *
 * Description:
 *
 * @author chenlm
 * create date on 2018/11/13
 *
 *******************************************************************************/
public class RedisUtil {
    private static String ip = "127.0.0.1";
    private static int port = 6379;
    private static Jedis jedis;
    private static Jedis jedis2;

    static {
        try {
            //连接本地的 Redis 服务
            jedis = new Jedis(ip, port, 600, 100000);
            jedis2 = new Jedis(ip, port, 600, 100000);
            System.out.println("连接成功");
            System.out.println("服务正在运行: " + jedis.ping());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * string 类型的使用
     * 缓存
     * 计数器
     * 分布式锁
     *
     * @throws Exception
     */
    @Test
    public void test1() throws Exception {
        //初始化为0
        jedis.set("aa", "0");
        //原子性增加
        long count = jedis.incrBy("aa", 3);
        long count1 = jedis.incrBy("aa", 5);
        System.out.println(count);
        System.out.println(count1);
        System.out.println(jedis.get("aa"));

        //设置存活时间
        jedis.set("clm", "很帅", "NX", "EX", 3L);
        TimeUnit.SECONDS.sleep(2L);
        System.out.println(jedis.get("clm"));
        TimeUnit.SECONDS.sleep(2L);
        System.out.println(jedis.get("clm"));


    }

    /**
     * hash 类型的使用
     *
     * @throws Exception
     */
    @Test
    public void test2() throws Exception {
        jedis.hset("hh", "1", "我是谁");
        System.out.println(jedis.hget("hh", "1"));
        //获取key对应的全部 hash
        System.out.println(jedis.hgetAll("hh"));
    }

    /**
     * list列表
     * 可用于消息队列，入栈出栈
     *
     * @throws Exception
     */
    @Test
    public void test3() throws Exception {
        jedis.flushDB();
        //（从右边push）
        jedis.rpush("rp", "1", "2");
        jedis.rpush("rp", "3", "4");
        //（从左边push）
        jedis.lpush("lp", "1", "2");
        jedis.lpush("lp", "3", "4");
        //获取list所有数据
        List<String> lpList = jedis.lrange("lp", 0, -1);
        List<String> rpList = jedis.lrange("rp", 0, -1);
        lpList.forEach(System.out::print);
        rpList.forEach(System.out::print);

    }

    /**
     * SET集合
     * Redis 的 Set 是 String 类型的无序集合。集合成员是唯一的，这就意味着集合中不能出现重复的数据。
     * Redis 中集合是通过哈希表实现的，所以添加，删除，查找的复杂度都是 O(1)。
     * <p>
     * <p>
     * 使用场景
     * 抽奖系统，用spop--随机弹出
     * 赞过的文章，收藏过的文章等--sadd  然后smembers
     * 共同关注 --交集 sinter
     *
     * @throws Exception
     */

    @Test
    public void test4() throws Exception {
        jedis.sadd("ylz", "帅", "高", "富");
        //向集合key添加element(如果element已经存在，添加失败）--覆盖
        jedis.sadd("ylz", "帅", "高", "帮");
        System.out.println(jedis.smembers("ylz"));
        jedis.sadd("ylz", "帅", "高", "帮", "帮");
        //随机弹出,对他的值随机抛出
        System.out.println(jedis.spop("ylz"));
    }

    /**
     * ZSet（有序集合）
     * Redis 有序集合和集合一样也是string类型元素的集合,且不允许重复的成员。
     * 不同的是每个元素都会关联一个double类型的分数。
     * redis正是通过分数来为集合中的成员进行从小到大的排序。
     * 有序集合的成员是唯一的,但分数(score)却可以重复。
     * <p>
     * <p>
     * 应用场景
     * 各种榜单（score:timestamp, saleCount, followCount)
     *
     * @throws Exception
     */
    @Test
    public void test5() throws Exception {
        //相同分数，谁先添加 谁是第一
        jedis.zadd("wa", 6.0, "hhh");
        jedis.zadd("wa", 6.0, "xdd");
        jedis.zadd("wa", 5.0, "lll");
        //返回指定元素的排名
        System.out.println(jedis.zrank("wa", "xdd"));

    }

    /**
     * 事务 --MULTI
     * 批量操作在发送 EXEC 命令前被放入队列缓存。
     * 收到 EXEC 命令后进入事务执行，事务中任意命令执行失败，其余的命令依然被执行。
     * 在事务执行过程，其他客户端提交的命令请求不会插入到事务执行命令序列中。
     *
     * @throws Exception
     */
    @Test
    public void testSW() throws Exception {
        /**
         *  一个链接要是开启事物，这个链接就必须在事务内操作，如果单独操作会报错，也就是说必须使用Transaction
         *  中的 api操作
         */
        Thread t1 = new Thread(() -> {
            Transaction transaction = jedis.multi();
            transaction.set("clm", "很帅");
            System.out.println(transaction.get("clm"));
            List<Object> objectList = transaction.exec();
            System.out.println("end");
        });


        /**
         * 不同的连接可以操作同个key 会有并发问题
         *
         */
        Thread t2 = new Thread(() -> {
            jedis2.set("clm", "非常帅");
            System.out.println(jedis2.get("clm"));
        });
        t1.start();
        t2.start();
        IntStream.range(0, 5).forEach((i) -> {
            try {
                TimeUnit.SECONDS.sleep(1);
                System.out.println(jedis.get("clm"));
            } catch (Exception e) {
                e.getStackTrace();

            }
        });


    }

    @Test
    public void testSW2() throws Exception {
        jedis.flushDB();
        jedis.set("clm","很帅");
        Transaction transaction= jedis.multi();
        transaction.set("cs","在吗");
        transaction.set("clm","非常帅啊");
        Object a=123;
        transaction.lpush("ylz",(String)a);
        List<Object> objectList=transaction.exec();
        if (objectList.isEmpty()){
            System.out.println("error");
        }else {
            System.out.println("sucess");
        }
        System.out.println( jedis.get("clm"));
        System.out.println( jedis.get("cs"));
    }

    /**
     * 类似了乐观锁，搭配事务一起用
     *
     * 用于监控key是否被其他线程连接改变
     *
     *
     * @throws Exception
     */
    @Test
    public void testWatch() throws Exception {
        jedis.flushDB();
        jedis.set("clm","很帅");
        //开始监控
        jedis.watch("clm","cs");
        //watch和multi之间插入监控的值导致事务无法执行--监控值被更改
//        jedis.set("clm","watch和multi之间插入");
        //其他值不影响
        jedis.set("cl","watch和multi之间插入");
        Transaction transaction= jedis.multi();
        transaction.set("cs","在吗");
        transaction.set("clm","非常帅啊");
        //监控的值被更改导致事务无法执行
//        jedis2.set("clm","干扰你");
        List<Object> objectList=transaction.exec();
        if (objectList.isEmpty()){
            System.out.println("error");
        }else {
            System.out.println("sucess");
        }
        System.out.println( jedis.get("clm"));
        System.out.println( jedis.get("cs"));

    }

    /**
     * 多线程监控会不会有问题
     *
     * 第一个连接把值成功写进了，watch监控到了发生变化，所以第二个连接的事物提交失败
     * @throws Exception
     */

    @Test
    public void testWatch2() throws Exception {
        jedis.flushDB();
        jedis.set("clm","很帅");
        jedis.watch("clm");
        jedis2.watch("clm");
        Transaction transaction= jedis.multi();
        transaction.set("cs","在吗");
        transaction.set("clm","非常帅啊");

        List<Object> objectList=transaction.exec();
        if (objectList.isEmpty()){
            System.out.println("error");
        }else {
            System.out.println("sucess");
        }

        //会提交失败
        Transaction transaction2= jedis2.multi();
        transaction.set("cs","在吗");
        transaction.set("clm","非常帅啊");
        List<Object> objectList2=transaction2.exec();
        if (objectList2.isEmpty()){
            System.out.println("error2");
        }else {
            System.out.println("sucess2");
        }


    }

    /**
     * lua脚本
     * @throws Exception
     */
    @Test
    public void testLua() throws Exception {
        jedis.flushDB();
        //参数必须是keys 和ARGV
        jedis.eval("return redis.call('set',KEYS[1],ARGV[1])",1,"name","chenliming");
        jedis.eval("return redis.call('rpush',KEYS[1],ARGV[1],ARGV[2])",1,"ylz","cs","dd");
        System.out.println(jedis.llen("ylz"));
        System.out.println(jedis.lpop("ylz"));
        System.out.println(jedis.llen("ylz"));


    }

    /**
     * 测试数据恢复
     * @throws Exception
     */
    @Test
    public void testRecove() throws Exception {
        //1先保存，持久化
//        jedis.eval("return redis.call('set',KEYS[1],ARGV[1])",1,"name","chenliming");
//        jedis.eval("return redis.call('set',KEYS[1],ARGV[1])",1,"sex","男");
//        jedis.save();
        //2重启redis get看看
        System.out.println(jedis.get("name"));


    }




}
