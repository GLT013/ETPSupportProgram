import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.tree.DefaultTreeModel;
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
import javax.swing.border.EtchedBorder;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
/* http://stackoverflow.com/questions/16157529/how-do-i-pass-objects-between-classes */
public class g_ArchiveTickets {

	public static JFrame frmArchiveTickets;
	private static JTree tree_active;
	private JLabel lbl_ClientSite;
	private JLabel lbl_Ticket;
	private JLabel lbl_Assigned;
	private JLabel lbl_Problem;
	private JLabel lbl_Update;
	JTextArea txt_Issue;
	JTextArea txt_Update;
	private JTextArea txt_Internal;
	private JLabel lblHours;
	private JLabel txt_TimeSpent;
	private JLabel lblDateLastUpdated;
	private JLabel lbl_UpdateDate;
	public static JList<c_Files> JList_FileList;
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
	private String directory = "\\\\10.10.38.252\\C$\\SupportProgram\\Files\\";
	private int numFiles; 
	private String client;
	private String site;
	private int siteid;
	private static JRadioButton rdbtnCategory;
	private static JRadioButton rdbtnSite;
	private static JLabel lblLastUpdatedByStr;
	private static JPanel panel_3;
	private static JScrollPane scrollPane_5;	
	final ArrayList <c_Archive> archiveListarr = new ArrayList<c_Archive>();	
	private static DefaultListModel<String> listModel = new DefaultListModel<String>();
	private static JCheckBox chckbxTicket;
	private static JCheckBox chckbxIssue;
	private static JCheckBox chckbxAssignedTo;
	private static JCheckBox chckbxInternal;
	private static JCheckBox chckbxResolution;	
	private static JList<String> searchList = new JList<String>();
	private static JScrollPane scrollPane_4;
	private static JLabel txtEnteredDate;
	private static JLabel txtStatus;
	
	

	

	public static void run() {				
		try {
			@SuppressWarnings("unused")
			g_ArchiveTickets window = new g_ArchiveTickets();
			g_ArchiveTickets.frmArchiveTickets.setVisible(true);
			g_ArchiveTickets.frmArchiveTickets.setLocationRelativeTo( g_MainMenu.frmMainMenu );
		} catch (Exception e) {
			e.printStackTrace();
		}
	}



