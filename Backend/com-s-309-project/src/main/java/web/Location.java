package web;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

/**
 * The Class Location.
 */
@Entity
@Table(name = "location")
public class Location {
	
	/**
	 * Instantiates a new location.
	 */
	public Location() {
		
		
	}
	
	/** The location id. */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int location_id;
	
	/** The location. */
	@Column(name="location")
	private String location;
	
	/** The sublocations. */
	//Address to user relationship
	@OneToMany(mappedBy="sublocation", fetch=FetchType.EAGER)
    private Set<Sublocation> sublocations;
	
  
	/**
	 * Instantiates a new location.
	 *
	 * @param location the location
	 */
	public Location(String location) {
		
		this.location = location;
		sublocations = new HashSet<>();
	}
	
	/**
	 * Gets the location.
	 *
	 * @return the location
	 */
	public String getLocation() {
		
		return location;
	}
	
	/**
	 * Adds the sublocation.
	 *
	 * @param sublocation the sublocation
	 */
	public void addSublocation(Sublocation sublocation) {
		
		sublocations.add(sublocation);
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
    public String toString() {
        return "Location [location_id=" + location_id + ", location=" + location + "]";
    }
    
	
}