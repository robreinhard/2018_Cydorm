package com.example.demo;

public class CyDormUser {
	private String userName;
    private String password;
    private boolean isAdmin;
    //TODO isLoggedIn needs set
    private boolean isLoggedIn;
    
    public CyDormUser(String userName, String password, boolean isAdmin) {
    		this.userName = userName;
    		this.password = password;
    		this.isAdmin = isAdmin;
    }
    
    public String getUserName() {
        return userName;
    }
    
    public boolean isLoggedIn() {
    		return isLoggedIn;
    }
    
    //TODO
    public void userAuth(String database, String username) {
    		//isLoggedIn=true;
    		isLoggedIn = false;
    }
    
    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    public boolean getAdminStatus() {
    		return isAdmin;
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