package redis;

import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.concurrent.TimeUnit;

/********************************************************************************
 *
 * Title: redis锁应用
 *
 * Description:
 *
 * @author chenlm
 * create date on 2020/7/15
 *
 *******************************************************************************/
public class RedisLockTest {


    /**
     * 使用连接池
     *
     * @return
     */
    private Jedis getJedis() {
        JedisPool jedisPool = new JedisPool("localhost", 45004);
        return jedisPool.getResource();
    }


    @Test
    public void test1() throws Exception {
        release("ui");
        Thread t1 = new Thread(() -> doword("T1"));
        Thread t2 = new Thread(() -> doword("T2"));
        Thread t3 = new Thread(() -> doword("T3"));
        t1.start();
        t2.start();
        t3.start();
        System.in.read();

    }

    private void doword(String name) {
        final String lockKey = "ui";
        try {
            redisLock(lockKey, 10);
            System.out.println(name + "我获得锁，开始处理。。。");
            TimeUnit.SECONDS.sleep(5);
            release(lockKey);
        } catch (Exception e) {
            e.printStackTrace();

        }

    }

    /**
     * 加锁
     * 如果程序问题，有可能出现死锁，锁将无法释放出来，其他一直获取不了
     *
     * @param key
     */
    private void redisLock(String key, int tryNum) throws Exception {
        //加锁
        //如果加锁失败，尝试不断加锁
        int count = 0;
        while (count <= tryNum) {
            long flag = getJedis().setnx(key, "1");
            if (flag == 0) {
                //失败 3s 重试
                TimeUnit.SECONDS.sleep(3);
                System.out.println("开始第" + count + "次尝试获取锁");
                count++;
            } else {
                return;
            }
        }
        throw new RuntimeException("经过" + tryNum + "次的尝试获取锁，还是获取不到！");
    }

    /**
     * 解锁
     *
     * @param key
     * @throws Exception
     */
    private void release(String key) throws Exception {
        getJedis().del(key);
        System.out.println(key + "锁已被释放");
    }
}
