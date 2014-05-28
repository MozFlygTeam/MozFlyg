package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class DBModelAirplaneType
{
	private int id;
	private String modelName;
	private int passengerCapacity;
	private int velocity;
	private double fuelConsumption;
	
	private static final String TABLE_NAME = "airplane_type";
	private static final String MODEL_NAME_COLUMN = "model_name";
	private static final String PASSENGER_CAPACITY_COLUMN = "passenger_capacity";
	private static final String VELOCITY_COLUMN = "velocity";
	private static final String FUEL_CONSUMPTION_COLUMN = "fuel_consumption";
	
	//Use to create new airplane type
	public DBModelAirplaneType(String modelName, int passengerCapacity, int velocity, double fuelConsumption) {
		this.modelName = modelName;
		this.passengerCapacity = passengerCapacity;
		this.velocity = velocity;
		this.fuelConsumption = fuelConsumption;
	}
	
	@Override
	public String toString(){
		return this.getModelName();
	} 
	
	public int getId() {
		return id;
	}



	public String getModelName() {
		return modelName;
	}



	public int getPassengerCapacity() {
		return passengerCapacity;
	}



	public int getVelocity() {
		return velocity;
	}



	public double getFuelConsumption() {
		return fuelConsumption;
	}



	public void setId(int id) {
		this.id = id;
	}



	public void setModelName(String modelName) {
		this.modelName = modelName;
	}



	public void setPassengerCapacity(int passengerCapacity) {
		this.passengerCapacity = passengerCapacity;
	}



	public void setVelocity(int velocity) {
		this.velocity = velocity;
	}



	public void setFuelConsumption(double fuelConsumption) {
		this.fuelConsumption = fuelConsumption;
	}

	

	//Used by DB to recreate existing airplane types
	public DBModelAirplaneType(int id, String modelName, int passengerCapacity, int velocity,
			double fuelConsumption) {
		this.id = id;
		this.modelName = modelName;
		this.passengerCapacity = passengerCapacity;
		this.velocity = velocity;
		this.fuelConsumption = fuelConsumption;
	}
	
public static DBModelAirplaneType getAirplaneType(int airplaneTypeId) {
		
		DBModelAirplaneType airplaneTypeList = null;

		try (Connection conn = DBConnector.getConnection())
		{
			String query = "SELECT id," + MODEL_NAME_COLUMN + "," + PASSENGER_CAPACITY_COLUMN + "," + VELOCITY_COLUMN + "," + FUEL_CONSUMPTION_COLUMN + " FROM " + TABLE_NAME
					+ " WHERE id = ?";
			
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setInt(1, airplaneTypeId);
			ResultSet result = statement.executeQuery();
			
			while (result.next())
			{
				int id = result.getInt(1);
				String model = result.getString(MODEL_NAME_COLUMN);
				int passengers = result.getInt(PASSENGER_CAPACITY_COLUMN);
				int speed = result.getInt(VELOCITY_COLUMN);
				double fuel = result.getDouble(FUEL_CONSUMPTION_COLUMN);
				
				airplaneTypeList = new DBModelAirplaneType(id, model, passengers, speed, fuel);
			}	 
		}
		catch (SQLException exception)
		{
			exception.printStackTrace();
		}
			
		return airplaneTypeList;
	}

	
	public static Vector<DBModelAirplaneType> getAllAirplaneTypes() {
		
		Vector<DBModelAirplaneType> airplaneTypeList = new Vector<DBModelAirplaneType>();

		try (Connection conn = DBConnector.getConnection())
		{
			String query = "SELECT id," + MODEL_NAME_COLUMN + "," + PASSENGER_CAPACITY_COLUMN + "," + VELOCITY_COLUMN + "," + FUEL_CONSUMPTION_COLUMN + " FROM " + TABLE_NAME;
			
			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery(query);
			
			while (result.next())
			{
				int id = result.getInt(1);
				String model = result.getString(MODEL_NAME_COLUMN);
				int passengers = result.getInt(PASSENGER_CAPACITY_COLUMN);
				int speed = result.getInt(VELOCITY_COLUMN);
				double fuel = result.getDouble(FUEL_CONSUMPTION_COLUMN);
				
				airplaneTypeList.add(new DBModelAirplaneType(id, model, passengers, speed, fuel));
			}	 
		}
		catch (SQLException exception)
		{
			exception.printStackTrace();
		}
			
		return airplaneTypeList;
	}

	public int insert() {
		
		try (Connection conn = DBConnector.getConnection())
		{
			String query = "INSERT INTO " + TABLE_NAME + 
					"(" + MODEL_NAME_COLUMN + "," + PASSENGER_CAPACITY_COLUMN + "," + VELOCITY_COLUMN + "," + FUEL_CONSUMPTION_COLUMN + ") " + 
					"VALUES (?, ?, ?, ?)";

			PreparedStatement statement = conn.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);

			statement.setString(1, modelName);
			statement.setInt(2, passengerCapacity);
			statement.setInt(3, velocity);
			statement.setDouble(4, fuelConsumption);
			

			int rowCount = statement.executeUpdate();

			//Use the DB-generated id
			ResultSet key = statement.getGeneratedKeys();
			key.next();
			this.id = key.getInt("GENERATED_KEY");

			return rowCount;	 
		} 
		catch (SQLException exception) 
		{
			exception.printStackTrace();
		}
		return -1;
	}
	
	/**
	 * @return
	 * Number of rows updated
	 */
	public int update()
	{
		try (Connection conn = DBConnector.getConnection()) {
			String query = "UPDATE " + TABLE_NAME + 
						   " SET " + MODEL_NAME_COLUMN + "=?," + PASSENGER_CAPACITY_COLUMN + "=?," + VELOCITY_COLUMN + "=?," + FUEL_CONSUMPTION_COLUMN + "=?" +
						   " WHERE id=?";

			PreparedStatement statement = conn.prepareStatement(query);
			statement.setString(1, modelName);
			statement.setInt(2, passengerCapacity);
			statement.setInt(3, velocity);
			statement.setDouble(4, fuelConsumption);
			statement.setInt(5, id);
			
			statement.executeUpdate();
			

			return statement.executeUpdate();	 
		} 
		catch (SQLException exception)
		{
			exception.printStackTrace();
		}
		return -1;
	}
	
	/**
	 * @return
	 * Number of rows updated
	 */
	public int delete() {
		try (Connection conn = DBConnector.getConnection()) {
			String query = "DELETE FROM " + TABLE_NAME +
						   " WHERE id=?";

			PreparedStatement statement = conn.prepareStatement(query);
			statement.setInt(1,id);


			return statement.executeUpdate();
		} 
		catch (SQLException exception) {
			exception.printStackTrace();
		}
		return -1;
	}
}
