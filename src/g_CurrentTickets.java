import it.cnr.imaa.essi.lablib.gui.checkboxtree.CheckboxTree;
import it.cnr.imaa.essi.lablib.gui.checkboxtree.TreeCheckingModel;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;
import com.inet.jortho.FileUserDictionary;
import com.inet.jortho.SpellChecker;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.event.TreeSelectionListener;
import javax.swing.event.TreeSelectionEvent;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ScrollPaneConstants;
import javax.swing.JCheckBox;
import java.awt.Toolkit;
import javax.swing.border.EtchedBorder;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.SwingConstants;
/* http://stackoverflow.com/questions/16157529/how-do-i-pass-objects-between-classes */
public class g_CurrentTickets {

	public static JFrame frmCurrentTickets;
	private static CheckboxTree tree_active;
	private static CheckboxTree tree_closed;
	private JLabel lbl_ClientSite;
	private JLabel lbl_Ticket;
	private JLabel lbl_Assigned;
	private JButton btnSave;
	private JComboBox<String> cb_Status;
	private JLabel lbl_Problem;
	private JLabel lbl_Update;
	private JLabel lbl_TicketEntered;
	private JTextArea txt_Issue;
	private JTextArea txt_Update;
	private JTextArea txt_Internal;
	private JLabel lblHours;
	private JTextField txt_TimeSpent;
	private JLabel lblDateLastUpdated;
	private JLabel lbl_UpdateDate;
	public static DefaultListModel<c_Files> fileListModel;
	public static DefaultListModel<c_Files> ExistingfileListModel;
	private List<String> ViewSiteData = new ArrayList<String>();
	private JPanel panel_Update;
	private JScrollPane scrollPane;
	private JPanel panel_Internal;
	private JLabel lblInternalUpdate;
	private JButton btn_External;
	private JButton btn_Internal;
	private int rowID;
	private String TicketNum;
	public String siteID;
	private JComboBox<String> cb_Assigned;
	private JButton btnEdit;	
	private String userDictionaryPath = "\\\\supportsql\\C$\\SupportProgram\\dictionary\\";
	//private int numFiles; 
	private int viewIP;
	private int SQLIP; 
	private int DevIP; 
	private int iDracIP;
	private int hostIP; 
	public static String client;
	public static String site;
	public static String category;
	private int siteid;
	private boolean highperformance;
	private boolean centralSQL;
	private boolean centralView;
	private static JCheckBox chckbxCcNotified;
	private static boolean CCNotifiedState = false;
	private static JRadioButton rdbtnAllTickets;
	private static JRadioButton rdbtnUpdates;
	private static JRadioButton rdbtnMyTickets;
	private static JLabel lblLastUpdatedByStr;
	
