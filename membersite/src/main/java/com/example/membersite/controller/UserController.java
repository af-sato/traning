package com.example.membersite.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/membersite/user")
public class UserController {

	@RequestMapping("/")
	public String index() {
		return "register_user";
	}
	
	@RequestMapping("/register")
	public String register() {
		//TODO 登録ボタン押下でユーザ情報をDBに登録
		return "index";
	}
}
