package models;

import java.util.Vector;

import javax.swing.table.AbstractTableModel;

class AlbumModel extends AbstractTableModel 
{
    private Vector<Album> data = new Vector<Album>();
    
    public void addAlbum(Album album) {
        data.addElement(album);
        fireTableDataChanged();
    }
    
    public void removeAlbum(int row)
    {
    	data.remove(row);
    	fireTableRowsDeleted(row, row);
    }
    
    public void setAlbums(Vector<Album> albums)
    {
    	if(albums != null)
    	{
    		data = albums;
    	}
    	else
    	{
    		data = new Vector<Album>();
    	}
    	
    	fireTableDataChanged();
    }
    
    public Album getAlbum(int row)
    {
    	return data.get(row);
    }
    
    @Override
    public int getColumnCount() {
        return 4;
    }
    @Override
    public int getRowCount() {
        return data.size();
    }
    @Override
    public String getColumnName(int col) {
        switch (col) {
        case 0: return "Album";
        case 1: return "Artist";
        case 2: return "Utgivningsår";
        case 3: return "Databas ID";
        }
        return "";
    }
    @Override
    public Object getValueAt(int row, int col)
    {
    	Album rowData = (Album) data.get(row);
        
        switch (col) {
        case 0: return rowData.getAlbumName();
        case 1: return rowData.getArtistName();
        case 2: return new Integer(rowData.getReleaseYear());
        case 3: return new Integer(rowData.getAlbumId());
        }        
        return "Unknown";
    }
    @Override
    public Class<?> getColumnClass(int col) {
        switch (col) {
        case 0: return String.class;
        case 1: return String.class;
        case 2: return Integer.class;
        case 3: return Integer.class;
        }
        return Object.class;
    }
    public boolean isCellEditable(int row, int col) {
        return false;
    }
}
