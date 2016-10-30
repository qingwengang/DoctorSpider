package Spider.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

/**
 * Created by qingwengang on 2016/7/7.
 */
@Entity
public class SchoolContent {
    private long Id;
    private long MuluId;
    private String OutContent;
    private String Content;
    private String Titles;
    @javax.persistence.Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public long getMuluId() {
        return MuluId;
    }

    public void setMuluId(long muluId) {
        MuluId = muluId;
    }

    public String getOutContent() {
        return OutContent;
    }

    public void setOutContent(String outContent) {
        OutContent = outContent;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public String getTitles() {
        return Titles;
    }

    public void setTitles(String titles) {
        Titles = titles;
    }
}
