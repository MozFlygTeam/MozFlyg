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

import models.DBModelAirplaneType;
import models.DBModelAirport;
import models.DBModelFlight;
	
public class WindowAddFlight extends JDialog implements ActionListener {

	private static final String  ADD = "add";
	private static final String  CANCEL = "cancel";
	JComboBox<DBModelAirport> droppDownFrom;
	JComboBox<DBModelAirport> droppDownTo;
	JComboBox<DBModelAirplaneType> droppDownAirplaneType;
	DBModelFlight model;
	JSpinner spinnerDepartingDate;
	JSpinner spinnerPrice;
	
	 public WindowAddFlight(){
		 
		 JPanel panel = new JPanel();
		 
		 droppDownFrom = new JComboBox<DBModelAirport>(DBModelAirport.getAll()); 
		 panel.add(droppDownFrom);
		 
		 droppDownTo = new JComboBox<DBModelAirport>(DBModelAirport.getAll()); 
		 panel.add(droppDownTo);
		 
		 droppDownAirplaneType = new JComboBox<DBModelAirplaneType>(DBModelAirplaneType.getAllAirplaneTypes()); 
		 panel.add(droppDownAirplaneType);
		 
		 spinnerDepartingDate = new JSpinner(new SpinnerDateModel());
		 panel.add(spinnerDepartingDate);
		 
		 spinnerPrice = new JSpinner(new SpinnerNumberModel(1000, 1,99999, 10));
		 panel.add(spinnerPrice);
		 
		 JLabel price = new JLabel("Kr");
		 panel.add(price);	
		 
		 JButton addBtn = new JButton("Skapa");
		 addBtn.addActionListener(this);
		 addBtn.setActionCommand(ADD);
		 panel.add(addBtn);
		 
		 JButton cancelBtn = new JButton("Avbryt");
		 cancelBtn.addActionListener(this);
		 cancelBtn.setActionCommand(CANCEL);
		 panel.add(cancelBtn);
		 
		 setTitle("Lägg Till Resa");
		 setModal(true);
		 add(panel);
		 pack();
		 setLocationRelativeTo(null);
	 }
	 
	
	// Hämta alla rader från db 
	 
	 public DBModelFlight getFlight(){
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
			DBModelAirplaneType airplaneType = (DBModelAirplaneType) droppDownAirplaneType.getSelectedItem();
			SpinnerDateModel spd = (SpinnerDateModel)spinnerDepartingDate.getModel();
			SpinnerNumberModel sn = (SpinnerNumberModel) spinnerPrice.getModel();
			java.util.Date now = new java.util.Date();
			
			now = spd.getDate();
			java.sql.Date sqlDateDepart = new java.sql.Date(now.getTime());
			
			
			model = new DBModelFlight(arFr, arTo, sqlDateDepart, airplaneType, sn.getNumber().doubleValue());	
	
			dispose();
			break;

		default:
			break;
		}
	}
	
	
}
