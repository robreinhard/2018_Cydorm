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
    private Integer id;
 
    @Column
    private String groceryItem;
    @Column
    private String groceryPrice;
    @Column
    private char approved;
    @Column
    private String studentID;
    
    
    public Grocery(String groceryItem, String groceryPrice, char approved, String studentID) {
    		this.studentID = studentID;
    		this.groceryItem = groceryItem;
    		this.groceryPrice = groceryPrice;
    		this.approved = 'F';		//Newly created item needs approval for billing
    }
    
    
    public Integer getId() {
    		return id;
    }
    
    public void setId(Integer id) {
    		id = this.id;
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
        return "Grocery Item [id=" + id + ", Grocery Item: " + groceryItem
                + ", Grocery Price" + groceryPrice + ", Purchaser ID=" + studentID + "]";
    }
}