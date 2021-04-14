package thistest;

/********************************************************************************
 *
 * Title: 
 *
 * Description:
 *
 * @author chenlm
 * create date on 2021/4/13 0013
 *
 *******************************************************************************/
public  class Father {

    protected String name=this.getClass().getName();


    public void fashow(){
        System.out.println(name);
    }
}
