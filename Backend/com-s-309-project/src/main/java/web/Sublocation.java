package web;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "Sublocation")
public class Sublocation {
	
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
		
	}
	
	public void setSublocation(String sublocation) {
		
		this.sublocation = sublocation;
	}
	
	@Override
    public String toString() {
        return "Sublocation [sublocation_id=" + sublocation_id + ", sublocation=" + sublocation + "]";
    }
    
	
}