package rabbitmq.spring;

public class MyConsumer {
    public void test(String message){
        System.err.println("消费者获取消息："+message);
    }
}
