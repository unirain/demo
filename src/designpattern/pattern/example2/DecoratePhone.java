package designpattern.pattern.example2;

/********************************************************************************
 *
 * Title: 抽象装饰类
 *
 * Description:
 *
 * @author chenlm
 * create date on 2018/12/29
 *
 *******************************************************************************/
public abstract class DecoratePhone  implements  Call{
    private Call call;
    public DecoratePhone(Call call){
        this.call=call;
    }
    @Override
    public void tell() {

       call.tell();
    }
}
