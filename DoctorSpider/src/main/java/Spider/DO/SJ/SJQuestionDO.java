package Spider.DO.SJ;

import java.util.List;

/**
 * Created by Administrator on 2016/6/17.
 */
public class SJQuestionDO {
    private String title;
    private String typeName;
    private String desc;
    private List<String> answers;
    public SJQuestionDO(){}
    public SJQuestionDO(String title, String typeName, String desc, List<String> answers) {
        this.title = title;
        this.typeName = typeName;
        this.desc = desc;
        this.answers = answers;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public void setAnswers(List<String> answers) {
        this.answers = answers;
    }
}
