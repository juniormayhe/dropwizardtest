package dropwizardrest;

import java.util.Date;
import java.util.UUID;

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.vz.mongodb.jackson.ObjectId;

public class Customer {
	
	
	//
	
	
	//private String id = UUID.randomUUID().toString();
	
	
    @NotBlank
    private String name;
    
    private final Date publishedOn = new Date();
    
    public Customer() {}
    
    public Customer(String name) {
    	super();
    	this.name = name;
    }
    //private String _id;
    
    @ObjectId
	@JsonProperty("_id")
    public String _id;
	//public String get_Id() {
		//return this._id;
	//}
    
    @JsonProperty("name")
    public String getName() {
        return this.name;
    }
    
    @JsonProperty("publishedOn")
    public Date getPublishedOn() {
        return this.publishedOn;
    }
    
    //setters : according to http://mongojack.org/tutorial.html
    //@ObjectId
    //@JsonProperty("_id")
    //public void setId(String id) {
      //this.id = id;
    //}
}
