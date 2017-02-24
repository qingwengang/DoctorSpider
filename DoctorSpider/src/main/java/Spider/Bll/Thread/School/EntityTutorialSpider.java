package Spider.Bll.Thread.School;

import Spider.DO.School.SchoolElement;
import Spider.Dao.SpiderSchoolDao;
import Spider.Entity.SpiderSchool;
import Util.JsoupUtil;
import org.codehaus.jackson.map.ObjectMapper;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * Created by Administrator on 2017/2/20.
 */
public class EntityTutorialSpider {
    public void SpiderContent(String url,String codeType,String mulu) {
        boolean flag = true;
//        String url = "/csharp/csharp-tutorials";
        while (flag) {
            url="http://www.entityframeworktutorial.net"+url;
            Document doc = JsoupUtil.GetDocument(url);
            Element articleElement = doc.select(".middle-content").get(0).select(".col-sm-7").get(0);
            SchoolElement sElement = new SchoolElement();
            for (Element child : articleElement.children()) {
                boolean ifParsed = false;
                if (child.tagName().equals("p")) {
                    SchoolElement ep = new SchoolElement("p", child.text());
                    sElement.addChild(ep);
                    ifParsed = true;
                }
                if(child.tagName().equals("div")&&child.attr("class").contains("noteDiv")){
                    sElement.addChild(new SchoolElement("note", child.ownText()));
                }
                if(child.tagName().equals("div")&&child.attr("class").contains("points")){
                    sElement.addChild(new SchoolElement("points", child.select(".col-xs-10").get(0).text()));
                }
                if (child.tagName().equals("div") && (child.attr("class").contains("code-panel")||child.attr("class").contains("panel-primary"))) {
                    String sourceContent=child.select(".panel-body").get(0).text();
                    sElement.addChild(new SchoolElement("source_"+codeType, sourceContent));
                    ifParsed = true;
                }
                if (child.tagName().equals("div") && child.attr("class").contains("table-responsive")) {
                    Element table = child.getElementsByTag("table").get(0);
                    SchoolElement tableElement = new SchoolElement();
                    tableElement.setType("table");
                    for (Element tr : table.getElementsByTag("tr")) {
                        SchoolElement trElement = new SchoolElement();
                        trElement.setType("tr");
                        for (Element td : tr.children()) {
                            trElement.addChild(new SchoolElement("td", td.text()));
                        }
                        tableElement.addChild(trElement);
                    }
                    sElement.addChild(tableElement);
                    ifParsed = true;
                }
                if (child.tagName().equals("h2")) {
                    sElement.addChild(new SchoolElement("h2", child.text()));
                    ifParsed = true;
                }
                if (child.tagName().equals("h3")) {
                    sElement.addChild(new SchoolElement("h3", child.text()));
                    ifParsed = true;
                }
                if (child.tagName().equals("figure")) {
                    sElement.addChild(new SchoolElement("img",""));
//                String imgUrl=child.getElementsByTag("a").get(0).attr("href");
//                imgUrl=imgUrl.replace("../","");
//                try {
//                    new ImgUtil().download("http://www.tutorialsteacher.com/"+imgUrl,"12.png","E:\\imgs");
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//                System.out.println(child.text());
//                ifParsed=true;
                }
                if (child.tagName().equals("samp")) {
                    sElement.addChild(new SchoolElement("source_result", child.text()));
//                System.out.println(child.text());
                    ifParsed = true;
                }
                if(child.tagName().equals("ul")&&!child.attr("class").equals("pager")){
                    SchoolElement ul=new SchoolElement();
                    ul.setType("ul");
                    for(Element li : child.getElementsByTag("li")){
                        ul.addChild(new SchoolElement("li",li.text()));
                    }
                    sElement.addChild(ul);
                }
                if(child.tagName().equals("ol")){
                    SchoolElement ul=new SchoolElement();
                    ul.setType("ol");
                    for(Element li : child.getElementsByTag("li")){
                        ul.addChild(new SchoolElement("li",li.text()));
                    }
                    sElement.addChild(ul);
                }
            }
            ObjectMapper mapper = new ObjectMapper();
            String json = "";
            try {
                json = mapper.writeValueAsString(sElement);
            } catch (IOException e) {
            }
            SpiderSchool school = new SpiderSchool();
            String[] urlArray=url.split("/");
            school.setContent(json);
            school.setResource("entitytutal");
            school.setMuluType(mulu);
            school.setPageName(urlArray[urlArray.length-1]);
            new SpiderSchoolDao().Add(school);
            Elements es = doc.select(".next");
            if(es!=null && es.size()>0){
                url=es.get(0).getElementsByTag("a").get(0).attr("href");
            }else {
                flag=false;
            }
        }
    }
}
