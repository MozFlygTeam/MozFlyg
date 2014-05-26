package models;

import java.util.Vector;

import javax.swing.table.AbstractTableModel;


public class TableModelFlight extends AbstractTableModel
{
  
	private static final long serialVersionUID = 1L;
	private Vector<DBModelFlight> data = new Vector<DBModelFlight>();
	
	public static final int ID_COLUMN = 0;
	public static final int DEPARTING_FROM_COLUMN = 1;
	public static final int ARRIVING_TO_COLUMN = 2;
	public static final int DEPARTING_TIME_COLUMN = 3;
	public static final int AIRPLANE_TYPE_COLUMN = 4;
	public static final int PRICE_COLUMN = 5;

	
	public void addFlight(DBModelFlight flight)
	{
			data.add(flight);
			fireTableDataChanged();
    }
    
	public void addFlightBooking(int row)
	{
		 DBModelFlight flight = data.get(row);
		 
		 if(DBModelBookedFlight.addBooking(DBModelAccount.loggedInUser.getId(), flight.getId()) == 1)
		 {
			 data.remove(row);
			 fireTableRowsDeleted(row, row);
		 }
	}
	
	public void removeFlightBooking(int row)
	{
		 DBModelFlight flight = data.get(row);
		 
		 if(DBModelBookedFlight.deleteBooking(DBModelAccount.loggedInUser.getId(), flight.getId()) == 1)
		 {
			 data.remove(row);
			 fireTableRowsDeleted(row, row);
		 }
	}
	
	
    public void deleteFlight(int row)
    {	 
    	 DBModelFlight model = data.get(row);
    	 if(model.delete() == 1){
    	 data.remove(row);
    	 fireTableRowsDeleted(row, row);
    	}
    }
    
    public void updateFlight(int row, DBModelFlight model)
    {
    	 data.setElementAt(model, row);
    	 fireTableRowsUpdated(row, row);
    	
    }
    
    public void setFlight(Vector<DBModelFlight> flight)
    {
    	if(flight != null)
    	{
    		data = flight;
    	}
    	else
    	{
    		data = new Vector<DBModelFlight>();
    	}
    	
    	fireTableDataChanged();
    }
    
    public DBModelFlight getFlight(int row)
    {
    	return data.get(row);
    }
    
    @Override
    public int getColumnCount() {
        return 6;
    }
    
    
    @Override
    public int getRowCount() {
        return data.size();
    }
    
    @Override
    public String getColumnName(int col)
    {
        switch (col) {
        case ID_COLUMN: return "id";
        case DEPARTING_FROM_COLUMN: return "Avreseort";
        case ARRIVING_TO_COLUMN: return "Destination";
        case DEPARTING_TIME_COLUMN: return "Avg��ngstid";
        case AIRPLANE_TYPE_COLUMN: return "Flygplanstyp";
        case PRICE_COLUMN: return "Pris";
        }
        return "";
    }
    
    @Override
    public void setValueAt(Object cellData, int row, int col)
    {
    	DBModelFlight selectedFlight = (DBModelFlight) data.get(row);
    	
        switch (col)
        {
	        case DEPARTING_FROM_COLUMN: selectedFlight.setDepartingFrom((DBModelAirport) cellData);
	        break;
	        case ARRIVING_TO_COLUMN: selectedFlight.setArrivingTo((DBModelAirport) cellData);
	        break;
	        case AIRPLANE_TYPE_COLUMN: selectedFlight.setAirplaneType((DBModelAirplaneType) cellData);
	        break;
        }
        selectedFlight.update();
    }
    
    @Override
    public Object getValueAt(int row, int col)
    {
    	DBModelFlight flight = (DBModelFlight) data.get(row);
        
        switch (col) {
        case ID_COLUMN: return flight.getId();
        case DEPARTING_FROM_COLUMN: return flight.getDepartingFrom();
        case ARRIVING_TO_COLUMN: return flight.getArrivingTo();
        case DEPARTING_TIME_COLUMN: return flight.getTimeDeparting();
        case AIRPLANE_TYPE_COLUMN: return flight.getAirplaneType();
        case PRICE_COLUMN: return flight.getPrice();
        }        
        return "Unknown";
    }
    
    @Override
    public Class<?> getColumnClass(int col) {
        switch (col)
        {
	        case ID_COLUMN: return Integer.class;
	        case DEPARTING_FROM_COLUMN: return DBModelAirport.class;
	        case ARRIVING_TO_COLUMN: return DBModelAirport.class;
	        case AIRPLANE_TYPE_COLUMN: return DBModelAirplaneType.class;
        }
        return Object.class;
    }
    
    public boolean isCellEditable(int row, int col) {
    	 
    	if(DBModelAccount.loggedInUser.isAdmin()){
	       return true;
    	}
    	else{
    		return false;
    	}
    }

}