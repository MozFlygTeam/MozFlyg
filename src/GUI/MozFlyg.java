package GUI;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import models.DBModelAccount;

public class MozFlyg extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private static final String AIRPORT = "airport";
	private static final String AIRPLANE = "airplane";
	private static final String ACCOUNT = "account";
	private static final String FLIGHT = "flight";
	private static final String BOOKING = "booking";

	public MozFlyg() {
		WindowLogin login = new WindowLogin();
		login.setVisible(true);
		
		initUI();
		
	}

	public final void initUI() {
		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		panel.setLayout(new GridLayout(0, 1));
		add(panel);
		JMenuBar menubar = new JMenuBar();
		
		// ADMIN MENYN
		System.out.print(DBModelAccount.loggedInUser.isAdmin());
		JMenu admin = new JMenu("Administrera");
		admin.setEnabled(false);
		menubar.add(admin);

		//Menyknapp till flygturer
		JMenuItem item3 = new JMenuItem("Flygturer");
		item3.addActionListener(this);
		item3.setActionCommand(FLIGHT);
		admin.add(item3);
		
		//Edit airport button
		JMenuItem itemAirport = new JMenuItem("Flygplatser");
		itemAirport.addActionListener(this);
		itemAirport.setActionCommand(AIRPORT);
		admin.add(itemAirport);
		
		//Administrera flygplanstyper
		JMenuItem itemAirplane = new JMenuItem("Flygplanstyper");
		itemAirplane.addActionListener(this);
		itemAirplane.setActionCommand(AIRPLANE);
		admin.add(itemAirplane);
		
		//Menyknapp till användarkonton
		JMenuItem item2 = new JMenuItem("Användarkonton");
		item2.addActionListener(this);
		item2.setActionCommand(ACCOUNT);
		admin.add(item2);

		//Knapp f��r att boka resor
		JButton button1 = new JButton("Boka resa");
		button1.addActionListener(this);
		button1.setActionCommand(BOOKING);
		panel.add(button1);
			
		if(DBModelAccount.loggedInUser.isAdmin()){	
			admin.setEnabled(true);
		}
		
		setJMenuBar(menubar);
		setTitle("MozFlyg");
		setSize(350, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {

			//	DBConnector.setConnectionData("jdbc:mysql://localhost:port/DB", "user", "pass");
			
				MozFlyg ex = new MozFlyg();
				ex.setVisible(true);	
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		String cmd = e.getActionCommand();

		switch (cmd) {
			case AIRPLANE:
				WindowAirplaneType airplaneType = new WindowAirplaneType();
				airplaneType.setVisible(true);
				break;	
			case AIRPORT:
				WindowAirport airport = new WindowAirport();
				airport.setVisible(true);
				break;
			case ACCOUNT:
				WindowAccountEdit account = new WindowAccountEdit();
				account.setVisible(true);
				break;
			case FLIGHT:
				WindowEditFlight flight = new WindowEditFlight();
				flight.setVisible(true);
			break;
			case BOOKING:
				WindowEditFlight bookingWindow = new WindowEditFlight();
				bookingWindow.setVisible(true);
				break;
			default:
				break;
		}

	}
}
