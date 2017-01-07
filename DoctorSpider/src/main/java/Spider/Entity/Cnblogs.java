package Spider.Entity;

import SpiderFramework.Entity.BaseSpiderEntity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

/**
 * Created by Administrator on 2017/1/6.
 */
@Entity
public class Cnblogs {
    private long Id;
    private String Name;
    private String Content;
    private String Url;
    private int SpiderFlag;
    private int IsPush;
    private String Type;
    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }
    @javax.persistence.Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public int getSpiderFlag() {
        return SpiderFlag;
    }

    public void setSpiderFlag(int spiderFlag) {
        SpiderFlag = spiderFlag;
    }

    public int getIsPush() {
        return IsPush;
    }

    public void setIsPush(int isPush) {
        IsPush = isPush;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }
}
