package rabbitmq.spring2;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PublishUtil {
    @Autowired
    private AmqpTemplate amqpTemplate;
    public void send(String exchage,String routingKey,Object message){

        amqpTemplate.convertAndSend(exchage,routingKey,message);

    }
}
