package rabbitmq.persisit;


import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.MessageProperties;
import rabbitmq.ConnectionUtil;

import java.util.Date;

/**
 * rabbitmq消息持久化的要点: *
 * -sender:定义持久化的exchange和message
 * -receiver:定义持久化的exchage和queue
 * 以上可以保证消息产生和传播过程中落盘，即使服务器宕机也可以保证消息不丢失。
 *
 * 测试：先点击sender发送消息 --> 关闭rabbitmq服务 --> 点击receiver --> 验证可以接收到宕机前消息，并且时间一致。
 *
 *
 */

public class Sender {
    private final static  String EXCHANGE_NAME="testpersist";

    public static void main(String[] args) throws Exception{
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
       //声明持久化的交换机(durable=true代表交换机持久化)
        channel.exchangeDeclare(EXCHANGE_NAME,"direct",true,false,null);
        //声明持久化消息（PERSISTENT_TEXT_PLAIN持久化的消息）
        channel.basicPublish(EXCHANGE_NAME,"routekey",
                MessageProperties.PERSISTENT_TEXT_PLAIN,("持久化消息"+new Date()).getBytes());
        channel.close();
        connection.close();
    }
}
