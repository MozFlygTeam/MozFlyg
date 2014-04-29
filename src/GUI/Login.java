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

public class Login extends JDialog implements ActionListener{
	
	private static final String LOGIN = "login";
	private static final String CANCEL = "cancel";

	public Login(){
        
        JPanel dataPnl = new JPanel(new GridLayout(3, 2, 5, 5));
        dataPnl.setBorder(new EmptyBorder(5, 5, 5, 5));
        add(dataPnl, BorderLayout.CENTER);
        
        dataPnl.add(new JLabel("Användarnamn"));
        JTextField userFld = new JTextField(10);
        dataPnl.add(userFld);
        
        dataPnl.add(new JLabel("Lösenord"));
        JTextField passFld = new JTextField(10);
        dataPnl.add(passFld);
        
        JPanel bottomPnl = new JPanel();
        add(bottomPnl, BorderLayout.PAGE_END);
        
        JButton btn = new JButton("Logga in");
        btn.setActionCommand(LOGIN);
        btn.addActionListener(this);
        bottomPnl.add(btn);
        
        btn = new JButton("Cancel");
        btn.setActionCommand(CANCEL);
        btn.addActionListener(this);
        bottomPnl.add(btn);
        
        pack();
	}
	
	public void actionPerformed(ActionEvent event){
		String action = event.getActionCommand();
		
		if (action.equals(CANCEL)) {
			System.exit(0);
        }
	}
}
