package models;

import java.util.Vector;

import javax.swing.table.AbstractTableModel;

public class TableModelAirplaneType extends AbstractTableModel
{
	private Vector<DBModelAirplaneType> airplaneTypes = new Vector<DBModelAirplaneType>();
	
	private static final int MODEL_NAME_COLUMN = 0;
	private static final int PASSENGER_CAPACITY_COLUMN = 1;
	private static final int VELOCITY_COLUMN = 2;
	private static final int FUEL_CONSUMPTION_COLUMN = 3;
	
	@Override
	public int getColumnCount()
	{
		return 4;
	}

	@Override
	public int getRowCount()
	{
		return airplaneTypes.size();
	}

	@Override
	public Object getValueAt(int row, int column) 
	{
		DBModelAirplaneType airplaneType = (DBModelAirplaneType) airplaneTypes.get(row);
        
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

}
