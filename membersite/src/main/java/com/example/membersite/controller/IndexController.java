package com.example.membersite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.membersite.model.domain.User;
import com.example.membersite.model.form.UserForm;
import com.example.membersite.model.mapper.UserMapper;
import com.example.membersite.model.session.LoginSession;

@Controller
@RequestMapping("/membersite")
public class IndexController {
	
	@Autowired
	private LoginSession loginSession;
	
	@Autowired
	private UserMapper userMapper;
	
	@RequestMapping("/")
	public String index(Model model) {
		if(loginSession.isLoginFrag()) {
			model.addAttribute("user", loginSession);
			return "my_page";
		} else {
			return "index";
		}
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(
			@ModelAttribute("userForm") UserForm form,
			Model model) {
		
		List<User> users = userMapper.findByIdAndPassword(form.getId(), form.getPassword());
		if(users.size() > 0) {
			User user = users.get(0);
			loginSession.setId(user.getId());
			loginSession.setName(user.getName());
			loginSession.setAge(user.getAge());
			loginSession.setMailaddress(user.getMailaddress());
			loginSession.setLoginFrag(true);
		} 
		return "forward:/membersite/";
	}
	
	@RequestMapping("/logout")
	public String logout() {
		loginSession.setLoginFrag(false);
		return "index";
	}
	
	@RequestMapping("/delete")
	public String delete(Model model) {
		int count = userMapper.deletedById(loginSession.getId());
		if(count > 0) {
			return "index";
		} else {
			model.addAttribute("count", count);
			return "my_page";
		}
	}
}
