package Spider.Entity;

import SpiderFramework.Entity.BaseSpiderEntity;

import javax.persistence.Entity;

/**
 * Created by Administrator on 2016/6/11.
 */
@Entity
public class HdfMulu extends BaseSpiderEntity{
    private String Name;
    private String Url;
    private int Level;
    private String TagName;
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

    public int getLevel() {
        return Level;
    }

    public void setLevel(int level) {
        Level = level;
    }

    public String getTagName() {
        return TagName;
    }

    public void setTagName(String tagName) {
        TagName = tagName;
    }
}
