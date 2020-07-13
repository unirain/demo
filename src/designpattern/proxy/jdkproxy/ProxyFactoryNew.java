package designpattern.proxy.jdkproxy;

import designpattern.proxy.staticproxy.IuserDao;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/********************************************************************************
 *
 * Title: 带有构造
 *
 * Description: 允许传入实现方法来反射调用
 *
 * @author chenlm
 * create date on 2020/4/23
 *
 *******************************************************************************/
public class ProxyFactoryNew<T> implements InvocationHandler {
    private T t;

    public ProxyFactoryNew(T t) {
        this.t = t;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("开始代理");
        Object o = method.invoke(t, args);
//        Object o = method.invoke(proxy, args); 使用这个会无限递归
        System.out.println("结束代理");
        return o;
    }

    public T getProxyInstance() {
        /**
         * 三个参数
         * 1.用哪个类的加载器去加载代理类
         * 2.动态代理类要实现的接口
         * 3.代理执行时会触发的invoke方法
         */
        return (T) Proxy.newProxyInstance(t.getClass().getClassLoader(), t.getClass().getInterfaces(), this);

    }

}
