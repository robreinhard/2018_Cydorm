package web;

/**
 * The Class UserRole.
 */
public class UserRole {

	/**
	 * Instantiates a new user role.
	 */
	public UserRole() {
		
	}
	
	/** The user. */
	private User theUser;
	
	/** The role. */
	private String role;
	
	/** The first name. */
	private String firstName;
	
	/** The last name. */
	private String lastName;
	
	/** The net ID. */
	private String netID;
	
	/** The password. */
	private String password;
	

	/**
	 * Instantiates a new user role.
	 *
	 * @param firstName the first name
	 * @param lastName the last name
	 * @param netID the net ID
	 * @param password the password
	 * @param role the role
	 */
	public UserRole(String firstName, String lastName, String netID, String password, String role) {
		
		this.role = role;
		this.firstName = firstName;
		this.lastName = lastName;
		this.netID = netID;
		this.password = password;
		
	}
	
	/**
	 * Gets the user.
	 *
	 * @return the user
	 */
	public User getUser() {
		
		return theUser;
	}
	
	/**
	 * Gets the role.
	 *
	 * @return the role
	 */
	public String getRole() {
		
		return role;
	}
	
	/**
	 * Sets the role.
	 *
	 * @param newRole the new role
	 */
	public void setRole(String newRole) {
		
		this.role = newRole;
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
	 * Sets the first name.
	 *
	 * @param newName the new first name
	 */
	public void setFirstName(String newName) {
		
		this.firstName = newName;
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
	 * Sets the last name.
	 *
	 * @param newName the new last name
	 */
	public void setLastName(String newName) {
		
		this.lastName = newName;
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
	 * Sets the net ID.
	 *
	 * @param newID the new net ID
	 */
	public void setNetID(String newID) {
		
		this.netID = newID;
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
	 * @param newPassword the new password
	 */
	public void setPassword(String newPassword) {
		
		this.password = newPassword;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		
		return "UserRole [firstName=" + firstName
                + ", lastName=" + lastName + ", netID=" + netID + ", password=" + password + ", role=" + role + "]";
	}
}
