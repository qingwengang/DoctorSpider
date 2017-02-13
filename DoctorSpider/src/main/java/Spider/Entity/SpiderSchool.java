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
    private int IfFanyiGoogle;
    private int IfFanyiBing;
    private int IfFanyiYoudao;
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

    public int getIfFanyiGoogle() {
        return IfFanyiGoogle;
    }

    public void setIfFanyiGoogle(int ifFanyiGoogle) {
        IfFanyiGoogle = ifFanyiGoogle;
    }

    public int getIfFanyiBing() {
        return IfFanyiBing;
    }

    public void setIfFanyiBing(int ifFanyiBing) {
        IfFanyiBing = ifFanyiBing;
    }

    public int getIfFanyiYoudao() {
        return IfFanyiYoudao;
    }

    public void setIfFanyiYoudao(int ifFanyiYoudao) {
        IfFanyiYoudao = ifFanyiYoudao;
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
