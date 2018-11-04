package web;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.Column;

@Entity
@Table(name="disputes")
public class Dispute {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int disputes_id;
	
	@Column(name="dispute")
	public String dispute;
	@Column(name="resolved")
	public char resolved;
	@Column(name="studentID")
	public String studentID;
	@Column(name="accusedID")
	public String accusedID;
	
	public Dispute(String dispute, String studentID, String accusedID) {
		this.dispute = dispute;
		this.studentID = studentID;
		this.accusedID = accusedID;
		setResolvedStatus('f');
	}
	
	public void setResolvedStatus(char resolved) {
		this.resolved = resolved;
	}
	
	public String getStudentID() {
		return studentID;
	}
	
	public String getAccusedID() {
		return accusedID;
	}
	
	public String getDispute() {
		return dispute;
	}
}
