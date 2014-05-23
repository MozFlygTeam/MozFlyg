package GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import models.DBModelAirplaneType;
import models.TableModelAirplaneType;

public class WindowAirplaneType extends JFrame implements ActionListener
{
	private static final String DELETE = "delete";
	private static final String NEW = "new";
	private JTable table;
	private TableModelAirplaneType tableModel;
	
	public WindowAirplaneType()
	{
		tableModel = new TableModelAirplaneType();
		table = new JTable(tableModel);
		JScrollPane scrollPane = new JScrollPane(table);
		
		JPanel buttonPanel =  new JPanel(new FlowLayout());
		
		JButton deleteButton = new JButton("Delete");
		deleteButton.addActionListener(this);
		deleteButton.setActionCommand(DELETE);
		
		JButton addButton = new JButton("Add");
		addButton.addActionListener(this);
		addButton.setActionCommand(NEW);
		
		buttonPanel.add(addButton);
		buttonPanel.add(deleteButton);
		add(scrollPane, BorderLayout.CENTER);
		add(buttonPanel, BorderLayout.PAGE_END);
		
		setTitle("Hantera Flygplanstyper");
		pack();
		setLocationRelativeTo(null);
		
		
	}

	@Override
	public void actionPerformed(ActionEvent event)
	{
		switch (event.getActionCommand()) 
		{
	      case NEW:
	    	  tableModel.addAirplaneType(new DBModelAirplaneType("AIRPLANE MODEL", 0, 0, 0));
	    	  break;
	      case DELETE:
	    	  tableModel.removeAirplaneType(table.getSelectedRow());
	    	  break;
	      }
		
	}
	
	
}
