package Spider.Bll.Thread.School;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Administrator on 2017/2/24.
 */
public class JavaTPointSpiderTest {
    private JavaTPointSpider spider=new JavaTPointSpider();
    @Test
    public void testUpdate() throws Exception {
        for(int i=787;i<914;i++){
            spider.UpdateContent(i);
            System.out.println(i+"升级完成！----------------------");
        }

    }
}