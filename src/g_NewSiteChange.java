import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import java.awt.Font;
import java.util.Date;
import java.util.Properties;

import javax.swing.JTextField;
import javax.swing.JEditorPane;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import java.awt.GridBagLayout;
import javax.swing.JCheckBox;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.awt.event.ActionEvent;

public class g_NewSiteChange {

	private static JFrame frm_NewSiteChange;
	private static JTextField txt_MOC;
	private static JTextField txt_RequestedBy;
	private JDatePanelImpl datePanel; 
	private static JDatePickerImpl datePicker;
	public static int siteid;	
	public static String client;
	public static String title;
	public static c_ButaneSites site;
	private static JEditorPane txt_Change;
	private static JCheckBox chckbxInj;
	private static JCheckBox chckbxPgm;
	private static JCheckBox chckbxHmi;
	private static JCheckBox chckbxReporting;
	private static JCheckBox chckbxSafety;
	private static JCheckBox chckbxSampling;
	private static JCheckBox chckbxSupply;
	private static JCheckBox chckbxSystem;
	private static JCheckBox chckbxOther;
	public static boolean flag = false;
	/**
	 * Launch the application.
	 */
			public static void run(JFrame frame) {
				try {
					g_NewSiteChange window = new g_NewSiteChange();
					window.frm_NewSiteChange.setVisible(true);
					frm_NewSiteChange.setLocationRelativeTo(frame);
					frame.dispose();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}


	/**
	 * Create the application.
	 */
	public g_NewSiteChange() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frm_NewSiteChange = new JFrame();
		frm_NewSiteChange.setResizable(false);
		frm_NewSiteChange.setBounds(100, 100, 776, 593);
		frm_NewSiteChange.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frm_NewSiteChange.getContentPane().setLayout(null);
		frm_NewSiteChange.setTitle("Automated Support Program v." + g_MainMenu.version);
		
		
		JPanel panel = new JPanel();
		panel.setBounds(20, 11, 730, 49);
		frm_NewSiteChange.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblClientSite = new JLabel(site.getName());
		lblClientSite.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblClientSite.setHorizontalAlignment(SwingConstants.CENTER);
		lblClientSite.setBounds(10, 11, 710, 27);
		panel.add(lblClientSite);
		
		UtilDateModel model = new UtilDateModel();
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		datePanel = new JDatePanelImpl(model,p);
		datePicker = new JDatePickerImpl(datePanel, new c_DateLabelFormatter());
		datePicker.getJFormattedTextField().setBackground(Color.WHITE);
		datePicker.setBounds(539, 84, 165, 28);
		frm_NewSiteChange.getContentPane().add(datePicker);
		
		txt_MOC = new JTextField();
		txt_MOC.setBounds(95, 85, 149, 27);
		frm_NewSiteChange.getContentPane().add(txt_MOC);
		txt_MOC.setColumns(10);
		
		JLabel lbl_MOC = new JLabel("MOC #:");
		lbl_MOC.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbl_MOC.setBounds(95, 71, 149, 14);
		frm_NewSiteChange.getContentPane().add(lbl_MOC);
		
		JLabel lblRequestedBy = new JLabel("Requested By:");
		lblRequestedBy.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblRequestedBy.setBounds(314, 71, 149, 14);
		frm_NewSiteChange.getContentPane().add(lblRequestedBy);
		
		txt_RequestedBy = new JTextField();
		txt_RequestedBy.setColumns(10);
		txt_RequestedBy.setBounds(314, 85, 149, 28);
		frm_NewSiteChange.getContentPane().add(txt_RequestedBy);
		
		JLabel lblChange = new JLabel("Change:");
		lblChange.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblChange.setBounds(20, 125, 460, 14);
		frm_NewSiteChange.getContentPane().add(lblChange);
		
		JButton btnNewButton = new JButton("Submit");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				InsertChange();
			}
		});
		btnNewButton.setBounds(635, 510, 89, 23);
		frm_NewSiteChange.getContentPane().add(btnNewButton);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				g_SiteChanges.run(frm_NewSiteChange);
			}
		});
		btnBack.setBounds(20, 510, 89, 23);
		frm_NewSiteChange.getContentPane().add(btnBack);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 148, 563, 351);
		frm_NewSiteChange.getContentPane().add(scrollPane);
		
		txt_Change = new JEditorPane();
		txt_Change.setFont(new Font("Tahoma", Font.PLAIN, 14));
		scrollPane.setViewportView(txt_Change);
		
		JLabel lblDate = new JLabel("Date:");
		lblDate.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblDate.setBounds(539, 71, 149, 14);
		frm_NewSiteChange.getContentPane().add(lblDate);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(596, 148, 154, 351);
		frm_NewSiteChange.getContentPane().add(panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{0, 0};
		gbl_panel_1.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel_1.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		chckbxInj = new JCheckBox("Injection");
		GridBagConstraints gbc_chckbxInj = new GridBagConstraints();
		gbc_chckbxInj.anchor = GridBagConstraints.WEST;
		gbc_chckbxInj.insets = new Insets(0, 0, 5, 0);
		gbc_chckbxInj.gridx = 0;
		gbc_chckbxInj.gridy = 0;
		panel_1.add(chckbxInj, gbc_chckbxInj);
		
		chckbxPgm = new JCheckBox("PGM");
		GridBagConstraints gbc_chckbxPgm = new GridBagConstraints();
		gbc_chckbxPgm.anchor = GridBagConstraints.WEST;
		gbc_chckbxPgm.insets = new Insets(0, 0, 5, 0);
		gbc_chckbxPgm.gridx = 0;
		gbc_chckbxPgm.gridy = 1;
		panel_1.add(chckbxPgm, gbc_chckbxPgm);
		
		chckbxHmi = new JCheckBox("HMI");
		GridBagConstraints gbc_chckbxHmi = new GridBagConstraints();
		gbc_chckbxHmi.anchor = GridBagConstraints.WEST;
		gbc_chckbxHmi.insets = new Insets(0, 0, 5, 0);
		gbc_chckbxHmi.gridx = 0;
		gbc_chckbxHmi.gridy = 2;
		panel_1.add(chckbxHmi, gbc_chckbxHmi);
		
		chckbxReporting = new JCheckBox("Reporting");
		GridBagConstraints gbc_chckbxReporting = new GridBagConstraints();
		gbc_chckbxReporting.insets = new Insets(0, 0, 5, 0);
		gbc_chckbxReporting.gridx = 0;
		gbc_chckbxReporting.gridy = 3;
		panel_1.add(chckbxReporting, gbc_chckbxReporting);
		
		chckbxSafety = new JCheckBox("Safety");
		GridBagConstraints gbc_chckbxSafety = new GridBagConstraints();
		gbc_chckbxSafety.anchor = GridBagConstraints.WEST;
		gbc_chckbxSafety.insets = new Insets(0, 0, 5, 0);
		gbc_chckbxSafety.gridx = 0;
		gbc_chckbxSafety.gridy = 4;
		panel_1.add(chckbxSafety, gbc_chckbxSafety);
		
		chckbxSampling = new JCheckBox("Sampling");
		GridBagConstraints gbc_chckbxSampling = new GridBagConstraints();
		gbc_chckbxSampling.anchor = GridBagConstraints.WEST;
		gbc_chckbxSampling.insets = new Insets(0, 0, 5, 0);
		gbc_chckbxSampling.gridx = 0;
		gbc_chckbxSampling.gridy = 5;
		panel_1.add(chckbxSampling, gbc_chckbxSampling);
		
		chckbxSupply = new JCheckBox("Supply");
		GridBagConstraints gbc_chckbxSupply = new GridBagConstraints();
		gbc_chckbxSupply.anchor = GridBagConstraints.WEST;
		gbc_chckbxSupply.insets = new Insets(0, 0, 5, 0);
		gbc_chckbxSupply.gridx = 0;
		gbc_chckbxSupply.gridy = 6;
		panel_1.add(chckbxSupply, gbc_chckbxSupply);
		
		chckbxSystem = new JCheckBox("System");
		GridBagConstraints gbc_chckbxSystem = new GridBagConstraints();
		gbc_chckbxSystem.anchor = GridBagConstraints.WEST;
		gbc_chckbxSystem.insets = new Insets(0, 0, 5, 0);
		gbc_chckbxSystem.gridx = 0;
		gbc_chckbxSystem.gridy = 7;
		panel_1.add(chckbxSystem, gbc_chckbxSystem);
		
		chckbxOther = new JCheckBox("Other");
		GridBagConstraints gbc_chckbxOther = new GridBagConstraints();
		gbc_chckbxOther.anchor = GridBagConstraints.WEST;
		gbc_chckbxOther.gridx = 0;
		gbc_chckbxOther.gridy = 8;
		panel_1.add(chckbxOther, gbc_chckbxOther);
		
		JLabel lblAffectedAreas = new JLabel("Affected Components:");
		lblAffectedAreas.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblAffectedAreas.setBounds(596, 125, 149, 14);
		frm_NewSiteChange.getContentPane().add(lblAffectedAreas);
		
		datePicker.getModel().setSelected(true);
		
		//Menubar
		JMenuBar menuBar = new JMenuBar();
		frm_NewSiteChange.setJMenuBar(menuBar);
		
		
				
		JMenu mnSupport = new JMenu("Support");
		menuBar.add(mnSupport);
		
		JMenuItem mntmNewTicket = new JMenuItem("New Ticket");
		mntmNewTicket.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				g_MainMenu.checkVersion();
				if(c_CheckOpenTickets.CheckTickets())
				{							
					g_TicketEntry.run(frm_NewSiteChange);
				}
				else
				{
					JOptionPane.showMessageDialog(frm_NewSiteChange, "Open Ticket Limit Exceeded. \n Please Close Old Tickets.");
				}
				
			}
		});
		mnSupport.add(mntmNewTicket);
		
		JMenuItem mntmCurrentTickets = new JMenuItem("Current Tickets");
		mntmCurrentTickets.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				g_CurrentTickets.run(frm_NewSiteChange);
			}
		});
		mnSupport.add(mntmCurrentTickets);
		
		JMenuItem mntmArchive = new JMenuItem("Archive");
		mntmArchive.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				g_ArchiveTickets.run(frm_NewSiteChange);
			}
		});
		mnSupport.add(mntmArchive);
		
		JMenu mnSites = new JMenu("Sites");
		menuBar.add(mnSites);
		
		JMenuItem mntmButaneSites = new JMenuItem("Butane Sites");
		mntmButaneSites.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				g_ViewSites.run(frm_NewSiteChange);
			}
		});
		mnSites.add(mntmButaneSites);
		
		JMenuItem mntmSiteChanges = new JMenuItem("Site Changes");
		mntmSiteChanges.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				g_SiteChanges.run(frm_NewSiteChange);
			}
		});
		mnSites.add(mntmSiteChanges);
		
		JMenu mnContacts = new JMenu("Contacts");
		menuBar.add(mnContacts);
		
		JMenuItem mntmENEmployees = new JMenuItem("EN Employees");
		mntmENEmployees.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				g_ViewEmployees.run(frm_NewSiteChange);
			}
		});
		mnContacts.add(mntmENEmployees);
		
		JMenuItem mntmEtpContacts = new JMenuItem("ETP Contacts");
		mntmEtpContacts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				g_ViewSunoco.run(frm_NewSiteChange);
			}
		});
		mnContacts.add(mntmEtpContacts);
		
		JMenu mnTools = new JMenu("Tools");
		menuBar.add(mnTools);
		
		JMenuItem mntmCreateChecklist = new JMenuItem("Create Checklist");
		mntmCreateChecklist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				g_Tools_CreateChecklist.run(frm_NewSiteChange);
			}
		});		
		mnTools.add(mntmCreateChecklist);

	}
	
	public static void InsertChange()
	{
		String dateEntered = "";
		DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm");		 		 
		Date date = new Date();
		dateEntered = dateFormat.format(date);
						
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");
		String strDate = datePicker.getJFormattedTextField().getText() + " " + LocalTime.now().format(dtf);

		String commandText = "INSERT INTO Sitechanges(SiteID,MOC,Change,ChangedBy,RequestedBy,DateEntered,DateChanged,Injection,HMI,PGM,Reporting,Safety,Sampling,Supply,System,Other) "
				+ "VALUES('" + site.getSiteID() + "','" + txt_MOC.getText() + "','" + txt_Change.getText() + "','" + g_MainMenu.CurrentUser + "','" + txt_RequestedBy.getText() + "','"
				+ dateEntered + "','" + strDate + "','" + chckbxInj.isSelected() + "','" + chckbxPgm.isSelected() + "','" + chckbxHmi.isSelected() + "','" + chckbxReporting.isSelected()
				+ "','" + chckbxSafety.isSelected() + "','" + chckbxSampling.isSelected() + "','" + chckbxSupply.isSelected() + "','" + chckbxSystem.isSelected() + "','" + chckbxOther.isSelected() +"')";
		
		
		try{
			c_Query.ExecuteQuery(commandText);
			JOptionPane.showMessageDialog(frm_NewSiteChange, "Site change entered successfully.");
			flag = true;
			g_SiteChanges.run(frm_NewSiteChange);
			
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(frm_NewSiteChange, "Error inserting change." + e.toString());			
			return;
			
		}
		
		
		 
	}
}
