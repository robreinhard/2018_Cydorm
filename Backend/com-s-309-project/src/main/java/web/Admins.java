package web;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "admins")
public class Admins {
	 @Id
	    private int id;

	    private String name;

	    /*  One TA may have multiple teams assigned. So One-to-Many mapping. Eager fetching type is
	        to load together everything. LAZY fetch type is on-demand fetching. For simplicity, use EAGER.
	        @JoinColumn annotation is used here to join list of teams with id of the TA. So, Team table
	        will have a column called "id" corresponding to "id" of TA
	        it is associated to (Automatically created by SpringBoot).
	     */
	    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	    @JoinColumn(name = "admin_id")
	    private List<House> house;	//change to house

	    public int getId() {
	        return id;
	    }

	    public String getName() {
	        return name;
	    }

	    public List<House> getTeams() { //Change to house
	        return house;
	    }

}
