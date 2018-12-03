package web;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.Column;


/**
 * The Class Grocery.
 */
@Entity
@Table(name="groceries")
public class Grocery {
	
	/**
	 * Instantiates a new grocery.
	 */
	public Grocery() {
		
	}
	
	/** The grocery id. */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer grocery_id;
 
    /** The grocery item. */
    @Column(name="groceryItem")
    private String groceryItem;
    
    /** The grocery price. */
    @Column(name="groceryPrice")
    private String groceryPrice;
    
    /** The approved. */
    @Column(name="approved")
    private char approved;
    
    /** The student ID. */
    @Column(name="studentID")
    private String studentID;
    
    
    
    
    /**
     * Instantiates a new grocery.
     *
     * @param groceryItem the grocery item
     * @param groceryPrice the grocery price
     * @param approved the approved
     * @param studentID the student ID
     */
    public Grocery(String groceryItem, String groceryPrice, char approved, String studentID) {
    		this.studentID = studentID;
    		this.groceryItem = groceryItem;
    		this.groceryPrice = groceryPrice;
    		this.approved = 'F';		
    }
    
    
    /**
     * Gets the id.
     *
     * @return the id
     */
    public Integer getId() {
    		return grocery_id;
    }
    
    /**
     * Sets the id.
     *
     * @param id the new id
     */
    public void setId(Integer id) {
    		id = this.grocery_id;
    }
    
    /**
     * Gets the grocery item.
     *
     * @return the grocery item
     */
    public String getGroceryItem() {
    		return groceryItem;
    }
    
    /**
     * Gets the grocery price.
     *
     * @return the grocery price
     */
    public String getGroceryPrice() {
    		return groceryPrice;
    }
 
    /**
     * Gets the student ID.
     *
     * @return the student ID
     */
    public String getstudentID() {
    		return studentID;
    }
    
    /**
     * Sets the first name.
     *
     * @param studentID the new first name
     */
    public void setFirstName(String studentID) {
		this.studentID=studentID;
    }
   
    
    /**
     * Sets the approval.
     *
     * @param approved the new approval
     */
    public void setApproval(char approved){
    		this.approved = approved;
    }
    
    /**
     * Gets the approval.
     *
     * @return the approval
     */
    public char getApproval() {
    		return approved;
    }
    
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    //This needs to be updated --Rob
    @Override
    public String toString() {
        return "Grocery Item [grocery_id=" + grocery_id + ", Grocery Item: " + groceryItem
                + ", Grocery Price: " + groceryPrice + ", Approved: " + approved + ", Purchaser ID=" + studentID + "]";
    }
}