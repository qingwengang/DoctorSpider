package Spider.Start;

import Spider.Bll.Thread.CnblogsContentByListPageThread;
import Spider.Bll.Thread.CnblogsContentThread;

/**
 * Created by Administrator on 2017/1/6.
 */
public class CnblogsStart {
    public static void main(String[] a){
//        CnblogsThread handler=new CnblogsThread();
//        handler.SetType("entity framework");
//        Thread t=new Thread(handler);
//        t.start();
        CnblogsContentThread handler=new CnblogsContentThread();
        Thread t=new Thread(handler);
        t.start();
        /**获取C#的列表**/
//        CnblogsContentByListPageThread ct=new CnblogsContentByListPageThread();
//        ct.SetCategory("108698","108700","Csharp");
//        Thread t=new Thread(ct);
//        t.start();
    }
}
