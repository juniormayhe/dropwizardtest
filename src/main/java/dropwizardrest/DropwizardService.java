//https://blog.openshift.com/day-13-dropwizard-the-awesome-java-rest-server-stack/

package dropwizardrest;
import com.mongodb.DB;
import com.mongodb.Mongo;
import com.yammer.dropwizard.Service;
import com.yammer.dropwizard.config.Bootstrap;
import com.yammer.dropwizard.config.Environment;
import com.yammer.dropwizard.config.HttpConfiguration;

import net.vz.mongodb.jackson.JacksonDBCollection;

import com.yammer.dropwizard.config.Configuration;

public class DropwizardService extends Service<AppConfiguration> {

	//service entry point
	public static void main(String[] args) throws Exception {
		// override args with "server". The "server" command will start the embedded jetty server.
		new DropwizardService().run(new String[] { "server" });
	}

	//called before running the service
	@Override
	public void initialize(Bootstrap<AppConfiguration> bootstrap) {

		bootstrap.setName("customer");
	}

	// called when service runs, we will add JAX-RS resources
	@Override
	public void run(AppConfiguration configuration, Environment env) throws Exception {

		

		
		//register JAX resource for using mongodb resource
		Mongo mongo = new Mongo(configuration.mongohost, configuration.mongoport);
		MongoManaged mongoManaged = new MongoManaged(mongo);
		env.manage(mongoManaged);
		env.addHealthCheck(new MongoHealthCheck(mongo));
		
		DB db = mongo.getDB(configuration.mongodb);
        JacksonDBCollection<Customer, String> customers = JacksonDBCollection.wrap(db.getCollection("customers"), Customer.class, String.class);

		//register JAX resource without database
		env.addResource(new CustomerStaticResource());
		
        env.addResource(new CustomerDBResource(customers));
        
        env.addResource(new CustomerResource(customers));
        
        
		//change dropwizard port
		HttpConfiguration httpConfig = new HttpConfiguration();
		httpConfig.setPort(8085);
		httpConfig.setAdminPort(8086);
	    configuration.setHttpConfiguration(httpConfig);
	}

}
