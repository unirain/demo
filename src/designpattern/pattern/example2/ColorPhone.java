package designpattern.pattern.example2;

/********************************************************************************
 *
 * Title: 彩色
 *
 * Description:
 *
 * @author chenlm
 * create date on 2018/12/29
 *
 *******************************************************************************/
public class ColorPhone extends DecoratePhone {

    public ColorPhone(Call call) {
        super(call);
    }

    @Override
    public void tell() {
        super.tell();
        System.out.println("我是彩色的");
    }
}
