package rabbitmq.route;


import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import rabbitmq.ConnectionUtil;

/**
 * 路由模式：在发布订阅模式基础上增加了routeKey
 *
 * 绑定相同routeKey的消息会进入相同队列。
 *
 */
public class Sender {
    private final  static String   EXCHANGE_NAME="testroute";
    private final  static String   ROUTE_KEY="routekey3";
    public static void main(String[] args) throws Exception{
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare(EXCHANGE_NAME,"direct");// direct 定义路由模式的交换机
        //发送消息
        channel.basicPublish(EXCHANGE_NAME,ROUTE_KEY,null,"路有消息4".getBytes());
        channel.close();
        connection.close();

    }
}
