package designpattern.proxy.jdkproxy;

import designpattern.proxy.staticproxy.IuserDao;
import designpattern.proxy.staticproxy.IuserDaoImpl;
import org.junit.Test;

/********************************************************************************
 *
 * Title: 
 *
 * Description:
 *
 * author chenlm
 * create date on 2018/5/5
 * Copyright:Copyright © 2017-2018
 * Company:易联众信息技术股份有限公司
 *
 *******************************************************************************/
public class test {
    public static void main(String[] args) {
        IuserDao proxy= new ProxyFactory<IuserDao>().getProxyInstance();
        proxy.save();
    }

    @Test
    public void test(){
        IuserDao proxy=new ProxyFactoryNew<IuserDao>(new IuserDaoImpl()).getProxyInstance();
        proxy.save();

    }
}
