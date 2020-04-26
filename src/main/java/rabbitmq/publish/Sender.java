package rabbitmq.publish;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import rabbitmq.ConnectionUtil;

/**
 * 生产发布模式
 *
 * 生产者消息不是直接发送给队列，而是先发送给exchange交换机。queue和交换机绑定，一个消息会进入多个队列，最终可以被多个消费者获取。
 * 场景：一个消息需要多方接收的时候，需要用到该模式。
 *
 */
public class Sender {
    private final static String EXCHANGE_NAME="testexchange";//定义交换机名字
    public static void main(String[] args) throws Exception {
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();

        //定义交换机
        channel.exchangeDeclare(EXCHANGE_NAME,"fanout"); //fanout也就是订阅发布模式

        //消息先发送到交换机中，交换机是没有保存功能的，如果没有消费者，消息会丢失
        channel.basicPublish(EXCHANGE_NAME,"",null,"发布订阅模式的消息".getBytes());

        channel.close();
        connection.close();

    }
}
