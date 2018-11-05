package web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;

@Service("userService")
public class UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserService(UserRepository userRepository,
                       RoleRepository roleRepository,
                       BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public User findUserByNetID(String netID) {
        return userRepository.findByNetID(netID);
    }

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
    
    public RoleRepository getRoleRepository() {
    	
    	return roleRepository;
    }

}