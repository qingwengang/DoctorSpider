package Spider.DO.School;

import java.util.LinkedList;
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

    public SchoolElement(){

    }

    public SchoolElement(String type,String enContent ) {
        this.enContent = enContent;
        this.type = type;
    }

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
    public void addChild(SchoolElement element){
        if(children==null){
            children=new LinkedList<>();
        }
        children.add(element);
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

    /**
     * 获取某个类型的所有子节点的集合
     * @param tagName
     * @return
     */
    public List<SchoolElement> GetElementsByTagName(String tagName){
        List<SchoolElement> resultList=new LinkedList<>();
        if(this.type!=null && this.type.equals(tagName)){
            resultList.add(this);
        }
        if(children!=null && children.size()>0){
            for(SchoolElement item : children){
                List<SchoolElement> itemList=item.GetElementsByTagName(tagName);
                if(itemList!=null && itemList.size()>0){
                    resultList.addAll(itemList);
                }
            }
        }
        return resultList;
    }

}
