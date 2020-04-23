package designpattern.adapter;

import java.util.ArrayList;
import java.util.List;

/********************************************************************************
 *
 * Title: 
 *
 * Description:
 *
 * @author chenlm
 * create date on 2018/12/28
 *
 *******************************************************************************/
public class Test {

    @org.junit.Test
    public void test1() throws Exception {
        Adaptee adaptee = new Adaptee();
        Target target = new Adapte(adaptee);
        target.power5v();

    }
}
