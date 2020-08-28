package designpattern.proxy.jdkproxy.remotehttp;

import designpattern.proxy.staticproxy.IuserDao;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/********************************************************************************
 *
 * Title: 代理类查询
 *
 * Description:
 *
 * @author chenlm
 * create date on 2020/8/17
 *
 *******************************************************************************/
public class ProxyHttp implements InvocationHandler {

     public <T>  T getIterProxy(Class<T> t){
         return (T) Proxy.newProxyInstance(t.getClassLoader(), new Class[]{t}, this);
     }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
         //获取参数
         //获取返回参数
        System.out.println(method.getReturnType());
        Class c=method.getReturnType();

        if (c.isInterface()){

        }
        return null;
    }
}
