import java.sql.ResultSet;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.border.EtchedBorder;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ButtonGroup;
import java.awt.Font;
import javax.swing.border.LineBorder;
import java.awt.Color;

public class g_EmailDistributionList {

	private JFrame frmEmailDistList;
	public static JList<c_EmailRecipients> ContactList;
	public static DefaultListModel<c_EmailRecipients> ContactListModel;
	public static JList<c_EmailRecipients> EmailList;
	public static DefaultListModel<c_EmailRecipients> EmailListModel;
	private static JScrollPane sp_DistList;
	private static JPanel pnl_blend_day;
	private static JScrollPane sp_Contacts;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private static JRadioButton rdbtnBlendSeasonDay;
	private static JRadioButton rdbtnOffHours;
	private static JRadioButton rdbtnSumDay;
	private static JRadioButton rdbtnSumOff;
	private static JRadioButton rdbtnfieldResourcesNeeded;
	private static int DistList = 1;
	
	/**
	 * Launch the application.
	 */
	public static void run(JFrame frame) {
				try {
					g_EmailDistributionList window = new g_EmailDistributionList();
					window.frmEmailDistList.setVisible(true);
					window.frmEmailDistList.setLocationRelativeTo(frame);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
	
	

	/**
	 * Create the application.
	 */
	public g_EmailDistributionList() {
		initialize();
		PopulateDistList();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmEmailDistList = new JFrame();
		frmEmailDistList.setResizable(false);
		frmEmailDistList.setBounds(100, 100, 638, 662);
		frmEmailDistList.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmEmailDistList.getContentPane().setLayout(null);
		frmEmailDistList.setTitle(g_MainMenu.TitleOnline);
		
		pnl_blend_day = new JPanel();
		pnl_blend_day.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		pnl_blend_day.setBounds(10, 91, 599, 470);
		frmEmailDistList.getContentPane().add(pnl_blend_day);
		pnl_blend_day.setLayout(null);
		
		sp_Contacts = new JScrollPane();
		sp_Contacts.setBounds(10, 61, 194, 381);
		sp_Contacts.setViewportBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		pnl_blend_day.add(sp_Contacts);
		
		ContactList = new JList<c_EmailRecipients>();
		ContactList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		ContactList.setFont(new Font("Rockwell", Font.PLAIN, 13));
		ContactList.setSelectedIndex(0);
		sp_Contacts.setViewportView(ContactList);
		
		sp_DistList = new JScrollPane();
		sp_DistList.setViewportBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		sp_DistList.setBounds(361, 60, 194, 381);
		pnl_blend_day.add(sp_DistList);			
		
		JButton button = new JButton(">>");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addRecipients();
			}
		});
		button.setBounds(233, 194, 89, 23);
		pnl_blend_day.add(button);
		
