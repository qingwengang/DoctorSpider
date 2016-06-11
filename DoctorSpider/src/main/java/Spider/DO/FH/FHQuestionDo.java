package Spider.DO.FH;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Administrator on 2016/6/10.
 */
public class FHQuestionDo {
    private long outId;
    private String title;
    private String questionInfo;
    private String desc;
    private List<String> answers;

    public long getOutId() {
        return outId;
    }

    public void setOutId(long outId) {
        this.outId = outId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getQuestionInfo() {
        return questionInfo;
    }

    public void setQuestionInfo(String questionInfo) {
        this.questionInfo = questionInfo;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public List<String> getAnswers() {
        if(answers==null){
            answers=new LinkedList<String>();
        }
        return answers;
    }

    public void setAnswers(List<String> answers) {
        this.answers = answers;
    }
}
