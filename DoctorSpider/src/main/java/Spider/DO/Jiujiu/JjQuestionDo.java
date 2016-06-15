package Spider.DO.Jiujiu;

import Spider.Entity.JjQuestion;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Administrator on 2016/6/15.
 */
public class JjQuestionDo {
    private String typeName;
    private String title;
    private String desc;
    private List<String> answers;
    private String outId;
    public JjQuestionDo(){}
    public JjQuestionDo(String typeName, String title, String desc,String outId, List<String> answers) {
        this.typeName = typeName;
        this.title = title;
        this.desc = desc;
        this.answers = answers;
        this.outId=outId;
    }

    public String getOutId() {
        return outId;
    }

    public void setOutId(String outId) {
        this.outId = outId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public List<String> getAnswers() {
        if(answers==null){
            answers=new LinkedList<>();
        }
        return answers;
    }

    public void setAnswers(List<String> answers) {
        this.answers = answers;
    }
}
