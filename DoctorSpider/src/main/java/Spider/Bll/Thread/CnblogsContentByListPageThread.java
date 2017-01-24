package Spider.Bll.Thread;

import Spider.Dao.CnblogsDao;
import Spider.Entity.Cnblogs;
import Util.JsoupUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/1/22.
 */
public class CnblogsContentByListPageThread implements Runnable {
    private CnblogsDao cnblogsDao=new CnblogsDao();
    private  String ParentCategoryId;
    private String CategoryId;
    private String CnblogType;
    public void SetCategory(String parentCategoryId,String categoryId,String cnblogType){
        this.ParentCategoryId=parentCategoryId;
        this.CategoryId=categoryId;
        this.CnblogType=cnblogType;
    }
    @Override
    public void run() {
        for(int i=200;i>0;i--){
            Map<String,String> ms=new HashMap<>();
            ms.put("CategoryType","SiteCategory");
            ms.put("ParentCategoryId",this.ParentCategoryId);
            ms.put("CategoryId",this.CategoryId);
            ms.put("PageIndex",String.valueOf(i));
            ms.put("TotalPostCount","4000");
            ms.put("ItemListActionName","PostList");
            try {
                Document doc= JsoupUtil.httpPost("http://www.cnblogs.com/mvc/AggSite/PostList.aspx", ms, null);
                Elements es=doc.select(".titlelnk");
                for(Element e : es){
                    Element link=e.getElementsByTag("a").get(0);
                    Cnblogs cnblogs=new Cnblogs();
                    cnblogs.setName(link.text());
                    cnblogs.setUrl(link.attr("href"));
                    cnblogs.setType(this.CnblogType);
                    cnblogsDao.Add(cnblogs);
                }
//                for(Element e : es){
//                    System.out.println(e.text());
//                }
//                System.out.println(doc);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }
}
