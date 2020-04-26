package rabbitmq.publish;

import com.rabbitmq.client.*;
import rabbitmq.ConnectionUtil;

import java.io.IOException;

/**
 * 消费者2
 *
 * 发布订阅模式必须先启动交换机
 */

public class Reciver2 {
    private final static String EXCHANGE_NAME="testexchange";//定义交换机名字
    private final static String QUEUE_NAME="testqueue2";//定义交换机名字
    public static void main(String[] args) throws  Exception{
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        //声明队列
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        //绑定队列到交换机
        channel.queueBind(QUEUE_NAME,EXCHANGE_NAME,"");

        //创建消费者
        DefaultConsumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费者"+QUEUE_NAME+"接收到消息："+new String(body));
                channel.basicAck(envelope.getDeliveryTag(),false);
            }
        };
        channel.basicConsume(QUEUE_NAME,false,consumer);

    }
}
