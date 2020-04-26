package rabbitmq.spring2;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class ReturnCallBackListener implements RabbitTemplate.ReturnCallback {

    /**
     * 失败时监听
     * @param message
     * @param replycode
     * @param replytext
     * @param exchange
     * @param routeKey
     */
    @Override
    public void returnedMessage(Message message, int replycode , String replytext, String exchange, String routeKey) {

        System.err.println("失败监听"+message+"replycode="+replycode+"replytext="+replytext+
                "exchange="+exchange+"routeKey="+routeKey);
    }
}
