package annotation.demo;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * 定义一个可以注解在FIELD上的注解
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface MyAnTargetFiled {
    /**
     * 定义注解的一个元素 并给默认值
     */
    String value() default "我是定义在类接口<字段FIELD>上的注解元素value的默认值";

}

