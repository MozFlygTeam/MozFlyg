package GUI;


import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.SpinnerDateModel;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableColumn;

import models.DBModelAccount;
import models.DBModelAirport;
import models.DBModelFlight;
import models.TableModelFlight;

  public class WindowEditFlight extends JFrame implements ActionListener, ListSelectionListener{

    public DBModelFlight flightModel;
    public static TableModelFlight tableModelFlight;
    private JButton removeButton;
    private JButton bookedFlightsBtn;
    private JButton bookFlightBtn;
    private JButton unbookFlightsBtn;
    private JButton showAllFlightsBtn;
    private JTable flightTable;
    
    JComboBox<DBModelAirport> droppDownFrom;
    JComboBox<DBModelAirport> droppDownTo;
    JSpinner spinnerDate;
    JButton searchButton;
    
    private static final String SHOWALL = "SHOWALL";
    private static final String SEARCH = "search";  
    private static final String ADD = "add";
    private static final String DELETE = "delete";
    private static final String BOOKED = "booked";
    private static final String BOOK = "book";
    private static final String UNBOOK = "unbook";
    
    public WindowEditFlight() {

      tableModelFlight = new TableModelFlight();
      flightModel = new DBModelFlight();
      flightTable = new JTable(tableModelFlight);
      
      
      //ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ndrar CellEditorn fï¿½ï¿½ï¿½ï¿½ï¿½ï¿½r avgï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ngsflygets kolumn till en dropdownbox
      TableColumn departingColumn = flightTable.getColumnModel().getColumn(TableModelFlight.DEPARTING_FROM_COLUMN);
      departingColumn.setCellEditor(new DefaultCellEditor(new JComboBox<DBModelAirport>(DBModelAirport.getAll())));
      
      //ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ndrar CellEditorn fï¿½ï¿½ï¿½ï¿½ï¿½ï¿½r ankomstflygets kolumn till en dropdownbox
      TableColumn arrivingColumn = flightTable.getColumnModel().getColumn(TableModelFlight.ARRIVING_TO_COLUMN);
      arrivingColumn.setCellEditor(new DefaultCellEditor(new JComboBox<DBModelAirport>(DBModelAirport.getAll())));
      
      flightTable.getSelectionModel().addListSelectionListener(this);
    
      JPanel searchPane = new JPanel();
       droppDownFrom = new JComboBox<DBModelAirport>(DBModelAirport.getAll());
       
       searchPane.add(droppDownFrom);
       
       droppDownTo = new JComboBox<DBModelAirport>(DBModelAirport.getAll()); 
       searchPane.add(droppDownTo);
       
       spinnerDate = new JSpinner(new SpinnerDateModel());
       searchPane.add(spinnerDate);
       
       searchButton = new JButton("Sök");
       searchButton.addActionListener(this);
       searchButton.setActionCommand(SEARCH);
       searchPane.add(searchButton);


      JPanel contentPane = new JPanel(new BorderLayout());
      JScrollPane scrollPane = new JScrollPane(flightTable);
      JPanel bottomPanel = new JPanel(new FlowLayout());

      setContentPane(contentPane);
      add(searchPane, BorderLayout.NORTH);
      add(scrollPane, BorderLayout.CENTER);
      add(bottomPanel, BorderLayout.PAGE_END);
      
      showAllFlightsBtn = new JButton("Visa alla");
      showAllFlightsBtn.addActionListener(this);
      showAllFlightsBtn.setActionCommand(SHOWALL);
      showAllFlightsBtn.setEnabled(true);
      searchPane.add(showAllFlightsBtn);
      
      bookedFlightsBtn = new JButton("Bokade resor");
      bookedFlightsBtn.addActionListener(this);
      bookedFlightsBtn.setActionCommand(BOOKED);
      bookedFlightsBtn.setEnabled(true);
      
      bookFlightBtn = new JButton("Boka flyg");
      bookFlightBtn.addActionListener(this);
      bookFlightBtn.setActionCommand(BOOK);
      bookFlightBtn.setEnabled(false);
      
      unbookFlightsBtn = new JButton("Avboka flyg");
      unbookFlightsBtn.addActionListener(this);
      unbookFlightsBtn.setActionCommand(UNBOOK);
      unbookFlightsBtn.setEnabled(false);
      
      bottomPanel.add(bookedFlightsBtn);
      bottomPanel.add(bookFlightBtn);
      bottomPanel.add(unbookFlightsBtn);
      
      if(DBModelAccount.loggedInUser.isAdmin())
      {
    	  bottomPanel.add(new JSeparator(SwingConstants.VERTICAL));
    	  
	      JButton addButton = new JButton("LÃ¤gg till");
	      addButton.addActionListener(this);
	      addButton.setActionCommand(ADD);
	
	      removeButton = new JButton("Ta Bort");
	      removeButton.addActionListener(this);
	      removeButton.setActionCommand(DELETE);
	      removeButton.setEnabled(false);
	      
	      bottomPanel.add(addButton);
	      bottomPanel.add(removeButton);
	      
	      setTitle("SÃ¶k och hantera resor");
      }
      else
      {
    	  setTitle("SÃ¶kning och bokning av resor");
      }
      
      pack();
      setLocationRelativeTo(null);
    } 
    
  
    private void addFlight() {
      
      WindowAddFlight add = new WindowAddFlight();
      add.setVisible(true);
      
      DBModelFlight model =  add.getFlight();
      if(model != null){
      
        if (model.insert() == 1) {
        tableModelFlight.addFlight(model);
        }
      }
      
    }
    
    @Override
    public void actionPerformed(ActionEvent event)
    {

      switch (event.getActionCommand())
      {
	      case ADD:
	        addFlight();
	        break;
	      case DELETE:
	        int i = flightTable.getSelectedRow();
	        tableModelFlight.deleteFlight(i);
	        break;
	      case SEARCH:
	        DBModelAirport arFr = (DBModelAirport) droppDownFrom.getSelectedItem();
	        DBModelAirport arTo = (DBModelAirport) droppDownTo.getSelectedItem();
	        SpinnerDateModel sp = (SpinnerDateModel)spinnerDate.getModel();
	        
	        java.sql.Date sqlDate = new java.sql.Date(sp.getDate().getTime());
	        System.out.print("SEARCH METODEN !!");
	        tableModelFlight.setFlight(DBModelFlight.getFlights(arFr.getId(),arTo.getId(), sqlDate));
	        
	        break;
	      case BOOKED:
	        
	        tableModelFlight.setFlight(DBModelFlight.getBookedFlights());
	        
	        break;
	      case BOOK:
	    	  tableModelFlight.addFlightBooking(flightTable.getSelectedRow());
	    	  break;
	      case UNBOOK:
	          tableModelFlight.removeFlightBooking(flightTable.getSelectedRow());
	          System.out.println("Vald flightrad:" + flightTable.getSelectedRow());
	          break;
	      case SHOWALL:
	          tableModelFlight.setFlight(DBModelFlight.getAllFlights());
	          break;
      }
    
      
    }
    
    public void valueChanged(ListSelectionEvent event)
    {
      if(DBModelAccount.loggedInUser.isAdmin())
      {
    	boolean selectionActive = flightTable.getSelectedRowCount() > 0;
    	
        removeButton.setEnabled(selectionActive);
        bookFlightBtn.setEnabled(selectionActive);
        unbookFlightsBtn.setEnabled(selectionActive);
      }
    }
    
    
  }