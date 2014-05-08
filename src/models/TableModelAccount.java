package models;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;


public class TableModelAccount extends AbstractTableModel
{
  
	private static final long serialVersionUID = 1L;
	private Vector<DBModelAccount> data = new Vector<DBModelAccount>();
    
 
	
	
	public void addAccount(DBModelAccount account) {

			data.add(account);
		  fireTableDataChanged();
    }
    
    public void removeAccount(int row)
    {
    	 
    	 DBModelAccount model = data.get(row);
    	 if(model.delete() == 1){
    	 data.remove(row);
    	 fireTableRowsDeleted(row, row);
    	}
    }
    
    public void setAccounts(Vector<DBModelAccount> account)
    {
    	if(account != null)
    	{
    		data = account;
    	}
    	else
    	{
    		data = new Vector<DBModelAccount>();
    	}
    	
    	fireTableDataChanged();
    }
    
    public TableModelAccount(){
		setAccounts(DBModelAccount.getAll());
	}
    
    public DBModelAccount getAccount(int row)
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
        case 1: return "Användarnamn";
        case 2: return "Lösenord";
        }
        return "";
    }
    @Override
    public Object getValueAt(int row, int col)
    {
    	DBModelAccount rowData = (DBModelAccount) data.get(row);
        
        switch (col) {
        case 0: return new Integer(rowData.getId());
        case 1: return rowData.getUsername();
        case 2: return rowData.getPassword();
       
        }        
        return "Unknown";
    }
    
    @Override
    public void setValueAt(Object cellData, int row, int col)
    {
    	DBModelAccount selectedAccount = (DBModelAccount) data.get(row);
    	
        switch (col)
        {
	        case 1: selectedAccount.setUsername((String) cellData);
	        break;
	        case 2: selectedAccount.setPassword((String) cellData);
	        break;
        }
        selectedAccount.update();
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
