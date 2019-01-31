package designpattern.Template;

/********************************************************************************
 *
 * Title: 
 *
 * Description:
 *
 * @author chenlm
 * create date on 2019/1/31
 *
 *******************************************************************************/
public class Test {

    @org.junit.Test
    public void test1() {
        Egg egg = new Egg();
        Tomatoes tomatoes=new Tomatoes();
        egg.cookFoods();
        tomatoes.cookFoods();
    }
}
