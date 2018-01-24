import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import java.awt.Font;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.LocalTime;

import javax.swing.JTextField;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.JEditorPane;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.util.Properties;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTextArea;
import java.awt.Toolkit;
import javax.swing.JCheckBox;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent; 

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

			public static void run() {			
				try {	
					new g_TicketEntry();
					g_TicketEntry window = new g_TicketEntry();
					window.frmTicketEntry.setVisible(true);
					window.frmTicketEntry.setLocationRelativeTo( g_MainMenu.frmMainMenu );
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}


	public g_TicketEntry() {
		initialize();
		PopulateClientCB();		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmTicketEntry = new JFrame();
		frmTicketEntry.setIconImage(Toolkit.getDefaultToolkit().getImage(g_TicketEntry.class.getResource("/icon.png")));
		frmTicketEntry.setBounds(100, 100, 767, 562);
		frmTicketEntry.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmTicketEntry.getContentPane().setLayout(null);
		if(g_MainMenu.offlineMode)
		{
			frmTicketEntry.setTitle(g_MainMenu.TitleOffline);	
		}
		else
		{
			frmTicketEntry.setTitle(g_MainMenu.TitleOnline);
		}
		
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
		cb_Category.setModel(new DefaultComboBoxModel<String>(new String[] {"", "Injection", "PGM", "Reporting", "Safety", "Sampling", "Supply", "System", "Other"}));
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
		
		JLabel lblWorkTicket = new JLabel("Ticket #");
		lblWorkTicket.setFont(new Font("Plantagenet Cherokee", Font.BOLD, 16));
		lblWorkTicket.setBounds(551, 11, 78, 20);
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
					g_CurrentTickets.run();
					frmTicketEntry.dispose();
				}
				else
				{
					g_MainMenu.run(frmTicketEntry);
					frmTicketEntry.dispose();
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
		

	}
	
	private void PopulateClientCB(){
		 String commandText = "SELECT DISTINCT Client FROM Sites ORDER BY Client Asc";        
	        ResultSet rs = c_Query.ExecuteResultSet(commandText);
	        	     
	        try {
				while((rs!=null) && (rs.next()))
				{					
					String client = rs.getString(1);				
					cb_Client.addItem(client);	
				}
			} catch (SQLException e) {
			}
	        try {
				rs.close();
			} catch (SQLException e) {
			
			}
	        
	        commandText = "SELECT DISTINCT Name FROM EN_Employees WHERE Active = 1 ORDER BY Name Asc";        
	        rs = c_Query.ExecuteResultSet(commandText);
	        try {
				while((rs!=null) && (rs.next()))
				{					
					String name = rs.getString(1);				
					cb_Assign.addItem(name);	
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
				cb_Site.addItem(rs.getString(1));	
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

		int testMonth = datePicker.getModel().getMonth();
		testMonth++;
		int testDay = datePicker.getModel().getDay();			
		int testYear = datePicker.getModel().getYear();
		

		String strDate = testYear + "-" + testMonth + "-" + testDay + " " + LocalTime.now().toString();
		String TicketNum = "";
		if(chkbx_NoTicketNum.isSelected())
		{
			TicketNum = NoTicketNumber();			
		}
		else
		{
			TicketNum = txt_TicketNum.getText();	
		}
		
		 if(Character.isLetter(TicketNum.charAt(0)))
		 {
			 TicketNum = TicketNum.substring(1);
		 }
		/*
			if (Character.isDigit(TicketNum.charAt(0)))
			{
				if(TicketNum.charAt(1) == '-')				
				{
					//String tmp = search.replaceAll("([^\0])[0-9]{4,6}([^\0])", "");
					String tmp2 = TicketNum.replace("-", "");
					TicketNum = TicketNum.charAt(0) + "000000" + tmp2.replaceFirst("\\d","");

				}
		*/
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
		//SendEmail(Assigned, Client, Site, TicketNum, Description);
		JOptionPane.showMessageDialog(null, "Ticket # " + TicketNum + " entered successfully");	
		if(g_MainMenu.CurrentTicketsNav)
		{
			g_CurrentTickets.run();
			frmTicketEntry.dispose();
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
       
        ticketNumber = "3-" + ticketNumber.format("%04d", count);
        
		return ticketNumber;
	}
	
	
	 public static void SendEmail(String Assigned, String Client, String Site, String Ticket, String Description){  
		 //final String username = "ENE.TJOHNSTON@gmail.com";
		 final String username = "butane.support@enengineering.com";
			//final String password = "Butane#Ops";
		 final String password = "Sunoco2017$";


			Properties props = new Properties();
			/*props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.port", "587");
			 */
			props.put("mail.transport.protocol", "smtp");
			props.put("mail.smtp.host", "mail.enengineering.com");
			props.put("mail.smtp.port", "2525");
			props.put("mail.smtp.auth", "true");
			Session session = Session.getInstance(props,
			  new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(username, password);
				}
			  });
			
			String commandText = "SELECT Email from EN_Employees WHERE Name= '" + Assigned + "'";
			String emailAddr = "";
			ResultSet rs = c_Query.ExecuteResultSet(commandText);
			try {
				rs.next();
				emailAddr = rs.getString(1);
			} catch (SQLException e) {
			}
	        try {
				rs.close();
			} catch (SQLException e) {
			}
	      
			try {

				Message message = new MimeMessage(session);
				//message.setFrom(new InternetAddress("travisreidjohnston@gmail.com"));
				message.setFrom(new InternetAddress("butane.support@enengineering.com"));
				message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(emailAddr));
				message.setSubject("New Ticket Assigned: " + Client + " - " + Site + ": #" + Ticket);
				message.setText("Dear " + Assigned + ","
					+ "\n\n Please look into the ticket below"
					+ "\n\n " + Client + "-" + Site + " " + Ticket  
					+ "\n\n Issue: " + Description + " "
					+ "\n\n Thanks," 
					+ "\n\n Butane.Support"			
					);

				Transport.send(message);

				//System.out.println("Done");
			}
			catch(Exception e)
			{
				
			}
	 }
}
