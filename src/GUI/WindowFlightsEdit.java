package GUI;


	import models.DBModelAirport;
import models.DBModelFlight;
import models.TableModelFlight;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

	public class WindowFlightsEdit extends JFrame implements ActionListener, ListSelectionListener{

		public DBModelFlight flightModel;
		public static TableModelFlight table;
		private JButton removeButton;
		private JButton editButton;
		private JTable recordTable;
		
		JComboBox<DBModelAirport> droppDownFrom;
		JComboBox<DBModelAirport> droppDownTo;
		JSpinner spinnerDate;
		JButton searchButton;
		
		
		private static final String SEARCH = "search";	
		private static final String ADD = "add";
		private static final String DELETE = "delete";
		private static final String EDIT = "editera";
		
		public WindowFlightsEdit() {

			table = new TableModelFlight();
			flightModel = new DBModelFlight();
			recordTable = new JTable(table);
			recordTable.getSelectionModel().addListSelectionListener(this);
		
			JPanel searchPane = new JPanel();
			 droppDownFrom = new JComboBox(DBModelAirport.getAll());
			 
			 searchPane.add(droppDownFrom);
			 
			 droppDownTo = new JComboBox(DBModelAirport.getAll()); 
			 searchPane.add(droppDownTo);
			 
			 spinnerDate = new JSpinner(new SpinnerDateModel());
			 searchPane.add(spinnerDate);
			 
			 searchButton = new JButton("Search");
			 searchButton.addActionListener(this);
			 searchPane.add(searchButton);

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
			System.out.print(model);
			if(model != null){
			
				if (model.insert() == 1) {
				table.addFlight(model);
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
				int i = recordTable.getSelectedRow();
				table.removeFlight(i);
				break;
			case EDIT:
				int j = recordTable.getSelectedRow();
				DBModelFlight fl = table.getFlight(j);
				WindowEditFlight edit = new WindowEditFlight(fl);
				edit.setVisible(true);
				
				if(edit.getFlight() !=null){
					DBModelFlight model = edit.getFlight();
					model.update();
					table.updateFlight(j,model);
					
				}
				
				break;
			case SEARCH:
				DBModelFlight.getFlights(1,5);
				break;
			}
			
			}
		
		public void valueChanged(ListSelectionEvent event)
		{
			removeButton.setEnabled(recordTable.getSelectedRowCount() > 0);
			editButton.setEnabled(recordTable.getSelectedRowCount() > 0);
		}
	}
	
	

