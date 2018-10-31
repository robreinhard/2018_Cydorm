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

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;


@Entity
@Table(name="User") //cyDormUsers
public class User {
	
	public User() {
		
	}
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer user_id;


    //TODO isLoggedIn needs set   
    @Column(name="firstName")
    private String firstName;
    @Column(name="lastName")
    private String lastName;
    @Column(name="netID")
    private String netID;
    @Column(name="password")
    private String password;
    
    
    
    //Role to User relationship <- does this need to be renamed to cydormuser_role
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinTable(name = "user_role", 
    		   joinColumns = @JoinColumn(name = "user_id"), 
    		   inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Role role; //this might need to be changed.
    
    //Address to user relationship
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinTable(name = "user_address",
    		   joinColumns = @JoinColumn(name= "user_id"),
    		   inverseJoinColumns = @JoinColumn(name = "address_id")
    )
    private Address residency;
    
    
    

    
    public User(String firstName, String lastName, String netID, String password) {
    		this.firstName = firstName;
    		this.lastName=lastName;
    		this.netID = netID;
    		this.password = password;
    }
    
    
    public Integer getId() {
    		return user_id;
    }
    
    public void setId(Integer id) {
    		id = this.user_id;
    }
   
    
    public String getFirstName() {
    		return firstName;
    }
    
    public String getLastName() {
    		return lastName;
    }
    
    public void setFirstName(String firstName){
    		this.firstName = firstName;
    }
    
    public void setLastName(String lastName) {
    		this.lastName = lastName;
    }
    
    public String getPassword() {
    	
    		return password;
    }
    
    public void setPassword(String password) {
    	
    		this.password = password;
    }
    
    //Might need to change depending on what we need.
    @Override
    public String toString() {
        return "User [user_id=" + user_id + ", firstName=" + firstName
                + ", lastName=" + lastName + ", netID=" + netID + "]";
    }
    
    
}