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
	
	@Column
	public String chore1 = "";
	@Column
	public String chore2 = "";
	@Column
	public String chore3 = "";
	@Column
	public String chore4 = "";
	@Column
	public String chore5 = "";
	@Column
	public String chore6 = "";
	@Column
	public String chore7 = "";
	@Column
	public String chore8 = "";
	
	public Chores() {
		
	}
	
	public void setChore1(String chore1) {
		this.chore1 = chore1;
	}
	
	public void setChore2(String chore2) {
		this.chore2 = chore2;
	}
	
	public void setChore3(String chore3) {
		this.chore3 = chore3;
	}
	
	public void setChore4(String chore4) {
		this.chore4 = chore4;
	}
	
	public void setChore5(String chore5) {
		this.chore5 = chore5;
	}
	
	public void setChore6(String chore6) {
		this.chore6 = chore6;
	}
	
	public void setChore7(String chore7) {
		this.chore7 = chore7;
	}
	
	public void setChore8(String chore8) {
		this.chore8 = chore8;
	}

}