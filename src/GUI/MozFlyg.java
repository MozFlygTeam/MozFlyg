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

public class MozFlyg extends JFrame implements ActionListener {
 /**
  *
  */
 private static final String EXIT = "exit";
 private static final String LOGOUT = "logout";
 private static final long serialVersionUID = 1L;
 public MozFlyg() {
  initUI();
 }
 public final void initUI() {
  JPanel panel = new JPanel();
  panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
  panel.setLayout(new GridLayout(0, 1));
  add(panel);
  JMenuBar menubar = new JMenuBar();
  // File menu
  JMenu file = new JMenu("File");
  menubar.add(file);
  JMenuItem logoutItem = new JMenuItem("Logout");
  logoutItem.addActionListener(this);
  logoutItem.setActionCommand(LOGOUT);
  file.add(logoutItem);
  
  JMenuItem exitItem = new JMenuItem("Exit");
  exitItem.addActionListener(this);
  exitItem.setActionCommand(EXIT);
  file.add(exitItem);
  
  // ADMIN MENYN
  
  JMenu admin = new JMenu("Admin");
  menubar.add(admin);
  
  JMenuItem item1 = new JMenuItem("item1");
  item1.addActionListener(this);
  item1.setActionCommand(EXIT);
  admin.add(item1);
  
  JMenuItem item2 = new JMenuItem("item2");
  item2.addActionListener(this);
  item2.setActionCommand(EXIT);
  admin.add(item2);
  
  JMenuItem item3 = new JMenuItem("item3");
  item3.addActionListener(this);
  item3.setActionCommand(EXIT);
  admin.add(item3);
  
  
  JButton button1 = new JButton("Button 1");
  button1.addActionListener(this);
  button1.setActionCommand(EXIT);
  panel.add(button1);
  
  JButton button2 = new JButton("Button 2");
  button2.addActionListener(this);
  button2.setActionCommand(EXIT);
  panel.add(button2);
  
  JButton button3 = new JButton("Button 3");
  button3.addActionListener(this);
  button3.setActionCommand(EXIT);
  panel.add(button3);
  
  JButton button4 = new JButton("Button 4");
  button4.addActionListener(this);
  button4.setActionCommand(EXIT);
  panel.add(button4);
  
  
  setJMenuBar(menubar);
  setTitle("MozFlyg");
  setSize(350, 300);
  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  setLocationRelativeTo(null);
 }
 public static void main(String[] args) {
  SwingUtilities.invokeLater(new Runnable() {
   public void run() {
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
  case EXIT:
   System.exit(0);
   break;
   
  case LOGOUT:
   System.exit(0);
   break;
  default:
   break;
  }
  
 }
}