package com.example.membersite.controller;

import java.text.ParseException;
//import java.text.SimpleDateFormat;
import java.util.Calendar;
//import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.membersite.model.dto.RegisterDto;
import com.example.membersite.model.form.UserForm;
import com.example.membersite.model.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;

	@RequestMapping("/membersite/user")
	public String index() {
		return "register_user";
	}
	
	@ResponseBody
	@RequestMapping(value = "/membersite/user/checkId", method = RequestMethod.POST)
	public boolean checkId(@RequestBody String json) throws JsonMappingException, JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode jsonNode = objectMapper.readTree(json);
		String id = jsonNode.get("id").textValue();
		int count = userService.checkId(id);
		return count > 0;
	}
	
	@RequestMapping(value = "/membersite/register", method = RequestMethod.POST)
	public String register(@ModelAttribute("userForm") UserForm userForm,
			Model model) throws ParseException {
		int year_birth = userForm.getYear();
		int month_birth = userForm.getMonth();
		int day_birth = userForm.getDay();

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
		
//		//TODO serviceメソッドへ
//		//TODO 変数名直す
//		// 誕生日と現在日から年齢を算出
//		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//		Date birth_date = dateFormat.parse(year_birth + "-" + userForm.getMonth() + "-" + userForm.getDay());
//		// 誕生日のミリ秒を算出
//		long birth = birth_date.getTime();
//		
//		Date today_date = new Date();
//		// 現在日のミリ秒を算出
//		long today = today_date.getTime();
//		// 現在日と誕生日の差分のミリ秒算出
//		
//		// 一年間のミリ秒算出
//		long one_year = 1000 * 60 * 60 * 24 * 365;
//		
//		int age = (int) ((today - birth) / one_year);
//		
		//TODO helperクラスに移動させる
		RegisterDto registerDto = new RegisterDto();
		registerDto.setId(userForm.getId());
		registerDto.setPassword(userForm.getPassword());
		registerDto.setName(userForm.getName());
		registerDto.setAge(age);
		registerDto.setMailaddress(userForm.getMailaddress());
		
		String registerErrorMessage = userService.insert(registerDto);
		if(registerErrorMessage.isEmpty()) {
			return "index";
		} else {
			model.addAttribute("registerErrorMessage",registerErrorMessage);
			return "register_user";
		}
	}
}
