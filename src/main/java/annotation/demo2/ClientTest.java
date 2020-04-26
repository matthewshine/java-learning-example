package annotation.demo2;

public class ClientTest {
    public static void main(String[] args) {
        StudentMark stu1 = new StudentMark("张三",33d,33d,33d,223d,31d,21d,32d);
        StudentMark stu2 = new StudentMark("张三",33d,33d,32d,223d,31d,21d,32d);
        try {
            AnnotationUtil.setMarkReasons(stu2);
            AnnotationUtil.setMarkReasons(stu1);
            System.out.println(stu1.toString());
            System.out.println(stu2.toString());
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
