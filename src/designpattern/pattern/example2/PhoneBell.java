package designpattern.pattern.example2;

/********************************************************************************
 *
 * Title: 手机壳
 *
 * Description:
 *
 * @author chenlm
 * create date on 2018/12/29
 *
 *******************************************************************************/
public class PhoneBell extends DecoratePhone {

    public PhoneBell(Call call) {
        super(call);
    }

    @Override
    public void tell() {
        super.tell();
        System.out.println("我是手机壳");
    }
}
