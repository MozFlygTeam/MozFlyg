package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AirportModel
{
	private int id;
	private String airport;
	private String city;
	private static final String tableName = "airport";
	private static final String airportColumn = "name";
	private static final String cityColumn = "city";
	
	public int getId() {
		return id;
	}
	public String getAirportName() {
		return airport;
	}
	public String getCityName() {
		return city;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	public void setAirportName(String airportName) {
		this.airport = airportName;
	}
	public void setCityName(String cityName) {
		this.city = cityName;
	}
	
	public int insert()
	{
		try (Connection conn = DBConnector.getConnection())
		{
			String query = "INSERT INTO ? (?, ?) VALUES (?, ?) airport";
			
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setString(1, tableName);
			statement.setString(2, airportColumn);
			statement.setString(3, cityColumn);
			statement.setString(4, airport);
			statement.setString(5, city);

			int result = statement.executeUpdate();
			
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
			statement.setString(3, airport);
			statement.setString(4, cityColumn);
			statement.setString(5, city);
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
	// Kommentar nummer två av kristoffer!
}
