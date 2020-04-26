package finalobj;

/**
 * final修饰的变量必须被初始化
 */

public class Person {
    /*名字（必须）*/
    private final String name;
    /*性别（必须）*/
    private final String gender;
    /*年龄（非必须）*/
    private final String age;
    /*鞋子（非必须）*/
    private final String shoes;
    /*衣服（非必须）*/
    private final String clothes;
    /*钱（非必须）*/
    private final String money;
    /*房子（非必须）*/
    private final String house;
    /*汽车（非必须）*/
    private final String car;
    /*职业（非必须）*/
    private final String career;

    public Person(Builder builder){
        this.name = builder.name;
        this.gender = builder.gender;
        this.age = builder.age;
        this.shoes = builder.shoes;
        this.clothes = builder.clothes;
        this.money = builder.money;
        this.house = builder.house;
        this.car = builder.car;
        this.career = builder.career;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"name\":\"")
                .append(name).append('\"');
        sb.append(",\"gender\":\"")
                .append(gender).append('\"');
        sb.append(",\"age\":\"")
                .append(age).append('\"');
        sb.append(",\"shoes\":\"")
                .append(shoes).append('\"');
        sb.append(",\"clothes\":\"")
                .append(clothes).append('\"');
        sb.append(",\"money\":\"")
                .append(money).append('\"');
        sb.append(",\"house\":\"")
                .append(house).append('\"');
        sb.append(",\"car\":\"")
                .append(car).append('\"');
        sb.append(",\"career\":\"")
                .append(career).append('\"');
        sb.append('}');
        return sb.toString();
    }

    public  static  class  Builder{
        /*名字（必须）*/
        private final String name;
        /*性别（必须）*/
        private final String gender;
        /*年龄（非必须）*/
        private  String age;
        /*鞋子（非必须）*/
        private  String shoes;
        /*衣服（非必须）*/
        private  String clothes;
        /*钱（非必须）*/
        private  String money;
        /*房子（非必须）*/
        private  String house;
        /*汽车（非必须）*/
        private  String car;
        /*职业（非必须）*/
        private  String career;

        public Builder(String name, String gender) {
            this.name = name;
            this.gender = gender;
        }

        public Builder setClothes(String clothes) {
            this.clothes = clothes;
            return  this;
        }

        public Builder setMoney(String money) {
            this.money = money;
            return  this;
        }

        public Builder setHouse(String house) {
            this.house = house;
            return  this;
        }

        public Builder setCar(String car) {
            this.car = car;
            return  this;
        }

        public Builder setCareer(String career) {
            this.career = career;
            return  this;
        }

        public Person build(){
            return new Person(this);
        }
    }

}