package web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;

/**
 * The Class GroceryService.
 */
@Service("groceryService")
public class GroceryService {

    /** The address repository. */
    private AddressRepository addressRepository;
    
    /** The grocery repository. */
    private GroceryRepository groceryRepository;

    /**
     * Instantiates a new grocery service.
     *
     * @param addressRepository the address repository
     * @param groceryRepository the grocery repository
     */
    @Autowired
    public GroceryService(AddressRepository addressRepository,
                       GroceryRepository groceryRepository) {
        this.addressRepository = addressRepository;
        this.groceryRepository = groceryRepository;
     
    }

    /**
     * Save grocery.
     *
     * @param grocery the grocery
     */
    public void saveGrocery(Grocery grocery) {
    	
    	groceryRepository.save(grocery);
    }
    
    /**
     * Find grocery by ID.
     *
     * @param grocery_id the grocery id
     * @return the grocery
     */
    public Grocery findGroceryByID(int grocery_id) {
    	
        return groceryRepository.findById(grocery_id);
        
    }
    
    /**
     * Save address grocery.
     *
     * @param address the address
     * @param grocery the grocery
     */
    public void saveAddressGrocery(Address address, Grocery grocery) {
    	
    	address.addGrocery(grocery);
    	addressRepository.save(address);
    	
    }
    
    /**
     * Delete grocery.
     *
     * @param address the address
     * @param grocery the grocery
     */
    public void deleteGrocery(Address address, Grocery grocery) {
    	
    	address.deleteGrocery(grocery);
    	addressRepository.save(address);
    	groceryRepository.delete(grocery);
    	
    }

}
