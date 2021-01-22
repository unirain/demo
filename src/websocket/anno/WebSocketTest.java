package websocket.anno;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/********************************************************************************
 *
 * Title: 测试使用websocket--注解实现
 *
 * Description: 每次新链接进来都会创建一个新的实例
 *
 * @author chenlm
 * create date on 2020/12/30 0030
 *
 *******************************************************************************/
@ServerEndpoint("/websocket")
public class WebSocketTest {
    private Session session;

    /**
     * 打开
     * @param session
     */
    @OnOpen
    public void onOpen(Session session){
        System.out.println(this);
        this.session=session;
        System.out.println("有新连接加入！");

    }

    /**
     * 关闭
     */
    @OnClose
    public void onClose(){
        System.out.println("连接关闭！");
    }

    /**
     * 接受到消息
     * @param msg
     * @param session
     */
    @OnMessage
    public void onMessage(String msg,Session session){
        System.out.printf("接收到客户端消息%s%n",msg);
        try{
            this.session.getBasicRemote().sendText(msg);
        }catch(Exception e){
            e.printStackTrace();
        }


    }



}
