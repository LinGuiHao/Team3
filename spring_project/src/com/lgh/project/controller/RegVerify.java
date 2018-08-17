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
				result.put("msg", "�ʼ����ͳɹ�,�뵽������֤");
				result.put("success", true);
			}
			else
			{
				result.put("msg","���䲻���ڣ��ʼ�����ʧ��");
				result.put("success", false);
			}
		}else {
			result.put("msg" , "�û����Ѵ��ڣ�������ע�ᡣ");
			result.put("success", false);
		}
		return gson.toJson(result);
	}
	
	private boolean sendmail(User user){
		String MailTo=user.getEmail();
		String MailSubject="����ũ��ע�ἤ��";
		String MailBCopyTo="";
	    String MailCopyTo="";
	    String MailBody="<h1>��������ũ���˺�ע�ἤ���ʼ�����������һ�����ӣ�</h1>"
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
