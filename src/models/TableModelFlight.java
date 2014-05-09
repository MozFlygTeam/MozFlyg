package models;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;


public class TableModelFlight extends AbstractTableModel
{
  
	private static final long serialVersionUID = 1L;
	private Vector<DBModelFlight> data = new Vector<DBModelFlight>();

	public void addAirport(DBModelFlight flight) {

			data.add(flight);
		  fireTableDataChanged();
    }
    
    public void removeAirport(int row)
    {
    	 
    	 DBModelFlight model = data.get(row);
    	 if(model.delete() == 1){
    	 data.remove(row);
    	 fireTableRowsDeleted(row, row);
    	}
    }
    
    public void setAirports(Vector<DBModelFlight> flight)
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
    
    public TableModelFlight() {
    	setAirports(DBModelFlight.getFlights());
	}
    
    public DBModelFlight getAirport(int row)
    {
    	return data.get(row);
    }
    
    @Override
    public int getColumnCount() {
        return 5;
    }
    @Override
    public int getRowCount() {
        return data.size();
    }
    @Override
    public String getColumnName(int col) {
        switch (col) {
        case 0: return "id";
        case 1: return "Avreseort";
        case 2: return "Destination";
        case 3: return "Avgångstid";
        case 4: return "Pris";
        }
        return "";
    }
    
    @Override
    public Object getValueAt(int row, int col)
    {
    	DBModelFlight rowData = (DBModelFlight) data.get(row);
        
        switch (col) {
        case 0: return rowData.getId();
        case 1: return rowData.getDepartingFrom();
        case 2: return rowData.getArrivingTo();
        case 3: return rowData.getTimeDeparting();
        case 4: return rowData.getPrice();
       
        }        
        return "Unknown";
    }
    
   /*
    *  @Override(non-Javadoc)
    * @see javax.swing.table.AbstractTableModel#setValueAt(java.lang.Object, int, int)
    /*
    public void setValueAt(Object cellData, int row, int col)
    {
    	DBModelFlight selectedflight = (DBModelFlight) data.get(row);
    	
        switch (col)
        {
	        case 1: selectedflight.setDepartingFrom((DBModelAirport) cellData);
	        break;
	        case 2: selectedflight.setArrivingTo((DBModelAirport) cellData);
	        break;
	        case 2: 
        }
        selectedflight.update();
    }
    */
    @Override
    public Class<?> getColumnClass(int col) {
        switch (col)
        {
	        case 0: return Integer.class;
	        case 1: return DBModelAirport.class;
	        case 2: return DBModelAirport.class;
        }
        return Object.class;
    }
    
    public boolean isCellEditable(int row, int col) {
    	 
	       return false;
	     
    }

}