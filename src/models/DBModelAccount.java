package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class DBModelAccount
{
	private int id;
	private String username;
	private String password;
	private boolean isAdmin;
	
	private static final String TABLE_NAME = "account";
	private static final String USERNAME_COLUMN = "username";
	private static final String PASSWORD_COLUMN = "password";
	private static final String IS_ADMIN_COLUMN = "isAdmin";
	
	//Konstruktor för att skapa ny användare
	public DBModelAccount(String username, String password){
		setUsername(username);
		setPassword(password);
	}
	
	//Konstruktor för att hämta data från Databas
	public DBModelAccount(int id, String username, String password){
		setId(id);
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
			String query = "SELECT * FROM " + TABLE_NAME + 
						   " WHERE " + USERNAME_COLUMN + 
						   " = ? AND " + PASSWORD_COLUMN + "= ?";
			
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
	
	public static Vector<DBModelAccount> getAllAccounts() {
		
		Vector<DBModelAccount> DBvector = new Vector<DBModelAccount>();

			try (Connection conn = DBConnector.getConnection())
			{
				
				String query = "SELECT * FROM " + TABLE_NAME;
				
				Statement statement = conn.createStatement();
				ResultSet result = statement.executeQuery(query);
				
				while (result.next())
				{
					int id = result.getInt(1);
					String username = result.getString(2);
					String password = result.getString(3);
				
					
					DBvector.add(new DBModelAccount(id, username, password));
				}
				
				
				 
			} catch (SQLException exception) {
				exception.printStackTrace();
			}
			
			 return DBvector;
		
	}

	public int insert() {
		
		try (Connection conn = DBConnector.getConnection())
		{
			String query = "INSERT INTO " + TABLE_NAME + 
					"(" + USERNAME_COLUMN + "," + PASSWORD_COLUMN + ") " + 
					"VALUES (?, ?)";

			PreparedStatement statement = conn.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);

			statement.setString(1, username);
			statement.setString(2, password);

			int rowCount = statement.executeUpdate();


			//Use the DB-generated id
			ResultSet key = statement.getGeneratedKeys();
			key.next();
			setId(key.getInt("GENERATED_KEY"));

			return rowCount;	 
		} 
		catch (SQLException exception) 
		{
			exception.printStackTrace();
		}
		return -1;
	}
	

}
