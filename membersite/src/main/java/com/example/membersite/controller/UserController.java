package com.example.membersite.controller;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.membersite.model.form.UserForm;
import com.example.membersite.model.mapper.UserMapper;

@Controller
public class UserController {
	
	@Autowired
	private UserMapper userMapper;

	@RequestMapping("/membersite/user")
	public String index() {
		return "register_user";
	}
	
	@RequestMapping(value = "/membersite/register", method = RequestMethod.POST)
	public String register(@ModelAttribute("userForm") UserForm form,
			Model model) {
		int year_birth = form.getYear();
		int month_birth = form.getMonth();
		int day_birth = form.getDay();
		
		Calendar calendar = Calendar.getInstance();
		int year_today = calendar.get(Calendar.YEAR);
		int month_today = calendar.get(Calendar.MONTH) + 1;
		int day_today = calendar.get(Calendar.DAY_OF_MONTH) + 1;
		
		int age= year_today - year_birth;
		if(month_today < month_birth) {
			-- age;
		} else if (month_today == month_birth) {
				if(day_birth < day_today) {
					-- age;
				}
			}
		
		//TODO DTOにつめる
		int count = userMapper.insert(form.getId(),form.getPassword(),form.getName(),age,form.getMailaddress());
		if(count > 0) {
			return "index";
		} else {
			//TODO アラート
			return "register_user";
		}
	}
	
	@ResponseBody
	@RequestMapping("/membersite/checkId")
	public boolean checkId(@RequestBody String ID) {
		int count = userMapper.findById(ID);
		return count > 0;
	}
}
