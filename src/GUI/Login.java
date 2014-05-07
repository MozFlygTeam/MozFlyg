package GUI;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import models.DBConnector;
import models.DBModelAccount;

public class Login extends JDialog implements ActionListener{
	
	private static final String LOGIN = "login";
	private static final String EXIT = "exit";
	
	private boolean loggedIn = false;
	
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
        passFld = new JPasswordField(10);
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
        btn = new JButton("Exit");
        btn.setActionCommand(EXIT);
        btn.addActionListener(this);
        bottomPnl.add(btn);
        
        pack();
	}
	
	public void setLoginStatus(boolean status){
		this.loggedIn = status;
	}
	
	public boolean getLoginStatus(){
		return this.loggedIn;
	}

	
	//Set loggedIn to true if user inputs matched in the database   
	public void loginVerification(){
		
		String inputUser = userFld.getText();
		String inputPass = passFld.getText();
		DBModelAccount account = new DBModelAccount(inputUser, inputPass); 
		
		if (account.checkUser()){
			setLoginStatus(true);
			System.out.println("Input matched with database");
			
		}
	}
	
	public void actionPerformed(ActionEvent event){
		String action = event.getActionCommand();
		
		if (action.equals(EXIT)){
			System.exit(0);
		}
		
		else if(action.equals(LOGIN)){
			loginVerification();
			System.out.println("Logged in = " + loggedIn);
		}
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				//Database, user, password
				DBConnector.setConnectionData("jdbc:mysql://localhost:3306/mozflyg", "root", "root");
				Login frame = new Login();
				frame.setVisible(true);
			}
		});
	}
}
