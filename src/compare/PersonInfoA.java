package compare;

/********************************************************************************
 *
 * Title: 
 *
 * Description:
 *
 * @author chenlm
 * create date on 2020/3/13
 *
 *******************************************************************************/
public class PersonInfoA {
    private String name;
    private int age;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    /**
     * 内比较器的做法 ，类必须实现Comparable接口
     */
//    @Override
//    public int compareTo(PersonInfoA personInfoA) {
//        //比较者大时返回正整数
//        return personInfoA.getAge()-this.age;
//    }
}
