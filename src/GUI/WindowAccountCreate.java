package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import models.DBModelAccount;

public class WindowAccountCreate extends JDialog implements ActionListener {
  
  private static final String CREATE = "create";
  private static final String CANCEL = "cancel";
  DBModelAccount accountModel;
  JTextField usernameFld;
  JTextField passwordFld;
  
  public WindowAccountCreate(){
    
    JPanel panel = new JPanel();
    
    JLabel usernameLbl = new JLabel("Användarnamn");
    usernameFld = new JTextField(10);
    panel.add(usernameLbl);
    panel.add(usernameFld);
    
    JLabel passwordLbl = new JLabel("Lösenord");
    passwordFld = new JTextField(10);
    panel.add(passwordLbl);
    panel.add(passwordFld);
    
    JButton addBtn = new JButton("Skapa");
    addBtn.addActionListener(this);
    addBtn.setActionCommand(CREATE);
    panel.add(addBtn);
    
    JButton cancelBtn = new JButton("Avbryt");
    cancelBtn.addActionListener(this);
    cancelBtn.setActionCommand(CANCEL);
    panel.add(cancelBtn);
    
    setModal(true);
    add(panel);
    pack();
  }
  
  public DBModelAccount getNewAccount(){ 
		return accountModel;
  }
  
  @Override
  public void actionPerformed(ActionEvent event){
    String cmd = event.getActionCommand();
    
    switch(cmd){
      case CANCEL: 
    	  accountModel = null;
    	  dispose();
        break;
      case CREATE:
        
    	if(usernameFld.getText().length() <= 0 || passwordFld.getText().length() <= 0){
    		JOptionPane.showMessageDialog(null, "Alla fält måste vara ifyllda!");
    	}
    	else{
    		accountModel = new DBModelAccount(usernameFld.getText(), passwordFld.getText()); 
    		accountModel.insert();
    		dispose();
    	}
    	
    	break;
    }
  }
}
