package SpiderFramework.Bll;

/**
 * Created by Administrator on 2016/6/15.
 */
public class BaseHandlerThread<T extends SpiderHandler> implements Runnable {
    private int count, no;
    public BaseHandlerThread(int count, int no){
        this.count=count;
        this.no=no;
    }
    @Override
    public void run() {


    }
}
