package web;

public class Residency {

	public Residency() {
		
	}
	
	private String location;
	private String sublocation;
	private String address;
	
	public Residency(String location, String sublocation, String address) {
		
		this.location = location;
		this.sublocation = sublocation;
		this.address = address;
		
	}
	
	public String getLocation() {
		
		return location;
	}
	
	public void setLocation(String newLocation) {
		
		location = newLocation;
	}
	
	public String getSublocation() {
		
		return sublocation;
		
	}
	
	public void setSublocation(String newSublocation) {
		
		sublocation = newSublocation;
	}
	
	public String getAddress() {
		
		return address;
	}
	
	public void setAddress(String newAddress) {
		
		address = newAddress;
	}
	
}
