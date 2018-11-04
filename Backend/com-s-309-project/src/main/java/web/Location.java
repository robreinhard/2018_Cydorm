package web;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "location")
public class Location {
	
	public Location() {
		
		
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int location_id;
	
	@Column(name="location")
	private String location;
	
	//Address to user relationship
    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "location_sublocation",
    		   joinColumns = @JoinColumn(name= "location_id"),
    		   inverseJoinColumns = @JoinColumn(name = "sublocation_id")
    )
    private Set<Sublocation> sublocations;
	
  
	public Location(String location) {
		
		this.location = location;
		sublocations = new HashSet<>();
	}
	
	public String getLocation() {
		
		return location;
	}
	
	public void addSublocation(Sublocation sublocation) {
		
		sublocations.add(sublocation);
	}
	
	@Override
    public String toString() {
        return "Location [location_id=" + location_id + ", location=" + location + "]";
    }
    
	
}