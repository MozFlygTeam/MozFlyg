package models;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBModelFlight {
	private int id;
	private DBModelAirport departingFrom;
	private DBModelAirport arrivingTo;
	private Date timeDeparting;
	private double price;
	
	private String tableName = "flight";
	private String columnDepartingFrom = "departing_from";
	private String columnArrivingTo = "arriving_to";
	private String columnTimeDeparting = "time_departing";
	private String columnPrice = "price";
	
	public DBModelAirport getDepartingFrom() {
		return departingFrom;
	}
	public DBModelAirport getArrivingTo() {
		return arrivingTo;
	}
	public Date getTimeDeparting() {
		return timeDeparting;
	}
	public double getPrice() {
		return price;
	}
	public void setDepartingFrom(DBModelAirport departingFrom) {
		this.departingFrom = departingFrom;
	}
	public void setArrivingTo(DBModelAirport arrivingTo) {
		this.arrivingTo = arrivingTo;
	}
	public void setTimeDeparting(Date timeDeparting) {
		this.timeDeparting = timeDeparting;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	public int insert()
	{
		try (Connection conn = DBConnector.getConnection())
		{
			String query = "INSERT INTO " + tableName + 
					"(" + columnDepartingFrom + "," + columnArrivingTo + "," + columnTimeDeparting + "," + columnPrice + ") " + 
					"VALUES (?, ?, ?, ?)";

			PreparedStatement statement = conn.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);

			statement.setInt(1, departingFrom.getId());
			statement.setInt(2, arrivingTo.getId());
			statement.setDate(3, timeDeparting);
			statement.setDouble(4, price);

			int rowCount = statement.executeUpdate();


			//Use the DB-generated id

			ResultSet key = statement.getGeneratedKeys();
			key.next();
			id = key.getInt("GENERATED_KEY");

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
						   " SET " + columnDepartingFrom + "=?, " + columnArrivingTo + "=? " + columnTimeDeparting + "=?, " + columnPrice + "=? " +
						   "WHERE id=?";

			PreparedStatement statement = conn.prepareStatement(query);
			statement.setInt(1, departingFrom.getId());
			statement.setInt(2, arrivingTo.getId());
			statement.setDate(3, timeDeparting);
			statement.setDouble(4, price);
			statement.setInt(5,id);

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
	
}
