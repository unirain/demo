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
public class TargetWork {
    public void show(String name) {
        this.testTA(name);
        System.out.println(name);
    }

    public void testTA(String name) {
        System.out.println(name);
    }
}
