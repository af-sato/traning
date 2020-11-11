package com.example.membersite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.membersite.model.dao.UserRepository;
import com.example.membersite.model.entity.User;
import com.example.membersite.model.form.UserForm;
import com.example.membersite.model.session.LoginSession;

@Controller
@RequestMapping("/membersite")
public class IndexController {
	
	@Autowired
	private LoginSession loginSession;
	
	@Autowired
	UserRepository userRepos;
	
	@RequestMapping("/")
	public String index() {
		if(loginSession.isLogined()) {
			//TODO 会員画面へ
		}
		return "index";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(
			@ModelAttribute("userForm") UserForm form,
			Model model) {
		
		List<User> users = userRepos.findByIdAndPassword(form.getId(), form.getPassword());
		if(users.size() > 0) {
			User user = users.get(0);
			loginSession.setId(user.getId());
			loginSession.setPassword(user.getPassword());
			model.addAttribute("name", user.getName());
			model.addAttribute("user", user);
		} 
		if(users.size() == 0){
			loginSession.setId(null);
			loginSession.setPassword(null);
			//TODO IDやpasswordが入力されていないor正しくないときはログイン画面のままにする
		}
		
		//この２文はDB検索がうまくいっている確認する文なので、のちに削除します
//		User user = users.get(0);
//		model.addAttribute("name", user.getName());
		
		return "my_page";
	}
	
	@RequestMapping("/logout")
	public String logout() {
		loginSession.setId(null);
		loginSession.setPassword(null);
		return "index";
	}
	
	@RequestMapping("/delete")
	public String delete() {
		//TODO DBからユーザ情報を削除
		return "index";
	}
}
