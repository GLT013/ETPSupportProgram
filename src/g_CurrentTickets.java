import it.cnr.imaa.essi.lablib.gui.checkboxtree.CheckboxTree;
import it.cnr.imaa.essi.lablib.gui.checkboxtree.TreeCheckingModel;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;
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
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ImageIcon;
import javax.swing.ListSelectionModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ScrollPaneConstants;
import javax.swing.JCheckBox;
import java.awt.Toolkit;
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
	JTextArea txt_Issue;
	JTextArea txt_Update;
	private JTextArea txt_Internal;
	private JLabel lblHours;
	private JTextField txt_TimeSpent;
	private JLabel lblDateLastUpdated;
	private JLabel lbl_UpdateDate;
	public static JList<c_Files> JList_FileList;
	public static DefaultListModel<c_Files> fileListModel;
	public static DefaultListModel<c_Files> ExistingfileListModel;
	private List<String> ViewSiteData = new ArrayList<String>();
	private JPanel panel_2;
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
	private String directory = "\\\\10.10.38.252\\C$\\SupportProgram\\Files\\";
	private int numFiles; 
	private int viewIP;
	private int SQLIP; 
	private int DevIP; 
	private int iDracIP;
	private int hostIP; 
	private boolean highperformance;
	private static JCheckBox chckbxCcNotified;
	private static String CCNotified;
	private static boolean CCNotifiedState = false;
	

	

	public static void run() {				
		try {
			g_CurrentTickets window = new g_CurrentTickets();
			window.frmCurrentTickets.setVisible(true);
			window.frmCurrentTickets.setLocationRelativeTo( g_MainMenu.frmMainMenu );
		} catch (Exception e) {
			e.printStackTrace();
		}
	}



	/**
	 * Create the application.
	 */
	public g_CurrentTickets() {
		initialize();		
		PopulateActiveTickets();
		PopulateRecentTickets();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmCurrentTickets = new JFrame();
		frmCurrentTickets.setIconImage(Toolkit.getDefaultToolkit().getImage(g_CurrentTickets.class.getResource("/icon.png")));
		
		if(g_MainMenu.offlineMode)
		{
			frmCurrentTickets.setTitle("Automated Support Program - OFFLINE");	
		}
		else
		{
			frmCurrentTickets.setTitle("Automated Support Program");
		}
		frmCurrentTickets.getContentPane().setBackground(Color.LIGHT_GRAY);
		frmCurrentTickets.setBounds(100, 100, 982, 895);
		frmCurrentTickets.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCurrentTickets.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 244, 372);
		frmCurrentTickets.getContentPane().add(panel);
		panel.setLayout(null);
		
		tree_active = new CheckboxTree();
		tree_active.getCheckingModel().setCheckingMode(TreeCheckingModel.CheckingMode.PROPAGATE_PRESERVING_CHECK);
		tree_active.setBackground(Color.WHITE);
		tree_active.setBounds(0, 0, 244, 379);
		tree_active.setBorder(UIManager.getBorder("DesktopIcon.border"));
		panel.add(tree_active);
		tree_active.addTreeSelectionListener(new TreeSelectionListener() {
			public void valueChanged(TreeSelectionEvent e) {
				PopulateActiveWindow();
			}
		});
	

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(264, 11, 668, 725);
		frmCurrentTickets.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		lbl_ClientSite = new JLabel("Client Site (ID)");
		lbl_ClientSite.setBounds(10, 11, 268, 20);
		lbl_ClientSite.setFont(new Font("Plantagenet Cherokee", Font.BOLD, 16));
		panel_1.add(lbl_ClientSite);
		
		lbl_Ticket = new JLabel("(Ticket #)");
		lbl_Ticket.setBounds(572, 11, 86, 20);
		lbl_Ticket.setFont(new Font("Plantagenet Cherokee", Font.BOLD, 16));
		panel_1.add(lbl_Ticket);
		
		lbl_Assigned = new JLabel("");
		lbl_Assigned.setFont(new Font("Plantagenet Cherokee", Font.BOLD, 16));
		lbl_Assigned.setBounds(25, 687, 134, 20);
		panel_1.add(lbl_Assigned);
		
		btnSave = new JButton("Update");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (cb_Status.getSelectedItem().toString().compareTo("Complete") == 0)
				{					
					CloseTicket();
				}
				else
				{
					UpdateTicket();
				}

				
			}
		});
		btnSave.setBounds(569, 696, 89, 23);
		panel_1.add(btnSave);
		
		cb_Status = new JComboBox<String>();
		cb_Status.setModel(new DefaultComboBoxModel<String>(new String[] {"Investigating", "Ongoing", "Ongoing - Need Field Support", "Complete"}));
		cb_Status.setBounds(266, 693, 191, 28);
		panel_1.add(cb_Status);
		
		ExistingfileListModel = new DefaultListModel<c_Files>();
		
		lbl_Problem = new JLabel("Issue");
		lbl_Problem.setFont(new Font("Plantagenet Cherokee", Font.BOLD, 16));
		lbl_Problem.setBounds(10, 77, 220, 20);
		panel_1.add(lbl_Problem);
		
		panel_Internal = new JPanel();
		panel_Internal.setBounds(1000, 322, 648, 324);
		panel_1.add(panel_Internal);
		panel_Internal.setLayout(null);
		panel_Internal.setVisible(false);
		
		JButton btnFileUpload = new JButton("File Upload..");
		btnFileUpload.setBounds(0, 205, 105, 23);
		panel_Internal.add(btnFileUpload);
		
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
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 239, 328, 74);
		panel_Internal.add(scrollPane_1);
		
		JList_FileList = new JList<c_Files>();
		JList_FileList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane_1.setViewportView(JList_FileList);
		
		JButton btn_OpenFile = new JButton("Open File");
		btn_OpenFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (JList_FileList.getSelectedIndex() == -1)
				{
					JOptionPane.showMessageDialog(null, "No file was selected.");
				}
				else if(JList_FileList.getSelectedIndex() > (numFiles-1))
				{
					JOptionPane.showMessageDialog(null, "File not found. \n Please verify file has been uploaded by hitting the update button.");
				}
				else
				{
					String source = fileListModel.getElementAt(JList_FileList.getSelectedIndex()).getFile();
					String filePath = directory + TicketNum + "_" + source;
					try {						
						Runtime.getRuntime().exec(new String[] {"cmd.exe", "/C", filePath});
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						JOptionPane.showMessageDialog(null, "Could not find file.");
					}
				}
			}
			
		});
		btn_OpenFile.setBounds(358, 237, 105, 23);
		panel_Internal.add(btn_OpenFile);
		
		btn_DeleteFile = new JButton("Delete File");
		btn_DeleteFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (JList_FileList.getSelectedIndex() == -1)
				{
					JOptionPane.showMessageDialog(null, "No file was selected.");
				}
				else if(JList_FileList.getSelectedIndex() > (numFiles-1))
				{
					
				}
				else
				{
					int index = JList_FileList.getSelectedIndex();
					String source = fileListModel.getElementAt(index).getFile();
					String filePath = directory + TicketNum + "_" + source;
					int reply = JOptionPane.showConfirmDialog(null, "Are you sure you wish to delete " + source + "?" , "Delete File", JOptionPane.YES_NO_OPTION);
			        if (reply == JOptionPane.YES_OPTION)
			        {
			        	String commandText = "DELETE FROM Files WHERE Filename = '" + source + "'";
			        	c_Query.ExecuteQuery(commandText);
				        fileListModel.remove(index);
				        File file = new File(filePath);
				        if(file.delete()){
				        	JOptionPane.showMessageDialog(null, source + " has been deleted.");
			    		}else{
			    			JOptionPane.showMessageDialog(null, "Error deleting file from hard drive");
			    		}

				        JList_FileList.removeAll();
				        JList_FileList.setModel(fileListModel);
				        JList_FileList.revalidate();
				        JList_FileList.repaint();
			        }
			        else
			        {
			        	JOptionPane.showMessageDialog(null, "Whew! That was close.");
			        }
				}
				
			}
		});
		btn_DeleteFile.setBounds(358, 271, 105, 23);
		panel_Internal.add(btn_DeleteFile);
		
		scrollPane_2 = new JScrollPane();
		scrollPane_2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane_2.setBounds(0, 27, 648, 176);
		panel_Internal.add(scrollPane_2);
		
		txt_Internal = new JTextArea();
		scrollPane_2.setViewportView(txt_Internal);
		txt_Internal.setLineWrap(true);
		txt_Internal.setWrapStyleWord(true);
		btnFileUpload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				
		        int returnValue = fileChooser.showOpenDialog(null);
		        if (returnValue == JFileChooser.APPROVE_OPTION) {
		          File selectedFile = fileChooser.getSelectedFile();  
				File inputFile = new File(
						selectedFile.getAbsoluteFile().getPath().toString());				
					AddFilesToList(selectedFile.getName(),inputFile);
				}
		        
			}
		});
		
		panel_Update = new JPanel();
		panel_Update.setBounds(10, 322, 648, 324);
		panel_1.add(panel_Update);
		panel_Update.setLayout(null);
		
		lbl_Update = new JLabel("Update");
		lbl_Update.setBounds(0, 0, 220, 20);
		panel_Update.add(lbl_Update);
		lbl_Update.setFont(new Font("Plantagenet Cherokee", Font.BOLD, 16));
		
		lblHours = new JLabel("Time Spent");
		lblHours.setBounds(0, 259, 156, 20);
		panel_Update.add(lblHours);
		lblHours.setFont(new Font("Plantagenet Cherokee", Font.BOLD, 16));
		
		txt_TimeSpent = new JTextField();
		txt_TimeSpent.setBounds(10, 283, 58, 25);
		panel_Update.add(txt_TimeSpent);
		txt_TimeSpent.setText("0.5");
		txt_TimeSpent.setToolTipText("Time Spent in Hours");
		txt_TimeSpent.setColumns(10);
		
		lblDateLastUpdated = new JLabel("Last Updated:");
		lblDateLastUpdated.setBounds(474, 255, 164, 20);
		panel_Update.add(lblDateLastUpdated);
		lblDateLastUpdated.setFont(new Font("Times New Roman", Font.BOLD, 16));
		
		lbl_UpdateDate = new JLabel("Date");
		lbl_UpdateDate.setBounds(474, 284, 164, 20);
		panel_Update.add(lbl_UpdateDate);
		lbl_UpdateDate.setFont(new Font("Plantagenet Cherokee", Font.BOLD, 16));
		
		scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(0, 28, 648, 216);
		panel_Update.add(scrollPane);
		
		txt_Update = new JTextArea();
		scrollPane.setViewportView(txt_Update);
		//txt_Update.setAutoscrolls(false);
		txt_Update.setLineWrap(true);
		txt_Update.setWrapStyleWord(true);
		
		
		btn_Internal = new JButton("Internal");
		btn_Internal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel_Update.setVisible(false);
				panel_Internal.setVisible(true);
				
			}
		});
		btn_Internal.setBounds(559, 0, 89, 23);
		panel_Update.add(btn_Internal);
		
		JLabel lblhours = new JLabel("Hours");
		lblhours.setFont(new Font("Plantagenet Cherokee", Font.PLAIN, 14));
		lblhours.setBounds(75, 284, 156, 20);
		panel_Update.add(lblhours);
		
		JButton btn_SiteData = new JButton("Site Data");
		btn_SiteData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//ViewSiteData();
				g_ViewSiteData.run(siteID);
			}
		});
		btn_SiteData.setBounds(10, 43, 105, 23);
		panel_1.add(btn_SiteData);
		
		lblAssignedTo = new JLabel("Assigned To:");
		lblAssignedTo.setFont(new Font("Plantagenet Cherokee", Font.BOLD, 16));
		lblAssignedTo.setBounds(25, 665, 134, 20);
		panel_1.add(lblAssignedTo);
		
		cb_Assigned = new JComboBox<String>();
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
		
		chckbxCcNotified = new JCheckBox("CC Notified");
		chckbxCcNotified.setBounds(466, 696, 97, 23);
		panel_1.add(chckbxCcNotified);
		
		JLabel lblOffline = new JLabel("Offline!");
		lblOffline.setForeground(Color.RED);
		lblOffline.setFont(new Font("Plantagenet Cherokee", Font.BOLD, 16));
		lblOffline.setBounds(294, 11, 97, 20);
		panel_1.add(lblOffline);
		lblOffline.setVisible(false);
		if(g_MainMenu.offlineMode)
		{
			lblOffline.setVisible(true);
		}
			
		
		panel_2 = new JPanel();
		panel_2.setBounds(264, 747, 668, 54);
		frmCurrentTickets.getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		JButton btnNewButton = new JButton("Generate Email");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {				
				GetCheckboxes();
			}
		});
		btnNewButton.setBounds(530, 11, 128, 23);
		panel_2.add(btnNewButton);
		
		JLabel icon_view = new JLabel("");		
		icon_view.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
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
					String ipAddress = "10.219." + siteID + "." + viewIP; 
					try {
						Runtime.getRuntime().exec(new String[] {"cmd.exe", "/C", "mstsc /v:"+ipAddress + " /w:" + width + " /h:" + height});
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			
		});
		icon_view.setToolTipText("View");
		icon_view.setIcon(new ImageIcon(g_CurrentTickets.class.getResource("/view.png")));
		icon_view.setBounds(30, 1, 32, 32);
		panel_2.add(icon_view);
		
		JLabel lblNewLabel_1 = new JLabel("View");
		lblNewLabel_1.setBounds(33, 32, 32, 14);
		panel_2.add(lblNewLabel_1);
		
		JLabel icon_sql = new JLabel("");
		icon_sql.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String width = "1920";
				String height = "1080";			
					String ipAddress = "10.219." + siteID + "." + SQLIP; 
					try {
						Runtime.getRuntime().exec(new String[] {"cmd.exe", "/C", "mstsc /v:"+ipAddress + " /w:" + width + " /h:" + height});
					} catch (IOException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
				}
			
		});
		icon_sql.setIcon(new ImageIcon(g_CurrentTickets.class.getResource("/sql.png")));
		icon_sql.setToolTipText("SQL");
		icon_sql.setBounds(90, 1, 32, 32);
		panel_2.add(icon_sql);
		
		JLabel icon_dev = new JLabel("");
		icon_dev.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String width = "1920";
				String height = "1080";	
				String ipAddress = "10.219." + siteID + "." + DevIP; 
					try {
						Runtime.getRuntime().exec(new String[] {"cmd.exe", "/C", "mstsc /v:"+ipAddress + " /w:" + width + " /h:" + height});
					} catch (IOException e3) {
						// TODO Auto-generated catch block
						e3.printStackTrace();
					}
				
			}
		});
		icon_dev.setIcon(new ImageIcon(g_CurrentTickets.class.getResource("/dev.png")));
		icon_dev.setToolTipText("Dev");
		icon_dev.setBounds(150, 1, 32, 32);
		panel_2.add(icon_dev);
		
		JLabel lblSql = new JLabel("SQL");
		lblSql.setBounds(96, 32, 32, 14);
		panel_2.add(lblSql);
		
		JLabel lblDev = new JLabel("Dev");
		lblDev.setBounds(157, 32, 32, 14);
		panel_2.add(lblDev);
		
		JLabel icon_idrac = new JLabel("");
		icon_idrac.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String ipAddress = "10.219." + siteID + "." + iDracIP; 
				try {					
					Runtime.getRuntime().exec("C:\\Program Files\\Internet Explorer\\iexplore.exe " + ipAddress);
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					System.out.println(e2.toString());
				}
			
			}
		});
		icon_idrac.setIcon(new ImageIcon(g_CurrentTickets.class.getResource("/idrac.png")));
		icon_idrac.setToolTipText("iDrac");
		icon_idrac.setBounds(210, 3, 32, 32);
		panel_2.add(icon_idrac);
		
		JLabel icon_host = new JLabel("");
		icon_host.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String ip = "10.219." + siteID + "." + hostIP; 
				JOptionPane.showMessageDialog(null, "VSphere Host IP: \n " + ip);
			}
		});
		icon_host.setIcon(new ImageIcon(g_CurrentTickets.class.getResource("/host.png")));
		icon_host.setToolTipText("Host");
		icon_host.setBounds(270, 3, 32, 32);
		panel_2.add(icon_host);
		
		JLabel lblIdrac = new JLabel("iDrac");
		lblIdrac.setBounds(214, 34, 32, 14);
		panel_2.add(lblIdrac);
		
		JLabel lblHost = new JLabel("Host");
		lblHost.setBounds(275, 34, 32, 14);
		panel_2.add(lblHost);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(10, 393, 244, 408);
		frmCurrentTickets.getContentPane().add(panel_3);
		
		
		tree_closed = new CheckboxTree();
		tree_closed.setBounds(0, 0, 244, 408);
		tree_closed.getCheckingModel().setCheckingMode(TreeCheckingModel.CheckingMode.PROPAGATE_PRESERVING_CHECK);
		panel_3.setLayout(null);
		tree_closed.setBackground(Color.WHITE);
		tree_closed.setBorder(UIManager.getBorder("DesktopIcon.border"));
		panel_3.add(tree_closed);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				g_MainMenu.run(frmCurrentTickets);
				frmCurrentTickets.dispose();
			}
		});
		btnBack.setBounds(10, 812, 89, 23);
		frmCurrentTickets.getContentPane().add(btnBack);
		
		JLabel btnRefresh = new JLabel("");
		btnRefresh.setBounds(222, 803, 32, 32);
		frmCurrentTickets.getContentPane().add(btnRefresh);
		btnRefresh.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				PopulateActiveWindow();
				PopulateActiveTickets();
				PopulateRecentTickets();				
			}
		});
		btnRefresh.setIcon(new ImageIcon(g_CurrentTickets.class.getResource("/reload.png")));
		btnRefresh.setToolTipText("Refresh");
		tree_closed.addTreeSelectionListener(new TreeSelectionListener() {
			public void valueChanged(TreeSelectionEvent e) {
				PopulateRecentWindow();
			}
		});
	}
	
	int tmp2[];
	private JLabel lblAssignedTo;
	private JButton btn_DeleteFile;
	private JScrollPane scrollPane_2;
	private JScrollPane scrollPane_3;
	
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
	

	private void PopulateActiveWindow()
	{		
		cb_Assigned.setVisible(false);
		lbl_Assigned.setVisible(true);
		btnEdit.setVisible(true);
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
		
		if(!g_MainMenu.offlineMode)
		{
		 commandText = "SELECT a.Client, a.Site, a.SiteID, a.HostIP, a.ViewIP, a.SQLIP, a.DevIP, a.iDracIP, a.HighPerformance, b.Category, b.Ticket, b.Description, b.Internal, b.Assigned, b.Status, CONVERT(varchar(17), b.UpdateDate, 113) as UpdateDate, CONVERT(varchar(17), b.CCNotified, 113) as CCNotified, b.Resolution, b.rowID "
							+ "FROM Sites a, SupportTickets b WHERE a.Client = b.Client and a.Site = b.Site and Ticket = '" + TicketNum + "'";
		}
		else
		{
			commandText = "SELECT a.Client, a.Site, a.SiteID, a.HostIP, a.ViewIP, a.SQLIP, a.DevIP, a.iDracIP, a.HighPerformance, b.Category, b.Ticket, b.Description, b.Internal, b.Assigned, b.Status, b.UpdateDate, b.CCNotified, b.Resolution, b.rowID "
					+ "FROM Sites a, SupportTickets b WHERE a.Client = b.Client and a.Site = b.Site and Ticket = '" + TicketNum + "'";
		}
		ResultSet rs = c_Query.ExecuteResultSet(commandText);
		
		try
		{
			while((rs!=null) && (rs.next()))
			{
				
				lbl_ClientSite.setText(rs.getString("Client") + " " + rs.getString("Site") + " (" + rs.getInt("SiteID") + ")");
				siteID = rs.getString("SiteID");
				viewIP = rs.getInt("ViewIP");
				SQLIP = rs.getInt("SQLIP");
				DevIP = rs.getInt("DevIP");
				iDracIP = rs.getInt("iDracIP");
				hostIP = rs.getInt("HostIP");
				highperformance = rs.getBoolean("HighPerformance");

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
				else if(Status.compareTo("Complete") == 0)
				{
					cb_Status.setSelectedIndex(3); 	
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
				txt_Internal.setText(rs.getString("Internal"));
				lbl_UpdateDate.setText(rs.getString("UpdateDate"));
				
				
				rowID = rs.getInt("rowID");

				tree_closed.clearSelection();
			}
		}
		catch(Exception e)
		{
			System.out.println(e.toString());
		}
		fileListModel = new DefaultListModel<c_Files>();
		
		commandText = "SELECT Filename FROM Files WHERE TicketNum = '" + TicketNum + "'";		
		rs = c_Query.ExecuteResultSet(commandText);
		
		try
		{

			fileListModel.clear();
		while((rs!=null) && (rs.next()))
		{
			c_Files fileName = new c_Files(rs.getString("Filename"));
			fileListModel.addElement(fileName);
		}
		}
		catch(Exception e)
		{
			//do nothing
		}
		
		numFiles = fileListModel.getSize();
		JList_FileList.setModel(fileListModel);
	}
	
	private void ChangeAssignment()
	{
		btnEdit.setVisible(false);
		lbl_Assigned.setVisible(false);
		cb_Assigned.setVisible(true);
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
		if(!g_MainMenu.offlineMode)
		{
		 commandText = "SELECT a.Client, a.Site, a.SiteID, a.HostIP, a.ViewIP, a.SQLIP, a.DevIP, a.iDracIP, a.HighPerformance, b.Category, b.Ticket, b.Description, b.Internal, b.Assigned, b.Status, CONVERT(varchar(17), b.UpdateDate, 113) as UpdateDate, CONVERT(varchar(17), b.CCNotified, 113) as CCNotified, b.Resolution, b.rowID "
							+ "FROM Sites a, SupportTickets b WHERE a.Client = b.Client and a.Site = b.Site and Ticket = '" + TicketNum + "'";
		}
		else
		{
			commandText = "SELECT a.Client, a.Site, a.SiteID, a.HostIP, a.ViewIP, a.SQLIP, a.DevIP, a.iDracIP, a.HighPerformance, b.Category, b.Ticket, b.Description, b.Internal, b.Assigned, b.Status, b.UpdateDate, b.CCNotified, b.Resolution, b.rowID "
					+ "FROM Sites a, SupportTickets b WHERE a.Client = b.Client and a.Site = b.Site and Ticket = '" + TicketNum + "'";
		}
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
				highperformance = rs.getBoolean("HighPerformance");
				ViewSiteData.add("(#" + rs.getString("Ticket") + ")"); //Ticket # Index 1
				ViewSiteData.add(Integer.toString(hostIP)); //Host, Index 2
				ViewSiteData.add(Integer.toString(viewIP)); //View, Index 3
				ViewSiteData.add(Integer.toString(SQLIP)); //SQL, Index 4
				ViewSiteData.add(Integer.toString(DevIP)); //Dev, Index 5
				ViewSiteData.add(Integer.toString(iDracIP)); //iDrac, Index 6				
				lbl_Assigned.setText(rs.getString("Assigned"));
				if(rs.getString("Status").compareTo("Complete") == 0)
				{
					cb_Status.setSelectedIndex(3); 	
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
				rowID = rs.getInt("rowID");
				
			}
		}
		catch(Exception e)
		{
			//do nothing
			System.out.println(e.toString());
		}
		cb_Status.setEnabled(false);
		fileListModel = new DefaultListModel<c_Files>();
		commandText = "SELECT Filename FROM Files WHERE TicketNum = '" + TicketNum + "'";		
		rs = c_Query.ExecuteResultSet(commandText);
		
		try
		{

			fileListModel.clear();
		while((rs!=null) && (rs.next()))
		{
			c_Files fileName = new c_Files(rs.getString("Filename"));
			fileListModel.addElement(fileName);
		}
		}
		catch(Exception e)
		{
			//do nothing
		}
		
		numFiles = fileListModel.getSize();
		JList_FileList.setModel(fileListModel);
		
		tree_active.clearSelection();
	
	}
	
	private void PopulateActiveTickets()
	{
		try {
			
			tree_active.setModel(new DefaultTreeModel(
					new DefaultMutableTreeNode("Active Tickets") {
						private static final long serialVersionUID = 1L;
						{
							DefaultMutableTreeNode proj;
							
							DefaultMutableTreeNode projnum;
							String commandText = "SELECT DISTINCT Client from SupportTickets WHERE Status != 'Complete' ORDER BY Client ASC";
							ResultSet rs = c_Query.ExecuteResultSet(commandText);
							
							while((rs!=null) && (rs.next()))
							{
								String projectname = rs.getString(1);
								proj = new DefaultMutableTreeNode(projectname);
									
									String commandText2 = "SELECT Site, Ticket, EmailSent from SupportTickets WHERE Active = 1 and Client ='" + projectname + "'";
									
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
							if(!g_MainMenu.offlineMode)
							{
								commandText = "SELECT DISTINCT Client from SupportTickets WHERE Status = 'Complete' and UpdateDate >= DATEADD(day,-1,GETDATE()) and EmailSent = 'False' ORDER BY Client ASC";
							}
							else
							{
								commandText = "SELECT DISTINCT Client from SupportTickets WHERE Status = 'Complete' and UpdateDate >= date('now','+1 day') and EmailSent = 'False' ORDER BY Client ASC";
							}
							ResultSet rs = c_Query.ExecuteResultSet(commandText);
							
							while((rs!=null) && (rs.next()))
							{
								String projectname = rs.getString(1);
								proj = new DefaultMutableTreeNode(projectname);	
									String commandText2 = "";
									if(!g_MainMenu.offlineMode)
									{
										commandText2 = "SELECT Site, Ticket from SupportTickets WHERE Status = 'Complete' and UpdateDate >= DATEADD(day,-1,GETDATE()) and EmailSent = 'False' and Client ='" + projectname + "'"; ;
									}
									else
									{
										commandText2 = "SELECT Site, Ticket from SupportTickets WHERE Status = 'Complete' and UpdateDate >= date('now','+1 day') and EmailSent = 'False' and Client ='" + projectname + "'"; ;
									}
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

	 private void AddFilesToList(String filename, File source)
	 {		 
		 c_Files addFile = new c_Files(filename,source);
		 ExistingfileListModel.addElement(addFile);
		 fileListModel.addElement(addFile);
	 }
	 
	 
	 public void CloseTicket()
	 {		 
		 DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
		 Date date = new Date();
		 String testDate = dateFormat.format(date);		 
		 String resolution = txt_Update.getText();
		 resolution = c_CleanString.Clean_String(resolution);
		 String commandText = "";
		 if(!CCNotifiedState && chckbxCcNotified.isSelected()) //If it wasn't originally selected and now is we need to stamp the time.
		 {
			
			 Date CCdate = new Date();
			 String ccDateString = dateFormat.format(CCdate);		 
			 commandText = "UPDATE SupportTickets SET Status = '" + cb_Status.getSelectedItem().toString() 
					 + "', Resolution = '" + resolution + "', TimeSpent = '" +  txt_TimeSpent.getText() 
					 + "', UpdateDate = '" + testDate + "', Active = 0, EmailSent = 'False', CCNotified = '" + ccDateString + "' WHERE rowID = " + rowID;	
			 
		 }
		 else
		 {
			 commandText = "UPDATE SupportTickets SET Status = '" + cb_Status.getSelectedItem().toString() 
					 + "', Resolution = '" + resolution + "', TimeSpent = '" +  txt_TimeSpent.getText() 
					 + "', UpdateDate = '" + testDate + "', Active = 0, EmailSent = 'False' WHERE rowID = " + rowID;	
		 }
		 
		 		
		 c_Query.UpdateResultSet(commandText);
		 commandText = "";
			
		 	
			
			/* Check if there is any files to upload from the internal window */
			if (!fileListModel.isEmpty()) {
				for(int i = 0; i < fileListModel.size(); i++)
				{
					File inputFile = new File(
							fileListModel.get(i).getFileSource());
					File outputFile = new File( 
							directory + TicketNum + "_" + fileListModel.get(i).toString());
										
					try {
						copyFileUsingStream(inputFile, outputFile);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				for(int i = 0; i < fileListModel.size(); i++)
				{
					commandText = commandText + " INSERT INTO Files (TicketNum, Filename, UploadDate) VALUES ('" + TicketNum + "','" + fileListModel.get(i).getFile() + "','" + testDate +"')";
				}				
				c_Query.UpdateResultSet(commandText);
			}
			
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
		 if(!g_MainMenu.offlineMode)
		 {			 
			 Date date = new Date();
			 testDate = dateFormat.format(date);	
		 }
		 else
		 {						
			 Timestamp currentTimestamp = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
			 testDate = dateFormat.format(currentTimestamp);	
		 }
		 	 
		 String commandText = "";
		 String resolution = txt_Update.getText();
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
		 
		 if(cb_Assigned.isVisible() == true && CCCompilation == true)
		 {
			 Date CCdate = new Date();
			 String ccDateTime = dateFormat.format(CCdate);
			 String assigned = cb_Assigned.getSelectedItem().toString();
			 commandText = "UPDATE SupportTickets SET Assigned = '" + assigned +"', Status = '" + cb_Status.getSelectedItem().toString() + "', Resolution = '" + resolution + "', TimeSpent = '" +  txt_TimeSpent.getText() + "', UpdateDate = '" + testDate + "', Active = 1, EmailSent = 'False', CCNotified = '" + ccDateTime + "' WHERE rowID = " + rowID;
		 }
		 else if(cb_Assigned.isVisible() == true && CCCompilation == false)
		 {
			 String assigned = cb_Assigned.getSelectedItem().toString();
			 commandText = "UPDATE SupportTickets SET Assigned = '" + assigned +"', Status = '" + cb_Status.getSelectedItem().toString() + "', Resolution = '" + resolution + "', TimeSpent = '" +  txt_TimeSpent.getText() + "', UpdateDate = '" + testDate + "', Active = 1, EmailSent = 'False' WHERE rowID = " + rowID;
		 }
		 else if(cb_Assigned.isVisible() == false && CCCompilation == true)
		 {
			 Date CCdate = new Date();
			 String ccDateTime = dateFormat.format(CCdate);
			 commandText = "UPDATE SupportTickets SET Status = '" + cb_Status.getSelectedItem().toString() + "', Resolution = '" + resolution + "', TimeSpent = '" +  txt_TimeSpent.getText() + "', UpdateDate = '" + testDate + "', Active = 1, EmailSent = 'False', CCNotified = '" + ccDateTime + "' WHERE rowID = " + rowID;
		 }
		 else
		 {
			commandText = "UPDATE SupportTickets SET Status = '" + cb_Status.getSelectedItem().toString() + "', Resolution = '" + resolution + "', TimeSpent = '" +  txt_TimeSpent.getText() + "', UpdateDate = '" + testDate + "', Active = 1, EmailSent = 'False' WHERE rowID = " + rowID;
		 }
		 
			c_Query.UpdateResultSet(commandText);

			commandText = "";
			
			
			/* Check if there is any files to upload from the internal window */			
			if (!ExistingfileListModel.isEmpty()) {
				
				for(int i = 0; i < ExistingfileListModel.size(); i++)
				{
					
					File inputFile = new File(
							ExistingfileListModel.get(i).getFileSource());
					File outputFile = new File(
							directory + TicketNum + "_" + ExistingfileListModel.get(i).toString());
										
					try {
						copyFileUsingStream(inputFile, outputFile);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				for(int i = 0; i < ExistingfileListModel.size(); i++)
				{
					commandText = commandText + " INSERT INTO Files (TicketNum, Filename, UploadDate) VALUES ('" + TicketNum + "','" + ExistingfileListModel.get(i).getFile() + "','" + testDate +"')";
				}				
				c_Query.UpdateResultSet(commandText);
				
				ExistingfileListModel.clear();
			}
			
			/*Check for Internal Update */
			if(txt_Internal.getText().compareTo("") != 0)
			{
				String internal = c_CleanString.Clean_String(txt_Internal.getText());
				commandText = "UPDATE SupportTickets SET Internal = '" + internal + "' WHERE Ticket = '" + TicketNum + "'";
				c_Query.UpdateResultSet(commandText);
			}

			PopulateActiveWindow();
			PopulateActiveTickets(); //Added to refresh window after update has been made. Will clear email sent flag.
			tree_active.getExpandsSelectedPaths();
			tree_active.setSelectionPath(tpath);
			tree_active.scrollPathToVisible(tpath);
			//System.out.println(tpath);
			
			//tree_active.setSelectionPath((TreePath) selectedNode.getUserObject());

		 
	 }
	 
	 private static void copyFileUsingStream(File source, File dest) throws IOException {
		    InputStream is = null;
		    OutputStream os = null;
		    try {
		        is = new FileInputStream(source);
		        os = new FileOutputStream(dest);
		        byte[] buffer = new byte[102400];
		        int length;
		        while ((length = is.read(buffer)) > 0) {
		            os.write(buffer, 0, length);
		        }
		    } finally {
		        is.close();
		        os.close();
		    }
		}
}
