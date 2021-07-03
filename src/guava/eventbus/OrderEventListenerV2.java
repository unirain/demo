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
public class OrderEventListenerV2 {

    @Subscribe
    public void dealWithEvent(OrderMessageV2 message){
        System.out.println("v2我收到了您的命令，命令内容为：" + message.getContent());
    }
}
