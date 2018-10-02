import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.DefaultListModel;
import javax.swing.border.EtchedBorder;

import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Toolkit;

import javax.swing.SwingConstants;
import javax.swing.JCheckBox;
import javax.swing.JEditorPane;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

public class g_SiteChanges {

	public static JFrame frmSiteChanges;
	private static JComboBox<c_ButaneSites> cb_Sites;
	private JPanel panel_1;
	private JTextField txt_MOC;
	private JTextField txt_RequestedBy;
	private JTextField txt_ChangedBy;
	private JEditorPane txt_Change;
	private static int selectedIndex;
	ArrayList <c_SiteChanges> result = new ArrayList<c_SiteChanges>();
	private static JList<String> dateList;
	private static DefaultListModel<String> listModel = new DefaultListModel<String>();
	private JScrollPane scrollPane;
	private JButton btn_Edit;
	private JButton btn_Accept;
	private JButton btn_Cancel;
	private JCheckBox chkbx_Injection;
	private JCheckBox chkbx_Reporting;
	private JCheckBox chkbx_Supply;
	private JCheckBox chkbx_PGM;
	private JCheckBox chkbx_Safety;
	private JCheckBox chkbx_System;
	private JCheckBox chkbx_HMI;
	private JCheckBox chkbx_Sampling;
	private JCheckBox chkbx_Other;
	private int rowID;
	private JButton button;
	private static boolean currentTicketsFlag = false;


	/**
	 * Launch the application.
	 */

