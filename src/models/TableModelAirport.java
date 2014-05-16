package models;

import java.util.Vector;
import javax.swing.table.AbstractTableModel;


public class TableModelAirport extends AbstractTableModel
{
  
	private static final long serialVersionUID = 1L;
	private Vector<DBModelAirport> data = new Vector<DBModelAirport>();
    	
	public void addAirport(DBModelAirport airport){
			data.add(airport);
		  fireTableDataChanged();
    }
    
    public void removeAirport(int row)
    {
    	 DBModelAirport model = data.get(row);
    	 if(model.delete() == 1){
    	 data.remove(row);
    	 fireTableRowsDeleted(row, row);
    	}
    }
    
    public void setAirports(Vector<DBModelAirport> airport)
    {
    	if(airport != null)
    	{
    		data = airport;
    	}
    	else
    	{
    		data = new Vector<DBModelAirport>();
    	}
    	
    	fireTableDataChanged();
    }
    
    public TableModelAirport(){
		setAirports(DBModelAirport.getAll());
	}
    
    public DBModelAirport getAirport(int row)
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
        case 0: return "id";
        case 1: return "Flygplats";
        case 2: return "Stad";
        }
        return "";
    }
    @Override
    public Object getValueAt(int row, int col)
    {
    	DBModelAirport rowData = (DBModelAirport) data.get(row);
        
        switch (col) {
        case 0: return new Integer(rowData.getId());
        case 1: return rowData.getAirportName();
        case 2: return rowData.getCityName();
       
        }        
        return "Unknown";
    }
    
    @Override
    public void setValueAt(Object cellData, int row, int col)
    {
    	DBModelAirport selectedAirport = (DBModelAirport) data.get(row);
    	
        switch (col)
        {
	        case 1: selectedAirport.setAirportName((String) cellData);
	        break;
	        case 2: selectedAirport.setCityName((String) cellData);
	        break;
        }
        selectedAirport.update();
    }
    @Override
    public Class<?> getColumnClass(int col) {
        switch (col)
        {
	        case 0: return Integer.class;
	        case 1: return String.class;
	        case 2: return String.class;
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
