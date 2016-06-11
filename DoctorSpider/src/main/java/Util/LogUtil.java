package Util;

import Spider.Config.StockConfig;

import java.io.IOException;
import java.util.Date;

/**
 * Created by wgqing on 2015/10/29.
 */
public class LogUtil {
    private static FileHelper fileHelper=new FileHelper();
    public static void Log120Ask(String content){
        try {
            fileHelper.WriteAppend(StockConfig.SpiderLogPath,"log",content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
