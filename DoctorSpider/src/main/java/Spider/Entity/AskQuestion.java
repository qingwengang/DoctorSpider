package Spider.Entity;

import SpiderFramework.Entity.BaseSpiderEntity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.util.Date;

/**
 * Created by Administrator on 2016/5/26.
 */
@Entity
public class AskQuestion extends BaseSpiderEntity {
    private String Type;
    private String OutId;
    private Date AuthorTime;

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

}
