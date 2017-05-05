package Spider.Start.School;

import Spider.Bll.Thread.School.CommonSpider;

/**
 * Created by Administrator on 2017/5/4.
 */
public class CommonStart {
    public static void main(String[] args){
        CommonSpider spider=new CommonSpider();
        spider.Spider("http://www.journaldev.com/2934/hibernate-many-to-many-mapping-join-tables","java","journaldev","hibernate","class","entry-content");
    }
}
