package designpattern.adapter;

import java.util.ArrayList;
import java.util.List;

/********************************************************************************
 *
 * Title: 
 *
 * Description:
 *
 * @author chenlm
 * create date on 2018/12/28
 *
 *******************************************************************************/
public class Test {

    @org.junit.Test
    public void test1() throws Exception {
        Adaptee adaptee = new Adaptee();
        Target target = new Adapte(adaptee);
        target.power5v();

    }
    @org.junit.Test
    public void test2() throws Exception {
        List<Integer> a=new ArrayList<>();
        for(int  i=1;i<11;i++){
            a.add(i);
        }
        List<List<Integer>> te=averageAssign(a,3);
        System.out.println(te.size());


    }
    public static <T> List<List<T>> averageAssign(List<T> source, int len) {
        if (source==null||source.isEmpty()){
            throw new RuntimeException("集合错误！");
        }
        List<List<T>> result = new ArrayList<List<T>>();
        if (source.size()<=len){
            result.add(source);
            return result;
        }
        int size = source.size();
        int count = (size + len - 1) / len;


        for (int i = 0; i < count; i++) {
            List<T> subList = source.subList(i * len, ((i + 1) * len > size ? size : len * (i + 1)));
            result.add(subList);
        }
        return result;
    }

}
