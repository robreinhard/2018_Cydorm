package web;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.Column;


@Entity
@Table(name="groceries")
public class Grocery {
	
	public Grocery() {
		
	}
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer grocery_id;
 
    @Column(name="groceryItem")
    private String groceryItem;
    @Column(name="groceryPrice")
    private String groceryPrice;
    @Column(name="approved")
    private char approved;
    @Column(name="studentID")
    private String studentID;
    
    
    public Grocery(String groceryItem, String groceryPrice, char approved, String studentID) {
    		this.studentID = studentID;
    		this.groceryItem = groceryItem;
    		this.groceryPrice = groceryPrice;
    		this.approved = 'F';		
    }
    
    
    public Integer getId() {
    		return grocery_id;
    }
    
    public void setId(Integer id) {
    		id = this.grocery_id;
    }
    
    public String getGroceryItem() {
    		return groceryItem;
    }
    
    public String getGroceryPrice() {
    		return groceryPrice;
    }
 
    public String getstudentID() {
    		return studentID;
    }
    
    public void setFirstName(String studentID) {
		this.studentID=studentID;
    }
   
    
    public void setApproval(char approved){
    		this.approved = approved;
    }
    
    public char getApproval() {
    		return approved;
    }
    
    
    @Override
    public String toString() {
        return "Grocery Item [id=" + grocery_id + ", Grocery Item: " + groceryItem
                + ", Grocery Price" + groceryPrice + ", Purchaser ID=" + studentID + "]";
    }
}