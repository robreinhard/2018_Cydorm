package web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainControl {

	public MainControl() {
	}

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private GroceryInterface groceryItem;

	// Only get requests
	@PostMapping("/addUser")
	public @ResponseBody String addNewUser(@RequestParam String firstName, @RequestParam String lastName,
			@RequestParam String email, @RequestParam int permLevel) {
		CyDormUser user = new CyDormUser(firstName, lastName, email, permLevel);
		userRepository.save(user);
		return "User created with the following information: \n"+
				"Firstname: "+firstName+ "\n"+
				"Lastname: "+lastName+"\n"+
				"Email Address: "+email+"\n"+
				"Permissions Level: "+ String.valueOf(permLevel) + "\n";
	}

	@GetMapping("/allUsers")
	public @ResponseBody Iterable<CyDormUser> getAllUsers() {
		// This returns a JSON with the users
		return userRepository.findAll();
	}
	
	@GetMapping("/addGroceryItem")
	public @ResponseBody String addItem(@RequestParam String groceryItem, @RequestParam String groceryPrice,
			@RequestParam char approved, @RequestParam String firstName, @RequestParam String lastName) {
		Grocery item = new Grocery(groceryItem, groceryPrice, approved, firstName, lastName);
		//item.setApproval(false); //force approval to false
		this.groceryItem.save(item);
		return "Grocery Item created with the following information: \n"+
		"Grocery Item: "+groceryItem+ "\n"+
		"Grocery Price: $"+groceryPrice+"\n"+
		"Is Approved: "+approved+"\n"+
		"Purchaser's Name: "+firstName+ " "+ lastName+ "\n";
	}
	
	@GetMapping("/allGroceries")
	public @ResponseBody Iterable<Grocery> getAllItems(){
		return this.groceryItem.findAll();
	}
	
	@GetMapping("/deleteGroceryItem")
	public @ResponseBody String deleteItem(@RequestParam int id) {
		Grocery toBeDeleted = this.groceryItem.findById(id);
		this.groceryItem.delete(toBeDeleted);
		return "Grocery Item deleted with the following id = " + id;
	}
}
