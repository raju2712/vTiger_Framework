package genericUtilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

public class DataBaseUtility {
	
	Connection connect;
	
	/**
	 * This method is used to get connection of database
	 * 
	 * @param url
	 * @param username
	 * @param password
	 * @return 
	 */
	public void getDbConnection(String url, String username, String password) {
		try
		{
			Driver driver = new Driver();
			DriverManager.registerDriver(driver);
			connect = DriverManager.getConnection(url, username, password);
		} 
		catch (Exception e) 
		{
			
		}
	}
	
	/**
	 * This method is used to get database connection of TestYantra Projects database
	 */
	public void getDbConnectionWithCredentials() {
		try 
		{
			Driver driver = new Driver();
			DriverManager.registerDriver(driver);
			connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/projects", "root", "root");
		} 
		catch (Exception e) 
		{
			
		}
	}
	
	/**
	 * This method is used to close the database connection with java
	 * 
	 * @throws SQLException
	 */
	public void closeDbConnection() throws SQLException {
		try 
		{
			connect.close();
		}
		catch (Exception e) 
		{
			
		}
	}
	
	/**
	 * This method is used to select query
	 * 
	 * @param query
	 * @return
	 */
	public ResultSet executeSelectQuery(String query) {
		ResultSet result = null;
		try 
		{
			Statement stmt = connect.createStatement();
			result = stmt.executeQuery(query);
		} 
		catch (Exception e) 
		{
			
		}
		return result;
	}
	
	/**
	 * This method is used to non-select query
	 * 
	 * @param query
	 * @return
	 */
	public int executeUpdateQuery(String query) {
		int result = 0;
		try 
		{
			Statement stmt = connect.createStatement();
			result = stmt.executeUpdate(query);
		}
		catch (Exception e) 
		{
			
		}
		return result;
	}

}
