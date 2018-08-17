package com.lgh.project.controller;



import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.lgh.project.beans.User;
import com.lgh.project.dao.UserDaoImpl;


@Controller
public class LoginVerify {

	@RequestMapping(value="/login",produces = "application/json; charset=utf-8")
	@ResponseBody
	public String Login(String username,String password){
//		ModelAndView result = new ModelAndView();
		Gson gson =new Gson();
		Map<String, Object> result = new HashMap<>();
		UserDaoImpl userDao = new UserDaoImpl();
		User user = userDao.login(username, password);
		if(!userDao.userIsExist(username)) {
			result.put("success", false);
			result.put("msg", "�û��������ڣ�");
		}else if (user==null) {
			result.put("success", false);
			result.put("msg", "�������");
		}else if (user!=null && user.getIntivation() == 0) {
			result.put("success", false);
			result.put("msg", "�˺�δ����!");
		}else {
			result.put("success",true);
			result.put("msg","��½");
			result.put("username", user.getName());
			result.put("email", user.getEmail());
		}
		return gson.toJson(result);
	}
	
	@RequestMapping(value="/login2")
	@ResponseBody
	public User login2(String username,String password) {
		User user = new User();
		user.setCodeUrl("123123");
		user.setEmail("123132");
		return user;
	}
}
