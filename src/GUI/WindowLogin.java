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
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import models.DBModelAccount;

public class WindowLogin extends JDialog implements ActionListener{
	
	private static final String LOGIN = "login";
	private static final String EXIT = "exit";
	
	private boolean loggedIn = false;
	
	JTextField userFld;
	JTextField passFld;
	
	public WindowLogin(){
    
		setModal(true);
		setTitle("Logga In");
		
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
        JPanel buttonPnl = new JPanel();
        add(buttonPnl, BorderLayout.PAGE_END);
        
        //Create login button
        JButton submitBtn = new JButton("Logga in");
        submitBtn.setActionCommand(LOGIN);
        submitBtn.addActionListener(this);
        buttonPnl.add(submitBtn);
        
        //Create cancel button
        JButton exitBtn = new JButton("Exit");
        exitBtn.setActionCommand(EXIT);
        exitBtn.addActionListener(this);
        buttonPnl.add(exitBtn);
        
        getRootPane().setDefaultButton(submitBtn);
        
        
        pack();
        setLocationRelativeTo(null);
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
		
		if (account.loginUser()){
			setLoginStatus(true);
			System.out.println("Input matched with database");
			dispose();
		}else{
			JOptionPane.showMessageDialog(null, "Mannen, fel användarnamn eller lösenord.", 
												"Failure", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void actionPerformed(ActionEvent event){
		String action = event.getActionCommand();
		
		if (action.equals(EXIT)){
			System.exit(0);
		}
		
		else if(action.equals(LOGIN)){
		
			if(userFld.getText().length() <= 0 || passFld.getText().length() <= 0){
				JOptionPane.showMessageDialog(null, "Fyll i båda fälten. Annars blir'e spö.", 
													"Failure", JOptionPane.ERROR_MESSAGE);
			}
			else{
				loginVerification();
				System.out.println("Logged in = " + loggedIn);
			}
		}
	}
}