package com.credsu.demo.data;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class TableCreate {
	
	 private static final String createTableSQL = "create table LoggerAlaram (\r\n" + "  Id  int primary key,\r\n" +
		        "  Type varchar(20),\r\n" + "  Host varchar(20),\r\n" + "  StartTime BIGINT,\r\n" +
		        "  EndTime BIGINT\r\n" + "  );";

	 private static final String dropTableSQL = "drop table LoggerAlaram ;";

		   

		    public void createTable() throws SQLException {

		        System.out.println(createTableSQL);
		        Connection connection = JDBCUtility.getConnection2();;
		        try ( 		        		
		            Statement statement = connection.createStatement();) {
		            // Step 2: Execute the query or update query
		        	statement.execute(dropTableSQL);
		            statement.execute(createTableSQL);
		        } catch (SQLException e) {
		            System.out.println(e.getMessage());
		        }finally {
		        	connection.close();connection = null;
		        }
		    }

}
