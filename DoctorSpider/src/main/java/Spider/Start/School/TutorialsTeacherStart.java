package Spider.Start.School;

import Spider.Bll.Thread.School.TutorialsTeacherSpider;
import Spider.Bll.Thread.School.TutorialsTeacherThread;

/**
 * Created by Administrator on 2017/2/10.
 */
public class TutorialsTeacherStart {
    public static void main(String[] a){
        TutorialsTeacherSpider spider=new TutorialsTeacherSpider();
//        spider.SpiderContent("/csharp/csharp-tutorials","c#");
//        spider.SpiderContent("/linq/linq-tutorials","c#");
//        spider.SpiderContent("/mvc/asp.net-mvc-tutorials","c#");
//        spider.SpiderContent("/javascript/javascript-tutorials","js");
//        spider.SpiderContent("/javascript/class-in-javascript","js");
//        spider.SpiderContent("/jquery/jquery-tutorials","js");
        spider.SpiderContent("/EntityFramework5/entity-framework5-introduction.aspx","entity5");
    }
}
