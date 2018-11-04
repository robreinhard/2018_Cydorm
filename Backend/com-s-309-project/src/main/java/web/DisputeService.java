package web;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("disputeService")
public class DisputeService {

    private UserRepository userRepository;
    private DisputeRepository disputeRepository;

    @Autowired
    public DisputeService(UserRepository userRepository, DisputeRepository disputeRepository) {
        this.userRepository = userRepository;
        this.disputeRepository = disputeRepository;
     
    }

    public void saveDispute(Dispute dispute) {
    		disputeRepository.save(dispute);  	
    }

}