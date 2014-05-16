package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JOptionPane;

public class DBModelAirport
{
	private int id;
	private String airportName;
	private String cityName;
	private static final String TABLE_NAME = "airport";
	private static final String COLUMN_AIRPORT = "name";
	private static final String COLUMN_CITY = "city";

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

	//Konstruktor för att skapa ny flygplats
	public DBModelAirport(String airport, String city){
		this.setAirportName(airport);
		this.setCityName(city);
	}
	
	//Konstruktor för att hämta från databasen och göra ett objekt
	public DBModelAirport(int id, String airport, String city)
	{
		this.setId(id);
		this.setAirportName(airport);
		this.setCityName(city);
	}
	
public static Vector<DBModelAirport> getAll() {
		
	Vector<DBModelAirport> DBvector = new Vector<DBModelAirport>();

		try (Connection conn = DBConnector.getConnection())
		{
			
			String query = "SELECT * FROM " + TABLE_NAME;
			
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

public static DBModelAirport getAirport(int airportId)
{
	DBModelAirport airport = null;

		try (Connection conn = DBConnector.getConnection())
		{
			String query = "SELECT * FROM " + TABLE_NAME + " WHERE id=?";
			
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setInt(1,airportId);
			
			ResultSet result = statement.executeQuery();
			
			while (result.next())
			{
				int id = result.getInt(1);
				String airportName = result.getString(2);
				String cityName = result.getString(3);
				
				airport = new DBModelAirport(id,airportName,cityName);
			}
			
			
			 
		} catch (SQLException exception) {
			exception.printStackTrace();
		}
		
	return airport;
}

	public int insert()
	{
		try (Connection conn = DBConnector.getConnection())
		{
			String query = "INSERT INTO " + TABLE_NAME + 
					"(" + COLUMN_AIRPORT + "," + COLUMN_CITY + ") " + 
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
	
	@Override
	public String toString(){
		return this.getAirportName() + " " + this.getCityName();
	} 
	
	public int update()
	{
		try (Connection conn = DBConnector.getConnection())
		{
			String query = "UPDATE " + TABLE_NAME + 
						   " SET " + COLUMN_AIRPORT + "=?, " +COLUMN_CITY + "=? " + 
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
			String query = "DELETE FROM " + TABLE_NAME +
						   " WHERE id=?";

			PreparedStatement statement = conn.prepareStatement(query);
			statement.setInt(1,id);

			int result = statement.executeUpdate();

			return result;	 
		} 
		catch (SQLException exception) 
		{
			JOptionPane.showMessageDialog(null, exception.getSQLState() + " " + exception.getMessage(),"Databasfel",JOptionPane.ERROR_MESSAGE);
			exception.printStackTrace();
		}
		return -1;
	}
}
