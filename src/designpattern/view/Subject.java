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
public interface Subject {
    void addView(View view);
    void doWord(String message);
    void deleteView(View view);

}
