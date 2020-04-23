package queue.delay;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/********************************************************************************
 *
 * Title: 延时任务
 *
 * Description: 延迟任务必须实现Delayed 接口
 *
 * @author chenlm
 * create date on 2019/12/31
 *
 *******************************************************************************/
public class DelayTask  implements Delayed {
    private String name;
    private long start;
    private long delayTime;

    public DelayTask(String name, long delaySecond) {
        this.name = name;
        this.start=System.currentTimeMillis();
        delayTime=TimeUnit.SECONDS.toMillis(delaySecond);
    }

    /**
     *  小于等于的时候就被弹出
     * @param timeUnit
     * @return
     */
    @Override
    public long getDelay(TimeUnit timeUnit) {
        return start+delayTime-System.currentTimeMillis();
    }

    /**
     * 序列位置，会影响弹出
     * @param delayed
     * @return
     */
    @Override
    public int compareTo(Delayed delayed) {
        //3 -2  =1 大于0放后
        return  (int) (this.getDelay(TimeUnit.MILLISECONDS)-delayed.getDelay(TimeUnit.MILLISECONDS));
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("DelayTask{");
        sb.append("name='").append(name).append('\'');
        sb.append(", start=").append(start);
        sb.append(", delayTime=").append(delayTime);
        sb.append('}');
        return sb.toString();
    }
}
