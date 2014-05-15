package models;

import java.util.Vector;
import javax.swing.table.AbstractTableModel;


public class TableModelBookedFlight extends AbstractTableModel
{
  
	private static final long serialVersionUID = 1L;
	private Vector<DBModelBookedFlight> data = new Vector<DBModelBookedFlight>();
    
 
	
	
	public void addBookedFlight(DBModelBookedFlight bookedFlight) {

			data.add(bookedFlight);
		  fireTableDataChanged();
    }
    
    public void removeBookedFlight(int row)
    {
    	 
    	 DBModelBookedFlight model = data.get(row);
    	 if(model.delete() == 1){
    	 data.remove(row);
    	 fireTableRowsDeleted(row, row);
    	}
    }
    
    public void setBookedFlights(Vector<DBModelBookedFlight> bookedFlight)
    {
    	if(bookedFlight != null)
    	{
    		data = bookedFlight
    	}
    	else
    	{
    		data = new Vector<DBModelBookedFlight>();
    	}
    	
    	fireTableDataChanged();
    }
    
    public TableModelBookedFlight(){
		setBookedFlights(DBModelBookedFlight.getAll());
	}
    
    public DBModelBookedFlight getBookedFlight(int row)
    {
    	return data.get(row);
    }
    
    @Override
    public int getColumnCount() {
        return 3;
    }
    @Override
    public int getRowCount() {
        return data.size();
    }
    @Override
    public String getColumnName(int col) {
        switch (col) {
        case 0: return "Användare";
        case 2: return "Flight";
        }
        return "";
    }
    @Override
    public Object getValueAt(int row, int col)
    {
    	DBModelBookedFlight rowData = (DBModelBookedFlight) data.get(row);
        
        switch (col) {
        case 0: return rowData.getAccountId();
        case 1: return rowData.getFlightId();
       
        }        
        return "Unknown";
    }
    
    @Override
    public void setValueAt(Object cellData, int row, int col)
    {
    	DBModelBookedFlight selectedBookedFlight = (DBModelBookedFlight) data.get(row);
    	
        switch (col)
        {
	        case 0: selectedBookedFlight.setAccountName((String) cellData);
	        break;
	        case 1: selectedBookedFlight.setFlightId((String) cellData);
	        break;
        }
        selectedBookedFlight.update();
    }
    @Override
    public Class<?> getColumnClass(int col) {
        switch (col)
        {
	        case 0: return Integer.class;
	        case 1: return Integer.class;
        }
        return Object.class;
    }
    public boolean isCellEditable(int row, int col) {
    	 switch (col) 
    	 {
	         case 0: return false;
	         default: return true;
    	 }

    }

}
