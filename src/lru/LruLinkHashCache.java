package lru;

import java.util.LinkedHashMap;
import java.util.Map;

/********************************************************************************
 *
 * Title: 基于linkhashmap
 *
 * Description: accessorder 利用
 *
 * @author chenlm
 * create date on 2021/6/17 0017
 *
 *******************************************************************************/
public class LruLinkHashCache<K,V> extends LinkedHashMap<K,V> {
    //容量
    private final int capacity;
    public LruLinkHashCache(int capacity){
        // 容量为最大值/0.75，即最大负载容量为maxSize,这边是因为hashmap扩容得判断方式是  size>threshold（负载因子*initial），当然后续threshold 双倍扩容
        // accessOrder=true  根据查询排序，即最近被使用的放到后面
        super((int) Math.ceil(capacity / 0.75) + 1,0.75f,true);
        this.capacity=capacity;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return super.size()>capacity;
    }
}
