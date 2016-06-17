package Util;

import Spider.Config.StockConfig;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.util.Objects;

/**
 * Created by Administrator on 2016/6/17.
 */
public class JsonUtil {
    public void WriteJsonToFileFromObj(Object o,String path,String id) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String json = "";
        try {
            json = mapper.writeValueAsString(o);
        } catch (IOException e) {
        }
        if(json!=null && !json.equals("")){
            try {
                new FileHelper().Write(path,id,json);
            } catch (IOException e) {
                throw e;
            }
        }
    }
}
