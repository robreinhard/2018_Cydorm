package web;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The Class ChoreService.
 */
@Service("choreService")
public class ChoreService {

    /** The address repository. */
    private AddressRepository addressRepository;
    
    /** The chore repository. */
    private ChoreRepository choreRepository;

    /**
     * Instantiates a new chore service.
     *
     * @param addressRepository the address repository
     * @param choreRepository the chore repository
     */
    @Autowired
    public ChoreService(AddressRepository addressRepository, ChoreRepository choreRepository) {
        this.addressRepository = addressRepository;
        this.choreRepository = choreRepository;
     
    }

    /**
     * Save chore.
     *
     * @param chore the chore
     */
    public void saveChore(Chore chore) {
    		choreRepository.save(chore);  	
    }

    /**
     * Find chore by ID.
     *
     * @param chore_id the chore id
     * @return the chore
     */
    public Chore findChoreByID(int chore_id) {
    	
    	return choreRepository.findById(chore_id);
    }
    
    /**
     * Save address chore.
     *
     * @param address the address
     * @param chore the chore
     */
    public void saveAddressChore(Address address, Chore chore) {
    	
    	address.addChore(chore);
    	addressRepository.save(address);
    	
    }
    
    /**
     * Delete chore.
     *
     * @param address the address
     * @param chore the chore
     */
    public void deleteChore(Address address, Chore chore) {
    	
    	address.deleteChore(chore);
    	addressRepository.save(address);
    	choreRepository.delete(chore);
    	
    }
    
}
