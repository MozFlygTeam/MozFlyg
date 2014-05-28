package models;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class DBModelFlight
{
  private int id;
  private DBModelAirport departingFrom;
  private DBModelAirport arrivingTo;
  private Date timeDeparting;
  private DBModelAirplaneType airplaneType;
  public DBModelAirplaneType getAirplaneType() {
	return airplaneType;
}

public void setAirplaneType(DBModelAirplaneType airplaneType) {
	this.airplaneType = airplaneType;
}

private double price;

  
  private static final String TABLE_FLIGHT = "flight";
  private static final String COLUMN_DEPARTING_FROM = "departing_from";
  private static final String COLUMN_ARRIVING_TO = "arriving_to";
  private static final String COLUMN_TIME_DEPARTING = "time_departing";
  private static final String COLUMN_PRICE = "price";
  private static final String COLUMN_ACCOUNT_ID = "account_id";
  private static final String COLUMN_FLIGHT_ID = "flight_id";
  private static final String COLUMN_AIRPLANE_TYPE_ID = "airplane_type";
  private static final String TABLE_BOOKED_FLIGHT = "booked_flight";
  private static final String TABLE_ACCOUNT = "account";
  
  public DBModelFlight(int id, DBModelAirport departingFrom, DBModelAirport arrivingTo, 
        Date timeDeparting, DBModelAirplaneType airplaneType, double price) 
  {
    this.id = id;
    this.departingFrom = departingFrom;
    this.arrivingTo = arrivingTo;
    this.timeDeparting = timeDeparting;
    this.airplaneType = airplaneType;
    this.price = price;
  }
  
  public DBModelFlight(DBModelAirport departingFrom, DBModelAirport arrivingTo, 
      Date timeDeparting, DBModelAirplaneType airplaneType, double price) 
    {
    this.departingFrom = departingFrom;
    this.arrivingTo = arrivingTo;
    this.timeDeparting = timeDeparting;
    this.airplaneType = airplaneType;
    this.price = price;
    }

  public DBModelFlight() {}

  public static Vector<DBModelFlight> getFlights(int fromAirportId, int toAirportId, Date date)
  {
    Vector<DBModelFlight> flightList = new Vector<DBModelFlight>();

      try (Connection conn = DBConnector.getConnection())
      {
        String query = "SELECT" + " id, " + COLUMN_DEPARTING_FROM + ", " + COLUMN_ARRIVING_TO + ", " + COLUMN_TIME_DEPARTING + ", "+ COLUMN_AIRPLANE_TYPE_ID + ", " + COLUMN_PRICE + 
            " FROM " + TABLE_FLIGHT +
            " WHERE " + COLUMN_DEPARTING_FROM + "=? AND " + COLUMN_ARRIVING_TO + "=? AND " + "DATE("+COLUMN_TIME_DEPARTING+")" + " =?";
        
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setInt(1, fromAirportId);
        statement.setInt(2, toAirportId);
        statement.setString(3, date.toString() );
        
        System.out.println(statement.toString());
        
        ResultSet result = statement.executeQuery();
        
        while (result.next())
        {
          int id = result.getInt(1);
          int departingId = result.getInt(COLUMN_DEPARTING_FROM);
          int arrivingId = result.getInt(COLUMN_ARRIVING_TO);
          Date departingTime = result.getDate(COLUMN_TIME_DEPARTING);
          int airplaneTypeId = result.getInt(COLUMN_AIRPLANE_TYPE_ID);
          double price = result.getDouble(COLUMN_PRICE);
          
          DBModelAirport airportFrom = DBModelAirport.getAirport(departingId);
          DBModelAirport airportTo = DBModelAirport.getAirport(arrivingId);
          
          DBModelAirplaneType airplaneType = DBModelAirplaneType.getAirplaneType(airplaneTypeId);
          
          flightList.add(new DBModelFlight(id, airportFrom, airportTo, departingTime, airplaneType, price));
        }
         
      } 
      catch (SQLException exception)
      {
        exception.printStackTrace();
      }
      
    return flightList;
  }
  
  public static Vector<DBModelFlight> getAllFlights()
  {
    Vector<DBModelFlight> flightList = new Vector<DBModelFlight>();

      try (Connection conn = DBConnector.getConnection())
      {
        String query = "SELECT" + " id, " + COLUMN_DEPARTING_FROM + ", " + COLUMN_ARRIVING_TO + ", " + COLUMN_TIME_DEPARTING + ", "+ COLUMN_AIRPLANE_TYPE_ID + ", " + COLUMN_PRICE + 
            " FROM " + TABLE_FLIGHT;
        
        Statement statement = conn.createStatement();
        
        System.out.println(statement.toString());
        
        ResultSet result = statement.executeQuery(query);
        
        while (result.next())
        {
          int id = result.getInt(1);
          int departingId = result.getInt(COLUMN_DEPARTING_FROM);
          int arrivingId = result.getInt(COLUMN_ARRIVING_TO);
          Date departingTime = result.getDate(COLUMN_TIME_DEPARTING);
          int airplaneTypeId = result.getInt(COLUMN_AIRPLANE_TYPE_ID);
          double price = result.getDouble(COLUMN_PRICE);
          
          DBModelAirport airportFrom = DBModelAirport.getAirport(departingId);
          DBModelAirport airportTo = DBModelAirport.getAirport(arrivingId);
          
          DBModelAirplaneType airplaneType = DBModelAirplaneType.getAirplaneType(airplaneTypeId);
          
          flightList.add(new DBModelFlight(id, airportFrom, airportTo, departingTime, airplaneType, price));
        }
         
      } 
      catch (SQLException exception)
      {
        exception.printStackTrace();
      }
      
    return flightList;
  }
  
  public static Vector<DBModelFlight> getBookedFlights()
  {
    Vector<DBModelFlight> flightList = new Vector<DBModelFlight>();
    
      try (Connection conn = DBConnector.getConnection())
      {
        String query = "SELECT flight.id, " + COLUMN_DEPARTING_FROM + ", " + COLUMN_ARRIVING_TO + ", " + COLUMN_TIME_DEPARTING + ", " + COLUMN_AIRPLANE_TYPE_ID + " ," + COLUMN_PRICE + 
                 " FROM " + TABLE_ACCOUNT +
                 " INNER JOIN " + TABLE_BOOKED_FLIGHT + " ON " + COLUMN_ACCOUNT_ID + " = account.id " + 
                 " INNER JOIN " + TABLE_FLIGHT + " ON " + COLUMN_FLIGHT_ID + " = flight.id" + 
                 " WHERE " + COLUMN_ACCOUNT_ID + "=?";
        
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setInt(1, DBModelAccount.loggedInUser.getId() );
        
        System.out.println(statement.toString());
        
        ResultSet result = statement.executeQuery();
        
        while (result.next())
        {
          int id = result.getInt(1);
          int departingId = result.getInt(COLUMN_DEPARTING_FROM);
          int arrivingId = result.getInt(COLUMN_ARRIVING_TO);
          Date departingTime = result.getDate(COLUMN_TIME_DEPARTING);
          int airplaneTypeId = result.getInt(COLUMN_AIRPLANE_TYPE_ID);
          double price = result.getDouble(COLUMN_PRICE);
          
          DBModelAirport airportFrom = DBModelAirport.getAirport(departingId);
          DBModelAirport airportTo = DBModelAirport.getAirport(arrivingId);
          
          DBModelAirplaneType airplaneType = DBModelAirplaneType.getAirplaneType(airplaneTypeId);
          
          flightList.add(new DBModelFlight(id, airportFrom, airportTo, departingTime, airplaneType, price));
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
      String query = "INSERT INTO " + TABLE_FLIGHT + 
          "(" + COLUMN_DEPARTING_FROM + "," + COLUMN_ARRIVING_TO + "," + COLUMN_TIME_DEPARTING + "," + COLUMN_AIRPLANE_TYPE_ID + "," + COLUMN_PRICE + ") " + 
          "VALUES (?, ?, ?, ?, ?)";

      PreparedStatement statement = conn.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);

      statement.setInt(1, departingFrom.getId());
      statement.setInt(2, arrivingTo.getId());
      statement.setDate(3, timeDeparting);
      statement.setInt(4, airplaneType.getId());
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
      String query = "UPDATE " + TABLE_FLIGHT + 
               " SET " + COLUMN_DEPARTING_FROM + "=?, " + COLUMN_ARRIVING_TO + "=?, " + COLUMN_TIME_DEPARTING + "=?, " + COLUMN_PRICE + "=? " +
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
      String query = "DELETE FROM " + TABLE_FLIGHT +
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

  public int getId() {
    return id;
  }

  

  
}
