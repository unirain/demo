package redis;


import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

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
    private static String ip = "172.18.0.43";
    private static int port = 6378;
    private static String password="redis$sso#43";
    private static Jedis jedis;

    static {
        try {
            //连接本地的 Redis 服务
            jedis = new Jedis(ip,port,600,600);
//            jedis.auth(password);
            System.out.println("连接成功");
            System.out.println("服务正在运行: "+jedis.ping());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public  void test1()throws Exception{
        jedis.set("1","111");
        jedis.incrBy("1",80);
        System.out.println(jedis.get("1"));
    }
    @Test
    public  void test2()throws Exception{

    }

}
