import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Properties;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.tree.DefaultTreeModel;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.event.TreeSelectionListener;
import javax.swing.event.TreeSelectionEvent;
import java.awt.Color;
import javax.swing.JFileChooser;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.DefaultListModel;
import javax.swing.JTextField;
import javax.swing.JTree;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ImageIcon;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.JCheckBox;
import java.awt.Toolkit;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.JRadioButton;
import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.border.LineBorder;
import javax.swing.JEditorPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.SwingConstants;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
/* http://stackoverflow.com/questions/16157529/how-do-i-pass-objects-between-classes */
public class g_ArchiveTickets {

	//private static final AbstractButton chckbxSite = null;
	public static JFrame frmArchiveTickets;
	private JLabel lbl_ClientSite;
	private JLabel lbl_Ticket;
	private JLabel lbl_Assigned;
	private JLabel lbl_Problem;
	private JLabel lbl_Update;
	private JEditorPane txt_Issue;
	private JEditorPane txt_Update;
	private JEditorPane txt_Internal;
	private JLabel lblHours;
	private JLabel txt_TimeSpent;
	private JLabel lblDateLastUpdated;
	private JLabel lbl_UpdateDate;
	public static DefaultListModel<c_Files> fileListModel;
	public static DefaultListModel<c_Files> ExistingfileListModel;
	private JPanel panel_Update;
	private JScrollPane scrollPane;
	private JPanel panel_Internal;
	private JLabel lblInternalUpdate;
	private JButton btn_External;
	private JButton btn_Internal;
	private String TicketNum;
	public String siteID;	
	private String client;
	private String site;
	private int siteid;
	private static JLabel lblLastUpdatedByStr;
	private static JPanel panel_3;
	final ArrayList <c_Archive> archiveListarr = new ArrayList<c_Archive>();	
	private static DefaultListModel<String> listModel = new DefaultListModel<String>();
	private static JCheckBox chckbxInternal;
	private static JList<String> searchList = new JList<String>();
	private static JLabel txtEnteredDate;
	private static JLabel txtStatus;
	private JScrollPane scrollPane_4;
	JComboBox<String> cb_Client = new JComboBox<String>();
	JComboBox<String> cb_Site = new JComboBox<String>();
	JComboBox<String> cb_Assigned = new JComboBox<String>();
	private JDatePanelImpl datePanel;
	private JDatePanelImpl datePanel2; 
	private JDatePickerImpl datePicker;
	private JDatePickerImpl datePicker2;
	private JButton btnFilter;
	private JPanel panel_2;
	private boolean Filter_FirstRun = true;
	private JLabel lblAssignedTo;
	private JScrollPane scrollPane_2;
	private JScrollPane scrollPane_3;
	private JPanel panel;
	private JButton btnSearch;
	private JLabel lblTicketStatus;
	private JPanel panel_4;
	private JTextArea txtKeywords;
	private JTextField txtTicketNumber;
	private JLabel lblIssue;
	private JLabel lblTicket;
	private JPanel panel_5;
	private JCheckBox lbldateRange;
	private JComboBox<String> cb_DateRange;
	private JLabel lblstartDate;
	private JLabel lblendDate;
	private JComboBox<String> cb_Category;
	private JComboBox<String> cb_OrderBy1;
	private JComboBox<String> cb_OrderByType1;
	private JComboBox<String> cb_OrderBy2;
	private JComboBox<String> cb_OrderByType2;
	private JComboBox<String> cb_OrderBy3;
	private JComboBox<String> cb_OrderByType3;
	private JLabel lblorderBy;
	private JLabel lblandThenBy;
	private JLabel label;
	private JPanel panel_6;
	private JLabel lbldateRange_1;
	private JPanel panel_7;
	private JButton btnSearch_1;
	private JLabel lblResults;
	private JLabel lblqueryExecutedIn;

		
	public static void run(JFrame frame) {				
		try {
			@SuppressWarnings("unused")
			g_ArchiveTickets window = new g_ArchiveTickets();
			g_ArchiveTickets.frmArchiveTickets.setVisible(true);
			g_ArchiveTickets.frmArchiveTickets.setLocationRelativeTo(frame);
			frame.dispose();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}



	/**
	 * Create the application.
	 */
	public g_ArchiveTickets() {
		initialize();		
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmArchiveTickets = new JFrame();
		frmArchiveTickets.setResizable(false);
		frmArchiveTickets.setIconImage(Toolkit.getDefaultToolkit().getImage(g_ArchiveTickets.class.getResource("/icon.png")));		
		frmArchiveTickets.setTitle(g_MainMenu.TitleOnline);
		frmArchiveTickets.setBounds(100, 100, 996, 916);
		frmArchiveTickets.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmArchiveTickets.getContentPane().setLayout(null);
		
	
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		panel_1.setBounds(290, 11, 668, 788);
		frmArchiveTickets.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		lbl_ClientSite = new JLabel("Client Site (ID)");
		lbl_ClientSite.setBounds(10, 11, 505, 20);
		lbl_ClientSite.setFont(new Font("Rockwell", Font.BOLD, 16));
		panel_1.add(lbl_ClientSite);
		
		lbl_Ticket = new JLabel("(Ticket #)");
		lbl_Ticket.setBounds(525, 11, 114, 20);
		lbl_Ticket.setFont(new Font("Rockwell", Font.BOLD, 16));
		panel_1.add(lbl_Ticket);
		
		
		lbl_Problem = new JLabel("Issue");
		lbl_Problem.setFont(new Font("Rockwell", Font.BOLD, 16));
		lbl_Problem.setBounds(10, 77, 220, 20);
		panel_1.add(lbl_Problem);
		
		panel_Internal = new JPanel();
		panel_Internal.setBounds(10, 310, 648, 336);
		panel_1.add(panel_Internal);
		panel_Internal.setLayout(null);
		panel_Internal.setVisible(false);
		
		lblInternalUpdate = new JLabel("Internal Update");
		lblInternalUpdate.setFont(new Font("Rockwell", Font.BOLD, 16));
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
		scrollPane_2.setBounds(0, 27, 648, 355);
		panel_Internal.add(scrollPane_2);
		
		txt_Internal = new JEditorPane();
		txt_Internal.setContentType("text/html");
		txt_Internal.setEditable(false);
		txt_Internal.setFont(new Font("Rockwell", Font.BOLD, 20));
		scrollPane_2.setViewportView(txt_Internal);
		
		
		panel_Update = new JPanel();
		panel_Update.setBounds(10, 310, 648, 336);
		panel_1.add(panel_Update);
		panel_Update.setLayout(null);
		
		lbl_Update = new JLabel("Update");
		lbl_Update.setBounds(0, 0, 220, 20);
		panel_Update.add(lbl_Update);
		lbl_Update.setFont(new Font("Rockwell", Font.BOLD, 16));
		
		scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(0, 28, 648, 354);
		panel_Update.add(scrollPane);
		
		txt_Update = new JEditorPane();
		txt_Update.setContentType("text/html");
		txt_Update.setEditable(false);
		txt_Update.setFont(new Font("Rockwell", Font.PLAIN, 16));
		scrollPane.setViewportView(txt_Update);
		
		
		btn_Internal = new JButton("Internal");
		btn_Internal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel_Update.setVisible(false);
				panel_Internal.setVisible(true);
				
			}
		});
		btn_Internal.setBounds(559, 0, 89, 23);
		panel_Update.add(btn_Internal);
		
		scrollPane_3 = new JScrollPane();
		scrollPane_3.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane_3.setBounds(10, 99, 648, 200);
		panel_1.add(scrollPane_3);
		
		txt_Issue = new JEditorPane();		
		txt_Issue.setContentType("text/html");
		txt_Issue.setFont(new Font("Rockwell", Font.PLAIN, 16));
		scrollPane_3.setViewportView(txt_Issue);
		txt_Issue.setEditable(false);
		
		btnSearch = new JButton("");
		btnSearch.setBounds(626, 11, 32, 32);
		panel_1.add(btnSearch);
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ReactivateTicket();			    
			}
			
		});
		btnSearch.setIcon(new ImageIcon(g_ArchiveTickets.class.getResource("/file.png")));
		btnSearch.setToolTipText("Reactivate Ticket");
		
		JLabel lblLastUpdatedBy = new JLabel("Last Updated By");
		lblLastUpdatedBy.setBounds(511, 717, 134, 20);
		panel_1.add(lblLastUpdatedBy);
		lblLastUpdatedBy.setFont(new Font("Rockwell", Font.BOLD, 16));
		
		lblLastUpdatedByStr = new JLabel("Assigned To:");
		lblLastUpdatedByStr.setBounds(511, 742, 134, 20);
		panel_1.add(lblLastUpdatedByStr);
		lblLastUpdatedByStr.setFont(new Font("Rockwell", Font.PLAIN, 16));
		
		JLabel lblEnteredDate = new JLabel("Entered Date:");
		lblEnteredDate.setBounds(10, 717, 114, 20);
		panel_1.add(lblEnteredDate);
		lblEnteredDate.setFont(new Font("Rockwell", Font.BOLD, 16));
		
		txtEnteredDate = new JLabel("Entered Date");
		txtEnteredDate.setBounds(10, 742, 185, 20);
		panel_1.add(txtEnteredDate);
		txtEnteredDate.setFont(new Font("Rockwell", Font.PLAIN, 16));
		
		lblDateLastUpdated = new JLabel("Last Updated:");
		lblDateLastUpdated.setBounds(274, 717, 164, 20);
		panel_1.add(lblDateLastUpdated);
		lblDateLastUpdated.setFont(new Font("Rockwell", Font.BOLD, 16));
		
		lbl_UpdateDate = new JLabel("Last Update Date");
		lbl_UpdateDate.setBounds(274, 742, 164, 20);
		panel_1.add(lbl_UpdateDate);
		lbl_UpdateDate.setFont(new Font("Rockwell", Font.PLAIN, 16));
		
		lblHours = new JLabel("Time Spent");
		lblHours.setBounds(502, 659, 156, 20);
		panel_1.add(lblHours);
		lblHours.setFont(new Font("Rockwell", Font.BOLD, 16));
		
		JLabel lblhours = new JLabel("Hours");
		lblhours.setBounds(542, 681, 58, 20);
		panel_1.add(lblhours);
		lblhours.setFont(new Font("Rockwell", Font.PLAIN, 14));
		
		txt_TimeSpent = new JLabel();
		txt_TimeSpent.setBounds(512, 681, 33, 20);
		panel_1.add(txt_TimeSpent);
		txt_TimeSpent.setFont(new Font("Rockwell", Font.PLAIN, 14));
		txt_TimeSpent.setText("0.5");
		txt_TimeSpent.setToolTipText("Time Spent in Hours");
		
		lblTicketStatus = new JLabel("Ticket Status:");
		lblTicketStatus.setBounds(274, 659, 114, 20);
		panel_1.add(lblTicketStatus);
		lblTicketStatus.setFont(new Font("Rockwell", Font.BOLD, 16));
		
		txtStatus = new JLabel("Ticket Status");
		txtStatus.setBounds(274, 681, 164, 20);
		panel_1.add(txtStatus);
		txtStatus.setFont(new Font("Rockwell", Font.PLAIN, 16));
		
		lblAssignedTo = new JLabel("Assigned To:");
		lblAssignedTo.setBounds(10, 659, 114, 20);
		panel_1.add(lblAssignedTo);
		lblAssignedTo.setFont(new Font("Rockwell", Font.BOLD, 16));
		
		lbl_Assigned = new JLabel("Assigned");
		lbl_Assigned.setBounds(10, 681, 134, 20);
		panel_1.add(lbl_Assigned);
		lbl_Assigned.setFont(new Font("Rockwell", Font.PLAIN, 16));
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				g_MainMenu.run(frmArchiveTickets);
				frmArchiveTickets.dispose();
			}
		});
		btnBack.setBounds(10, 858, 89, 23);
		frmArchiveTickets.getContentPane().add(btnBack);
		
		panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(10, 11, 273, 788);
		frmArchiveTickets.getContentPane().add(panel);
		panel.setBorder(null);
				panel.setLayout(null);
				
				chckbxInternal = new JCheckBox("Internal");
				chckbxInternal.setBackground(Color.WHITE);
				chckbxInternal.setBounds(54, 2, 73, 23);				
				
				panel_4 = new JPanel();
				panel_4.setLayout(null);
				panel_4.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
				panel_4.setBackground(new Color(255, 245, 238));
				panel_4.setBounds(0, 37, 273, 751);
				panel.add(panel_4);
				panel_4.setVisible(false);
				
				txtKeywords = new JTextArea();
				txtKeywords.setLineWrap(true);
				txtKeywords.setFont(new Font("Rockwell", Font.PLAIN, 14));
				txtKeywords.setColumns(10);
				txtKeywords.setBounds(10, 247, 253, 75);
				panel_4.add(txtKeywords);
				
				txtTicketNumber = new JTextField();
				txtTicketNumber.addKeyListener(new KeyAdapter() {
					@Override
					public void keyPressed(KeyEvent e) {
						if(e.getKeyCode() == KeyEvent.VK_ENTER){
						    e.consume();
						    Search();
						    }
					}
				});
				txtTicketNumber.setFont(new Font("Rockwell", Font.PLAIN, 11));
				txtTicketNumber.setColumns(10);
				txtTicketNumber.setBounds(10, 182, 253, 31);
				panel_4.add(txtTicketNumber);
				
				cb_Client = new JComboBox<String>();
				cb_Client.setBackground(Color.LIGHT_GRAY);
				cb_Client.addItemListener(new ItemListener() {
					public void itemStateChanged(ItemEvent e) {				
						PopulateSiteList(cb_Client.getSelectedItem().toString());
					}
				});
				cb_Client.setFont(new Font("Rockwell", Font.PLAIN, 12));
				cb_Client.setBounds(10, 11, 253, 28);
				panel_4.add(cb_Client);
				
				cb_Site = new JComboBox<String>();
				cb_Site.setBackground(Color.LIGHT_GRAY);
				cb_Site.setEnabled(false);
				cb_Site.setFont(new Font("Rockwell", Font.PLAIN, 12));
				cb_Site.setBounds(10, 50, 253, 28);
				panel_4.add(cb_Site);
				
				lblIssue = new JLabel("<html><center>Keywords: (separated by commas) </center></html>");
				lblIssue.setFont(new Font("Rockwell", Font.PLAIN, 14));
				lblIssue.setBounds(10, 229, 253, 14);
				panel_4.add(lblIssue);
				
				lblTicket = new JLabel("Ticket #:");
				lblTicket.setFont(new Font("Rockwell", Font.PLAIN, 14));
				lblTicket.setBounds(10, 168, 65, 14);
				panel_4.add(lblTicket);
				
				cb_Assigned = new JComboBox<String>();
				cb_Assigned.setBackground(Color.LIGHT_GRAY);
				cb_Assigned.setFont(new Font("Rockwell", Font.PLAIN, 11));
				cb_Assigned.setBounds(10, 129, 253, 28);
				panel_4.add(cb_Assigned);
				
				UtilDateModel model = new UtilDateModel();
				Properties p = new Properties();
				p.put("text.today", "Today");
				p.put("text.month", "Month");
				p.put("text.year", "Year");
				datePanel = new JDatePanelImpl(model,p);
				Border empty = BorderFactory.createEmptyBorder(0, 0, 0, 0);
				Border dashed = BorderFactory.createDashedBorder(null, 5, 5);
				Border compound = new CompoundBorder(empty, dashed);
				panel_5 = new JPanel();
				panel_5.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
				panel_5.setBounds(10, 356, 251, 384);
				panel_4.add(panel_5);
				panel_5.setLayout(null);
				panel_5.setVisible(false);
				
				UtilDateModel model2 = new UtilDateModel();
				Properties p2 = new Properties();
				p2.put("text.today", "Today");
				p2.put("text.month", "Month");
				p2.put("text.year", "Year");
				datePanel = new JDatePanelImpl(model,p);
				
				datePanel2 = new JDatePanelImpl(model2,p2);
				
				lblorderBy = new JLabel("<html><center>Order Results:</center></html>");
				lblorderBy.setFont(new Font("Rockwell", Font.PLAIN, 14));
				lblorderBy.setBounds(5, 185, 191, 18);
				panel_5.add(lblorderBy);
				
				panel_6 = new JPanel();
				panel_6.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
				panel_6.setBounds(5, 25, 240, 157);
				panel_5.add(panel_6);
				panel_6.setLayout(null);
				datePicker2 = new JDatePickerImpl(datePanel2, new c_DateLabelFormatter());
				datePicker2.setBounds(5, 119, 193, 28);
				panel_6.add(datePicker2);
				datePicker2.getJFormattedTextField().setFont(new Font("Rockwell", Font.PLAIN, 11));
				datePicker2.getJFormattedTextField().setBackground(Color.WHITE);
				
				lblendDate = new JLabel("<html><center>End Date:</center></html>");
				lblendDate.setBounds(5, 100, 65, 18);
				panel_6.add(lblendDate);
				lblendDate.setFont(new Font("Rockwell", Font.PLAIN, 14));
				
				datePicker = new JDatePickerImpl(datePanel, new c_DateLabelFormatter());
				datePicker.setBounds(5, 66, 193, 28);
				panel_6.add(datePicker);
				datePicker.getJFormattedTextField().setFont(new Font("Rockwell", Font.PLAIN, 11));
				datePicker.getJFormattedTextField().setBackground(Color.WHITE);
				
				lblstartDate = new JLabel("<html><center>Start Date:</center></html>");
				lblstartDate.setBounds(5, 45, 65, 18);
				panel_6.add(lblstartDate);
				lblstartDate.setFont(new Font("Rockwell", Font.PLAIN, 14));
				
				datePicker.setVisible(false);							
				datePicker2.setVisible(false);
				lblendDate.setVisible(false);
				lblstartDate.setVisible(false);
				
				cb_DateRange = new JComboBox<String>();
				cb_DateRange.setBounds(3, 3, 231, 28);
				panel_6.add(cb_DateRange);
				cb_DateRange.setModel(new DefaultComboBoxModel<String>(new String[] {"All", "Entered Date", "Last Updated Date", "Entered & Last Updated Date"}));
				cb_DateRange.setFont(new Font("Rockwell", Font.PLAIN, 11));
				cb_DateRange.setBackground(Color.LIGHT_GRAY);
				cb_DateRange.addItemListener(new ItemListener() {
					public void itemStateChanged(ItemEvent e) {				
						if(cb_DateRange.getSelectedIndex() == 0)
						{
							
							datePicker.setVisible(false);							
							datePicker2.setVisible(false);
							lblendDate.setVisible(false);
							lblstartDate.setVisible(false);
						}
						else
						{
							
							datePicker.setVisible(true);
							datePicker2.setVisible(true);
							lblendDate.setVisible(true);
							lblstartDate.setVisible(true);
							
						}
					}
				});
				
				lbldateRange_1 = new JLabel("<html><center>Date Range: </center></html>");
				lbldateRange_1.setFont(new Font("Rockwell", Font.PLAIN, 14));
				lbldateRange_1.setBounds(10, 6, 231, 14);
				panel_5.add(lbldateRange_1);
				
				panel_7 = new JPanel();
				panel_7.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
				panel_7.setBounds(5, 205, 238, 157);
				panel_5.add(panel_7);
				panel_7.setLayout(null);
				
				cb_OrderByType3 = new JComboBox<String>();
				cb_OrderByType3.setBounds(160, 120, 65, 28);
				panel_7.add(cb_OrderByType3);
				cb_OrderByType3.setModel(new DefaultComboBoxModel(new String[] {"", "ASC", "DESC"}));
				cb_OrderByType3.setFont(new Font("Rockwell", Font.PLAIN, 11));
				cb_OrderByType3.setBackground(Color.LIGHT_GRAY);
				
				cb_OrderBy3 = new JComboBox<String>();
				cb_OrderBy3.setBounds(10, 120, 125, 28);
				panel_7.add(cb_OrderBy3);
				cb_OrderBy3.setFont(new Font("Rockwell", Font.PLAIN, 11));
				cb_OrderBy3.setBackground(Color.LIGHT_GRAY);
				
				cb_OrderByType2 = new JComboBox<String>();
				cb_OrderByType2.setBounds(160, 65, 65, 28);
				panel_7.add(cb_OrderByType2);
				cb_OrderByType2.setModel(new DefaultComboBoxModel(new String[] {"", "ASC", "DESC"}));
				cb_OrderByType2.setFont(new Font("Rockwell", Font.PLAIN, 11));
				cb_OrderByType2.setBackground(Color.LIGHT_GRAY);
				
				cb_OrderBy2 = new JComboBox<String>();
				cb_OrderBy2.setBounds(10, 65, 125, 28);
				panel_7.add(cb_OrderBy2);
				cb_OrderBy2.setFont(new Font("Rockwell", Font.PLAIN, 11));
				cb_OrderBy2.setBackground(Color.LIGHT_GRAY);
				
				lblandThenBy = new JLabel("<html><center>And Then By</center></html>");
				lblandThenBy.setBounds(10, 45, 118, 18);
				panel_7.add(lblandThenBy);
				lblandThenBy.setFont(new Font("Rockwell", Font.PLAIN, 14));
				
				label = new JLabel("<html><center>And Then By</center></html>");
				label.setBounds(10, 101, 118, 18);
				panel_7.add(label);
				label.setFont(new Font("Rockwell", Font.PLAIN, 14));
				
				cb_OrderByType1 = new JComboBox<String>();
				cb_OrderByType1.setBounds(160, 6, 65, 28);
				panel_7.add(cb_OrderByType1);
				cb_OrderByType1.setModel(new DefaultComboBoxModel(new String[] {"", "ASC", "DESC"}));
				cb_OrderByType1.setFont(new Font("Rockwell", Font.PLAIN, 11));
				cb_OrderByType1.setBackground(Color.LIGHT_GRAY);
				
				cb_OrderBy1 = new JComboBox<String>();
				cb_OrderBy1.setBounds(10, 6, 125, 28);
				panel_7.add(cb_OrderBy1);
				cb_OrderBy1.setFont(new Font("Rockwell", Font.PLAIN, 11));
				cb_OrderBy1.setBackground(Color.LIGHT_GRAY);
				
				datePicker2.getModel().setSelected(true);
				
				lbldateRange = new JCheckBox("<html><center>Advanced: </center></html>");
				lbldateRange.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(lbldateRange.isSelected())
						{
							panel_5.setVisible(true);
						}
						else
						{
							panel_5.setVisible(false);
						}
					}
				});
				lbldateRange.setBackground(new Color(255, 245, 238));
				lbldateRange.setFont(new Font("Rockwell", Font.PLAIN, 14));
				lbldateRange.setBounds(10, 335, 109, 14);
				panel_4.add(lbldateRange);
				
				cb_Category = new JComboBox<String>();
				cb_Category.setFont(new Font("Rockwell", Font.PLAIN, 11));
				cb_Category.setBackground(Color.LIGHT_GRAY);
				cb_Category.setBounds(10, 90, 253, 28);
				panel_4.add(cb_Category);
				
				panel_2 = new JPanel();
				panel_2.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
				panel_2.setBackground(Color.WHITE);
				panel_2.setBounds(0, 37, 273, 751);
				panel.add(panel_2);
				panel_2.setLayout(null);
				
				scrollPane_4 = new JScrollPane();
				scrollPane_4.setBounds(0, 0, 273, 751);
				panel_2.add(scrollPane_4);
				
				panel_3 = new JPanel();
				panel_3.setBounds(0, 0, 273, 38);
				panel.add(panel_3);
				panel_3.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
				panel_3.setBackground(Color.WHITE);
				panel_3.setLayout(null);
				
				btnFilter = new JButton("Filter");				
				btnFilter.setFont(new Font("Rockwell", Font.PLAIN, 11));
				btnFilter.setBackground(Color.LIGHT_GRAY);
				btnFilter.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						if(Filter_FirstRun)
						{
							Filter_FirstRun = false;
							PopulateClientCB();
						}
						
						if(panel_4.isVisible())
						{
							btnFilter.setText("Filter");
							panel_4.setVisible(false);		
							panel_2.setVisible(true);
							btnFilter.setBackground(Color.WHITE);
						}
						else
						{							
							btnFilter.setText("Hide");
							btnFilter.setBackground(Color.LIGHT_GRAY);
							panel_4.setVisible(true);
							panel_2.setVisible(false);
						}
					}
				});
				btnFilter.setIcon(new ImageIcon(g_ArchiveTickets.class.getResource("/filter.png")));
				btnFilter.setBounds(5, 5, 90, 30);
				panel_3.add(btnFilter);
				
				btnSearch_1 = new JButton("Search");
				btnSearch_1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Search();
					}
				});
				btnSearch_1.setIcon(new ImageIcon(g_ArchiveTickets.class.getResource("/magnifier.png")));
				btnSearch_1.setFont(new Font("Rockwell", Font.PLAIN, 11));
				btnSearch_1.setBackground(Color.LIGHT_GRAY);
				btnSearch_1.setBounds(171, 4, 95, 30);
				panel_3.add(btnSearch_1);
				
				JButton btnResetFilters = new JButton("");
				btnResetFilters.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						ClearFilters();
					}
				});
				btnResetFilters.setToolTipText("<html><center>Reset Filters </center></html>");
				btnResetFilters.setVerticalAlignment(SwingConstants.TOP);
				btnResetFilters.setBounds(241, 805, 42, 34);
				frmArchiveTickets.getContentPane().add(btnResetFilters);
				btnResetFilters.setIcon(new ImageIcon(g_ArchiveTickets.class.getResource("/settings.png")));
				btnResetFilters.setFont(new Font("Rockwell", Font.PLAIN, 11));
				btnResetFilters.setBackground(Color.LIGHT_GRAY);
				
				lblResults = new JLabel("<html><center>Search Results Found:</center></html>");
				lblResults.setFont(new Font("Rockwell", Font.PLAIN, 16));
				lblResults.setBounds(10, 805, 221, 20);
				frmArchiveTickets.getContentPane().add(lblResults);
				
				lblqueryExecutedIn = new JLabel("<html><center><i>Query Executed In:</i></center></html>");
				lblqueryExecutedIn.setFont(new Font("Rockwell", Font.PLAIN, 14));
				lblqueryExecutedIn.setBounds(10, 826, 221, 20);
				frmArchiveTickets.getContentPane().add(lblqueryExecutedIn);
				scrollPane_4.getViewport().setBackground(Color.WHITE);
				scrollPane_4.getViewport().setBorder(null);
				
			
				
				searchList.addListSelectionListener(new ListSelectionListener() {
					public void valueChanged(ListSelectionEvent arg0) {
						try{
							if(!arg0.getValueIsAdjusting())
							{			
								String html1 = "<html><div style='line-height:200%'> ";
								String html2 = "</div></html>";
								lbl_ClientSite.setText(archiveListarr.get(searchList.getSelectedIndex()).getClient() + " " + archiveListarr.get(searchList.getSelectedIndex()).getSite() + " (" + archiveListarr.get(searchList.getSelectedIndex()).getCategory() + ")" );
								lbl_Ticket.setText("(#" + archiveListarr.get(searchList.getSelectedIndex()).getTicket() + ")");
								lbl_Assigned.setText(archiveListarr.get(searchList.getSelectedIndex()).getAssigned());
								txt_Issue.setText(html1 + archiveListarr.get(searchList.getSelectedIndex()).getDescription() + html2);							
								txt_Update.setText(html1 + archiveListarr.get(searchList.getSelectedIndex()).getResolution() + html2);
								txt_Internal.setText(html1 + archiveListarr.get(searchList.getSelectedIndex()).getInternal() + html2);
								txt_TimeSpent.setText(archiveListarr.get(searchList.getSelectedIndex()).getTimeSpent());
								lbl_UpdateDate.setText(archiveListarr.get(searchList.getSelectedIndex()).getUpdateDate());								
								lblLastUpdatedByStr.setText(archiveListarr.get(searchList.getSelectedIndex()).getLastUpdatedBy());
								txtEnteredDate.setText(archiveListarr.get(searchList.getSelectedIndex()).getEnteredDate());
								txtStatus.setText(archiveListarr.get(searchList.getSelectedIndex()).getStatus());	
								
								if( archiveListarr.get(searchList.getSelectedIndex()).getInternal().compareTo("") != 0)
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
						}
						catch(Exception e)
						{
								
						}
					}
				});
				
				listModel.removeAllElements();
				searchList.setModel(listModel);
	}
	
	
	private void Search()
	{
		long startTime = System.currentTimeMillis();
		panel_4.setVisible(false);
		panel_2.setVisible(true);
		btnFilter.setText("Filter");
		listModel.clear();
		listModel.removeAllElements();		
		archiveListarr.removeAll(archiveListarr);
		
		String filters = "";		
		String client = cb_Client.getSelectedItem().toString();
		String site = "";
		String assigned = cb_Assigned.getSelectedItem().toString();
		String category = cb_Category.getSelectedItem().toString();
		String order = "";
		if(client.compareTo("All Clients") != 0)
		{
			filters = filters + " CLIENT LIKE '%" + cb_Client.getSelectedItem().toString() + "%'";	
		}
		
		if(cb_Site.isEnabled())
		{
										
			site = cb_Site.getSelectedItem().toString();
			if(site.compareTo("All " + client + " Sites") != 0)
			{
				if(filters.compareTo("") != 0)
				{
					filters = filters + " AND";
				}	
				filters = filters + " SITE LIKE '%" + site + "%'";
			}	
		}
		
		if(category.compareTo("All Categories") != 0)
		{
			if(filters.compareTo("") != 0)
			{
				filters = filters + " AND";
			}		
			
			filters = filters + " Category LIKE " + "'%" + category + "%'";			
		}
		
		if(assigned.compareTo("All Employees") != 0)
		{
			if(filters.compareTo("") != 0)
			{
				filters = filters + " AND";
			}		
			
			filters = filters + " ( Assigned LIKE " + "'%" + assigned + "%' OR LastUpdatedBy LIKE '%" + assigned + "%')";			
		}
		
		String tmpKeywords = txtKeywords.getText();
		tmpKeywords = tmpKeywords.trim();
		if(tmpKeywords.compareTo("") != 0)
		{
			if(filters.compareTo("") != 0)
			{
				filters = filters + " AND";
			}							
			String split[] = tmpKeywords.split(",");
			String tmpFilter  = " Resolution LIKE '%" + split[0].toString() + "%' OR Description LIKE '%" + split[0].toString() + "%' OR Internal LIKE '%" + split[0].toString() + "%'";
			for(int i = 1; i < split.length; i++)
			{				
				tmpFilter = tmpFilter + " OR Resolution LIKE '%" + split[i].toString() + "%' OR Description LIKE '%" + split[i].toString() + "%' OR Internal LIKE '%" + split[i].toString() + "%'";
			}
			
			tmpFilter = "(" + tmpFilter + ")";
			filters = "(" + filters + tmpFilter + ")";
			
		}
		
		if(txtTicketNumber.getText().compareTo("") != 0)
		{
			if(filters.compareTo("") != 0)
			{
				filters = filters + " AND";
			}		
			
			filters = filters + " Ticket LIKE '%" + txtTicketNumber.getText() + "%'";			
		}
		
		if(lbldateRange.isSelected())
		{
			if(cb_DateRange.getSelectedIndex() !=0)
			{
				
				int dateIndex = cb_DateRange.getSelectedIndex();				
				
				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");
				String startDate = datePicker.getJFormattedTextField().getText() + " " + LocalTime.now().format(dtf);				
				String endDate = datePicker2.getJFormattedTextField().getText() + " " + LocalTime.now().format(dtf);
				
				if(filters.compareTo("") != 0)
				{
					filters = filters + " AND";
				}	
								
				//Entered Date
				if(dateIndex == 0)
				{
					filters = filters + " EnteredDate >= '" + startDate + "' AND EnteredDate <= '" + endDate + "'";
				}
				//Update Date
				else if(dateIndex == 1)
				{
					filters = filters + " UpdateDate >= '" + startDate + "' AND UpdateDate <= '" + endDate + "'";
				}
				//Both
				else
				{
					filters = filters + " ((UpdateDate >= '" + startDate + "' AND UpdateDate <= '" + endDate + "') OR (EnteredDate >= '" + startDate + "' AND EnteredDate <= '" + endDate + "'))";
					
				}
			}
				
			if(cb_OrderBy1.getSelectedIndex() != 0)
			{
				String order1 = cb_OrderBy1.getSelectedItem().toString();
				String order1Type = "";
				if(cb_OrderByType1.getSelectedIndex() == 2)
				{
					order1Type = "DESC";
				}
				else
				{
					order1Type = "ASC";
				}
				
				order = order1 + " " + order1Type;
				if(cb_OrderBy2.getSelectedIndex() != 0)
				{
					String order2 = cb_OrderBy2.getSelectedItem().toString();
					String order2Type = "";
					if(cb_OrderByType2.getSelectedIndex() == 2)
					{
						order2Type = "DESC";
					}
					else
					{
						order2Type = "ASC";
					}					
					order = order + ", " + order2 + " " + order2Type;
					
					if(cb_OrderBy3.getSelectedIndex() != 0)
					{
						String order3 = cb_OrderBy3.getSelectedItem().toString();
						String order3Type = "";
						if(cb_OrderByType2.getSelectedIndex() == 2)
						{
							order3Type = "DESC";
						}
						else
						{
							order3Type = "ASC";
						}
						
						order = order + ", " + order3 + " " + order3Type;
					}
				}												
			}						
		}
		
		
		if(filters.compareTo("") != 0)
		{
			filters = "and " + filters;
		}
		
		if(order.compareTo("") == 0)
		{
			order = "Client asc, Site asc, EnteredDate desc";
		}
		
				
		
			String commandText = "SELECT Client, Site, Category, Ticket, CONVERT(varchar(17), EnteredDate, 113) as EnteredDateFormatted, Description, Assigned, Status, Resolution, Internal, Active, EmailSent, CONVERT(varchar(17), UpdateDate, 113) as UpdateDate,TimeSpent,CCNotified,LastUpdatedBy "
					+ "FROM SupportTickets WHERE Active = 0 " + filters + " ORDER BY " + order;
			
			ResultSet rs = c_Query.ExecuteResultSet(commandText);
			//System.out.println(commandText);
			
			try {
				while ((rs!=null) && (rs.next()))
				{				
					  c_Archive arch = new c_Archive();
					  arch.setClient(rs.getString("Client"));
					  arch.setSite(rs.getString("Site"));					  
					  arch.setCategory(rs.getString("Category"));
					  arch.setTicket(rs.getString("Ticket"));
					  arch.setEnteredDate(rs.getString("EnteredDateFormatted"));
					  arch.setDescription(rs.getString("Description"));
					  arch.setAssigned(rs.getString("Assigned"));
					  arch.setStatus(rs.getString("Status"));
					  arch.setResolution(rs.getString("Resolution"));					  
					  arch.setInternal(rs.getString("Internal"));
					  if(rs.wasNull())
					  {
						  arch.setInternal("");
					  }
					  arch.setActive(rs.getString("Active"));
					  arch.setEmailSent(rs.getString("EmailSent"));
					  arch.setUpdateDate(rs.getString("UpdateDate"));
					  arch.setTimeSpent(rs.getString("TimeSpent"));
					  arch.setCCNotified(rs.getString("CCNotified"));
					  arch.setLastUpdatedBy(rs.getString("LastUpdatedBy"));
					  archiveListarr.add(arch);
					  listModel.addElement(arch.getClient() + " " + arch.getSite() + " (" + arch.getTicket() + ")");	
				}			
				
				scrollPane_4.setViewportView(searchList);
	
				searchList.setModel(listModel);
				
				searchList.setSelectedIndex(0);
				
				lblResults.setText("<html><center>Search Results Found: " + Integer.toString(listModel.getSize()) + " </center></html>");
			
			}	
			
			catch (SQLException e)
			{
				
			}	
			

			long stopTime = System.currentTimeMillis();

			lblqueryExecutedIn.setText("<html><center><i>Query Executed In:  " + (stopTime - startTime) + " ms. </i></center></html>");
		
		
	}
	
	private void ReactivateTicket()
	{			 
				TicketNum = archiveListarr.get(searchList.getSelectedIndex()).getTicket();
			    String TicketUpdate = "UPDATE SupportTickets SET Active = 1, EmailSent = 0, Status = 'Investigating' WHERE Ticket =  '" + TicketNum + "'";
			    try
			    {
			    	c_Query.ExecuteQuery(TicketUpdate);			 
			    }
			    catch(Exception e)
			    {			    
			    	System.out.println(e.toString());
			    }			   
				    JOptionPane.showMessageDialog(frmArchiveTickets, "Ticket #" + TicketNum + " has been reactivated.");
				    Search();								
	}
	
	public void RefreshAll()
	{
		//PopulateActiveWindow();		
		Search();
	}
	
	
	private void PopulateSiteList(String site){
		cb_Site.removeAllItems();
		if((site.compareTo("") == 0) || (site.compareTo("All Clients") == 0)) {
			cb_Site.setEnabled(false);
		}
		else
		{
			cb_Site.setEnabled(true);
			cb_Site.addItem("All " + site +" Sites");
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
	
	private void PopulateClientCB()
	{
			cb_Client.addItem("All Clients");
		 	String commandText = "SELECT DISTINCT Client FROM Sites ORDER BY Client Asc";        
	        ResultSet rs = c_Query.ExecuteResultSet(commandText);
	        	     
	        try {
				while((rs!=null) && (rs.next()))
				{							
					cb_Client.addItem(rs.getString("Client"));	
				}
			} catch (SQLException e) {
			}
	        
	        commandText = "SELECT DISTINCT Name FROM EN_Employees ORDER BY Name Asc";        
	        rs = c_Query.ExecuteResultSet(commandText);
	        cb_Assigned.addItem("All Employees");
	        try {
				while((rs!=null) && (rs.next()))
				{				
					cb_Assigned.addItem(rs.getString("Name"));	
				}
			} catch (SQLException e) {
			}
	        
	        commandText = "SELECT DISTINCT Category FROM SupportTickets ORDER BY Category Asc";        
	        rs = c_Query.ExecuteResultSet(commandText);
	        cb_Category.addItem("All Categories");
	        try {
				while((rs!=null) && (rs.next()))
				{				
					cb_Category.addItem(rs.getString("Category"));	
				}
			} catch (SQLException e) {
			}
	        
	        commandText = "Select TOP(1) * FROM SupportTickets";
	        rs = c_Query.ExecuteResultSet(commandText);
	        cb_OrderBy1.addItem("");
   		 	cb_OrderBy2.addItem("");
   		 	cb_OrderBy3.addItem(""); 
	        try {
	        	ResultSetMetaData rsmd = rs.getMetaData();	     
	        	 for (int i = 2; i <= rsmd.getColumnCount(); i++)
	        	 {	        		 
	        		 cb_OrderBy1.addItem(rsmd.getColumnName(i));
	        		 cb_OrderBy2.addItem(rsmd.getColumnName(i));
	        		 cb_OrderBy3.addItem(rsmd.getColumnName(i));
	        	 }
			} catch (SQLException e) {
			}
	       
	       
	        try {
				rs.close();
			} catch (SQLException e) {
			
			}	       
	}
	
	private void ClearFilters()
	{		
		cb_Category.setSelectedIndex(0);
		cb_Client.setSelectedIndex(0);				
		cb_OrderBy1.setSelectedIndex(0);
		cb_OrderByType1.setSelectedIndex(0);
		cb_OrderBy2.setSelectedIndex(0);
		cb_OrderByType2.setSelectedIndex(0);
		cb_OrderBy3.setSelectedIndex(0);
		cb_OrderByType3.setSelectedIndex(0);
		txtTicketNumber.setText("");
		txtKeywords.setText("");
		//lbldateRange.setSelected(false);
		cb_DateRange.setSelectedIndex(0);
		
	}
}

