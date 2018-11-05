package web;


import javax.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;




@Controller
public class AdminController {

    @Autowired
    private UserService userService;
    
    @Autowired
    private ResidencyService residencyService;
    

    @RequestMapping(value="/admin/addResidency", method = RequestMethod.GET)
    public ModelAndView admin(){
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByNetID(auth.getName());
        modelAndView.addObject("userName", user.getFirstName());
        Residency residency = new Residency();
        modelAndView.addObject("residency", residency);
        modelAndView.setViewName("admin/addResidency");
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
        modelAndView.setViewName("admin/addResidency");
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
        modelAndView.setViewName("admin/setUserToResidency");
        return modelAndView;
    }
    
    @RequestMapping(value = "/admin/setUserToResidency", method = RequestMethod.POST)
    public ModelAndView setUser (@Valid Residency residency, BindingResult bindingResult) {
    	
    	System.out.println(residency.toString());
        ModelAndView modelAndView = new ModelAndView();
        if (residency.getNetID() != null) {
        	
        	 User rUser = userService.findUserByNetID(residency.getNetID());
             System.out.println(rUser.toString());
             Address isAddressValid = residencyService.findAddress(residency.getAddress());
             System.out.println(isAddressValid.toString());
             Sublocation isSublocationValid = residencyService.findSublocation(residency.getSublocation());
             System.out.println(isSublocationValid.toString());
             Location  isLocationValid = residencyService.findLocation(residency.getLocation());
             System.out.println(isLocationValid.toString());
             if (!(isAddressValid == null || isSublocationValid == null || isLocationValid == null)) {
                 
             	residencyService.setUserAddress(rUser, isAddressValid);
                 
             }
        	
        }
       
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();      
        User user = userService.findUserByNetID(auth.getName());
        modelAndView.addObject("userName", user.getFirstName());
        modelAndView.addObject("residency", residency);
        modelAndView.setViewName("admin/setUserToResidency");
        return modelAndView;
        
    }
    
    @RequestMapping(value = "/admin/assignCA", method = RequestMethod.GET)
    public ModelAndView assignCAGet(){
    	ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByNetID(auth.getName());
        modelAndView.addObject("userName", user.getFirstName());
        Residency residency = new Residency();
        modelAndView.addObject("residency", residency);
        modelAndView.setViewName("admin/assignCA");
        return modelAndView;
    }
    
    @RequestMapping(value = "/admin/assignCA", method = RequestMethod.POST)
    public ModelAndView assignCAPost(@Valid Residency residency, BindingResult bindingResult) {
    	
    	System.out.println(residency.toString());
        ModelAndView modelAndView = new ModelAndView();
        if (residency.getNetID() != null) {
        	
        	 User rUser = userService.findUserByNetID(residency.getNetID());
             System.out.println(rUser.toString());
             if ( rUser.getRole() == userService.getRoleRepository().findByRole("CA")) {
            	 System.out.println("RUNS");
            	 Address isAddressValid = residencyService.findAddress(residency.getAddress());
                 System.out.println(isAddressValid.toString());
                 Sublocation isSublocationValid = residencyService.findSublocation(residency.getSublocation());
                 System.out.println(isSublocationValid.toString());
                 Location  isLocationValid = residencyService.findLocation(residency.getLocation());
                 System.out.println(isLocationValid.toString());
                 if (!(isAddressValid == null || isSublocationValid == null || isLocationValid == null)) {
                     
                 	residencyService.setUserAddress(rUser, isAddressValid);
                    residencyService.setCASublocation(rUser, isSublocationValid);
                 }
             }
             
        	
        }
       
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();      
        User user = userService.findUserByNetID(auth.getName());
        modelAndView.addObject("userName", user.getFirstName());
        modelAndView.addObject("residency", residency);
        modelAndView.setViewName("admin/assignCA");
        return modelAndView;
    }
    
    
}
