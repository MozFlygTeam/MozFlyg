package GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import models.DBModelAccount;
import models.TableModelAccount;

public class WindowAccountEdit extends JFrame implements ActionListener, ListSelectionListener{
  
  public DBModelAccount accountModel;
  public static TableModelAccount table;
  private JButton removeButton;
  private JTable userTable;
  private static final String ADD = "add";
  private static final String DELETE = "delete";
  
  //Constructor
  public WindowAccountEdit(){
    
    table = new TableModelAccount();
    userTable = new JTable(table);
    userTable.getSelectionModel().addListSelectionListener(this);
    DBModelAccount.getAllAccounts();
    
    JPanel contentPane = new JPanel(new BorderLayout());
    JScrollPane scrollPane = new JScrollPane(userTable);
    JPanel bottomPanel = new JPanel(new FlowLayout());

    JButton addButton = new JButton("Lägg till");
    addButton.addActionListener(this);
    addButton.setActionCommand(ADD);

    removeButton = new JButton("Ta Bort");
    removeButton.addActionListener(this);
    removeButton.setActionCommand(DELETE);
    removeButton.setEnabled(false);

    setContentPane(contentPane);
    add(scrollPane, BorderLayout.CENTER);
    add(bottomPanel, BorderLayout.PAGE_END);
    bottomPanel.add(addButton);
    bottomPanel.add(removeButton);

    setTitle("Hantera Användare");
    pack();
    setLocationRelativeTo(null);
  }
    //Method to add a new user
    private void addAccountWindow() {
      
    	WindowAccountCreate windowCreate = new WindowAccountCreate();
    	windowCreate.setVisible(true);
      
    	DBModelAccount account =  windowCreate.getNewAccount();
		System.out.print(account);
		if(account != null){
			
			if (account.insert() == 1) {
				table.addAccount(account);
			}
		}
    }
    
    @Override
    public void actionPerformed(ActionEvent event) {

      switch (event.getActionCommand()) {
      case ADD:
        addAccountWindow();
        DBModelAccount.getAllAccounts();
        break;
      case DELETE:
        int i = userTable.getSelectedRow();
        table.removeAccount(i);
        break;

      }
    }
    
    public void valueChanged(ListSelectionEvent event)
    {
      removeButton.setEnabled(userTable.getSelectedRowCount() > 0);
    }
}
