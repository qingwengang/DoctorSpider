package Spider.Bll.Thread.School;

import Spider.DO.School.SchoolElement;
import Spider.Dao.SpiderSchoolDao;
import Spider.Entity.SpiderSchool;
import Util.JsoupUtil;
import org.codehaus.jackson.map.ObjectMapper;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;

/**
 * Created by Administrator on 2017/5/4.
 */
public class CommonSpider {
    private SchoolElement sElement;
    private Document doc;
    public void Spider(String url,String codeType,String resource,String mulu,String contentNodeType,String contentNodeName){
        doc = JsoupUtil.GetDocument(url);
        Element articleElement=GetContentNode(contentNodeType,contentNodeName);
        sElement = new SchoolElement();
        spiderElement(articleElement,"hibernate");
        ObjectMapper mapper = new ObjectMapper();
        String json = "";
        try {
            json = mapper.writeValueAsString(sElement);
        } catch (IOException e) {
        }
        SpiderSchool school = new SpiderSchool();
        school.setContent(json);
        school.setResource(resource);
        school.setMuluType(mulu);
        school.setPageName(url);
        new SpiderSchoolDao().Add(school);
    }
    private void spiderElement(Element articleElement,String mulu){
        for (Element child : articleElement.children()){
            try{
                if(child.tagName().equals("p") || child.tagName().equals("h1") || child.tagName().equals("h2") || child.tagName().equals("h3") || child.tagName().equals("h4")){
                    SchoolElement ep = new SchoolElement(child.tagName(), child.text());
                    sElement.addChild(ep);
                }
                if (child.tagName().equals("ul")) {
                    SchoolElement ul = new SchoolElement();
                    ul.setType("ul");
                    for (Element li : child.getElementsByTag("li")) {
                        ul.addChild(new SchoolElement("li", li.text()));
                    }
                    sElement.addChild(ul);
                }
                if (child.tagName().equals("ol")) {
                    SchoolElement ul = new SchoolElement();
                    ul.setType("ol");
                    for (Element li : child.getElementsByTag("li")) {
                        ul.addChild(new SchoolElement("li", li.text()));
                    }
                    sElement.addChild(ul);
                }
                if(child.tagName().equals("pre")){
                    sElement.addChild(new SchoolElement("source_result", child.ownText()));
                }
            }catch (Exception e){

            }
        }
    }
    private Element GetContentNode(String contentNodeType,String contentNodeName){
        if(contentNodeType.equals("id")){
            return doc.getElementById(contentNodeName);
        }else if(contentNodeType.equals("class")){
            return doc.select("."+contentNodeName).get(0);
        }
        return null;
    }
}
