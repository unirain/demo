package designpattern.proxy.jdkproxy;

import designpattern.proxy.staticproxy.IuserDao;
import designpattern.proxy.staticproxy.IuserDaoImpl;

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

    public T getProxyInstance(){
        return (T)Proxy.newProxyInstance(IuserDao.class.getClassLoader(), new Class[]{IuserDao.class},this);

    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        System.out.println("开始代理");
        Object i=method.invoke(this,args);
        System.out.println("结算");

        return i;
    }
}
