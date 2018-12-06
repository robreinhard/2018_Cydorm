package web;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

/**
 * The Class Sublocation.
 */
@Entity
@Table(name = "sublocation")
public class Sublocation {
	
	/**
	 * Instantiates a new sublocation.
	 */
	public Sublocation() {
		
	}
	
	/** The sublocation id. */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int sublocation_id;
	
	/** The sublocation. */
	@Column(name="sublocation")
	private String sublocation;
	
	/** The addresses. */
	//Address to user relationship
    @OneToMany(mappedBy="sublocation", fetch=FetchType.EAGER)
    private Set<Address> addresses;
	
    
    /** The ca. */
    @OneToOne(cascade= CascadeType.ALL)
    @JoinTable(name = "sublocation_user",
    		   joinColumns = @JoinColumn(name="sublocation_id"),
    		   inverseJoinColumns = @JoinColumn(name="user_id")
    )
	private User ca;
    
    /** The location. */
    //Address to user relationship
    @ManyToOne(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
    @JoinTable(name = "sublocation_location",
    		   joinColumns = @JoinColumn(name= "sublocation_id"),
    		   inverseJoinColumns = @JoinColumn(name = "location_id")
    )
    private Location location;
    
	/**
	 * Instantiates a new sublocation.
	 *
	 * @param sublocation the sublocation
	 */
	public Sublocation(String sublocation) {
		
		this.sublocation = sublocation;
		addresses = new HashSet<>();;
	}
	
	/**
	 * Gets the sublocation.
	 *
	 * @return the sublocation
	 */
	public String getSublocation() {
		
		return sublocation;
	}
	
	/**
	 * Gets the ca.
	 *
	 * @return the ca
	 */
	public User getCA() {
		
		return ca;
	}
	
	/**
	 * Sets the ca.
	 *
	 * @param user the new ca
	 */
	public void setCA(User user) {
		
		ca = user;
	}
	
	/**
	 * Gets the addresses.
	 *
	 * @return the addresses
	 */
	public Set<Address> getAddresses() {
		
		return addresses;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
    public String toString() {
        return "Sublocation [sublocation_id=" + sublocation_id + ", sublocation=" + sublocation + "]";
    }
    
	public Location getLocation() {
		
		return location;
	}
	
	public void setLocation(Location location) {
		
		this.location = location;
	}
}