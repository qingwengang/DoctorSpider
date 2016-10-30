package Spider.Start.School;

import Spider.Bll.School.ImgUpload;

/**
 * Created by Administrator on 2016/7/12.
 */
public class GetImgUrl {
    public static void main(String[] args){
//        String a=new ImgUpload().GetImgUploadUrl();
//        System.out.println(a);
        String url="http://www.cnblogs.com/qingwengang/articles/5663642.html";
        new ImgUpload().UpdateImgUrl(url);
    }
}
