package rabbitmq.spring2;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 可以通过监听和回调，确认消息队列的状态。
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:ApplicationContext2.xml"})
public class TestMain {

    @Autowired
    private  PublishUtil publishUtil;
    private static String exChange = "DIRECT_EX";
    private static String queue = "CONFIRM_TEST";

    /**
     * exchage、queue都正确，confirm会执行，ack=true
     * @throws Exception
     */
    @Test
    public void test1()throws Exception{
        String message ="当前时间是"+System.currentTimeMillis();
        publishUtil.send(exChange,queue,message);
        Thread.sleep(1000);
    }


    /**
     * exchage错误
     * queue都正确，confirm会执行，ack=false
     * @throws Exception
     */
    @Test
    public void test2()throws Exception{
        String message ="当前时间是"+System.currentTimeMillis();
        publishUtil.send("wrongexchange",queue,message);
        Thread.sleep(1000);
    }

    /**
     * exchage正确
     * queue错误，confirm会执行，ack=true,失败会执行
     * @throws Exception
     */
    @Test
    public void test3()throws Exception{
        String message ="当前时间是"+System.currentTimeMillis();
        publishUtil.send(exChange,"wrong",message);
        Thread.sleep(1000);
    }

    /**
     * exchage，queue都错误
     * confirm会执行，ack=false,失败不执行
     * @throws Exception
     */
    @Test
    public void test4()throws Exception{
        String message ="当前时间是"+System.currentTimeMillis();
        publishUtil.send("wrong","wrong",message);
        Thread.sleep(1000);
    }

}
