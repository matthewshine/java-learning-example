package annotation.demo2;

import java.lang.reflect.Field;

public class AnnotationUtil {

    public static void setMarkReasons(StudentMark bean) throws NoSuchFieldException, IllegalAccessException {

        //获取到目标对象所有的字段
        Field []fields =  bean.getClass().getDeclaredFields();

        double score=100;
        String isSubtraction="";
        boolean isFouJue =false;
        StringBuilder sbReason = new StringBuilder();
        for(Field field:fields){
            //判断字段上是否标注了MarkReason自定义注解
            if(field.isAnnotationPresent(MarkReason.class)  ){
                    field.setAccessible(true); //允许访问私有属性

                //获取字段上注解类型,并且计算分值
                MarkReason markReason = field.getAnnotation(MarkReason.class);
                double fieldvalue = (double)field.get(bean);
                if(markReason.isFouJue()){
                    score=0;
                    isFouJue=true;
                }else if(markReason.isSubtraction()){
                    score-=fieldvalue;
                    isSubtraction="-";
                }else {
                    score+=fieldvalue;
                    isSubtraction="+";
                }
                //拼接统计项
                sbReason.append(markReason.reasonName()+":"+isSubtraction+fieldvalue);
            }

        }
        score=isFouJue?0:score;
        sbReason.append("总分:"+score);

        Field targetField = bean.getClass().getDeclaredField("summary");
        targetField.setAccessible(true);
        targetField.set(bean,sbReason.toString());//设置summary字段的值
    }
}
