import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JEditorPane;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URISyntaxException;
import java.awt.event.ActionEvent;
import javax.swing.border.EtchedBorder;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class g_ReportEmail {

	private static JFrame frmReportEmail;
	private static String SupportEmail = "";
	public static JList<c_EmailRecipients> EmailList;
	public static DefaultListModel<c_EmailRecipients> EmailListModel;
	public static JList<c_EmailRecipients> EmailList2;
	public static DefaultListModel<c_EmailRecipients> EmailListModel2;
	private static JScrollPane scrollPane_1;
	private static JScrollPane scrollPane_2;

	/**
	 * Launch the application.
	 */
	
			public static void run() {
				try {
					@SuppressWarnings("unused")
					g_ReportEmail window = new g_ReportEmail();
					g_ReportEmail.frmReportEmail.setVisible(true);
					g_ReportEmail.frmReportEmail.setLocationRelativeTo( g_CurrentTickets.frmCurrentTickets );
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

	/**
	 * Create the application.
	 */
	public g_ReportEmail() {
		initialize();
		PopulateEmployees();
		PopulateDefaultEmails();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmReportEmail = new JFrame();
		frmReportEmail.setIconImage(Toolkit.getDefaultToolkit().getImage(g_ReportEmail.class.getResource("/icon.png")));
		frmReportEmail.setBounds(100, 100, 800, 900);
		frmReportEmail.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmReportEmail.getContentPane().setLayout(null);
		frmReportEmail.setTitle("Automated Support Program v." + g_MainMenu.version);
						
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 38, 764, 469);
		frmReportEmail.getContentPane().add(scrollPane);
		
		final JEditorPane editorPane = new JEditorPane();
		editorPane.setFont(new Font("Calibri", Font.PLAIN, 11));
		scrollPane.setViewportView(editorPane);
		editorPane.setContentType("text/html");
		editorPane.setText(SupportEmail);
		
		JButton btnCopyToClipboard = new JButton("Generate Email");
		btnCopyToClipboard.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnCopyToClipboard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				editorPane.selectAll();
				editorPane.copy();
				
				DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
				Date date = new Date();
				String date2 = dateFormat.format(date);				
				@SuppressWarnings("deprecation")
				int hour = date.getHours();
						String am_pm = "";
				if(hour < 12)
				{
					am_pm = "Morning";
				}
				else
				{
					am_pm = "Afternoon";
				}
				String test = EmailList2.getModel().getElementAt(0).getEmail();
				for(int i=1; i < EmailList2.getModel().getSize(); i++){
				     test =  test + ";" + EmailList2.getModel().getElementAt(i).getEmail();  
				     
				}
				
				try {
					c_Email.mailto(Arrays.asList(test),date2 + " " + am_pm + " Support Update", "");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (URISyntaxException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
		btnCopyToClipboard.setBounds(651, 831, 117, 29);
		frmReportEmail.getContentPane().add(btnCopyToClipboard);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setViewportBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		scrollPane_1.setBounds(10, 569, 194, 241);
		frmReportEmail.getContentPane().add(scrollPane_1);
		
		final JList<?> list = new JList<Object>();
		scrollPane_1.setViewportView(list);
		
		JButton btnNewButton = new JButton(">>");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addRecipients();
				
			}
		});
		btnNewButton.setBounds(214, 638, 89, 23);
		frmReportEmail.getContentPane().add(btnNewButton);
		
		scrollPane_2 = new JScrollPane();
		scrollPane_2.setViewportBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		scrollPane_2.setBounds(325, 569, 194, 241);
		frmReportEmail.getContentPane().add(scrollPane_2);
		
		
		EmailList2 = new JList<c_EmailRecipients>();
		EmailList2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane_2.setViewportView(EmailList2);
		
		JButton button = new JButton("<<");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RemoveRecipient();
			}
		});
		button.setBounds(214, 708, 89, 23);
		frmReportEmail.getContentPane().add(button);
		
		JButton btnSelectAll = new JButton("Copy");
		btnSelectAll.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnSelectAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editorPane.selectAll();
				editorPane.copy();
			}
		});
		btnSelectAll.setBounds(524, 831, 117, 29);
		frmReportEmail.getContentPane().add(btnSelectAll);
		
		JLabel lblNewLabel = new JLabel("Email Preview");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(10, 11, 119, 29);
		frmReportEmail.getContentPane().add(lblNewLabel);
		
		JLabel lblContactList = new JLabel("Contact List");
		lblContactList.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblContactList.setBounds(10, 529, 119, 29);
		frmReportEmail.getContentPane().add(lblContactList);
		
		JLabel lblEmailRecipients = new JLabel("Email Recipients");
		lblEmailRecipients.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblEmailRecipients.setBounds(324, 529, 119, 29);
		frmReportEmail.getContentPane().add(lblEmailRecipients);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				g_CurrentTickets.run();
				frmReportEmail.dispose();
			}
		});
		btnBack.setBounds(10, 834, 89, 23);
		frmReportEmail.getContentPane().add(btnBack);
		
		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setBounds(544, 587, 193, 54);
		frmReportEmail.getContentPane().add(panel);
		panel.setLayout(null);
		
		JButton btnDayShift = new JButton("Day Shift");
		btnDayShift.setBounds(10, 22, 77, 23);
		panel.add(btnDayShift);
		
		JButton btnOffHours = new JButton("Off Hours");
		btnOffHours.setBounds(97, 22, 86, 23);
		panel.add(btnOffHours);
		
		JLabel lblBlendSeason = new JLabel("Blend Season");
		lblBlendSeason.setBounds(0, 0, 193, 14);
		panel.add(lblBlendSeason);
		lblBlendSeason.setHorizontalAlignment(SwingConstants.CENTER);
		lblBlendSeason.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_1.setBounds(544, 663, 193, 94);
		frmReportEmail.getContentPane().add(panel_1);
		
		JButton button_1 = new JButton("Day Shift");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PopulateSummerDayEmails();
			}
		});
		button_1.setBounds(10, 22, 77, 23);
		panel_1.add(button_1);
		
		JButton btnFieldResourcesNeeded = new JButton("Field Resources Needed");
		btnFieldResourcesNeeded.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PopulateSummerFieldResources();
			}
		});
		btnFieldResourcesNeeded.setBounds(10, 60, 173, 23);
		panel_1.add(btnFieldResourcesNeeded);
		
		JLabel lblSummer = new JLabel("Summer");
		lblSummer.setHorizontalAlignment(SwingConstants.CENTER);
		lblSummer.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblSummer.setBounds(0, 0, 193, 14);
		panel_1.add(lblSummer);
		
		JButton btnOffHours_1 = new JButton("Off Hours");
		btnOffHours_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PopulateSummerOffHourEmails();
			}
		});
		btnOffHours_1.setBounds(96, 22, 87, 23);
		panel_1.add(btnOffHours_1);
		
		JLabel lblEmailDistributions = new JLabel("Email Distribution Lists");
		lblEmailDistributions.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblEmailDistributions.setBounds(544, 529, 173, 29);
		frmReportEmail.getContentPane().add(lblEmailDistributions);
		btnOffHours.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PopulateOffHourEmails();
			}
		});
		btnDayShift.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PopulateDayShiftEmails();
			}
		});
	}
	
	private void PopulateDefaultEmails()
	{
		String name = "";
		for(int i=0; i < EmailList.getModel().getSize(); i++)
		{
			name = EmailList.getModel().getElementAt(i).getName().toString();
		    if(name.compareTo("Butane Support") == 0)
		    {
		    	addRecipients(i);
		    	i--;
		    }
		}
	}
	
	private void PopulateOffHourEmails()
	{
		String name = "";
		for(int i=0; i < EmailList.getModel().getSize(); i++)
		{
			name = EmailList.getModel().getElementAt(i).getName().toString();
		    if(name.compareTo("Bill Tirri") == 0	|| name.compareTo("Bob Crowley") == 0 || 
		       name.compareTo("James Waymire") == 0 ||   name.compareTo("Joe Klems") == 0 ||
		       name.compareTo("Chris Sheedy") == 0 || name.compareTo("Sean McCausland") == 0 ||
		       name.compareTo("James McClintock") == 0 || name.compareTo("James Banks") == 0 ||
		       name.compareTo("Elizabeth Parzanese") == 0 || name.compareTo("Keegan Lowry") == 0)
		    {
		    	addRecipients(i);
		    	i--;
		    }
		}
	}
	
	private void PopulateSummerOffHourEmails()
	{
		String name = "";
		for(int i=0; i < EmailList.getModel().getSize(); i++)
		{
			name = EmailList.getModel().getElementAt(i).getName().toString();
		    if(name.compareTo("Bill Tirri") == 0	|| name.compareTo("Bob Crowley") == 0 || 
		       name.compareTo("Joe Klems") == 0 ||     name.compareTo("Chris Sheedy") == 0 || 
		       name.compareTo("Jim Robbins") == 0 ||   name.compareTo("James McClintock") == 0 || 
		       name.compareTo("Keegan Lowry") == 0)
		    {
		    	addRecipients(i);
		    	i--;
		    }
		}
	}
	
	private void PopulateSummerDayEmails()
	{
		String name = "";
		for(int i=0; i < EmailList.getModel().getSize(); i++)
		{
			name = EmailList.getModel().getElementAt(i).getName().toString();
		    if(name.compareTo("Bill Tirri") == 0	|| name.compareTo("Bob Crowley") == 0 || 
		       name.compareTo("Joe Klems") == 0 	|| name.compareTo("James McClintock") == 0 || 
		       name.compareTo("Jim Robbins") == 0   ||   name.compareTo("Keegan Lowry") == 0 ||
		       name.compareTo("Chris Sheedy") == 0)
		    {
		    	addRecipients(i);
		    	i--;
		    }
		}
	}
	
	
	private void PopulateSummerFieldResources()
	{
		String name = "";
		for(int i=0; i < EmailList.getModel().getSize(); i++)
		{
			name = EmailList.getModel().getElementAt(i).getName().toString();
		    if(name.compareTo("Bill Tirri") == 0	||   name.compareTo("Bob Crowley") == 0 || 
		       name.compareTo("Joe Klems") == 0 	||   name.compareTo("James McClintock") == 0 ||
		       name.compareTo("Jim Robbins") == 0)
		      
		    {
		    	addRecipients(i);
		    	i--;
		    }
		}
	}
	
	
	
	
	private void PopulateDayShiftEmails()
	{
		String name = "";
		for(int i=0; i < EmailList.getModel().getSize(); i++)
		{
			name = EmailList.getModel().getElementAt(i).getName().toString();
		    if(name.compareTo("Bill Tirri") == 0	|| name.compareTo("Bob Crowley") == 0 || 
		       name.compareTo("Joe Klems") == 0 ||  name.compareTo("Chris Sheedy") == 0 || 
		       name.compareTo("James McClintock") == 0 || name.compareTo("Keegan Lowry") == 0)
		    {
		    	addRecipients(i);
		    	i--;
		    }
		}
	}
	
	private void PopulateEmployees()
	{	
		
		EmailList = new JList<c_EmailRecipients>();
		EmailListModel = new DefaultListModel<c_EmailRecipients>();
		EmailListModel2 = new DefaultListModel<c_EmailRecipients>();
		c_EmailRecipients emailRecipients = new c_EmailRecipients("Butane Support","butane.support@enengineering.com");
		 EmailListModel.addElement(emailRecipients);
		String commandText = "SELECT Name, Email FROM Sunoco_Contacts UNION ALL SELECT Name, Email FROM EN_Employees ORDER BY Name asc";
		
		ResultSet rs = c_Query.ExecuteResultSet(commandText);
		try{

			while ((rs!=null) && (rs.next()))
			{
				 emailRecipients = new c_EmailRecipients(rs.getString(1),rs.getString(2));
				 EmailListModel.addElement(emailRecipients);
			}
		}
		catch(Exception e)
		{
			System.out.println("ERROR " + e.toString());
		}
		
		scrollPane_1.setViewportView(EmailList);		
		EmailList.setModel(EmailListModel);
		EmailList.setSelectedIndex(0);
	}
	
	public static void addRecipients()
	{
		c_EmailRecipients tmp = EmailList.getSelectedValue();		
		EmailListModel2.addElement(tmp);
		EmailListModel.removeElement(tmp);
		EmailList.setModel(EmailListModel);
		EmailList2.setModel(EmailListModel2);
	}
	
	
	public static void addRecipients(int id)
	{
		c_EmailRecipients tmp = EmailList.getModel().getElementAt(id);		
		EmailListModel2.addElement(tmp);
		EmailListModel.removeElement(tmp);
		EmailList.setModel(EmailListModel);
		EmailList2.setModel(EmailListModel2);
	}
	
	public static void RemoveRecipient()
	{
		c_EmailRecipients tmp = EmailList2.getSelectedValue();
		EmailListModel.addElement(tmp);;
		EmailListModel2.removeElement(tmp);
		EmailList.setModel(EmailListModel);
		EmailList2.setModel(EmailListModel2);		
	}	

	@SuppressWarnings("deprecation")
	public static void GenerateEmailReport(List<String> TicketList )
	{		
		c_ConnectToDatabase.Connect();		
		String client = "";
		String site = "";
		String ticketnumber = "";
		String issue = "";
		String investigation = "";
		String status = "";
		Timestamp TOC = null; //time of completion
		String duration = "";
		String TOC_Formatted = "";
		SupportEmail = "<html>";
		
		for(int i = 0; i < TicketList.size(); i++)
		{
			boolean ccnotified = true;
			String update = "Update SupportTickets SET EmailSent = 1 WHERE Ticket = '" + TicketList.get(i) +"'";
			c_Query.ExecuteQuery(update);
			String commandText = "SELECT Client,Site,Ticket,Description,Status,Resolution,UpdateDate,TimeSpent,CCNotified FROM SupportTickets WHERE Ticket = '" + TicketList.get(i) +"'";
			ResultSet rs = c_Query.ExecuteResultSet(commandText);
			Timestamp ccNotifiedTime = null;
			String ccNotifiedTime_Formatted  = "";
			try {
				while((rs!=null) && (rs.next()))
				{
					
					client = rs.getString("client");
					site = rs.getString("site");
					ticketnumber = rs.getString("ticket");
					if(ticketnumber.charAt(0) == '3')
					{
						ticketnumber = "?-?????";
					}
					
					issue = rs.getString("Description");
					investigation = rs.getString("Resolution");
					status = rs.getString("Status");
			
					TOC = rs.getTimestamp("UpdateDate");
					TOC.setHours(TOC.getHours()+1); //Update to Eastern Time Zone.
					TOC_Formatted = new SimpleDateFormat("MM/dd/yyyy HH:mm").format(TOC);
					
					
					duration = rs.getString("TimeSpent");	
					if((rs.getString("CCNotified") != null) && (rs.getString("CCNotified").compareTo("null") != 0))
					{						
							ccNotifiedTime = rs.getTimestamp("CCNotified");							
							ccNotifiedTime.setHours(ccNotifiedTime.getHours()+1); //Update to Eastern Time Zone.
							ccNotifiedTime_Formatted = new SimpleDateFormat("MM/dd/yyyy HH:mm").format(ccNotifiedTime) + " EST.";												
					}
					else
					{
						ccNotifiedTime_Formatted = "N/A";
						ccnotified = false;
					}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			SupportEmail = SupportEmail + "<u>" + client + " " + site + " " + " #" + ticketnumber + ":" + "</u> <br />" +
					"<b>Issue: </b>" + issue + "<br />" +
					"<b>Investigation: </b>" + investigation + "<br />";
			if(status.compareTo("Complete") != 0)
			{
				SupportEmail = SupportEmail + "<b>Status: </b><u><span style='background-color: #FFFF00'>" + status +"</span></u><br />" +
						"<b>Time of Investigation: </b>" + TOC_Formatted + " EST.<br />" +
						"<b>Duration: </b>" + duration + " hours. <br />";
			}
			else
			{
				SupportEmail = SupportEmail + "<b>Status: </b> " + status +"<br />" +
				"<b>Time of Completion: </b>" + TOC_Formatted + " EST.<br />" + 
				"<b>Duration: </b>" + duration + " hours. <br />";
			}		
			
			if(ccnotified)
			{			
			SupportEmail = SupportEmail + "<b>CCNotified: </b>" + ccNotifiedTime_Formatted + " <br />";
			}			
			
			SupportEmail = SupportEmail + "<br />"; //add a break between tickets.
		}
		
		
		SupportEmail = SupportEmail + "</html>";
		
		run();
		
	}
}
