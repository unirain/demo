package designpattern.proxy.dynamicProxy;

/********************************************************************************
 *
 * Title: 
 *
 * Description:
 *
 * @author chenlm
 * create date on 2018/9/20
 *
 *******************************************************************************/
public class Test {
    public static void main(String[] args) {
        DynamicProxyTest dynamicProxyTest=new DynamicProxyTest();
        TargetWork targetWork=dynamicProxyTest.createProxy();
//        targetWork.testTA("chenlm");
        targetWork.show("chenlm");


    }

    @org.junit.Test
    public  void test1()throws Exception{
        DynamicProxyTestEasy dynamicProxyTestEasy=new DynamicProxyTestEasy();
        TargetWork targetWork=dynamicProxyTestEasy.createProxy();
        targetWork.show("dd");

    }
}
