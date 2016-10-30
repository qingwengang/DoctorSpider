package Spider.Bll.School;

import Spider.Dao.SchoolContentDao;
import Spider.Entity.SchoolContent;
import Util.JsoupUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.text.MessageFormat;
import java.util.List;

/**
 * Created by Administrator on 2016/7/12.
 */
public class ImgUpload {
    private SchoolContentDao contentDao = new SchoolContentDao();

    public String GetImgUploadUrl() {
        String sql = "select * from schoolcontent where IfImgUpLoaded is null or IfImgUpLoaded=0";
        List<SchoolContent> contentList = new SchoolContentDao().Query(sql);
        StringBuilder sbIMgUrls = new StringBuilder();
        sbIMgUrls.append("<p id='qwg'>");
        for (SchoolContent schoolContent : contentList) {
            String html = "<html>" + schoolContent.getOutContent() + "</html>";
            Document doc = JsoupUtil.GetDocumentByHtml(html, "utf-8");
            Elements imgs = doc.getElementsByTag("img");
            for (Element img : imgs) {
                sbIMgUrls.append(MessageFormat.format("<p><img src=\"{0}\" alt=\"\" id=\"{0}\" width=\"500\" /></p>", img.attr("src")));
            }
        }
        sbIMgUrls.append("</p>");
        return sbIMgUrls.toString();
    }

    public void UpdateImgUrl(String url) {
        String sql = "select * from schoolcontent where IfImgUpLoaded is null or IfImgUpLoaded=0";
        List<SchoolContent> contentList = new SchoolContentDao().Query(sql);
        Document doc1 = JsoupUtil.GetDocument(url);
        for (SchoolContent schoolContent : contentList) {
            Document doc = JsoupUtil.GetDocumentByHtml(schoolContent.getOutContent(), "utf-8");
            Elements imgs = doc.getElementsByTag("img");
            for (Element img : imgs) {
                String imgUrl = img.attr("src");
                Element imgBlog = doc1.getElementById(imgUrl);
                if (imgBlog != null) {
                    schoolContent.setOutContent(schoolContent.getOutContent().replace(img.attr("src"),imgBlog.attr("src")));
                }
            }
            contentDao.Update(schoolContent);
        }
    }
}
