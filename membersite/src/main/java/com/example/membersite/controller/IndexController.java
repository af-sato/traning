package com.example.membersite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.membersite.model.dao.UserRepository;
import com.example.membersite.model.entity.User;
import com.example.membersite.model.form.UserForm;

@Controller
@RequestMapping("/membersite")
public class IndexController {
	
	@Autowired
	UserRepository userRepos;
	
	@RequestMapping("/")
	public String index() {
		return "index";
	}
	
	@RequestMapping("/login")
	public String login(
			@ModelAttribute("userForm") UserForm form,
			Model model) {
		
		List<User> user = userRepos.findByIdAndPassword(form.getId(), form.getPassword());
		if(user.size() > 0) {
			model.addAttribute("name", user.get(0).getName());
			model.addAttribute("user", user);
		} 
		return "my_page";
	}
}
