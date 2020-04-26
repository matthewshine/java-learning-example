package delayqueue.Demo1;
public class WxMessage {
    private String fromname;
    private String toName;
    private String Content;

    public WxMessage(String fromname, String toName, String content) {
        this.fromname = fromname;
        this.toName = toName;
        Content = content;
    }

    public String getFromname() {
        return fromname;
    }

    public void setFromname(String fromname) {
        this.fromname = fromname;
    }

    public String getToName() {
        return toName;
    }

    public void setToName(String toName) {
        this.toName = toName;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }


}
