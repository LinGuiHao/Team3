package com.lgh.project.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.lgh.project.beans.User;
import com.lgh.project.dao.UserDaoImpl;

@Controller
public class MailVerify {
	
	
	@RequestMapping(value="/mailverify",produces = "application/json; charset=utf-8")
	@ResponseBody
	public  String mailSend(String code) {
		UserDaoImpl userDao = new UserDaoImpl();
		Gson gson = new Gson();
		Map<String, Object> result = new HashMap<>();
		if(!userDao.codeIsExist(code)) {
			result.put("msg" , "δ�ҵ�ע���룬ע��ʧ��");
			result.put("success", false);
		}else {
			User user = userDao.findByCode(code);
			user.setCodeUrl(null);
			user.setIntivation(1);
			userDao.updateByCode(user);
			result.put("msg" , "����ɹ�������ӭʹ������ũ�壡��");
			result.put("success", true);
		}
		return gson.toJson(result);
	}
	
	
}
