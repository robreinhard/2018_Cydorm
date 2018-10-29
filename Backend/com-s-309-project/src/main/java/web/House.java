package web;
import javax.persistence.*;

@Entity
@Table(name = "Houses")
public class House {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column
	private String name;
	@Column
	private String resident1;
	@Column
	private String resident2;
	@Column
	private String resident3;
	@Column
	private String resident4;
	@Column
	private String chores;
	
	public House(String name, String resident1, String resident2, String resident3, String resident4) {
		this.name = name;
		this.resident1 = resident1;
		this.resident2 = resident2;
		this.resident3 = resident3;
		this.resident4 = resident4;
	}
	
	
	public void setChoreList(String chores) {
		this.chores = chores;
	}
	
	//This could be the start of a beautiful relationship
	public String chores() {
		return chores;
	}
}
