package rabbitmq.worker;

import com.rabbitmq.client.*;
import rabbitmq.ConnectionUtil;

import java.io.IOException;

/**
 * 消费者2
 */

public class Reciver2 {
    private final  static String QUEUE="testworker"; //队列名字
    public static void main(String[] args) throws  Exception{
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE,false,false,false,null);

        //创建consumer(自动循环监听)
        DefaultConsumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
//                super.handleDelivery(consumerTag, envelope, properties, body);
                //收到消息后对消息处理
                System.out.println("消费者2收到的内容是："+new String(body));
                //确认收到消息
                channel.basicAck(envelope.getDeliveryTag(),false); //false 确认接收到消息。true：拒绝接收消息。
            }
        };
        //注册消费者。参数2：手动确认，代表收到消息后需要收到告诉服务器，我们收到消息了。
        channel.basicConsume(QUEUE,false,consumer);
    }
}
