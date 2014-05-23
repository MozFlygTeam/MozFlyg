package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

	//TODO skicka in vilken ID:t på användare som är inloggad i get booked getAllFlights.
	//Ifall värdet är -1 lista alla resor som är bokade.
	public static Vector<DBModelBookedFlight> getAllBookedFlights() {
		
		Vector<DBModelBookedFlight> DBvector = new Vector<DBModelBookedFlight>();
		
			try (Connection conn = DBConnector.getConnection())
			{
				 
					String query = "SELECT " + COLUMN_ACCOUNT_ID + ", " + COLUMN_FLIGHT_ID +
								   " FROM " + TABLE_NAME + " WHERE " + COLUMN_ACCOUNT_ID + "=?";
					
					
					PreparedStatement statement = conn.prepareStatement(query);
					statement.setInt(1, DBModelAccount.loggedInUser.getId());
					
					ResultSet result = statement.executeQuery();
					
					while (result.next())
					{
					
						int accountId = result.getInt(COLUMN_ACCOUNT_ID);
						int flightId = result.getInt(COLUMN_FLIGHT_ID);
						
						
						DBvector.add(new DBModelBookedFlight(accountId, flightId));
					}
				 
			} catch (SQLException exception) {
				exception.printStackTrace();
			}
			
			 return DBvector;
	}
	
	public static int deleteBooking(int accountId, int flightId)
	{
		System.out.println("DeleteBooking called!");
		try (Connection conn = DBConnector.getConnection())
		{
			String query = "DELETE FROM " + TABLE_NAME +
						   " WHERE " + COLUMN_ACCOUNT_ID + "=? AND " +
						   COLUMN_FLIGHT_ID + "=?";
		
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setInt(1,accountId);
			statement.setInt(2,flightId);
				
			System.out.println(statement.toString());
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
	
public int delete()
	{
		System.out.println("Delete called!");
		try (Connection conn = DBConnector.getConnection())
		{
			String query = "DELETE FROM " + TABLE_NAME +
						   " WHERE " + COLUMN_ACCOUNT_ID + "=? AND " +
						   COLUMN_FLIGHT_ID + "=?";
		
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setInt(1,accountId);
			statement.setInt(2,flightId);
				
			System.out.println(statement.toString());
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

public int insert() {
	
	try (Connection conn = DBConnector.getConnection())
	{
		String query = "INSERT INTO " + TABLE_NAME + 
				"(" + COLUMN_ACCOUNT_ID + "," + COLUMN_FLIGHT_ID + ") " + 
				"VALUES (?, ?, ?, ?)";

		PreparedStatement statement = conn.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);

		statement.setInt(1, accountId);
		statement.setInt(2, flightId);
	
		

		int rowCount = statement.executeUpdate();

			return rowCount;	 
	} 
	catch (SQLException exception) 
	{
		exception.printStackTrace();
	}
	return -1;
}




}


