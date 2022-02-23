package com.credsu.demo.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCUtility {


	private static String jdbcURL = "jdbc:hsqldb:hsql://localhost/testdb";
    private static String jdbcUsername = "SA";
    private static String jdbcPassword = "";
	
		
	//create connection of hsqldb
	public static Connection getConnection2() throws SQLException{
		return DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
	}
}
