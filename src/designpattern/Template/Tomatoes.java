package designpattern.Template;

/********************************************************************************
 *
 * Title: 炒西红柿
 *
 * Description:
 *
 * @author chenlm
 * create date on 2019/1/31
 *
 *******************************************************************************/
public class Tomatoes extends CookTemplate {
    @Override
    public void pourVegetable() {
        System.out.println("放西红柿");
    }

    @Override
    public void pourSauce() {
        System.out.println("放西红柿需要的调味料");
    }
}
