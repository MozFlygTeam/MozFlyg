package GUI;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerNumberModel;

import models.DBModelAirport; 
import models.DBModelFlight;
import models.DBModelRutt;
	
public class WindowAddRutt extends JDialog implements ActionListener {

	private static final String  ADD = "add";
	private static final String  CANCEL = "cancel";
	JComboBox<DBModelAirport> droppDownFrom;
	JComboBox<DBModelAirport> droppDownTo;
	DBModelRutt model;
	JSpinner spinnerDate;
	JSpinner spinnerPrice;
	
	 public WindowAddRutt(){
		 
		 JPanel panel = new JPanel();
		 
		 droppDownFrom = new JComboBox<DBModelAirport>(DBModelAirport.getAll()); 
		 panel.add(droppDownFrom);
		 
		 droppDownTo = new JComboBox<DBModelAirport>(DBModelAirport.getAll()); 
		 panel.add(droppDownTo);
		 
		 JButton addBtn = new JButton("Skapa");
		 addBtn.addActionListener(this);
		 addBtn.setActionCommand(ADD);
		 panel.add(addBtn);
		 
		 JButton cancelBtn = new JButton("Avbryt");
		 cancelBtn.addActionListener(this);
		 cancelBtn.setActionCommand(CANCEL);
		 panel.add(cancelBtn);
		 
		 setModal(true);
		 add(panel);
		 pack();
	 }
	 
	
	// H��mta alla rader fr��n db 
	 
	 public DBModelRutt getRutt(){
		 return model;
	 }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		 String cmd = e.getActionCommand();
		 
		 switch (cmd) {
		case CANCEL:
			model = null;
				dispose();
			break;
		case ADD:
			
			DBModelAirport arFr = (DBModelAirport) droppDownFrom.getSelectedItem();
			DBModelAirport arTo = (DBModelAirport) droppDownTo.getSelectedItem();
			model = new DBModelRutt(arFr, arTo);	
	
			dispose();
			break;

		default:
			break;
		}
	}
	
	
}
