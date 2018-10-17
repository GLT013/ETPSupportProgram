import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.border.EtchedBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.JPanel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JCheckBox;
import java.awt.Toolkit;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JTextArea;

public class g_ViewSites {

	public static JFrame frmButaneSites;
	private static JList<String> list_ButaneSites = new JList<String>();
	private static JList<String> list_ServersExt = new JList<String>();
	private static JList<String> list_ServersInt = new JList<String>();
	//private static JTree tree_sites;
	private static DefaultListModel<String> listModel = new DefaultListModel<String>();
	private static DefaultListModel<String> listModel2 = new DefaultListModel<String>();
	private static DefaultListModel<String> listModel3 = new DefaultListModel<String>();
	private JScrollPane scrollPane_ButaneSites;
	private JScrollPane scrollPane_ServersExt;
	private JScrollPane scrollPane_ServersInt;
	JTextField txtSite = new JTextField("");
	JTextField txtSiteID = new JTextField("");
	JTextField txtAddress = new JTextField("");
	JTextField txtPhone = new JTextField("");
	private final JButton btnEdit = new JButton("");
	private JTextField txtClient;
	private final JButton btnAccept = new JButton("");
	private final JButton btnCancel = new JButton("");
	final ArrayList <c_ButaneSites> result = new ArrayList<c_ButaneSites>();
	final ArrayList <c_Servers> ServersExt = new ArrayList<c_Servers>();
	final ArrayList <c_Servers> ServersInt = new ArrayList<c_Servers>();
	private final JButton btnAddSite = new JButton("");
	private final JButton btnNewButton = new JButton("Back");
	private JTextField txtClientAbbrv;
	private JTextField txtSiteAbbrv;
	private JTextField txtiDrac;
	private JTextField txtHost;
	private JTextField txtView;
	private JTextField txtSQL;
	private JTextField txtDev;
	private JTextField txtGeneration;
	private JTextField txtFieldTech;
	private JTextField txtFieldSupervisor;
	private JTextField txtTimezone;
	private JTextField txtState;
	private JCheckBox chk_HighPerformance;
	private JCheckBox chk_Twic;
	private JCheckBox chk_SQL;
	private JCheckBox chk_View;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JRadioButton rdbtnButaneSites;
	private JRadioButton rdbtnServers;
	private JTextField txt_ServerName;
	private JTextField txt_ServerIP;
	private JTextArea txt_ServerDesc;
	private JPanel panel_4;
	private JPanel panel_5;
	private JButton lbl_CentralSQL;
	private JButton lbl_CentralView;
	private JTextField txtStateAbbrv;
	private String sharepointURL;
	private JButton lbl_View;
	private JButton lbl_SQL;
	private JButton lbl_Dev;
	private JButton lbl_Host;
	private JButton lbl_iDrac;
	

	/**
	 * Launch the application.
	 */
	
			public static void run(JFrame frame) {
				try {
					@SuppressWarnings("unused")
					g_ViewSites window = new g_ViewSites();
					g_ViewSites.frmButaneSites.setVisible(true);
					g_ViewSites.frmButaneSites.setLocationRelativeTo(frame);
					frame.dispose();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
	

	/**
	 * Create the application.
	 */
	public g_ViewSites() {
		initialize();
		PopulateSites();
		PopulateServersExt();
		PopulateServersInt();
		//PopulateSitesTree();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmButaneSites = new JFrame();
		frmButaneSites.setResizable(false);
		frmButaneSites.setIconImage(Toolkit.getDefaultToolkit().getImage(g_ViewSites.class.getResource("/icon.png")));
		frmButaneSites.setBounds(100, 100, 909, 772);
		frmButaneSites.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frmButaneSites.setTitle(g_MainMenu.TitleOnline);
		
		
		frmButaneSites.addWindowListener(new java.awt.event.WindowAdapter() {
			 @Override
			    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				 listModel.removeAllElements();
				 list_ButaneSites.setModel(listModel);
				 listModel2.removeAllElements();
				 list_ServersExt.setModel(listModel2);
				 listModel3.removeAllElements();
				 list_ServersInt.setModel(listModel3);
				 frmButaneSites.dispose(); 
			 }
		
		});
		frmButaneSites.getContentPane().setLayout(null);

		scrollPane_ButaneSites = new JScrollPane();
		scrollPane_ButaneSites.setBounds(36, 47, 263, 589);
		scrollPane_ButaneSites.setViewportBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		frmButaneSites.getContentPane().add(scrollPane_ButaneSites);
		btnEdit.setIcon(new ImageIcon(g_ViewSites.class.getResource("/edit.png")));
		btnEdit.setBounds(828, 11, 32, 32);
		btnEdit.setToolTipText("Edit");
		btnEdit.addActionListener(new ActionListener() {			
			public void actionPerformed(ActionEvent arg0) {
				if(rdbtnButaneSites.isSelected())
					{
					txtClient.setEditable(true);
					txtSite.setEditable(true);
					//txtSiteID.setEditable(true); do not allow siteID to be changed.
					txtClientAbbrv.setEditable(true);
					txtSiteAbbrv.setEditable(true);
					txtiDrac.setEditable(true);
					txtState.setEditable(true);
					txtStateAbbrv.setEditable(true);
					txtHost.setEditable(true);
					txtView.setEditable(true);								
					txtSQL.setEditable(true);
					txtDev.setEditable(true);
					txtGeneration.setEditable(true);					
					txtAddress.setEditable(true);
					txtPhone.setEditable(true);
					txtFieldTech.setEditable(true);
					txtFieldSupervisor.setEditable(true);
					txtTimezone.setEditable(true);	
					chk_HighPerformance.setEnabled(true);
					chk_Twic.setEnabled(true);
					chk_SQL.setEnabled(true);
					chk_View.setEnabled(true);		
					lbl_View.setVisible(false);
					lbl_SQL.setVisible(false);
					lbl_Dev.setVisible(false);
					lbl_Host.setVisible(false);
					lbl_iDrac.setVisible(false);
					if(!chk_SQL.isSelected())
					{
						txtSQL.setVisible(true);	
					}
					if(!chk_View.isSelected())
					{
						txtView.setVisible(true);							
					}
					txtDev.setVisible(true);		
					txtiDrac.setVisible(true);		
					txtHost.setVisible(true);		
				}
				else
				{
					txt_ServerName.setEditable(true);
					txt_ServerIP.setEditable(true);
					txt_ServerDesc.setEditable(true);					
				}				
				btnEdit.setEnabled(false);
				btnEdit.setVisible(false);
				btnCancel.setVisible(true);
				btnCancel.setEnabled(true);
				btnAccept.setVisible(true);
				btnAccept.setEnabled(true);
			}
		});     
		
		
		frmButaneSites.getContentPane().add(btnEdit);
		btnAccept.setBounds(786, 11, 34, 34);
		btnAccept.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {				
				AcceptEdits();
			}
		});
		btnAccept.setToolTipText("Accept Edits");
		btnAccept.setEnabled(false);
		btnAccept.setVisible(false);
		btnAccept.setIcon(new ImageIcon(g_ViewSites.class.getResource("/ok-icon.png")));
		
