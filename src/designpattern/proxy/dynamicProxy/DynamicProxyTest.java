package designpattern.proxy.dynamicProxy;

import net.sf.cglib.proxy.*;

import java.lang.reflect.Method;

/********************************************************************************
 *
 * Title:  cglib代理
 *
 * Description:
 *
 * @author chenlm
 * create date on 2018/9/20
 *
 *******************************************************************************/
public class DynamicProxyTest {
    public TargetWork createProxy() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(TargetWork.class);
        enhancer.setCallbacks(new Callback[]{NoOp.INSTANCE,new proxyMethod()});
        enhancer.setCallbackFilter(new filter());
        return (TargetWork) enhancer.create();
    }

    public class proxyMethod implements MethodInterceptor {
        @Override
        public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
            System.out.println("进入代理");
            TargetWork targetWork=new TargetWork();
            Object o1 = methodProxy.invokeSuper(o, objects);
            System.out.println("结束代理");
            return o1;
        }
    }
    public class filter implements CallbackFilter{
        @Override
        public int accept(Method method) {
            if (method.getName().contains("TA")){
                return 1;
            }else{
                return 0;
            }
        }
    }
}
