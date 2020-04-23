package classload;

/********************************************************************************
 *
 * Title: 
 *
 * Description:
 *
 * @author chenlm
 * create date on 2019/4/10
 *
 *******************************************************************************/
public class Test {

    @org.junit.Test
    public void test1()throws Exception{
        ClassLoader classLoader=this.getClass().getClassLoader();
        //sun.misc.Launcher$AppClassLoader@18b4aac2
        System.out.println(classLoader);
        //sun.misc.Launcher$ExtClassLoader@7adf9f5f
        System.out.println(classLoader.getParent());
        //为空提到Bootstrap Loader是用C++语言写的，依java的观点来看，
        //逻辑上并不存在Bootstrap Loader的类实体，所以在java程序代码里试图打印出其内容时，我们就会看到输出为null。
        System.out.println(classLoader.getParent().getParent());
    }
}
