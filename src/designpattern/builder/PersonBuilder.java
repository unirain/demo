package designpattern.builder;

/********************************************************************************
 *
 * Title: 构造人员
 *
 * Description:  尽量要放入person类中，原因是防止person类的字段变更，而builder没有变更
 *
 * @author chenlm
 * create date on 2019/1/31
 *
 *******************************************************************************/
public class PersonBuilder {
    private String name;
    private int age;
    private String sex;

    /**
     * 创建构造器
     *
     * @return
     */
    public static PersonBuilder create() {
        return new PersonBuilder();
    }

    public PersonBuilder age(int age) {
        this.age = age;
        return this;
    }

    public PersonBuilder name(String name) {
        this.name = name;
        return this;
    }

    public PersonBuilder sex(String sex) {
        this.sex = sex;
        return this;
    }

    Person build() {
        Person person = new Person();
        person.setAge(age);
        person.setName(name);
        person.setSex(sex);
        return person;
    }

}
