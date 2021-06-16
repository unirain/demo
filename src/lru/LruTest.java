package lru;

import cn.hutool.cache.impl.LRUCache;
import org.junit.Test;

/********************************************************************************
 *
 * Title: 
 *
 * Description:
 *
 * @author chenlm
 * create date on 2021/6/16 0016
 *
 *******************************************************************************/
public class LruTest {
    @Test
    public void should_setCache(){
//        LruCache lruCache=new LruCache(3);
        LRUCache<String,String> lruCache=new LRUCache<>(3);
        lruCache.put("1","1");
        lruCache.put("2","2");
        lruCache.put("3","3");
        lruCache.put("4","4");

        System.out.println(lruCache.get("2"));


    }
}
