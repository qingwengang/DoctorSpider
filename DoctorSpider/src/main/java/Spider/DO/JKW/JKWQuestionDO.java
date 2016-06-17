package Spider.DO.JKW;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/6/17.
 */
public class JKWQuestionDO {
    private String title;
    private String typeName;
    private String desc;
    private String sexage;
    private List<String> answers;
    public JKWQuestionDO(){}
    public JKWQuestionDO(String title, String typeName, String desc, String sexage, List<String> answers) {
        this.title = title;
        this.typeName = typeName;
        this.desc = desc;
        this.sexage = sexage;
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

    public String getSexage() {
        return sexage;
    }

    public void setSexage(String sexage) {
        this.sexage = sexage;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public void setAnswers(List<String> answers) {
        this.answers = answers;
    }
}
