package GUI;

import models.DBModelAirport;
import models.TableModelAirport;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class WindowAirport extends JFrame implements ActionListener, ListSelectionListener{

	public DBModelAirport airportModel;
	public static TableModelAirport table;
	private JButton removeButton;
	private JTable recordTable;
	private static final String ADD = "add";
	private static final String DELETE = "delete";
	
	public WindowAirport() {

		table = new TableModelAirport();
		airportModel = new DBModelAirport();
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

	// funktionen f�r att l�gga till airport
	private void addAirport() {

		WindowAddAirport add = new WindowAddAirport();
		add.setVisible(true);
		
		DBModelAirport model =  add.getAirport();
		if(model != null){
		
			if (model.insert() == 1) {
				table.addAirport(model);
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
			int i = recordTable.getSelectedRow();
			table.removeAirport(i);
			break;

		}
	}
	
	public void valueChanged(ListSelectionEvent event)
	{
		removeButton.setEnabled(recordTable.getSelectedRowCount() > 0);
	}
}
