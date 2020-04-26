package rabbitmq.topic;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import rabbitmq.ConnectionUtil;

/**
 * topic模式和route模式的本质区别就在于，topic支持routekey的模糊匹配
 *
 * ”*“ 匹配单个元素 "#"匹配多个元素
 * 测试：routekey.1.2 是，消费者1接收不到。routekey.1两者都可以收到
 *
 */
public class Sender {
    private static final  String EXCHANGE_NAME="testtopic";

    public static void main(String[] args) throws Exception{
        Connection connection = ConnectionUtil.getConnection();
        Channel channel= connection.createChannel();
        channel.exchangeDeclare(EXCHANGE_NAME,"topic"); //指定交换机为topic模式

        channel.basicPublish(EXCHANGE_NAME,"routekey.1.2",null,"topic消息".getBytes());
        channel.close();
        connection.close();

    }
}
