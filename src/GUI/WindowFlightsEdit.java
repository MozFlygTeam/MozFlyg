package GUI;


	import models.DBConnector;
	import models.DBModelFlight;
	import models.TableModelFlight;
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
	import javax.swing.event.TableModelEvent;
	import javax.swing.event.TableModelListener;
	import javax.swing.table.AbstractTableModel;

	public class WindowFlightsEdit extends JFrame implements ActionListener, ListSelectionListener{

		public DBModelFlight flightModel;
		public static TableModelFlight table;
		private JButton removeButton;
		private JTable recordTable;
		private static final String ADD = "add";
		private static final String DELETE = "delete";
		
		public WindowFlightsEdit() {

			table = new TableModelFlight();
			flightModel = new DBModelFlight();
			recordTable = new JTable(table);
			recordTable.getSelectionModel().addListSelectionListener(this);
		

			JPanel contentPane = new JPanel(new BorderLayout());
			JScrollPane scrollPane = new JScrollPane(recordTable);
			JPanel bottomPanel = new JPanel(new FlowLayout());

			JButton addButton = new JButton("Lägg till");
			addButton.addActionListener(this);
			addButton.setActionCommand(ADD);

			removeButton = new JButton("Ta Bort");
			removeButton.addActionListener(this);
			removeButton.setActionCommand(DELETE);
			removeButton.setEnabled(false);

			setContentPane(contentPane);
			add(scrollPane, BorderLayout.CENTER);
			add(bottomPanel, BorderLayout.PAGE_END);
			bottomPanel.add(addButton);
			bottomPanel.add(removeButton);

			pack();
			setLocationRelativeTo(null);
		} 
			//
		// funktionen f�r att l�gga till airport
		//private void addFlight() {

		//	Addflight add = new Addflight();
		//	add.setVisible(true);
			
		//	DBModelFlight model =  add.getFlight();
		//	System.out.print(model);
		//	if(model != null){
			//
			//	if (model.insert() == 1) {
		//			table.addAirport(model);
			//	}
		//	}
			
		//}

		// Ska skapas

		@Override
		public void actionPerformed(ActionEvent event) {

			switch (event.getActionCommand()) {
			case ADD:
				WindowAddFlight add = new WindowAddFlight();
				add.setVisible(true);
				
				break;
			case DELETE:
				int i = recordTable.getSelectedRow();
				//table.removeFlight(i);
				break;

			}
		}
		
		public void valueChanged(ListSelectionEvent event)
		{
			removeButton.setEnabled(recordTable.getSelectedRowCount() > 0);
		}
	}
	
	

