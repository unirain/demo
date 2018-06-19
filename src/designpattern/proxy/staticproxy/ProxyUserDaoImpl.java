package designpattern.proxy.staticproxy;

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
public class ProxyUserDaoImpl implements IuserDao {
    private IuserDao iuserDao;
    public ProxyUserDaoImpl(IuserDao iuserDao){
        this.iuserDao=iuserDao;
    }
    @Override
    public void save() {
        System.out.println("保存之前....");
        iuserDao.save();
        System.out.println("保存之后......");
    }
}
