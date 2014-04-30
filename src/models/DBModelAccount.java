package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBModelAccount
{
	private int id;
	private String username;
	private String password;
	private boolean isAdmin;
	
	private static final String tableName = "account";
	private static final String usernameColumn = "username";
	private static final String passwordColumn = "password";
	private static final String isAdminColumn = "isAdmin";
	
	public DBModelAccount(String username, String password){
		setUsername(username);
		setPassword(password);
	}
	
	public int getId() {
		return id;
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public boolean isAdmin() {
		return isAdmin;
	}
	
	private void setId(int id) {
		this.id = id;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	//Check if the user inputs match with the data in database
	public boolean checkUser(){
		try(Connection conn = DBConnector.getConnection()){
			String query = "SELECT * FROM account WHERE username = ? AND password = ?";
			
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setString(1, getUsername());
			statement.setString(2, getPassword());
			
			
			ResultSet result = statement.executeQuery();
			
			return result.next();
		} 
		catch (SQLException exception) 
		{
			exception.printStackTrace();
		}
		
		return false;
			
	}

}
