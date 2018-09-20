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
        targetWork.show("chenlm");
        targetWork.testTA("chenlm");


    }
}
