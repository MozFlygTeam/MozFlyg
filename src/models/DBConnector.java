package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector
{
	private static String dbURL;
	private static String username;
	private static String password;
	
	public static void setConnectionData(String url, String user, String passwd)
	{
		dbURL = url;
		username = user;
		password = passwd;
	}
	
	public static Connection getConnection() throws SQLException
	{
		return DriverManager.getConnection(dbURL, username, password);
	}
}