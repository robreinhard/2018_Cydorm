package web;

public class UserRole {

	public UserRole() {
		
	}
	
	private User theUser;
	private String theRole;
	private String firstName;
	private String lastName;
	private String netID;
	private String password;
	

	public UserRole(String firstName, String lastName, String netID, String password, String role) {
		
		theRole = role;
		this.firstName = firstName;
		this.lastName = lastName;
		this.netID = netID;
		this.password = password;
		
	}
	
	public User getUser() {
		
		return theUser;
	}
	
	public String getRole() {
		
		return theRole;
	}
	
	public String getFirstName() {
		
		return firstName;
	}
	
	public void setFirstName(String newName) {
		
		this.firstName = newName;
	}
	
	public String getLastName() {
		
		return lastName;
	}
	
	public void setLastName(String newName) {
		
		this.lastName = newName;
	}
	
	public String getNetID() {
		
		return netID;
	}
	
	public void setNetID(String newID) {
		
		this.netID = newID;
	}
	
	public String getPassword() {
		
		return password;
	}
	
	public void setPassword(String newPassword) {
		
		this.password = newPassword;
	}
}
