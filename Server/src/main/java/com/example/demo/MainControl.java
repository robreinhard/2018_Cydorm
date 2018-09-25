package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.CyDormUser;
import com.example.demo.RepositoryForUser;

@Controller
public class MainControl {
	@Autowired
	private RepositoryForUser userRepository;
	
	@GetMapping(path="/test")
	public String sayTest() {
		return "Test Passed!";
	}
	
	//Only get requests
	@GetMapping(path="/add")
	public @ResponseBody String addNewUser (@RequestParam String firstName, @RequestParam String lastName, 
			@RequestParam String email) {
		CyDormUser user = new CyDormUser(firstName, lastName, email);
		userRepository.save(user);
		return "Saved";
	}

	@GetMapping(path="/all")
	public @ResponseBody Iterable<CyDormUser> getAllUsers() {
		// This returns a JSON with the users
		return userRepository.findAll();
	}
}
