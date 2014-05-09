package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import models.DBModelAirport;
import models.DBModelFlight;


	
public class WindowAddFlight extends JDialog implements ActionListener {

	private static final String  ADD = "add";
	private static final String  CANCEL = "cancel";
	DBModelAirport model;

	 public WindowAddFlight(){
		 
		 JPanel panel = new JPanel();
		 
		 JComboBox<DBModelAirport> droppDownFrom = new JComboBox(DBModelAirport.getAll()); 
		 panel.add(droppDownFrom);
		 
		 JComboBox<DBModelAirport> droppDownTo = new JComboBox(DBModelAirport.getAll()); 
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
	 
	
	// Hämta alla rader från db 
	 public DBModelAirport getAirport(){ 
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
			//model = new DBModelAirport(airport.getText(),city.getText());
			dispose();
			break;

		default:
			break;
		}
	}
	
	
}
