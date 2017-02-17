package Util;

        import org.junit.Test;

        import static org.junit.Assert.*;

/**
 * Created by Administrator on 2017/2/12.
 */
public class ImgUtilTest {

    @Test
    public void testDownload() throws Exception {
//        new ImgUtil().download("http://ui.51bi.com/opt/siteimg/images/fanbei0923/Mid_07.jpg", "51bi.gif","c:\\image\\");
        new ImgUtil().download("http://www.tutorialsteacher.com/Content/images/csharp/value-type-memory-allocation.png", "51bi.gif", "c:\\image\\");
    }
}