package GUI;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Login extends JDialog implements ActionListener{
	
	private static final String LOGIN = "login";
	private static final String CANCEL = "cancel";

	private String user = "123";
	private String pass = "321";
	
	JTextField userFld;
	JTextField passFld;
	
	
	public Login(){
        
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
        
        //Create panel
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
	
	public String getUser(){
		return user;
	}
	
	public String getPass(){
		return pass;
	}
	
	public boolean loginVerification(){
		
		
		
		String inputUser = userFld.getText();
		String inputPass = passFld.getText();
		boolean loggedIn = false;
		
		if(inputUser.equals(getUser()) && inputPass.equals(getPass())){
			loggedIn = true;
		}
		return loggedIn;
	}
	
	public void checkIfLoggedIn(){
		if(loginVerification() == true){
			System.out.println("You are logged in!!! :D");
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
