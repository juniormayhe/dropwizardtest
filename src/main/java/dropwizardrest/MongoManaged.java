package dropwizardrest;

import com.mongodb.Mongo;
import com.yammer.dropwizard.lifecycle.Managed;
 
//MongoManaged allows us to manage resources on application start and stop
public class MongoManaged implements Managed {
 
    private Mongo mongo;
 
    public MongoManaged(Mongo mongo) {
        this.mongo = mongo;
    }
 
    @Override
    public void start() throws Exception {
    }
 
    @Override
    public void stop() throws Exception {
        mongo.close();
    }
 
}