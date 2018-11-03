package web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;

@Service("residencyService")
public class ResidencyService {

    private UserRepository userRepository;
    private LocationRepository locationRepository;
    private SublocationRepository sublocationRepository;
    private AddressRepository addressRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public ResidencyService(LocationRepository locationRepository, SublocationRepository sublocationRepository, AddressRepository addressRepository, UserRepository userRepository) {
    	
    	this.locationRepository = locationRepository;
    	this.sublocationRepository = sublocationRepository;
    	this.addressRepository = addressRepository;
        this.userRepository = userRepository;

    	
    }

    public User findUserByNetID(String netID) {
        return userRepository.findByNetID(netID);
    }
    
    public Location findLocation(String location) {
        return locationRepository.findByLocation(location);
    }
    
    public Sublocation findSublocation(String sublocation) {
        return sublocationRepository.findBySublocation(sublocation);
    }
    public Address findAddress(String address) {
        return addressRepository.findByAddress(address);
    }

    public void saveLocation(Location location, Sublocation sublocation) {
    	
    	location.addSublocation(sublocation);
        locationRepository.save(location);
    	
    }
    
    public void saveSublocation(Sublocation sublocation, Address address) {
    	
    	System.out.println("ADDRESS: " + address.toString());
    	sublocation.addAddress(address);
    	sublocationRepository.save(sublocation);
    	
    }
    
    
    public void saveAddress(Address address) {
    	
    	addressRepository.save(address);
    }

}