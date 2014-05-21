package GUI;


import models.DBModelAirport;
import models.DBModelFlight;
import models.TableModelFlight;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.SpinnerDateModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableColumn;

	public class WindowFlightsEdit extends JFrame implements ActionListener, ListSelectionListener{

		public DBModelFlight flightModel;
		public static TableModelFlight tableModelFlight;
		private JButton removeButton;
		private JButton editButton;
		private JTable flightTable;
		
		JComboBox<DBModelAirport> droppDownFrom;
		JComboBox<DBModelAirport> droppDownTo;
		JSpinner spinnerDate;
		JButton searchButton;
		
		
		private static final String SEARCH = "search";	
		private static final String ADD = "add";
		private static final String DELETE = "delete";
		private static final String EDIT = "editera";
		
		public WindowFlightsEdit() {

			tableModelFlight = new TableModelFlight();
			flightModel = new DBModelFlight();
			flightTable = new JTable(tableModelFlight);
			
			
			TableColumn departingColumn = flightTable.getColumnModel().getColumn(TableModelFlight.DEPARTING_FROM_COLUMN);
			departingColumn.setCellEditor(new DefaultCellEditor(new JComboBox<DBModelAirport>(DBModelAirport.getAll())));
			
			
			flightTable.getSelectionModel().addListSelectionListener(this);
		
			JPanel searchPane = new JPanel();
			 droppDownFrom = new JComboBox<DBModelAirport>(DBModelAirport.getAll());
			 
			 searchPane.add(droppDownFrom);
			 
			 droppDownTo = new JComboBox<DBModelAirport>(DBModelAirport.getAll()); 
			 searchPane.add(droppDownTo);
			 
			 spinnerDate = new JSpinner(new SpinnerDateModel());
			 searchPane.add(spinnerDate);
			 
			 searchButton = new JButton("Search");
			 searchButton.addActionListener(this);
			 searchButton.setActionCommand(SEARCH);
			 searchPane.add(searchButton);

			JPanel contentPane = new JPanel(new BorderLayout());
			JScrollPane scrollPane = new JScrollPane(flightTable);
			JPanel bottomPanel = new JPanel(new FlowLayout());

			JButton addButton = new JButton("Lägg till");
			addButton.addActionListener(this);
			addButton.setActionCommand(ADD);

			removeButton = new JButton("Ta Bort");
			removeButton.addActionListener(this);
			removeButton.setActionCommand(DELETE);
			removeButton.setEnabled(false);
			
			
			editButton = new JButton("Editera");
			editButton.addActionListener(this);
			editButton.setActionCommand(EDIT);
			editButton.setEnabled(false);
			
	
			setContentPane(contentPane);
			add(searchPane, BorderLayout.NORTH);
			add(scrollPane, BorderLayout.CENTER);
			add(bottomPanel, BorderLayout.PAGE_END);
			bottomPanel.add(addButton);
			bottomPanel.add(removeButton);
			bottomPanel.add(editButton);
			
			
			pack();
			setLocationRelativeTo(null);
		} 
		
	
		private void addFlight() {
			
			WindowAddFlight add = new WindowAddFlight();
			add.setVisible(true);
			
			DBModelFlight model =  add.getFlight();
			if(model != null){
			
				if (model.insert() == 1) {
				tableModelFlight.addFlight(model);
				}
			}
			
		}
		
		@Override
		public void actionPerformed(ActionEvent event) {

			switch (event.getActionCommand()) {
			case ADD:
				addFlight();
				break;
			case DELETE:
				int i = flightTable.getSelectedRow();
				tableModelFlight.removeFlight(i);
				break;
			case SEARCH:
				DBModelAirport arFr = (DBModelAirport) droppDownFrom.getSelectedItem();
				DBModelAirport arTo = (DBModelAirport) droppDownTo.getSelectedItem();
				SpinnerDateModel sp = (SpinnerDateModel)spinnerDate.getModel();
				
				java.sql.Date sqlDate = new java.sql.Date(sp.getDate().getTime());
				System.out.print("SEARCH METODEN !!");
				tableModelFlight.setFlight(DBModelFlight.getFlights(arFr.getId(),arTo.getId(), sqlDate));
				
				break;
			}
			
			}
		
		public void valueChanged(ListSelectionEvent event)
		{
			removeButton.setEnabled(flightTable.getSelectedRowCount() > 0);
			editButton.setEnabled(flightTable.getSelectedRowCount() > 0);
		}
	}
	
	

