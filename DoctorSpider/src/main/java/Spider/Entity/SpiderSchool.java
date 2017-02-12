package Spider.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

/**
 * Created by Administrator on 2017/2/12.
 */
@Entity
public class SpiderSchool {
    private long Id;
    private String Resource;
    private String Content;
    private int IfFanyi;
    private String MuluType;
    private String PageName;
    @javax.persistence.Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public String getResource() {
        return Resource;
    }

    public void setResource(String resource) {
        Resource = resource;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public int getIfFanyi() {
        return IfFanyi;
    }

    public void setIfFanyi(int ifFanyi) {
        IfFanyi = ifFanyi;
    }

    public String getMuluType() {
        return MuluType;
    }

    public void setMuluType(String muluType) {
        MuluType = muluType;
    }

    public String getPageName() {
        return PageName;
    }

    public void setPageName(String pageName) {
        PageName = pageName;
    }
}
