package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBModelAirport
{
	private int id;
	private String airportName;
	private String cityName;
	private static final String tableName = "airport";
	private static final String airportColumn = "name";
	private static final String cityColumn = "city";
	
	public int getId() {
		return id;
	}
	public String getAirportName() {
		return airportName;
	}
	public String getCityName() {
		return cityName;
	}
	
	private void setId(int id) {
		this.id = id;
	}
	public void setAirportName(String airportName) {
		this.airportName = airportName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	
	public int insert()
	{
		try (Connection conn = DBConnector.getConnection())
		{
			String query = "INSERT INTO ? (?, ?) VALUES (?, ?)";
			
			PreparedStatement statement = conn.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
			statement.setString(1, tableName);
			statement.setString(2, airportColumn);
			statement.setString(3, cityColumn);
			statement.setString(4, airportName);
			statement.setString(5, cityName);

			int result = statement.executeUpdate();
			
			// TODO Verify that id is returned
			
			ResultSet key = statement.getGeneratedKeys();
			setId(key.getInt(id));
			
			return result;	 
		} 
		catch (SQLException exception) 
		{
			exception.printStackTrace();
		}
		return -1;
	}
	
	public int update()
	{
		try (Connection conn = DBConnector.getConnection())
		{
			String query = "UPDATE ? SET ?=?, ?=? WHERE id=?";
			
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setString(1, tableName);
			statement.setString(2, airportColumn);
			statement.setString(3, airportName);
			statement.setString(4, cityColumn);
			statement.setString(5, cityName);
			statement.setInt(6,id);

			int result = statement.executeUpdate();
			
			return result;	 
		} 
		catch (SQLException exception) 
		{
			exception.printStackTrace();
		}
		return -1;
	}
	
	public int delete()
	{
		try (Connection conn = DBConnector.getConnection())
		{
			String query = "DELETE FROM ? WHERE id=?";
			
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setString(1, tableName);
			statement.setInt(2,id);

			int result = statement.executeUpdate();
			
			return result;	 
		} 
		catch (SQLException exception) 
		{
			exception.printStackTrace();
		}
		return -1;
	}
	//En kommentar
	// Kommentar nummer tv� av kristoffer!
}
