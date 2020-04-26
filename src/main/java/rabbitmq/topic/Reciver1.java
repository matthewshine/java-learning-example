package rabbitmq.topic;

import com.rabbitmq.client.*;
import rabbitmq.ConnectionUtil;

import java.io.IOException;

/**
 * 消费者1
 *
 * 接收key1和key2
 */

public class Reciver1 {
    private static final  String EXCHANGE_NAME="testtopic";
    private final  static String   ROUTE_KEY1="routekey.*"; //topic模式下，可以实现模糊匹配
    private final  static String   ROUTE_KEY2="abc.#"; //topic模式下，可以实现模糊匹配
    private final static String QUEUE_NAME="testqueue1";//定义交换机名字
    public static void main(String[] args) throws  Exception{
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        //声明队列
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        //绑定队列到交换机
        // 和发布模式相比，这里我们多了一个绑定就是参数3：ROUTE_KEY。所有指定相同ROUTEKEY的消息  ，都会进入这个队列。
        channel.queueBind(QUEUE_NAME,EXCHANGE_NAME,ROUTE_KEY1);
        //如果要接收多个key
        channel.queueBind(QUEUE_NAME,EXCHANGE_NAME,ROUTE_KEY2);

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
