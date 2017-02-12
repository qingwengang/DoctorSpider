package Spider.Start.School;

import Spider.Bll.Thread.School.TutorialsTeacherSpider;
import Spider.Bll.Thread.School.TutorialsTeacherThread;

/**
 * Created by Administrator on 2017/2/10.
 */
public class TutorialsTeacherStart {
    public static void main(String[] a){
        TutorialsTeacherSpider spider=new TutorialsTeacherSpider();
        spider.SpiderContent();
    }
}
