package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import models.DBModelAirport;


	
public class AddAirport extends JDialog implements ActionListener {

	private static final String  ADD = "add";
	private static final String  CANCEL = "cancel";
	
	 JTextField city;
	 JTextField airport;
	 
	 public AddAirport(){
		 
		 JPanel panel = new JPanel();
		 
		 JLabel cityLabel = new JLabel("Stad");
		 city = new JTextField(10);
		 panel.add(cityLabel);
		 panel.add(city);
		 
		 JLabel airportLabel = new JLabel("Flygplatts");
		 airport = new JTextField(10);
		 panel.add(airportLabel);
		 panel.add(airport);
		 
		 JButton addBtn = new JButton("Lägg till");
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
		 
		 DBModelAirport model = new DBModelAirport();
		 model.setAirportName(airport.getText());
		 model.setCityName(airport.getText());
		 return model;
	 }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		 String cmd = e.getActionCommand();
		 
		 switch (cmd) {
		case CANCEL:
				dispose();
			break;
		case ADD:
			getAirport();
			dispose();
			break;

		default:
			break;
		}
	}
	
	
}
