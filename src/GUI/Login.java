package GUI;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import models.DBConnector;
import models.DBModelAccount;

public class Login extends JDialog implements ActionListener{
	
	private static final String LOGIN = "login";
	private static final String CANCEL = "cancel";
	
	JTextField userFld;
	JTextField passFld;
	
	
	public Login(){
        //Create field panel
        JPanel dataPnl = new JPanel(new GridLayout(3, 2, 5, 5));
        dataPnl.setBorder(new EmptyBorder(5, 5, 5, 5));
        add(dataPnl, BorderLayout.CENTER);
        
        //Create username field
        dataPnl.add(new JLabel("Användarnamn"));
        userFld = new JTextField(10);
        dataPnl.add(userFld);
        
        //Create password field
        dataPnl.add(new JLabel("Lösenord"));
        passFld = new JTextField(10);
        dataPnl.add(passFld);
        
        //Create button panel
        JPanel bottomPnl = new JPanel();
        add(bottomPnl, BorderLayout.PAGE_END);
        
        //Create login button
        JButton btn = new JButton("Logga in");
        btn.setActionCommand(LOGIN);
        btn.addActionListener(this);
        bottomPnl.add(btn);
        
        //Create cancel button
        btn = new JButton("Cancel");
        btn.setActionCommand(CANCEL);
        btn.addActionListener(this);
        bottomPnl.add(btn);
        
        pack();
	}
	
	//Set loggedIn to true if user inputs matched in the database   
	public boolean loginVerification(){
		
		String inputUser = userFld.getText();
		String inputPass = passFld.getText();
		boolean loggedIn = false;
		DBModelAccount account = new DBModelAccount(inputUser, inputPass); 
		
		if (account.checkUser()){
			loggedIn = true;
		}
		return loggedIn;
	}
	
	//Check if user is logged in
	public void checkIfLoggedIn(){
		if(loginVerification()){
			System.out.println("Welcome!");
		}
	}
	
	public void actionPerformed(ActionEvent event){
		String action = event.getActionCommand();
		
		if (action.equals(CANCEL)){
			System.exit(0);
		}
		
		else if(action.equals(LOGIN)){
			if(loginVerification()){
				checkIfLoggedIn();
			}
		}
	}
}
