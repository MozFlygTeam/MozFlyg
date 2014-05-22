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

import models.TableModelAirplaneType;

public class WindowAirplaneType extends JFrame implements ActionListener
{
	private static final String DELETE = "delete";
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
		
		buttonPanel.add(deleteButton);
		add(scrollPane, BorderLayout.CENTER);
		add(buttonPanel, BorderLayout.PAGE_END);
		
		pack();
		setLocationRelativeTo(null);
		
		
	}

	@Override
	public void actionPerformed(ActionEvent event)
	{
		switch (event.getActionCommand()) {
	      case DELETE:
	        int i = table.getSelectedRow();
	        tableModel.removeAirplaneType(i);
	        break;

	      }
		
	}
	
	
}
