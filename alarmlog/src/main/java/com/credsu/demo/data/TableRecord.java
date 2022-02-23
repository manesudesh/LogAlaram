package com.credsu.demo.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import com.credsu.demo.alarmlog.LogFile;

public class TableRecord {
	
	
	
	private static final String INSERT_LOGGERALARL_SQL = "INSERT INTO LoggerAlaram" +
	        "  (Id, Type, Host, StartTime, EndTime) VALUES " +
	        " (?, ?, ?, ?, ?);";

	    

	    public static void insertRecord(LogFile logfile) throws SQLException {
	        System.out.println(INSERT_LOGGERALARL_SQL);
	        Connection connection = JDBCUtility.getConnection2();
	        try (
	            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_LOGGERALARL_SQL)) {
	            preparedStatement.setInt(1, logfile.getId());
	            preparedStatement.setString(2, getStringOrEmpty(logfile.getType()));
	            preparedStatement.setString(3, getStringOrEmpty(logfile.getHost()));
	            preparedStatement.setLong(4,logfile.getStartTime());
	            preparedStatement.setLong(5, logfile.getEndTime());

	            System.out.println(preparedStatement);
	            // Step 3: Execute the query or update query
	            preparedStatement.executeUpdate();
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	        }finally {
	        	connection.close();connection = null;
	        }
	    }
	    
	  //return string . If string is null then return empty
	    private static String getStringOrEmpty(String str) {
	    	return str!=null?str:"";
	    }

}
