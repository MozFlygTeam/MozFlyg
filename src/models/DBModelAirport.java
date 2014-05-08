package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

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

	public DBModelAirport(){


	}

	public DBModelAirport(String airport, String city){
		this.setAirportName(airport);
		this.setCityName(city);
	}

	public DBModelAirport(int id, String airport, String city){

		this.setId(id);
		this.setAirportName(airport);
		this.setCityName(city);

	}
	
public static Vector<DBModelAirport> getAll() {
		
	Vector<DBModelAirport> DBvector = new Vector<DBModelAirport>();

		try (Connection conn = DBConnector.getConnection())
		{
			
			String query = "SELECT * FROM airport ";
			
			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery(query);
			
			while (result.next())
			{
				int id = result.getInt(1);
				String airport = result.getString(2);
				String city = result.getString(3);
			
				
				DBvector.add(new DBModelAirport(id,airport,city));
			}
			
			
			 
		} catch (SQLException exception) {
			exception.printStackTrace();
		}
		
		 return DBvector;
	}

	public int insert()
	{
		try (Connection conn = DBConnector.getConnection())
		{
			String query = "INSERT INTO " + tableName + 
					"(" + airportColumn + "," + cityColumn + ") " + 
					"VALUES (?, ?)";

			PreparedStatement statement = conn.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);

			statement.setString(1, airportName);
			statement.setString(2, cityName);

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

	public int update()
	{
		try (Connection conn = DBConnector.getConnection())
		{
			String query = "UPDATE " + tableName + 
						   " SET " + airportColumn + "=?, " +cityColumn + "=? " + 
						   "WHERE id=?";

			PreparedStatement statement = conn.prepareStatement(query);
			statement.setString(1, airportName);
			statement.setString(2, cityName);
			statement.setInt(3,id);

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
			String query = "DELETE FROM " + tableName +
						   " WHERE id=?";

			PreparedStatement statement = conn.prepareStatement(query);
			statement.setInt(1,id);

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