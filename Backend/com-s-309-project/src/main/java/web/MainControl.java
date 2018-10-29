package web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * The Class MainControl. This is used as the API that the client's
 * web browser/app uses to communicate with the Spring SQL database. 
 */
@Controller
public class MainControl {

	/**
	 * Instantiates a new main control.
	 */
	public MainControl() {
	}

	/** The user repository. */
	@Autowired
	private UserRepository userRepository;
	
	/** The grocery item. */
	@Autowired
	private GroceryInterface groceryItem;

	/**
	 * Adds the new user via POST request.
	 *
	 * @param firstName the first name
	 * @param lastName the last name
	 * @param email the email
	 * @param permLevel the permission level (1,2, or 3)
	 * @param studentID the student ID
	 * @return the newly added user
	 */
	@PostMapping("/addUser")
	public @ResponseBody String addNewUser(@RequestParam String firstName, @RequestParam String lastName,
			@RequestParam String email, @RequestParam int permLevel, @RequestParam String studentID) {
		CyDormUser user = new CyDormUser(firstName, lastName, email, permLevel, studentID);
		userRepository.save(user);
		return "User created with the following information: \n"+
				"Firstname: "+firstName+ "\n"+
				"Lastname: "+lastName+"\n"+
				"Email Address: "+email+"\n"+
				"Permissions Level: "+ String.valueOf(permLevel) + "\n";
	}

	/**
	 * Gets the all users.
	 *
	 * @return the all users in the database
	 */
	@GetMapping("/allUsers")
	public @ResponseBody Iterable<CyDormUser> getAllUsers() {
		// This returns a JSON with the users
		return userRepository.findAll();
	}
	
	/**
	 * Adds the item via POST request.
	 *
	 * @param groceryItem the grocery item
	 * @param groceryPrice the grocery price
	 * @param approved the status of the grocery item's approval
	 * @param studentID the purchaser's studentID
	 * @return the string
	 */
	@PostMapping("/addGroceryItem")
	public @ResponseBody String addItem(@RequestParam String groceryItem, @RequestParam String groceryPrice,
			@RequestParam char approved, @RequestParam String studentID) {
		Grocery item = new Grocery(groceryItem, groceryPrice, approved, studentID);
		//item.setApproval(false); //force approval to false
		this.groceryItem.save(item);
		return "Grocery Item created with the following information: \n"+
		"Grocery Item: "+groceryItem+ "\n"+
		"Grocery Price: $"+groceryPrice+"\n"+
		"Is Approved: "+approved+"\n"+
		"Purchaser's ID: " + studentID + "\n";
	}
	
	/**
	 * Gets all the grocery items.
	 *
	 * @return the all the grocery items
	 */
	@GetMapping("/allGroceries")
	public @ResponseBody Iterable<Grocery> getAllItems(){
		return this.groceryItem.findAll();
	}
	
	/**
	 * Delete grocery item.
	 *
	 * @param id the id
	 * @return the item deleted
	 */
	@PostMapping("/deleteGroceryItem")
	public @ResponseBody String deleteItem(@RequestParam int id) {
		Grocery toBeDeleted = this.groceryItem.findById(id);
		this.groceryItem.delete(toBeDeleted);
		return "Grocery Item deleted with the following id = " + id;
	}
	
	/**
	 * Edits the item.
	 *
	 * @param id the id
	 * @return the item edited
	 */
	@PostMapping("/editGroceryItem")
	public @ResponseBody String editItem(@RequestParam int id) {
		Grocery edit = this.groceryItem.findById(id);
		
		return "";
	}
	
	/**
	 * Adds a chore.
	 *
	 * @return the chore added
	 */
	@PostMapping("/addChore")
	public @ResponseBody String addChore() {
		
		return "NULL";
	}
}
