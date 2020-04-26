package rabbitmq;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class ConnectionUtil {
    public static Connection getConnection() throws Exception{
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("10.10.10.86");
        connectionFactory.setPort(5673);
        connectionFactory.setUsername("rabbitadmin");
        connectionFactory.setPassword("123456");
        connectionFactory.setVirtualHost("/");
        return  connectionFactory.newConnection(); //创建一个新的连接
    }
}
