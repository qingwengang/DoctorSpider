package Spider.Entity;

import SpiderFramework.Entity.BaseSpiderEntity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

/**
 * Created by Administrator on 2016/5/6.
 */
@Entity
public class Mulu extends BaseSpiderEntity {

    private String Name;
    private String Url;
    private String Source;

    private int Level;
    private long ParentId;
    private String Type;
    private long AskId;


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


    public int getLevel() {
        return Level;
    }

    public void setLevel(int level) {
        Level = level;
    }

    public long getParentId() {
        return ParentId;
    }

    public void setParentId(long parentId) {
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

    public Mulu() {
    }

    public Mulu(String name, String url, String source, int level, long parentId, String type, long askId) {
        Name = name;
        Url = url;
        Source = source;
        Level = level;
        ParentId = parentId;
        Type = type;
        AskId = askId;
    }
}
