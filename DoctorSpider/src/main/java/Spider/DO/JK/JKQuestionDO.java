package Spider.DO.JK;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Administrator on 2016/6/16.
 */
public class JKQuestionDO {
    private String title;
    private String sex;
    private String age;
    private String address;
    private String questionDes;
    private List<String> answers;
    public JKQuestionDO(){}
    public JKQuestionDO(String title, String sex, String age, String address, String questionDes, List<String> answers) {
        this.title = title;
        this.sex = sex;
        this.age = age;
        this.address = address;
        this.questionDes = questionDes;
        this.answers = answers;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getQuestionDes() {
        return questionDes;
    }

    public void setQuestionDes(String questionDes) {
        this.questionDes = questionDes;
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
