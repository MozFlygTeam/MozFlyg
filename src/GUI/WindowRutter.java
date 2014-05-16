package GUI;

import models.DBModelAirport;
import models.DBModelRutt;
import models.TableModelAirport;
import models.TableModelRutter;

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

public class WindowRutter extends JFrame implements ActionListener, ListSelectionListener{

	public DBModelAirport rutterModel;
	public static TableModelRutter table;
	private JButton removeButton;
	private JTable recordTable;
	private static final String ADD = "add";
	private static final String DELETE = "delete";
	
	public WindowRutter() {

		table = new TableModelRutter();
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
	private void addRutt() {

		WindowAddRutt add = new WindowAddRutt();
		add.setVisible(true);
		
		DBModelRutt model =  add.getRutt();
		if(model != null){
		
			if (model.insert() == 1) {
				table.addRutt(model);
			}
		}
		
	}

	// Ska skapas

	@Override
	public void actionPerformed(ActionEvent event) {

		switch (event.getActionCommand()) {
		case ADD:
			addRutt();
			break;
		case DELETE:
			int i = recordTable.getSelectedRow();			
			table.removeRutt(i);
			break;

		}
	}
	
	public void valueChanged(ListSelectionEvent event)
	{
		removeButton.setEnabled(recordTable.getSelectedRowCount() > 0);
	}

	
}