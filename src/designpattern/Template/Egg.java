package designpattern.Template;

/********************************************************************************
 *
 * Title: 煎蛋
 *
 * Description:
 *
 * @author chenlm
 * create date on 2019/1/31
 *
 *******************************************************************************/
public class Egg extends CookTemplate {
    @Override
    public void pourVegetable() {
        System.out.println("放入搅拌好的鸡蛋");
    }

    @Override
    public void pourSauce() {
        System.out.println("放入煎蛋需要的调味料");
    }
}
