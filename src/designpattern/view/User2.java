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
public class User2 implements View {
    private String message;
    private String username;
    public User2(String username){
        this.username=username;
    }
    @Override
    public void update(String message) {
        this.message=message;
        System.out.println(username+"获得数据"+message);
    }
}
