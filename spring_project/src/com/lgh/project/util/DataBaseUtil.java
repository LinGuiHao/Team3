package com.lgh.project.util;

import java.sql.Connection;
import java.sql.DriverManager;

import org.hibernate.metamodel.relational.Database;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;

import com.sun.org.apache.xml.internal.security.Init;

public class DataBaseUtil {
	
	public static Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/wenshi?serverTimezone=GMT%2B8&useUnicode=true&characterEncoding=utf8&useSSL=false";
			String username = "root";
			String password = "1234";
			connection = DriverManager.getConnection(url, username, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return connection;
	}
	
	public static void closeConnection(Connection con) {
		if(con!=null) {
			try {
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
