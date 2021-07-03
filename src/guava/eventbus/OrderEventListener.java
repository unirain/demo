package guava.eventbus;

import com.google.common.eventbus.Subscribe;

/********************************************************************************
 *
 * Title: 监听者
 *
 * Description:
 *
 * @author chenlm
 * create date on 2021/6/25 0025
 *
 *******************************************************************************/
public class OrderEventListener {

    @Subscribe
    public void dealWithEvent(OrderMessage message){
        System.out.println("我收到了您的命令，命令内容为：" + message.getContent());
    }
}
