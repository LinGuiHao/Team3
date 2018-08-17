package com.lgh.project.dao;

import com.lgh.project.beans.User;

public interface UserDao {
	/**
	 * 用于注册时保存用户
	 * @param user
	 */
	void saveUser(User user);
	
	/**
	 * 用于验证用户名是否存在
	 * @param name
	 * @return
	 */
	boolean userIsExist(String name);
	
	/**
	 * 验证登陆是否成功
	 * @param name
	 * @param password
	 * @return
	 */
	User login(String name,String password);
	
	/**
	 * 查看此激活码是否已存在
	 * @param code
	 * @return
	 */
	boolean codeIsExist(String code);
	
	/**
	 * 更新数据库数据
	 * @param user
	 */
	void updateByCode(User user);
	
	/**
	 * 通过验证码找到用户
	 * @param code
	 * @return
	 */
	User findByCode(String code);
}
