package Spider.DO;

import java.util.Date;

/**
 * Created by Administrator on 2016/5/26.
 */
public class AnswerDo {
    private String DoctorId;
    private String Content;
    private Date AnswerTime;

    public String getDoctorId() {
        return DoctorId;
    }

    public void setDoctorId(String doctorId) {
        DoctorId = doctorId;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public Date getAnswerTime() {
        return AnswerTime;
    }

    public void setAnswerTime(Date answerTime) {
        AnswerTime = answerTime;
    }
}
