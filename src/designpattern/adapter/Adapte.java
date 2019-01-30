package designpattern.adapter;

/********************************************************************************
 *
 * Title: 适配器
 *
 * Description:目标接口在现有接口不满足时，适配类实现目标接口，然后通过聚合或者继承拿到源类或者源对象，然后进行匹配
 *
 * @author chenlm
 * create date on 2018/12/28
 *
 *******************************************************************************/
public class Adapte implements Target {
    private Adaptee adaptee;

    public Adapte(Adaptee adaptee) {
        this.adaptee = adaptee;
    }


    @Override
    public void power5v() {
        System.out.println("开始适配");
        this.adaptee.power();

    }


}
