import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
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
import java.awt.Color;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.JPanel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JCheckBox;
import java.awt.Toolkit;

public class g_ViewSites {

	public static JFrame frmButaneSites;
	private static JList<String> list = new JList<String>();
	private static DefaultListModel<String> listModel = new DefaultListModel<String>();
	private JScrollPane scrollPane;
	JTextField txtSite = new JTextField("");
	JTextField txtSiteID = new JTextField("");
	JTextField txtAddress = new JTextField("");
	JTextField txtPhone = new JTextField("");
	private final JButton btnEdit = new JButton("");
	private JTextField txtClient;
	private final JButton btnAccept = new JButton("");
	private final JButton btnCancel = new JButton("");
	final ArrayList <c_ButaneSites> result = new ArrayList<c_ButaneSites>();
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
	private JTextField txtHMI;
	private JTextField txtFieldTech;
	private JTextField txtFieldSupervisor;
	private JTextField txtTimezone;
	private JTextField txtState;
	private JCheckBox chk_HighPerformance;
	private JCheckBox chk_Twic;
	

	/**
	 * Launch the application.
	 */
	
			public static void run() {
				try {
					@SuppressWarnings("unused")
					g_ViewSites window = new g_ViewSites();
					g_ViewSites.frmButaneSites.setVisible(true);
					g_ViewSites.frmButaneSites.setLocationRelativeTo( g_MainMenu.frmMainMenu );
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
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmButaneSites = new JFrame();
		frmButaneSites.setIconImage(Toolkit.getDefaultToolkit().getImage(g_ViewSites.class.getResource("/icon.png")));
		frmButaneSites.setBounds(100, 100, 909, 730);
		frmButaneSites.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frmButaneSites.getContentPane().setLayout(null);		
		frmButaneSites.setTitle("Automated Support Program");
		
		
		frmButaneSites.addWindowListener(new java.awt.event.WindowAdapter() {
			 @Override
			    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				 listModel.removeAllElements();
				 list.setModel(listModel);
				 frmButaneSites.dispose(); 
			 }
		
		});

		scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		scrollPane.setBounds(36, 47, 263, 589);
		frmButaneSites.getContentPane().add(scrollPane);
		btnEdit.setToolTipText("Edit");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {				
				txtClient.setEditable(true);
				txtSite.setEditable(true);
				//txtSiteID.setEditable(true); do not allow siteID to be changed.
				txtClientAbbrv.setEditable(true);
				txtSiteAbbrv.setEditable(true);
				txtiDrac.setEditable(true);
				txtState.setEditable(true);
				txtHost.setEditable(true);
				txtView.setEditable(true);
				txtSQL.setEditable(true);
				txtDev.setEditable(true);
				txtGeneration.setEditable(true);
				txtHMI.setEditable(true);
				txtAddress.setEditable(true);
				txtPhone.setEditable(true);
				txtFieldTech.setEditable(true);
				txtFieldSupervisor.setEditable(true);
				txtTimezone.setEditable(true);	
				chk_HighPerformance.setEnabled(true);
				chk_Twic.setEnabled(true);
			
				btnEdit.setEnabled(false);
				btnEdit.setVisible(false);
				btnCancel.setVisible(true);
				btnCancel.setEnabled(true);
				btnAccept.setVisible(true);
				btnAccept.setEnabled(true);
			}
		});     
		btnEdit.setIcon(new ImageIcon(g_ViewSites.class.getResource("/edit.png")));
		btnEdit.setBounds(828, 11, 32, 32);
		
		frmButaneSites.getContentPane().add(btnEdit);
		btnAccept.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AcceptEdits();
			}
		});
		btnAccept.setToolTipText("Accept Edits");
		btnAccept.setEnabled(false);
		btnAccept.setVisible(false);
		btnAccept.setIcon(new ImageIcon(g_ViewSites.class.getResource("/ok-icon.png")));
		btnAccept.setBounds(786, 11, 34, 34);
		
		frmButaneSites.getContentPane().add(btnAccept);
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CancelEdits();
			}
		});
		btnCancel.setToolTipText("Cancel Edits");
		btnCancel.setEnabled(false);
		btnCancel.setIcon(new ImageIcon(g_ViewSites.class.getResource("/Actions-window-close-icon.png")));
		btnCancel.setBounds(828, 11, 32, 32);
		
		frmButaneSites.getContentPane().add(btnCancel);
		
		JLabel lblSites = new JLabel("Butane Sites");
		lblSites.setHorizontalAlignment(SwingConstants.CENTER);
		lblSites.setForeground(Color.RED);
		lblSites.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblSites.setBounds(36, 18, 260, 18);
		frmButaneSites.getContentPane().add(lblSites);
		btnAddSite.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				g_NewSite.run();				
				frmButaneSites.dispose();
			}
		});
		btnAddSite.setIcon(new ImageIcon(g_ViewSites.class.getResource("/Plus-48.png")));
		btnAddSite.setToolTipText("New Site");
		btnAddSite.setBounds(267, 638, 32, 32);
		
		frmButaneSites.getContentPane().add(btnAddSite);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				try{
					if(!arg0.getValueIsAdjusting())
					{
						CancelEdits();
						txtClient.setText(result.get(list.getSelectedIndex()).getClient());
						txtSite.setText(result.get(list.getSelectedIndex()).getSite());
						txtSiteID.setText(String.valueOf(result.get(list.getSelectedIndex()).getSiteID()));
						txtState.setText(result.get(list.getSelectedIndex()).getState());
						txtClientAbbrv.setText(result.get(list.getSelectedIndex()).getClientAbbrv());
						txtSiteAbbrv.setText(result.get(list.getSelectedIndex()).getSiteAbbrv());
						txtiDrac.setText(String.valueOf(result.get(list.getSelectedIndex()).getiDrac()));
						txtHost.setText(String.valueOf(result.get(list.getSelectedIndex()).getHost()));
						txtView.setText(String.valueOf(result.get(list.getSelectedIndex()).getView()));
						txtSQL.setText(String.valueOf(result.get(list.getSelectedIndex()).getSQL()));
						txtDev.setText(String.valueOf(result.get(list.getSelectedIndex()).getDev()));
						txtGeneration.setText(String.valueOf(result.get(list.getSelectedIndex()).getGeneration()));
						txtHMI.setText(result.get(list.getSelectedIndex()).getHMI());
						txtAddress.setText(result.get(list.getSelectedIndex()).getAddress());
						txtPhone.setText(result.get(list.getSelectedIndex()).getPhone());
						txtFieldTech.setText(result.get(list.getSelectedIndex()).getFieldTech());
						txtFieldSupervisor.setText(result.get(list.getSelectedIndex()).getFieldSupervisor());
						txtTimezone.setText(result.get(list.getSelectedIndex()).getTimezone());
						
						if(result.get(list.getSelectedIndex()).getTwic())
						{
							chk_Twic.setSelected(true);
						}
						else
						{
							chk_Twic.setSelected(false);
						}
						
						if(result.get(list.getSelectedIndex()).getHighPerformance())
						{
							chk_HighPerformance.setSelected(true);
						}
						else
						{
							chk_HighPerformance.setSelected(false);
						}
					}
				}
				catch(Exception e)
				{
					
				}
			}
		});
		
		listModel.removeAllElements();
		list.setModel(listModel);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				g_MainMenu.run(frmButaneSites);
				frmButaneSites.dispose();
			}
		});
		btnNewButton.setBounds(10, 660, 83, 32);
		
		frmButaneSites.getContentPane().add(btnNewButton);
		
		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setBounds(643, 47, 240, 161);
		frmButaneSites.getContentPane().add(panel);
		panel.setLayout(null);
		
		txtiDrac = new JTextField("");
		txtiDrac.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(!txtiDrac.isEditable())
				{
					String ipAddress = "10.219." + txtSiteID.getText() + "." + txtiDrac.getText(); 
					try {
						//Runtime.getRuntime().exec("iexplore.exe "+ipAddress);
						Runtime.getRuntime().exec("C:\\Program Files\\Internet Explorer\\iexplore.exe " + ipAddress);
					} catch (IOException e2) {
						// TODO Auto-generated catch block
						System.out.println(e2.toString());
					}
				}
			}
		});
		txtiDrac.setBounds(136, 95, 40, 30);
		txtiDrac.setColumns(1);
		panel.add(txtiDrac);
		txtiDrac.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		txtiDrac.setEditable(false);
		
		JLabel lbliDrac = new JLabel("iDrac");
		lbliDrac.setBounds(136, 130, 45, 14);
		panel.add(lbliDrac);
		lbliDrac.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblServer = new JLabel("Server Information");
		lblServer.setHorizontalAlignment(SwingConstants.CENTER);
		lblServer.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblServer.setBounds(0, 10, 240, 14);
		panel.add(lblServer);
		
		txtHost = new JTextField("");
		txtHost.setBounds(48, 95, 45, 30);
		panel.add(txtHost);
		txtHost.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		txtHost.setEditable(false);
		
		JLabel lblHost = new JLabel("Host");
		lblHost.setBounds(48, 130, 45, 14);
		panel.add(lblHost);
		lblHost.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		txtView = new JTextField("");
		txtView.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {				
				
				if(!txtView.isEditable())
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
					String ipAddress = "10.219." + txtSiteID.getText() + "." + txtView.getText(); 
					try {
						Runtime.getRuntime().exec(new String[] {"cmd.exe", "/C", "mstsc /v:"+ipAddress + " /w:" + width + " /h:" + height});
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		
		txtView.setBounds(28, 35, 45, 30);
		panel.add(txtView);
		txtView.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		txtView.setEditable(false);
		
		JLabel lblView = new JLabel("View");
		lblView.setBounds(28, 70, 45, 14);
		panel.add(lblView);
		lblView.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		txtSQL = new JTextField("");
		txtSQL.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(!txtSQL.isEditable())
				{
					String width = "1920";
					String height = "1080";			
					String ipAddress = "10.219." + txtSiteID.getText() + "." + txtSQL.getText(); 
					try {
						Runtime.getRuntime().exec(new String[] {"cmd.exe", "/C", "mstsc /v:"+ipAddress + " /w:" + width + " /h:" + height});
					} catch (IOException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
				}
			}
			
		});
		txtSQL.setBounds(88, 35, 45, 30);
		panel.add(txtSQL);
		txtSQL.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		txtSQL.setEditable(false);
		
		JLabel lblSQL = new JLabel("SQL");
		lblSQL.setBounds(88, 70, 45, 14);
		panel.add(lblSQL);
		lblSQL.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		txtDev = new JTextField("");
		txtDev.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(!txtDev.isEditable())
				{
					String width = "1920";
					String height = "1080";			
					String ipAddress = "10.219." + txtSiteID.getText() + "." + txtDev.getText(); 
					try {
						Runtime.getRuntime().exec(new String[] {"cmd.exe", "/C", "mstsc /v:"+ipAddress + " /w:" + width + " /h:" + height});
					} catch (IOException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
				}
			}
		});
		txtDev.setBounds(148, 35, 45, 30);
		panel.add(txtDev);
		txtDev.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		txtDev.setEditable(false);
		
		JLabel lblDev = new JLabel("Dev");
		lblDev.setBounds(148, 70, 45, 14);
		panel.add(lblDev);
		lblDev.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_1.setBounds(323, 47, 310, 161);
		frmButaneSites.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblClientInformation = new JLabel("Site Information");
		lblClientInformation.setHorizontalAlignment(SwingConstants.CENTER);
		lblClientInformation.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblClientInformation.setBounds(0, 11, 310, 14);
		panel_1.add(lblClientInformation);
		
		txtClient = new JTextField();
		txtClient.setBounds(10, 38, 130, 31);
		panel_1.add(txtClient);
		txtClient.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		txtClient.setBackground(UIManager.getColor("Button.background"));
		txtClient.setEditable(false);
		txtClient.setColumns(10);
		txtSite.setBounds(158, 36, 130, 30);
		panel_1.add(txtSite);
		txtSite.setEditable(false);
		
		
		txtSite.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		
		JLabel lblNewLabel = new JLabel("Client");
		lblNewLabel.setBounds(10, 70, 130, 14);
		panel_1.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblSite = new JLabel("Site");
		lblSite.setBounds(158, 70, 130, 14);
		panel_1.add(lblSite);
		lblSite.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtSiteID.setBounds(10, 102, 80, 30);
		panel_1.add(txtSiteID);
		txtSiteID.setEditable(false);
		
		
		txtSiteID.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		
		txtClientAbbrv = new JTextField("");
		txtClientAbbrv.setBounds(112, 102, 80, 30);
		panel_1.add(txtClientAbbrv);
		txtClientAbbrv.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		txtClientAbbrv.setEditable(false);
		
		txtSiteAbbrv = new JTextField("");
		txtSiteAbbrv.setBounds(215, 102, 80, 30);
		panel_1.add(txtSiteAbbrv);
		txtSiteAbbrv.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		txtSiteAbbrv.setEditable(false);
		
		JLabel lblSiteID = new JLabel("Site ID");
		lblSiteID.setBounds(10, 135, 80, 14);
		panel_1.add(lblSiteID);
		lblSiteID.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblClientAbbrv = new JLabel("Client Abbrv");
		lblClientAbbrv.setBounds(112, 135, 80, 14);
		panel_1.add(lblClientAbbrv);
		lblClientAbbrv.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblSiteAbbrv = new JLabel("Site Abbrv");
		lblSiteAbbrv.setBounds(215, 135, 80, 14);
		panel_1.add(lblSiteAbbrv);
		lblSiteAbbrv.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_2.setBounds(323, 235, 560, 278);
		frmButaneSites.getContentPane().add(panel_2);
		panel_2.setLayout(null);
		txtAddress.setBounds(113, 32, 310, 30);
		panel_2.add(txtAddress);
		txtAddress.setEditable(false);
		
		
		txtAddress.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		
		JLabel lblTerminalInformation = new JLabel("Terminal Information");
		lblTerminalInformation.setHorizontalAlignment(SwingConstants.CENTER);
		lblTerminalInformation.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTerminalInformation.setBounds(0, 7, 560, 14);
		panel_2.add(lblTerminalInformation);
		
		JLabel lblAddress = new JLabel("Address:");
		lblAddress.setBounds(10, 37, 77, 14);
		panel_2.add(lblAddress);
		lblAddress.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtPhone.setBounds(113, 125, 310, 30);
		panel_2.add(txtPhone);
		txtPhone.setEditable(false);
		
		txtPhone.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		
		JLabel lblPhone = new JLabel("Phone:");
		lblPhone.setBounds(10, 130, 113, 14);
		panel_2.add(lblPhone);
		lblPhone.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		JLabel lblFieldTech = new JLabel("Field Tech:");
		lblFieldTech.setBounds(10, 181, 113, 14);
		panel_2.add(lblFieldTech);
		lblFieldTech.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		txtFieldTech = new JTextField("");
		txtFieldTech.setBounds(113, 176, 310, 30);
		panel_2.add(txtFieldTech);
		txtFieldTech.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		txtFieldTech.setEditable(false);
		
		JLabel lblField = new JLabel("Field");
		lblField.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblField.setBounds(10, 231, 113, 14);
		panel_2.add(lblField);
		
		JLabel lblSupervisor = new JLabel("Supervisor:");
		lblSupervisor.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblSupervisor.setBounds(10, 253, 113, 14);
		panel_2.add(lblSupervisor);
		
		txtFieldSupervisor = new JTextField("");
		txtFieldSupervisor.setBounds(113, 236, 310, 30);
		panel_2.add(txtFieldSupervisor);
		txtFieldSupervisor.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		txtFieldSupervisor.setEditable(false);
		
		JLabel lblState = new JLabel("State");
		lblState.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblState.setBounds(10, 78, 77, 14);
		panel_2.add(lblState);
		
		txtState = new JTextField("");
		txtState.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		txtState.setEditable(false);
		txtState.setBounds(113, 73, 310, 30);
		panel_2.add(txtState);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_3.setBounds(323, 515, 560, 121);
		frmButaneSites.getContentPane().add(panel_3);
		panel_3.setLayout(null);
		
		JLabel lblMiscInformation = new JLabel("Misc Information");
		lblMiscInformation.setHorizontalAlignment(SwingConstants.CENTER);
		lblMiscInformation.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblMiscInformation.setBounds(0, 11, 560, 14);
		panel_3.add(lblMiscInformation);
		
		txtGeneration = new JTextField("");
		txtGeneration.setBounds(21, 52, 83, 30);
		panel_3.add(txtGeneration);
		txtGeneration.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		txtGeneration.setEditable(false);
		
		JLabel lblGeneration = new JLabel("Generation");
		lblGeneration.setBounds(21, 87, 83, 14);
		panel_3.add(lblGeneration);
		lblGeneration.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		txtHMI = new JTextField("");
		txtHMI.setBounds(135, 52, 83, 30);
		panel_3.add(txtHMI);
		txtHMI.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		txtHMI.setEditable(false);
		
		JLabel lblHmi = new JLabel("HMI");
		lblHmi.setBounds(135, 87, 83, 14);
		panel_3.add(lblHmi);
		lblHmi.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblTwic = new JLabel("TWIC");
		lblTwic.setBounds(476, 87, 60, 14);
		panel_3.add(lblTwic);
		lblTwic.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		txtTimezone = new JTextField("");
		txtTimezone.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		txtTimezone.setEditable(false);
		txtTimezone.setBounds(261, 52, 83, 30);
		panel_3.add(txtTimezone);
		
		JLabel lblTimezone = new JLabel("Timezone");
		lblTimezone.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTimezone.setBounds(261, 87, 83, 14);
		panel_3.add(lblTimezone);
		
		chk_HighPerformance = new JCheckBox("");
		chk_HighPerformance.setBounds(391, 52, 36, 23);
		panel_3.add(chk_HighPerformance);
		
		JLabel lblHighPerformance = new JLabel("High");
		lblHighPerformance.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblHighPerformance.setBounds(391, 73, 46, 14);
		panel_3.add(lblHighPerformance);
		
		JLabel lblPerformance = new JLabel("Performance");
		lblPerformance.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblPerformance.setBounds(375, 87, 79, 14);
		panel_3.add(lblPerformance);
		
		chk_Twic = new JCheckBox("");
		chk_Twic.setBounds(479, 56, 36, 23);
		panel_3.add(chk_Twic);
	}
	
	private void CancelEdits()
	{
		txtClient.setEditable(false);
		txtSite.setEditable(false);
		txtSiteID.setEditable(false);
		txtClientAbbrv.setEditable(false);
		txtSiteAbbrv.setEditable(false);
		txtiDrac.setEditable(false);
		txtState.setEditable(false);
		txtHost.setEditable(false);
		txtView.setEditable(false);
		txtSQL.setEditable(false);
		txtDev.setEditable(false);
		txtGeneration.setEditable(false);
		txtHMI.setEditable(false);
		txtAddress.setEditable(false);
		txtPhone.setEditable(false);
		txtFieldTech.setEditable(false);
		txtFieldSupervisor.setEditable(false);
		txtTimezone.setEditable(false);
		chk_HighPerformance.setEnabled(false);
		chk_Twic.setEnabled(false);
		
		btnEdit.setEnabled(true);
		btnEdit.setVisible(true);
		btnAccept.setEnabled(false);
		btnAccept.setVisible(false);
	}
	
	private void PopulateSites()
	{	
		
		String commandText = "SELECT [Client],[Site],[State],[SiteID],[ClientAbbrv],[SiteAbbrv],[iDracIP],[HostIP],[ViewIP],[SQLIP],[DevIP],[Generation],[HMI],[Address],[Phone],[Field_Tech],[Field_Supervisor],[Twic],[Timezone],[HighPerformance] FROM Sites ORDER BY Client asc, Site asc";
		ResultSet rs = c_Query.ExecuteResultSet(commandText);
		try{

			while ((rs!=null) && (rs.next()))
			{
				  c_ButaneSites sites = new c_ButaneSites();
				  sites.setClient(rs.getString(1));
				  sites.setSite(rs.getString(2));
				  sites.setState(rs.getString(3));
				  sites.setSiteID(rs.getInt(4));
				  sites.setClientAbbrv(rs.getString(5));
				  sites.setSiteAbbrv(rs.getString(6));
				  sites.setiDrac(rs.getInt(7));
				  sites.setHost(rs.getInt(8));
				  sites.setView(rs.getInt(9));
				  sites.setSQL(rs.getInt(10));
				  sites.setDev(rs.getInt(11));
				  sites.setGeneration(rs.getFloat(12));
				  sites.setHMI(rs.getString(13));
				  sites.setAddress(rs.getString(14));
				  sites.setPhone(rs.getString(15));
				  sites.setFieldTech(rs.getString(16));
				  sites.setFieldSupervisor(rs.getString(17));
				  sites.setTwic(rs.getBoolean(18));
				  sites.setTimezone(rs.getString(19));
				  sites.setHighPerformance(rs.getBoolean(20));				  
				  result.add(sites);
				  listModel.addElement(sites.getName());				  
			}
		}
		catch(Exception e)
		{
			System.out.println("ERROR " + e.toString());
		}
		
		scrollPane.setViewportView(list);

		list.setModel(listModel);
		
		list.setSelectedIndex(0);
		
	}
	
	/* This function will refresh the list of the JPanel on the left hand side after an edit has been made. */
	private void RefreshList()
	{
		int index = list.getSelectedIndex();
		listModel.removeAllElements();
		list.setModel(listModel);
		PopulateSites();
		list.setSelectedIndex(index);
	}
	
	
	private void AcceptEdits()
	{
		int siteID = result.get(list.getSelectedIndex()).getSiteID();

		result.get(list.getSelectedIndex()).updateSites(txtClient.getText(),  txtSite.getText(),  txtState.getText(), Integer.parseInt(txtSiteID.getText()),  txtClientAbbrv.getText(),  txtSiteAbbrv.getText(), Integer.parseInt(txtiDrac.getText()), 
				Integer.parseInt(txtHost.getText()), Integer.parseInt(txtView.getText()), Integer.parseInt(txtSQL.getText()), Integer.parseInt(txtDev.getText()), Float.parseFloat(txtGeneration.getText()),  txtHMI.getText(),  txtAddress.getText(),  txtPhone.getText(),  txtFieldTech.getText(),  txtFieldSupervisor.getText(),
			chk_Twic.isSelected(),  txtTimezone.getText(), chk_HighPerformance.isSelected());
		String commandText = "Update Sites set Client = '" + txtClient.getText() + "', Site = '" + txtSite.getText() + "', State = '" + txtState.getText() + "', SiteID = '" + txtSiteID.getText() + "', ClientAbbrv = '" + txtClientAbbrv.getText() + "', SiteAbbrv = '" + txtSiteAbbrv.getText() + "', iDracIP = '" + txtiDrac.getText() + "', HostIP = '" + txtHost.getText() + "', ViewIP = '" + txtView.getText() + "', SQLIP = '" + txtSQL.getText() + "', DevIP = '" + txtDev.getText() + "', Generation = '" + txtGeneration.getText() + "', HMI = '" + txtHMI.getText() + "', Address = '" + txtAddress.getText() + "', Phone = '" + txtPhone.getText() + "', Field_Tech = '" + txtFieldTech.getText() + "', Field_Supervisor = '" + txtFieldSupervisor.getText() + "', Twic = '" + chk_Twic.isSelected() + "', Timezone = '" + txtTimezone.getText() + "', HighPerformance = '" + chk_HighPerformance.isSelected() + "' WHERE SiteID = '" + siteID + "'";
		
		c_Query.ExecuteQuery(commandText);		
		result.set(list.getSelectedIndex(), result.get(list.getSelectedIndex()));
		RefreshList();
		
		
	}
}
