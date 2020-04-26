package annotation.demo;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * 测试注解四个不同作用域的注解类
 */
@MyAnTargetType
public class AnnotationTest {

    @MyAnTargetFiled
    private String field = "我是字段";

    @MyAnTargetMethod("测试方法")
    public void test(@MyAnTargetParameter String args) {
        System.out.println("参数值===" + args);
    }

    public static void main(String[] args) {
        // 获取类上的注解MyAnTargetType
        MyAnTargetType t = AnnotationTest.class.getAnnotation(MyAnTargetType.class);
        System.out.println("类上的注解值===" + t.value());

        MyAnTargetMethod tm = null;
        Method method = null;
        try {
            // 根据反射获取AnnotationTest类上的test方法
            method = AnnotationTest.class.getDeclaredMethod("test", String.class);
            // 获取方法上的注解MyAnTargetMethod
            tm = method.getAnnotation(MyAnTargetMethod.class);
            System.out.println("方法上的注解值===" + tm.value());

            Annotation[][] annotations = method.getParameterAnnotations();
            for (Annotation[] tt : annotations) {
                for (Annotation t1 : tt) {
                    if (t1 instanceof MyAnTargetParameter) {
                        System.out.println("参数上的注解值===" + ((MyAnTargetParameter) t1).value());
                    }
                }
            }

            method.invoke(new AnnotationTest(), "改变默认的参数");
            // 获取AnnotationTest类上字段field的注解MyAnTargetField
            MyAnTargetFiled filedan = AnnotationTest.class.getDeclaredField("field").getAnnotation(MyAnTargetFiled.class);
            System.out.println("字段上的注解值===" + filedan);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
