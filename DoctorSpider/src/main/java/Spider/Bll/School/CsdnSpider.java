package Spider.Bll.School;

import Spider.Dao.CSDNMuluDao;
import Spider.Entity.CSDNMulu;
import Util.JsoupUtil;
import Util.LogUtil;
import com.mongodb.DBPortPool;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.print.Doc;
import java.text.MessageFormat;

/**
 * Created by Administrator on 2016/10/28.
 */
public class CsdnSpider {
    private static CSDNMuluDao muluDao=new CSDNMuluDao();
    public static void getmulu(String typeName,int pageStart,int pageEnd){
        for(int i=pageEnd;i>=pageStart;i--){
            String url= MessageFormat.format("http://bbs.csdn.net/forums/{0}?page={1}",typeName,i);
            Document doc = JsoupUtil.GetDocument(url);
            Elements titles=doc.select(".table_list").get(0).select(".title");
            for(Element title : titles){
                System.out.println(title.getElementsByTag("a").get(0).attr("href"));
                CSDNMulu mulu=new CSDNMulu();
                mulu.setSpiderFlag(0);
                mulu.setUrl(title.getElementsByTag("a").get(0).attr("href"));
                mulu.setType(typeName);
                muluDao.Add(mulu);
            }
            LogUtil.Log120Ask(typeName+"-"+i);
        }

    }
    public static void getContent(String url){
        Document doc=JsoupUtil.GetDocument("http://bbs.csdn.net/topics/190144794");

    }
}
