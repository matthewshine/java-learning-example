package rabbitmq.worker;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import rabbitmq.ConnectionUtil;

/**
 * work模式：
 * 生产者1，队列向两个消费者分发数据。
 *
 * 测试：运行过程中停止消费者1，队列自动转入消费者2，没有丢失。
 */
public class Sender {

    private final  static String QUEUE="testworker"; //队列名字

    public static void main(String[]args) throws Exception {
        //1.0 获取连接
        Connection conn = ConnectionUtil.getConnection();
        //2.0 创建通道
        Channel channel = conn.createChannel();
        //3.0 声明队列。如果队列存在则do nothing
        //参数1：队列名字，
        //参数2：是否持久化队列（默认在内存中，mq重启后消失），设置成true可以保存到erlang自带数据库，重启后重新读入数据。
        //参数3：是否排外。1当连接关闭后是否会自动删除队列。2是否私有当前队列。如果设置true，其他通道不可访问。只适用于一个消费者场景。
        //参数4：是否自动删除。
        //参数5：其他参数
        channel.queueDeclare(QUEUE,false,false,false,null);
        //4.0 发送内容
        for(int i=0;i<10000;i++){
            channel.basicPublish("",QUEUE,null,("发送消息"+i).getBytes());
            Thread.sleep(200);
        }
        //4.0 关闭连接
        channel.close();
        conn.close();
    }
}
