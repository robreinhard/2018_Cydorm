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
public class MainController {

    @Autowired
    private UserService userService;

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
        	
            userService.saveUser(new User(userRole.getFirstName(),userRole.getLastName(),userRole.getNetID(),userRole.getPassword()), userRole.getRole()); 
            modelAndView.addObject("successMessage", "User has been registered successfully");
            modelAndView.addObject("user", new User());
            modelAndView.setViewName("registration");
            
        }
        
        return modelAndView;
    }

    


}
