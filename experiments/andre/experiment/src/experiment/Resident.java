package experiment;

public class Resident {
	  
    private String name;
    private String emailAddr;
    private String streetAddr;
    private String aptNum;

  
    public Resident(String name, String emailAddr, String streetAddr, String aptNum) {
    		name = this.name;
    		emailAddr = this.emailAddr;
    		streetAddr = this.streetAddr;
    		aptNum = this.aptNum;
    }
    
    //If resident wants to change email address
    public void setEmailAddress(String emailAddr) {
        this.emailAddr = emailAddr;
    }
    
    //Resident gets married, divorced, etc.
    public void setName(String name) {
        this.name = name;
    }
    
    //Resident moves but is still able to use app
    public void setStreetAddr(String streetAddr, String aptNum) {
    		streetAddr = this.streetAddr;
    		aptNum = this.aptNum;
    }
    
    public String getName() {
        return name;
    }
    
    public String getEmailAddress() {
        return emailAddr;
    }
    
    public String getStreetAddr() {
    		return streetAddr;
    }
    
    public String getAptNum() {
    		return aptNum;
    }
}