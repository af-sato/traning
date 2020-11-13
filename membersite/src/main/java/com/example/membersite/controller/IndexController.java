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
		if(loginSession.isLogined()) {
			//TODO　ログインしてるユーザーの情報が表示されるようにする
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
			loginSession.setLogined(true);
			model.addAttribute("user", loginSession);
			return "my_page";
		} else {
			return "index";
		}
	}
	
	@RequestMapping("/logout")
	public String logout() {
		loginSession.setLogined(false);
		return "index";
	}
	
	@RequestMapping("/delete")
	public String delete() {
		int result = userMapper.deletedById(loginSession.getId());
		if(result > 0) {
			return "index";
		} else {
			//TODO アラート
			return "my_page";
		}
	}
}
