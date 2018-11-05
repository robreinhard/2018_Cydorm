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
public class CAController {

    @Autowired
    private UserService userService;
    
    @Autowired
    private ResidencyService residencyService;
    

    @RequestMapping(value="/ca/", method = RequestMethod.GET)
    public ModelAndView admin(){
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByNetID(auth.getName());
        modelAndView.addObject("userName", user.getFirstName());
        Residency residency = new Residency();
        modelAndView.addObject("residency", residency);
        modelAndView.setViewName("ca/caView");
        return modelAndView;
    }
    
    
    
}
