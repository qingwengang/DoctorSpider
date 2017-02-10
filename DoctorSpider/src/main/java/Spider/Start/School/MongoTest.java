package Spider.Start.School;

import Spider.Entity.Dog;
import com.cybermkd.mongo.kit.MongoKit;
import com.cybermkd.mongo.kit.MongoQuery;
import com.cybermkd.mongo.plugin.MongoJFinalPlugin;
import com.cybermkd.mongo.plugin.MongoPlugin;
import com.mongodb.MongoClient;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.transaction.TransactionManager;

/**
 * Created by Administrator on 2017/2/10.
 */
public class MongoTest {
    public static void main(String[] args){
        MongoPlugin mongoPlugin=new MongoPlugin();
        mongoPlugin.add("127.0.0.1",27017);
        mongoPlugin.setDatabase("local");
        MongoClient client = mongoPlugin.getMongoClient();
        MongoKit.INSTANCE.init(client, mongoPlugin.getDatabase());


        MongoQuery query=new MongoQuery();
        query.use("fd").set("key", "value").save();

        client.close();
    }
}
