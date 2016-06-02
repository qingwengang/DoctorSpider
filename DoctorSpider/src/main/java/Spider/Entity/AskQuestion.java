package Spider.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.util.Date;

/**
 * Created by Administrator on 2016/5/26.
 */
@Entity
public class AskQuestion {
    private long Id;
    private String Type;
    private String OutId;
    private Date AuthorTime;
    private int SpiderFlag;
    @javax.persistence.Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
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

    public int getSpiderFlag() {
        return SpiderFlag;
    }

    public void setSpiderFlag(int spiderFlag) {
        SpiderFlag = spiderFlag;
    }
}
