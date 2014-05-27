package GUI;

import models.DBModelAirport;
import models.TableModelAirport;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class WindowAirport extends JFrame implements ActionListener, ListSelectionListener{

	public DBModelAirport airportModel;
	public static TableModelAirport tableModelAirport;
	private JButton removeButton;
	private JTable airportTable;
	private static final String ADD = "add";
	private static final String DELETE = "delete";
	
	public WindowAirport() {
		
		setTitle("Hantera flygplatser");
		
		JComboBox<DBModelAirport> va = new JComboBox<DBModelAirport>(DBModelAirport.getAll());
		
		tableModelAirport = new TableModelAirport();
		airportModel = new DBModelAirport();
		airportTable = new JTable(tableModelAirport);

		JPanel contentPane = new JPanel(new BorderLayout());
		JScrollPane scrollPane = new JScrollPane(airportTable);
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

	// funktionen f�r att l�gga till airport
	private void addAirport() {

		WindowAddAirport add = new WindowAddAirport();
		add.setVisible(true);
		
		DBModelAirport model =  add.getAirport();
		if(model != null){
		
			if (model.insert() == 1) {
				tableModelAirport.addAirport(model);
			}
		}
		
	}

	// Ska skapas

	@Override
	public void actionPerformed(ActionEvent event) {

		switch (event.getActionCommand()) {
		case ADD:
			addAirport();
			break;
		case DELETE:
			int i = airportTable.getSelectedRow();
			tableModelAirport.removeAirport(i);
			break;

		}
	}
	
	public void valueChanged(ListSelectionEvent event)
	{
		removeButton.setEnabled(airportTable.getSelectedRowCount() > 0);
	}
}
