package web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;

@Service("groceryService")
public class GroceryService {

    private AddressRepository addressRepository;
    private GroceryRepository groceryRepository;

    @Autowired
    public GroceryService(AddressRepository addressRepository,
                       GroceryRepository groceryRepository) {
        this.addressRepository = addressRepository;
        this.groceryRepository = groceryRepository;
     
    }

    public void saveGrocery(Grocery grocery) {
    	
    	groceryRepository.save(grocery);
    }
    
    public Grocery findGroceryByID(int grocery_id) {
    	
        return groceryRepository.findById(grocery_id);
        
    }
    
    public void saveAddressGrocery(Address address, Grocery grocery) {
    	
    	address.addGrocery(grocery);
    	addressRepository.save(address);
    	
    }
    
    public void deleteGrocery(Address address, Grocery grocery) {
    	
    	address.deleteGrocery(grocery);
    	addressRepository.save(address);
    	groceryRepository.delete(grocery);
    	
    }

}