	/**
	 * Create the application.
	 */
	public g_ArchiveTickets() {
		initialize();		
		PopulateActiveTickets();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmArchiveTickets = new JFrame();
		frmArchiveTickets.setIconImage(Toolkit.getDefaultToolkit().getImage(g_ArchiveTickets.class.getResource("/icon.png")));
		frmArchiveTickets.setTitle("Automated Support Program");
		frmArchiveTickets.getContentPane().setBackground(Color.LIGHT_GRAY);
		frmArchiveTickets.setBounds(100, 100, 964, 895);
		frmArchiveTickets.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmArchiveTickets.getContentPane().setLayout(null);
	

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		panel_1.setBounds(265, 11, 668, 788);
		frmArchiveTickets.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		lbl_ClientSite = new JLabel("Client Site (ID)");
		lbl_ClientSite.setBounds(10, 11, 283, 20);
		lbl_ClientSite.setFont(new Font("Plantagenet Cherokee", Font.BOLD, 16));
		panel_1.add(lbl_ClientSite);
		
		lbl_Ticket = new JLabel("(Ticket #)");
		lbl_Ticket.setBounds(544, 11, 114, 20);
		lbl_Ticket.setFont(new Font("Plantagenet Cherokee", Font.BOLD, 16));
		panel_1.add(lbl_Ticket);
		
		lbl_Assigned = new JLabel("");
		lbl_Assigned.setFont(new Font("Plantagenet Cherokee", Font.BOLD, 16));
		lbl_Assigned.setBounds(21, 739, 231, 20);
		panel_1.add(lbl_Assigned);
		
		ExistingfileListModel = new DefaultListModel<c_Files>();
		
		lbl_Problem = new JLabel("Issue");
		lbl_Problem.setFont(new Font("Plantagenet Cherokee", Font.BOLD, 16));
		lbl_Problem.setBounds(10, 77, 220, 20);
		panel_1.add(lbl_Problem);
		
		panel_Internal = new JPanel();
		panel_Internal.setBounds(10, 322, 648, 324);
		panel_1.add(panel_Internal);
		panel_Internal.setLayout(null);
		panel_Internal.setVisible(false);
		
		JButton btnFileUpload = new JButton("File Upload..");
		btnFileUpload.setEnabled(false);
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
		scrollPane_1.setBounds(0, 239, 328, 74);
		panel_Internal.add(scrollPane_1);
		
		JList_FileList = new JList<c_Files>();
		JList_FileList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane_1.setViewportView(JList_FileList);
		
		JButton btn_OpenFile = new JButton("Open File");
		btn_OpenFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (JList_FileList.getSelectedIndex() == -1)
				{
					JOptionPane.showMessageDialog(frmArchiveTickets, "No file was selected.");
				}
				else if(JList_FileList.getSelectedIndex() > (numFiles-1))
				{
					JOptionPane.showMessageDialog(frmArchiveTickets, "File not found. \n Please verify file has been uploaded by hitting the update button.");
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
						JOptionPane.showMessageDialog(frmArchiveTickets, "Could not find file.");
					}
				}
			}
			
		});
		btn_OpenFile.setBounds(358, 237, 105, 23);
		panel_Internal.add(btn_OpenFile);
		
		btn_DeleteFile = new JButton("Delete File");
		btn_DeleteFile.setEnabled(false);
		btn_DeleteFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (JList_FileList.getSelectedIndex() == -1)
				{
					JOptionPane.showMessageDialog(frmArchiveTickets, "No file was selected.");
				}
				else if(JList_FileList.getSelectedIndex() > (numFiles-1))
				{
					
				}
				else
				{
					int index = JList_FileList.getSelectedIndex();
					String source = fileListModel.getElementAt(index).getFile();
					String filePath = directory + TicketNum + "_" + source;
					int reply = JOptionPane.showConfirmDialog(frmArchiveTickets, "Are you sure you wish to delete " + source + "?" , "Delete File", JOptionPane.YES_NO_OPTION);
			        if (reply == JOptionPane.YES_OPTION)
			        {
			        	String commandText = "DELETE FROM Files WHERE Filename = '" + source + "'";
			        	c_Query.ExecuteQuery(commandText);
				        fileListModel.remove(index);
				        File file = new File(filePath);
				        if(file.delete()){
				        	JOptionPane.showMessageDialog(frmArchiveTickets, source + " has been deleted.");
			    		}else{
			    			JOptionPane.showMessageDialog(frmArchiveTickets, "Error deleting file from hard drive");
			    		}

				        JList_FileList.removeAll();
				        JList_FileList.setModel(fileListModel);
				        JList_FileList.revalidate();
				        JList_FileList.repaint();
			        }
			        else
			        {
			        	JOptionPane.showMessageDialog(frmArchiveTickets, "Whew! That was close.");
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
		
		lblLastUpdatedByStr = new JLabel("Assigned To:");
		lblLastUpdatedByStr.setFont(new Font("Plantagenet Cherokee", Font.BOLD, 16));
		lblLastUpdatedByStr.setBounds(499, 275, 134, 20);
		panel_Internal.add(lblLastUpdatedByStr);
		
		JLabel lblLastUpdatedBy = new JLabel("Last Updated By");
		lblLastUpdatedBy.setFont(new Font("Plantagenet Cherokee", Font.BOLD, 16));
		lblLastUpdatedBy.setBounds(499, 239, 134, 20);
		panel_Internal.add(lblLastUpdatedBy);
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
		
		txt_TimeSpent = new JLabel();
		txt_TimeSpent.setFont(new Font("Plantagenet Cherokee", Font.PLAIN, 14));
		txt_TimeSpent.setBounds(10, 283, 33, 25);
		panel_Update.add(txt_TimeSpent);
		txt_TimeSpent.setText("0.5");
		txt_TimeSpent.setToolTipText("Time Spent in Hours");
		
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
		lblhours.setBounds(40, 283, 58, 20);
		panel_Update.add(lblhours);
		
		JLabel lblEnteredDate = new JLabel("Entered Date:");
		lblEnteredDate.setBounds(215, 259, 114, 20);
		panel_Update.add(lblEnteredDate);
		lblEnteredDate.setFont(new Font("Plantagenet Cherokee", Font.BOLD, 16));
		
		txtEnteredDate = new JLabel("");
		txtEnteredDate.setBounds(215, 284, 185, 20);
		panel_Update.add(txtEnteredDate);
		txtEnteredDate.setFont(new Font("Plantagenet Cherokee", Font.BOLD, 16));
		
		JButton btn_SiteData = new JButton("Site Data");
		btn_SiteData.setEnabled(false);
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
		lblAssignedTo.setBounds(21, 717, 114, 20);
		panel_1.add(lblAssignedTo);
		
		scrollPane_3 = new JScrollPane();
		scrollPane_3.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane_3.setBounds(10, 99, 648, 212);
		panel_1.add(scrollPane_3);
		
		txt_Issue = new JTextArea();		
		txt_Issue.setLineWrap(true);
		txt_Issue.setWrapStyleWord(true);
		scrollPane_3.setViewportView(txt_Issue);
		txt_Issue.setEditable(false);
		
		JLabel lblOffline = new JLabel("Offline!");
		lblOffline.setForeground(Color.RED);
		lblOffline.setFont(new Font("Plantagenet Cherokee", Font.BOLD, 16));
		lblOffline.setBounds(294, 11, 97, 20);
		panel_1.add(lblOffline);
		
		btnSearch = new JButton("");
		btnSearch.setBounds(487, 727, 32, 32);
		panel_1.add(btnSearch);
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ReactivateTicket();			    
			}
			
		});
		btnSearch.setIcon(new ImageIcon(g_ArchiveTickets.class.getResource("/file.png")));
		btnSearch.setToolTipText("Reactivate Ticket");
		
		JLabel lblTicketLookup = new JLabel("Reactivate");
		lblTicketLookup.setBounds(476, 758, 74, 14);
		panel_1.add(lblTicketLookup);
		
		JButton button = new JButton("");
		button.setBounds(582, 727, 32, 32);
		panel_1.add(button);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {				
				if (client.compareTo("Kinder Morgan") == 0 || client.compareTo("Phillips 66") == 0 ||
					client.compareTo("Motiva") == 0 || client.compareTo("Chevron") == 0 ||
					client.compareTo("Caljet") == 0 || client.compareTo("Shell") == 0 ||
					client.compareTo("Sinclair") == 0 || client.compareTo("Cummins") == 0)
				{

					int reply = JOptionPane.showConfirmDialog(frmArchiveTickets, "Phone #281-637-6472 \n Call Control Center?" , "Control Center Phone #", JOptionPane.YES_NO_OPTION);
			        if (reply == JOptionPane.YES_OPTION)
			        {
			        	try {

							Runtime.getRuntime().exec(new String[] {"C:\\Program Files (x86)\\Microsoft Office\\Office16\\lync.exe", "/C", "Callto:tel:+ 12816376472"});
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
			        }
			        
				}
				else
				{
					int reply = JOptionPane.showConfirmDialog(frmArchiveTickets, "Phone #281-637-6473 \n Call Control Center?" , "Control Center Phone #", JOptionPane.YES_NO_OPTION);
			        if (reply == JOptionPane.YES_OPTION)
			        {
			        	try {

							Runtime.getRuntime().exec(new String[] {"C:\\Program Files (x86)\\Microsoft Office\\Office16\\lync.exe", "/C", "Callto:tel:+ 12816376473"});
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
			        }
				}												
			}
		});
		button.setIcon(new ImageIcon(g_ArchiveTickets.class.getResource("/telephone.png")));
		button.setToolTipText("Call CC");
		
		JLabel lblControlCenter = new JLabel("Control Center");
		lblControlCenter.setBounds(560, 758, 98, 14);
		panel_1.add(lblControlCenter);
		
		lblTicketStatus = new JLabel("Ticket Status:");
		lblTicketStatus.setFont(new Font("Plantagenet Cherokee", Font.BOLD, 16));
		lblTicketStatus.setBounds(248, 717, 114, 20);
		panel_1.add(lblTicketStatus);
		
		txtStatus = new JLabel("");
		txtStatus.setFont(new Font("Plantagenet Cherokee", Font.BOLD, 16));
		txtStatus.setBounds(248, 739, 178, 20);
		panel_1.add(txtStatus);
		lblOffline.setVisible(false);				
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				g_MainMenu.run(frmArchiveTickets);
				frmArchiveTickets.dispose();
			}
		});
		btnBack.setBounds(10, 822, 89, 23);
		frmArchiveTickets.getContentPane().add(btnBack);
		
		JButton btnRefresh = new JButton("");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PopulateActiveWindow();
				PopulateActiveTickets();				
			}
		});
		btnRefresh.setBounds(203, 803, 50, 42);
		frmArchiveTickets.getContentPane().add(btnRefresh);
		btnRefresh.setIcon(new ImageIcon(g_ArchiveTickets.class.getResource("/reload.png")));
		btnRefresh.setToolTipText("Refresh");
		
		panel = new JPanel();
		panel.setBounds(10, 32, 248, 767);
		frmArchiveTickets.getContentPane().add(panel);
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				panel.setLayout(null);
		
				scrollPane_5 = new JScrollPane();
				scrollPane_5.setBounds(0, 0, 248, 767);
				panel.add(scrollPane_5);
				
				tree_active = new JTree();
				scrollPane_5.setViewportView(tree_active);
				//tree_active.getCheckingModel().setCheckingMode(TreeCheckingModel.CheckingMode.PROPAGATE_PRESERVING_CHECK);
				tree_active.setBackground(Color.WHITE);
				tree_active.setBorder(UIManager.getBorder("DesktopIcon.border"));
				
				scrollPane_4 = new JScrollPane();
				scrollPane_4.setBounds(0, 0, 248, 666);
				panel.add(scrollPane_4);
				
			
				//scrollPane_4.setViewportView(searchList);				
				
				
				JPanel panel_4 = new JPanel();
				panel_4.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				panel_4.setBackground(Color.WHITE);
				panel_4.setBounds(10, 11, 248, 23);
				frmArchiveTickets.getContentPane().add(panel_4);
				panel_4.setLayout(null);
				
				rdbtnCategory = new JRadioButton("Category");
				rdbtnCategory.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						AdjustWindowSize();
						PopulateActiveTickets();
					}
				});
				rdbtnCategory.setSelected(true);
				buttonGroup.add(rdbtnCategory);
				rdbtnCategory.setBackground(Color.WHITE);
				rdbtnCategory.setBounds(17, 2, 71, 18);
				panel_4.add(rdbtnCategory);
				
				rdbtnSite = new JRadioButton("Site");
				rdbtnSite.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						AdjustWindowSize();
						PopulateActiveTickets();
					}
				});
				buttonGroup.add(rdbtnSite);
				rdbtnSite.setBackground(Color.WHITE);
				rdbtnSite.setBounds(93, 2, 58, 18);
				panel_4.add(rdbtnSite);
				
				
				rdbtnSearch = new JRadioButton("Search");
				rdbtnSearch.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						AdjustWindowSize();
						
						
					}
				});
				rdbtnSearch.setBackground(Color.WHITE);
				rdbtnSearch.setBounds(153, 2, 67, 18);
				buttonGroup.add(rdbtnSearch);
				panel_4.add(rdbtnSearch);
				
				panel_3 = new JPanel();
				panel_3.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				panel_3.setBackground(Color.WHITE);
				panel_3.setBounds(10, 32, 248, 100);
				frmArchiveTickets.getContentPane().add(panel_3);
				panel_3.setLayout(null);
				panel_3.setVisible(false);
				
				txtSearch = new JTextField();
				txtSearch.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						Search();
					}
				});
				txtSearch.setBounds(10, 65, 182, 28);
				panel_3.add(txtSearch);
				txtSearch.setColumns(10);
				
				JButton btnSearch1 = new JButton("");
				btnSearch1.setBackground(Color.WHITE);
				btnSearch1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						Search();
					}
				});
				btnSearch1.setIcon(new ImageIcon(g_ArchiveTickets.class.getResource("/Search-32.png")));
				btnSearch1.setBounds(202, 63, 30, 30);
				panel_3.add(btnSearch1);
				
				chckbxTicket = new JCheckBox("Ticket");
				chckbxTicket.setBackground(Color.WHITE);
				chckbxTicket.setBounds(6, 2, 60, 23);
				panel_3.add(chckbxTicket);
				
				chckbxIssue = new JCheckBox("Issue");
				chckbxIssue.setBackground(Color.WHITE);
				chckbxIssue.setBounds(75, 2, 60, 23);
				panel_3.add(chckbxIssue);
				
				chckbxAssignedTo = new JCheckBox("Assigned To");
				chckbxAssignedTo.setBackground(Color.WHITE);
				chckbxAssignedTo.setBounds(147, 2, 95, 23);
				panel_3.add(chckbxAssignedTo);
				
				chckbxInternal = new JCheckBox("Internal");
				chckbxInternal.setBackground(Color.WHITE);
				chckbxInternal.setBounds(42, 29, 73, 23);
				panel_3.add(chckbxInternal);
				
				chckbxResolution = new JCheckBox("Resolution");
				chckbxResolution.setBackground(Color.WHITE);
				chckbxResolution.setBounds(111, 29, 106, 23);
				panel_3.add(chckbxResolution);
				
				
				searchList.addListSelectionListener(new ListSelectionListener() {
					public void valueChanged(ListSelectionEvent arg0) {
						try{
							if(!arg0.getValueIsAdjusting())
							{
								
								lbl_ClientSite.setText(archiveListarr.get(searchList.getSelectedIndex()).getClient() + " " + archiveListarr.get(searchList.getSelectedIndex()).getSite());
								lbl_Ticket.setText("(#" + archiveListarr.get(searchList.getSelectedIndex()).getTicket() + ")");
								lbl_Assigned.setText(archiveListarr.get(searchList.getSelectedIndex()).getAssigned());
								txt_Issue.setText(archiveListarr.get(searchList.getSelectedIndex()).getDescription());
								txt_Update.setText(archiveListarr.get(searchList.getSelectedIndex()).getResolution());
								txt_Internal.setText(archiveListarr.get(searchList.getSelectedIndex()).getInternal());
								txt_TimeSpent.setText(archiveListarr.get(searchList.getSelectedIndex()).getTimeSpent());
								lbl_UpdateDate.setText(archiveListarr.get(searchList.getSelectedIndex()).getUpdateDate());
								lblLastUpdatedByStr.setText(archiveListarr.get(searchList.getSelectedIndex()).getLastUpdatedBy());
								txtEnteredDate.setText(archiveListarr.get(searchList.getSelectedIndex()).getEnteredDate());
								txtStatus.setText(archiveListarr.get(searchList.getSelectedIndex()).getStatus());
																
								fileListModel = new DefaultListModel<c_Files>();
								String commandText = "SELECT Filename FROM Files WHERE TicketNum = '" + archiveListarr.get(searchList.getSelectedIndex()).getTicket() + "'";		
								
								ResultSet rs = c_Query.ExecuteResultSet(commandText);
								
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
						}
						catch(Exception e)
						{
							
						}
					}
				});
							
				listModel.removeAllElements();
				searchList.setModel(listModel);
				
											
		tree_active.addTreeSelectionListener(new TreeSelectionListener() {
			public void valueChanged(TreeSelectionEvent e) {
				PopulateActiveWindow();
			}
		});
	}
	
	int tmp2[];
	private JLabel lblAssignedTo;
	private JButton btn_DeleteFile;
	private JScrollPane scrollPane_2;
	private JScrollPane scrollPane_3;
	private JPanel panel;
	private JButton btnSearch;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JRadioButton rdbtnSearch;
	private JTextField txtSearch;
	private JLabel lblTicketStatus;
	private void AdjustWindowSize()
	{
		if(rdbtnSearch.isSelected())
		{
			panel.setBounds(10, 133, 248, 666);
			panel_3.setVisible(true);
			scrollPane_5.setVisible(false);
			searchList.setVisible(true);
			scrollPane_4.setVisible(true);
			
		}
		else
		{
			panel.setBounds(10, 32, 248, 767);
			panel_3.setVisible(false);
			scrollPane_5.setVisible(true);
			searchList.setVisible(false);
			scrollPane_4.setVisible(false);
		}
		
	}
	
	private void Search()
	{

		listModel.clear();
		listModel.removeAllElements();		
		archiveListarr.removeAll(archiveListarr);
		
		String filters = "";
		String searchTerm = txtSearch.getText().toUpperCase();
		
		if(chckbxTicket.isSelected())
		{
			filters = filters + " TICKET LIKE '%" + searchTerm + "%'";
		}
		
		if(chckbxIssue.isSelected())
		{
			if(filters.compareTo("") != 0)
			{
				filters = filters + "OR";
			}						
				filters = filters + " UPPER(Description) LIKE " + "'%" + searchTerm + "%'";			
		}
		
		if(chckbxAssignedTo.isSelected())
		{
			if(filters.compareTo("") != 0)
			{
				filters = filters + "OR";
			}
			filters = filters + " UPPER(Assigned) LIKE '%" + searchTerm + "%'";
		}
		
		if(chckbxInternal.isSelected())
		{
			if(filters.compareTo("") != 0)
			{
				filters = filters + "OR";
			}
			filters = filters + " UPPER(Internal) LIKE " + "'%" + searchTerm + "%'";		
		}
		
		if(chckbxResolution.isSelected())
		{
			if(filters.compareTo("") != 0)
			{
				filters = filters + "OR";
			}
			filters = filters + " UPPER(Resolution) LIKE " + "'%" + searchTerm + "%'";
		}
		
		if(filters.compareTo("") == 0)			
		{			
			JOptionPane.showMessageDialog(frmArchiveTickets, "Please select a search criteria.");
		}
		else
		{
			String commandText = "SELECT Client, Site, Category, Ticket, CONVERT(varchar(17), EnteredDate, 113) as EnteredDate, Description, Assigned, Status, Resolution, Internal, Active, EmailSent, CONVERT(varchar(17), UpdateDate, 113) as UpdateDate,TimeSpent,CCNotified,LastUpdatedBy "
					+ "FROM SupportTickets WHERE" + filters + " ORDER BY Client asc, Site asc, EnteredDate desc";
			
			ResultSet rs = c_Query.ExecuteResultSet(commandText);
			try {
				while ((rs!=null) && (rs.next()))
				{				
					  c_Archive arch = new c_Archive();
					  arch.setClient(rs.getString("Client"));
					  arch.setSite(rs.getString("Site"));
					  arch.setCategory(rs.getString("Category"));
					  arch.setTicket(rs.getString("Ticket"));
					  arch.setEnteredDate(rs.getString("EnteredDate"));
					  arch.setDescription(rs.getString("Description"));
					  arch.setAssigned(rs.getString("Assigned"));
					  arch.setStatus(rs.getString("Status"));
					  arch.setResolution(rs.getString("Resolution"));
					  arch.setInternal(rs.getString("Internal"));
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
			
			}
			catch (SQLException e)
			{
				
			}					
		}
		
	}
	
	private void ReactivateTicket()
	{			 
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
				    PopulateActiveWindow();
					PopulateActiveTickets();					
	}
	
	public void RefreshAll()
	{
		PopulateActiveWindow();
		PopulateActiveTickets();
		
	}
	

	private void PopulateActiveWindow()
	{		
		
		lbl_Assigned.setVisible(true);					
		String commandText = "";
		if(rdbtnCategory.isSelected())
		{
			try{
				String tmp = tree_active.getSelectionPath().getLastPathComponent().toString();									
				TicketNum = tmp.split("[\\(\\)]")[1];		
				}
				catch (Exception e)
				{
					//do nothing
				}			
		}
		else if(rdbtnSite.isSelected())
		{
			try{
				String tmp = tree_active.getSelectionPath().getLastPathComponent().toString();									
				TicketNum = tmp;
				}
				catch (Exception e)
				{
					//do nothing
				}							

		}
		
		commandText = "SELECT a.Client, a.Site, a.SiteID, a.HostIP, a.ViewIP, a.SQLIP, a.DevIP, a.iDracIP, a.HighPerformance, b.Status, b.Category, b.Ticket, b.Description, b.Internal, b.Assigned, b.Status, CONVERT(varchar(17), b.EnteredDate, 113) as EnteredDate, CONVERT(varchar(17), b.UpdateDate, 113) as UpdateDate, b.TimeSpent, b.CCNotified, b.Resolution, b.LastUpdatedBy, b.rowID "
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
				rs.getInt("ViewIP");
				rs.getInt("SQLIP");
				rs.getInt("DevIP");
				rs.getInt("iDracIP");
				rs.getInt("HostIP");
				rs.getBoolean("HighPerformance");				
				lbl_Ticket.setText("(#" + rs.getString("Ticket") + ")");
				lbl_Assigned.setText(rs.getString("Assigned"));				
				txt_Issue.setText(rs.getString("Description"));
				txt_Update.setText(rs.getString("Resolution"));
				txt_Internal.setText(rs.getString("Internal"));
				txt_TimeSpent.setText(rs.getString("TimeSpent"));
				lbl_UpdateDate.setText(rs.getString("UpdateDate"));
				lblLastUpdatedByStr.setText(rs.getString("LastUpdatedBy"));		
				txtEnteredDate.setText(rs.getString("EnteredDate"));
				txtStatus.setText(rs.getString("Status"));
				
				rs.getInt("rowID");
				
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
	
	private void PopulateActiveTickets()
	{
		if(rdbtnSite.isSelected())
		{
			try {				
				tree_active.setModel(new DefaultTreeModel(
						new DefaultMutableTreeNode("Sites") {
							private static final long serialVersionUID = 1L;
							{
								DefaultMutableTreeNode node_category;
								DefaultMutableTreeNode node_client;
								DefaultMutableTreeNode node_ticket;
								
								String commandText = "SELECT DISTINCT Client from SupportTickets WHERE Status = 'Complete' ORDER BY Client ASC";
								ResultSet rs = c_Query.ExecuteResultSet(commandText);
								
								while((rs!=null) && (rs.next()))
								{									
									String tmpClient = rs.getString("Client");
									node_category = new DefaultMutableTreeNode(tmpClient);
									String commandText3 = "SELECT DISTINCT Site from SupportTickets WHERE Client = '" + tmpClient +"'";
									ResultSet rs3 = c_Query.ExecuteResultSet(commandText3);
									while((rs3!=null) && (rs3.next()))
									{									
										String tmpSite = rs3.getString("Site");
										node_client = new DefaultMutableTreeNode(tmpSite);
										node_category.add(node_client);
																				
										String commandText2 = "SELECT Ticket from SupportTickets WHERE Active = 0 and Site ='" + tmpSite + "' and Client = '" + tmpClient + "' ORDER BY Site asc, Ticket";										
										ResultSet rs2 = c_Query.ExecuteResultSet(commandText2);										
										while((rs2!=null) && (rs2.next()))
										{											
												node_ticket = new DefaultMutableTreeNode(rs2.getString("Ticket"));																
												node_client.add(node_ticket);										
										}							
									add(node_category);
										
									}

								}
							}
						}
					));
			} catch (SQLException e) {
				
				e.printStackTrace();
			}	
			
		}
		else
		{
			try {				
				tree_active.setModel(new DefaultTreeModel(
						new DefaultMutableTreeNode("Category") {
							private static final long serialVersionUID = 1L;
							{
								DefaultMutableTreeNode node_category;
								DefaultMutableTreeNode node_client;
								DefaultMutableTreeNode node_ticket;
								
								String commandText = "SELECT DISTINCT Category from SupportTickets WHERE Status = 'Complete' ORDER BY Category ASC";
								ResultSet rs = c_Query.ExecuteResultSet(commandText);
								
								while((rs!=null) && (rs.next()))
								{									
									String category = rs.getString("Category");
									node_category = new DefaultMutableTreeNode(category);
									String commandText3 = "SELECT DISTINCT Client from SupportTickets WHERE Category = '" + category +"'";
									ResultSet rs3 = c_Query.ExecuteResultSet(commandText3);
									while((rs3!=null) && (rs3.next()))
									{									
										String tmpclient = rs3.getString("Client");
										node_client = new DefaultMutableTreeNode(tmpclient);
										node_category.add(node_client);																				
										String commandText2 = "SELECT Site, Ticket  from SupportTickets WHERE Active = 0 and Category ='" + category + "' and Client = '" + tmpclient + "' ORDER BY Site asc, Ticket asc";										
										ResultSet rs2 = c_Query.ExecuteResultSet(commandText2);										
										while((rs2!=null) && (rs2.next()))
										{											
												node_ticket = new DefaultMutableTreeNode(rs2.getString("Site") + " ("  + rs2.getString("Ticket") +  ")");																
												node_client.add(node_ticket);										
										}							
									add(node_category);
										
									}
									
										
										
								}
							}
						}
					));
			} catch (SQLException e) {
				
				e.printStackTrace();
			}	
		}
	}
	
	 private void AddFilesToList(String filename, File source)
	 {		 
		 c_Files addFile = new c_Files(filename,source);
		 ExistingfileListModel.addElement(addFile);
		 fileListModel.addElement(addFile);
	 }
}
