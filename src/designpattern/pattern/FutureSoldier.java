package designpattern.pattern;

/********************************************************************************
 *
 * Title: 未来战士皮肤
 *
 * Description: 拿到实例，想干嘛就干嘛，，在前后加工能，这有点像代理了
 *
 * @author chenlm
 * create date on 2018/6/19
 *
 *******************************************************************************/
public class FutureSoldier extends HeroDecorator{
    public FutureSoldier(Hero heroDecortor){
        super(heroDecortor);
    }

    @Override
    public void init() {
        super.init();
        setSkin();
    }
    private void setSkin() {
        System.out.println("皮肤:未来战士！");
    }
}
