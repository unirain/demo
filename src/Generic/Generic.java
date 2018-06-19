package Generic;

import sun.rmi.runtime.Log;

import java.util.List;

/********************************************************************************
 *
 * Title: 
 *
 * Description:
 *
 * author chenlm
 * create date on 2018/5/18
 * Copyright:Copyright © 2017-2018
 * Company:易联众信息技术股份有限公司
 *
 *******************************************************************************/
public class Generic<T> {
    private T name;
    private int a;
    public Generic(T name,int b){
        this.name=name;
        this.a=b;
    }

    public Generic(){

    }

    public T getName() {
        return name;
    }

    public void setName(T name) {
        this.name = name;
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }
    public void showKeyValue1(Generic<?> obj){
        System.out.println(obj);

    }
    public List<? extends  Object> findAlll(){
        return null;
    }

    public static void main(String[] args) {
//        Generic<String> gener=new Generic<String>("a",6);
//        System.out.println(gener.a);
        Generic<Integer> generic=new Generic<>(6,9);
        generic.showKeyValue1(generic);
//        String a=generic.findAll(String.class);
//        generic.findAlll()

    }

}
