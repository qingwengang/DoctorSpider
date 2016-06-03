package Spider.DO;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Administrator on 2016/5/26.
 */
public class QuestionDo {
    private long Id;
    private String Type;
    private String OutId;
    private Date AuthorTime;
    private String AuthorId;
    private String Title;
    private String Content;
    private String Description;
    private String NeedHelp;
    private String AuthSex;
    private String Laiyuan;
    private List<AnswerDo> answers;
    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getOutId() {
        return OutId;
    }

    public void setOutId(String outId) {
        OutId = outId;
    }

    public Date getAuthorTime() {
        return AuthorTime;
    }

    public void setAuthorTime(Date authorTime) {
        AuthorTime = authorTime;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getNeedHelp() {
        return NeedHelp;
    }

    public void setNeedHelp(String needHelp) {
        NeedHelp = needHelp;
    }

    public String getAuthSex() {
        return AuthSex;
    }

    public void setAuthSex(String authSex) {
        AuthSex = authSex;
    }

    public String getLaiyuan() {
        return Laiyuan;
    }

    public void setLaiyuan(String laiyuan) {
        Laiyuan = laiyuan;
    }

    public List<AnswerDo> getAnswers() {
        if(answers==null){
            answers=new LinkedList<AnswerDo>();
        }
        return answers;
    }

    public void setAnswers(List<AnswerDo> answers) {
        this.answers = answers;
    }

    public String getAuthorId() {
        return AuthorId;
    }

    public void setAuthorId(String authorId) {
        AuthorId = authorId;
    }
}
