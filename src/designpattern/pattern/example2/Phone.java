package designpattern.pattern.example2;

/********************************************************************************
 *
 * Title: 手机
 *
 * Description: 具体组件，将要被装饰
 *
 * @author chenlm
 * create date on 2018/12/29
 *
 *******************************************************************************/
public class Phone implements Call{
    @Override
    public void tell() {
        System.out.println("我是手机");
    }
}
