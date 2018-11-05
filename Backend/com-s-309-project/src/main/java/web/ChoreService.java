package web;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("choreService")
public class ChoreService {

    private AddressRepository addressRepository;
    private ChoreRepository choreRepository;

    @Autowired
    public ChoreService(AddressRepository addressRepository, ChoreRepository choreRepository) {
        this.addressRepository = addressRepository;
        this.choreRepository = choreRepository;
     
    }

    public void saveChore(Chore chore) {
    		choreRepository.save(chore);  	
    }

    public Chore findChoreByID(int chore_id) {
    	
    	return choreRepository.findById(chore_id);
    }
    
    public void saveAddressChore(Address address, Chore chore) {
    	
    	address.addChore(chore);
    	addressRepository.save(address);
    	
    }
    
    public void deleteChore(Address address, Chore chore) {
    	
    	address.deleteChore(chore);
    	addressRepository.save(address);
    	choreRepository.delete(chore);
    	
    }
    
}
