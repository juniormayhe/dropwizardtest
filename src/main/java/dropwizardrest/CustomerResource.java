package dropwizardrest;
import java.util.Arrays;
import java.util.List;
 
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
 
import com.yammer.metrics.annotation.Timed;

//Create a new JAX-RS resource for requests made to / path

@Path("/")
public class CustomerResource {

	//get a collection of users as JSON
	@GET
	@Produces(value = MediaType.APPLICATION_JSON)
	@Timed
	public List<Customer> index() {
		//dummy info to simulate database access 
        return Arrays.asList(
        		new Customer("Julia"),
        		new Customer("Johanna"),
        		new Customer("Junior"));
    }
}
