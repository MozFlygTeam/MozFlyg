package models;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class DBModelRutt {

	private DBModelAirport airport_depart;
	private DBModelAirport airport_arrive;
	
	private static String TABLE_NAME = "rutter";
	
	private static String COLUMN_DEPARTING_FROM = "id";
	private static String COLUMN_ARRIVING_TO = "id_depart";
	
	
	public DBModelRutt(DBModelAirport airport_depart, DBModelAirport airport_arrive){
		this.setAirport_depart(airport_depart);
		this.setAirport_arrive(airport_arrive);
	} 
	
	public static Vector<DBModelRutt> getAllRutt()
	{
		
		Vector<DBModelRutt> ruttList = new Vector<DBModelRutt>();
		
			try (Connection conn = DBConnector.getConnection())
			{
					String query = "SELECT rutter.id, airport.id FROM rutter LEFT JOIN airport ON rutter.id_depart = airport.id";
				
				
					Statement statement = conn.createStatement();
					ResultSet result = statement.executeQuery(query);	
					
				while (result.next())
				{
					int departingId = result.getInt(1);
					int arrivingId = result.getInt(2);
					
					System.out.print(departingId);
					System.out.print(arrivingId);
					
					DBModelAirport airportFrom = DBModelAirport.getAirport(departingId);
					System.out.print(airportFrom.getCityName());
					
					DBModelAirport airportTo = DBModelAirport.getAirport(arrivingId);
					System.out.print(airportTo.getCityName());
					
					ruttList.add(new DBModelRutt(airportFrom, airportTo));
				}
				 
			} 
			catch (SQLException exception)
			{
				exception.printStackTrace();
			}
			
		return ruttList;
	}
	
	public int insert()
	{
		try (Connection conn = DBConnector.getConnection())
		{
			String query = "INSERT INTO rutter(id,id_depart) VALUES (?,?)";

			PreparedStatement statement = conn.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);

			statement.setInt(1, airport_depart.getId());
			statement.setInt(2, airport_arrive.getId());

			int rowCount = statement.executeUpdate();

			return rowCount;	 
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
						   " WHERE id=? AND id_depart=?";

			PreparedStatement statement = conn.prepareStatement(query);
			statement.setInt(1,airport_depart.getId());
			statement.setInt(2,airport_arrive.getId());

			int result = statement.executeUpdate();

			return result;	 
		} 
		catch (SQLException exception) 
		{
			exception.printStackTrace();
		}
		return -1;
	}



	public DBModelAirport getAirport_depart() {
		return airport_depart;
	}

	public void setAirport_depart(DBModelAirport airport_depart) {
		this.airport_depart = airport_depart;
	}

	public DBModelAirport getAirport_arrive() {
		return airport_arrive;
	}

	public void setAirport_arrive(DBModelAirport airport_arrive) {
		this.airport_arrive = airport_arrive;
	}
	
	
}
