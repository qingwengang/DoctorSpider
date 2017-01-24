package Spider.Start;

import Util.JsoupUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.LinkedList;
        import java.util.List;

/**
 * Created by Administrator on 2017/1/17.
 */
public class TutorialspointStart {
    public static void main(String[] args){
        String url="https://www.tutorialspoint.com/asp.net_core/asp.net_core_views.htm";
        Document doc= JsoupUtil.GetDocument(url);
        Element body=doc.getElementById("middle-col");
        List<Element> imgList=new LinkedList<>();
        List<Element> contentElements=new LinkedList<>();
        for(Element e : body.children()){
            if(e.tagName().equals("img")){
                imgList.add(e);
            }
        }
    }
}
