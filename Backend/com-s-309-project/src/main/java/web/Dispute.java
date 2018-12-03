package web;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonSubTypes.Type;

import javax.persistence.Column;

/**
 * The Class Dispute.
 */
@Entity
@Table(name="disputes")
public class Dispute {
	
	/**
	 * Instantiates a new dispute.
	 */
	public Dispute() {
		
		
	}
	
	/** The dispute id. */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int dispute_id;
	
	/** The dispute name. */
	@Column(name="disputeName")
	private String disputeName;
	
	/** The resolved. */
	@Column(name="resolved")
	private char resolved;
	
	/** The visability. */
	@Column(name="visability")
	private char visability;
	
	/** The student ID. */
	@Column(name="studentID")
	private String studentID;
	
	/** The dispute body. */
	@Column(name="disputeBody", length=65535, columnDefinition="text")
	private String disputeBody;
	
	
	
	/**
	 * Instantiates a new dispute.
	 *
	 * @param disputeName the dispute name
	 * @param studentID the student ID
	 * @param disputeBody the dispute body
	 */
	public Dispute(String disputeName, String studentID, String disputeBody) {
		this.disputeName = disputeName;
		this.studentID = studentID;
		this.disputeBody = disputeBody;
		this.visability = 'F';
		this.resolved = 'F';
	}
	
	/**
	 * Gets the visability.
	 *
	 * @return the visability
	 */
	public char getVisability() {
		
		return visability;
	}
	
	/**
	 * Sets the visability.
	 *
	 * @param visability the new visability
	 */
	public void setVisability(char visability) {
		
		this.visability = visability;
	}
	
	/**
	 * Gets the resolved.
	 *
	 * @return the resolved
	 */
	public char getResolved() {
		
		return resolved;
		
	}
	
	/**
	 * Sets the resolved.
	 *
	 * @param resolved the new resolved
	 */
	public void setResolved(char resolved) {
		this.resolved = resolved;
	}
	
	/**
	 * Gets the student ID.
	 *
	 * @return the student ID
	 */
	public String getStudentID() {
		return studentID;
	}
	
	/**
	 * Sets the student ID.
	 *
	 * @param studentID the new student ID
	 */
	public void setStudentID(String studentID) {
		
		this.studentID = studentID;
	}
	
	/**
	 * Gets the dispute name.
	 *
	 * @return the dispute name
	 */
	public String getDisputeName() {
		return disputeName;
	}
	
	/**
	 * Sets the dispute name.
	 *
	 * @param disputeName the new dispute name
	 */
	public void setDisputeName(String disputeName) {
		
		this.disputeName = disputeName;
	}
	
	/**
	 * Gets the dispute body.
	 *
	 * @return the dispute body
	 */
	public String getDisputeBody() {
		return disputeBody;
	}
	
	/**
	 * Sets the dispute body.
	 *
	 * @param disputeBody the new dispute body
	 */
	public void setDisputeBody(String disputeBody) {
		
		this.disputeBody = disputeBody;
	}
}
