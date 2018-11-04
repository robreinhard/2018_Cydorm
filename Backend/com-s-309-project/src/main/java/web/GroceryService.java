package web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;

@Service("groceryService")
public class GroceryService {

    private UserRepository userRepository;
    private GroceryRepository groceryRepository;

    @Autowired
    public GroceryService(UserRepository userRepository,
                       GroceryRepository groceryRepository) {
        this.userRepository = userRepository;
        this.groceryRepository = groceryRepository;
     
    }

    public void saveGrocery(Grocery grocery) {
    	
    	groceryRepository.save(grocery);
    	
    }

}
