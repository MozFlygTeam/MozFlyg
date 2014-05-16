package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JOptionPane;

public class DBModelBookedFlight{
	private int accountId;
	private int flightId;
	private static final String TABLE_NAME = "booked_flight";
	private static final String COLUMN_ACCOUNT_ID = "account_id";
	private static final String COLUMN_FLIGHT_ID = "flight_id";
	
	public int getAccountId() {
		return accountId;
	}
	public int getFlightId() {
		return flightId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}
	public void setFlightId(int flightId) {
		this.flightId = flightId;
	}

	public DBModelBookedFlight(){

	}

	//Konstruktor f��r att skapa ny flygplats
	public DBModelBookedFlight(int accountId, int flightId){
		this.setAccountId(accountId);
		this.setFlightId(flightId);
	}

	public static Vector<DBModelBookedFlight> getAllBookedFlights() {
		
		Vector<DBModelBookedFlight> DBvector = new Vector<DBModelBookedFlight>();

			try (Connection conn = DBConnector.getConnection())
			{
				
				String query = "SELECT " + COLUMN_ACCOUNT_ID + "," + COLUMN_FLIGHT_ID +" FROM " + TABLE_NAME;
				
				Statement statement = conn.createStatement();
				ResultSet result = statement.executeQuery(query);
				
				while (result.next())
				{
				
					int accountId = result.getInt(COLUMN_FLIGHT_ID);
					int flightId = result.getInt(COLUMN_FLIGHT_ID);
					
					
					DBvector.add(new DBModelBookedFlight(accountId, flightId));
				}
				
				 
			} catch (SQLException exception) {
				exception.printStackTrace();
			}
			
			 return DBvector;
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