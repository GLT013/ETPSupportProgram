import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.ListSelectionModel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;


public class g_SupportArchive {	
	public static JFrame frmSupportArchive;
	public static JScrollPane eq2ScrollPane;	
	public static JList<c_Equipment> eqSiteList;
	public static JList<c_Equipment> eqTicketList;
	public static JList<c_Equipment> eqCategoryList;		
	public static DefaultListModel<c_Equipment> eqListModel;
	public static DefaultListModel<c_Equipment> eqListModel2;
	public static DefaultListModel<c_Equipment> eqListModel3;// = new DefaultListModel<Equipment>();		
	public static JTextField Comments_txtArea;
	public static JTextField txtSearch;	
	public static JTextArea Resolution_textArea;
	public static JTextArea Problem_textArea;
	public static String search;
	public static boolean search1 = true;
	public static JLabel lblDemoV;
	public static JLabel label_1;
	public static JLabel label;
	public static JLabel label_2;	
	public static boolean QuickLookUp = false;
	
	/**
	 * Quick Lookup Main Function
	 */
	public static void run() {
	
		try {	
			new g_SupportArchive();
			frmSupportArchive.setVisible(true);
			frmSupportArchive.setLocationRelativeTo(g_MainMenu.frmMainMenu);	
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		//If connection is successful, call this function.
		
		populateSiteFromDB();
		populateTicketFromDB();
		populateCategoryFromDB();
		
	}
		
	
	
	/**
	 * Create the application.
	 */
	public g_SupportArchive() {
		initialize();			
	}
	
	
	public static void populateTicketFromDB()
	{
		eqListModel2 = new DefaultListModel<c_Equipment>();		
		String commandText = "SELECT DISTINCT Ticket FROM SupportTickets ORDER BY Ticket asc";
		ResultSet rs = c_Query.ExecuteResultSet(commandText);
		try {
			while ((rs!=null) && (rs.next()))
			{
				c_Equipment projNum = new c_Equipment(rs.getString(1));
				eqListModel2.addElement(projNum);
			}
		}
		catch (SQLException e)
		{
			JOptionPane.showMessageDialog(null, "populateSiteFromDB" + e.toString());
		}
		eqTicketList.setModel(eqListModel2);
	}
	
	
	public static void populateTicketFromDB(String ticket)
	{
		eqListModel2 = new DefaultListModel<c_Equipment>();		
		String commandText = "SELECT DISTINCT Ticket FROM SupportTickets WHERE Ticket = '" + ticket + "' ORDER BY Ticket asc";
		ResultSet rs = c_Query.ExecuteResultSet(commandText);
		try {
			while ((rs!=null) && (rs.next()))
			{
				c_Equipment projNum = new c_Equipment(rs.getString(1));
				eqListModel2.addElement(projNum);
			}
		}
		catch (SQLException e)
		{
			JOptionPane.showMessageDialog(null, "populateSiteFromDB" + e.toString());
		}
		eqTicketList.setModel(eqListModel2);
	}
	
	
	public static void populateCategoryFromDB()
	{
		eqListModel3 = new DefaultListModel<c_Equipment>();			
		c_Equipment proj = new c_Equipment("All Categories");		
		eqListModel3.addElement(proj);
		String commandText = "SELECT DISTINCT Category FROM SupportTickets ORDER BY Category ASC";
		ResultSet rs = c_Query.ExecuteResultSet(commandText);
		try {
			while ((rs!=null) && (rs.next()))
			{
				String partNumber = rs.getString(1);			
				c_Equipment partNum = new c_Equipment(partNumber);
				eqListModel3.addElement(partNum);
			
			}
		}
		catch (SQLException e)
		{
			JOptionPane.showMessageDialog(null, "populateCategoryFromDB" + e.toString());
		}
		
		eqCategoryList.setModel(eqListModel3);
	}
	
	
	public static void populateCategoryFromDB(String categorySelected)
	{
		eqListModel3 = new DefaultListModel<c_Equipment>();		
		String commandText = "SELECT DISTINCT Category FROM SupportTickets WHERE Category = '" + categorySelected + "' ORDER BY Category ASC";
		ResultSet rs = c_Query.ExecuteResultSet(commandText);
		try {
			while ((rs!=null) && (rs.next()))
			{
				String partNumber = rs.getString(1);			
				c_Equipment partNum = new c_Equipment(partNumber);
				eqListModel3.addElement(partNum);
			
			}
		}
		catch (SQLException e)
		{
			JOptionPane.showMessageDialog(null, "populateCategoryFromDB" + e.toString());
		}
		
		eqCategoryList.setModel(eqListModel3);
	}
	
	

	
	public static void populateSiteFromDB()
	{
		
		eqListModel = new DefaultListModel<c_Equipment>();		
		c_Equipment proj = new c_Equipment("All Sites");		
		eqListModel.addElement(proj);
        String commandText = "SELECT DISTINCT Client, Site FROM SupportTickets ORDER BY Client Asc";        
        ResultSet rs = c_Query.ExecuteResultSet(commandText);
     
        try {
			while((rs!=null) && (rs.next()))
			{
				
				String project = rs.getString(1) + " - " + rs.getString(2);				
							
				proj = new c_Equipment(project);
				
				eqListModel.addElement(proj);			

			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "populateSiteFromDB" + e.toString());
		}
        try {
			rs.close();
		} catch (SQLException e) {
		
			JOptionPane.showMessageDialog(null, "Error Closing populateSiteFromDB" + e.toString());
		}
		eqSiteList.setModel(eqListModel);   
	}
	
	
	public static void populateSiteFromDB(String client, String site)
	{
		
		eqListModel = new DefaultListModel<c_Equipment>();		
		c_Equipment proj = new c_Equipment("All Sites");		
		eqListModel.addElement(proj);
        String commandText = "SELECT DISTINCT Site FROM SupportTickets WHERE Site = '" + site + "' ORDER BY Site Asc";        
        ResultSet rs = c_Query.ExecuteResultSet(commandText);
     
        try {
			while((rs!=null) && (rs.next()))
			{
				
				String project = rs.getString(1);				
							
				proj = new c_Equipment(project);
				
				eqListModel.addElement(proj);			

			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "populateSiteFromDB" + e.toString());
		}
        try {
			rs.close();
		} catch (SQLException e) {
		
			JOptionPane.showMessageDialog(null, "Error Closing populateSiteFromDB" + e.toString());
		}
		eqSiteList.setModel(eqListModel);   
	}
	
	public static void Search()
	{
		eqListModel3.removeAllElements();	
		eqListModel2.removeAllElements();
		eqListModel.removeAllElements();
		
		search = txtSearch.getText().toUpperCase();
		
		String commandText = "";
		
		if ((search.length() >= 2)) 
		{			
			if (Character.isDigit(search.charAt(0)))
			{
				/*if(search.charAt(1) == '-')				
				{
					//String tmp = search.replaceAll("([^\0])[0-9]{4,6}([^\0])", "");
					String tmp2 = search.replace("-", "");
					modifiedSearch = search.charAt(0) + "000000" + tmp2.replaceFirst("\\d","");

				}
				else
				{
					modifiedSearch = search;
				}
			*/	
				commandText = "SELECT Client, Site, Ticket, Category FROM SupportTickets WHERE Ticket LIKE " + "'%" + search + "%'";
			}
			else
			{
				commandText = "SELECT Client, Site, Ticket, Category FROM SupportTickets WHERE UPPER(Description) LIKE " + "'%" + search + "%' OR Client LIKE '%" + search + "%'" + "OR Site LIKE '%" + search + "%'";
			}
			
		String site = "";
		String ticket = "";
		String tech = "";
		
		ResultSet rs = c_Query.ExecuteResultSet(commandText);
			try {
				while ((rs!=null) && (rs.next()))
				{
					
					site = rs.getString(1) + " - " + rs.getString(2);
					ticket = rs.getString(3);
					tech = rs.getString(4);
					
					c_Equipment site_ = new c_Equipment(site);
					c_Equipment ticket_ = new c_Equipment(ticket);
					c_Equipment tech_ = new c_Equipment(tech);
					
					eqListModel.addElement(site_);	
					eqListModel2.addElement(ticket_);	
					eqListModel3.addElement(tech_);			
				}
				
				eqSiteList.setModel(eqListModel);
				eqTicketList.setModel(eqListModel2);
				eqCategoryList.setModel(eqListModel3);
			}
			catch (SQLException e)
			{
				
			}
		

		try
		{
		eqTicketList.setSelectedValue(eqListModel2.firstElement(), false);
		}
		catch (Exception e)
		{
			//ClearAll();
			
		}
		
		}
		else
		{
			ClearAll();
			
		}
		
		
	}
	
	
	public static void ClearFields()
	{	
		
		Problem_textArea.setText(null);			
		Resolution_textArea.setText(null);				
	
	}
		
	public void populateTicketsfromSite(String siteSelected)
	{
		eqListModel3.removeAllElements();	
		eqListModel2.removeAllElements();
		
				
		c_Equipment proj = new c_Equipment("All Categories");		
		eqListModel3.addElement(proj);
		
		
		
		String commandText = "";
		String commandText2 = "";
		
		if (siteSelected.equals("All Sites"))
		{
				populateSiteFromDB();
				populateCategoryFromDB();
				populateTicketFromDB();
		}
		else
		{
			String[] parts = siteSelected.split(" - ", 2);
			String client = parts[0];
			String site = parts[1];
			commandText = "SELECT DISTINCT Ticket FROM SupportTickets WHERE Site = '" + site + "' AND Client = '" + client + "' ORDER BY Ticket asc";
			commandText2 = "SELECT DISTINCT Category FROM SupportTickets WHERE Site = '" + site + "' AND Client = '" + client + "' ORDER BY Category asc";
		
		
		ResultSet rs = c_Query.ExecuteResultSet(commandText);
		try {
			while ((rs!=null) && (rs.next()))
			{
				c_Equipment projNum = new c_Equipment(rs.getString(1));
				eqListModel2.addElement(projNum);
			}
		}
		catch (SQLException e)
		{
			JOptionPane.showMessageDialog(null, "populateSiteFromDB" + e.toString());
		}
		eqTicketList.setModel(eqListModel2);
		
		
		rs = c_Query.ExecuteResultSet(commandText2);
		try {
			while ((rs!=null) && (rs.next()))
			{
				String projectNum = rs.getString(1);
				c_Equipment projNum = new c_Equipment(projectNum);
				eqListModel3.addElement(projNum);
			}
		}
		catch (SQLException e)
		{
			JOptionPane.showMessageDialog(null, "populateSiteFromDB" + e.toString());
		}
		eqCategoryList.setModel(eqListModel3);
		}
	}
	
	
	
	public void PopulateTicketsFromCategory(String categorySelected)
	{
		eqListModel.removeAllElements();	
		eqListModel2.removeAllElements();
		
		
		c_Equipment proj = new c_Equipment("All Sites");		
		eqListModel.addElement(proj);
		
		String commandText = "";
		String commandText2 = "";
		
		if (categorySelected.equals("All Categories"))
		{			
			populateSiteFromDB();
			populateTicketFromDB();
			populateCategoryFromDB();
		}
		else
		{
			
			commandText = "SELECT DISTINCT Ticket FROM SupportTickets WHERE Category = '" + categorySelected + "' ORDER BY Ticket asc";
			commandText2 = "SELECT DISTINCT Client, Site FROM SupportTickets WHERE Category = '" + categorySelected + "' ORDER BY Site asc";
			
			ResultSet rs = c_Query.ExecuteResultSet(commandText);
			try {
				while ((rs!=null) && (rs.next()))
				{
					c_Equipment projNum = new c_Equipment(rs.getString(1));
					eqListModel2.addElement(projNum);
				}
			}
			catch (SQLException e)
			{
				JOptionPane.showMessageDialog(null, "populateSiteFromDB" + e.toString());
			}
			eqTicketList.setModel(eqListModel2);
			
			
			rs = c_Query.ExecuteResultSet(commandText2);
			try {
				while ((rs!=null) && (rs.next()))
				{
					String projectNum = rs.getString(1);
					c_Equipment projNum = new c_Equipment(projectNum);
					eqListModel.addElement(projNum);
				}
			}
			catch (SQLException e)
			{
				JOptionPane.showMessageDialog(null, "populateSiteFromDB" + e.toString());
			}
			eqSiteList.setModel(eqListModel);
		}
		
		
		
		
		
		
	}



	public static void populateFieldsfromDB(String ticket)
	{
		String commandText = "SELECT Description, Resolution FROM SupportTickets WHERE Ticket = " + "'" + ticket + "'";        
	    ResultSet rs = c_Query.ExecuteResultSet(commandText);
	    try {
			if((rs!=null) && (rs.next()))
			{	
				
				Problem_textArea.setText(rs.getString("Description"));					
				Resolution_textArea.setText(rs.getString("Resolution"));				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        try {
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void ClearAll()
	{
		eqListModel.clear();
		eqListModel2.clear();
		eqListModel3.clear();		
		populateSiteFromDB();
		populateTicketFromDB();
		populateCategoryFromDB();

	}
	
	
	private void initialize() {
		frmSupportArchive = new JFrame();
		frmSupportArchive.setTitle("Support Ticket Lookup");		
		frmSupportArchive.setSize(710,789);
		frmSupportArchive.setLocationRelativeTo(null);	
		frmSupportArchive.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSupportArchive.getContentPane().setLayout(null);
		
		
		txtSearch = new JTextField();
		txtSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				Search();
			}
			@Override
			public void keyReleased(KeyEvent e) {
				Search();
			}
		});
		txtSearch.setToolTipText("Search For Issue or Ticket");
		txtSearch.setFocusable(true);			
		txtSearch.setBounds(518, 20, 136, 30);
		frmSupportArchive.getContentPane().add(txtSearch);
		txtSearch.setColumns(10);
		
		
		JLabel lblNewLabel = new JLabel("Site");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(241, 67, 210, 14);
		frmSupportArchive.getContentPane().add(lblNewLabel);
		
		
		JScrollPane eqScrollPane = new JScrollPane();
		eqScrollPane.setBounds(241, 92, 210, 168);
		frmSupportArchive.getContentPane().add(eqScrollPane);
		

		
		eqSiteList = new JList<c_Equipment>();
		eqSiteList.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if(!e.getValueIsAdjusting()){
				
					try{
						eqTicketList.setSelectedIndex(-1);
						c_Equipment selected = eqSiteList.getSelectedValue();
						String selectedValue = selected.toString();	
						populateTicketsfromSite(selectedValue);	
					}
					catch (Exception e1)
					{
						
					}
					
				}
				
				
			}
			
		});
		

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(g_SupportArchive.class.getResource("/075837-3d-glossy-blue-orb-icon-business-magnifying-glass-ps_48.png")));
		lblNewLabel_1.setToolTipText("Search");
		lblNewLabel_1.setBounds(458, 11, 48, 48);
		frmSupportArchive.getContentPane().add(lblNewLabel_1);
		
		eqScrollPane.setViewportView(eqSiteList);
		eqSiteList.setFont(new Font("Tahoma", Font.BOLD, 12));
		eqSiteList.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		eqSiteList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		
		eq2ScrollPane = new JScrollPane();
		eq2ScrollPane.setBounds(458, 92, 210, 168);
		frmSupportArchive.getContentPane().add(eq2ScrollPane);
		
		eqTicketList = new JList<c_Equipment>();
		eq2ScrollPane.setViewportView(eqTicketList);
		eqTicketList.setFont(new Font("Tahoma", Font.BOLD, 12));
		eqTicketList.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		eqTicketList.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				
				if(!e.getValueIsAdjusting()){					
		
							c_Equipment selected = eqTicketList.getSelectedValue();	
							try{
								String selectedValue = selected.toString();
								populateFieldsfromDB(selectedValue);
							}
							catch (Exception e1)
							{
							}					
						
					}
					
				}
				
			
			
		});
		
		JLabel lblProjectNumber = new JLabel("Ticket Number");
		lblProjectNumber.setHorizontalAlignment(SwingConstants.CENTER);
		lblProjectNumber.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblProjectNumber.setBounds(458, 67, 210, 14);
		frmSupportArchive.getContentPane().add(lblProjectNumber);
		
		JScrollPane eq3scrollPane = new JScrollPane();
		eq3scrollPane.setBounds(23, 92, 210, 168);
		frmSupportArchive.getContentPane().add(eq3scrollPane);
		
		eqCategoryList = new JList<c_Equipment>();
		eqCategoryList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		eq3scrollPane.setViewportView(eqCategoryList);
		eqCategoryList.setFont(new Font("Tahoma", Font.BOLD, 12));
		eqCategoryList.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		eqCategoryList.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if(!e.getValueIsAdjusting()){			
				c_Equipment selected = eqCategoryList.getSelectedValue();
				try
				{
					String selectedValue = selected.toString();
					PopulateTicketsFromCategory(selectedValue);
				}
				catch (Exception e1)
				{
					
				}
				
				}
				
				
			}
			
		});
		
		JLabel lblPartNumber = new JLabel("Category");
		lblPartNumber.setHorizontalAlignment(SwingConstants.CENTER);
		lblPartNumber.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPartNumber.setBounds(23, 67, 210, 14);
		frmSupportArchive.getContentPane().add(lblPartNumber);
		
		JLabel Description = new JLabel("Problem");
		Description.setHorizontalAlignment(SwingConstants.CENTER);
		Description.setFont(new Font("Tahoma", Font.BOLD, 14));
		Description.setBounds(41, 271, 126, 14);
		frmSupportArchive.getContentPane().add(Description);
		
			
		JLabel lblComments = new JLabel("Resolution");
		lblComments.setHorizontalAlignment(SwingConstants.CENTER);
		lblComments.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblComments.setBounds(51, 475, 102, 14);
		frmSupportArchive.getContentPane().add(lblComments);
		
		label = new JLabel("");
		label.setToolTipText("EN Engineering");
		label.setBounds(33, 617, 222, 33);
		frmSupportArchive.getContentPane().add(label);
		
		label_2 = new JLabel("");
		label_2.setText("1.0.6");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setBounds(596, 698, 72, 14);
		frmSupportArchive.getContentPane().add(label_2);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(23, 296, 645, 168);
		frmSupportArchive.getContentPane().add(scrollPane);
		
		Problem_textArea = new JTextArea();
		scrollPane.setViewportView(Problem_textArea);
		Problem_textArea.setWrapStyleWord(true);
		Problem_textArea.setFont(new Font("Latha", Font.PLAIN, 16));
		Problem_textArea.setColumns(5);
		Problem_textArea.setEditable(false);
		Problem_textArea.setLineWrap(true);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(23, 500, 645, 192);
		frmSupportArchive.getContentPane().add(scrollPane_1);
		
			
		Resolution_textArea = new JTextArea();
		scrollPane_1.setViewportView(Resolution_textArea);
		Resolution_textArea.setWrapStyleWord(true);
		Resolution_textArea.setLineWrap(true);
		Resolution_textArea.setFont(new Font("Ebrima", Font.BOLD, 16));
		Resolution_textArea.setEditable(false);
		
		JButton btnNewButton = new JButton("Back");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				g_MainMenu.run(frmSupportArchive);
				frmSupportArchive.dispose();
			}
		});
		btnNewButton.setBounds(23, 717, 89, 23);
		frmSupportArchive.getContentPane().add(btnNewButton);
		
	
	
}
}
