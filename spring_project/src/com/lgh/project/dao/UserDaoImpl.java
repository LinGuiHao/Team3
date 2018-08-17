  package com.lgh.project.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.springframework.stereotype.Service;

import com.lgh.project.beans.User;
import com.lgh.project.util.DataBaseUtil;

@Service("userDao")
public class UserDaoImpl implements UserDao{
	
	private Connection connection;
	
	
	@Override
	public boolean userIsExist(String name) {
		connection = DataBaseUtil.getConnection();
		String sql = "select * from user where username = ?";
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();
			if(!rs.next()) {
				return false;
			}
			rs.close();
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DataBaseUtil.closeConnection(connection);
		}
		return true;
	}
	
	@Override
	public void saveUser(User user) {
		Connection connection = DataBaseUtil.getConnection();
		String sql = "insert into user(username,password,email,activation,codeurl) value(?,?,?,?,?)";
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, user.getName());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getEmail());
			ps.setInt(4, user.getIntivation());
			ps.setString(5, user.getCodeUrl());
			ps.executeUpdate();
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DataBaseUtil.closeConnection(connection);
		}
	}
	
	@Override 
	public User login(String name,String password) {
		User user = null;
		Connection connection = DataBaseUtil.getConnection();
		String sql = "select * from user where username=? and password=? and activation=1";
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, name);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				user = new User();
				user.setName(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setEmail(rs.getString("email"));
				user.setIntivation(rs.getInt("activation"));
			}
			rs.close();
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DataBaseUtil.closeConnection(connection);
		}
		return user;
	}

	@Override
	public boolean codeIsExist(String code) {
		connection = DataBaseUtil.getConnection();
		String sql = "select * from user where codeurl = ?";
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, code);
			ResultSet rs = ps.executeQuery();
			if(!rs.next()) {
				return false;
			}
			rs.close();
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DataBaseUtil.closeConnection(connection);
		}
		return true;
	}

	@Override
	public void updateByCode(User user) {
		Connection connection = DataBaseUtil.getConnection();
		String sql = "update user set codeurl=?,activation=? where username=?;";
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, user.getCodeUrl());
			ps.setInt(2, user.getIntivation());
			ps.setString(3, user.getName());
			ps.executeUpdate();
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DataBaseUtil.closeConnection(connection);
		}
	}

	@Override
	public User findByCode(String code) {
		User user = new User();
		Connection connection = DataBaseUtil.getConnection();
		String sql = "select * from user where codeurl=?";
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, code);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				user.setName(rs.getString("username"));
				user.setPassword(rs.getString("PASSWORD"));
				user.setEmail(rs.getString("email"));
				user.setIntivation(rs.getInt("activation"));
			}
			rs.close();
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DataBaseUtil.closeConnection(connection);
		}
		return user;
	}


}
