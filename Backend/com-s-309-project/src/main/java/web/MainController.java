package web;

import java.util.Set;

import javax.persistence.Entity;
import javax.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


@Controller
public class MainController {

    @Autowired
    private UserService userService;
    
    @Autowired
    private ResidencyService residencyService;
    
    @Autowired
    private GroceryService groceryService;

    @RequestMapping(value={"/login"}, method = RequestMethod.GET)
    public ModelAndView login(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }


    @RequestMapping(value="/registration", method = RequestMethod.GET)
    public ModelAndView registration(){
        ModelAndView modelAndView = new ModelAndView();
        UserRole userRole = new UserRole();
        modelAndView.addObject("userRole", userRole);
        modelAndView.setViewName("registration");
        return modelAndView;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ModelAndView createNewUser(@Valid UserRole userRole, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();

        
        User userExists = userService.findUserByNetID(userRole.getNetID());
        if (userExists != null) {
            bindingResult
                    .rejectValue("netID", "error.user",
                            "There is already a user registered with the netID provided");
        }
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("registration");
        } else {
        	System.out.println(userRole.toString());
        	userService.saveUser(new User(userRole.getFirstName(),userRole.getLastName(),userRole.getNetID(),userRole.getPassword()), userRole.getRole()); 
            modelAndView.addObject("successMessage", "User has been registered successfully");
            modelAndView.addObject("user", new User());
            modelAndView.setViewName("registration");
            
        }
        
        return modelAndView;
    }

    @RequestMapping(value="/navbar", method = RequestMethod.GET)
    public ModelAndView navbar(){
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByNetID(auth.getName());
        modelAndView.addObject("userName", user.getFirstName());
        modelAndView.addObject("netID", user.getNetID());
        modelAndView.setViewName("/navbar");
        return modelAndView;
    }

    @RequestMapping(value="/admin/addResidency", method = RequestMethod.GET)
    public ModelAndView admin(){
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByNetID(auth.getName());
        modelAndView.addObject("userName", user.getFirstName());
        Residency residency = new Residency();
        Residency userResidency = new Residency();
        modelAndView.addObject("residency", residency);
        modelAndView.setViewName("/admin/addResidency");
        return modelAndView;
    }
    
    

    @RequestMapping(value = "/admin/addResidency", method = RequestMethod.POST)
    public ModelAndView createResidency (@Valid Residency residency, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        System.out.println(residency.getAddress());
        System.out.println(residency.getSublocation());
        System.out.println(residency.getLocation());


        Address address = residencyService.findAddress(residency.getAddress());
        if (address == null) {
           
        	address = new Address(residency.getAddress());
        	residencyService.saveAddress(address);
        	
        }
        Sublocation sublocation = residencyService.findSublocation(residency.getSublocation());
        if (sublocation == null) {
        	
        	sublocation = new Sublocation(residency.getSublocation());
        	System.out.println(residencyService.findAddress(residency.getAddress()));
        	System.out.println(address.toString());
        	
        }
    	residencyService.saveSublocation(sublocation,residencyService.findAddress(residency.getAddress()));
        Location location = residencyService.findLocation(residency.getLocation());
        if (location == null) {
        	
        	location = new Location(residency.getLocation());
        }
    	residencyService.saveLocation(location,residencyService.findSublocation(residency.getSublocation()));

    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByNetID(auth.getName());
    	modelAndView.addObject("userName", user.getFirstName());
        modelAndView.addObject("residency", residency);
        modelAndView.setViewName("/admin/addResidency");
        return modelAndView;
        
       
    }
    
    @RequestMapping(value="/admin/setUserToResidency", method = RequestMethod.GET)
    public ModelAndView admin2(){
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByNetID(auth.getName());
        modelAndView.addObject("userName", user.getFirstName());
        Residency residency = new Residency();
        modelAndView.addObject("residency", residency);
        modelAndView.setViewName("/admin/setUserToResidency");
        return modelAndView;
    }
    
    @RequestMapping(value = "/admin/setUserToResidency", method = RequestMethod.POST)
    public ModelAndView setUser (@Valid Residency residency, BindingResult bindingResult) {
    	
    	System.out.println(residency.toString());
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByNetID(auth.getName());
        System.out.println(user.toString());
        Address isAddressValid = residencyService.findAddress(residency.getAddress());
        System.out.println(isAddressValid.toString());
        Sublocation isSublocationValid = residencyService.findSublocation(residency.getSublocation());
        System.out.println(isSublocationValid.toString());
        Location  isLocationValid = residencyService.findLocation(residency.getLocation());
        System.out.println(isLocationValid.toString());
        if (!(isAddressValid == null || isSublocationValid == null | isLocationValid == null)) {
            
        	residencyService.setUserAddress(user, isAddressValid);
            
        }
        
       
        modelAndView.addObject("userName", user.getFirstName());
        modelAndView.addObject("residency", residency);
        modelAndView.setViewName("/admin/setUserToResidency");
        return modelAndView;
        
    }
    
    @MessageMapping("/addGroceryItem")
    @SendTo("/allGroceries")
    public Set<Grocery> addGroceryItem(Grocery grocery) throws Exception {

    	 System.out.println(grocery.toString());
    	 grocery.setApproval('F');
         User user = userService.findUserByNetID(grocery.getstudentID());
         groceryService.saveGrocery(grocery);
         System.out.println(grocery);
         groceryService.saveAddressGrocery(user.getAddress(), grocery);
         
         return user.getAddress().getGroceries();
    	
    }
    
    @MessageMapping("/dumpGrocery")
    @SendTo("/allGroceries")
    public Set<Grocery> dumpGrocery(String jsonData) throws Exception {

    	 ObjectMapper objectMapper = new ObjectMapper();
    	 JsonNode rootNode = objectMapper.readTree(jsonData);
    	 JsonNode netID = rootNode.path("netID");
         User user = userService.findUserByNetID(netID.asText());
         
         return user.getAddress().getGroceries();
    	
    }
    
    @MessageMapping("/deleteGroceryItem")
    @SendTo("/allGroceries")
    public Set<Grocery> deleteGrocery(String jsonData) throws Exception {
    	
    	ObjectMapper objectMapper = new ObjectMapper();
   	 	JsonNode rootNode = objectMapper.readTree(jsonData);
   	 	JsonNode netID = rootNode.path("netID");
   	 	User user = userService.findUserByNetID(netID.asText());
   	 	JsonNode groceryID = rootNode.path("grocery_id");
   	 	Grocery grocery = groceryService.findGroceryByID(groceryID.asInt());
   	 	groceryService.deleteGrocery(user.getAddress(),grocery);
   	 	user = userService.findUserByNetID(netID.asText());
   	 	return user.getAddress().getGroceries();
    	
    }
}
