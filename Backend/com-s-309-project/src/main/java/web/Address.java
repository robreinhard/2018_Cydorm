package web;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "Address")
public class Address {
	
	public Address() {
		
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int address_id;
	
	@Column(name="address")
	private String address;
	
	//Address to user relationship
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval=true, fetch=FetchType.EAGER)
    @JoinTable(name = "address_grocery",
    		   joinColumns = @JoinColumn(name= "address_id"),
    		   inverseJoinColumns = @JoinColumn(name = "grocery_id")
    )
    private Set<Grocery> groceries;
	
	public Address(String address) {
		
		this.address = address;
		groceries = new HashSet<>();;

	}
	
	public String getAddress() {
		
		return address;
	}
	
	public void setAddress(String address) {
		
		this.address = address;
	}
	
	public Set<Grocery> getGroceries() {
		
		return groceries;
	}
	
	public void addGrocery(Grocery grocery) {
		
		groceries.add(grocery);
	}
	
	public void deleteGrocery(Grocery grocery) {
		
		groceries.remove(grocery);
		System.out.println(groceries);
	}
	
	@Override
    public String toString() {
        return "Address [address_id=" + address_id + ", address=" + address + "]";
    }
    
	
}
