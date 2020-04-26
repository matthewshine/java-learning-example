package rabbitmq.persisit;

import com.rabbitmq.client.*;
import rabbitmq.ConnectionUtil;

import java.io.IOException;

public class Receiver {
    private final static  String EXCHANGE_NAME="testpersist";
    private final static  String QUEUE_NAME="persisitqueue";
    public static void main(String[] args)throws Exception {
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        //声明一个持久化交换机
        channel.exchangeDeclare(EXCHANGE_NAME,"direct",true,false,null);
         //声明持久化队列，参数2：durable=true;
        channel.queueDeclare(QUEUE_NAME,true,false,false,null);
        channel.queueBind(QUEUE_NAME,EXCHANGE_NAME,"routekey");
        DefaultConsumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("收到消息了"+new String(body));

            }
        };
        channel.basicConsume(QUEUE_NAME,true,consumer);
    }
}
