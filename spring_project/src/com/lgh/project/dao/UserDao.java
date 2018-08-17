package com.lgh.project.dao;

import com.lgh.project.beans.User;

public interface UserDao {
	/**
	 * ����ע��ʱ�����û�
	 * @param user
	 */
	void saveUser(User user);
	
	/**
	 * ������֤�û����Ƿ����
	 * @param name
	 * @return
	 */
	boolean userIsExist(String name);
	
	/**
	 * ��֤��½�Ƿ�ɹ�
	 * @param name
	 * @param password
	 * @return
	 */
	User login(String name,String password);
	
	/**
	 * �鿴�˼������Ƿ��Ѵ���
	 * @param code
	 * @return
	 */
	boolean codeIsExist(String code);
	
	/**
	 * �������ݿ�����
	 * @param user
	 */
	void updateByCode(User user);
	
	/**
	 * ͨ����֤���ҵ��û�
	 * @param code
	 * @return
	 */
	User findByCode(String code);
}
