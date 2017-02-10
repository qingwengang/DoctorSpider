package Spider.DO.School;

import java.util.List;

/**
 * Created by Administrator on 2017/2/10.
 */
public class SchoolElement {
    private String enContent;
    private String type;
    private List<SchoolElement> children;
    private String googleContent;
    private String bingContent;
    private String youdaoContent;

    public String getEnContent() {
        return enContent;
    }

    public void setEnContent(String enContent) {
        this.enContent = enContent;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<SchoolElement> getChildren() {
        return children;
    }

    public void setChildren(List<SchoolElement> children) {
        this.children = children;
    }

    public String getGoogleContent() {
        return googleContent;
    }

    public void setGoogleContent(String googleContent) {
        this.googleContent = googleContent;
    }

    public String getBingContent() {
        return bingContent;
    }

    public void setBingContent(String bingContent) {
        this.bingContent = bingContent;
    }

    public String getYoudaoContent() {
        return youdaoContent;
    }

    public void setYoudaoContent(String youdaoContent) {
        this.youdaoContent = youdaoContent;
    }
}
