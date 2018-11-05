package web;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonSubTypes.Type;

import javax.persistence.Column;

@Entity
@Table(name="disputes")
public class Dispute {
	
	public Dispute() {
		
		
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int dispute_id;
	
	@Column(name="disputeName")
	private String disputeName;
	@Column(name="resolved")
	private char resolved;
	@Column(name="visability")
	private char visability;
	@Column(name="studentID")
	private String studentID;
	@Column(name="disputeBody", length=65535, columnDefinition="text")
	private String disputeBody;
	
	
	
	public Dispute(String disputeName, String studentID, String disputeBody) {
		this.disputeName = disputeName;
		this.studentID = studentID;
		this.disputeBody = disputeBody;
		this.visability = 'F';
		this.resolved = 'F';
	}
	
	public char getVisability() {
		
		return visability;
	}
	
	public void setVisability(char visability) {
		
		this.visability = visability;
	}
	
	public char getResolved() {
		
		return resolved;
		
	}
	public void setResolved(char resolved) {
		this.resolved = resolved;
	}
	
	public String getStudentID() {
		return studentID;
	}
	
	public void setStudentID(String studentID) {
		
		this.studentID = studentID;
	}
	
	public String getDisputeName() {
		return disputeName;
	}
	
	public void setDisputeName(String disputeName) {
		
		this.disputeName = disputeName;
	}
	
	public String getDisputeBody() {
		return disputeBody;
	}
	
	public void setDisputeBody(String disputeBody) {
		
		this.disputeBody = disputeBody;
	}
}
