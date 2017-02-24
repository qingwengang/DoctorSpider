package Spider.Start.School;

import Spider.Bll.Thread.School.EntityTutorialSpider;

/**
 * Created by Administrator on 2017/2/20.
 */
public class EntityTutorialStart {
    public static void main(String[] a){
        EntityTutorialSpider spider=new EntityTutorialSpider();
//        spider.SpiderContent("/EntityFramework5/entity-framework5-introduction.aspx","c#","entity5");
//        spider.SpiderContent("/entityframework6/introduction.aspx","c#","entity6");
        spider.SpiderContent("/code-first/entity-framework-code-first.aspx","c#","code-first");
    }
}
