package models;

import java.util.Vector;

import javax.swing.table.AbstractTableModel;

public class TableModelAirplaneType extends AbstractTableModel
{
	private Vector<DBModelAirplaneType> airplaneTypeList = new Vector<DBModelAirplaneType>();
	
	private static final int MODEL_NAME_COLUMN = 0;
	private static final int PASSENGER_CAPACITY_COLUMN = 1;
	private static final int VELOCITY_COLUMN = 2;
	private static final int FUEL_CONSUMPTION_COLUMN = 3;
	
	public void addAirplaneType(DBModelAirplaneType airplaneType)
	{
		airplaneTypeList.add(airplaneType);
		fireTableDataChanged();    
	}
    
	public void removeAirplaneType(int row)
	{
		DBModelAirplaneType selectedAirplaneType = airplaneTypeList.get(row);
    	 
    	 if(selectedAirplaneType.delete() == 1)
    	 {
    		 airplaneTypeList.remove(row);
	    	 fireTableRowsDeleted(row, row);
    	 }
    }
    
    public void populateAirplaneTypes(Vector<DBModelAirplaneType> airplaneTypesDB)
    {
    	if(airplaneTypeList != null)
    	{
    		airplaneTypeList = airplaneTypesDB;
    	}
    	else
    	{
    		airplaneTypeList = new Vector<DBModelAirplaneType>();
    	}
    	fireTableDataChanged();
    }
	
	@Override
	public int getColumnCount()
	{
		return 4;
	}

	@Override
	public int getRowCount()
	{
		return airplaneTypeList.size();
	}

	@Override
	public Object getValueAt(int row, int column) 
	{
		DBModelAirplaneType airplaneType = (DBModelAirplaneType) airplaneTypeList.get(row);
        
        switch (column)
        {
	        case MODEL_NAME_COLUMN: 
	        	return airplaneType.getModelName();
	        case PASSENGER_CAPACITY_COLUMN: 
	        	return airplaneType.getPassengerCapacity();
	        case VELOCITY_COLUMN: 
	        	return airplaneType.getVelocity();
	        case FUEL_CONSUMPTION_COLUMN: 
	        	return airplaneType.getFuelConsumption();
	        default: return "Unknown";
        }        
	}
	
	/**
	 * @param cellData
	 * Redigerat cellinneh�ll tas emot som ett generellt objekt i parametern cellData ovan.
	 * Vi m�ste tala om vad det �r f�r objekt vi f�tt, t.ex. en textstr�ng, innan det kan sparas i kontot 
	 */
	@Override
    public void setValueAt(Object cellData, int row, int col)
    {
		DBModelAirplaneType editedAirplaneType = (DBModelAirplaneType) airplaneTypeList.get(row);
    	
        switch (col)
        {
        case MODEL_NAME_COLUMN: 
        	editedAirplaneType.setModelName((String) cellData);
        	break;
        case PASSENGER_CAPACITY_COLUMN: 
        	editedAirplaneType.setPassengerCapacity((Integer) cellData);
        	break;
        case VELOCITY_COLUMN: 
        	editedAirplaneType.setVelocity((Integer) cellData);
        	break;
        case FUEL_CONSUMPTION_COLUMN: 
        	editedAirplaneType.setFuelConsumption((Double) cellData);
        	break;
        }
        editedAirplaneType.update();
    }
	
	@Override
    public Class<?> getColumnClass(int col)
    {
        switch (col)
        {
	        case MODEL_NAME_COLUMN: return String.class;
	        case PASSENGER_CAPACITY_COLUMN: return Integer.class;
	        case VELOCITY_COLUMN: return Integer.class;
	        case FUEL_CONSUMPTION_COLUMN: return Double.class;
	        default: return Object.class;
        }
    }
	
	@Override
	public boolean isCellEditable(int row, int col)
	{
		return true;
	}

}
