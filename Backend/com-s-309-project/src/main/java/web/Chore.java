package web;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import java.util.Date;

import javax.persistence.Column;

@Entity
@Table(name = "chore")
public class Chore {
	
	public Chore() {
		
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int chore_id;
	@Column(name="chore")
	public String chore;
	@Column(name="completed")
	public char completed;
	@Column(name="studentID")
	public String studentID;
	@Temporal(TemporalType.DATE)
	private java.util.Date dueDate;
	
	public Chore(String chore, String studentID, Date dueDate) {
		this.studentID = studentID;
		this.chore = chore;
		this.dueDate = dueDate;
	}
	
	public Long getDueDate() {
		
		return dueDate.getTime();
	}
	
	public void setDueDate(Date dueDate) {
		
		this.dueDate = dueDate;
	}
	
	public String getChore() {
		
		return chore;
	}
	
	public void setChore(String chore) {
		this.chore = chore;
	}
	
	public char getCompleted() {
		
		return completed;
	}
	
	public void setCompleted(char newComplete) {
		
		this.completed = newComplete;
	}
	
	public String getStudentID() {
		
		return studentID;
	}
	
	public void setStudentID(String studentID) {
		
		this.studentID = studentID;
	}
	
	@Override
	public String toString() {
		
		return "Chore Item [chore_id=" + chore_id + ", chore= " + chore
				+ ", completed=  " + completed + ", date:" + dueDate.getTime() + ", studentID=" + studentID + "]";
	}
	
}