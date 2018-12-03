package web;

/**
 * The Class Residency.
 */
public class Residency {

	/**
	 * Instantiates a new residency.
	 */
	public Residency() {
		
	}
	
	/** The location. */
	private String location;
	
	/** The sublocation. */
	private String sublocation;
	
	/** The address. */
	private String address;
	
	/** The net ID. */
	private String netID;
	
	/**
	 * Instantiates a new residency.
	 *
	 * @param location the location
	 * @param sublocation the sublocation
	 * @param address the address
	 * @param netID the net ID
	 */
	public Residency(String location, String sublocation, String address, String netID) {
		
		this.location = location;
		this.sublocation = sublocation;
		this.address = address;
		this.netID = netID;
	}
	
	/**
	 * Instantiates a new residency.
	 *
	 * @param location the location
	 * @param sublocation the sublocation
	 * @param address the address
	 */
	public Residency(String location, String sublocation, String address) {
		
		this.location = location;
		this.sublocation = sublocation;
		this.address = address;

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
	 * @param newNetID the new net ID
	 */
	public void setNetID(String newNetID) {
		
		this.netID = newNetID;
	}
	
	/**
	 * Gets the location.
	 *
	 * @return the location
	 */
	public String getLocation() {
		
		return location;
	}
	
	/**
	 * Sets the location.
	 *
	 * @param newLocation the new location
	 */
	public void setLocation(String newLocation) {
		
		location = newLocation;
	}
	
	/**
	 * Gets the sublocation.
	 *
	 * @return the sublocation
	 */
	public String getSublocation() {
		
		return sublocation;
		
	}
	
	/**
	 * Sets the sublocation.
	 *
	 * @param newSublocation the new sublocation
	 */
	public void setSublocation(String newSublocation) {
		
		sublocation = newSublocation;
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
	 * @param newAddress the new address
	 */
	public void setAddress(String newAddress) {
		
		address = newAddress;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		
		return "Residency [location= " + location + ", sublocation= " + sublocation + ", address= " + address + "]";
	}
	
}
