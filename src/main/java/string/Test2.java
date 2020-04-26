package string;

public class Test2 {
    public static void main(String[] args) {
        String a = new String("sss");
        String b = new String("sss");

        String c = "aaa";
        String d = "aaa";
        System.out.println(a==b);
        System.out.println(c==d);
    }
}
