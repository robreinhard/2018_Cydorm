package web;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;



/**
 * The Class AdminController.
 */
@Controller
public class AdminController {

    /** The user service. */
    @Autowired
    private UserService userService;
    
    /** The residency service. */
    @Autowired
    private ResidencyService residencyService;
    

    /**
     * Admin.
     *
     * @return the model and view
     */
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
    
    @RequestMapping(value="/admin/userManager", method = RequestMethod.GET)
    public ModelAndView userManager(){
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByNetID(auth.getName());
        modelAndView.addObject("userName", user.getFirstName());
        Residency residency = new Residency();
        modelAndView.addObject("residency", residency);
        modelAndView.setViewName("admin/userManager");
        return modelAndView;
    }

    /**
     * Creates the residency.
     *
     * @param residency the residency
     * @param bindingResult the binding result
     * @return the model and view
     */
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
    
    /**
     * Admin 2.
     *
     * @return the model and view
     */
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
    
    /**
     * Sets the user.
     *
     * @param residency the residency
     * @param bindingResult the binding result
     * @return the model and view
     */
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
    
    /**
     * Assign CA get.
     *
     * @return the model and view
     */
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
    
    /**
     * Assign CA post.
     *
     * @param residency the residency
     * @param bindingResult the binding result
     * @return the model and view
     */
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
    
    @GetMapping("/allUsers")
	public @ResponseBody String getAllItems() throws JSONException{
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByNetID(auth.getName()); 
        System.out.println("THIS WORKS: "+user);
		return userService.allUsers();
	}
    
    @RequestMapping(value = "/admin/userManager", method = RequestMethod.POST)
    public ModelAndView ass(@RequestParam("file") MultipartFile file, ModelMap modelMap) throws IOException {
    	
        ModelAndView modelAndView = new ModelAndView();
        modelMap.addAttribute("file", file);

        String extension = "";
        File theFile = new File(file.getOriginalFilename());
        theFile.createNewFile();
        FileOutputStream fos = new FileOutputStream(theFile);
        fos.write(file.getBytes());
        fos.close();
        
        int i = theFile.getName().lastIndexOf('.');
        if (i > 0) {
            extension = theFile.getName().substring(i+1);
        }        
        System.out.println("FILE SUCCESSULLY UPLOADED: HERE IS EXTENSION: " + extension);
        
        Scanner scanner1 = new Scanner(theFile);
        while (scanner1.hasNextLine()) {
        	
        	Scanner scanner2 = new Scanner(scanner1.nextLine());
        	int j = 0; 
        	String parameters[] = new String[5];
    		boolean shouldAdd = true;

        	while (scanner2.hasNext()) {
        		
        		parameters[j] = scanner2.next();
            	if (parameters[j].length() == 0) {
            		
            		shouldAdd = false;
            	}
        		j++;

        	}
        	
        	if (shouldAdd) {
        		
            	userService.saveUser(new User(parameters[0],parameters[1],parameters[2],parameters[3]), parameters[4]); 

        	}
        	
            scanner2.close();

        }
        
        scanner1.close();
        
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();      
        User user = userService.findUserByNetID(auth.getName());
        modelAndView.addObject("userName", user.getFirstName());
        modelAndView.setViewName("admin/userManager");
        return new ModelAndView("redirect:/admin/userManager");
    }
}
