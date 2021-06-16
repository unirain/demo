package lru;

import com.google.common.collect.Maps;

import java.util.Map;
import java.util.Objects;

/********************************************************************************
 *
 * Title:  lru 缓存
 *
 * Description:LRU（Least Recently Used）直译为“最近最少使用”
 *
 * 1 缓存的容量是有限的
 * 2 当缓存容量不足以存放需要缓存的新数据时，必须丢掉最不常用的缓存数据
 *
 * @author chenlm
 * create date on 2021/6/15 0015
 *
 *******************************************************************************/
public class LruCache {
    //存储
    private final Map<String, Point> cacheMap = Maps.newConcurrentMap();
    //头和尾用来标识，方便丢弃和插入时快速找到
    //头（这边为最少访问）
    private Point head;
    //尾巴
    private Point tail;
    //容量
    private final int capacity;

    public LruCache(int capacity) {
        this.capacity = capacity;
    }

    public void put(String key, Object value) {
        Point mapValue = cacheMap.get(key);
        if (!Objects.isNull(mapValue)) {
            //not null
            mapValue.setValue(value);
            refreshPoint(mapValue);
        } else {
            //is null
            if (cacheMap.size() >= capacity) {
                String oldKey = removePoint(head);
                cacheMap.remove(oldKey);
            }
            Point point = new Point();
            point.setKey(key);
            point.setValue(value);
            addPoint(point);
            cacheMap.put(key, point);
        }
    }

    public Object get(String key) {
        Point mapValue = cacheMap.get(key);
        if (mapValue != null) {
            refreshPoint(mapValue);
        }
        return mapValue == null ? null : mapValue.getValue();
    }

    /**
     * 刷新到最近访问
     *
     * @param point
     */
    private void refreshPoint(Point point) {
        if (point == tail) {
            return;
        }
        removePoint(point);
        addPoint(point);
    }

    /**
     * 移除节点
     * <p>
     * 要考虑头和尾巴，中间切断
     *
     * @param point
     */
    private String removePoint(Point point) {
        if (point == tail) {
            //尾巴
            tail = point.getPrev();
        } else if (point == head) {
            //头
            head = point.getNext();
        } else {
            //中间
            point.getPrev().setNext(point.getNext());
            point.getNext().setPrev(point.getPrev());
        }
        return point.getKey();
    }

    /**
     * 添加节点，尾部添加
     *
     * @param point
     */
    private void addPoint(Point point) {
        if (tail != null) {
            tail.setNext(point);
            point.setPrev(tail);
        }
        //指向尾部
        tail = point;
        //只有一个节点
        if (head == null) {
            head = point;
        }
    }


}
