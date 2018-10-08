package web;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.Column;


@Entity
@Table(name="groceries") //cyDormUsers
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
    private String firstName;
    @Column
    private String lastName;
    
    
    public Grocery(String groceryItem, String groceryPrice, char approved, String firstName, String lastName) {
    		this.firstName = firstName;
    		this.lastName=lastName;
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
 
    public String getFirstName() {
    		return firstName;
    }
    
    public void setFirstName(String firstName) {
		this.firstName = firstName;
    }
   
    
    public void setLastName(String lastName) {
		this.lastName = lastName;
    }
    
    public String getLastName() {
    		return lastName;
    }
    
    public void setApproval(char approved){
    		this.approved = approved;
    }
    
    public char getApproval() {
    		return approved;
    }
    
    
    @Override
    public String toString() {
    		String fullName = firstName + " " + lastName;
        return "Grocery Item [id=" + id + ", Grocery Item: " + groceryItem
                + ", Grocery Price" + groceryPrice + ", Purchaser Name=" + fullName + "]";
    }
}