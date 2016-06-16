package SpiderFramework.Bll;

import Util.LogUtil;

/**
 * Created by Administrator on 2016/6/15.
 */
public class BaseHandlerThread<T extends SpiderHandler> implements Runnable {
    private int count, no;
    private Class<T> classType;
    private T t;
    public BaseHandlerThread(int count, int no,Class<T> classType){
        this.count=count;
        this.no=no;
        try {
            this.t=classType.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void run() {
        boolean flag=true;
        while (flag){
            try {
                t.DoSpider();
                flag=false;
            } catch (Exception e) {
                LogUtil.Log120Ask("SpiderFHQuestionThreader线程：" + t.getThreadNo() + "意外终止，" + e.getStackTrace() + e.getMessage());
                flag=true;
            }
        }
    }
}
