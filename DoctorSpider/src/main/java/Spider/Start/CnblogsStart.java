package Spider.Start;

import Spider.Bll.Thread.CnblogsContentThread;
import Spider.Bll.Thread.CnblogsThread;

/**
 * Created by Administrator on 2017/1/6.
 */
public class CnblogsStart {
    public static void main(String[] a){
        CnblogsThread handler=new CnblogsThread();
        Thread t=new Thread(handler);
        t.start();
//        CnblogsContentThread handler=new CnblogsContentThread();
//        Thread t=new Thread(handler);
//        t.start();
    }
}
