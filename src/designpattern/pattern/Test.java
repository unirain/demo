package designpattern.pattern;

/********************************************************************************
 *
 * Title: 
 *
 * Description:
 *
 * @author chenlm
 * create date on 2018/6/19
 *
 *******************************************************************************/
public class Test {
    public static void main(String[] args) {
        Hero hero=new Ez();
        Vitor vitor=new Vitor();
        HeroDecorator decorator=new FutureSoldier(hero);
        decorator.init();
        HeroDecorator videcorator=new FutureSoldier(vitor);
        videcorator.init();
    }
}
