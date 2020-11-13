package com.example.membersite.controller;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.membersite.model.form.UserForm;
import com.example.membersite.model.mapper.UserMapper;

@Controller
@RequestMapping("/membersite/user")
public class UserController {
	
	@Autowired
	private UserMapper userMapper;

	@RequestMapping("/")
	public String index() {
		return "register_user";
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String register(@ModelAttribute("userForm") UserForm form,
			Model model) {
		//TODO 生年月日を年齢に直す
		int year_birth = form.getYear();
		int month_birth = form.getMonth();
		int day_birth = form.getDay();
		
		int year_today;
		int month_today;
		int day_today;
		
		Calendar calendar = Calendar.getInstance();
		year_today = calendar.get(Calendar.YEAR);
		month_today = calendar.get(Calendar.MONTH) + 1;
		day_today = calendar.get(Calendar.DAY_OF_MONTH) + 1;
		
		int age= year_today - year_birth;
		if(month_today < month_birth) {
			-- age;
		} else if (month_today == month_birth) {
				if(day_birth < day_today) {
					-- age;
				}
			}
		
		int result = userMapper.insert(form.getId(),form.getPassword(),form.getName(),form.getAge(),form.getMailaddress());
		if(result > 0) {
			return "index";
		} else {
			return "register_user";
		}
	}
}
