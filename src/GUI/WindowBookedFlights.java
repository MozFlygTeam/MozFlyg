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
import models.DBModelBookedFlight;
import models.TableModelBookedFlight;

public class WindowBookedFlights extends JFrame implements ActionListener, ListSelectionListener{
  
  public DBModelBookedFlight bookedFlightmodel;
  public static TableModelBookedFlight table;
  private JTable bookedFlightTable;
  JButton unbookButton;
  private static final String UNBOOK = "delete";
  
  //Constructor
  public WindowBookedFlights(){
    
    table = new TableModelBookedFlight();
    bookedFlightTable = new JTable(table);
    bookedFlightTable.getSelectionModel().addListSelectionListener(this);
    DBModelBookedFlight.getAllBookedFlights();
    
    JPanel contentPane = new JPanel(new BorderLayout());
    JScrollPane scrollPane = new JScrollPane(bookedFlightTable);
    JPanel bottomPanel = new JPanel(new FlowLayout());

    unbookButton = new JButton("Avboka Resa");
    unbookButton.addActionListener(this);
    unbookButton.setActionCommand(UNBOOK);
    unbookButton.setEnabled(false);

    setContentPane(contentPane);
    add(scrollPane, BorderLayout.CENTER);
    add(bottomPanel, BorderLayout.PAGE_END);
    bottomPanel.add(unbookButton);

    pack();
    setLocationRelativeTo(null);
  }
    
    @Override
    public void actionPerformed(ActionEvent event) {

      switch (event.getActionCommand()) {
      
      case UNBOOK:
        int i = bookedFlightTable.getSelectedRow();
        table.removeBookedFlight(i);
        break;

      }
    }
    
    public void valueChanged(ListSelectionEvent event)
    {
      unbookButton.setEnabled(bookedFlightTable.getSelectedRowCount() > 0);
    }
}
