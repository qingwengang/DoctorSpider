package Spider.Bll;

import Spider.Dao.MuluDao;
import Spider.Entity.Mulu;
import Util.JsoupUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.List;

/**
 * Created by Administrator on 2016/6/2.
 */
public class AskBll {
    private MuluDao muluDao=new MuluDao();
    public void getMulu(){
        System.out.print("--------------------getmulu 开始----------------------");
        System.out.println("120 get mulu start");
        String url = "http://www.120ask.com/list/";
        Document doc = JsoupUtil.GetDocument(url);
        Elements lis=doc.select(".h-left");
        Element e=lis.get(0).getElementsByTag("ul").get(0);
        System.out.print(e);
        for(Element li : e.children()){
            System.out.print(li.text());
            System.out.print(li.children().get(0).attr("href"));
            Mulu mulu=new Mulu();
            mulu.setName(li.text());
            mulu.setUrl(li.children().get(0).attr("href"));
            mulu.setSpiderFlag(0);
            mulu.setLevel(0);
            mulu.setParentId(0);
            mulu.setSource("120ask");
            mulu.setType(mulu.getUrl().substring(27,mulu.getUrl().length()-1));
            muluDao.Add(mulu);
        }
        getMuluByLevel(0);
        getMuluByLevel(1);
        getMuluByLevel(2);
        System.out.print("--------------------getmulu结束----------------------");
    }
    private void getMulu(Mulu mulu) {
        Document doc = JsoupUtil.GetDocument(mulu.getUrl());
        Elements ul = doc.select(".h-ul1");
        if (ul != null && ul.size()>0) {
            for (Element li : ul.get(0).children()) {
                System.out.print(li.text());
                System.out.print(li.children().get(0).attr("href"));
                Mulu entity = new Mulu();
                entity.setName(li.text());
                entity.setUrl(li.children().get(0).attr("href"));
                entity.setSpiderFlag(0);
                entity.setLevel(mulu.getLevel() + 1);
                entity.setParentId(mulu.getId());
                entity.setSource("120ask");
                entity.setType(entity.getUrl().substring(27, entity.getUrl().length() - 1));
                muluDao.Add(entity);
            }
        }
    }

    private void getMuluByLevel(int level){
        List<Mulu> mulus=new MuluDao().Query("select * from mulu where source='120ask' and level="+level);
        for(Mulu mulu : mulus){
            getMulu(mulu);
        }
    }
}
