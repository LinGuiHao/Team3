package com.lgh.project.beans;

import org.springframework.stereotype.Component;

@Component("user")
public class User {
	private String name;
	private String password;
	private String email;
	private int intivation;//0Î´¼¤»î£¬1ÒÑ¼¤»î
	private String codeUrl;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getIntivation() {
		return intivation;
	}
	public void setIntivation(int intivation) {
		this.intivation = intivation;
	}
	public String getCodeUrl() {
		return codeUrl;
	}
	public void setCodeUrl(String codeUrl) {
		this.codeUrl = codeUrl;
	}
	
	
}
