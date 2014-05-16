package models;


import java.util.Vector;
import javax.swing.table.AbstractTableModel;


public class TableModelBookedFlight extends AbstractTableModel
{
  
	private static final long serialVersionUID = 1L;
	private Vector<DBModelBookedFlight> data = new Vector<DBModelBookedFlight>();
    
	private static final int ACCOUNT_ID = 0;
	private static final int FLIGHT_ID = 1;
	
	
	public void addBookedFlight(DBModelBookedFlight bookedFlight)
	{
		data.add(bookedFlight);
		fireTableDataChanged();    
	}
    
	public void removeBookedFlight(int row)
	{
    	 DBModelBookedFlight model = data.get(row);
    	 
    	 if(model.delete() == 1)
    	 {
    		 data.remove(row);
	    	 fireTableRowsDeleted(row, row);
    	 }
    }
    
    public void setBookedFlights(Vector<DBModelBookedFlight> bookedFlight)
    {
    	if(bookedFlight != null)
    	{
    		data = bookedFlight;
    	}
    	else
    	{
    		data = new Vector<DBModelBookedFlight>();
    	}
    	
    	fireTableDataChanged();
    }
    
    public TableModelBookedFlight()
    {
		setBookedFlights(DBModelBookedFlight.getAllBookedFlights());
	}
    
    public DBModelBookedFlight getBookedFlight(int row)
    {
    	return data.get(row);
    }
    
    //Tabellen beh�ver veta hur m�nga kolumner den ska visa
    @Override
    public int getColumnCount()
    {
        return 2;
    }
    
    //Tabellen beh�ver veta hur m�nga rader den ska visa
    @Override
    public int getRowCount()
    {
        return data.size();
    }
    
    //Tabellen beh�ver veta vilka kolumnnamn den ska visa
    @Override
    public String getColumnName(int col)
    {
        switch (col)
        {
	        case ACCOUNT_ID: return "Användar-ID";
	        case FLIGHT_ID: return "Flight-ID";
	        default: return "";
        }
    }
    
    //Tabellen beh�ver veta vilket v�rde som ska visas i en cell
    @Override
    public Object getValueAt(int row, int col)
    {
    	DBModelBookedFlight rowData = (DBModelBookedFlight) data.get(row);
        
        switch (col)
        {
	        case ACCOUNT_ID: return rowData.getAccountId();
	        case FLIGHT_ID: return rowData.getFlightId();
	        default: return "Unknown";
        }        
    }
    
    //Tabellen beh�ver veta vilka datatyper som anv�nds f�r att visa v�rdena p� ett anpassat s�tt. T.ex. bockningsruta f�r boolean.
    @Override
    public Class<?> getColumnClass(int col)
    {
        switch (col)
        {
	        case ACCOUNT_ID: return Integer.class;
	        case FLIGHT_ID: return Integer.class;
	        default: return Object.class;
        }
    }
}
