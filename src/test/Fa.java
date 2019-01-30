package test;

/********************************************************************************
 *
 * Title: 
 *
 * Description:
 *
 * @author chenlm
 * create date on 2018/9/17
 *
 *******************************************************************************/
public abstract class Fa {
    protected String name;
    public void setName(String name){
        this.name=name;
    }
    public void show(){
        System.out.println(this.name);
    }
}
