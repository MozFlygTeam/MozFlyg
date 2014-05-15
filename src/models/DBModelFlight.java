package models;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class DBModelFlight
{
	private int id;
	private DBModelAirport departingFrom;
	private DBModelAirport arrivingTo;
	private Date timeDeparting;
	private Date timeArriving;
	private double price;

	
	private static String TABLE_NAME = "flight";
	private static String COLUMN_DEPARTING_FROM = "departing_from";
	private static String COLUMN_ARRIVING_TO = "arriving_to";
	private static String COLUMN_TIME_DEPARTING = "time_departing";
	private static String COLUMN_TIME_ARRIVING = "time_arriving";
	private static String COLUMN_PRICE = "price";
	
	public DBModelFlight(int id, DBModelAirport departingFrom, DBModelAirport arrivingTo, 
				Date timeDeparting,Date timeArriving, double price) 
	{
		this.id = id;
		this.departingFrom = departingFrom;
		this.arrivingTo = arrivingTo;
		this.timeDeparting = timeDeparting;
		this.timeArriving = timeArriving;
		this.price = price;
	}
	
	public DBModelFlight(DBModelAirport departingFrom, DBModelAirport arrivingTo, 
			Date timeDeparting,Date timeArriving, double price) 
		{
		this.departingFrom = departingFrom;
		this.arrivingTo = arrivingTo;
		this.timeDeparting = timeDeparting;
		this.timeArriving = timeArriving;
		this.price = price;
		}

	public DBModelFlight() {}

	public static Vector<DBModelFlight> getFlights(int fromAirportId, int toAirportId, Date date)
	{
		Vector<DBModelFlight> flightList = new Vector<DBModelFlight>();

			try (Connection conn = DBConnector.getConnection())
			{
				String query = "SELECT" + " id, " + COLUMN_DEPARTING_FROM + ", " + COLUMN_ARRIVING_TO + ", " + COLUMN_TIME_DEPARTING + ", " + COLUMN_PRICE + 
						" FROM " + TABLE_NAME +
						" WHERE " + COLUMN_DEPARTING_FROM + "=? AND " + COLUMN_ARRIVING_TO + "=? AND " + COLUMN_TIME_DEPARTING + " =? AND" + COLUMN_TIME_ARRIVING;
				
				PreparedStatement statement = conn.prepareStatement(query);
				statement.setInt(1, fromAirportId);
				statement.setInt(2, toAirportId);
				statement.setString(3, date.toString() );
				
				ResultSet result = statement.executeQuery();
				
				while (result.next())
				{
					int id = result.getInt(1);
					int departingId = result.getInt(COLUMN_DEPARTING_FROM);
					int arrivingId = result.getInt(COLUMN_ARRIVING_TO);
					Date departingTime = result.getDate(COLUMN_TIME_DEPARTING);
					Date arrivalTime = result.getDate(COLUMN_TIME_ARRIVING);
					double price = result.getDouble(COLUMN_PRICE);
					
					DBModelAirport airportFrom = DBModelAirport.getAirport(departingId);
					DBModelAirport airportTo = DBModelAirport.getAirport(arrivingId);
					
					flightList.add(new DBModelFlight(id, airportFrom, airportTo, departingTime,arrivalTime, price));
				}
				 
			} 
			catch (SQLException exception)
			{
				exception.printStackTrace();
			}
			
		return flightList;
	}
	
	public int insert()
	{
		try (Connection conn = DBConnector.getConnection())
		{
			String query = "INSERT INTO " + TABLE_NAME + 
					"(" + COLUMN_DEPARTING_FROM + "," + COLUMN_ARRIVING_TO + "," + COLUMN_TIME_DEPARTING + "," + COLUMN_PRICE + ") " + 
					"VALUES (?, ?, ?, ?)";

			PreparedStatement statement = conn.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);

			statement.setInt(1, departingFrom.getId());
			statement.setInt(2, arrivingTo.getId());
			statement.setDate(3, timeDeparting);
			statement.setDate(3, timeArriving);
			statement.setDouble(5, price);

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
			String query = "UPDATE " + TABLE_NAME + 
						   " SET " + COLUMN_DEPARTING_FROM + "=?, " + COLUMN_ARRIVING_TO + " =?, " + COLUMN_TIME_DEPARTING + " =?, "+ COLUMN_TIME_DEPARTING +" =? "+ COLUMN_PRICE + " =? " +
						   "WHERE id=?";

			PreparedStatement statement = conn.prepareStatement(query);
			statement.setInt(1, departingFrom.getId());
			statement.setInt(2, arrivingTo.getId());
			statement.setDate(3, timeDeparting);
			statement.setDate(4, timeDeparting);
			statement.setDouble(5, price);
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
			String query = "DELETE FROM " + TABLE_NAME +
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
	
	public DBModelAirport getDepartingFrom() {
		return departingFrom;
	}
	public DBModelAirport getArrivingTo() {
		return arrivingTo;
	}
	public Date getTimeDeparting() {
		return timeDeparting;
	}
	public Date getTimeArriving() {
		return timeArriving;
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
	public void setTimeArriving(Date timeArriving) {
		this.timeArriving = timeArriving;
	}
	public void setPrice(double price) {
		this.price = price;
	}

	public int getId() {
		return id;
	}

	

	
}
