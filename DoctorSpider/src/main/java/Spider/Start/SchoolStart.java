package Spider.Start;

import Spider.Bll.School.MuluSpider;
import Spider.Bll.School.SchoolSpider;

/**
 * Created by qingwengang on 2016/7/7.
 */
public class SchoolStart {
    public static void main(String[] args){
        new MuluSpider().GetMulu("http://www.runoob.com/html/html-intro.html");
        new MuluSpider().GetMulu("http://www.runoob.com/css/css-tutorial.html");
        new MuluSpider().GetMulu("http://www.runoob.com/js/js-tutorial.html");
        new MuluSpider().GetMulu("http://www.runoob.com/jquery/jquery-tutorial.html");
        new MuluSpider().GetMulu("http://www.runoob.com/mysql/mysql-tutorial.html");
        new SchoolSpider().GetContent();
    }
}
