package web;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "Residency")
public class Residency {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int residency_id;
	
	@Column(name="location")
	private String location;	
	@Column(name="sublocation")
	private String sublocation;
	@Column(name="address")
	private String address;
	
	//Address to user relationship
    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "residency_grocery",
    		   joinColumns = @JoinColumn(name= "residency_id"),
    		   inverseJoinColumns = @JoinColumn(name = "grocery_id")
    )
    private Set<Grocery> groceries;
	
	public Residency(String location, String sublocation, String address) {
		this.location = location;
		this.sublocation = sublocation;
		this.address = address;
	}
	
	public void setLocation(String location) {
		
		this.location = location;
		
	}
	
	public void setSublocation(String sublocation) {
		
		this.sublocation = sublocation;
	}
	
	public void setAddress(String address) {
		
		this.address = address;
	}
	
	@Override
    public String toString() {
        return "Residency [residency_id=" + residency_id + ", location=" + location
                + ", sublocation=" + sublocation + ", address=" + address + "]";
    }
    
	
}
