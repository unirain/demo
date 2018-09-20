package designpattern.pattern;

/********************************************************************************
 *
 * Title: 英雄皮肤抽象
 *
 * Description:
 *
 * @author chenlm
 * create date on 2018/6/19
 *
 *******************************************************************************/
public abstract class HeroDecorator implements Hero {
    private Hero heroDecorator;


    public HeroDecorator(Hero hero) {
        this.heroDecorator = hero;
    }

    @Override
    public void init() {
        heroDecorator.init();
    }
}
