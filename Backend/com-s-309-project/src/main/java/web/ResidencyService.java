package web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;

/**
 * The Class ResidencyService.
 */
@Service("residencyService")
public class ResidencyService {

    /** The user repository. */
    private UserRepository userRepository;
    
    /** The location repository. */
    private LocationRepository locationRepository;
    
    /** The sublocation repository. */
    private SublocationRepository sublocationRepository;
    
    /** The address repository. */
    private AddressRepository addressRepository;
    
    /** The b crypt password encoder. */
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * Instantiates a new residency service.
     *
     * @param locationRepository the location repository
     * @param sublocationRepository the sublocation repository
     * @param addressRepository the address repository
     * @param userRepository the user repository
     */
    @Autowired
    public ResidencyService(LocationRepository locationRepository, SublocationRepository sublocationRepository, AddressRepository addressRepository, UserRepository userRepository) {
    	
    	this.locationRepository = locationRepository;
    	this.sublocationRepository = sublocationRepository;
    	this.addressRepository = addressRepository;
        this.userRepository = userRepository;

    	
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
     * Find location.
     *
     * @param location the location
     * @return the location
     */
    public Location findLocation(String location) {
        return locationRepository.findByLocation(location);
    }
    
    /**
     * Find sublocation.
     *
     * @param sublocation the sublocation
     * @return the sublocation
     */
    public Sublocation findSublocation(String sublocation) {
        return sublocationRepository.findBySublocation(sublocation);
    }
    
    /**
     * Find address.
     *
     * @param address the address
     * @return the address
     */
    public Address findAddress(String address) {
        return addressRepository.findByAddress(address);
    }

    /**
     * Save location.
     *
     * @param location the location
     * @param sublocation the sublocation
     */
    public void saveLocation(Location location, Sublocation sublocation) {
    	
    	sublocation.setLocation(location);
        sublocationRepository.save(sublocation);
    	
    }
    
    /**
     * Save sublocation.
     *
     * @param sublocation the sublocation
     * @param address the address
     */
    public void saveSublocation(Sublocation sublocation, Address address) {
    	
    	System.out.println("ADDRESS: " + address.toString());
    	address.setSublocation(sublocation);
    	addressRepository.save(address);
    	
    }
    
    
    /**
     * Save address.
     *
     * @param address the address
     */
    public void saveAddress(Address address) {
    	
    	addressRepository.save(address);
    }
    
    /**
     * Sets the user address.
     *
     * @param user the user
     * @param address the address
     */
    public void setUserAddress(User user, Address address) {
    	
    	user.setAddress(address);
    	userRepository.save(user);
    	
    }
    
    /**
     * Sets the CA sublocation.
     *
     * @param user the user
     * @param sublocation the sublocation
     */
    public void setCASublocation(User user, Sublocation sublocation) {
    	
    	System.out.println("setCASublocation Runs");
    	sublocation.setCA(user);
    	userRepository.save(user);
    	
    }

}