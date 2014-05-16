package models;

import java.util.Vector;
import javax.swing.table.AbstractTableModel;

	public class TableModelRutter extends AbstractTableModel
	{
	  
		private static final long serialVersionUID = 1L;
		private static Vector<DBModelRutt> data = new Vector<DBModelRutt>();
	    	
		public void addRutt(DBModelRutt rutt){
				data.add(rutt);
			  fireTableDataChanged();
			  
	    }
	   
	    public void removeRutt(int row)
	    {
	    	 DBModelRutt model = data.get(row);
	    	 if(model.delete() == 1){
	    	 data.remove(row);
	    	 fireTableRowsDeleted(row, row);
	    	}
	    
	    }
	    
	    public void setRutt(Vector<DBModelRutt> rutt)
	    {
	    	if(rutt != null)
	    	{
	    		data = rutt;
	    	}
	    	else
	    	{
	    		data = new Vector<DBModelRutt>();
	    	}
	    	
	    	fireTableDataChanged();
	    }
	    
	    public TableModelRutter(){
			setRutt(DBModelRutt.getAllRutt());
		}
	    
	    public DBModelRutt getRutt(int row)
	    {
	    	return data.get(row);
	    }
	    
	    @Override
	    public int getColumnCount() {
	        return 2;
	    }
	    @Override
	    public int getRowCount() {
	        return data.size();
	    }
	    @Override
	    public String getColumnName(int col) {
	        switch (col) {
	        case 0: return "FRÅN";
	        case 1: return "TILL";
	        }
	        return "";
	    }
	    @Override
	    public Object getValueAt(int row, int col)
	    {
	    	DBModelRutt rowData = (DBModelRutt) data.get(row);
	        
	        switch (col){ 
	        case 0: return rowData.getAirport_depart();
	        case 1: return rowData.getAirport_arrive();
	        }        
	        return "Unknown";
	    }
	    /*
	    @Override
	    public void setValueAt(Object cellData, int row, int col)
	    {
	    	DBModelRutt selectedRutt = (DBModelRutt) data.get(row);
	    	
	        switch (col)
	        {
		        case 1: selectedRutt.setAirport_arrive((DBModelAirport) cellData);
		        break;
		        case 2: selectedRutt.setAirport_depart((DBModelAirport) cellData);
		        break;
	        }
	       // selectedRutt.update();
	    }
	    
	    */
	    
	    @Override
	    public Class<?> getColumnClass(int col) {
	        switch (col)
	        {
		        case 0: return DBModelAirport.class;
		        case 1: return DBModelAirport.class;
	        }
	        return Object.class;
	    }
	    public boolean isCellEditable(int row, int col) {
	    	 switch (col) 
	    	 {
				 default: return true;
	    	 }

	    }

	}
	
	
	
