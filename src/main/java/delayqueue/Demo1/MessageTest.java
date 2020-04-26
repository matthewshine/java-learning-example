package delayqueue.Demo1;

import java.util.concurrent.TimeUnit;

public class MessageTest {
    public static void main(String[] args) {

        ItemQueueThread queueThread = ItemQueueThread.getInstance();
        queueThread.init();

        WxMessage wxMessage1 = new WxMessage("gaolaing","客户","这是一条测试消息,5s后执行");
        WxMessage wxMessage2 = new WxMessage("gaola1ing","gaoliang","这是一条测试消息,30s后执行");
        WxMessage wxMessage3 = new WxMessage("gaola1ing","gdddiang","这是一条测试消息,15s后执行");


        queueThread.put(3,wxMessage1, TimeUnit.SECONDS);
        queueThread.put(30,wxMessage2, TimeUnit.SECONDS);
        queueThread.put(15,wxMessage3, TimeUnit.SECONDS);

    }
}
