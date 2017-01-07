package Spider.Start;

        import Spider.Bll.School.CsdnSpider;
        import Spider.Bll.Thread.SpiderCSDNThread;
        import Spider.Dao.CSDNContentDao;
        import Spider.Entity.CSDNContent;

/**
 * Created by Administrator on 2016/10/28.
 */
public class CSDNStart {
    public static void main(String[] args){
//        CsdnSpider.getmulu("CSharp",1,800);
        for(int i=0;i<1;i++){
            SpiderCSDNThread handler=new SpiderCSDNThread(1,i);
            Thread t=new Thread(handler);
            t.start();
        }
//        CSDNContent csdnContent=new CSDNContent();
//        csdnContent.setContent("1111");
//        csdnContent.setMuluId(1);
//        new CSDNContentDao().Add(csdnContent);
    }
}
