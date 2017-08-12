package dropwizardrest;
import java.util.ArrayList;
import java.util.List;
 
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
 
import net.vz.mongodb.jackson.DBCursor;
import net.vz.mongodb.jackson.JacksonDBCollection;
 
import com.yammer.metrics.annotation.Timed;

//Create a new JAX-RS resource for requests made to / path

@Path("/")
public class CustomerResource {

	//get a collection of users as JSON
	private JacksonDBCollection<Customer, String> collection;
	 
    public CustomerResource(JacksonDBCollection<Customer, String> customers) {
        this.collection = customers;
    }
 
    @GET
    @Produces(value = MediaType.APPLICATION_JSON)
    @Timed
    public List<Customer> index() {
        DBCursor<Customer> dbCursor = collection.find();
        List<Customer> customers = new ArrayList<>();
        while (dbCursor.hasNext()) {
        	Customer customer  = dbCursor.next();
            customers.add(customer);
        }
        return customers;
    }
}
