package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.CyDormUser;
import com.example.demo.UserRepository;;

@Controller
public class MainControl {

	public MainControl() {
	}

	@Autowired
	private UserRepository userRepository;

	// Only get requests
	@GetMapping("/add")
	public @ResponseBody String addNewUser(@RequestParam String firstName, @RequestParam String lastName,
			@RequestParam String email, @RequestParam String password, @RequestParam int permLevel) {
		CyDormUser user = new CyDormUser(firstName, lastName, email, password, permLevel);
		userRepository.save(user);
		return "Saved.";
	}

	@GetMapping("/all")
	public @ResponseBody Iterable<CyDormUser> getAllUsers() {
		// This returns a JSON with the users
		return userRepository.findAll();
	}
}
