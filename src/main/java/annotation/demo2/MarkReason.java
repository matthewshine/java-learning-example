package annotation.demo2;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 注解定义了三个属性 resonName、isSubtraction、isFouJue，被 MarkReason 注解修饰的属性可以拥有这三个属性。
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = ElementType.FIELD)
public @interface MarkReason {
    //评分项目名称
    public String reasonName();
    //是否减分项
    public boolean isSubtraction() default false;
    //是否否决项
    public boolean isFouJue() default false;
}