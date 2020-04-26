package rabbitmq.spring;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringTest {
    public static void main(String[] args) throws  Exception{
        ApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("classpath:ApplicationContext.xml");

        RabbitTemplate rabbitTemplate = applicationContext.getBean(RabbitTemplate.class);

        rabbitTemplate.convertAndSend("Spring的消息");
        ((ClassPathXmlApplicationContext) applicationContext).destroy();//销毁
    }
}
