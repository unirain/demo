package redis;

import com.sun.deploy.util.StringUtils;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/********************************************************************************
 *
 * Title: 分布式锁2.0版本
 *
 * Description: 目的是解决上一版本死锁，锁过期但是线程还在处理等问题
 *
 * @author chenlm
 * create date on 2020/7/16
 *
 *******************************************************************************/
public class RedisLockV2 {


    /**
     * 锁键
     */
    private String lockKey;
    /**
     * 锁超时时间
     */
    private int expireMsecs;
    /**
     * 锁等待，防止线程饥饿
     */
    private int timeoutMsecs;


    public String getLockKey() {
        return lockKey;
    }

    public void setLockKey(String lockKey) {
        this.lockKey = lockKey;
    }

    public int getExpireMsecs() {
        return expireMsecs;
    }

    public void setExpireMsecs(int expireMsecs) {
        this.expireMsecs = expireMsecs;
    }

    public int getTimeoutMsecs() {
        return timeoutMsecs;
    }

    public void setTimeoutMsecs(int timeoutMsecs) {
        this.timeoutMsecs = timeoutMsecs;
    }

    /**
     * 获取redis连接
     *
     * @return
     */
    public Jedis getJedis() {
        JedisPool jedisPool = new JedisPool("localhost", 45004);
        return jedisPool.getResource();
    }

    /**
     * 获取锁
     *
     * @return
     */
    public boolean acquire() {
        int timeOut = timeoutMsecs;
        try {
            while (timeOut >= 0) {
                //开始获得锁
                long expires = System.currentTimeMillis() + expireMsecs + 1;
                long flag = getJedis().setnx(lockKey, String.valueOf(expires));
                if (flag == 1) {
                    //获得锁
                    return true;
                }
                //尝试判断过期时间
                String currentValueStr = getJedis().get(lockKey);
                if (currentValueStr==null && Long.parseLong(currentValueStr) < System.currentTimeMillis()) {
                    //已过期
                    //获取旧值，此时可能会有两个线程进来
                    String oldValue = getJedis().getSet(lockKey, String.valueOf(expires));
                    //防止两个线程进来，在判断一次,旧值可能为空，也就是锁可能刚好释放了
                    if (currentValueStr.equals(oldValue) || oldValue == null) {
                        return true;
                    }
                }
                timeOut--;
                TimeUnit.SECONDS.sleep(3);
            }

        } catch (Exception e) {
            e.printStackTrace();

        }
        return false;


    }

    /**
     * 锁释放
     */
    public void replace() {
        //获取锁
        try {
            //保证在当前有效期内
            String keyValue = getJedis().get(lockKey);
            if (keyValue == null && Long.parseLong(keyValue) < System.currentTimeMillis()) {
                //在有效期内
                getJedis().del(keyValue);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void test1()throws Exception{
        RedisLockV2 redisLockV2=new RedisLockV2();
        redisLockV2.setLockKey("chenlm-test");
        redisLockV2.setExpireMsecs(15*1000);
        redisLockV2.setTimeoutMsecs(10);
        IntStream.range(1,10).forEach(i->{
            Thread thread=new Thread(()->{
                if (redisLockV2.acquire()){
                    System.out.println("我是"+i+",我已经获取了锁");
                    System.out.println("我是"+i+",我开始做事情");
                    try{
                        TimeUnit.SECONDS.sleep(5);
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                    redisLockV2.replace();
                    System.out.println("我是"+i+",我事情做好了，锁已经释放");
                }else {
                    System.out.println("我是"+i+"我获取不到锁");
                }

            });
            thread.start();
        });
        System.in.read();
    }



}
