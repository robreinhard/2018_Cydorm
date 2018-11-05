package web;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("disputeService")
public class DisputeService {

    private AddressRepository addressRepository;
    private DisputeRepository disputeRepository;

    @Autowired
    public DisputeService(AddressRepository addressRepository, DisputeRepository disputeRepository) {
        this.addressRepository = addressRepository;
        this.disputeRepository = disputeRepository;
     
    }

    public void saveDispute(Dispute dispute) {
    		disputeRepository.save(dispute);  	
    }

    public Dispute findDisputeByID(int dispute_id) {
    	
    	return disputeRepository.findById(dispute_id);
    }
    
    public void saveAddressDispute(Address address, Dispute dispute) {
    	
    	address.addDispute(dispute);
    	addressRepository.save(address);
    }
    
    public void deleteDispute(Address address, Dispute dispute) {
    	
    	address.deleteDispute(dispute);
    	addressRepository.save(address);
    	disputeRepository.delete(dispute);
    }
    
}