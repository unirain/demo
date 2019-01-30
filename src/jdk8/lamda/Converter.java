package jdk8.lamda;

/********************************************************************************
 *
 * Title: 
 *
 * Description:
 *
 * @author chenlm
 * create date on 2018/8/17
 *
 *******************************************************************************/
@FunctionalInterface
public interface Converter<F,T> {
    T convert(F from);

    /**
     * jdk8支持定义默认非抽象方法
     * @param a
     * @param b
     * @return
     */
    default int sort(int a,int b){
        return a*b;
    }

}
