package websocket.program;

import javax.websocket.*;

/********************************************************************************
 *
 * Title: 编程式websocket
 *
 * Description:
 *
 * @author chenlm
 * create date on 2020/12/30 0030
 *
 *******************************************************************************/
public class WebSocketPro extends Endpoint {
    private static class ChatMessageHandler implements MessageHandler.Partial<String> {
        private Session session;

        private ChatMessageHandler(Session session) {
            this.session = session;
        }

        @Override
        public void onMessage(String message, boolean last) {
            String msg = String.format("%s %s %s", session.getId(), "said:", message);
            try {
                session.getBasicRemote().sendText(msg);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    ;

    @Override
    public void onOpen(Session session, EndpointConfig endpointConfig) {
        System.out.println("打开了一个连接");
        session.addMessageHandler(new ChatMessageHandler(session));

    }

    @Override
    public void onClose(Session session, CloseReason closeReason) {
        System.out.println("关闭");
    }
}
