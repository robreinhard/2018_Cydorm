package web;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.Column;

@Entity
@Table(name = "Chores")
public class Chores {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int chores_id;
	
	@Column(name="chore")
	public String chore;
	@Column(name="completed")
	public char completed;
	@Column(name="studentID")
	public String studentID;
	
	public Chores(String chore, String studentID) {
		this.studentID = studentID;
		this.chore = chore;
	}
	
	public void setChore1(String chore) {
		this.chore = chore;
	}
	
}