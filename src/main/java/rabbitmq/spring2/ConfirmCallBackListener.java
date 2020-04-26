package rabbitmq.spring2;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.stereotype.Component;

/**
 * 确认消息时回调监听
 */
@Component
public class ConfirmCallBackListener implements RabbitTemplate.ConfirmCallback {
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        System.err.println("确认回调 ack=="+ack+"  Cause== "+cause);
    }
}
