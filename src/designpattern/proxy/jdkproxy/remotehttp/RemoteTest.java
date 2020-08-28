package designpattern.proxy.jdkproxy.remotehttp;

import org.junit.Test;

import java.lang.reflect.Proxy;

/********************************************************************************
 *
 * Title: test
 *
 * Description:
 *
 * @author chenlm
 * create date on 2020/8/17
 *
 *******************************************************************************/
public class RemoteTest {

    @Test
    public void test()throws Exception{
        ProxyHttp proxyHttp=new ProxyHttp();
        CallQueryService callQueryServic=proxyHttp.getIterProxy(CallQueryService.class);
        callQueryServic.queryMap("ddd");

    }
}