	private JComboBox<String> cb_Category;
	public static boolean SearchArchive = false;
	private JButton btnFlag;
	private JButton btnUnFlag;
	private static boolean emailSentFlag;
	private JButton btnSaveAssign;
	private JButton btnCancelAssign;

	
	public static void run(JFrame frame) {
		try {			
			@SuppressWarnings("unused")
			g_CurrentTickets window = new g_CurrentTickets();
			g_CurrentTickets.frmCurrentTickets.setVisible(true);
			frmCurrentTickets.setVisible(true);				
			frmCurrentTickets.setLocationRelativeTo(frame);		
			frame.dispose();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the application.
	 */
	public g_CurrentTickets() {
		//long startTime = System.currentTimeMillis();
		initialize();		
		PopulateActiveTickets();
		PopulateRecentTickets();
		//long stopTime = System.currentTimeMillis();
		//System.out.println("Elapsed time was " + (stopTime - startTime) + " miliseconds.");
	}
	
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmCurrentTickets = new JFrame();
		frmCurrentTickets.setResizable(false);
		frmCurrentTickets.setIconImage(Toolkit.getDefaultToolkit().getImage(g_CurrentTickets.class.getResource("/icon.png")));				
		frmCurrentTickets.setTitle(g_MainMenu.TitleOnline);	
		frmCurrentTickets.getContentPane().setBackground(Color.LIGHT_GRAY);
		frmCurrentTickets.setBounds(100, 100, 964, 916);
		frmCurrentTickets.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCurrentTickets.getContentPane().setLayout(null);
	

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		panel_1.setBounds(265, 11, 668, 790);
		frmCurrentTickets.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		lbl_ClientSite = new JLabel("Client Site (ID)");
		lbl_ClientSite.setBounds(10, 11, 493, 20);
		lbl_ClientSite.setFont(new Font("Rockwell", Font.BOLD, 16));
		panel_1.add(lbl_ClientSite);
		
		lbl_Ticket = new JLabel("(Ticket #)");
		lbl_Ticket.setBounds(544, 11, 114, 20);
		lbl_Ticket.setFont(new Font("Rockwell", Font.BOLD, 16));
		panel_1.add(lbl_Ticket);
		
		lbl_Assigned = new JLabel("");
		lbl_Assigned.setFont(new Font("Rockwell", Font.PLAIN, 16));
		lbl_Assigned.setBounds(25, 694, 231, 20);
		panel_1.add(lbl_Assigned);
		
		btnSave = new JButton("Update");
		btnSave.setFont(new Font("Rockwell", Font.PLAIN, 12));
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				if (cb_Status.getSelectedItem().toString().compareTo("Complete") == 0)
				{					
					CloseTicket();
				}
				else if (cb_Status.getSelectedItem().toString().compareTo("Closed by ETP") == 0)
				{
					ClosedByETP();
				}
				else
				{
					UpdateTicket();
				}	
				
				/*if(AssignChange)
				{
					c_ConnectToDatabase.SendUpdateEmail(cb_Assigned.getSelectedItem().toString(), client, site, TicketNum, txt_Issue.getText());
				}
				AssignChange = false;
				*/
			}
		});
		btnSave.setBounds(569, 739, 89, 23);
		panel_1.add(btnSave);
		
		cb_Status = new JComboBox<String>();
		cb_Status.setFont(new Font("Rockwell", Font.PLAIN, 12));
		cb_Status.setModel(new DefaultComboBoxModel<String>(new String[] {"Investigating", "Ongoing", "Ongoing - Need Field Support", "Ongoing - Need ETP Approval", "Closed by ETP", "Complete"}));
		cb_Status.setBounds(245, 694, 191, 28);
		panel_1.add(cb_Status);
		
		ExistingfileListModel = new DefaultListModel<c_Files>();
		
		lbl_Problem = new JLabel("Issue");
		lbl_Problem.setFont(new Font("Rockwell", Font.BOLD, 16));
		lbl_Problem.setBounds(10, 77, 220, 20);
		panel_1.add(lbl_Problem);
		
		panel_Internal = new JPanel();
		panel_Internal.setBounds(10, 322, 648, 324);
		panel_1.add(panel_Internal);
		panel_Internal.setLayout(null);
		panel_Internal.setVisible(false);
		
		lblInternalUpdate = new JLabel("Internal Update");
		lblInternalUpdate.setFont(new Font("Plantagenet Cherokee", Font.BOLD, 16));
		lblInternalUpdate.setBounds(0, 0, 220, 20);
		panel_Internal.add(lblInternalUpdate);
		
		btn_External = new JButton("External");
		btn_External.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel_Internal.setVisible(false);
				panel_Update.setVisible(true);
			}
		});
		btn_External.setBounds(559, 0, 89, 23);
		panel_Internal.add(btn_External);
		
		
		scrollPane_2 = new JScrollPane();
		scrollPane_2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane_2.setBounds(0, 27, 648, 176);
		panel_Internal.add(scrollPane_2);
		
		txt_Internal = new JTextArea();
		txt_Internal.setFont(new Font("Rockwell", Font.PLAIN, 16));
		scrollPane_2.setViewportView(txt_Internal);
		txt_Internal.setLineWrap(true);
		txt_Internal.setWrapStyleWord(true);
		
		lblLastUpdatedByStr = new JLabel("Assigned To:");
		lblLastUpdatedByStr.setFont(new Font("Plantagenet Cherokee", Font.BOLD, 16));
		lblLastUpdatedByStr.setBounds(499, 275, 134, 20);
		panel_Internal.add(lblLastUpdatedByStr);
		
		JLabel lblLastUpdatedBy = new JLabel("Last Updated By");
		lblLastUpdatedBy.setFont(new Font("Plantagenet Cherokee", Font.BOLD, 16));
		lblLastUpdatedBy.setBounds(499, 239, 134, 20);
		panel_Internal.add(lblLastUpdatedBy);
		panel_Update = new JPanel();
		panel_Update.setBounds(10, 322, 648, 324);
		panel_1.add(panel_Update);
		panel_Update.setLayout(null);
		
		lbl_Update = new JLabel("Update");
		lbl_Update.setBounds(0, 0, 220, 20);
		panel_Update.add(lbl_Update);
		lbl_Update.setFont(new Font("Rockwell", Font.BOLD, 16));
		
		lblHours = new JLabel("Time Spent");
		lblHours.setBounds(0, 259, 156, 20);
		panel_Update.add(lblHours);
		lblHours.setFont(new Font("Rockwell", Font.BOLD, 16));
		
		txt_TimeSpent = new JTextField();
		txt_TimeSpent.setFont(new Font("Rockwell", Font.PLAIN, 12));
		txt_TimeSpent.setBounds(10, 283, 58, 25);
		panel_Update.add(txt_TimeSpent);
		txt_TimeSpent.setText("0.5");
		txt_TimeSpent.setToolTipText("Time Spent in Hours");
		txt_TimeSpent.setColumns(10);
		
		lblDateLastUpdated = new JLabel("Last Updated:");
		lblDateLastUpdated.setBounds(474, 259, 164, 20);
		panel_Update.add(lblDateLastUpdated);
		lblDateLastUpdated.setFont(new Font("Rockwell", Font.BOLD, 16));
		
		lbl_UpdateDate = new JLabel("Date");
		lbl_UpdateDate.setBounds(474, 288, 164, 20);
		panel_Update.add(lbl_UpdateDate);
		lbl_UpdateDate.setFont(new Font("Rockwell", Font.BOLD, 16));
		
		scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(0, 28, 648, 216);
		panel_Update.add(scrollPane);
		
		txt_Update = new JTextArea();
		scrollPane.setViewportView(txt_Update);
		txt_Update.setLineWrap(true);
		txt_Update.setWrapStyleWord(true);
		
		
		btn_Internal = new JButton("Internal");
		btn_Internal.setFont(new Font("Rockwell", Font.PLAIN, 12));
		btn_Internal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel_Update.setVisible(false);
				panel_Internal.setVisible(true);
				
			}
		});
		btn_Internal.setBounds(559, 0, 89, 23);
		panel_Update.add(btn_Internal);
		
		JLabel lblhours = new JLabel("Hours");
		lblhours.setFont(new Font("Rockwell", Font.PLAIN, 14));
		lblhours.setBounds(75, 284, 156, 20);
		panel_Update.add(lblhours);
		
		JButton btn_SiteData = new JButton("Site Changes");
		btn_SiteData.setFont(new Font("Rockwell", Font.PLAIN, 12));
		btn_SiteData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//ViewSiteData2();
				//g_ViewSiteData2.run(siteID);
				g_SiteChanges.run(lbl_ClientSite.getText());
			}
		});
		btn_SiteData.setBounds(10, 43, 105, 23);
		panel_1.add(btn_SiteData);
		
		lblAssignedTo = new JLabel("Assigned To:");
		lblAssignedTo.setFont(new Font("Rockwell", Font.BOLD, 16));
		lblAssignedTo.setBounds(25, 665, 114, 20);
		panel_1.add(lblAssignedTo);
		
		cb_Assigned = new JComboBox<String>();
		cb_Assigned.setFont(new Font("Rockwell", Font.PLAIN, 12));
		cb_Assigned.setBounds(10, 694, 165, 26);
		cb_Assigned.setVisible(false);
		panel_1.add(cb_Assigned);
		
		btnEdit = new JButton("");
		btnEdit.setIcon(new ImageIcon(g_CurrentTickets.class.getResource("/edit.png")));
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ChangeAssignment();
			}
		});
		
		btnEditCategory = new JButton("");
		btnEditCategory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ChangeCategory();
			}
		});
		btnEditCategory.setIcon(new ImageIcon(g_CurrentTickets.class.getResource("/edit.png")));
		btnEditCategory.setBounds(603, 669, 16, 16);
		panel_1.add(btnEditCategory);
		btnEdit.setBounds(139, 669, 16, 16);
		panel_1.add(btnEdit);
		
		scrollPane_3 = new JScrollPane();
		scrollPane_3.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane_3.setBounds(10, 99, 648, 212);
		panel_1.add(scrollPane_3);
		
		txt_Issue = new JTextArea();		
		txt_Issue.setLineWrap(true);
		txt_Issue.setWrapStyleWord(true);
		scrollPane_3.setViewportView(txt_Issue);
		txt_Issue.setEditable(false);
		
		chckbxCcNotified = new JCheckBox("<html><center>CC Notified</center></html>");
		chckbxCcNotified.setFont(new Font("Rockwell", Font.PLAIN, 12));
		chckbxCcNotified.setBounds(478, 739, 79, 32);
		panel_1.add(chckbxCcNotified);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_3.setBounds(10, 497, 248, 304);
		frmCurrentTickets.getContentPane().add(panel_3);
		panel_3.setLayout(null);
		
		scrollPane_4 = new JScrollPane();
		scrollPane_4.setBounds(0, 0, 248, 304);
		panel_3.add(scrollPane_4);
		
		
		tree_closed = new CheckboxTree();
		tree_closed.setFont(new Font("Rockwell", Font.PLAIN, 12));
		scrollPane_4.setViewportView(tree_closed);
		tree_closed.getCheckingModel().setCheckingMode(TreeCheckingModel.CheckingMode.PROPAGATE_PRESERVING_CHECK);
		tree_closed.setBackground(Color.WHITE);
		tree_closed.setBorder(UIManager.getBorder("DesktopIcon.border"));
		tree_closed.addTreeSelectionListener(new TreeSelectionListener() {
			public void valueChanged(TreeSelectionEvent e) {
				PopulateRecentWindow();
			}
		});
		
		JButton btnBack = new JButton("Back");
		btnBack.setFont(new Font("Rockwell", Font.PLAIN, 12));
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				g_MainMenu.run(frmCurrentTickets);
				frmCurrentTickets.dispose();
			}
		});
		btnBack.setBounds(10, 822, 89, 23);
		frmCurrentTickets.getContentPane().add(btnBack);
		
		JButton btnRefresh = new JButton("");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PopulateActiveWindow();
				PopulateActiveTickets();
				PopulateRecentTickets();
			}
		});
		btnRefresh.setBounds(203, 803, 50, 42);
		frmCurrentTickets.getContentPane().add(btnRefresh);
		btnRefresh.setIcon(new ImageIcon(g_CurrentTickets.class.getResource("/reload.png")));
		btnRefresh.setToolTipText("Refresh");
		
		btnTicketAdd = new JButton("");
		btnTicketAdd.setBackground(Color.LIGHT_GRAY);
		btnTicketAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(c_CheckOpenTickets.CheckTickets())
				{										
					g_TicketEntry.run(frmCurrentTickets);
					//frmCurrentTickets.dispose();
				}
				else
				{
					JOptionPane.showMessageDialog(frmCurrentTickets, "Open Ticket Limit Exceeded. \n Please Close Old Tickets.");
				}
			}
		});
		btnTicketAdd.setIcon(new ImageIcon(g_CurrentTickets.class.getResource("/document.png")));
		btnTicketAdd.setToolTipText("Add Ticket");
		btnTicketAdd.setBounds(120, 803, 50, 42);
		frmCurrentTickets.getContentPane().add(btnTicketAdd);
		
		panel = new JPanel();
		panel.setBounds(10, 34, 248, 454);
		frmCurrentTickets.getContentPane().add(panel);
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setLayout(null);
		
				JScrollPane scrollPane_5 = new JScrollPane();
				scrollPane_5.setBounds(0, 0, 248, 477);
				panel.add(scrollPane_5);
				
				tree_active = new CheckboxTree();
				tree_active.setFont(new Font("Rockwell", Font.PLAIN, 12));
				scrollPane_5.setViewportView(tree_active);
				tree_active.getCheckingModel().setCheckingMode(TreeCheckingModel.CheckingMode.PROPAGATE_PRESERVING_CHECK);
				tree_active.setBackground(Color.WHITE);
				tree_active.setBorder(UIManager.getBorder("DesktopIcon.border"));
				
				JPanel panel_4 = new JPanel();
				panel_4.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				panel_4.setBackground(Color.WHITE);
				panel_4.setBounds(10, 11, 248, 23);
				frmCurrentTickets.getContentPane().add(panel_4);
				panel_4.setLayout(null);
				
				rdbtnAllTickets = new JRadioButton("All");
				rdbtnAllTickets.setFont(new Font("Rockwell", Font.PLAIN, 11));
				rdbtnAllTickets.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						PopulateActiveTickets();
					}
				});
				rdbtnAllTickets.setSelected(true);
				buttonGroup.add(rdbtnAllTickets);
				rdbtnAllTickets.setBackground(Color.WHITE);
				rdbtnAllTickets.setBounds(3, 1, 55, 18);
				panel_4.add(rdbtnAllTickets);
				
				rdbtnMyTickets = new JRadioButton("My Tickets");
				rdbtnMyTickets.setFont(new Font("Rockwell", Font.PLAIN, 11));
				rdbtnMyTickets.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						PopulateActiveTickets();
					}
				});
				buttonGroup.add(rdbtnMyTickets);
				rdbtnMyTickets.setBackground(Color.WHITE);
				rdbtnMyTickets.setBounds(55, 3, 80, 15);
				panel_4.add(rdbtnMyTickets);												
				
				rdbtnUpdates = new JRadioButton("New Updates");
				rdbtnUpdates.setFont(new Font("Rockwell", Font.PLAIN, 11));
				rdbtnUpdates.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						PopulateActiveTickets();
					}
				});
				buttonGroup.add(rdbtnUpdates);
				rdbtnUpdates.setBackground(Color.WHITE);
				rdbtnUpdates.setBounds(140, 3, 102, 15);
				panel_4.add(rdbtnUpdates);
				SpellChecker.setUserDictionaryProvider(new FileUserDictionary(userDictionaryPath));
				SpellChecker.registerDictionaries(getClass().getResource(userDictionaryPath), "en");
				SpellChecker.getOptions().setIgnoreAllCapsWords(true);
				SpellChecker.getOptions().setIgnoreCapitalization(true);
			    SpellChecker.register( txt_Internal);		        			    			   
			    SpellChecker.register( txt_Update);
			    
			    JLabel lblTicketEntered = new JLabel("Ticket Entered:");
			    lblTicketEntered.setFont(new Font("Rockwell", Font.BOLD, 16));
			    lblTicketEntered.setBounds(242, 259, 164, 20);
			    panel_Update.add(lblTicketEntered);
			    
			    lbl_TicketEntered = new JLabel("Date");
			    lbl_TicketEntered.setFont(new Font("Rockwell", Font.BOLD, 16));
			    lbl_TicketEntered.setBounds(242, 288, 164, 20);
			    panel_Update.add(lbl_TicketEntered);
			    
			    btn_AnalyzerGuide = new JButton("");
			    btn_AnalyzerGuide.addActionListener(new ActionListener() {
			    	public void actionPerformed(ActionEvent e) {
			    		try {
							Runtime.getRuntime().exec("C:\\Program Files\\Internet Explorer\\iexplore.exe " + "https://drive.google.com/open?id=1azY7gVHoZq14Qe6VpcYUyXWuGEtcuo38");
						} catch (IOException e2) {
						}
					
			    	}
			    });
			    btn_AnalyzerGuide.setIcon(new ImageIcon(g_CurrentTickets.class.getResource("/alarmguide.png")));
			    btn_AnalyzerGuide.setToolTipText("Analyzer Alarms Guide");
			    btn_AnalyzerGuide.setVisible(false);
			    btn_AnalyzerGuide.setBounds(169, 42, 24, 24);
			    panel_1.add(btn_AnalyzerGuide);
			    
			    cb_Category = new JComboBox<String>();
			    cb_Category.setFont(new Font("Rockwell", Font.PLAIN, 12));
			    cb_Category.setModel(new DefaultComboBoxModel<String>(new String[] {"", "Injection", "HMI", "PGM", "Reporting", "Safety", "Sampling", "Supply", "System", "Other"}));
			    cb_Category.setBounds(493, 694, 165, 26);
			    panel_1.add(cb_Category);
			    
			    lbl_Category = new JLabel("");
			    lbl_Category.setBounds(508, 694, 157, 20);
			    panel_1.add(lbl_Category);
			    lbl_Category.setFont(new Font("Rockwell", Font.PLAIN, 16));
			    
			    lblCategory = new JLabel("Category:");
			    lblCategory.setFont(new Font("Rockwell", Font.BOLD, 16));
			    lblCategory.setBounds(508, 665, 97, 20);
			    panel_1.add(lblCategory);
			    
			    JLabel icon_view = new JLabel("<html> View </html>");		
			    icon_view.setBounds(10, 739, 32, 32);
			    panel_1.add(icon_view);
			    icon_view.addMouseListener(new MouseAdapter() {
			    	@Override
			    	public void mouseClicked(MouseEvent arg0) {
			    		String ipAddress = "";
			    		
			    		if(!centralView)
			    		{
			    			ipAddress = "10.219." + siteID + "." + viewIP;
			    		}
			    		else
			    		{
			    			ipAddress = "192.168.32.74";
			    		}
			    		
			    		if(arg0.getButton() == MouseEvent.BUTTON1)
			    		{
			    			String width;
			    			String height;
			    			if(!highperformance)
			    			{
			    				width = "1280";
			    				height = "1024";
			    			}
			    			else
			    			{
			    				width = "1920";
			    				height = "1080";
			    			}					
			    				
			    			try {
			    					Runtime.getRuntime().exec(new String[] {"cmd.exe", "/C", "mstsc /v:"+ipAddress + " /w:" + width + " /h:" + height});
			    			} catch (IOException e) {
			    				e.printStackTrace();						
			    			}
			    		}
			    		else if(arg0.getButton() == MouseEvent.BUTTON2)
			    		{
			    			try {
			    				Runtime.getRuntime().exec(new String[] {"cmd.exe", "/C", "start \\\\"+ipAddress+"\\c$"});
			    			} catch (IOException e) {
			    				e.printStackTrace();
			    			}					
			    		}
			    		else if(arg0.getButton() == MouseEvent.BUTTON3)
			    		{
			    			try {
			    				Runtime.getRuntime().exec(new String[] {"cmd.exe", "/c", "start cmd.exe /K ping "+ipAddress+""});												
			    			} catch (IOException e) {
			    				e.printStackTrace();
			    			}					
			    		}
			    	}
			    	
			    });
			    icon_view.setToolTipText("View");
			    icon_view.setIcon(new ImageIcon(g_CurrentTickets.class.getResource("/view.png")));
			    
			    JLabel lblNewLabel_1 = new JLabel("View");
			    lblNewLabel_1.setFont(new Font("Rockwell", Font.PLAIN, 11));
			    lblNewLabel_1.setBounds(13, 770, 32, 14);
			    panel_1.add(lblNewLabel_1);
			    
			    JLabel icon_sql = new JLabel("");
			    icon_sql.setBounds(70, 739, 32, 32);
			    panel_1.add(icon_sql);
			    icon_sql.addMouseListener(new MouseAdapter() {
			    	@Override
			    	public void mouseClicked(MouseEvent e) {
			    		String width = "1920";
			    		String height = "1080";			
			    		String ipAddress = "";
			    		
			    		if(!centralSQL)
			    		{
			    			ipAddress = "10.219." + siteID + "." + SQLIP;
			    		}
			    		else
			    		{
			    			ipAddress = "192.168.32.75";
			    		}
			    		
			    		if(e.getButton() == MouseEvent.BUTTON1)
			    		{
			    				try {
			    					Runtime.getRuntime().exec(new String[] {"cmd.exe", "/C", "mstsc /v:"+ipAddress + " /w:" + width + " /h:" + height});
			    				} catch (IOException e2) {
			    					e2.printStackTrace();
			    				}
			    			
			    		}
			    		else if(e.getButton() == MouseEvent.BUTTON2)
			    		{
			    			try {
			    				Runtime.getRuntime().exec(new String[] {"cmd.exe", "/C", "start \\\\"+ipAddress+"\\c$"});
			    			} catch (IOException e1) {
			    				e1.printStackTrace();
			    			}					
			    		}
			    		else if(e.getButton() == MouseEvent.BUTTON3)
			    		{
			    			try {
			    				Runtime.getRuntime().exec(new String[] {"cmd.exe", "/c", "start cmd.exe /K ping "+ipAddress+""});												
			    			} catch (IOException e1) {
			    				e1.printStackTrace();
			    			}					
			    		}
			    	}
			    	
			    });
			    icon_sql.setIcon(new ImageIcon(g_CurrentTickets.class.getResource("/sql.png")));
			    icon_sql.setToolTipText("SQL");
			    
			    JLabel lblSql = new JLabel("SQL");
			    lblSql.setFont(new Font("Rockwell", Font.PLAIN, 11));
			    lblSql.setBounds(76, 770, 32, 14);
			    panel_1.add(lblSql);
			    
			    JLabel icon_dev = new JLabel("");
			    icon_dev.setBounds(130, 739, 32, 32);
			    panel_1.add(icon_dev);
			    icon_dev.addMouseListener(new MouseAdapter() {
			    	@Override
			    	public void mouseClicked(MouseEvent e) {
			    		String width = "1920";
			    		String height = "1080";	
			    		String ipAddress = "10.219." + siteID + "." + DevIP; 
			    		if(e.getButton() == MouseEvent.BUTTON1)
			    		{
			    			try {
			    				Runtime.getRuntime().exec(new String[] {"cmd.exe", "/C", "mstsc /v:"+ipAddress + " /w:" + width + " /h:" + height});
			    			} catch (IOException e3) {
			    				e3.printStackTrace();
			    			}
			    		}
			    		else if(e.getButton() == MouseEvent.BUTTON2)
			    		{
			    			try {
			    				Runtime.getRuntime().exec(new String[] {"cmd.exe", "/C", "start \\\\"+ipAddress+"\\c$"});
			    			} catch (IOException e1) {
			    				e1.printStackTrace();
			    			}		
			    		}
			    		else if(e.getButton() == MouseEvent.BUTTON3)
			    		{
			    			try {
			    				Runtime.getRuntime().exec(new String[] {"cmd.exe", "/c", "start cmd.exe /K ping "+ipAddress+""});												
			    			} catch (IOException e1) {
			    				e1.printStackTrace();
			    			}					
			    		}
			    	}
			    });
			    icon_dev.setIcon(new ImageIcon(g_CurrentTickets.class.getResource("/dev.png")));
			    icon_dev.setToolTipText("Dev");
			    
			    JLabel lblDev = new JLabel("Dev");
			    lblDev.setFont(new Font("Rockwell", Font.PLAIN, 11));
			    lblDev.setBounds(137, 770, 32, 14);
			    panel_1.add(lblDev);
			    
			    JLabel icon_idrac = new JLabel("");
			    icon_idrac.setBounds(190, 741, 32, 32);
			    panel_1.add(icon_idrac);
			    icon_idrac.addMouseListener(new MouseAdapter() {
			    	@Override
			    	public void mouseClicked(MouseEvent e) {
			    		String ipAddress = "10.219." + siteID + "." + iDracIP; 
			    		if(e.getButton() == MouseEvent.BUTTON1)
			    		{
			    			try {					
			    				Runtime.getRuntime().exec("C:\\Program Files\\Internet Explorer\\iexplore.exe " + ipAddress);
			    			} catch (IOException e2) {
			    			
			    			}
			    		}				
			    		else if(e.getButton() == MouseEvent.BUTTON3)
			    		{
			    			try {
			    				Runtime.getRuntime().exec(new String[] {"cmd.exe", "/c", "start cmd.exe /K ping "+ipAddress+""});												
			    			} catch (IOException e1) {
			    				e1.printStackTrace();
			    			}					
			    		}			
			    	}
			    });
			    icon_idrac.setIcon(new ImageIcon(g_CurrentTickets.class.getResource("/idrac.png")));
			    icon_idrac.setToolTipText("iDrac");
			    
			    JLabel lblIdrac = new JLabel("iDrac");
			    lblIdrac.setFont(new Font("Rockwell", Font.PLAIN, 11));
			    lblIdrac.setBounds(194, 772, 32, 14);
			    panel_1.add(lblIdrac);
			    
			    JLabel icon_host = new JLabel("");
			    icon_host.setBounds(250, 741, 32, 32);
			    panel_1.add(icon_host);
			    icon_host.addMouseListener(new MouseAdapter() {
			    	@Override
			    	public void mouseClicked(MouseEvent e) {				
			    		String ipAddress = "10.219." + siteID + "." + hostIP;
			    		if(e.getButton() == MouseEvent.BUTTON1)
			    		{
			    			try {					
			    				Runtime.getRuntime().exec("C:\\Program Files\\Internet Explorer\\iexplore.exe " + ipAddress + "\\ui");
			    			} catch (IOException e2) {
			    			}
			    		}
			    		else if (e.getButton() == MouseEvent.BUTTON3)
			    		{
			    			try {
			    				Runtime.getRuntime().exec(new String[] {"cmd.exe", "/c", "start cmd.exe /K ping "+ipAddress+""});												
			    			} catch (IOException e1) {
			    				e1.printStackTrace();
			    			}					
			    		}
			    		
			    	}
			    });
			    icon_host.setIcon(new ImageIcon(g_CurrentTickets.class.getResource("/host.png")));
			    icon_host.setToolTipText("Host");
			    
			    JLabel lblHost = new JLabel("Host");
			    lblHost.setFont(new Font("Rockwell", Font.PLAIN, 11));
			    lblHost.setBounds(255, 772, 32, 14);
			    panel_1.add(lblHost);
			    
			    			    
			    JButton button = new JButton("");
			    button.setBounds(324, 739, 32, 32);
			    panel_1.add(button);
			    button.addActionListener(new ActionListener() {
			    	public void actionPerformed(ActionEvent arg0) {				
			    		if (client.compareTo("Kinder Morgan") == 0 || client.compareTo("Phillips 66") == 0 ||
			    			client.compareTo("Motiva") == 0 || client.compareTo("Chevron") == 0 ||
			    			client.compareTo("Shell") == 0 ||
			    			client.compareTo("Sinclair") == 0 || client.compareTo("Cummins") == 0)
			    		{
			    			int reply = JOptionPane.showConfirmDialog(frmCurrentTickets, "Phone #713-989-4409 \n Call Control Center?" , "Control Center Phone #", JOptionPane.YES_NO_OPTION);
			    	        if (reply == JOptionPane.YES_OPTION)
			    	        {
			    	        	try {

			    					Runtime.getRuntime().exec(new String[] {"C:\\Program Files (x86)\\Microsoft Office\\Office16\\lync.exe", "/C", "Callto:tel:+ 17139894409"});
			    				} catch (IOException e) {
			    					e.printStackTrace();
			    				}
			    	        }
			    	        
			    		}
			    		else
			    		{
			    			int reply = JOptionPane.showConfirmDialog(frmCurrentTickets, "Phone #713-989-4408 \n Call Control Center?" , "Control Center Phone #", JOptionPane.YES_NO_OPTION);
			    	        if (reply == JOptionPane.YES_OPTION)
			    	        {
			    	        	try {

			    					Runtime.getRuntime().exec(new String[] {"C:\\Program Files (x86)\\Microsoft Office\\Office16\\lync.exe", "/C", "Callto:tel:+ 17139894408"});
			    				} catch (IOException e) {
			    					e.printStackTrace();
			    				}
			    	        }
			    		}												
			    	}
			    });
			    button.setIcon(new ImageIcon(g_CurrentTickets.class.getResource("/telephone.png")));
			    button.setToolTipText("Call CC");
			    
			    JLabel lblControlCenter = new JLabel("Control Center");
			    lblControlCenter.setFont(new Font("Rockwell", Font.PLAIN, 11));
			    lblControlCenter.setBounds(302, 770, 98, 14);
			    panel_1.add(lblControlCenter);
			    
			    lblTicketStatus = new JLabel("Ticket Status:");
			    lblTicketStatus.setHorizontalAlignment(SwingConstants.LEFT);
			    lblTicketStatus.setFont(new Font("Rockwell", Font.BOLD, 16));
			    lblTicketStatus.setBounds(254, 665, 141, 20);
			    panel_1.add(lblTicketStatus);
			    
			  			   
			    btnArchive = new JButton("");
			    btnArchive.setToolTipText("Archive");
			    btnArchive.setFont(new Font("Rockwell", Font.PLAIN, 11));
			    btnArchive.setSelectedIcon(new ImageIcon(g_CurrentTickets.class.getResource("/archive_hover.png")));
			    btnArchive.setIcon(new ImageIcon(g_CurrentTickets.class.getResource("/archive.png")));
			    btnArchive.addActionListener(new ActionListener() {
			    	public void actionPerformed(ActionEvent e) {
			    		SearchArchive = true;
			    		g_ArchiveTickets.runPopup(frmCurrentTickets);			    		
			    	}
			    });
			    btnArchive.setBounds(125, 42, 24, 24);
			    panel_1.add(btnArchive);
			    
			    btnFlag = new JButton("");
			    btnFlag.setToolTipText("Unflag Email Sent");
			    btnFlag.addActionListener(new ActionListener() {
			    	public void actionPerformed(ActionEvent arg0) {
			    		ClearEmailFlag();
			    	}
			    });
			    btnFlag.setIcon(new ImageIcon(g_CurrentTickets.class.getResource("/flag.png")));
			    btnFlag.setBounds(589, 44, 30, 30);
			    panel_1.add(btnFlag);
			    btnFlag.setVisible(false);
			    
			    btnUnFlag = new JButton("");
			    btnUnFlag.setToolTipText("Flag Email Sent");
			    btnUnFlag.addActionListener(new ActionListener() {
			    	public void actionPerformed(ActionEvent e) {
			    		SetEmailFlag();
			    	}
			    });
			    btnUnFlag.setIcon(new ImageIcon(g_CurrentTickets.class.getResource("/unflag.png")));
			    btnUnFlag.setBounds(589, 44, 30, 30);
			    panel_1.add(btnUnFlag);
			    
			    btnSaveAssign = new JButton("");
			    btnSaveAssign.addActionListener(new ActionListener() {
			    	public void actionPerformed(ActionEvent e) {
			    		SaveAssign();
			    	}
			    });
			    btnSaveAssign.setIcon(new ImageIcon(g_CurrentTickets.class.getResource("/ok-icon.png")));
			    btnSaveAssign.setBounds(130, 669, 16, 16);
			    panel_1.add(btnSaveAssign);
			    btnSaveAssign.setVisible(false);
			    
			    btnCancelAssign = new JButton("");
			    btnCancelAssign.addActionListener(new ActionListener() {
			    	public void actionPerformed(ActionEvent arg0) {
			    		CancelAssigned();
			    	}
			    });
			    btnCancelAssign.setIcon(new ImageIcon(g_CurrentTickets.class.getResource("/Actions-window-close-icon.png")));
			    btnCancelAssign.setBounds(155, 669, 16, 16);
			    panel_1.add(btnCancelAssign);
			    btnCancelAssign.setVisible(false);
			    
			    btnSaveCategory = new JButton("");
			    btnSaveCategory.addActionListener(new ActionListener() {
			    	public void actionPerformed(ActionEvent e) {
			    		SaveCategory();
			    	}
			    });
			    btnSaveCategory.setIcon(new ImageIcon(g_CurrentTickets.class.getResource("/ok-icon.png")));
			    btnSaveCategory.setBounds(603, 669, 16, 16);
			    panel_1.add(btnSaveCategory);
			    btnSaveCategory.setVisible(false);
			    
			    btnCancelCategory = new JButton("");
			    btnCancelCategory.addActionListener(new ActionListener() {
			    	public void actionPerformed(ActionEvent e) {
			    		CancelCategory();
			    	}
			    });
			    btnCancelCategory.setIcon(new ImageIcon(g_CurrentTickets.class.getResource("/Actions-window-close-icon.png")));
			    btnCancelCategory.setBounds(625, 669, 16, 16);
			    panel_1.add(btnCancelCategory);			    
			    btnUnFlag.setVisible(false);
			    btnCancelCategory.setVisible(false);
			    
			    
			    JButton btnNewButton = new JButton("Generate Email");
			    btnNewButton.setFont(new Font("Rockwell", Font.PLAIN, 12));
			    btnNewButton.setBounds(798, 822, 128, 23);
			    frmCurrentTickets.getContentPane().add(btnNewButton);
			    btnNewButton.addActionListener(new ActionListener() {
			    	public void actionPerformed(ActionEvent arg0) {				
			    		GetCheckboxes();
			    	}
			    });
			    cb_Category.setVisible(false);
				
		tree_active.addTreeSelectionListener(new TreeSelectionListener() {
			public void valueChanged(TreeSelectionEvent e) {
				PopulateActiveWindow();
			}
		});
		
				//Menubar
				JMenuBar menuBar = new JMenuBar();
				frmCurrentTickets.setJMenuBar(menuBar);		
				JMenu mnSupport = new JMenu("Support");
				menuBar.add(mnSupport);			
				JMenuItem mntmNewTicket = new JMenuItem("New Ticket");
				mntmNewTicket.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						g_MainMenu.checkVersion();
						if(c_CheckOpenTickets.CheckTickets())
						{							
							g_TicketEntry.run(frmCurrentTickets);
						}
						else
						{
							JOptionPane.showMessageDialog(frmCurrentTickets, "Open Ticket Limit Exceeded. \n Please Close Old Tickets.");
						}						
					}
				});
				mnSupport.add(mntmNewTicket);
				
				JMenuItem mntmCurrentTickets = new JMenuItem("Current Tickets");				
				mnSupport.add(mntmCurrentTickets);
				mntmCurrentTickets.setEnabled(false);	
				
				JMenuItem mntmArchive = new JMenuItem("Archive");
				mntmArchive.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						g_ArchiveTickets.run(frmCurrentTickets);
					}
				});
				mnSupport.add(mntmArchive);
				
				JMenu mnSites = new JMenu("Sites");
				menuBar.add(mnSites);				
				JMenuItem mntmButaneSites = new JMenuItem("Butane Sites");
				mntmButaneSites.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						g_ViewSites.run(frmCurrentTickets);
					}
				});
				mnSites.add(mntmButaneSites);
				
				JMenuItem mntmSiteChanges = new JMenuItem("Site Changes");
				mntmSiteChanges.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						g_SiteChanges.run(frmCurrentTickets);
					}
				});
				mnSites.add(mntmSiteChanges);
				
				JMenu mnContacts = new JMenu("Contacts");
				menuBar.add(mnContacts);
				
				JMenuItem mntmENEmployees = new JMenuItem("EN Employees");
				mntmENEmployees.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						g_ViewEmployees.run(frmCurrentTickets);
					}
				});
				mnContacts.add(mntmENEmployees);
				
				JMenuItem mntmEtpContacts = new JMenuItem("ETP Contacts");
				mntmEtpContacts.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						g_ViewSunoco.run(frmCurrentTickets);
					}
				});
				mnContacts.add(mntmEtpContacts);
			
				JMenu mnTools = new JMenu("Tools");
				menuBar.add(mnTools);
				
				JMenuItem mntmCreateChecklist = new JMenuItem("Create Checklist");
				mntmCreateChecklist.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						g_Tools_CreateChecklist.run(frmCurrentTickets);
					}
				});		
				mnTools.add(mntmCreateChecklist);
	}
	
	int tmp2[];
	private JLabel lblAssignedTo;	
	private JScrollPane scrollPane_2;
	private JScrollPane scrollPane_3;
	private JPanel panel;
	private JScrollPane scrollPane_4;
	private JButton btnTicketAdd;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JButton btn_AnalyzerGuide;
	private JLabel lbl_Category;
	private JButton btnEditCategory;
	private JLabel lblCategory;
	private JLabel lblTicketStatus;	
	private JButton btnArchive;
	private JButton btnSaveCategory;
	private JButton btnCancelCategory;
			
	private void GetCheckboxes()
	{
		TreePath[] active_checkpaths = tree_active.getCheckingModel().getCheckingPaths();	
		TreePath[] closed_checkpaths = tree_closed.getCheckingModel().getCheckingPaths();	
		List<String> TicketList = new ArrayList<String>();
						
		for (int i = 0; i <= active_checkpaths.length; i++)
		{
			try
			{
			String tmp = active_checkpaths[i].toString();
			tmp = tmp.split("[\\(\\)]")[1];	//grab ticket number only
			TicketList.add(tmp);
			}
			catch (Exception e)
			{
				//do nothing
			}		
		}
		
		
		for (int i = 0; i <= closed_checkpaths.length; i++)
		{
			try
			{
			String tmp = closed_checkpaths[i].toString();
			tmp = tmp.split("[\\(\\)]")[1];	//grab ticket number only
			TicketList.add(tmp);
			}
			catch (Exception e)
			{
				//do nothing
			}

		}
		
		g_ReportEmail.GenerateEmailReport(TicketList);
		frmCurrentTickets.dispose();
	
	}
	
	public void RefreshAll()
	{
		PopulateActiveWindow();
		PopulateActiveTickets();
		PopulateRecentTickets();
	}
	

	private void PopulateActiveWindow()
	{		
		cb_Assigned.setVisible(false);
		cb_Category.setVisible(false);
		lbl_Assigned.setVisible(true);
		lbl_Category.setVisible(true);
		btnEdit.setVisible(true);
		btnEditCategory.setVisible(true);
		cb_Status.setEnabled(true);
		try{
		String tmp = tree_active.getSelectionPath().getLastPathComponent().toString();	
		
		
		TicketNum = tmp.split("[\\(\\)]")[1];		
		}
		catch (Exception e)
		{
			//do nothing
		}
		
		String commandText = "";
		
		
		 commandText = "SELECT a.Client, a.Site, a.SiteID, a.HostIP, a.ViewIP, a.SQLIP, a.DevIP, a.iDracIP, a.HighPerformance, a.CentralSQL, a.CentralView, b.EmailSent, b.Category, b.Ticket, b.Description, b.Internal, b.Assigned, b.Status, CONVERT(varchar(17), b.EnteredDate, 113) as EnteredDate, CONVERT(varchar(17), b.UpdateDate, 113) as UpdateDate, b.TimeSpent, CONVERT(varchar(17), b.CCNotified, 113) as CCNotified, b.Resolution, b.LastUpdatedBy, b.rowID "
							+ "FROM Sites a, SupportTickets b WHERE a.Client = b.Client and a.Site = b.Site and Ticket = '" + TicketNum + "'";
		
		ResultSet rs = c_Query.ExecuteResultSet(commandText);
		
		try
		{
			while((rs!=null) && (rs.next()))
			{
				client = rs.getString("Client");
				site = rs.getString("Site");
				siteid = rs.getInt("SiteID");
				lbl_ClientSite.setText(client + " " + site + " (" + siteid + ")");
				siteID = rs.getString("SiteID");
				viewIP = rs.getInt("ViewIP");
				SQLIP = rs.getInt("SQLIP");
				DevIP = rs.getInt("DevIP");
				iDracIP = rs.getInt("iDracIP");
				hostIP = rs.getInt("HostIP");
				lbl_TicketEntered.setText(rs.getString("EnteredDate"));
				highperformance = rs.getBoolean("HighPerformance");
				centralSQL = rs.getBoolean("CentralSQL");
				centralView = rs.getBoolean("CentralView");
				category = rs.getString("Category");
				lbl_Category.setText(category);
				emailSentFlag = rs.getBoolean("EmailSent");
				if(category.compareTo("Sampling")==0)
				{
					btn_AnalyzerGuide.setVisible(true);
				}
				else
				{
					btn_AnalyzerGuide.setVisible(false);
				}
				
				if(emailSentFlag)
				{
					btnFlag.setVisible(true);
				}
				else
				{
					btnUnFlag.setVisible(true);
				}
				
				lbl_Ticket.setText("(#" + rs.getString("Ticket") + ")");
				lbl_Assigned.setText(rs.getString("Assigned"));
				
				String Status = rs.getString("Status");
				
				if(Status.compareTo("Investigating") == 0)
				{
					cb_Status.setSelectedIndex(0);						
				}
				else if (Status.compareTo("Ongoing") == 0)
				{
					cb_Status.setSelectedIndex(1);	
				}
				else if (Status.compareTo("Ongoing - Need Field Support") == 0)
				{
					cb_Status.setSelectedIndex(2);	
				}
				else if (Status.compareTo("Ongoing - Need ETP Approval") == 0)
				{
					cb_Status.setSelectedIndex(3);	
				}
				else if (Status.compareTo("Closed by ETP") == 0)
				{
					cb_Status.setSelectedIndex(4);	
				}
				else if(Status.compareTo("Complete") == 0)
				{
					cb_Status.setSelectedIndex(5); 	
				}
				
				if(rs.getString("CCNotified") == null || (rs.getString("CCNotified").compareTo("null") == 0))
				{
					CCNotifiedState = false;
					chckbxCcNotified.setSelected(false);						
				}				
				else
				{
					CCNotifiedState = true;
					chckbxCcNotified.setSelected(true);
				}
				txt_Issue.setText(rs.getString("Description"));
				txt_Update.setText(rs.getString("Resolution"));
				txt_Internal.setText(rs.getString("Internal"));
				txt_TimeSpent.setText(rs.getString("TimeSpent"));
				lbl_UpdateDate.setText(rs.getString("UpdateDate"));
				lblLastUpdatedByStr.setText(rs.getString("LastUpdatedBy"));								
				rowID = rs.getInt("rowID");
				tree_closed.clearSelection();
			}
		}
		catch(Exception e)
		{
			System.out.println(e.toString());
		}
		
		if(txt_Internal.getText().compareTo("") != 0)
		{
			btn_Internal.setText("* Internal *");
			btn_Internal.setBackground(Color.orange);
		}
		else
		{
			btn_Internal.setText("Internal");
			btn_Internal.setBackground(Color.getColor("240,240,240"));
			
		}
		
		
	}
	
	private void ChangeAssignment()
	{
		btnEdit.setVisible(false);		
		lbl_Assigned.setVisible(false);
		cb_Assigned.setVisible(true);
		btnCancelAssign.setVisible(true);
		btnSaveAssign.setVisible(true);
		
		String commandText = "SELECT DISTINCT Name FROM EN_Employees WHERE Active = 1 ORDER BY Name Asc";       
		
        ResultSet rs = c_Query.ExecuteResultSet(commandText);
        try {
			while((rs!=null) && (rs.next()))
			{					
				String name = rs.getString(1);				
				cb_Assigned.addItem(name);	
			}
		} catch (SQLException e) {
		}
        try {
			rs.close();
		} catch (SQLException e) {
		
		}        
        //AssignChange = true;
        
	}
	
	private void SaveAssign()
	{
		btnEdit.setVisible(true);
		btnCancelAssign.setVisible(false);
		btnSaveAssign.setVisible(false);
		
		String assigned = cb_Assigned.getSelectedItem().toString();
		cb_Assigned.setVisible(false);
		
		lbl_Assigned.setText(assigned);
		String commandText = "UPDATE SupportTickets SET Assigned ='" + assigned + "' WHERE Ticket = '" + TicketNum + "'";
		c_Query.UpdateResultSet(commandText);
		lbl_Assigned.setVisible(true);
		//Send update email
		c_ConnectToDatabase.SendUpdateEmail(assigned, client, site, TicketNum, txt_Issue.getText());
	}
	
	private void SaveCategory()
	{
		btnEditCategory.setVisible(true);
		btnCancelCategory.setVisible(false);
		btnSaveCategory.setVisible(false);
		
		String category = cb_Category.getSelectedItem().toString();
		cb_Category.setVisible(false);
		
		lblCategory.setText(category);
		String commandText = "UPDATE SupportTickets SET Category ='" + category + "' WHERE Ticket = '" + TicketNum + "'";
		c_Query.UpdateResultSet(commandText);
		lblCategory.setVisible(true);
		
	}
	
	
	private void ChangeCategory()
	{	
		btnEditCategory.setVisible(false);
		btnSaveCategory.setVisible(true);
		btnCancelCategory.setVisible(true);		
		lbl_Category.setVisible(false);
		cb_Category.setVisible(true);
		String commandText = "SELECT DISTINCT Category FROM SupportTickets ORDER BY Category Asc";       
		
        ResultSet rs = c_Query.ExecuteResultSet(commandText);
        try {
			while((rs!=null) && (rs.next()))
			{					
				String category = rs.getString(1);				
				cb_Category.addItem(category);	
			}
		} catch (SQLException e) {
		}
        try {
			rs.close();
		} catch (SQLException e) {
		
		}                        
	}
	
	
	
	
	private void PopulateRecentWindow()
	{		
		try{
		String tmp = tree_closed.getSelectionPath().getLastPathComponent().toString();		
		TicketNum = tmp.split("[\\(\\)]")[1];		
		}
		catch (Exception e)
		{
			//do nothing
		}
		String commandText = "";
		
		 commandText = "SELECT a.Client, a.Site, a.SiteID, a.HostIP, a.ViewIP, a.SQLIP, a.DevIP, a.iDracIP, a.HighPerformance, b.EmailSent, b.Category, b.Ticket, b.Description, b.Internal, b.Assigned, b.Status, CONVERT(varchar(17), b.UpdateDate, 113) as UpdateDate, CONVERT(varchar(17), b.CCNotified, 113) as CCNotified, b.TimeSpent, b.Resolution, b.LastUpdatedBy, b.rowID "
							+ "FROM Sites a, SupportTickets b WHERE a.Client = b.Client and a.Site = b.Site and Ticket = '" + TicketNum + "'";
		
		ResultSet rs = c_Query.ExecuteResultSet(commandText);
		
		try
		{
			while((rs!=null) && (rs.next()))
			{
				String siteInfo = rs.getString("Client") + " " + rs.getString("Site") + " (" + rs.getString("SiteID") + ")";
				lbl_ClientSite.setText(siteInfo);
				lbl_Ticket.setText("(#" + rs.getString("Ticket") + ")");
				ViewSiteData.add(siteInfo); 
				siteID = rs.getString("SiteID");
				viewIP = rs.getInt("ViewIP");
				SQLIP = rs.getInt("SQLIP");
				DevIP = rs.getInt("DevIP");
				iDracIP = rs.getInt("iDracIP");
				hostIP = rs.getInt("HostIP");
				category = rs.getString("Category");
				highperformance = rs.getBoolean("HighPerformance");
				//emailSentFlag = rs.getBoolean("EmailSent");
				ViewSiteData.add("(#" + rs.getString("Ticket") + ")"); //Ticket # Index 1
				ViewSiteData.add(Integer.toString(hostIP)); //Host, Index 2
				ViewSiteData.add(Integer.toString(viewIP)); //View, Index 3
				ViewSiteData.add(Integer.toString(SQLIP)); //SQL, Index 4
				ViewSiteData.add(Integer.toString(DevIP)); //Dev, Index 5
				ViewSiteData.add(Integer.toString(iDracIP)); //iDrac, Index 6				
				lbl_Assigned.setText(rs.getString("Assigned"));
				if(rs.getString("Status").compareTo("Complete") == 0)
				{
					cb_Status.setSelectedIndex(5); 	
				}
				if(rs.getString("CCNotified") == null)
				{
					CCNotifiedState = false;
					chckbxCcNotified.setSelected(false);
						
				}
				else
				{
					CCNotifiedState = true;
					chckbxCcNotified.setSelected(true);
				}
				txt_Issue.setText(rs.getString("Description"));
				txt_Update.setText(rs.getString("Resolution"));
				lbl_UpdateDate.setText(rs.getString("UpdateDate"));
				txt_Internal.setText(rs.getString("Internal"));
				txt_TimeSpent.setText(rs.getString("TimeSpent"));
				lblLastUpdatedByStr.setText(rs.getString("LastUpdatedBy"));		
				rowID = rs.getInt("rowID");	
				btnFlag.setVisible(false);
				btnUnFlag.setVisible(false);								
			}
		}
		catch(Exception e)
		{
			//do nothing
			System.out.println(e.toString());
		}
		cb_Status.setEnabled(false);
		
		tree_active.clearSelection();
	
	}
	
	private void PopulateActiveTickets()
	{
		if(rdbtnMyTickets.isSelected())
		{
			try {				
				tree_active.setModel(new DefaultTreeModel(
						new DefaultMutableTreeNode("Active Tickets") {
							private static final long serialVersionUID = 1L;
							{
								DefaultMutableTreeNode proj;
								
								DefaultMutableTreeNode projnum;
								String commandText = "SELECT DISTINCT Client from SupportTickets WHERE (Status != 'Complete' AND Status != 'Closed By ETP') AND Assigned ='" + g_MainMenu.CurrentUser + "' ORDER BY Client ASC";
								ResultSet rs = c_Query.ExecuteResultSet(commandText);
								
								while((rs!=null) && (rs.next()))
								{
									String projectname = rs.getString(1);
									proj = new DefaultMutableTreeNode(projectname);
										
										String commandText2 = "SELECT Site, Ticket, EmailSent from SupportTickets WHERE Active = 1 AND Assigned ='" + g_MainMenu.CurrentUser + "' and Client ='" + projectname + "' ORDER BY Site ASC";
										
										ResultSet rs2 = c_Query.ExecuteResultSet(commandText2);
										boolean emailSent = false;
										while((rs2!=null) && (rs2.next()))
										{
											emailSent = rs2.getBoolean("EmailSent");
											
											if(emailSent)
											{
												projnum = new DefaultMutableTreeNode("<html><div style=background-color:#FF5733;>" + rs2.getString("Site") + " ("  + rs2.getString("Ticket") +  ")</div></html>");
											}
											else
											{
												projnum = new DefaultMutableTreeNode(rs2.getString("Site") + " ("  + rs2.getString("Ticket") +  ")");											
											}
																					
											proj.add(projnum);										
										}							
									add(proj);
								}
							}
						}
					));
			} catch (SQLException e) {
				
				e.printStackTrace();
			}	
			
		}
		else if (rdbtnAllTickets.isSelected())
		{
			try {				
				tree_active.setModel(new DefaultTreeModel(
						new DefaultMutableTreeNode("Active Tickets") {
							private static final long serialVersionUID = 1L;
							{
								DefaultMutableTreeNode proj;
								
								DefaultMutableTreeNode projnum;
								String commandText = "SELECT DISTINCT Client from SupportTickets WHERE (Status != 'Complete' AND Status != 'Closed By ETP') ORDER BY Client ASC";
								ResultSet rs = c_Query.ExecuteResultSet(commandText);
								
								while((rs!=null) && (rs.next()))
								{
									String projectname = rs.getString(1);
									proj = new DefaultMutableTreeNode(projectname);
										
										String commandText2 = "SELECT Site, Ticket, EmailSent from SupportTickets WHERE Active = 1 and Client ='" + projectname + "' ORDER BY Site ASC";
										
										ResultSet rs2 = c_Query.ExecuteResultSet(commandText2);
										boolean emailSent = false;
										while((rs2!=null) && (rs2.next()))
										{
											emailSent = rs2.getBoolean("EmailSent");
											
											if(emailSent)
											{
												projnum = new DefaultMutableTreeNode("<html><div style=background-color:#FF5733;>" + rs2.getString("Site") + " ("  + rs2.getString("Ticket") +  ")</div></html>");
											}
											else
											{
												projnum = new DefaultMutableTreeNode(rs2.getString("Site") + " ("  + rs2.getString("Ticket") +  ")");											
											}
																					
											proj.add(projnum);										
										}							
									add(proj);
								}
							}
						}
					));
			} catch (SQLException e) {
				
				e.printStackTrace();
			}	
		}
		else if (rdbtnUpdates.isSelected())
		{
			try {				
				tree_active.setModel(new DefaultTreeModel(
						new DefaultMutableTreeNode("Active Tickets") {
							private static final long serialVersionUID = 1L;
							{
								DefaultMutableTreeNode proj;
								
								DefaultMutableTreeNode projnum;
								String commandText = "SELECT DISTINCT Client from SupportTickets WHERE (Status != 'Complete' AND Status != 'Closed By ETP') AND (EmailSent is null OR EmailSent = 0) ORDER BY Client ASC";
								ResultSet rs = c_Query.ExecuteResultSet(commandText);
								
								while((rs!=null) && (rs.next()))
								{
									String projectname = rs.getString(1);
									proj = new DefaultMutableTreeNode(projectname);
										
										String commandText2 = "SELECT Site, Ticket, EmailSent from SupportTickets WHERE Active = 1 AND (EmailSent is null OR EmailSent = 0) and Client ='" + projectname + "' ORDER BY Site ASC";
										
										ResultSet rs2 = c_Query.ExecuteResultSet(commandText2);
										boolean emailSent = false;
										while((rs2!=null) && (rs2.next()))
										{
											emailSent = rs2.getBoolean("EmailSent");
											
											if(emailSent)
											{
												projnum = new DefaultMutableTreeNode("<html><div style=background-color:#FF5733;>" + rs2.getString("Site") + " ("  + rs2.getString("Ticket") +  ")</div></html>");
											}
											else
											{
												projnum = new DefaultMutableTreeNode(rs2.getString("Site") + " ("  + rs2.getString("Ticket") +  ")");											
											}
																					
											proj.add(projnum);										
										}							
									add(proj);
								}
							}
						}
					));
			} catch (SQLException e) {
				
				e.printStackTrace();
			}	
			
			
		}
		
			
	}
	
	
	private void PopulateRecentTickets()
	{		
		try {
			tree_closed.setModel(new DefaultTreeModel(
					new DefaultMutableTreeNode("Recently Closed Tickets") {
						private static final long serialVersionUID = 1L;
						{
							DefaultMutableTreeNode proj;
							DefaultMutableTreeNode projnum;
							String commandText = "";

							commandText = "SELECT DISTINCT Client from SupportTickets WHERE Status = 'Complete' and EmailSent = 0 ORDER BY Client ASC";
							ResultSet rs = c_Query.ExecuteResultSet(commandText);
							
							while((rs!=null) && (rs.next()))
							{
								String projectname = rs.getString(1);
								proj = new DefaultMutableTreeNode(projectname);	
									String commandText2 = "";									
									commandText2 = "SELECT Site, Ticket from SupportTickets WHERE Status = 'Complete' and EmailSent = 0 and Client ='" + projectname + "'"; ;
									ResultSet rs2 = c_Query.ExecuteResultSet(commandText2);
									
									while((rs2!=null) && (rs2.next()))
									{
										projnum = new DefaultMutableTreeNode(rs2.getString(1) + " (" + rs2.getString(2) + ")");
										proj.add(projnum);
									}
																	
								add(proj);							
							}
						}
					}
				));
		} catch (SQLException e) {
			
			e.printStackTrace();
		}		
	}

	
	 
	 public void ClosedByETP()
	 {
		 String commandText = "UPDATE SupportTickets SET Active = 0, Status = 'Closed By ETP' WHERE rowID = " + rowID;
		 c_Query.UpdateResultSet(commandText);
		 PopulateActiveWindow();
		 PopulateActiveTickets();
	 }
	 
	 
	 public void CloseTicket()
	 {		 
		 DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm");
		 String testDate = "";
		 		 
			 Date date = new Date();
			 testDate = dateFormat.format(date);	
		
		 			 
		 String resolution = txt_Update.getText();
		 resolution = c_CleanString.Clean_String(resolution);
		 String assigned = lbl_Assigned.getText();
		 String commandText = "";
		 String category = lbl_Category.getText();
		 boolean CCCompilation = false;
		 if(!CCNotifiedState && chckbxCcNotified.isSelected())
		 {
			 CCCompilation = true;
		 }
		 else
		 {
			 CCCompilation = false;
		 }
		 
		
		 
		 //if(cb_Assigned.isVisible() == true && CCCompilation == true)
	     if(CCCompilation == true)
		 {
			 Date CCdate = new Date();
			 String ccDateTime = dateFormat.format(CCdate);
			// String assigned = cb_Assigned.getSelectedItem().toString();
			 commandText = "UPDATE SupportTickets SET Assigned = '" + assigned +"', Category = '" + category + "', Status = '" + cb_Status.getSelectedItem().toString() + "', Resolution = '" + resolution + "', TimeSpent = '" +  txt_TimeSpent.getText() + "', UpdateDate = '" + testDate + "', Active = 0, EmailSent = 0, CCNotified = '" + ccDateTime + "', LastUpdatedBy = '" + g_MainMenu.CurrentUser + "' WHERE rowID = " + rowID;
		 }
	     /*
		 else if(cb_Assigned.isVisible() == true && CCCompilation == false)
		 {
			 String assigned = cb_Assigned.getSelectedItem().toString();
			 commandText = "UPDATE SupportTickets SET Assigned = '" + assigned +"', Category = '" + category  + ",Status = '" + cb_Status.getSelectedItem().toString() + "', Resolution = '" + resolution + "', TimeSpent = '" +  txt_TimeSpent.getText() + "', UpdateDate = '" + testDate + "', Active = 0, EmailSent = 0, LastUpdatedBy = '" + g_MainMenu.CurrentUser + "' WHERE rowID = " + rowID;
		 }
		 else if(cb_Assigned.isVisible() == false && CCCompilation == true)
		 {
			 Date CCdate = new Date();
			 String ccDateTime = dateFormat.format(CCdate);
			 commandText = "UPDATE SupportTickets SET Status = '" + cb_Status.getSelectedItem().toString() + "', Category ='" + category  + "', Resolution = '" + resolution + "', TimeSpent = '" +  txt_TimeSpent.getText() + "', UpdateDate = '" + testDate + "', Active = 0, EmailSent = 0, CCNotified = '" + ccDateTime + "', LastUpdatedBy = '" + g_MainMenu.CurrentUser + "' WHERE rowID = " + rowID;
		 }
		 */
		 else
		 {
			commandText = "UPDATE SupportTickets SET Assigned = '" + assigned + "', Status = '" + cb_Status.getSelectedItem().toString() + "', Category = '" + category  + "', Resolution = '" + resolution + "', TimeSpent = '" +  txt_TimeSpent.getText() + "', UpdateDate = '" + testDate + "', Active = 0, EmailSent = 0, LastUpdatedBy = '" + g_MainMenu.CurrentUser + "' WHERE rowID = " + rowID;
		 }
		 
			c_Query.UpdateResultSet(commandText);

			commandText = "";
		 	
	
			/*Check for Internal Update */
			if(txt_Internal.getText().compareTo("") != 0)
			{
				String internal = c_CleanString.Clean_String(txt_Internal.getText());
				commandText = "UPDATE SupportTickets SET Internal = '" + internal + "' WHERE Ticket = '" + TicketNum + "'";
				c_Query.UpdateResultSet(commandText);
			}
			
			PopulateActiveWindow();
			PopulateActiveTickets();
			PopulateRecentTickets();
	 }
	 

	 public void UpdateTicket()
	 {
		 TreeSelectionModel test = (TreeSelectionModel)tree_active.getSelectionModel();
		 TreePath tpath = test.getSelectionPath();
		 
		 String testDate = "";
		 DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm");
		 		 
		 Date date = new Date();
		 testDate = dateFormat.format(date);	
		 String commandText = "";
		 String resolution = txt_Update.getText();
		 String assigned = lbl_Assigned.getText();
		 String category = lbl_Category.getText();
		 resolution = c_CleanString.Clean_String(resolution);
		 boolean CCCompilation = false;
		 if(!CCNotifiedState && chckbxCcNotified.isSelected())
		 {
			 CCCompilation = true;
		 }
		 else
		 {
			 CCCompilation = false;
		 }
		 
		 
		 
		// if(cb_Assigned.isVisible() == true && CCCompilation == true)
		 if(CCCompilation == true)
		 {
			 Date CCdate = new Date();
			 String ccDateTime = dateFormat.format(CCdate);			 
			 //String assigned = cb_Assigned.getSelectedItem().toString();
			 commandText = "UPDATE SupportTickets SET Assigned = '" + assigned +"', Category = '" + category  + "', Status = '" + cb_Status.getSelectedItem().toString() + "', Resolution = '" + resolution + "', TimeSpent = '" +  txt_TimeSpent.getText() + "', UpdateDate = '" + testDate + "', Active = 1, EmailSent = 0, CCNotified = '" + ccDateTime + "', LastUpdatedBy = '" + g_MainMenu.CurrentUser + "' WHERE rowID = " + rowID;
		 }
		/*else if(cb_Assigned.isVisible() == true && CCCompilation == false)
		
		 {
			 String assigned = cb_Assigned.getSelectedItem().toString();
			 commandText = "UPDATE SupportTickets SET Assigned = '" + assigned +"', Category = '" + cb_Category.getSelectedItem().toString()  + "', Status = '" + cb_Status.getSelectedItem().toString() + "', Resolution = '" + resolution + "', TimeSpent = '" +  txt_TimeSpent.getText() + "', UpdateDate = '" + testDate + "', Active = 1, EmailSent = 0, LastUpdatedBy = '" + g_MainMenu.CurrentUser + "' WHERE rowID = " + rowID;
		 }
		 else if(cb_Assigned.isVisible() == false && CCCompilation == true)
		 {
			 Date CCdate = new Date();
			 String ccDateTime = dateFormat.format(CCdate);
			 commandText = "UPDATE SupportTickets SET Status = '" + cb_Status.getSelectedItem().toString() + "', Category = '" + cb_Category.getSelectedItem().toString()  + "',  Resolution = '" + resolution + "', TimeSpent = '" +  txt_TimeSpent.getText() + "', UpdateDate = '" + testDate + "', Active = 1, EmailSent = 0, CCNotified = '" + ccDateTime + "', LastUpdatedBy = '" + g_MainMenu.CurrentUser + "' WHERE rowID = " + rowID;
		 }
		 */
		 else
		 {
			commandText = "UPDATE SupportTickets SET Assigned = '" + assigned +"', Status = '" + cb_Status.getSelectedItem().toString() + "', Category = '" + category  + "',  Resolution = '" + resolution + "', TimeSpent = '" +  txt_TimeSpent.getText() + "', UpdateDate = '" + testDate + "', Active = 1, EmailSent = 0, LastUpdatedBy = '" + g_MainMenu.CurrentUser + "' WHERE rowID = " + rowID;
		 }
		 
			c_Query.UpdateResultSet(commandText);

			commandText = "";
									
			
			/*Check for Internal Update */
			if(txt_Internal.getText().compareTo("") != 0)
			{
				String internal = c_CleanString.Clean_String(txt_Internal.getText());
				commandText = "UPDATE SupportTickets SET Internal = '" + internal + "' WHERE Ticket = '" + TicketNum + "'";
				c_Query.UpdateResultSet(commandText);
			}

			PopulateActiveWindow();
			tree_active.getExpandsSelectedPaths();
			tree_active.setSelectionPath(tpath);
			tree_active.scrollPathToVisible(tpath);					 
	 }
	 
	 private void CancelAssigned()
	 {		
		 btnCancelAssign.setVisible(false);
		 btnSaveAssign.setVisible(false);
		 btnEdit.setVisible(true);
		 cb_Assigned.setVisible(false);
		 lbl_Assigned.setVisible(true);
	 }
	 
	 private void CancelCategory()
	 {		
		 
		 btnCancelCategory.setVisible(false);
		 btnSaveCategory.setVisible(false);
		 btnEditCategory.setVisible(true);
		 cb_Category.setVisible(false);
		 lbl_Category.setVisible(true);
	 }
	 
	 private void ClearEmailFlag()
	 {
		// String internal = c_CleanString.Clean_String(txt_Internal.getText());
			String commandText = "UPDATE SupportTickets SET EmailSent = 0 WHERE Ticket = '" + TicketNum + "'";
			c_Query.UpdateResultSet(commandText);		
			btnFlag.setVisible(false);
			btnUnFlag.setVisible(true);
	 }
	 
	 private void SetEmailFlag()
	 {
		 String commandText = "UPDATE SupportTickets SET EmailSent = 1 WHERE Ticket = '" + TicketNum + "'";
		c_Query.UpdateResultSet(commandText);	
		btnFlag.setVisible(true);
		btnUnFlag.setVisible(false);
		 
	 }
}
