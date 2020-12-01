package com.example.membersite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.membersite.model.domain.User;
import com.example.membersite.model.dto.LoginResultDto;
import com.example.membersite.model.form.UserForm;
import com.example.membersite.model.service.IndexService;
import com.example.membersite.model.session.LoginSession;

@Controller
@RequestMapping("/membersite")
public class IndexController {
	
	@Autowired
	private IndexService indexService;
	
	@Autowired
	private LoginSession loginSession;
	
	@RequestMapping("/")
	public String index(Model model) {
		if(loginSession.getId() == null) {
			model.addAttribute("loginErrorMessage",loginSession.getMessage());
			return "index";
		} else {
			String id = loginSession.getId();
			User user = indexService.searchUser(id);
			model.addAttribute("user", user);
			return "my_page";
		}
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(
			@ModelAttribute("userForm") UserForm userForm,
			Model model) {
		
		//TODO　helperクラス
		String id = userForm.getId();
		String password = userForm.getPassword();
		LoginResultDto loginResultDto = indexService.loginCheck(id, password);
		loginSession.setId(loginResultDto.getId());
		//TODO メッセージ用に別にオブジェクトをつくる
		loginSession.setMessage(loginResultDto.getMessage());
		return "forward:/membersite/";
	}
	
	@RequestMapping("/logout")
	public String logout() {
		loginSession.setId(null);
		return "index";
	}
	
	@RequestMapping("/delete")
	public String delete(Model model) {
		String id = loginSession.getId();
		String deleteErrorMessage = indexService.deleteCheck(id);
		if(deleteErrorMessage.isEmpty()) {
			return "index";
		} else {
			model.addAttribute("deleteErrorMessage", deleteErrorMessage);
			return "my_page";
		}
	}
}
