package Spider.Bll.Thread;

import Spider.Dao.CnblogsDao;
import Spider.Entity.Cnblogs;
import Util.JsoupUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


/**
 * Created by Administrator on 2017/1/6.
 */
//根据搜索内容来爬取数据
public class CnblogsThread implements Runnable {
    private CnblogsDao cnblogsDao=new CnblogsDao();
    private String type;
    public void SetType(String type){
        this.type=type;
    }
    @Override
    public void run() {
        for(int i=1;i<=50;i++){
            String url="http://zzk.cnblogs.com/s/blogpost?Keywords="+type+"&pageindex="+i;
            Document doc= JsoupUtil.GetDocument(url);
            Elements es=doc.select(".searchItemTitle");
            for(Element e : es){
                Element link=e.getElementsByTag("a").get(0);
                Cnblogs cnblogs=new Cnblogs();
                cnblogs.setName(link.text());
                cnblogs.setUrl(link.attr("href"));
                cnblogs.setType(type);
                cnblogsDao.Add(cnblogs);
            }
        }
    }
}