		JButton button_1 = new JButton("<<");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RemoveRecipient();
			}
		});
		button_1.setBounds(233, 264, 89, 23);
		pnl_blend_day.add(button_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel_1.setBounds(10, 11, 199, 71);
		frmEmailDistList.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		rdbtnBlendSeasonDay = new JRadioButton("Day Shift");
		rdbtnBlendSeasonDay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DistList = 1;
				PopulateDistList();
			}
		});
		rdbtnBlendSeasonDay.setFont(new Font("Rockwell", Font.PLAIN, 11));
		buttonGroup.add(rdbtnBlendSeasonDay);
		rdbtnBlendSeasonDay.setBounds(6, 41, 83, 23);
		panel_1.add(rdbtnBlendSeasonDay);
		rdbtnBlendSeasonDay.setSelected(true);
		
		rdbtnOffHours = new JRadioButton("Off Hours");
		rdbtnOffHours.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DistList = 2;
				PopulateDistList();
			}
		});
		rdbtnOffHours.setFont(new Font("Rockwell", Font.PLAIN, 11));
		buttonGroup.add(rdbtnOffHours);
		rdbtnOffHours.setBounds(112, 41, 81, 23);
		panel_1.add(rdbtnOffHours);
		
		JLabel lblBlendSeason = new JLabel("<html><center>Blend Season</center><html>");
		lblBlendSeason.setFont(new Font("Rockwell", Font.BOLD, 14));
		lblBlendSeason.setHorizontalAlignment(SwingConstants.CENTER);
		lblBlendSeason.setBounds(6, 11, 183, 14);
		panel_1.add(lblBlendSeason);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel_2.setLayout(null);
		panel_2.setBounds(245, 9, 364, 71);
		frmEmailDistList.getContentPane().add(panel_2);
		
		rdbtnSumDay = new JRadioButton("Day Shift");
		rdbtnSumDay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DistList = 3;
				PopulateDistList();
			}
			
		});
		rdbtnSumDay.setFont(new Font("Rockwell", Font.PLAIN, 11));
		buttonGroup.add(rdbtnSumDay);
		rdbtnSumDay.setBounds(6, 41, 83, 23);
		panel_2.add(rdbtnSumDay);
		
		rdbtnSumOff = new JRadioButton("Off Hours");
		rdbtnSumOff.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DistList = 4;
				PopulateDistList();
			}
		});
		rdbtnSumOff.setFont(new Font("Rockwell", Font.PLAIN, 11));
		buttonGroup.add(rdbtnSumOff);
		rdbtnSumOff.setBounds(104, 41, 83, 23);
		panel_2.add(rdbtnSumOff);
		
		JLabel lblSummer = new JLabel("<html><center>Summer</center><html>");
		lblSummer.setFont(new Font("Rockwell", Font.BOLD, 14));
		lblSummer.setHorizontalAlignment(SwingConstants.CENTER);
		lblSummer.setBounds(6, 11, 380, 14);
		panel_2.add(lblSummer);
		
		rdbtnfieldResourcesNeeded = new JRadioButton("<html><center>Field Resources Needed </center></html>");
		buttonGroup.add(rdbtnfieldResourcesNeeded);
		rdbtnfieldResourcesNeeded.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DistList = 5;
				PopulateDistList();
			}
		});
		rdbtnfieldResourcesNeeded.setHorizontalAlignment(SwingConstants.CENTER);
		rdbtnfieldResourcesNeeded.setFont(new Font("Rockwell", Font.PLAIN, 11));
		rdbtnfieldResourcesNeeded.setBounds(189, 41, 171, 23);
		panel_2.add(rdbtnfieldResourcesNeeded);
		
		EmailList = new JList<c_EmailRecipients>();
		EmailList.setFont(new Font("Rockwell", Font.PLAIN, 13));
		EmailList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		sp_DistList.setViewportView(EmailList);
		
		JLabel lblcontactList = new JLabel("<html><center>Contact List</center><html>");
		lblcontactList.setHorizontalAlignment(SwingConstants.CENTER);
		lblcontactList.setFont(new Font("Rockwell", Font.BOLD, 14));
		lblcontactList.setBounds(10, 34, 194, 14);
		pnl_blend_day.add(lblcontactList);
		
		JLabel lbldistributionList = new JLabel("<html><center>Distribution List</center><html>");
		lbldistributionList.setHorizontalAlignment(SwingConstants.CENTER);
		lbldistributionList.setFont(new Font("Rockwell", Font.BOLD, 14));
		lbldistributionList.setBounds(359, 34, 196, 14);
		pnl_blend_day.add(lbldistributionList);
		
		JButton btnNewButton = new JButton("Back");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmEmailDistList.dispose();
			}
		});
		btnNewButton.setBounds(10, 590, 89, 23);
		frmEmailDistList.getContentPane().add(btnNewButton);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SaveToDB();
			}
		});
		btnSave.setBounds(520, 590, 89, 23);
		frmEmailDistList.getContentPane().add(btnSave);
	}
	
	
	private void PopulateEmployees()
	{			

		ContactListModel = new DefaultListModel<c_EmailRecipients>();
		EmailListModel = new DefaultListModel<c_EmailRecipients>();
		String commandText = "SELECT Name, Email FROM Sunoco_Contacts ORDER BY Name asc";
		
		ResultSet rs = c_Query.ExecuteResultSet(commandText);
		try{

			while ((rs!=null) && (rs.next()))
			{
				 c_EmailRecipients emailRecipients = new c_EmailRecipients(rs.getString(1),rs.getString(2));
				 ContactListModel.addElement(emailRecipients);
			}
		}
		catch(Exception e)
		{
			System.out.println("ERROR " + e.toString());
		}
		
		sp_Contacts.setViewportView(ContactList);		
		ContactList.setModel(ContactListModel);
		ContactList.setSelectedIndex(0);
	}
	
	public static void addRecipients()
	{
		c_EmailRecipients tmp = ContactList.getSelectedValue();		
		EmailListModel.addElement(tmp);
		ContactListModel.removeElement(tmp);
		ContactList.setModel(ContactListModel);
		EmailList.setModel(EmailListModel);
		
	}
	
	
	public static void addRecipients(int id)
	{
		c_EmailRecipients tmp = ContactList.getModel().getElementAt(id);		
		EmailListModel.addElement(tmp);
		ContactListModel.removeElement(tmp);
		ContactList.setModel(ContactListModel);
		EmailList.setModel(EmailListModel);
	}
	
	public static void RemoveRecipient()
	{
		c_EmailRecipients tmp = EmailList.getSelectedValue();
		ContactListModel.addElement(tmp);;
		EmailListModel.removeElement(tmp);
		ContactList.setModel(ContactListModel);
		EmailList.setModel(EmailListModel);		
	}	
	
	public void PopulateDistList()
	{	
		try {
			ContactListModel.removeAllElements();
			EmailListModel.removeAllElements();
		}
		catch(Exception e)
		{
			
		}
		PopulateEmployees();		
		String commandText = "SELECT a.Name, a.Email FROM Sunoco_Contacts a, Email_Distribution b WHERE a.rowid = b.NameID AND b.DistributionList = "+DistList+ " Order by Name asc";		
		ResultSet rs = c_Query.ExecuteResultSet(commandText);
		try{		
			String name = "";
			while ((rs!=null) && (rs.next()))
			{
				name = rs.getString("Name");
				for(int i=0; i < ContactList.getModel().getSize(); i++)
				{					
				    if(name.compareTo(ContactList.getModel().getElementAt(i).getName()) == 0)   
				    {
				    	addRecipients(i);
				    	i--;
				    }
				}
			}
		}
		catch(Exception e)
		{
			System.out.println("ERROR " + e.toString());
		}
		
		sp_DistList.setViewportView(EmailList);		
		EmailList.setModel(EmailListModel);
		EmailList.setSelectedIndex(0);
		
	}
	
	private void SaveToDB()
	{		
		String commandText = "DELETE FROM Email_Distribution WHERE DistributionList = "+ DistList + "";
		c_Query.ExecuteQuery(commandText);
		
		commandText = "INSERT INTO Email_Distribution(NameID,DistributionList) VALUES(";
		String valueText = "(SELECT rowid from Sunoco_Contacts WHERE Name = '" + EmailList.getModel().getElementAt(0).getName() + "')," + DistList + ")";
		for(int i=1; i < EmailList.getModel().getSize(); i++)
		{					
			valueText = valueText + ",((SELECT rowid from Sunoco_Contacts WHERE Name = '" + EmailList.getModel().getElementAt(i).getName() + "')," + DistList + ")";
			
		}
		
		commandText = commandText + valueText;	
		try
		{
			c_Query.ExecuteQuery(commandText);
			JOptionPane.showMessageDialog(frmEmailDistList, "Email List Successfully Saved To Database.");
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(frmEmailDistList, "Error! " + e.toString());
		}
		
		
	}
	
	
	
}
