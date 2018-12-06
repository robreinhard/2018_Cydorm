package web;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;



import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;


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
    
    public String allUsers() throws JSONException {
    	
    
        	
    	JSONArray array = new JSONArray();
    	Collection<User> theUsers = userRepository.findAll();
    	Iterator<User> iterator = theUsers.iterator();
    	
    	while (iterator.hasNext() ) {
    		JSONObject user = new JSONObject();
    		
    		User thisUser = iterator.next();
        	user.put("user_id", thisUser.getId());
        	user.put("firstName", thisUser.getFirstName());
        	user.put("lastName", thisUser.getLastName());
        	user.put("netID", thisUser.getNetID());
        	user.put("role", thisUser.getRole().toString());
        	try {
        		System.out.println("LOCATION: " + thisUser.getAddress().getSublocation().getLocation().getLocation());
        		user.put("location", thisUser.getAddress().getSublocation().getLocation().getLocation());
        		
        	}
        	catch (NullPointerException e ) {
        		
            	user.put("location","");

        	}
        	try {
        		
            	user.put("sublocation", thisUser.getAddress().getSublocation().getSublocation());

        	}
        	catch (NullPointerException e) {
        		
            	user.put("sublocation","");

        	}
        	try {
        	user.put("address", thisUser.getAddress().getAddress());
        	}
        	catch (NullPointerException e) {
        		
        		 user.put("address","");
        	}

        	array.put(user);
    	}
    	
    	return array.toString();
    	
      
    }

}