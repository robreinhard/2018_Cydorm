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



/**
 * The Class MainController.
 */
@Controller
public class MainController {

    /** The user service. */
    @Autowired
    private UserService userService;
    
    

    /**
     * Login view.
     *
     * @return the model and view
     */
    @RequestMapping(value={"/login"}, method = RequestMethod.GET)
    public ModelAndView login(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }


    /**
     * Registration view.
     *
     * @return the model and view
     */
    @RequestMapping(value="/registration", method = RequestMethod.GET)
    public ModelAndView registration(){
        ModelAndView modelAndView = new ModelAndView();
        UserRole userRole = new UserRole();
        modelAndView.addObject("userRole", userRole);
        modelAndView.setViewName("registration");
        return modelAndView;
    }

    /**
     * Creates the new user.
     *
     * @param userRole the user role
     * @param bindingResult the binding result
     * @return the model and view
     */
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

    /**
     * Navbar.
     *
     * @return the model and view
     */
    @RequestMapping(value="/navbar", method = RequestMethod.GET)
    public ModelAndView navbar(){
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByNetID(auth.getName());
        modelAndView.addObject("userName", user.getFirstName());
        modelAndView.addObject("netID", user.getNetID());
        modelAndView.addObject("nextWeek", new NextWeek());
        modelAndView.setViewName("navbar");
        return modelAndView;
    }

    
    
    
}
