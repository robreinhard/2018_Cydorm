package com.example.demo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;


@Entity
@Table(name="cyDormUsers")
public class CyDormUser {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String userName;
    private String password;
    private int permLevel;
    private Integer id;
    //TODO isLoggedIn needs set
    private boolean isLoggedIn;
    private String email;
    private String firstName;
    private String lastName;
    
    public CyDormUser(String firstName, String lastName, String email) {
    		this.firstName = firstName;
    		this.lastName = lastName;
    		this.email = email;
    }
    
    public CyDormUser(String userName, String password, int permLevel, boolean isLoggedIn, Integer id) {
    		this.userName = userName;
    		this.password = password;
    		this.permLevel = permLevel;	//Look into security best practices
    		this.isLoggedIn = false;
    		this.id = id;
    }
    
    public Integer getId() {
    		return id;
    }
    
    public void setId(Integer id) {
    		id = this.id;
    }
    
    public String getUserName() {
        return userName;
    }
    
    public boolean isLoggedIn() {
    		return isLoggedIn;
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
    
    //TODO
    public void userAuth(String database, String username) {
    		//isLoggedIn=true;
    		isLoggedIn = false;	//Hacker no hacking!
    }
    
    public String getEmail() {
    		return email;
    }
    
    public void setEmail(String email) {
    		this.email = email;
    }
    
    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    public int getPermLevel() {
    		return permLevel;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
    		if(verifyPassword(password))
    			this.password = password;
    }
    
    /***
     * This method verifies the password before the user can reset/set password.
     * @param password
     * @return true if password fits requirements, or false if not.
     */
    public boolean verifyPassword(String password) {
    		if (password.isEmpty()) return false;
    		else if (password.length() < 8) return false;
    		else if (password.contains(userName)) return false;
    		else if (password.toLowerCase().contains("password")) return false;
    		else return true;	//Check for upper/lower/special chars combo
    }
}