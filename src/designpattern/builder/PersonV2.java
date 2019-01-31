package designpattern.builder;

/********************************************************************************
 *
 * Title: builder模式第二版
 *
 * Description: 创建则的字段和创建源一起，防止维护时冲突
 *
 * @author chenlm
 * create date on 2019/1/31
 *
 *******************************************************************************/
public class PersonV2 {
    private final String name;
    private final int age;
    private final String sex;

    public PersonV2(Builder builder) {
        this.name = builder.name;
        this.age = builder.age;
        this.sex = builder.sex;
    }

    /**
     * 主动构造嵌套类
     *
     * @return
     */
    public static Builder create() {
        return new Builder();
    }

    public static class Builder {
        private String name;
        private int age;
        private String sex;

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder sex(String sex) {
            this.sex = sex;
            return this;
        }

        public Builder age(int age) {
            this.age = age;
            return this;
        }

        public PersonV2 build() {
            return new PersonV2(this);
        }
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("PersonV2{");
        sb.append("name='").append(name).append('\'');
        sb.append(", age=").append(age);
        sb.append(", sex=").append(sex);
        sb.append('}');
        return sb.toString();
    }
}
