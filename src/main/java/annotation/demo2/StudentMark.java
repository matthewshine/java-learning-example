package annotation.demo2;

/**
 * 业务逻辑：总分100分，根据各个字段规则为每人评分，评分最高的3名学生可以得到奖学金。
 *  注:这里只给出了6个字段，实际上评分项可能有几十个，如果通过if else判断代码会非常臃肿
 *
 *  1，如果选项是否决项，则直接分数为0
 *  2，如果选项是减分项则从总分中减去对应分值
 *  3，如果选项是普通项则计入总分
 *
 */
public class StudentMark {
    private  String name;
    @MarkReason(reasonName = "积极参加学校活动")
    private Double point1; //评分项1  加分项
    @MarkReason(reasonName = "挂科超过2门以上",isSubtraction = true)
    private Double point2; //评分项2  减分项
    @MarkReason(reasonName = "考试作弊行为",isFouJue = true)
    private Double point3; //评分项3  否决项（分数直接为0）
    @MarkReason(reasonName = "成绩达到优秀的课程数量超过6门")
    private Double point4; //评分项4
    @MarkReason(reasonName = "获得创业大赛前三名")
    private Double point5; //评分项5
    @MarkReason(reasonName = "1年内已经领取过奖学金",isFouJue = true)
    private Double point6; //评分项6
    @MarkReason(reasonName = "旷课行为超过2次",isSubtraction = true)
    private Double point7; //评分项6
    private String summary; //汇总

    public StudentMark(String name,Double point1, Double point2, Double point3, Double point4, Double point5, Double point6, Double point7) {
        this.name = name;
        this.point1 = point1;
        this.point2 = point2;
        this.point3 = point3;
        this.point4 = point4;
        this.point5 = point5;
        this.point6 = point6;
        this.point7 = point7;
    }

    public Double getPoint1() {
        return point1;
    }

    public void setPoint1(Double point1) {
        this.point1 = point1;
    }

    public Double getPoint2() {
        return point2;
    }

    public void setPoint2(Double point2) {
        this.point2 = point2;
    }

    public Double getPoint3() {
        return point3;
    }

    public void setPoint3(Double point3) {
        this.point3 = point3;
    }

    public Double getPoint4() {
        return point4;
    }

    public void setPoint4(Double point4) {
        this.point4 = point4;
    }

    public Double getPoint5() {
        return point5;
    }

    public void setPoint5(Double point5) {
        this.point5 = point5;
    }

    public Double getPoint6() {
        return point6;
    }

    public void setPoint6(Double point6) {
        this.point6 = point6;
    }

    public Double getPoint7() {
        return point7;
    }

    public void setPoint7(Double point7) {
        this.point7 = point7;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"name\":\"")
                .append(name).append('\"');
        sb.append(",\"point1\":")
                .append(point1);
        sb.append(",\"point2\":")
                .append(point2);
        sb.append(",\"point3\":")
                .append(point3);
        sb.append(",\"point4\":")
                .append(point4);
        sb.append(",\"point5\":")
                .append(point5);
        sb.append(",\"point6\":")
                .append(point6);
        sb.append(",\"point7\":")
                .append(point7);
        sb.append(",\"summary\":\"")
                .append(summary).append('\"');
        sb.append('}');
        return sb.toString();
    }
}