		frmButaneSites.getContentPane().add(btnAccept);
		btnCancel.setBounds(828, 11, 32, 32);
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CancelEdits();
			}
		});
		btnCancel.setToolTipText("Cancel Edits");
		btnCancel.setEnabled(false);
		btnCancel.setIcon(new ImageIcon(g_ViewSites.class.getResource("/Actions-window-close-icon.png")));
		
		frmButaneSites.getContentPane().add(btnCancel);
		btnAddSite.setBounds(267, 638, 32, 32);
		btnAddSite.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdbtnButaneSites.isSelected())
				{
					g_NewSite.run(frmButaneSites);
				}
				else
				{
					g_NewServer.run(frmButaneSites);
				}
				frmButaneSites.dispose();
			}
		});
		btnAddSite.setIcon(new ImageIcon(g_ViewSites.class.getResource("/Plus-48.png")));
		btnAddSite.setToolTipText("New Site");
		
		frmButaneSites.getContentPane().add(btnAddSite);
		list_ButaneSites.setFont(new Font("Rockwell", Font.PLAIN, 13));
		list_ButaneSites.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		
		list_ButaneSites.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				try{
					if(!arg0.getValueIsAdjusting())
					{
						CancelEdits();
						txtClient.setText(result.get(list_ButaneSites.getSelectedIndex()).getClient());
						txtSite.setText(result.get(list_ButaneSites.getSelectedIndex()).getSite());
						txtSiteID.setText(String.valueOf(result.get(list_ButaneSites.getSelectedIndex()).getSiteID()));
						txtState.setText(result.get(list_ButaneSites.getSelectedIndex()).getState());
						txtStateAbbrv.setText(result.get(list_ButaneSites.getSelectedIndex()).getStateAbbrv());
						txtClientAbbrv.setText(result.get(list_ButaneSites.getSelectedIndex()).getClientAbbrv());
						txtSiteAbbrv.setText(result.get(list_ButaneSites.getSelectedIndex()).getSiteAbbrv());
						txtiDrac.setText(String.valueOf(result.get(list_ButaneSites.getSelectedIndex()).getiDrac()));
						txtHost.setText(String.valueOf(result.get(list_ButaneSites.getSelectedIndex()).getHost()));
						txtView.setText(String.valueOf(result.get(list_ButaneSites.getSelectedIndex()).getView()));
						txtSQL.setText(String.valueOf(result.get(list_ButaneSites.getSelectedIndex()).getSQL()));
						txtDev.setText(String.valueOf(result.get(list_ButaneSites.getSelectedIndex()).getDev()));
						txtGeneration.setText(String.valueOf(result.get(list_ButaneSites.getSelectedIndex()).getGeneration()));						
						txtAddress.setText(result.get(list_ButaneSites.getSelectedIndex()).getAddress());
						txtPhone.setText(result.get(list_ButaneSites.getSelectedIndex()).getPhone());
						txtFieldTech.setText(result.get(list_ButaneSites.getSelectedIndex()).getFieldTech());
						txtFieldSupervisor.setText(result.get(list_ButaneSites.getSelectedIndex()).getFieldSupervisor());
						txtTimezone.setText(result.get(list_ButaneSites.getSelectedIndex()).getTimezone());
						sharepointURL = result.get(list_ButaneSites.getSelectedIndex()).getSharepoint();						
						
						txtDev.setVisible(false);
						txtiDrac.setVisible(false);
						txtHost.setVisible(false);
						
						if(result.get(list_ButaneSites.getSelectedIndex()).getTwic())
						{
							chk_Twic.setSelected(true);
						}
						else
						{
							chk_Twic.setSelected(false);
						}
						
						if(result.get(list_ButaneSites.getSelectedIndex()).getHighPerformance())
						{
							chk_HighPerformance.setSelected(true);
						}
						else
						{
							chk_HighPerformance.setSelected(false);
						}
						
						if(result.get(list_ButaneSites.getSelectedIndex()).getCentralSQL())
						{
							chk_SQL.setSelected(true);
							txtSQL.setVisible(false);
							lbl_SQL.setVisible(false);
							lbl_CentralSQL.setVisible(true);
						}
						else
						{
							chk_SQL.setSelected(false);
							txtSQL.setVisible(false);
							lbl_SQL.setVisible(true);
							lbl_CentralSQL.setVisible(false);							
						}
						
						if(result.get(list_ButaneSites.getSelectedIndex()).getCentralView())
						{
							chk_View.setSelected(true);							
							txtView.setVisible(false);
							lbl_View.setVisible(false);
							
							lbl_CentralView.setVisible(true);
						}
						else
						{
							chk_View.setSelected(false);
							txtView.setVisible(false);
							lbl_View.setVisible(true);
							lbl_CentralView.setVisible(false);
						}
					}
				}
				catch(Exception e)
				{
					
				}
			}
		});
		
		listModel.removeAllElements();
		list_ButaneSites.setModel(listModel);
		btnNewButton.setBounds(10, 660, 83, 32);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				g_MainMenu.run(frmButaneSites);
				frmButaneSites.dispose();
			}
		});
		
		frmButaneSites.getContentPane().add(btnNewButton);
		
		final JPanel panel = new JPanel();
		panel.setBounds(643, 47, 240, 161);
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		frmButaneSites.getContentPane().add(panel);
		panel.setLayout(null);
		
		txtiDrac = new JTextField("");		
		txtiDrac.setBounds(136, 95, 40, 30);
		txtiDrac.setColumns(1);
		panel.add(txtiDrac);
		txtiDrac.setFont(new Font("Rockwell", Font.PLAIN, 14));
		txtiDrac.setEditable(false);
		txtiDrac.setVisible(false);
		
		JLabel lbliDrac = new JLabel("iDrac");
		lbliDrac.setBounds(136, 130, 45, 14);
		panel.add(lbliDrac);
		lbliDrac.setFont(new Font("Rockwell", Font.PLAIN, 14));
		
		JLabel lblServer = new JLabel("Server Information");
		lblServer.setHorizontalAlignment(SwingConstants.CENTER);
		lblServer.setFont(new Font("Rockwell", Font.PLAIN, 18));
		lblServer.setBounds(0, 10, 240, 14);
		panel.add(lblServer);
		
		txtHost = new JTextField("");		
		txtHost.setBounds(48, 95, 45, 30);
		panel.add(txtHost);
		txtHost.setFont(new Font("Rockwell", Font.PLAIN, 14));
		txtHost.setEditable(false);
		txtHost.setVisible(false);
		
		JLabel lblHost = new JLabel("Host");
		lblHost.setBounds(48, 130, 45, 14);
		panel.add(lblHost);
		lblHost.setFont(new Font("Rockwell", Font.PLAIN, 14));
		
		txtView = new JTextField("");
		txtView.setVisible(false);
		
		
		txtView.setBounds(28, 35, 45, 30);
		panel.add(txtView);
		txtView.setFont(new Font("Rockwell", Font.PLAIN, 14));
		txtView.setEditable(false);
		txtView.setVisible(false);
		JLabel lblView = new JLabel("View");
		lblView.setBounds(28, 70, 45, 14);
		panel.add(lblView);
		lblView.setFont(new Font("Rockwell", Font.PLAIN, 14));
		
		txtSQL = new JTextField("");		
		txtSQL.setBounds(88, 35, 45, 30);
		panel.add(txtSQL);
		txtSQL.setFont(new Font("Rockwell", Font.PLAIN, 14));
		txtSQL.setEditable(false);
		txtSQL.setVisible(false);
		
		JLabel lblSQL = new JLabel("SQL");
		lblSQL.setBounds(88, 70, 45, 14);
		panel.add(lblSQL);
		lblSQL.setFont(new Font("Rockwell", Font.PLAIN, 14));
		
		txtDev = new JTextField("");
		
		txtDev.setBounds(148, 35, 45, 30);
		panel.add(txtDev);
		txtDev.setFont(new Font("Rockwell", Font.PLAIN, 14));
		txtDev.setEditable(false);
		
		JLabel lblDev = new JLabel("Dev");
		lblDev.setBounds(148, 70, 45, 14);
		panel.add(lblDev);
		lblDev.setFont(new Font("Rockwell", Font.PLAIN, 14));
		
		lbl_CentralView = new JButton("");
		lbl_CentralView.setIcon(new ImageIcon(g_ViewSites.class.getResource("/view.png")));
		lbl_CentralView.setFont(new Font("Rockwell", Font.PLAIN, 14));
		lbl_CentralView.setBounds(28, 35, 32, 32);
		panel.add(lbl_CentralView);
		
		lbl_CentralSQL = new JButton("");
		lbl_CentralSQL.setIcon(new ImageIcon(g_ViewSites.class.getResource("/sql.png")));
		lbl_CentralSQL.setFont(new Font("Rockwell", Font.PLAIN, 14));
		lbl_CentralSQL.setBounds(87, 35, 32, 32);
		panel.add(lbl_CentralSQL);
		
		lbl_View = new JButton("");
		lbl_View.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(!txtView.isEditable())
				{
					String ipAddress = "";
					if(!chk_View.isSelected())
					{
						ipAddress = "10.219." + txtSiteID.getText() + "." + txtView.getText();
					}
					else //central view
					{
						ipAddress = "192.168.32.74";
					}
					
					if(arg0.getButton() == MouseEvent.BUTTON1)
					{			
						String width;
						String height;
						if(!chk_HighPerformance.isSelected())
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
			}
		});
		
		lbl_View.setIcon(new ImageIcon(g_ViewSites.class.getResource("/view.png")));
		lbl_View.setBounds(27, 35, 32, 32);
		lbl_View.setBorderPainted(false); 
		//lbl_View.setContentAreaFilled(false); 
		//lbl_View.setFocusPainted(false); 
		lbl_View.setOpaque(false);
		panel.add(lbl_View);
		
		lbl_Host = new JButton("");
		lbl_Host.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg4) {
				if(!txtHost.isEditable())
				{
					if(arg4.getButton() == MouseEvent.BUTTON1)
					{
						String ipAddress = "10.219." + txtSiteID.getText() + "." + txtHost.getText() + "/ui"; 
						try {
							Runtime.getRuntime().exec("C:\\Program Files\\Internet Explorer\\iexplore.exe " + ipAddress);
						} catch (IOException e2) {
						}
					}					
					else if(arg4.getButton() == MouseEvent.BUTTON3)
					{					
						try {
							Runtime.getRuntime().exec(new String[] {"cmd.exe", "/c", "start cmd.exe /K ping 10.219."+txtSiteID.getText()+"."+txtHost.getText()+""});												
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
			}
		});
		lbl_Host.setIcon(new ImageIcon(g_ViewSites.class.getResource("/host.png")));
		lbl_Host.setBounds(48, 93, 32, 32);
		panel.add(lbl_Host);
		
		lbl_iDrac = new JButton("");
		lbl_iDrac.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg5) {
				if(!txtiDrac.isEditable())
				{
					if(arg5.getButton() == MouseEvent.BUTTON1)
					{
						String ipAddress = "10.219." + txtSiteID.getText() + "." + txtiDrac.getText(); 
						try {
							Runtime.getRuntime().exec("C:\\Program Files\\Internet Explorer\\iexplore.exe " + ipAddress);
						} catch (IOException e2) {
						}
					}
					else if(arg5.getButton() == MouseEvent.BUTTON3)
					{					
						try {
							Runtime.getRuntime().exec(new String[] {"cmd.exe", "/c", "start cmd.exe /K ping 10.219."+txtSiteID.getText()+"."+txtiDrac.getText()+""});												
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
			}
		});
		lbl_iDrac.setIcon(new ImageIcon(g_ViewSites.class.getResource("/idrac.png")));
		lbl_iDrac.setBounds(136, 93, 32, 32);
		panel.add(lbl_iDrac);
		
		lbl_Dev = new JButton("");
		lbl_Dev.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg1) {
				if(!txtDev.isEditable())
				{					
					if(arg1.getButton() == MouseEvent.BUTTON1)
					{
						String width = "1920";
						String height = "1080";			
						String ipAddress = "10.219." + txtSiteID.getText() + "." + txtDev.getText(); 
						try {
							Runtime.getRuntime().exec(new String[] {"cmd.exe", "/C", "mstsc /v:"+ipAddress + " /w:" + width + " /h:" + height});
						} catch (IOException e2) {
							e2.printStackTrace();
						}
					}
					else if(arg1.getButton() == MouseEvent.BUTTON2)
					{					
						try {
							Runtime.getRuntime().exec(new String[] {"cmd.exe", "/C", "start \\\\10.219."+txtSiteID.getText()+"."+txtDev.getText()+"\\c$"});
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
					else if(arg1.getButton() == MouseEvent.BUTTON3)
					{					
						try {
							Runtime.getRuntime().exec(new String[] {"cmd.exe", "/c", "start cmd.exe /K ping 10.219."+txtSiteID.getText()+"."+txtDev.getText()+""});												
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
			}
		});
		lbl_Dev.setIcon(new ImageIcon(g_ViewSites.class.getResource("/dev.png")));
		lbl_Dev.setBounds(144, 35, 32, 32);
		panel.add(lbl_Dev);
		
		lbl_SQL = new JButton("");
		lbl_SQL.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg2) {
				if(!txtSQL.isEditable())
				{
					String ipAddress = ""; 
					if(!chk_SQL.isSelected())
					{
						ipAddress = "10.219." + txtSiteID.getText() + "." + txtSQL.getText(); 
					}
					else //central view
					{
						ipAddress = "192.168.32.75";
					}
					if(arg2.getButton() == MouseEvent.BUTTON1)
					{
						String width = "1920";
						String height = "1080";			
						
						try {
							Runtime.getRuntime().exec(new String[] {"cmd.exe", "/C", "mstsc /v:"+ipAddress + " /w:" + width + " /h:" + height});
						} catch (IOException e2) {
							e2.printStackTrace();
						}
					}
				
					else if(arg2.getButton() == MouseEvent.BUTTON2)
					{					
						try {
							Runtime.getRuntime().exec(new String[] {"cmd.exe", "/C", "start \\\\"+ipAddress+"\\c$"});							
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
					else if(arg2.getButton() == MouseEvent.BUTTON3)
					{					
						try {
							Runtime.getRuntime().exec(new String[] {"cmd.exe", "/c", "start cmd.exe /K ping "+ipAddress+""});												
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
			}
		});
		lbl_SQL.setIcon(new ImageIcon(g_ViewSites.class.getResource("/sql.png")));
		lbl_SQL.setBounds(87, 35, 32, 32);
		panel.add(lbl_SQL);
		txtView.setVisible(false);
		
		final JPanel panel_1 = new JPanel();
		panel_1.setBounds(323, 47, 310, 161);
		panel_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		frmButaneSites.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblClientInformation = new JLabel("Site Information");
		lblClientInformation.setHorizontalAlignment(SwingConstants.CENTER);
		lblClientInformation.setFont(new Font("Rockwell", Font.PLAIN, 18));
		lblClientInformation.setBounds(0, 11, 310, 14);
		panel_1.add(lblClientInformation);
		
		txtClient = new JTextField();
		txtClient.setBounds(10, 35, 150, 31);
		panel_1.add(txtClient);
		txtClient.setFont(new Font("Rockwell", Font.PLAIN, 14));
		txtClient.setBackground(UIManager.getColor("Button.background"));
		txtClient.setEditable(false);
		txtClient.setColumns(10);
		txtSite.setBounds(170, 35, 130, 30);
		panel_1.add(txtSite);
		txtSite.setEditable(false);
		
		
		txtSite.setFont(new Font("Rockwell", Font.PLAIN, 14));
		
		JLabel lblNewLabel = new JLabel("Client");
		lblNewLabel.setBounds(10, 70, 130, 14);
		panel_1.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Rockwell", Font.PLAIN, 14));
		
		JLabel lblSite = new JLabel("Site");
		lblSite.setBounds(170, 70, 130, 14);
		panel_1.add(lblSite);
		lblSite.setFont(new Font("Rockwell", Font.PLAIN, 14));
		txtSiteID.setBounds(10, 102, 80, 30);
		panel_1.add(txtSiteID);
		txtSiteID.setEditable(false);
		
		
		txtSiteID.setFont(new Font("Rockwell", Font.PLAIN, 14));
		
		txtClientAbbrv = new JTextField("");
		txtClientAbbrv.setBounds(112, 102, 80, 30);
		panel_1.add(txtClientAbbrv);
		txtClientAbbrv.setFont(new Font("Rockwell", Font.PLAIN, 14));
		txtClientAbbrv.setEditable(false);
		
		txtSiteAbbrv = new JTextField("");
		txtSiteAbbrv.setBounds(215, 102, 80, 30);
		panel_1.add(txtSiteAbbrv);
		txtSiteAbbrv.setFont(new Font("Rockwell", Font.PLAIN, 14));
		txtSiteAbbrv.setEditable(false);
		
		JLabel lblSiteID = new JLabel("Site ID");
		lblSiteID.setBounds(10, 135, 80, 14);
		panel_1.add(lblSiteID);
		lblSiteID.setFont(new Font("Rockwell", Font.PLAIN, 14));
		
		JLabel lblClientAbbrv = new JLabel("Client Abbrv");
		lblClientAbbrv.setBounds(112, 135, 93, 14);
		panel_1.add(lblClientAbbrv);
		lblClientAbbrv.setFont(new Font("Rockwell", Font.PLAIN, 14));
		
		JLabel lblSiteAbbrv = new JLabel("Site Abbrv");
		lblSiteAbbrv.setBounds(215, 135, 80, 14);
		panel_1.add(lblSiteAbbrv);
		lblSiteAbbrv.setFont(new Font("Rockwell", Font.PLAIN, 14));
		
		
		final JPanel panel_2 = new JPanel();
		panel_2.setBounds(323, 235, 560, 278);
		panel_2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		frmButaneSites.getContentPane().add(panel_2);
		panel_2.setLayout(null);
		txtAddress.setBounds(113, 32, 365, 30);
		panel_2.add(txtAddress);
		txtAddress.setEditable(false);
		
		
		txtAddress.setFont(new Font("Rockwell", Font.PLAIN, 14));
		
		JLabel lblTerminalInformation = new JLabel("Terminal Information");
		lblTerminalInformation.setHorizontalAlignment(SwingConstants.CENTER);
		lblTerminalInformation.setFont(new Font("Rockwell", Font.PLAIN, 18));
		lblTerminalInformation.setBounds(0, 7, 560, 14);
		panel_2.add(lblTerminalInformation);
		
		JLabel lblAddress = new JLabel("Address:");
		lblAddress.setBounds(10, 37, 77, 14);
		panel_2.add(lblAddress);
		lblAddress.setFont(new Font("Rockwell", Font.PLAIN, 18));
		txtPhone.setBounds(113, 125, 365, 30);
		panel_2.add(txtPhone);
		txtPhone.setEditable(false);
		
		txtPhone.setFont(new Font("Rockwell", Font.PLAIN, 14));
		
		JLabel lblPhone = new JLabel("Phone:");
		lblPhone.setBounds(10, 130, 113, 14);
		panel_2.add(lblPhone);
		lblPhone.setFont(new Font("Rockwell", Font.PLAIN, 18));
		
		JLabel lblFieldTech = new JLabel("Field Tech:");
		lblFieldTech.setBounds(10, 181, 113, 14);
		panel_2.add(lblFieldTech);
		lblFieldTech.setFont(new Font("Rockwell", Font.PLAIN, 18));
		
		txtFieldTech = new JTextField("");
		txtFieldTech.setBounds(113, 176, 365, 30);
		panel_2.add(txtFieldTech);
		txtFieldTech.setFont(new Font("Rockwell", Font.PLAIN, 14));
		txtFieldTech.setEditable(false);
		
		JLabel lblField = new JLabel("Field");
		lblField.setFont(new Font("Rockwell", Font.PLAIN, 18));
		lblField.setBounds(10, 231, 113, 14);
		panel_2.add(lblField);
		
		JLabel lblSupervisor = new JLabel("Supervisor:");
		lblSupervisor.setFont(new Font("Rockwell", Font.PLAIN, 18));
		lblSupervisor.setBounds(10, 253, 113, 14);
		panel_2.add(lblSupervisor);
		
		txtFieldSupervisor = new JTextField("");
		txtFieldSupervisor.setBounds(113, 236, 365, 30);
		panel_2.add(txtFieldSupervisor);
		txtFieldSupervisor.setFont(new Font("Rockwell", Font.PLAIN, 14));
		txtFieldSupervisor.setEditable(false);
		
		JLabel lblState = new JLabel("State:");
		lblState.setFont(new Font("Rockwell", Font.PLAIN, 18));
		lblState.setBounds(10, 78, 77, 14);
		panel_2.add(lblState);
		
		txtState = new JTextField("");
		txtState.setFont(new Font("Rockwell", Font.PLAIN, 14));
		txtState.setEditable(false);
		txtState.setBounds(113, 73, 223, 30);
		panel_2.add(txtState);
		
		JLabel lblStateAbbrv = new JLabel("Abbrv:");
		lblStateAbbrv.setFont(new Font("Rockwell", Font.PLAIN, 18));
		lblStateAbbrv.setBounds(364, 78, 63, 14);
		panel_2.add(lblStateAbbrv);
		
		txtStateAbbrv = new JTextField("");
		txtStateAbbrv.setFont(new Font("Rockwell", Font.PLAIN, 14));
		txtStateAbbrv.setEditable(false);
		txtStateAbbrv.setBounds(431, 73, 47, 30);
		panel_2.add(txtStateAbbrv);
		txtStateAbbrv.setEditable(false);
		
		JButton lblMapIcon = new JButton("");
		lblMapIcon.setIcon(new ImageIcon(g_ViewSites.class.getResource("/google.png")));
		lblMapIcon.setBounds(488, 32, 28, 28);
		panel_2.add(lblMapIcon);
		
		final JPanel panel_3 = new JPanel();
		panel_3.setBounds(323, 515, 560, 121);
		panel_3.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		frmButaneSites.getContentPane().add(panel_3);
		panel_3.setLayout(null);
		
		JLabel lblMiscInformation = new JLabel("Misc Information");
		lblMiscInformation.setHorizontalAlignment(SwingConstants.CENTER);
		lblMiscInformation.setFont(new Font("Rockwell", Font.PLAIN, 18));
		lblMiscInformation.setBounds(0, 11, 560, 14);
		panel_3.add(lblMiscInformation);
		
		txtGeneration = new JTextField("");
		txtGeneration.setBounds(21, 52, 83, 30);
		panel_3.add(txtGeneration);
		txtGeneration.setFont(new Font("Rockwell", Font.PLAIN, 14));
		txtGeneration.setEditable(false);
		
		JLabel lblGeneration = new JLabel("Generation");
		lblGeneration.setBounds(21, 87, 83, 14);
		panel_3.add(lblGeneration);
		lblGeneration.setFont(new Font("Rockwell", Font.PLAIN, 14));
		
		JLabel lblTwic = new JLabel("TWIC");
		lblTwic.setBounds(299, 75, 46, 14);
		panel_3.add(lblTwic);
		lblTwic.setFont(new Font("Rockwell", Font.PLAIN, 13));
		
		txtTimezone = new JTextField("");
		txtTimezone.setFont(new Font("Rockwell", Font.PLAIN, 14));
		txtTimezone.setEditable(false);
		txtTimezone.setBounds(124, 52, 83, 30);
		panel_3.add(txtTimezone);
		
		JLabel lblTimezone = new JLabel("Timezone");
		lblTimezone.setFont(new Font("Rockwell", Font.PLAIN, 14));
		lblTimezone.setBounds(124, 87, 83, 14);
		panel_3.add(lblTimezone);
		
		chk_HighPerformance = new JCheckBox("");
		chk_HighPerformance.setBounds(238, 52, 26, 23);
		panel_3.add(chk_HighPerformance);
		
		JLabel lblHighPerformance = new JLabel("<html><center>High Performance </center></html>");
		lblHighPerformance.setFont(new Font("Rockwell", Font.PLAIN, 13));
		lblHighPerformance.setBounds(215, 75, 74, 35);
		panel_3.add(lblHighPerformance);
		
		chk_Twic = new JCheckBox("");
		chk_Twic.setBounds(302, 52, 26, 23);
		panel_3.add(chk_Twic);
		
		JLabel lblCentralSlq = new JLabel("<html><center>Central SQL</center></html>");
		lblCentralSlq.setFont(new Font("Rockwell", Font.PLAIN, 13));
		lblCentralSlq.setBounds(353, 75, 46, 27);
		panel_3.add(lblCentralSlq);
		
		chk_SQL = new JCheckBox("");
		chk_SQL.setBounds(365, 52, 26, 23);
		panel_3.add(chk_SQL);
		
		JLabel lblCentralView = new JLabel("<html><center>Central View</center></html>");
		lblCentralView.setFont(new Font("Rockwell", Font.PLAIN, 13));
		lblCentralView.setBounds(416, 75, 51, 27);
		panel_3.add(lblCentralView);
		
		chk_View = new JCheckBox("");
		chk_View.setBounds(431, 52, 26, 23);
		panel_3.add(chk_View);
		
		JButton lblSharePoint = new JButton("");
		lblSharePoint.setToolTipText("Sharepoint");
		lblSharePoint.setBounds(499, 47, 32, 30);
		panel_3.add(lblSharePoint);
		lblSharePoint.setIcon(new ImageIcon(g_ViewSites.class.getResource("/sharepoint.png")));
		
		JLabel lblsharepoint = new JLabel("<html><center>Sharepoint</center></html>");
		lblsharepoint.setFont(new Font("Rockwell", Font.PLAIN, 13));
		lblsharepoint.setBounds(480, 74, 70, 27);
		panel_3.add(lblsharepoint);
		
		rdbtnButaneSites = new JRadioButton("Butane Sites");
		rdbtnButaneSites.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				scrollPane_ButaneSites.setVisible(true);
				panel.setVisible(true);
				panel_1.setVisible(true);
				panel_2.setVisible(true);
				panel_3.setVisible(true);
				panel_4.setVisible(false);
				panel_5.setVisible(false);
			}
		});
		rdbtnButaneSites.setFont(new Font("Rockwell", Font.BOLD, 12));
		buttonGroup.add(rdbtnButaneSites);
		rdbtnButaneSites.setBounds(50, 20, 109, 23);
		rdbtnButaneSites.setSelected(true);
		frmButaneSites.getContentPane().add(rdbtnButaneSites);
		
		rdbtnServers = new JRadioButton("Servers");
		rdbtnServers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				scrollPane_ButaneSites.setVisible(false);
				panel.setVisible(false);
				panel_1.setVisible(false);
				panel_2.setVisible(false);
				panel_3.setVisible(false);
				panel_4.setVisible(true);
				panel_5.setVisible(true);
			}
		});
		rdbtnServers.setFont(new Font("Rockwell", Font.BOLD, 12));
		buttonGroup.add(rdbtnServers);
		rdbtnServers.setBounds(180, 20, 109, 23);
		frmButaneSites.getContentPane().add(rdbtnServers);
		
		panel_4 = new JPanel();
		panel_4.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_4.setBounds(325, 47, 568, 589);
		frmButaneSites.getContentPane().add(panel_4);
		panel_4.setLayout(null);
		panel_4.setVisible(false);
		
		txt_ServerName = new JTextField();
		txt_ServerName.setEditable(false);
		txt_ServerName.setBounds(41, 40, 165, 28);
		panel_4.add(txt_ServerName);
		txt_ServerName.setColumns(10);
		
		txt_ServerIP = new JTextField();
		txt_ServerIP.setEditable(false);
		txt_ServerIP.setColumns(10);
		txt_ServerIP.setBounds(277, 40, 165, 28);
		panel_4.add(txt_ServerIP);
		
		JLabel lblServerName = new JLabel("Server Name");
		lblServerName.setFont(new Font("Rockwell", Font.BOLD, 14));
		lblServerName.setBounds(43, 15, 115, 28);
		panel_4.add(lblServerName);
		
		JLabel lblServerIp = new JLabel("Server IP");
		lblServerIp.setFont(new Font("Rockwell", Font.BOLD, 14));
		lblServerIp.setBounds(275, 15, 115, 28);
		panel_4.add(lblServerIp);
		
		txt_ServerDesc = new JTextArea();
		txt_ServerDesc.setEditable(false);
		txt_ServerDesc.setLineWrap(true);
		txt_ServerDesc.setBounds(40, 110, 493, 316);
		panel_4.add(txt_ServerDesc);
		
		JLabel lblDescription = new JLabel("Description");
		lblDescription.setFont(new Font("Rockwell", Font.BOLD, 14));
		lblDescription.setBounds(41, 79, 115, 28);
		panel_4.add(lblDescription);
		
		JButton lblNewLabel_1 = new JButton("");
		lblNewLabel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg3) {
				if(arg3.getButton() == MouseEvent.BUTTON1)
				{
				String width;
				String height;				
				width = "1920";
				height = "1080";
				
				try {
					Runtime.getRuntime().exec(new String[] {"cmd.exe", "/C", "mstsc /v:"+txt_ServerIP.getText() + " /w:" + width + " /h:" + height});
				} catch (IOException e) {
					e.printStackTrace();						
					}
				}				
			else if(arg3.getButton() == MouseEvent.BUTTON2)
			{					
				try {
					Runtime.getRuntime().exec(new String[] {"cmd.exe", "/C", "start \\\\"+txt_ServerIP.getText()+"\\c$"});
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			else if(arg3.getButton() == MouseEvent.BUTTON3)
			{					
				try {
					Runtime.getRuntime().exec(new String[] {"cmd.exe", "/c", "start cmd.exe /K ping "+txt_ServerIP.getText()+""});												
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		
			}
		});
		lblNewLabel_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		lblNewLabel_1.setToolTipText("RDP");
		lblNewLabel_1.setIcon(new ImageIcon(g_ViewSites.class.getResource("/sql.png")));
		lblNewLabel_1.setBounds(479, 40, 32, 32);
		panel_4.add(lblNewLabel_1);
		
		panel_5 = new JPanel();
		panel_5.setBorder(null);
		panel_5.setBounds(34, 47, 265, 589);
		frmButaneSites.getContentPane().add(panel_5);
		panel_5.setLayout(null);
		panel_5.setVisible(false);
		
		scrollPane_ServersInt = new JScrollPane();
		scrollPane_ServersInt.setBounds(0, 314, 263, 275);
		panel_5.add(scrollPane_ServersInt);
		scrollPane_ServersInt.setViewportBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		
		list_ServersInt = new JList<String>();
		list_ServersInt.setFont(new Font("Rockwell", Font.PLAIN, 13));
		list_ServersInt.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list_ServersInt.setSelectedIndex(0);
		scrollPane_ServersInt.setViewportView(list_ServersInt);
		
		scrollPane_ServersExt = new JScrollPane();
		scrollPane_ServersExt.setBounds(0, 0, 263, 275);
		panel_5.add(scrollPane_ServersExt);
		scrollPane_ServersExt.setViewportBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		listModel3.removeAllElements();
		list_ServersInt.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				try{
					if(!arg0.getValueIsAdjusting())
					{
						CancelEdits();
						list_ServersExt.clearSelection();
						txt_ServerName.setText(ServersInt.get(list_ServersInt.getSelectedIndex()).getServer());
						txt_ServerIP.setText(ServersInt.get(list_ServersInt.getSelectedIndex()).getIP());
						txt_ServerDesc.setText(ServersInt.get(list_ServersInt.getSelectedIndex()).getDescription());						
					}
				}
				catch(Exception e)
				{
					
				}
			}
		});
		list_ServersExt.setFont(new Font("Rockwell", Font.PLAIN, 13));
		list_ServersExt.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		
		scrollPane_ServersExt.setViewportView(list_ServersExt);
		
		JLabel lblInternalServers = new JLabel("Internal Servers");
		lblInternalServers.setBounds(10, 285, 115, 28);
		panel_5.add(lblInternalServers);
		lblInternalServers.setFont(new Font("Rockwell", Font.BOLD, 14));
		listModel2.removeAllElements();
		list_ServersExt.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				try{
					if(!arg0.getValueIsAdjusting())
					{
						CancelEdits();
						list_ServersInt.clearSelection();
						txt_ServerName.setText(ServersExt.get(list_ServersExt.getSelectedIndex()).getServer());
						txt_ServerIP.setText(ServersExt.get(list_ServersExt.getSelectedIndex()).getIP());
						txt_ServerDesc.setText(ServersExt.get(list_ServersExt.getSelectedIndex()).getDescription());												
					}
				}
				catch(Exception e)
				{
					
				}
			}
		});
		
		
		//Menubar
		JMenuBar menuBar = new JMenuBar();
		frmButaneSites.setJMenuBar(menuBar);
		
		
				
		JMenu mnSupport = new JMenu("Support");
		menuBar.add(mnSupport);
		
		JMenuItem mntmNewTicket = new JMenuItem("New Ticket");
		mntmNewTicket.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				g_MainMenu.checkVersion();
				if(c_CheckOpenTickets.CheckTickets())
				{							
					g_TicketEntry.run(frmButaneSites);
				}
				else
				{
					JOptionPane.showMessageDialog(frmButaneSites, "Open Ticket Limit Exceeded. \n Please Close Old Tickets.");
				}
				
			}
		});
		mnSupport.add(mntmNewTicket);
		
		JMenuItem mntmCurrentTickets = new JMenuItem("Current Tickets");
		mntmCurrentTickets.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				g_CurrentTickets.run(frmButaneSites);
			}
		});
		mnSupport.add(mntmCurrentTickets);
		
		JMenuItem mntmArchive = new JMenuItem("Archive");
		mntmArchive.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				g_ArchiveTickets.run(frmButaneSites);
			}
		});
		mnSupport.add(mntmArchive);
		
		JMenu mnSites = new JMenu("Sites");
		menuBar.add(mnSites);
		
		JMenuItem mntmButaneSites = new JMenuItem("Butane Sites");		
		mnSites.add(mntmButaneSites);
		mntmButaneSites.setEnabled(false);
		
		JMenuItem mntmSiteChanges = new JMenuItem("Site Changes");
		mntmSiteChanges.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				g_SiteChanges.run(frmButaneSites);
			}
		});
		mnSites.add(mntmSiteChanges);
		
		JMenu mnContacts = new JMenu("Contacts");
		menuBar.add(mnContacts);
		
		JMenuItem mntmENEmployees = new JMenuItem("EN Employees");
		mntmENEmployees.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				g_ViewEmployees.run(frmButaneSites);
			}
		});
		mnContacts.add(mntmENEmployees);
		
		JMenuItem mntmEtpContacts = new JMenuItem("ETP Contacts");
		mntmEtpContacts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				g_ViewSunoco.run(frmButaneSites);
			}
		});
		mnContacts.add(mntmEtpContacts);
		
		JMenu mnTools = new JMenu("Tools");
		menuBar.add(mnTools);
		
		JMenuItem mntmCreateChecklist = new JMenuItem("Create Checklist");
		mntmCreateChecklist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				g_Tools_CreateChecklist.run(frmButaneSites);
			}
		});		
		mnTools.add(mntmCreateChecklist);

	}
	
	private void CancelEdits()
	{
		if(rdbtnButaneSites.isSelected())
		{
			txtClient.setEditable(false);
			txtSite.setEditable(false);
			txtSiteID.setEditable(false);
			txtClientAbbrv.setEditable(false);
			txtSiteAbbrv.setEditable(false);
			txtiDrac.setEditable(false);
			txtState.setEditable(false);
			txtStateAbbrv.setEditable(false);
			txtHost.setEditable(false);
			txtView.setEditable(false);
			txtSQL.setEditable(false);
			txtDev.setEditable(false);
			txtGeneration.setEditable(false);			
			txtAddress.setEditable(false);
			txtPhone.setEditable(false);
			txtFieldTech.setEditable(false);
			txtFieldSupervisor.setEditable(false);
			txtTimezone.setEditable(false);
			chk_HighPerformance.setEnabled(false);
			chk_Twic.setEnabled(false);
			chk_SQL.setEnabled(false);
			chk_View.setEnabled(false);
			
			lbl_View.setVisible(true);
			lbl_SQL.setVisible(true);
			lbl_Dev.setVisible(true);
			lbl_iDrac.setVisible(true);
			lbl_Host.setVisible(true);
			txtView.setVisible(false);
			txtSQL.setVisible(false);
			txtDev.setVisible(false);
			txtiDrac.setVisible(false);
			txtHost.setVisible(false);
		}
		else
		{
			txt_ServerName.setEditable(false);
			txt_ServerIP.setEditable(false);
			txt_ServerDesc.setEditable(false);
		}
		
		btnEdit.setEnabled(true);
		btnEdit.setVisible(true);
		btnAccept.setEnabled(false);
		btnAccept.setVisible(false);
	}
	
	/*
	private void PopulateSitesTree()
	{
		try {				
			tree_sites.setModel(new DefaultTreeModel(
					new DefaultMutableTreeNode("Sites") {
						private static final long serialVersionUID = 1L;
						{
							DefaultMutableTreeNode node_category;
							DefaultMutableTreeNode node_client;
							DefaultMutableTreeNode node_ticket;
							
							String commandText = "SELECT DISTINCT Client from SupportTickets ORDER BY Client ASC";
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
									 
									
																			
									//String commandText2 = "SELECT Ticket from SupportTickets WHERE Active = 0 and Site ='" + tmpSite + "' and Client = '" + tmpClient + "' ORDER BY Site asc, Ticket";
									 String commandText2 = "SELECT [Client],[Site],[State],[SiteID],[ClientAbbrv],[SiteAbbrv],[iDracIP],[HostIP],[ViewIP],[SQLIP],[DevIP],[Generation],[HMI],[Address],[Phone],[Field_Tech],[Field_Supervisor],[Twic],[Timezone],[HighPerformance] FROM Sites WHERE client = '" + tmpClient + "' and site = '" + tmpSite +"' ORDER BY Client asc, Site asc";										
									ResultSet rs2 = c_Query.ExecuteResultSet(commandText2);										
									while((rs2!=null) && (rs2.next()))
									{											
											//node_ticket = new DefaultMutableTreeNode(rs2.getString("Ticket"));																
											//node_client.add(node_ticket);		
										c_ButaneSites sites = new c_ButaneSites();
										 sites.setClient(rs2.getString(1));
										  sites.setSite(rs2.getString(2));
										  sites.setState(rs2.getString(3));
										  sites.setSiteID(rs2.getInt(4));
										  sites.setClientAbbrv(rs2.getString(5));
										  sites.setSiteAbbrv(rs2.getString(6));
										  sites.setiDrac(rs2.getInt(7));
										  sites.setHost(rs2.getInt(8));
										  sites.setView(rs2.getInt(9));
										  sites.setSQL(rs2.getInt(10));
										  sites.setDev(rs2.getInt(11));
										  sites.setGeneration(rs2.getFloat(12));
										  sites.setHMI(rs2.getString(13));
										  sites.setAddress(rs2.getString(14));
										  sites.setPhone(rs2.getString(15));
										  sites.setFieldTech(rs2.getString(16));
										  sites.setFieldSupervisor(rs2.getString(17));
										  sites.setTwic(rs2.getBoolean(18));
										  sites.setTimezone(rs2.getString(19));
										  sites.setHighPerformance(rs2.getBoolean(20));	
										node_client = new DefaultMutableTreeNode(sites);
										node_category.add(node_client);
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
	*/
	private void PopulateSites()
	{	
		String client, site, state, clientabbrv, siteabbrv, address, phone, fieldtech, fieldsupervisor, timezone, stateabbrv, sharepoint;
		int siteid, idrac, host, view, sql, dev;
		float generation;
		boolean twic, highperformance, centralSQL, centralView;

		String commandText = "SELECT [Client],[Site],[State],[StateAbbrv],[SiteID],[ClientAbbrv],[SiteAbbrv],[iDracIP],[HostIP],[ViewIP],[SQLIP],[DevIP],[Generation],[CentralSQL],[Address],[Phone],[Field_Tech],[Field_Supervisor],[Twic],[Timezone],[HighPerformance],[CentralView],[SharepointURL] FROM Sites ORDER BY Client asc, Site asc";
		ResultSet rs = c_Query.ExecuteResultSet(commandText);
		try{

			while ((rs!=null) && (rs.next()))
			{
				client = rs.getString("Client"); 
				site = rs.getString("Site");
				state = rs.getString("State");
				stateabbrv = rs.getString("StateAbbrv");
				clientabbrv = rs.getString("ClientAbbrv");
				siteabbrv = rs.getString("SiteAbbrv");
				address = rs.getString("Address");
				phone = rs.getString("Phone");
				fieldtech = rs.getString("Field_Tech");
				fieldsupervisor = rs.getString("Field_Supervisor");
				timezone = rs.getString("Timezone");
				siteid = rs.getInt("SiteID");
				idrac = rs.getInt("iDracIP");
				host = rs.getInt("HostIP");
				view = rs.getInt("ViewIP");
				sql = rs.getInt("SQLIP");
				dev = rs.getInt("DevIP");
				generation = rs.getFloat("Generation");
				twic = rs.getBoolean("Twic");
				highperformance = rs.getBoolean("HighPerformance");
				centralSQL = rs.getBoolean("CentralSQL");
				centralView = rs.getBoolean("CentralView");
				sharepoint = rs.getString("SharepointURL");
				c_ButaneSites sites = new c_ButaneSites(client,site,state,stateabbrv,siteid,clientabbrv,siteabbrv,idrac,host,view,sql,dev,generation,address,phone,fieldtech,fieldsupervisor,twic,timezone,highperformance,centralSQL,centralView, sharepoint);
				sites.setClient(client);
				sites.setSite(site);
				sites.setState(state);
				sites.setStateAbbrv(stateabbrv);
				sites.setSiteID(siteid);
				sites.setClientAbbrv(clientabbrv);
				sites.setSiteAbbrv(siteabbrv);
				sites.setiDrac(idrac);
				sites.setHost(host);
				sites.setView(view);
				sites.setSQL(sql);
				sites.setDev(dev);
				sites.setGeneration(generation);				  
				sites.setCentralSQL(centralSQL);
				sites.setAddress(address);
				sites.setPhone(phone);
				sites.setFieldTech(fieldtech);
				sites.setFieldSupervisor(fieldsupervisor);
				sites.setTwic(twic);
				sites.setTimezone(timezone);
				sites.setHighPerformance(highperformance);
				sites.setCentralView(centralView);		
				sites.setSharepoint(sharepoint);
				result.add(sites);
				listModel.addElement(sites.getName());				  
			}
		}
		catch(Exception e)
		{
			System.out.println("ERROR " + e.toString());
		}
		
		scrollPane_ButaneSites.setViewportView(list_ButaneSites);

		list_ButaneSites.setModel(listModel);
		
		list_ButaneSites.setSelectedIndex(0);
		
	}
	
	private void PopulateServersExt()
	{	
		String commandText = "SELECT [ServerName],[ServerIP],[Description] FROM Servers WHERE Internal = 0 ORDER BY ServerName";
		ResultSet rs = c_Query.ExecuteResultSet(commandText);
		try{

			while ((rs!=null) && (rs.next()))
			{
				  c_Servers servers = new c_Servers();
				  servers.setServer(rs.getString(1));
				  servers.setIP(rs.getString(2));
				  servers.setDescription(rs.getString(3));				 		  
				  ServersExt.add(servers);
				  listModel2.addElement(servers.getName());				  
			}
		}
		catch(Exception e)
		{
			System.out.println("ERROR " + e.toString());
		}
		
		scrollPane_ServersExt.setViewportView(list_ServersExt);
		
		list_ServersExt.setModel(listModel2);		
		
	}
	
	
	private void PopulateServersInt()
	{	

		String commandText = "SELECT [ServerName],[ServerIP],[Description] FROM Servers WHERE Internal = 1 ORDER BY ServerName";
		ResultSet rs = c_Query.ExecuteResultSet(commandText);
		try{

			while ((rs!=null) && (rs.next()))
			{
				  c_Servers servers = new c_Servers();
				  servers.setServer(rs.getString(1));
				  servers.setIP(rs.getString(2));
				  servers.setDescription(rs.getString(3));				 		  
				  ServersInt.add(servers);
				  listModel3.addElement(servers.getName());				  
			}
		}
		catch(Exception e)
		{
			System.out.println("ERROR " + e.toString());
		}
		
		scrollPane_ServersInt.setViewportView(list_ServersInt);

		list_ServersInt.setModel(listModel3);			
	}
	
	/* This function will refresh the list of the JPanel on the left hand side after an edit has been made. */
	private void RefreshList()
	{
		int index = list_ButaneSites.getSelectedIndex();
		listModel.removeAllElements();
		
		list_ButaneSites.setModel(listModel);
		PopulateSites();
		//PopulateSitesTree();
		list_ButaneSites.setSelectedIndex(index);
	}
	
	/* This function will refresh the list of the JPanel on the left hand side after an edit has been made. */
	private void RefreshListServersExt()
	{
		int index = list_ServersExt.getSelectedIndex();		
		listModel2.removeAllElements();		
		list_ServersExt.setModel(listModel2);		
		PopulateServersExt();		
		list_ServersExt.setSelectedIndex(index);		
	}
	
	/* This function will refresh the list of the JPanel on the left hand side after an edit has been made. */
	private void RefreshListServersInt()
	{
		int index = list_ServersInt.getSelectedIndex();		
		listModel3.removeAllElements();		
		list_ServersInt.setModel(listModel3);
		PopulateServersInt();		
		list_ServersInt.setSelectedIndex(index);
		
	}
	
	
	
	private void AcceptEdits()
	{
		if(rdbtnButaneSites.isSelected())
		{
			int siteID = result.get(list_ButaneSites.getSelectedIndex()).getSiteID();

			result.get(list_ButaneSites.getSelectedIndex()).updateSites(txtClient.getText(),  txtSite.getText(),  txtState.getText(),  txtStateAbbrv.getText(), Integer.parseInt(txtSiteID.getText()),  txtClientAbbrv.getText(),  txtSiteAbbrv.getText(), Integer.parseInt(txtiDrac.getText()), 
					Integer.parseInt(txtHost.getText()), Integer.parseInt(txtView.getText()), Integer.parseInt(txtSQL.getText()), Integer.parseInt(txtDev.getText()), Float.parseFloat(txtGeneration.getText()),  txtAddress.getText(),  txtPhone.getText(),  txtFieldTech.getText(),  txtFieldSupervisor.getText(),
				chk_Twic.isSelected(),  txtTimezone.getText(), chk_HighPerformance.isSelected(), chk_SQL.isSelected(), chk_View.isSelected());
			
			
			String commandText = "Update Sites set Client = '" + txtClient.getText() + "', Site = '" + txtSite.getText() + "', State = '" + txtState.getText() + "', StateAbbrv = '" + txtStateAbbrv.getText() + "', SiteID = '" + txtSiteID.getText() + "', ClientAbbrv = '" + txtClientAbbrv.getText() + "', SiteAbbrv = '" + txtSiteAbbrv.getText() + "', iDracIP = '" + txtiDrac.getText() + "', HostIP = '" + txtHost.getText() + "', ViewIP = '" + txtView.getText() + "', SQLIP = '" + txtSQL.getText() + "', DevIP = '" + txtDev.getText() + "', Generation = '" + txtGeneration.getText() + "', Address = '" + txtAddress.getText() + "', Phone = '" + txtPhone.getText() + "', Field_Tech = '" + txtFieldTech.getText() + "', Field_Supervisor = '" + txtFieldSupervisor.getText() + "', Twic = '" + chk_Twic.isSelected() + "', Timezone = '" + txtTimezone.getText() + "', HighPerformance = '" + chk_HighPerformance.isSelected() + "', CentralSQL = '" + chk_SQL.isSelected() +"', CentralView = '" + chk_View.isSelected() + "' WHERE SiteID = '" + siteID + "'";
			
			c_Query.ExecuteQuery(commandText);		
			result.set(list_ButaneSites.getSelectedIndex(), result.get(list_ButaneSites.getSelectedIndex()));
			RefreshList();
		}
		else
		{
			String serverName = "";
			try
			{
				serverName = ServersExt.get(list_ServersExt.getSelectedIndex()).getServer();			
			}
			catch(Exception e)
			{}

			if(serverName.compareTo("") == 0)
			{
				serverName = txt_ServerName.getText();	
				ServersInt.get(list_ServersInt.getSelectedIndex()).updateServer(txt_ServerName.getText(),txt_ServerIP.getText(),txt_ServerDesc.getText());
				String commandText = "Update Servers set ServerName = '" + txt_ServerName.getText() + "', ServerIP = '" + txt_ServerIP.getText() + "', Description = '" + txt_ServerDesc.getText() + "' WHERE ServerName = '" + list_ServersInt.getSelectedValue().toString() + "'";				
				c_Query.ExecuteQuery(commandText);					
				ServersInt.set(list_ServersInt.getSelectedIndex(), ServersInt.get(list_ServersInt.getSelectedIndex()));
				RefreshListServersInt();				
			}
			else
			{
				ServersExt.get(list_ServersExt.getSelectedIndex()).updateServer(txt_ServerName.getText(),txt_ServerIP.getText(),txt_ServerDesc.getText());
				String commandText = "Update Servers set ServerName = '" + txt_ServerName.getText() + "', ServerIP = '" + txt_ServerIP.getText() + "', Description = '" + txt_ServerDesc.getText() + "' WHERE ServerName = '" + serverName + "'";
				c_Query.ExecuteQuery(commandText);					
				ServersExt.set(list_ServersExt.getSelectedIndex(), ServersExt.get(list_ServersExt.getSelectedIndex()));
				RefreshListServersExt();				
			}
		}
		
		
	}
}
