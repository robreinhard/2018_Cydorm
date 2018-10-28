package web;
import javax.persistence.*;

@Entity
@Table(name = "Houses")
public class House {
	
	@Id
	private int id;
	
	private String name;
}
