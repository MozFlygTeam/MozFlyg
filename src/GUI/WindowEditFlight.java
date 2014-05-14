package GUI;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

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
	
public class WindowEditFlight extends JDialog implements ActionListener {

	private static final String  EDIT = "edit";
	private static final String  CANCEL = "cancel";
	JComboBox<DBModelAirport> droppDownFrom;
	JComboBox<DBModelAirport> droppDownTo;
	DBModelFlight model;
	JSpinner spinnerDate;
	JSpinner spinnerPrice;
	
	 public WindowEditFlight(DBModelFlight flight){
		 
		 model = flight;
		 
		 System.out.print(flight.getDepartingFrom().toString());
		 
		 JPanel panel = new JPanel();
		 
		 
		 droppDownFrom = new JComboBox(DBModelAirport.getAll());
		 droppDownFrom.getModel().setSelectedItem(model.getDepartingFrom());
	 
		 panel.add(droppDownFrom);
		 
		 droppDownTo = new JComboBox(DBModelAirport.getAll()); 
		 droppDownTo.getModel().setSelectedItem(model.getArrivingTo());
		 panel.add(droppDownTo);
		 
		 spinnerDate = new JSpinner(new SpinnerDateModel());
		 spinnerDate.setValue(model.getTimeDeparting());
		 panel.add(spinnerDate);
		 
		 
		 spinnerPrice = new JSpinner(new SpinnerNumberModel(model.getPrice(), 1,99999, 10));
	
		 panel.add(spinnerPrice);
		 
		
		 JLabel price = new JLabel("Kr");
		 panel.add(price);	
		 
		 JButton addBtn = new JButton("Spara");
		 addBtn.addActionListener(this);
		 addBtn.setActionCommand(EDIT);
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
		case EDIT:
			
			DBModelAirport arFr = (DBModelAirport) droppDownFrom.getSelectedItem();
			DBModelAirport arTo = (DBModelAirport) droppDownTo.getSelectedItem();
			SpinnerDateModel sp = (SpinnerDateModel)spinnerDate.getModel();
			SpinnerNumberModel sn = (SpinnerNumberModel) spinnerPrice.getModel();
			java.util.Date now = new java.util.Date();
			now = sp.getDate();
			java.sql.Date sqlDate = new java.sql.Date(now.getTime());
			
			model.setDepartingFrom(arFr);
			model.setArrivingTo(arTo);
			model.setTimeDeparting(sqlDate);
			model.setPrice(sn.getNumber().doubleValue());	
			
		
			
			dispose();
			break;

		default:
			break;
		}
	}
	
	
}
