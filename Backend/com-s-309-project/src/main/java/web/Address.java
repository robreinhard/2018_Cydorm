package web;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

/**
 * The Class Address. Used to access the properties of the user's address.
 */
@Entity
@Table(name = "address")
public class Address {
	
	/**
	 * Instantiates a new address.
	 */
	public Address() {
		
	}
	
	/** The address id. */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int address_id;
	
	/** The address. */
	@Column(name="address")
	private String address;
	
	/** The groceries. */
	//Address to user relationship
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval=true, fetch=FetchType.EAGER)
    @JoinTable(name = "address_grocery",
    		   joinColumns = @JoinColumn(name= "address_id"),
    		   inverseJoinColumns = @JoinColumn(name = "grocery_id")
    )
    private Set<Grocery> groceries;
	
    /** The sublocation. */
    //Address to user relationship
    @ManyToOne(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
    @JoinTable(name = "address_sublocation",
    		   joinColumns = @JoinColumn(name= "address_id"),
    		   inverseJoinColumns = @JoinColumn(name = "sublocation_id")
    )
    private Sublocation sublocation;
    
    /** The disputes. */
    //Address to user relationship
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval=true, fetch=FetchType.EAGER)
    @JoinTable(name = "address_dispute",
    		   joinColumns = @JoinColumn(name= "address_id"),
    		   inverseJoinColumns = @JoinColumn(name = "dispute_id")
    )
    private Set<Dispute> disputes;
    
	/**
	 * Instantiates a new address.
	 *
	 * @param address the address
	 */
	public Address(String address) {
		
		this.address = address;
		groceries = new HashSet<>();;
		chores = new HashSet<>();
		disputes = new HashSet<>();
		
	}
	
	/**
	 * Gets the address.
	 *
	 * @return the address
	 */
	public String getAddress() {
		
		return address;
	}
	
	/**
	 * Sets the address.
	 *
	 * @param address the new address
	 */
	public void setAddress(String address) {
		
		this.address = address;
	}
	
	/**
	 * Gets the groceries.
	 *
	 * @return the groceries
	 */
	public Set<Grocery> getGroceries() {
		
		return groceries;
	}
	
	/** The chores. */
	//Address has many chores
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval=true, fetch=FetchType.EAGER)
    @JoinTable(name = "address_chores",
    		   joinColumns = @JoinColumn(name= "address_id"),
    		   inverseJoinColumns = @JoinColumn(name = "chore_id")
    )
	public Set<Chore> chores; 
    
    /**
     * Gets the chores.
     *
     * @return the chores
     */
    public Set<Chore> getChores(){
		return chores;
	}
	
    /**
     * Adds the chore.
     *
     * @param chore the chore
     */
    public void addChore(Chore chore) {
    	
    	chores.add(chore);
    }
    
    /**
     * Delete chore.
     *
     * @param chore the chore
     */
    public void deleteChore(Chore chore) {
    	
    	chores.remove(chore);
    	System.out.println(chores);
    }
    
    
    /**
     * Gets the disputes.
     *
     * @return the disputes
     */
    public Set<Dispute> getDisputes(){
		return disputes;
	}

    /**
     * Adds the grocery.
     *
     * @param grocery the grocery
     */
    public void addGrocery(Grocery grocery) {
		
		groceries.add(grocery);
	}
	
    /**
     * Adds the dispute.
     *
     * @param dispute the dispute
     */
    public void addDispute(Dispute dispute) {
    	
    	disputes.add(dispute);
    }
    
    /**
     * Delete dispute.
     *
     * @param dispute the dispute
     */
    public void deleteDispute(Dispute dispute) {
    	
    	disputes.remove(dispute);
    	
    }
    
	/**
	 * Delete grocery.
	 *
	 * @param grocery the grocery
	 */
	public void deleteGrocery(Grocery grocery) {
		
		groceries.remove(grocery);
		System.out.println(groceries);
	}
	
	/**
	 * Gets the sublocation.
	 *
	 * @return the sublocation
	 */
	public Sublocation getSublocation() {
		
		return sublocation;
	}
	
	/**
	 * Sets the sublocation.
	 *
	 * @param sublocation the new sublocation
	 */
	public void setSublocation(Sublocation sublocation) {
		
		this.sublocation = sublocation;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
    public String toString() {
        return "Address [address_id=" + address_id + ", address=" + address + "]";
    }
    
	
}
