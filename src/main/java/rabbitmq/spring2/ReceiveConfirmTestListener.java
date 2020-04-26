package rabbitmq.spring2;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ReceiveConfirmTestListener implements ChannelAwareMessageListener {
    /**
     * 收到消息时执行的监听
     * @param message
     * @param channel
     * @throws Exception
     */
    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        System.err.println("消费者收到了消息"+message);
        try {
            channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);//false确认收到消息
        } catch (IOException e) {
            e.printStackTrace();
            //异常时处理。
        }
    }
}
