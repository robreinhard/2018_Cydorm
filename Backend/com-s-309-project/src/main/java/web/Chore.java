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

/**
 * The Class Chore.
 */
@Entity
@Table(name = "chore")
public class Chore {
	
	/**
	 * Instantiates a new chore.
	 */
	public Chore() {
		
	}
	
	/** The chore id. */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer chore_id;
	
	/** The chore. */
	@Column(name="chore")
	private String chore;
	
	/** The completed. */
	@Column(name="completed")
	private char completed;
	
	/** The student ID. */
	@Column(name="studentID")
	private String studentID;
	
	/** The due date. */
	@Temporal(TemporalType.DATE)
	private java.util.Date dueDate;
	
	/**
	 * Instantiates a new chore.
	 *
	 * @param chore the chore
	 * @param studentID the student ID
	 * @param dueDate the due date
	 */
	public Chore(String chore, String studentID, Date dueDate) {
		this.studentID = studentID;
		this.chore = chore;
		this.dueDate = dueDate;
	}
	
	/**
     * Gets the id.
     *
     * @return the id
     */
    public Integer getId() {
    		return chore_id;
    }
    
	/**
	 * Gets the due date.
	 *
	 * @return the due date
	 */
	public Long getDueDate() {
		
		return dueDate.getTime();
	}
	
	/**
	 * Sets the due date.
	 *
	 * @param dueDate the new due date
	 */
	public void setDueDate(Date dueDate) {
		
		this.dueDate = dueDate;
	}
	
	/**
	 * Gets the chore.
	 *
	 * @return the chore
	 */
	public String getChore() {
		
		return chore;
	}
	
    /**
     * Sets the id.
     *
     * @param id the new id
     */
    public void setId(Integer id) {
    		id = this.chore_id;
    }
    
	/**
	 * Sets the chore.
	 *
	 * @param chore the new chore
	 */
	public void setChore(String chore) {
		this.chore = chore;
	}
	
	/**
	 * Gets the completed.
	 *
	 * @return the completed
	 */
	public char getCompleted() {
		
		return completed;
	}
	
	/**
	 * Sets the completed.
	 *
	 * @param newComplete the new completed
	 */
	public void setCompleted(char newComplete) {
		
		this.completed = newComplete;
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
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		
		return "Chore Item [chore_id=" + chore_id + ", chore= " + chore
				+ ", completed=  " + completed + ", date:" + dueDate.getTime() + ", studentID=" + studentID + "]";
	}
	
}