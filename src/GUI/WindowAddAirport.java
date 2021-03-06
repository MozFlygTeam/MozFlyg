package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import models.DBModelAirport;


	
public class WindowAddAirport extends JDialog implements ActionListener {

	private static final String  ADD = "add";
	private static final String  CANCEL = "cancel";
	DBModelAirport model;
	 JTextField city;
	 JTextField airport;
	 
	 public WindowAddAirport(){
		 
		 JPanel panel = new JPanel();
		 
		 JLabel cityLabel = new JLabel("Stad");
		 city = new JTextField(10);
		 panel.add(cityLabel);
		 panel.add(city);
		 
		 JLabel airportLabel = new JLabel("Flygplats");
		 airport = new JTextField(10);
		 panel.add(airportLabel);
		 panel.add(airport);
		 
		 JButton addBtn = new JButton("Skapa");
		 addBtn.addActionListener(this);
		 addBtn.setActionCommand(ADD);
		 panel.add(addBtn);
		 
		 JButton cancelBtn = new JButton("Avbryt");
		 cancelBtn.addActionListener(this);
		 cancelBtn.setActionCommand(CANCEL);
		 panel.add(cancelBtn);
		 
		 setTitle("Lägg Till Flygplats");
		 setModal(true);
		 add(panel);
		 pack();
	 }
	 
	
	// H��mta alla rader fr��n db 
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
			//Kollar s�� att anv��ndaren inte skickar in tomma str��ngar
			if(airport.getText().trim().length() <= 0 || 
			   city.getText().trim().length() <= 0){
				JOptionPane.showMessageDialog(null, "Alla fält måste vara ifyllda!");
			}
			else{
				model = new DBModelAirport(airport.getText().trim(), city.getText().trim());
				dispose();
			}
			
			break;

		default:
			break;
		}
	}
	
	
}
