package com.example.demo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.Column;


@Entity
@Table(name="cyDormUsers") //cyDormUsers
public class CyDormUser {
	
	public CyDormUser() {
		
	}
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	
    private int permLevel;
    private Integer id;
    //TODO isLoggedIn needs set

    
    @Column
    private String email;
    @Column
    private String firstName;
    @Column
    private String lastName;
    
    /*public CyDormUser(String firstName, String lastName, String email, int permLevel) {
    		this.firstName = firstName;
    		this.lastName = lastName;
    		this.email = email;
    		this.permLevel = permLevel;
    }
    */
    
    
    public CyDormUser(String firstName, String lastName, String email, int permLevel) {
    		this.firstName = firstName;
    		this.lastName=lastName;
    		this.email = email;
    		//this.user_password = user_password;
    		this.permLevel = permLevel;
    }
    
    
    public Integer getId() {
    		return id;
    }
    
    public void setId(Integer id) {
    		id = this.id;
    }
    /*
    public String getuser_name() {
        return user_name;
    }*/
    
    //public boolean isLoggedIn() {
    	//	return isLoggedIn;
    //}
    
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
    
    //TODO
    //public void userAuth(String database, String user_name) {
    		//isLoggedIn=true;
    	//	isLoggedIn = false;	//Hacker no hacking!
    //}
    
    /*public void setTempPass() {
    		user_password = "SetMeLater";
    }*/
    
    public String getEmail() {
    		return email;
    }
    
    public void setEmail(String email) {
    		this.email = email;
    }
    
    /*public void setuser_name() {
        user_name = firstName+lastName.charAt(0);
    }*/
    
    public int getPermLevel() {
    		return permLevel;
    }
    
    public void setPermLevel(int num) {
		permLevel = num;
}
    
    /*public String getuser_password() {
        return user_password;
    }
    
    public void setuser_password(String user_password) {
    		if(verifyuser_password(user_password))
    			this.user_password = user_password;
    }*/
    
    /***
     * This method verifies the user_password before the user can reset/set user_password.
     * @param user_password
     * @return true if user_password fits requirements, or false if not.
     */
    public boolean verifyuser_password(String user_password) {
    		if (user_password.isEmpty()) return false;
    		else if (user_password.length() < 8) return false;
    		//else if (user_password.contains(user_name)) return false;
    		else if (user_password.toLowerCase().contains("user_password")) return false;
    		else return true;	//Check for upper/lower/special chars combo
    }
    
    @Override
    public String toString() {
        return "CyDormUser [id=" + id + ", firstName=" + firstName
                + ", lastName=" + lastName + ", email=" + email + "]";
    }
}