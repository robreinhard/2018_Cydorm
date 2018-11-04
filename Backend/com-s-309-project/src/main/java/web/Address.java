package web;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "address")
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
	
	//Address has many chores
    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "address_chores",
    		   joinColumns = @JoinColumn(name= "address_id"),
    		   inverseJoinColumns = @JoinColumn(name = "chore_id")
    )
	public Set<Chores> chores; 
    
    public Set<Chores> getChores(){
		return chores;
	}
	
  //Address has many chores
    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "address_disputes",
    		   joinColumns = @JoinColumn(name= "address_id"),
    		   inverseJoinColumns = @JoinColumn(name = "dispute_id")
    )
	public Set<Dispute> dispute; 
    
    public Set<Dispute> getDispute(){
		return dispute;
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
