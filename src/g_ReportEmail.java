import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Calendar;
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
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import javax.swing.border.EtchedBorder;

import net.sourceforge.jtds.jdbc.DateTime;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Toolkit;

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
					g_ReportEmail window = new g_ReportEmail();
					window.frmReportEmail.setVisible(true);
					window.frmReportEmail.setLocationRelativeTo( g_CurrentTickets.frmCurrentTickets );
					
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
		frmReportEmail.setBounds(100, 100, 800, 906);
		frmReportEmail.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmReportEmail.getContentPane().setLayout(null);
		if(g_MainMenu.offlineMode)
		{
			frmReportEmail.setTitle("Automated Support Program - OFFLINE");	
		}
		else
		{
			frmReportEmail.setTitle("Automated Support Program");
		}
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 38, 764, 469);
		frmReportEmail.getContentPane().add(scrollPane);
		
		final JEditorPane editorPane = new JEditorPane();
		editorPane.setFont(new Font("Calibri", Font.PLAIN, 11));
		scrollPane.setViewportView(editorPane);
		editorPane.setContentType("text/html");
		editorPane.setText(SupportEmail);
		
		JButton btnCopyToClipboard = new JButton("Generate Draft Email");
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
					mailto(Arrays.asList(test),date2 + " " + am_pm + " Support Update", "");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (URISyntaxException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
		btnCopyToClipboard.setBounds(577, 767, 155, 43);
		frmReportEmail.getContentPane().add(btnCopyToClipboard);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setViewportBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		scrollPane_1.setBounds(10, 569, 194, 241);
		frmReportEmail.getContentPane().add(scrollPane_1);
		
		final JList list = new JList();
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
		
		JButton btnSelectAll = new JButton("Copy to Clipboard");
		btnSelectAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editorPane.selectAll();
				editorPane.copy();
			}
		});
		btnSelectAll.setBounds(577, 533, 155, 43);
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
	}
	
	private void PopulateDefaultEmails()
	{
		String name = "";
		for(int i=0; i < EmailList.getModel().getSize(); i++)
		{
			name = EmailList.getModel().getElementAt(i).getName().toString();
		    if(name.compareTo("Bill Tirri") == 0	|| name.compareTo("Bob Crowley") == 0 || 
		       name.compareTo("Jim Robbins") == 0 ||   name.compareTo("Joe Klems") == 0 ||
		       name.compareTo("Chris Sheedy") == 0 || name.compareTo("Butane Support") == 0 ||
		       name.compareTo("James McClintock") == 0)
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
	
	public static void mailto(List<String> recipients, String subject,
	        String body) throws IOException, URISyntaxException {
	    String uriStr = String.format("mailto:%s?subject=%s&body=%s",
	            join(",", recipients), // use semicolon ";" for Outlook!
	            urlEncode(subject),
	            urlEncode(body));
	    Desktop.getDesktop().browse(new URI(uriStr));
	}

	private static final String urlEncode(String str) {
	    try {
	        return URLEncoder.encode(str, "UTF-8").replace("+", "%20");
	    } catch (UnsupportedEncodingException e) {
	        throw new RuntimeException(e);
	    }
	}

	public static final String join(String sep, Iterable<?> objs) {
	    StringBuilder sb = new StringBuilder();
	    for(Object obj : objs) {
	        if (sb.length() > 0) sb.append(sep);
	        sb.append(obj);
	    }
	    return sb.toString();
	}
	
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
		Date TOC_Date = null;
		SupportEmail = "<html>";
		
		for(int i = 0; i < TicketList.size(); i++)
		{
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
					issue = rs.getString("Description");
					investigation = rs.getString("Resolution");
					status = rs.getString("Status");

					if(!g_MainMenu.offlineMode)
					{
						TOC = rs.getTimestamp("UpdateDate");
						TOC.setHours(TOC.getHours()+1); //Update to Eastern Time Zone.
						TOC_Formatted = new SimpleDateFormat("MM/dd/yyyy hh:mm").format(TOC);
					}
					else
					{
						TOC_Formatted = rs.getString("UpdateDate");
						
						SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm"); //First put string into date format						
						Date date = null;
						try {
							date = formatter.parse(TOC_Formatted);							
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();							
						}						
						Calendar cal = Calendar.getInstance();
						cal.setTime(date);							
						cal.add(Calendar.HOUR_OF_DAY, 1); //Update to Eastern Time Zone		
						SimpleDateFormat tmpFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm"); //Used to convert date format to email format.
						TOC_Formatted = tmpFormat.format(cal.getTime());//Format to appropriate date format.												
					}
					
					duration = rs.getString("TimeSpent");	
					if((rs.getString("CCNotified") != null) && (rs.getString("CCNotified").compareTo("null") != 0))
					{
						if(!g_MainMenu.offlineMode)
						{

							ccNotifiedTime = rs.getTimestamp("CCNotified");
							ccNotifiedTime.setHours(TOC.getHours()+1); //Update to Eastern Time Zone.
							ccNotifiedTime_Formatted = new SimpleDateFormat("MM/dd/yyyy hh:mm").format(ccNotifiedTime);							
						}
						else
						{
							ccNotifiedTime_Formatted = rs.getString("CCNotified");							
							SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm"); //First put string into date format						
							Date date = null;
							try {
								date = formatter.parse(ccNotifiedTime_Formatted);							
							} catch (ParseException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();							
							}						
							Calendar cal = Calendar.getInstance();
							cal.setTime(date);							
							cal.add(Calendar.HOUR_OF_DAY, 1); //Update to Eastern Time Zone		
							SimpleDateFormat tmpFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm"); //Used to convert date format to email format.
							ccNotifiedTime_Formatted = tmpFormat.format(cal.getTime()) + " EST.";//Format to appropriate date format.						
							
							
						}
						
					}
					else
					{
						ccNotifiedTime_Formatted = "N/A";
					}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			SupportEmail = SupportEmail + "<u>" + client + " " + site + " " + " #" + ticketnumber + ":" + "</u> <br />" +
									"<b>Issue: </b>" + issue + "<br />" +
									"<b>Investigation: </b>" + investigation + "<br />" +
									"<b>Status: </b> " + status +"<br />" +
									"<b>Time of Completion: </b>" + TOC_Formatted + " EST.<br />" +
									"<b>Duration: </b>" + duration + " hours. <br />" + 
									"<b>CCNotified: </b>" + ccNotifiedTime_Formatted + " <br /><br />";
		}
		
		SupportEmail = SupportEmail + "</html>";
		
		run();
		
	}
}
