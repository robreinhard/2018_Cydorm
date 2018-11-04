package web;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("choreService")
public class ChoreService {

    private UserRepository userRepository;
    private ChoreRepository choreRepository;

    @Autowired
    public ChoreService(UserRepository userRepository, ChoreRepository choreRepository) {
        this.userRepository = userRepository;
        this.choreRepository = choreRepository;
     
    }

    public void saveChore(Chores chore) {
    		choreRepository.save(chore);  	
    }

}
