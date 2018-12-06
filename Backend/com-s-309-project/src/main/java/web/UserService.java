package web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;


/**
 * The Class UserService.
 */
@Service("userService")
public class UserService {

    /** The user repository. */
    private UserRepository userRepository;
    
    /** The role repository. */
    private RoleRepository roleRepository;
    
    /** The b crypt password encoder. */
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * Instantiates a new user service.
     *
     * @param userRepository the user repository
     * @param roleRepository the role repository
     * @param bCryptPasswordEncoder the b crypt password encoder
     */
    @Autowired
    public UserService(UserRepository userRepository,
                       RoleRepository roleRepository,
                       BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    /**
     * Find user by net ID.
     *
     * @param netID the net ID
     * @return the user
     */
    public User findUserByNetID(String netID) {
        return userRepository.findByNetID(netID);
    }

    /**
     * Save user.
     *
     * @param user the user
     * @param role the role
     */
    public void saveUser(User user, String role) {
    	
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(1);
        
        if (role.equals("ADMIN")) {
        	
            user.setRole(roleRepository.findByRole("ADMIN"));

        }
        else if (role.equals("CA")) {
        	
            user.setRole(roleRepository.findByRole("CA"));
        }
        else {
        	
            user.setRole(roleRepository.findByRole("RESIDENT"));

        }
        
        userRepository.save(user);
    }
    
    /**
     * Gets the role repository.
     *
     * @return the role repository
     */
    public RoleRepository getRoleRepository() {
    	
    	return roleRepository;
    }

}