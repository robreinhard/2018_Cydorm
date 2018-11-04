package web;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "sublocation")
public class Sublocation {
	
	public Sublocation() {
		
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int sublocation_id;
	
	@Column(name="sublocation")
	private String sublocation;
	
	//Address to user relationship
    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "sublocation_address",
    		   joinColumns = @JoinColumn(name= "sublocation_id"),
    		   inverseJoinColumns = @JoinColumn(name = "address_id")
    )
    private Set<Address> addresses;
	
    @OneToOne(cascade= CascadeType.ALL)
    @JoinTable(name = "sublocation_user",
    		   joinColumns = @JoinColumn(name="sublocation_id"),
    		   inverseJoinColumns = @JoinColumn(name="user_id")
    )
	private User ca;
    
	public Sublocation(String sublocation) {
		
		this.sublocation = sublocation;
		addresses = new HashSet<>();;
	}
	
	public String getSublocation() {
		
		return sublocation;
	}
	
	public void addAddress(Address address) {
		
		System.out.println("DEEPER: " + address);
		System.out.println(addresses);
		addresses.add(address);
		System.out.println(addresses);
	}
	
	@Override
    public String toString() {
        return "Sublocation [sublocation_id=" + sublocation_id + ", sublocation=" + sublocation + "]";
    }
    
	
}