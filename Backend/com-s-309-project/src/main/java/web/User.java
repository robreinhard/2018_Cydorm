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


@Entity
@Table(name="User") //cyDormUsers
public class User {
	
	public User() {
		
	}
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer user_id;


    @Column(name="firstName")
    @NotEmpty(message = "*Please provide a first name")
    private String firstName;
    @Column(name="lastName")
    @NotEmpty(message = "*Please provide a last name")
    private String lastName;
    @Column(name="netID")
    @NotEmpty(message = "*Please provide an Iowa State NetID")
    private String netID;
    @Column(name="password")
    @Length(min = 6, message = "*Your password must have at least 6 characters")
    @NotEmpty(message = "*Please provide a password")
    private String password;
    @Column(name="active")
    private int active;
    
    
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
   
    public String getNetID() {
    		return netID;
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
    
    public Role getRole() {
    	
    		return role;
    }
    
    public void setRole(Role role) {
    	
    		this.role = role;
    }
    
    public int getActive() {
    	
    		return active;
    }
    
    public void setActive(int newActive) {
    	
    		active = newActive;
    }
    //Might need to change depending on what we need.
    @Override
    public String toString() {
        return "User [user_id=" + user_id + ", firstName=" + firstName
                + ", lastName=" + lastName + ", netID=" + netID + "]";
    }
    
    
}