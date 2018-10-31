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

    public User findUserByEmail(String netID) {
        return userRepository.findByNetID(netID);
    }

    public void saveUser(User user, String role) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        if (role == "ADMIN") {
        	
            Role userRole = roleRepository.findByRole("ADMIN");

        }
        else if (role == "CA") {
        	
        	Role userRole = roleRepository.findByRole("CA");
        }
        else {
        	
        	Role userRole = roleRepository.findByRole("RESIDENT");

        }
        userRepository.save(user);
    }

}