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
    
	private static final int ID_COLUMN = 0;
	private static final int USERNAME_COLUMN = 1;
	private static final int PASSWORD_COLUMN = 2;
	private static final int IS_ADMIN_COLUMN = 3;
	
	
	public void addAccount(DBModelAccount account)
	{
		data.add(account);
		fireTableDataChanged();    
	}
    
	public void removeAccount(int row)
	{
    	 DBModelAccount model = data.get(row);
    	 
    	 if(model.delete() == 1)
    	 {
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
    
    public TableModelAccount()
    {
		setAccounts(DBModelAccount.getAllAccounts());
	}
    
    public DBModelAccount getAccount(int row)
    {
    	return data.get(row);
    }
    
    @Override
    public int getColumnCount()
    {
        return 4;
    }
    
    @Override
    public int getRowCount()
    {
        return data.size();
    }
    
    @Override
    public String getColumnName(int col)
    {
        switch (col)
        {
	        case ID_COLUMN: return "id";
	        case USERNAME_COLUMN: return "Användarnamn";
	        case PASSWORD_COLUMN: return "Lösenord";
	        case IS_ADMIN_COLUMN: return "Admin";
	        default: return "";
        }
    }
    
    @Override
    public Object getValueAt(int row, int col)
    {
    	DBModelAccount rowData = (DBModelAccount) data.get(row);
        
        switch (col)
        {
	        case ID_COLUMN: return new Integer(rowData.getId());
	        case USERNAME_COLUMN: return rowData.getUsername();
	        case PASSWORD_COLUMN: return rowData.getPassword();
	        case IS_ADMIN_COLUMN: return rowData.isAdmin();
	        default: return "Unknown";
        }        
    }
    
    @Override
    public void setValueAt(Object cellData, int row, int col)
    {
    	DBModelAccount selectedAccount = (DBModelAccount) data.get(row);
    	
        switch (col)
        {
	        case USERNAME_COLUMN: selectedAccount.setUsername((String) cellData);
	        break;
	        case PASSWORD_COLUMN: selectedAccount.setPassword((String) cellData);
	        break;
	        case IS_ADMIN_COLUMN: selectedAccount.setAdmin((boolean) cellData);
	        break;
        }
        selectedAccount.update();
    }
    
    @Override
    public Class<?> getColumnClass(int col)
    {
        switch (col)
        {
	        case ID_COLUMN: return Integer.class;
	        case USERNAME_COLUMN: return String.class;
	        case PASSWORD_COLUMN: return String.class;
	        case IS_ADMIN_COLUMN: return Boolean.class;
	        default: return Object.class;
        }
    }
    
    @Override
    public boolean isCellEditable(int row, int col)
    {
    	switch (col) 
    	{
    		case ID_COLUMN: return false;
	        default: return true;
    	}
    }

}
