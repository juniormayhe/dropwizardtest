package dropwizardrest;

import java.util.ArrayList;
import java.util.List;
 
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.annotate.JsonCreator;

import net.vz.mongodb.jackson.DBCursor;
import net.vz.mongodb.jackson.JacksonDBCollection;
 
import com.yammer.metrics.annotation.Timed;
 
@Path("/customers")
@Produces(value = MediaType.APPLICATION_JSON)
@Consumes(value = MediaType.APPLICATION_JSON)
public class CustomerDBResource {
	
    private JacksonDBCollection<Customer, String> collection;
    
    @JsonCreator
    public CustomerDBResource(JacksonDBCollection<Customer, String> customers) {
        this.collection = customers;
    }
 
    @POST
    @Timed
    public Response addNewCustomer(@Valid Customer customer) {
        collection.insert(customer);
        return Response.noContent().build();
    }
}
