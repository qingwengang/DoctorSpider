package Spider.Start.School;

import Spider.Bll.Thread.School.JavaTPointSpider;

/**
 * Created by Administrator on 2017/2/15.
 */
public class JavaTPointStart {
    public static void main(String[] args){
        JavaTPointSpider s=new JavaTPointSpider();
//        s.Spider("java-tutorial","java","java");
//        s.Spider("java-get-current-date","java","java");
//        s.Spider("collections-in-java","java","java");
//        s.Spider("servlet-api","java","servlet");
//        s.Spider("page-implicit-object","java","jsp");
        s.Spider("spring-and-jpa-integration","java","spring");
    }
}
