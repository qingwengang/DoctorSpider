package Spider.DO.Online;

import Spider.Entity.*;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by qingwengang on 2016/7/4.
 */
public class OnlineQuestionDo {
    private AskQuestion askQuestion;
    private FHQuestion fhQuestion;
    private JjQuestion jjQuestion;
    private JKWQuestion jkwQuestion;
    private SJQuestion sjQuestion;
    private String title;
    private String desc;
    private String content;
    private String needHelp;
    private List<String> answers;
    private String source;
    private String outId;

    public AskQuestion getAskQuestion() {
        return askQuestion;
    }

    public void setAskQuestion(AskQuestion askQuestion) {
        this.askQuestion = askQuestion;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public String getNeedHelp() {
        return needHelp;
    }

    public void setNeedHelp(String needHelp) {
        this.needHelp = needHelp;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getOutId() {
        return outId;
    }

    public void setOutId(String outId) {
        this.outId = outId;
    }

    public FHQuestion getFhQuestion() {
        return fhQuestion;
    }

    public void setFhQuestion(FHQuestion fhQuestion) {
        this.fhQuestion = fhQuestion;
    }

    public JjQuestion getJjQuestion() {
        return jjQuestion;
    }

    public void setJjQuestion(JjQuestion jjQuestion) {
        this.jjQuestion = jjQuestion;
    }

    public JKWQuestion getJkwQuestion() {
        return jkwQuestion;
    }

    public void setJkwQuestion(JKWQuestion jkwQuestion) {
        this.jkwQuestion = jkwQuestion;
    }

    public SJQuestion getSjQuestion() {
        return sjQuestion;
    }

    public void setSjQuestion(SJQuestion sjQuestion) {
        this.sjQuestion = sjQuestion;
    }
}
