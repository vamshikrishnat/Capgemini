package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.bean.RegisterDTO;
import com.example.demo.repo.JPARepository;
import com.example.demo.service.UserDeatilsService;


@Controller
public class UserDetailsController {

	@Autowired
	UserDeatilsService userDetailsService;

	@Autowired
	JPARepository repo;

	@GetMapping("/")
	public String register(Model model) {
		return "Register";
	}

	@PostMapping("/registerSuccess")
	public String registerSuccess(@ModelAttribute RegisterDTO user, Model model) {
		System.err.println("Password : "+ user.getPassword());
		userDetailsService.registerUser(user);
		model.addAttribute("details", "Register Success");
		return "Login";
	}

	@GetMapping("/loginrequest")
	public String login(@ModelAttribute RegisterDTO user, Model model) {
		return "Login";
	}

	@PostMapping("/loginsuccess")
	public String loginTest(@RequestParam("name") String name, @RequestParam("password") String password, Model model) {
		
		boolean con = userDetailsService.login(name, password);
		if (con == true) {
			model.addAttribute("details", "Login Success");
			List<RegisterDTO> list = userDetailsService.getDetails();
			model.addAttribute("all", list);
			return "Home";
		}

		else {
			model.addAttribute("details", "Login Failed");
			return "Login";
		}
	}

	@GetMapping("/editrequest")
	public String edit(@RequestParam("name") String name, Model model) {
		RegisterDTO user = repo.getByName(name);
		model.addAttribute("user", user);
		return "Edit";
	}

	@PostMapping("/editsuccess")
	public String editUser(@ModelAttribute RegisterDTO user, Model model) {
		System.err.println(user.getName());
		userDetailsService.update(user);
		List<RegisterDTO> list = userDetailsService.getDetails();
		model.addAttribute("all", list);
		return "Home";
	}

	@GetMapping("/delete")
	public String deleteUser(@ModelAttribute RegisterDTO name, Model model) {
		userDetailsService.delete(name);
		System.err.println(name);
		List<RegisterDTO> list = userDetailsService.getDetails();
		model.addAttribute("all", list);
		return "Home";
	}

}
