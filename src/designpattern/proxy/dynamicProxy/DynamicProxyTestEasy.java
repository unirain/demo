package designpattern.proxy.dynamicProxy;

import net.sf.cglib.proxy.*;

import java.lang.reflect.Method;

/********************************************************************************
 *
 * Title: 代理的简单使用
 *
 * Description:
 *
 * @author chenlm
 * create date on 2018/9/20
 *
 *******************************************************************************/
public class DynamicProxyTestEasy {
    public TargetWork createProxy() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(TargetWork.class);
        enhancer.setCallback(new proxyMethod());
        return (TargetWork) enhancer.create();
    }
    public class proxyMethod implements MethodInterceptor {
        @Override
        public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
            System.out.println("进入代理");
            Object o1 = methodProxy.invokeSuper(o, objects);
            System.out.println("结束代理");
            return o1;
        }
    }
}
