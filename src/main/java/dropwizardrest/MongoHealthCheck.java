package dropwizardrest;

import com.mongodb.Mongo;
import com.yammer.metrics.core.HealthCheck;
 
// check if MongoDB is connected or not. 
// A health check is Dropwizard feature to do a runtime test 
// to verify the service’s behaviour in production environment.
public class MongoHealthCheck extends HealthCheck {
	private Mongo mongo;
	 
    protected MongoHealthCheck(Mongo mongo) {
        super("MongoDBHealthCheck");
        this.mongo = mongo;
    }
 
    @Override
    protected Result check() throws Exception {
        mongo.getDatabaseNames();
        return Result.healthy();
    }
}
