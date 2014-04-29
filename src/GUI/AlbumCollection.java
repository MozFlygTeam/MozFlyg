package GUI;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;

import record.misc.Album;
import record.misc.DBConnectionData;



public class AlbumCollection extends JFrame implements ActionListener, ListSelectionListener
{
	public AlbumModel albumModel;
	private JButton removeButton;
	private JTable recordTable;
	private static final String ADD = "add";
	private static final String DELETE = "delete";
	private static final String UPDATE = "update";
	
	public AlbumCollection()
	{
		albumModel = new AlbumModel();
		recordTable = new JTable(albumModel);
		JPanel contentPane = new JPanel(new BorderLayout());
		JScrollPane scrollPane = new JScrollPane(recordTable);
		JPanel bottomPanel = new JPanel(new FlowLayout());
			
		JButton addButton = new JButton("Lägg till");
			addButton.addActionListener(this);
			addButton.setActionCommand(ADD);
			
		removeButton = new JButton("Ta Bort");
			removeButton.addActionListener(this);
			removeButton.setActionCommand(DELETE);
			
		JButton updateButton = new JButton("Ändra Album");
			updateButton.addActionListener(this);
			updateButton.setActionCommand(UPDATE);
			
//		albumModel.addAlbum(new Album("Plattan","Artisten",2000));
		
		setContentPane(contentPane);
		add(scrollPane, BorderLayout.CENTER);
		add(bottomPanel, BorderLayout.PAGE_END);
		bottomPanel.add(addButton);
		bottomPanel.add(removeButton);
		bottomPanel.add(updateButton);
		
//		recordTable.getColumnModel().getColumn(0).setPreferredWidth();
		
		
		albumModel.setAlbums(readAlbums());
		
		
		pack();
		setLocationRelativeTo(null);
	}
	
    public static void main(String[] args) {
        
    	
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
            	DBConnectionData.setConnectionData("jdbc:mysql://localhost:3306/185292-mydb", "185292_ot76697", "3pig3n3tiC");
            	
            	AlbumCollection myRecords = new AlbumCollection();
                myRecords.setVisible(true);
            }
        });
    }
    
    private void enterAlbum()
	{
		AlbumDialog albumDialog = new AlbumDialog();
		albumDialog.setVisible(true);
		Album inputAlbum = albumDialog.getAlbum();
		
		if (inputAlbum != null)
		{
			inputAlbum.dbInsert();
//			System.out.println(readAlbums().elementAt(0).getAlbumId());
			albumModel.setAlbums(readAlbums());
		}
	}
    
    private void deleteAlbum()
    {
    	int row = recordTable.getSelectedRow();
    	Album selected = albumModel.getAlbum(row);
    	selected.dbDelete();
    	albumModel.removeAlbum(row);
    }
    
    private void updateAlbum() {
		// TODO Auto-generated method stub
    	int row = recordTable.getSelectedRow();
    	Album selectedAlbum = albumModel.getAlbum(row);
    	AlbumDialog editDialog = new AlbumDialog(selectedAlbum);
    	editDialog.setVisible(true);
    	selectedAlbum.dbUpdate();
    	albumModel.fireTableRowsUpdated(row, row);
	}

	@Override
	public void actionPerformed(ActionEvent event)
	{
		switch (event.getActionCommand())
		{
			case ADD: 
				enterAlbum();
				break;
			case DELETE: 
				deleteAlbum();
				break;
			case UPDATE: 
				updateAlbum();
				break;
			
		}
	}

	

	@Override
	public void valueChanged(ListSelectionEvent arg0)
	{
		// TODO Auto-generated method stub
		removeButton.setEnabled(recordTable.getSelectedRow() >= 0);
	}
	
	public Vector<Album> readAlbums()
	{
		
		try (Connection conn = DBConnectionData.getConnection())
		{
			String query = "SELECT album_id, album_name, artist_name, release_year FROM albums ORDER BY release_year DESC";
			
			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery(query);
			
			Vector<Album> dbAlbums = new Vector<Album>();
			
			while (result.next())
			{
				int albumId = result.getInt(1);
				String albumName = result.getString(2);
				String artistName = result.getString(3);
				int releaseYear = result.getInt(4);
				
				dbAlbums.add(new Album(albumId,albumName,artistName,releaseYear));
			}
			
			return dbAlbums;
			 
		} catch (SQLException exception) {
			exception.printStackTrace();
		}
		
		return null;
	}
	
}

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
