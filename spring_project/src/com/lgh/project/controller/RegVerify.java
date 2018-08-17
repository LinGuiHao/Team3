package com.lgh.project.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.lgh.project.beans.User;
import com.lgh.project.dao.UserDaoImpl;
import com.lgh.project.mail.SendMail;

@Controller
public class RegVerify {
	
	@RequestMapping(value="/reg",produces = "application/json; charset=utf-8")
	@ResponseBody
	public String reg(String username,String password,String email) {
		User user = new User();
		UserDaoImpl userDao = new UserDaoImpl();
		Gson gson = new Gson();
		Map<String, Object> result = new HashMap<>();
		user.setName(username);
		user.setPassword(password);
		user.setEmail(email);
		String code;
		while(true) {
			code = UUID.randomUUID().toString().replaceAll("-","");
			if (!userDao.codeIsExist(code)) {
				break;
			}
		}
		user.setCodeUrl(code);
		user.setIntivation(0);
		if(!userDao.userIsExist(user.getName())) {
			userDao.saveUser(user);			
			if(sendmail(user))
			{
				result.put("msg", "邮件发送成功,请到邮箱验证");
				result.put("success", true);
			}
			else
			{
				result.put("msg","邮箱不存在，邮件发送失败");
				result.put("success", false);
			}
		}else {
			result.put("msg" , "用户名已存在，请重新注册。");
			result.put("success", false);
		}
		return gson.toJson(result);
	}
	
	private boolean sendmail(User user){
		String MailTo=user.getEmail();
		String MailSubject="温氏农场注册激活";
		String MailBCopyTo="";
	    String MailCopyTo="";
	    String MailBody="<h1>来自温氏农场账号注册激活邮件，激活请点击一下链接：</h1>"
	    		+ "<h3>"
	    		+ "<a href='http://localhost:8080/spring_project/mailverify?code="+user.getCodeUrl()+"'>" 
	    		+ "http://localhost:8080/spring_project/mailverify?code="+user.getCodeUrl()
	    		+ "</a>"
	    		+ "</h3>";
		String SMTPHost = "smtp.163.com";
		String Port="25";
		String MailUsername = "scau_guihao@163.com";
		String MailPassword = "lgh163";
		String MailFrom = "scau_guihao@163.com";
		if(SMTPHost==null||SMTPHost==""||MailUsername==null||MailUsername==""||MailPassword==null||MailPassword==""||MailFrom==null||MailFrom=="")
		{
			System.out.println("Servlet parameter Wrongs");
		}
		SendMail send=new SendMail(SMTPHost,Port,MailUsername,MailPassword);
		if(send.sendingMimeMail(MailFrom, MailTo, MailCopyTo, MailBCopyTo, MailSubject, MailBody)){
			return true;
		}
		else
		{
			return false;
		}
	}
	
}
