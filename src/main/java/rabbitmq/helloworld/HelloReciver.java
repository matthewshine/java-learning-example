package rabbitmq.helloworld;//package rabbitmq.helloworld;
//
//import com.rabbitmq.client.*;
//import rabbitmq.ConnectionUtil;
//public class HelloReciver {
//    private final  static String QUEUE="testhello"; //队列名字
//
//    public static void main(String[] args) throws Exception {
//        //1.0 获取连接
//        Connection conn = ConnectionUtil.getConnection();
//        //2.0 创建通道
//        Channel channel = conn.createChannel();
//        //3.0 声明队列。如果队列存在则do nothing
//        channel.queueDeclare(QUEUE,false,false,false,null);
//        //4.0 接收消息
//        DefaultConsumer consumer = new DefaultConsumer(channel);//创建消费者
////        channel.basicConsume(QUEUE,true,consumer);
//        //5.0 获取消息
//        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
//
//        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
//            String message = new String(delivery.getBody(), "UTF-8");
//            System.out.println(" [x] Received '" + message + "'");
//        };
//        channel.basicConsume(QUEUE, true, deliverCallback, consumerTag -> { }); //参数2：true 自动确认收到消息。
//        //如果不确认，服务器会标记该消息为不可用，一直等待消费者反馈。
//    }
//}
