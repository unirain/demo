package designpattern.view;

import java.util.LinkedList;
import java.util.List;

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
public class SubjectImpl implements Subject {
    private List<View> list=new LinkedList<>();
    @Override
    public void addView(View view) {
        list.add(view);

    }

    @Override
    public void doWord(String message) {
        for (View view : list) {
            view.update(message);
        }

    }

    @Override
    public void deleteView(View view) {
        list.remove(view);

    }

}
