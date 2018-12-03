package web;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The Class DisputeService.
 */
@Service("disputeService")
public class DisputeService {

    /** The address repository. */
    private AddressRepository addressRepository;
    
    /** The dispute repository. */
    private DisputeRepository disputeRepository;

    /**
     * Instantiates a new dispute service.
     *
     * @param addressRepository the address repository
     * @param disputeRepository the dispute repository
     */
    @Autowired
    public DisputeService(AddressRepository addressRepository, DisputeRepository disputeRepository) {
        this.addressRepository = addressRepository;
        this.disputeRepository = disputeRepository;
     
    }

    /**
     * Save dispute.
     *
     * @param dispute the dispute
     */
    public void saveDispute(Dispute dispute) {
    		disputeRepository.save(dispute);  	
    }

    /**
     * Find dispute by ID.
     *
     * @param dispute_id the dispute id
     * @return the dispute
     */
    public Dispute findDisputeByID(int dispute_id) {
    	
    	return disputeRepository.findById(dispute_id);
    }
    
    /**
     * Save address dispute.
     *
     * @param address the address
     * @param dispute the dispute
     */
    public void saveAddressDispute(Address address, Dispute dispute) {
    	
    	address.addDispute(dispute);
    	addressRepository.save(address);
    }
    
    /**
     * Delete dispute.
     *
     * @param address the address
     * @param dispute the dispute
     */
    public void deleteDispute(Address address, Dispute dispute) {
    	
    	address.deleteDispute(dispute);
    	addressRepository.save(address);
    	disputeRepository.delete(dispute);
    }
    
}