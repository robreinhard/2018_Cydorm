package web;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;


/**
 * The Class User.
 */
@Entity
@Table(name="user") //cyDormUsers
public class User {
	
	/**
	 * Instantiates a new user.
	 */
	public User() {
		
	}
	
	/** The user id. */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer user_id;


    /** The first name. */
    @Column(name="firstName")
    @NotEmpty(message = "*Please provide a first name")
    private String firstName;
    
    /** The last name. */
    @Column(name="lastName")
    @NotEmpty(message = "*Please provide a last name")
    private String lastName;
    
    /** The net ID. */
    @Column(name="netID")
    @NotEmpty(message = "*Please provide an Iowa State NetID")
    private String netID;
    
    /** The password. */
    @Column(name="password")
    @Length(min = 6, message = "*Your password must have at least 6 characters")
    @NotEmpty(message = "*Please provide a password")
    private String password;
    
    /** The active. */
    @Column(name="active")
    private int active;
    
    
    /** The role. */
    //Role to User relationship <- does this need to be renamed to cydormuser_role
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinTable(name = "user_role", 
    		   joinColumns = @JoinColumn(name = "user_id"), 
    		   inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Role role; //this might need to be changed.
    
    /** The address. */
    //Address to user relationship
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinTable(name = "user_address",
    		   joinColumns = @JoinColumn(name= "user_id"),
    		   inverseJoinColumns = @JoinColumn(name = "address_id")
    )
    private Address address;
    
    
    

    
    /**
     * Instantiates a new user.
     *
     * @param firstName the first name
     * @param lastName the last name
     * @param netID the net ID
     * @param password the password
     */
    public User(String firstName, String lastName, String netID, String password) {
    	
    		this.firstName = firstName;
    		this.lastName=lastName;
    		this.netID = netID;
    		this.password = password;
    		
    }
    
    
    /**
     * Gets the id.
     *
     * @return the id
     */
    public Integer getId() {
    		return user_id;
    }
    
    /**
     * Sets the id.
     *
     * @param id the new id
     */
    public void setId(Integer id) {
    		id = this.user_id;
    }
   
    /**
     * Gets the net ID.
     *
     * @return the net ID
     */
    public String getNetID() {
    		return netID;
    }
    
    /**
     * Gets the first name.
     *
     * @return the first name
     */
    public String getFirstName() {
    		return firstName;
    }
    
    /**
     * Gets the last name.
     *
     * @return the last name
     */
    public String getLastName() {
    		return lastName;
    }
    
    /**
     * Sets the first name.
     *
     * @param firstName the new first name
     */
    public void setFirstName(String firstName){
    		this.firstName = firstName;
    }
    
    /**
     * Sets the last name.
     *
     * @param lastName the new last name
     */
    public void setLastName(String lastName) {
    		this.lastName = lastName;
    }
    
    /**
     * Gets the password.
     *
     * @return the password
     */
    public String getPassword() {
    	
    		return password;
    }
    
    /**
     * Sets the password.
     *
     * @param password the new password
     */
    public void setPassword(String password) {
    	
    		this.password = password;
    }
    
    /**
     * Gets the role.
     *
     * @return the role
     */
    public Role getRole() {
    	
    		return role;
    }
    
    /**
     * Sets the role.
     *
     * @param role the new role
     */
    public void setRole(Role role) {
    	
    		this.role = role;
    }
    
    /**
     * Gets the active.
     *
     * @return the active
     */
    public int getActive() {
    	
    		return active;
    }
    
    /**
     * Gets the address.
     *
     * @return the address
     */
    public Address getAddress() {
    	
    		return address;
    }
    
    /**
     * Sets the address.
     *
     * @param address the new address
     */
    public void setAddress(Address address) {
    	
    	
    	this.address = address;
    	System.out.println(this.address.toString());
    }
    
    /**
     * Sets the active.
     *
     * @param newActive the new active
     */
    public void setActive(int newActive) {
    	
    		active = newActive;
    }
    
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    //Might need to change depending on what we need.
    @Override
    public String toString() {
        return "User [user_id=" + user_id + ", firstName=" + firstName
                + ", lastName=" + lastName + ", netID=" + netID + "]";
    }
    
    
    
    
}