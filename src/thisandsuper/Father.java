package thisandsuper;

/********************************************************************************
 *
 * Title: 父类
 *
 * Description:
 *
 * @author chenlm
 * create date on 2018/6/19
 *
 *******************************************************************************/
public abstract class Father {
    protected  String username="父类";
    protected  void setUsername(String username){
        this.username=username;
    }
    protected void show(){
        System.out.println(this.username);

    }

    public String getUsername() {
        return username;
    }
}
