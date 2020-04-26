package annotation.demo1;

import java.lang.reflect.Method;

@Schedule(dayOfMonth="last")
@Schedule(dayOfWeek="Wed", hour=24)
public class Test {

    @Schedule(dayOfMonth="last")
    @Schedule(dayOfWeek="Fri", hour=23)
    public  void doPeriodicCleanup(){}

    public static void main(String[] args) throws Exception {


        Method doPeriodicCleanup = Test.class.getMethod("doPeriodicCleanup");

        Schedules schedules = doPeriodicCleanup.getAnnotation(Schedules.class);
        System.out.println("获取标记方法上的重复注解：");
        for (Schedule schedule: schedules.value()){
            System.out.println(schedule);
        }

        System.out.println("获取标记类上的重复注解：");
        if (Test.class.isAnnotationPresent(Schedules.class)){
            schedules = Test.class.getAnnotation(Schedules.class);
            for (Schedule schedule: schedules.value()){
                System.out.println(schedule);
            }
        }
    }
}
