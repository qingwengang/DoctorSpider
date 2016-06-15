package Spider.Config;

import java.util.Date;

/**
 * Created by Administrator on 2016/5/24.
 */
public class StockConfig {
    public static Date now=new Date();
    public static String askQuestionPath="F:/120ask/Question/";
    public static String SpiderLogPath="F:/spiderLog/"+now.getYear()+"/"+now.getMonth()+"/"+now.getDay()+"/";
    public static String JsoupLogPath="F:/jsoupLog/"+now.getMonth()+"/"+now.getDay()+"/";
    public static String FHQuestionPath="F:/FH/";
    public static String JiujiuPath="F:/9939/";
}
