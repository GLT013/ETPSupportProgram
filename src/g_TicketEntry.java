import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import java.awt.Font;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JTextField;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.util.Properties;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTextArea;
import java.awt.Toolkit;
import javax.swing.JCheckBox; 

public class g_TicketEntry {

	private static JFrame frmTicketEntry;
	private JComboBox<String> cb_Site = new JComboBox<String>();
	private JComboBox<String> cb_Category = new JComboBox<String>();;
	private JComboBox<String> cb_Client = new JComboBox<String>();
	private JTextField txt_TicketNum;
	private JTextArea txt_Description = new JTextArea();
	private JComboBox<String> cb_Assign;
	private JDatePanelImpl datePanel; 
	private JDatePickerImpl datePicker;
	private static JCheckBox chkbx_NoTicketNum;

			public static void run(JFrame frame) {			
				try {	
					g_TicketEntry window = new g_TicketEntry();
					g_TicketEntry.frmTicketEntry.setVisible(true);
					g_TicketEntry.frmTicketEntry.setLocationRelativeTo(frame);
					frame.dispose();
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}


	public g_TicketEntry() {
		//long startTime = System.currentTimeMillis();
		initialize();
		PopulateClientCB();		
		//long stopTime = System.currentTimeMillis();
		//System.out.println("Elapsed time was " + (stopTime - startTime) + " miliseconds.");
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmTicketEntry = new JFrame();
		frmTicketEntry.setResizable(false);
		frmTicketEntry.setIconImage(Toolkit.getDefaultToolkit().getImage(g_TicketEntry.class.getResource("/icon.png")));
		frmTicketEntry.setBounds(100, 100, 767, 576);
		frmTicketEntry.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmTicketEntry.getContentPane().setLayout(null);		
		frmTicketEntry.setTitle(g_MainMenu.TitleOnline);
		
		
		cb_Site.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				
			}
		});
		cb_Site.setModel(new DefaultComboBoxModel<String>(new String[] {""}));
		cb_Site.setBounds(32, 107, 145, 28);
		frmTicketEntry.getContentPane().add(cb_Site);
		
		cb_Category = new JComboBox<String>();
		cb_Category.setBounds(274, 37, 165, 28);
		cb_Category.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				//PopulateSubCategory(cb_Category.getSelectedItem().toString());
			}
		});
		cb_Category.setModel(new DefaultComboBoxModel<String>(new String[] {"", "Injection", "HMI", "PGM", "Reporting", "Safety", "Sampling", "Supply", "System", "Other"}));
		frmTicketEntry.getContentPane().add(cb_Category);
		
		JButton btnSubmit = new JButton("Enter Ticket");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SubmitTicket();
			}
		});
		btnSubmit.setBounds(593, 489, 107, 23);
		frmTicketEntry.getContentPane().add(btnSubmit);
		cb_Client.setModel(new DefaultComboBoxModel<String>(new String[] {""}));
		cb_Client.setBounds(32, 32, 145, 28);
		cb_Client.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {				
				PopulateSiteList(cb_Client.getSelectedItem().toString());
			}
		});
		frmTicketEntry.getContentPane().add(cb_Client);
		
		JLabel lblClient = new JLabel("Client");
		lblClient.setFont(new Font("Plantagenet Cherokee", Font.BOLD, 16));
		lblClient.setBounds(76, 11, 51, 20);
		frmTicketEntry.getContentPane().add(lblClient);
		
		JLabel lblSite = new JLabel("Site");
		lblSite.setFont(new Font("Plantagenet Cherokee", Font.BOLD, 16));
		lblSite.setBounds(76, 76, 35, 20);
		frmTicketEntry.getContentPane().add(lblSite);
		
		JLabel lblCategory = new JLabel("Category");
		lblCategory.setFont(new Font("Plantagenet Cherokee", Font.BOLD, 16));
		lblCategory.setBounds(317, 11, 78, 20);
		frmTicketEntry.getContentPane().add(lblCategory);
		
		cb_Site.setEnabled(false);
		cb_Category.setEnabled(false);
		
		JLabel lblWorkTicket = new JLabel("Notification #");
		lblWorkTicket.setFont(new Font("Plantagenet Cherokee", Font.BOLD, 16));
		lblWorkTicket.setBounds(522, 11, 135, 20);
		frmTicketEntry.getContentPane().add(lblWorkTicket);
		
		txt_TicketNum = new JTextField();
		txt_TicketNum.setColumns(10);
		txt_TicketNum.setBounds(522, 42, 135, 26);
		frmTicketEntry.getContentPane().add(txt_TicketNum);
		
		JLabel lblDescription = new JLabel("Description");
		lblDescription.setFont(new Font("Plantagenet Cherokee", Font.BOLD, 16));
		lblDescription.setBounds(32, 161, 142, 14);
		frmTicketEntry.getContentPane().add(lblDescription);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(g_MainMenu.CurrentTicketsNav)
				{
					g_CurrentTickets.run(frmTicketEntry);
				}
				else
				{
					g_MainMenu.run(frmTicketEntry);
				}
			}
		});
		btnBack.setBounds(32, 489, 107, 23);
		frmTicketEntry.getContentPane().add(btnBack);
		
		cb_Assign = new JComboBox<String>();
		cb_Assign.setModel(new DefaultComboBoxModel<String>(new String[] {""}));
		cb_Assign.setBounds(274, 109, 165, 26);
		frmTicketEntry.getContentPane().add(cb_Assign);
	
		JLabel lblAssignedTo = new JLabel("Assign To");
		lblAssignedTo.setFont(new Font("Plantagenet Cherokee", Font.BOLD, 16));
		lblAssignedTo.setBounds(312, 78, 87, 20);
		frmTicketEntry.getContentPane().add(lblAssignedTo);
		
		JLabel lblDate = new JLabel("Date");
		lblDate.setFont(new Font("Plantagenet Cherokee", Font.BOLD, 16));
		lblDate.setBounds(564, 76, 65, 20);
		frmTicketEntry.getContentPane().add(lblDate);
		
		UtilDateModel model = new UtilDateModel();
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		datePanel = new JDatePanelImpl(model,p);
		datePicker = new JDatePickerImpl(datePanel, new c_DateLabelFormatter());
		datePicker.getJFormattedTextField().setBackground(Color.WHITE);
		datePicker.setBounds(522, 107, 165, 28);
		frmTicketEntry.getContentPane().add(datePicker);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(32, 186, 668, 256);
		frmTicketEntry.getContentPane().add(scrollPane);
		scrollPane.setViewportView(txt_Description);
		
		
		txt_Description.setFont(new Font("Plantagenet Cherokee", Font.PLAIN, 16));
		txt_Description.setLineWrap(true);
		txt_Description.setWrapStyleWord(true);
		
		chkbx_NoTicketNum = new JCheckBox("No Ticket \r\n#");		
		chkbx_NoTicketNum.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(chkbx_NoTicketNum.isSelected())
				{
					txt_TicketNum.setEnabled(false);					
				}
				else
				{
					txt_TicketNum.setEnabled(true);
				}
			}
		});
	
		chkbx_NoTicketNum.setBounds(663, 42, 97, 23);
		frmTicketEntry.getContentPane().add(chkbx_NoTicketNum);
		datePicker.getModel().setSelected(true);
		

		//Menubar
		JMenuBar menuBar = new JMenuBar();
		frmTicketEntry.setJMenuBar(menuBar);
								
		JMenu mnSupport = new JMenu("Support");
		menuBar.add(mnSupport);
		
		JMenuItem mntmNewTicket = new JMenuItem("New Ticket");		
		mnSupport.add(mntmNewTicket);
		mntmNewTicket.setEnabled(false);
		
		JMenuItem mntmCurrentTickets = new JMenuItem("Current Tickets");
		mntmCurrentTickets.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				g_CurrentTickets.run(frmTicketEntry);
			}
		});
		mnSupport.add(mntmCurrentTickets);
		
		JMenuItem mntmArchive = new JMenuItem("Archive");
		mntmArchive.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				g_ArchiveTickets.run(frmTicketEntry);
			}
		});
		mnSupport.add(mntmArchive);
		
		JMenu mnSites = new JMenu("Sites");
		menuBar.add(mnSites);
		
		JMenuItem mntmButaneSites = new JMenuItem("Butane Sites");
		mntmButaneSites.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				g_ViewSites.run(frmTicketEntry);
			}
		});
		mnSites.add(mntmButaneSites);
		
		JMenuItem mntmSiteChanges = new JMenuItem("Site Changes");
		mntmSiteChanges.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				g_SiteChanges.run(frmTicketEntry);
			}
		});
		mnSites.add(mntmSiteChanges);
		
		JMenu mnContacts = new JMenu("Contacts");
		menuBar.add(mnContacts);
		
		JMenuItem mntmENEmployees = new JMenuItem("EN Employees");
		mntmENEmployees.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				g_ViewEmployees.run(frmTicketEntry);
			}
		});
		mnContacts.add(mntmENEmployees);
		
		JMenuItem mntmEtpContacts = new JMenuItem("ETP Contacts");
		mntmEtpContacts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				g_ViewSunoco.run(frmTicketEntry);
			}
		});
		mnContacts.add(mntmEtpContacts);
		
		JMenu mnTools = new JMenu("Tools");
		menuBar.add(mnTools);
		
		JMenuItem mntmCreateChecklist = new JMenuItem("Create Checklist");
		mntmCreateChecklist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				g_Tools_CreateChecklist.run(frmTicketEntry);
			}
		});		
		mnTools.add(mntmCreateChecklist);
	}
	
	private void PopulateClientCB()
	{
		 	String commandText = "SELECT DISTINCT Client FROM Sites ORDER BY Client Asc";        
	        ResultSet rs = c_Query.ExecuteResultSet(commandText);
	        	     
	        try {
				while((rs!=null) && (rs.next()))
				{							
					cb_Client.addItem(rs.getString("Client"));	
				}
			} catch (SQLException e) {
			}
	
	        commandText = "SELECT DISTINCT Name FROM EN_Employees WHERE Active = 1 ORDER BY Name Asc";        
	        rs = c_Query.ExecuteResultSet(commandText);
	        try {
				while((rs!=null) && (rs.next()))
				{				
					cb_Assign.addItem(rs.getString("Name"));	
				}
			} catch (SQLException e) {
			}
	        
	        
	        try {
				rs.close();
			} catch (SQLException e) {
			
			}
	       
	}
	
	private void PopulateSiteList(String site){
		cb_Site.removeAllItems();
		if(site.compareTo("") == 0){
			cb_Site.setEnabled(false);
		}
		else
		{
			cb_Site.setEnabled(true);
			cb_Category.setEnabled(true);
		}
		String commandText = "SELECT Site from Sites WHERE Client = '" + site + "' ORDER BY Site ASC";
		ResultSet rs = c_Query.ExecuteResultSet(commandText);
		try {
			while((rs!=null) && (rs.next()))
			{								
				cb_Site.addItem(rs.getString("Site"));	
			}
		} catch (SQLException e) {
		}
        try {
			rs.close();
		} catch (SQLException e) {
		}
	}
	

	private void SubmitTicket(){
		String Client = cb_Client.getSelectedItem().toString();
		String Site = cb_Site.getSelectedItem().toString();
		String Category = cb_Category.getSelectedItem().toString();
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");
		String strDate = datePicker.getJFormattedTextField().getText() + " " + LocalTime.now().format(dtf);
	
		String TicketNum = "";
		if(chkbx_NoTicketNum.isSelected())
		{
			TicketNum = NoTicketNumber();			
		}
		else
		{
			TicketNum = txt_TicketNum.getText();	
			TicketNum = TicketNum.trim();
		}
		
		 if(Character.isLetter(TicketNum.charAt(0)))
		 {
			 TicketNum = TicketNum.substring(1);
		 }
		
		String Description = txt_Description.getText();	
		String Assigned = cb_Assign.getSelectedItem().toString();
		
		Description = Description.replaceAll("\'","");
		
		
		if (TicketNum.length() > 11)
		{
			
			String temp = "";
			if (TicketNum.startsWith("1"))
			{
				temp = TicketNum.replace("1000000", "1-");
				TicketNum = temp;
				
			}
			else if (TicketNum.startsWith("2"))
			{
				temp = TicketNum.replace("2000000", "2-");
				
			}
			
			TicketNum = temp;			
						
		}
		
		
		if(Assigned.compareTo("") == 0){
			Assigned = g_MainMenu.CurrentUser;
		}
		
		if(TicketNum.compareTo("") == 0){
			JOptionPane.showMessageDialog(frmTicketEntry, "Unique Ticket Number Required");
			return;
		}
		String TicketCheck = "SELECT COUNT(*) as Total FROM SupportTickets WHERE Ticket = '" + TicketNum + "'";
		 ResultSet rs = c_Query.ExecuteResultSet(TicketCheck);
		 int count = 0;
	        try {
				while((rs!=null) && (rs.next()))
				{					
					count = rs.getInt("Total");
					
				}
			} catch (SQLException e) {
			}
	        try {
				rs.close();
			} catch (SQLException e) {
			
			}
	        
	        if(count > 0)
	        {
	        	JOptionPane.showMessageDialog(frmTicketEntry, "Ticket Number Already Exists.");
				return;	        	
	        }
	       
	        Description = c_CleanString.Clean_String(Description);
	  

	        String commandText = "INSERT INTO SupportTickets (Client,Site,Category,Ticket,EnteredDate,Description,Assigned,Status,Resolution,Active,UpdateDate) "
				+ "VALUES ('" + Client + "', '" + Site + "', '" + Category + "', '" + TicketNum + "', '" + strDate + "', '" + Description + "', '" + Assigned + "', 'Investigating', '', 1, '" + strDate +"')";
		
		
		c_Query.UpdateResultSet(commandText);
		//Send email only if the assigned user is not the current user.
		if(Assigned.compareTo(g_MainMenu.CurrentUser) != 0)
		{
			c_ConnectToDatabase.SendEmail(Assigned, Client, Site, TicketNum, Description);
		}
		JOptionPane.showMessageDialog(frmTicketEntry, "Ticket # " + TicketNum + " entered successfully");	
		if(g_MainMenu.CurrentTicketsNav)
		{
			g_CurrentTickets.run(frmTicketEntry);
		}
		else
		{
			g_MainMenu.run(frmTicketEntry);
			frmTicketEntry.dispose();
		}
	
	}
	
	private String NoTicketNumber()
	{
		String ticketNumber = "";
		String commandText = "SELECT COUNT(*) as Total FROM SupportTickets WHERE Ticket LIKE '%3-%'";
		ResultSet rs = c_Query.ExecuteResultSet(commandText);
		int count = 0;
        try {
			while((rs!=null) && (rs.next()))
			{					
				count = rs.getInt("Total");				
			}
		} catch (SQLException e) {
		}
        try {
			rs.close();
		} catch (SQLException e) {
		
		}
        
        count++; //increment the number
       
        ticketNumber = "3-" + String.format("%04d", count);
        
		return ticketNumber;
	}
	
	
	
}
