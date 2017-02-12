package Spider.Bll.Thread.School;

import Spider.DO.School.SchoolElement;
import Spider.Dao.SpiderSchoolDao;
import Spider.Entity.SpiderSchool;
import Util.ImgUtil;
import Util.JsoupUtil;
import org.codehaus.jackson.map.ObjectMapper;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * Created by Administrator on 2017/2/12.
 */
public class TutorialsTeacherSpider {
    public void SpiderContent() {
        boolean flag = true;
        String url = "/csharp/csharp-tutorials";
        while (flag) {

            url="http://www.tutorialsteacher.com"+url;
            Document doc = JsoupUtil.GetDocument(url);
            Element articleElement = doc.select(".article").get(0);
            SchoolElement sElement = new SchoolElement();
            for (Element child : articleElement.children()) {
                boolean ifParsed = false;
                if (child.tagName().equals("p")) {
                    SchoolElement ep = new SchoolElement("p", child.text());
                    sElement.addChild(ep);
                    ifParsed = true;
                }
                if (child.tagName().equals("div") && child.attr("class").contains("code-panel")) {
                    sElement.addChild(new SchoolElement("C#", child.text()));
                    ifParsed = true;
                }
                if (child.tagName().equals("div") && child.attr("class").contains("table-responsive")) {
                    Element table = child.getElementsByTag("table").get(0);
                    SchoolElement tableElement = new SchoolElement();
                    for (Element tr : table.children()) {
                        SchoolElement trElement = new SchoolElement();
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
                    sElement.addChild(new SchoolElement("result", child.text()));
//                System.out.println(child.text());
                    ifParsed = true;
                }
            }
            ObjectMapper mapper = new ObjectMapper();
            String json = "";
            try {
                json = mapper.writeValueAsString(sElement);
            } catch (IOException e) {
            }
            SpiderSchool school = new SpiderSchool();
            school.setContent(json);
            school.setResource("tutalTeacher");
            school.setMuluType("C#");
            school.setPageName("aaa");
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
