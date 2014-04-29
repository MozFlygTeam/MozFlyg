package models;

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
	
	
}
