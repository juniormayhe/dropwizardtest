package dropwizardrest;

import java.util.Date;
import java.util.UUID;

import org.hibernate.validator.constraints.NotBlank;

public class Customer {
	private String id = UUID.randomUUID().toString();
	 
    @NotBlank
    private String name;
    
    private final Date publishedOn = new Date();
    
    public Customer(String name) {
    	this.name = name;
    }

    public String getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }
    
    public Date getPublishedOn() {
        return publishedOn;
    }
}
