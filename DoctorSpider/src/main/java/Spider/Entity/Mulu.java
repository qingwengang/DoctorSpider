package Spider.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

/**
 * Created by Administrator on 2016/5/6.
 */
@Entity
public class Mulu {
    private int Id;
    private String Name;
    private String Url;
    private String Source;
    private int SpiderFlag;
    private int Level;
    private int ParentId;
    private String Type;
    private long AskId;
    @javax.persistence.Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }

    public String getSource() {
        return Source;
    }

    public void setSource(String source) {
        Source = source;
    }

    public int getSpiderFlag() {
        return SpiderFlag;
    }

    public void setSpiderFlag(int spiderFlag) {
        SpiderFlag = spiderFlag;
    }

    public int getLevel() {
        return Level;
    }

    public void setLevel(int level) {
        Level = level;
    }

    public int getParentId() {
        return ParentId;
    }

    public void setParentId(int parentId) {
        ParentId = parentId;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public long getAskId() {
        return AskId;
    }

    public void setAskId(long askId) {
        AskId = askId;
    }
}
