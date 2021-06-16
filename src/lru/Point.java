package lru;

/********************************************************************************
 *
 * Title: 缓存节点
 *
 * Description:
 *
 * @author chenlm
 * create date on 2021/6/15 0015
 *
 *******************************************************************************/


public class Point {
    private String key;
    private Object value;
    private Point prev;
    private Point next;

    public Point() {
        super();
    }
    public Point(String key, Object value, Point prev, Point next) {
        this.key = key;
        this.value = value;
        this.prev = prev;
        this.next = next;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public Point getPrev() {
        return prev;
    }

    public void setPrev(Point prev) {
        this.prev = prev;
    }

    public Point getNext() {
        return next;
    }

    public void setNext(Point next) {
        this.next = next;
    }
}
