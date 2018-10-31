package web;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "Address")
public class Address {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int address_id;
	
	@Column(name="address")
	private String address;
	
	//Address to user relationship
    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "address_grocery",
    		   joinColumns = @JoinColumn(name= "address_id"),
    		   inverseJoinColumns = @JoinColumn(name = "grocery_id")
    )
    private Set<Grocery> groceries;
	
	public Address(String location, String sublocation, String address) {
		
		this.address = address;
		
	}
	
	public void setAddress(String address) {
		
		this.address = address;
	}
	
	@Override
    public String toString() {
        return "Address [address_id=" + address_id + ", address=" + address + "]";
    }
    
	
}
