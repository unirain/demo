package websocket.program;

import javax.websocket.Endpoint;
import javax.websocket.server.ServerApplicationConfig;
import javax.websocket.server.ServerEndpointConfig;
import java.util.HashSet;
import java.util.Set;

/********************************************************************************
 *
 * Title: 
 *
 * Description: 会被自动识别，自动加载endpoint的实现类
 *
 * @author chenlm
 * create date on 2020/12/30 0030
 *
 *******************************************************************************/
public class ChatServerApplicationConfig implements ServerApplicationConfig {
    @Override
    public Set<ServerEndpointConfig> getEndpointConfigs(Set<Class<? extends Endpoint>> set) {
        Set<ServerEndpointConfig> result = new HashSet<>();
        if (set.contains(WebSocketPro.class)) {
            result.add(ServerEndpointConfig.Builder.create(WebSocketPro.class, "/program/chat").build());
        }
        return result;
    }

    @Override
    public Set<Class<?>> getAnnotatedEndpointClasses(Set<Class<?>> set) {
        return set;
    }
}
