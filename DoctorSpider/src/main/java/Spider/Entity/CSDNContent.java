package Spider.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

/**
 * Created by Administrator on 2016/10/29.
 */
@Entity
public class CSDNContent {
    private long Id;
    private long MuluId;
    private String Content;

    public long getMuluId() {
        return MuluId;
    }

    public void setMuluId(long muluId) {
        MuluId = muluId;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }
    @javax.persistence.Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    public long getId() {

        return Id;
    }

    public void setId(long id) {
        Id = id;
    }
}
