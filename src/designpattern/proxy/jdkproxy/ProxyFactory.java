package designpattern.proxy.jdkproxy;

import designpattern.proxy.staticproxy.IuserDao;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/********************************************************************************
 *
 * Title: 动态代理
 *
 * Description:
 *
 * author chenlm
 * create date on 2018/5/5
 * Copyright:Copyright © 2017-2018
 * Company:易联众信息技术股份有限公司
 *
 *******************************************************************************/
public class ProxyFactory<T> implements InvocationHandler {


    public T getProxyInstance() {
        /**
         * 三个参数
         * 1.用哪个类的加载器去加载代理类
         * 2.动态代理类要实现的接口
         * 3.代理执行时会触发的invoke方法
         */
        return (T) Proxy.newProxyInstance(IuserDao.class.getClassLoader(), new Class[]{IuserDao.class}, this);

    }


    /**
     * @param proxy  就是代理对象，newProxyInstance方法的返回对象
     * @param method 调用的方法
     * @param args   方法中的参数
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        //如果要使用实现类的方法代理，必须要把实现类通过构造传进来
        System.out.println("开始代理");
        Object i = method.invoke(this, args);
        System.out.println("处理代理。。。");
        System.out.println("结算");

        return null;
    }
}