			public static void run(JFrame frame) {
				try {
					new g_SiteChanges();
					g_SiteChanges.frmSiteChanges.setVisible(true);
					frmSiteChanges.setLocationRelativeTo(frame);
					frame.dispose();
					currentTicketsFlag = false;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			
			public static void run(String siteName) {
				try {
					new g_SiteChanges();
					g_SiteChanges.frmSiteChanges.setVisible(true);
					frmSiteChanges.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					frmSiteChanges.setLocationRelativeTo( g_MainMenu.frmMainMenu );
					frmSiteChanges.setIconImage(Toolkit.getDefaultToolkit().getImage(g_ViewSites.class.getResource("/icon.png")));
					setSelectedValue(cb_Sites, siteName);
					currentTicketsFlag = true;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			

	/**
	 * Create the application.
	 */
	public g_SiteChanges() {
		initialize();
				
			PopulateSiteList();
			PopulateInformation();
			if(g_NewSiteChange.flag)
			{
				SelectSite();
			}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmSiteChanges = new JFrame();
		frmSiteChanges.setResizable(false);
		frmSiteChanges.setBounds(100, 100, 858, 678);
		frmSiteChanges.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSiteChanges.setIconImage(Toolkit.getDefaultToolkit().getImage(g_ViewSites.class.getResource("/icon.png")));
		frmSiteChanges.getContentPane().setLayout(null);
		frmSiteChanges.setTitle("Automated Support Program v." + g_MainMenu.version);
		
		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setBounds(10, 5, 826, 49);
		frmSiteChanges.getContentPane().add(panel);
		panel.setLayout(null);
		
		cb_Sites = new JComboBox<c_ButaneSites>();
		cb_Sites.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PopulateInformation();
			}
		});
		cb_Sites.setBounds(277, 11, 306, 31);
		panel.add(cb_Sites);
		
		panel_1 = new JPanel();
		panel_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_1.setBounds(10, 60, 181, 493);
		frmSiteChanges.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 28, 161, 454);
		panel_1.add(scrollPane);
		
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_2.setBounds(216, 213, 620, 340);
		frmSiteChanges.getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		txt_Change = new JEditorPane();
		txt_Change.setEditable(false);
		txt_Change.setContentType("text");
		txt_Change.setBounds(10, 5, 600, 324);
		panel_2.add(txt_Change);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_3.setBounds(216, 60, 620, 142);
		frmSiteChanges.getContentPane().add(panel_3);
		panel_3.setLayout(null);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_4.setBounds(10, 33, 291, 98);
		panel_3.add(panel_4);
		GridBagLayout gbl_panel_4 = new GridBagLayout();
		gbl_panel_4.columnWidths = new int[]{71, 162, 0};
		gbl_panel_4.rowHeights = new int[]{20, 20, 20, 0};
		gbl_panel_4.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_4.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_4.setLayout(gbl_panel_4);
		
		JLabel lblNewLabel = new JLabel("MOC #");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		panel_4.add(lblNewLabel, gbc_lblNewLabel);
		
		txt_MOC = new JTextField();
		txt_MOC.setEditable(false);
		GridBagConstraints gbc_txt_MOC = new GridBagConstraints();
		gbc_txt_MOC.anchor = GridBagConstraints.NORTHWEST;
		gbc_txt_MOC.insets = new Insets(0, 0, 5, 0);
		gbc_txt_MOC.gridx = 1;
		gbc_txt_MOC.gridy = 0;
		panel_4.add(txt_MOC, gbc_txt_MOC);
		txt_MOC.setColumns(10);
		
		JLabel lblRequestedBy = new JLabel("Requested By:");
		lblRequestedBy.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblRequestedBy = new GridBagConstraints();
		gbc_lblRequestedBy.anchor = GridBagConstraints.WEST;
		gbc_lblRequestedBy.insets = new Insets(0, 0, 5, 5);
		gbc_lblRequestedBy.gridx = 0;
		gbc_lblRequestedBy.gridy = 1;
		panel_4.add(lblRequestedBy, gbc_lblRequestedBy);
		
		txt_RequestedBy = new JTextField();
		txt_RequestedBy.setEditable(false);
		GridBagConstraints gbc_txt_RequestedBy = new GridBagConstraints();
		gbc_txt_RequestedBy.anchor = GridBagConstraints.NORTH;
		gbc_txt_RequestedBy.fill = GridBagConstraints.HORIZONTAL;
		gbc_txt_RequestedBy.insets = new Insets(0, 0, 5, 0);
		gbc_txt_RequestedBy.gridx = 1;
		gbc_txt_RequestedBy.gridy = 1;
		panel_4.add(txt_RequestedBy, gbc_txt_RequestedBy);
		txt_RequestedBy.setColumns(10);
		
		JLabel lblChangedBy = new JLabel("Changed By:");
		lblChangedBy.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblChangedBy = new GridBagConstraints();
		gbc_lblChangedBy.anchor = GridBagConstraints.WEST;
		gbc_lblChangedBy.insets = new Insets(0, 0, 0, 5);
		gbc_lblChangedBy.gridx = 0;
		gbc_lblChangedBy.gridy = 2;
		panel_4.add(lblChangedBy, gbc_lblChangedBy);
		
		txt_ChangedBy = new JTextField();
		txt_ChangedBy.setEditable(false);
		GridBagConstraints gbc_txt_ChangedBy = new GridBagConstraints();
		gbc_txt_ChangedBy.anchor = GridBagConstraints.NORTH;
		gbc_txt_ChangedBy.fill = GridBagConstraints.HORIZONTAL;
		gbc_txt_ChangedBy.gridx = 1;
		gbc_txt_ChangedBy.gridy = 2;
		panel_4.add(txt_ChangedBy, gbc_txt_ChangedBy);
		txt_ChangedBy.setColumns(10);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_5.setBounds(311, 33, 271, 98);
		panel_3.add(panel_5);
		GridBagLayout gbl_panel_5 = new GridBagLayout();
		gbl_panel_5.columnWidths = new int[]{87, 98, 76, 0};
		gbl_panel_5.rowHeights = new int[]{23, 23, 23, 0};
		gbl_panel_5.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_5.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_5.setLayout(gbl_panel_5);
		
		chkbx_Injection = new JCheckBox("Injection");
		chkbx_Injection.setBackground(Color.WHITE);
		GridBagConstraints gbc_chkbx_Injection = new GridBagConstraints();
		gbc_chkbx_Injection.anchor = GridBagConstraints.NORTHWEST;
		gbc_chkbx_Injection.insets = new Insets(0, 0, 5, 5);
		gbc_chkbx_Injection.gridx = 0;
		gbc_chkbx_Injection.gridy = 0;
		panel_5.add(chkbx_Injection, gbc_chkbx_Injection);
		
		chkbx_Reporting = new JCheckBox("Reporting");
		GridBagConstraints gbc_chkbx_Reporting = new GridBagConstraints();
		gbc_chkbx_Reporting.anchor = GridBagConstraints.NORTHWEST;
		gbc_chkbx_Reporting.insets = new Insets(0, 0, 5, 5);
		gbc_chkbx_Reporting.gridx = 1;
		gbc_chkbx_Reporting.gridy = 0;
		panel_5.add(chkbx_Reporting, gbc_chkbx_Reporting);
		
		chkbx_Supply = new JCheckBox("Supply");
		GridBagConstraints gbc_chkbx_Supply = new GridBagConstraints();
		gbc_chkbx_Supply.anchor = GridBagConstraints.NORTH;
		gbc_chkbx_Supply.fill = GridBagConstraints.HORIZONTAL;
		gbc_chkbx_Supply.insets = new Insets(0, 0, 5, 0);
		gbc_chkbx_Supply.gridx = 2;
		gbc_chkbx_Supply.gridy = 0;
		panel_5.add(chkbx_Supply, gbc_chkbx_Supply);
		
		chkbx_PGM = new JCheckBox("PGM");
		GridBagConstraints gbc_chkbx_PGM = new GridBagConstraints();
		gbc_chkbx_PGM.anchor = GridBagConstraints.NORTH;
		gbc_chkbx_PGM.fill = GridBagConstraints.HORIZONTAL;
		gbc_chkbx_PGM.insets = new Insets(0, 0, 5, 5);
		gbc_chkbx_PGM.gridx = 0;
		gbc_chkbx_PGM.gridy = 1;
		panel_5.add(chkbx_PGM, gbc_chkbx_PGM);
		
		chkbx_Safety = new JCheckBox("Safety");
		GridBagConstraints gbc_chkbx_Safety = new GridBagConstraints();
		gbc_chkbx_Safety.anchor = GridBagConstraints.NORTH;
		gbc_chkbx_Safety.fill = GridBagConstraints.HORIZONTAL;
		gbc_chkbx_Safety.insets = new Insets(0, 0, 5, 5);
		gbc_chkbx_Safety.gridx = 1;
		gbc_chkbx_Safety.gridy = 1;
		panel_5.add(chkbx_Safety, gbc_chkbx_Safety);
		
		chkbx_System = new JCheckBox("System");
		GridBagConstraints gbc_chkbx_System = new GridBagConstraints();
		gbc_chkbx_System.anchor = GridBagConstraints.NORTHWEST;
		gbc_chkbx_System.insets = new Insets(0, 0, 5, 0);
		gbc_chkbx_System.gridx = 2;
		gbc_chkbx_System.gridy = 1;
		panel_5.add(chkbx_System, gbc_chkbx_System);
		
		chkbx_HMI = new JCheckBox("HMI");
		GridBagConstraints gbc_chkbx_HMI = new GridBagConstraints();
		gbc_chkbx_HMI.anchor = GridBagConstraints.NORTH;
		gbc_chkbx_HMI.fill = GridBagConstraints.HORIZONTAL;
		gbc_chkbx_HMI.insets = new Insets(0, 0, 0, 5);
		gbc_chkbx_HMI.gridx = 0;
		gbc_chkbx_HMI.gridy = 2;
		panel_5.add(chkbx_HMI, gbc_chkbx_HMI);
		
		chkbx_Sampling = new JCheckBox("Sampling");
		GridBagConstraints gbc_chkbx_Sampling = new GridBagConstraints();
		gbc_chkbx_Sampling.anchor = GridBagConstraints.NORTH;
		gbc_chkbx_Sampling.fill = GridBagConstraints.HORIZONTAL;
		gbc_chkbx_Sampling.insets = new Insets(0, 0, 0, 5);
		gbc_chkbx_Sampling.gridx = 1;
		gbc_chkbx_Sampling.gridy = 2;
		panel_5.add(chkbx_Sampling, gbc_chkbx_Sampling);
		
		chkbx_Other = new JCheckBox("Other");
		GridBagConstraints gbc_chkbx_Other = new GridBagConstraints();
		gbc_chkbx_Other.anchor = GridBagConstraints.NORTH;
		gbc_chkbx_Other.fill = GridBagConstraints.HORIZONTAL;
		gbc_chkbx_Other.gridx = 2;
		gbc_chkbx_Other.gridy = 2;
		panel_5.add(chkbx_Other, gbc_chkbx_Other);
		
		JLabel lbl_Change = new JLabel("Information:");
		lbl_Change.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_Change.setBounds(10, 11, 291, 14);
		panel_3.add(lbl_Change);
		
		JLabel lblAffectedComponents = new JLabel("Affected Components:");
		lblAffectedComponents.setHorizontalAlignment(SwingConstants.CENTER);
		lblAffectedComponents.setBounds(311, 11, 271, 14);
		panel_3.add(lblAffectedComponents);
		
		JButton btn_AddChange = new JButton("");
		btn_AddChange.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {				
				//c_ButaneSites tmp = 	
				g_NewSiteChange.site = (c_ButaneSites) cb_Sites.getSelectedItem();		
				selectedIndex = cb_Sites.getSelectedIndex();
				g_NewSiteChange.run(frmSiteChanges);				
			}
		});
		btn_AddChange.setIcon(new ImageIcon(g_SiteChanges.class.getResource("/document.png")));
		btn_AddChange.setSelectedIcon(new ImageIcon(g_SiteChanges.class.getResource("/document.png")));
		btn_AddChange.setToolTipText("Add Change");
		btn_AddChange.setBackground(Color.LIGHT_GRAY);
		btn_AddChange.setBounds(141, 564, 50, 42);
		frmSiteChanges.getContentPane().add(btn_AddChange);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PopulateInformation();
			}
		});
		btnNewButton.setBounds(873, 552, 89, 23);
		frmSiteChanges.getContentPane().add(btnNewButton);
		
		dateList = new JList<String>();
		dateList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {	
				if(listModel.size()>0 && !dateList.isSelectionEmpty())
				{
					try{	
						
							if(!arg0.getValueIsAdjusting())
							{
						
								txt_Change.setText(result.get(dateList.getSelectedIndex()).getChange());
								txt_MOC.setText(result.get(dateList.getSelectedIndex()).getMOCNum());
								txt_RequestedBy.setText(result.get(dateList.getSelectedIndex()).getRequestedBy());
								txt_ChangedBy.setText(result.get(dateList.getSelectedIndex()).getChangedBy());
								chkbx_Injection.setSelected(result.get(dateList.getSelectedIndex()).getinj());		
								chkbx_Reporting.setSelected(result.get(dateList.getSelectedIndex()).getreporting());
								chkbx_Supply.setSelected(result.get(dateList.getSelectedIndex()).getsupply());
								chkbx_PGM.setSelected(result.get(dateList.getSelectedIndex()).getpgm());
								chkbx_Safety.setSelected(result.get(dateList.getSelectedIndex()).getsafety());
								chkbx_System.setSelected(result.get(dateList.getSelectedIndex()).getsystem());
								chkbx_HMI.setSelected(result.get(dateList.getSelectedIndex()).gethmi());
								chkbx_Sampling.setSelected(result.get(dateList.getSelectedIndex()).getsampling());
								chkbx_Other.setSelected(result.get(dateList.getSelectedIndex()).getother());		
								rowID = result.get(dateList.getSelectedIndex()).getRowID();
							}
					}
					
				catch(Exception e)
				{
					System.out.println("Error Value Change " + e.toString());
				}
			}
				else
				{
					ClearSelection();
				}
			
			}
	});

		scrollPane.setViewportView(dateList);
		
		JLabel lbl_Date = new JLabel("Date Changed:");
		lbl_Date.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_Date.setBounds(10, 11, 161, 14);
		panel_1.add(lbl_Date);
		
		btn_Edit = new JButton("");
		btn_Edit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Edit();
				btn_Edit.setEnabled(false);
				btn_Edit.setVisible(false);
				btn_Cancel.setVisible(true);
				btn_Cancel.setEnabled(true);
				btn_Accept.setVisible(true);
				btn_Accept.setEnabled(true);
			}
			
		});
		btn_Edit.setIcon(new ImageIcon(g_SiteChanges.class.getResource("/edit.png")));
		btn_Edit.setToolTipText("Edit");
		btn_Edit.setBounds(800, 564, 32, 32);
		frmSiteChanges.getContentPane().add(btn_Edit);
		
		btn_Accept = new JButton("");
		btn_Accept.setVisible(false);
		btn_Accept.setEnabled(false);
		btn_Accept.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btn_Edit.setEnabled(true);
				btn_Edit.setVisible(true);
				btn_Cancel.setVisible(false);
				btn_Cancel.setEnabled(false);
				btn_Accept.setVisible(false);
				btn_Accept.setEnabled(false);
				AcceptChanges();
				
			}
		});
		btn_Accept.setIcon(new ImageIcon(g_SiteChanges.class.getResource("/ok-icon.png")));
		btn_Accept.setToolTipText("Edit");
		btn_Accept.setBounds(762, 564, 32, 32);
		frmSiteChanges.getContentPane().add(btn_Accept);
		
		btn_Cancel = new JButton("");
		btn_Cancel.setVisible(false);
		btn_Cancel.setEnabled(false);
		btn_Cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Cancel();
				btn_Edit.setEnabled(true);
				btn_Edit.setVisible(true);
				btn_Cancel.setVisible(false);
				btn_Cancel.setEnabled(false);
				btn_Accept.setVisible(false);
				btn_Accept.setEnabled(false);
				
			}
		});
		btn_Cancel.setIcon(new ImageIcon(g_SiteChanges.class.getResource("/Actions-window-close-icon.png")));
		btn_Cancel.setToolTipText("Edit");
		btn_Cancel.setBounds(800, 564, 32, 32);
		frmSiteChanges.getContentPane().add(btn_Cancel);
		
		button = new JButton("Back");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!currentTicketsFlag)
				{
					g_MainMenu.run(frmSiteChanges);
					frmSiteChanges.dispose();
				}
				else
				{
					frmSiteChanges.dispose();
				}
			}
		});
		button.setBounds(10, 583, 89, 23);
		frmSiteChanges.getContentPane().add(button);
		
		//Menubar
		JMenuBar menuBar = new JMenuBar();
		frmSiteChanges.setJMenuBar(menuBar);
		
		
				
		JMenu mnSupport = new JMenu("Support");
		menuBar.add(mnSupport);
		
		JMenuItem mntmNewTicket = new JMenuItem("New Ticket");
		mntmNewTicket.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				g_MainMenu.checkVersion();
				if(c_CheckOpenTickets.CheckTickets())
				{							
					g_TicketEntry.run(frmSiteChanges);
				}
				else
				{
					JOptionPane.showMessageDialog(frmSiteChanges, "Open Ticket Limit Exceeded. \n Please Close Old Tickets.");
				}
				
			}
		});
		mnSupport.add(mntmNewTicket);
		
		JMenuItem mntmCurrentTickets = new JMenuItem("Current Tickets");
		mntmCurrentTickets.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				g_CurrentTickets.run(frmSiteChanges);
			}
		});
		mnSupport.add(mntmCurrentTickets);
		
		JMenuItem mntmArchive = new JMenuItem("Archive");
		mntmArchive.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				g_ArchiveTickets.run(frmSiteChanges);
			}
		});
		mnSupport.add(mntmArchive);
		
		JMenu mnSites = new JMenu("Sites");
		menuBar.add(mnSites);
		
		JMenuItem mntmButaneSites = new JMenuItem("Butane Sites");
		mntmButaneSites.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				g_ViewSites.run(frmSiteChanges);
			}
		});
		mnSites.add(mntmButaneSites);
		
		JMenuItem mntmSiteChanges = new JMenuItem("Site Changes");		
		mnSites.add(mntmSiteChanges);
		mntmSiteChanges.setEnabled(false);
		
		JMenu mnContacts = new JMenu("Contacts");
		menuBar.add(mnContacts);
		
		JMenuItem mntmENEmployees = new JMenuItem("EN Employees");
		mntmENEmployees.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				g_ViewEmployees.run(frmSiteChanges);
			}
		});
		mnContacts.add(mntmENEmployees);
		
		JMenuItem mntmEtpContacts = new JMenuItem("ETP Contacts");
		mntmEtpContacts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				g_ViewSunoco.run(frmSiteChanges);
			}
		});
		mnContacts.add(mntmEtpContacts);
		
		JMenu mnTools = new JMenu("Tools");
		menuBar.add(mnTools);
		
		JMenuItem mntmCreateChecklist = new JMenuItem("Create Checklist");
		mntmCreateChecklist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				g_Tools_CreateChecklist.run(frmSiteChanges);
			}
		});		
		mnTools.add(mntmCreateChecklist);

		
	}
	
	private void ClearSelection()
	{
		txt_Change.setText("");
		txt_MOC.setText("");
		txt_RequestedBy.setText("");
		txt_ChangedBy.setText("");
		chkbx_Injection.setSelected(false);		
		chkbx_Reporting.setSelected(false);
		chkbx_Supply.setSelected(false);
		chkbx_PGM.setSelected(false);
		chkbx_Safety.setSelected(false);
		chkbx_System.setSelected(false);
		chkbx_HMI.setSelected(false);
		chkbx_Sampling.setSelected(false);
		chkbx_Other.setSelected(false);	
	}
	
	private void PopulateSiteList()
	{		
		String client, site;
		int siteid;
		String commandText = "SELECT Client, Site, SiteID FROM Sites ORDER BY Client, Site Asc";
		ResultSet rs = c_Query.ExecuteResultSet(commandText);
		try{

			while ((rs!=null) && (rs.next()))
			{
				client = rs.getString("Client");
				site = rs.getString("Site");
				siteid = rs.getInt("SiteID");
				c_ButaneSites sites = new c_ButaneSites(client,site,siteid);
			  	sites.setClient(client);
			  	sites.setSite(site);				  
			  	sites.setSiteID(siteid);				  				  
			  	cb_Sites.addItem(sites);				  				
			}					        	     
		
		}
		catch(Exception e)
		{
			
		}
	}
	
	private void SelectSite()
	{
		cb_Sites.setSelectedIndex(selectedIndex);		
	}
	
	private void Edit()
	{
		txt_Change.setEditable(true);
		txt_MOC.setEditable(true);
		txt_RequestedBy.setEditable(true);
		txt_ChangedBy.setEditable(true);		
	}
	
	private void Cancel()
	{
		txt_Change.setEditable(false);
		txt_MOC.setEditable(false);
		txt_RequestedBy.setEditable(false);
		txt_ChangedBy.setEditable(false);
		txt_Change.setText(result.get(dateList.getSelectedIndex()).getChange());
		txt_MOC.setText(result.get(dateList.getSelectedIndex()).getMOCNum());
		txt_RequestedBy.setText(result.get(dateList.getSelectedIndex()).getRequestedBy());
		txt_ChangedBy.setText(result.get(dateList.getSelectedIndex()).getChangedBy());
		chkbx_Injection.setSelected(result.get(dateList.getSelectedIndex()).getinj());		
		chkbx_Reporting.setSelected(result.get(dateList.getSelectedIndex()).getreporting());
		chkbx_Supply.setSelected(result.get(dateList.getSelectedIndex()).getsupply());
		chkbx_PGM.setSelected(result.get(dateList.getSelectedIndex()).getpgm());
		chkbx_Safety.setSelected(result.get(dateList.getSelectedIndex()).getsafety());
		chkbx_System.setSelected(result.get(dateList.getSelectedIndex()).getsystem());
		chkbx_HMI.setSelected(result.get(dateList.getSelectedIndex()).gethmi());
		chkbx_Sampling.setSelected(result.get(dateList.getSelectedIndex()).getsampling());
		chkbx_Other.setSelected(result.get(dateList.getSelectedIndex()).getother());	
		
	}
	
	private void AcceptChanges()
	{
		String commandText = "Update SiteChanges SET MOC = '" + txt_MOC.getText() + "', Change = '" + txt_Change.getText() + "', RequestedBy = '" + txt_RequestedBy.getText() +   
				 "', Injection = '" + chkbx_Injection.isSelected() + "', HMI = '" + chkbx_HMI.isSelected() + "', PGM = '" + chkbx_PGM.isSelected() + 
				 "', Reporting = '" + chkbx_Reporting.isSelected() + "', Safety = '" + chkbx_Safety.isSelected() + "', Sampling = '" + chkbx_Sampling.isSelected() + 
				 "', Supply = '" + chkbx_Supply.isSelected() + "', System = '" + chkbx_System.isSelected() + "', Other = '" + chkbx_Other.isSelected() + 
				 "' WHERE rowID = " + rowID + "";
		try
		{
			c_Query.ExecuteQuery(commandText);
		}
		catch(Exception e)
		{
			System.out.println(e.toString());
		}	
		UpdateObject();
	}
	
	private void UpdateObject()
	{
		result.get(selectedIndex).setMOCNum(txt_MOC.getText());		
		result.get(selectedIndex).setChange(txt_Change.getText());
		result.get(selectedIndex).setRequestedBy(txt_RequestedBy.getText());
		result.get(selectedIndex).sethmi(chkbx_HMI.isSelected());
		result.get(selectedIndex).setinj(chkbx_Injection.isSelected());
		result.get(selectedIndex).setpgm(chkbx_PGM.isSelected());
		result.get(selectedIndex).setreporting(chkbx_Reporting.isSelected());
		result.get(selectedIndex).setsafety(chkbx_Safety.isSelected());
		result.get(selectedIndex).setsampling(chkbx_Sampling.isSelected());
		result.get(selectedIndex).setsupply(chkbx_Supply.isSelected());
		result.get(selectedIndex).setsystem(chkbx_System.isSelected());
		result.get(selectedIndex).setother(chkbx_Other.isSelected());		
	}
	
	
	private void PopulateInformation()
	{	
		listModel = new DefaultListModel<String>();		
		result = new ArrayList<c_SiteChanges>();
		c_ButaneSites tmp = (c_ButaneSites) cb_Sites.getSelectedItem();					 
		String commandText = "SELECT rowID,SiteID,MOC,Change,ChangedBy,RequestedBy,CONVERT(varchar(17), DateChanged, 113) as DateConverted,Injection,HMI,PGM,Reporting,Safety,Sampling,Supply,System,Other FROM SiteChanges WHERE SiteID = " + tmp.getSiteID() + " ORDER BY DateChanged desc";
		ResultSet rs = c_Query.ExecuteResultSet(commandText);
		try{

			while ((rs!=null) && (rs.next()))
			{
				  c_SiteChanges siteChange = new c_SiteChanges();				  
				  siteChange.setChangeDate(rs.getString("DateConverted"));
				  siteChange.setChange(rs.getString("Change"));
				  siteChange.setChangedBy(rs.getString("ChangedBy"));
				  siteChange.sethmi(rs.getBoolean("HMI"));
				  siteChange.setinj(rs.getBoolean("Injection"));
				  siteChange.setMOCNum(rs.getString("MOC"));
				  siteChange.setother(rs.getBoolean("Other"));
				  siteChange.setpgm(rs.getBoolean("PGM"));
				  siteChange.setreporting(rs.getBoolean("Reporting"));
				  siteChange.setRequestedBy(rs.getString("RequestedBy"));
				  siteChange.setsafety(rs.getBoolean("Safety"));
				  siteChange.setsampling(rs.getBoolean("Sampling"));
				  siteChange.setsupply(rs.getBoolean("Supply"));
				  siteChange.setsystem(rs.getBoolean("System"));	
				  siteChange.setRowID(rs.getInt("rowID"));
				  result.add(siteChange);
				  listModel.addElement(siteChange.getChangeDate());	  						  				
			}					        	     
		
		}
		catch(Exception e)
		{
			
		}
		
		dateList.setModel(listModel);		
		scrollPane.setViewportView(dateList);
		
		if(listModel.getSize() > 0)
		{				
			dateList.setSelectedIndex(0);
		}
		else
		{
			dateList.clearSelection();
		}
		
	}
	
	public static void setSelectedValue(JComboBox<c_ButaneSites> comboBox, String value)
    {
	
        for (int i = 0; i < comboBox.getItemCount(); i++)
        {
           String tmp = comboBox.getItemAt(i).toString();
            if (value.compareTo(tmp) == 0)
            {
                comboBox.setSelectedIndex(i);
                break;
            }
        }
    }
}