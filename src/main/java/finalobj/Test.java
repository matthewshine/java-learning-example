package finalobj;

public class Test {
    public static void main(String[] args) {

        Person person = new Person.Builder("zhangsan", "1").setCar("car").setCareer("carrer").setHouse("housre").build();
        System.out.println(person.toString());
    }
}
