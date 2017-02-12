package Spider.Bll.Thread.School;

import Spider.DO.School.SchoolElement;
import Util.JsoupUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/**
 * Created by Administrator on 2017/2/10.
 */
public class TutorialsTeacherThread implements Runnable {
    @Override
    public void run() {
        String url="http://www.tutorialsteacher.com/csharp/csharp-generic-sortedlist";
        Document doc= JsoupUtil.GetDocument(url);
        Element articleElement = doc.select(".article").get(0);
        SchoolElement sElement=new SchoolElement();
        sElement.getChildren().add(new SchoolElement());
        for(Element child : articleElement.children()){
            boolean ifParsed=false;
            if(child.tagName().equals("p")){
                System.out.println(child.text());
                ifParsed=true;
            }
            if(child.tagName().equals("div") && child.attr("class").contains("code-panel")){
                System.out.println(child.text());
                ifParsed=true;
            }
            if(child.tagName().equals("div") && child.attr("class").contains("table-responsive")){
                System.out.println(child.text());
                ifParsed=true;
            }
            if(child.tagName().equals("h2")){
                System.out.println(child.text());
                ifParsed=true;
            }
            if(child.tagName().equals("figure")){
                System.out.println(child.text());
                ifParsed=true;
            }
            if(child.tagName().equals("samp")){
                System.out.println(child.text());
                ifParsed=true;
            }
        }
    }
}
