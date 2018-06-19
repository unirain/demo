package designpattern.view;

/********************************************************************************
 *
 * Title: 
 *
 * Description:
 *
 * author chenlm
 * create date on 2018/5/5
 * Copyright:Copyright © 2017-2018
 * Company:易联众信息技术股份有限公司
 *
 *******************************************************************************/
public class test {
    public static void main(String[] args) {
        Subject subject = new SubjectImpl();
        View one = new User1("chenlm");
        View two = new User2("lsq");
        subject.addView(one);
        subject.addView(two);
        subject.doWord("开始测试下离开");

    }
}
