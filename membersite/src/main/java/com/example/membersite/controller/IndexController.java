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
		
		List<User> users = userRepos.findByIdAndPassword(form.getId(), form.getPassword());
		if(users.size() > 0) {
			User user = users.get(0);
			model.addAttribute("name", user.getName());
			model.addAttribute("user", user);
		} 
		if(users.size() == 0){
			//TODO IDやpasswordが入力されていないor正しくないときはログイン画面のままにする
		}
		
		//この２文はDB検索がうまくいっている確認する文なので、のちに削除します
//		User user = users.get(0);
//		model.addAttribute("name", user.getName());
		
		return "my_page";
	}
	
	@RequestMapping("/logout")
	public String logout() {
		return "index";
	}
	
	@RequestMapping("/delete")
	public String delete() {
		//TODO DBからユーザ情報を削除
		return "index";
	}
}
