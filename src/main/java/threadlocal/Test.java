package threadlocal;

public class Test {
    static ThreadLocal <Long> threadLocal = new ThreadLocal<Long>(){
        @Override
        protected Long initialValue() {
            System.out.println("--int--");
            return  Thread.currentThread().getId(); //返回线程id
        }
    };
    public static void main(String[] args) {

        new Thread(){
            @Override
            public void run() {
                System.out.println(threadLocal.get());
            }
        }.start();

        System.out.println(threadLocal.get());


    }
}
