import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import net.miginfocom.swing.MigLayout;
import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import java.awt.Toolkit;

public class g_ViewSiteData {

	private JFrame frmSiteData;
	JPanel panel = new JPanel();
	static JPanel panel_1 = new JPanel();	
	private static c_SiteInfo siteInfo = new c_SiteInfo();
	private JLabel txtHost;
	private JLabel txtView;
	private JLabel txtSQL;
	private JLabel txtDev;
	private JLabel txtiDrac;
	final JPanel panel_2 = new JPanel();
	private JPanel panel_4;
	private JScrollPane scrollPane;
	private JLabel lblSite;
	private JLabel lblPhone;
	private JLabel lblAddress;
	private JLabel lblHMI;
	private JLabel lblPLC;
	private JLabel lblClientSite;
	private JLabel lblTwic;
	private JLabel lblFieldTech;
	private JLabel lblFieldSupervisor;
	private JPanel panel_6;
	
	/**
	 * Launch the application.
	 */
	
			public static void run(String site) {
				try {
					g_ViewSiteData window = new g_ViewSiteData(siteInfo);
					window.frmSiteData.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		
	

	/**
	 * Create the application.
	 * @param site 
	 */
	public g_ViewSiteData(c_SiteInfo site) {
		initialize();
		PopulateServerInfo();
		PopulateInjectorData();
		GenerateSiteData();
		PopulateSystemInfo();
		PopulateTankInfo();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmSiteData = new JFrame();
		frmSiteData.setResizable(false);
		frmSiteData.setIconImage(Toolkit.getDefaultToolkit().getImage(g_ViewSiteData.class.getResource("/icon.png")));
		frmSiteData.setBounds(100, 100, 869, 867);
		frmSiteData.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmSiteData.getContentPane().setLayout(null);			
		frmSiteData.setTitle("Automated Support Program v." + g_MainMenu.version);
		
				
		panel.setBackground(new Color(245, 245, 245));
		panel.setBounds(0, 0, 850, 800);
		frmSiteData.getContentPane().add(panel);
		panel.setLayout(null);
		panel_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		
		
		panel_1.setBounds(580, 11, 259, 190);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblClient = new JLabel("Server Information");
		lblClient.setBounds(0, 10, 200, 22);
		panel_1.add(lblClient);
		lblClient.setHorizontalAlignment(SwingConstants.CENTER);
		lblClient.setFont(new Font("Plantagenet Cherokee", Font.BOLD, 16));
		
		
		
		JLabel lblViewIp = new JLabel("View:");
		lblViewIp.setBounds(10, 73, 51, 19);
		panel_1.add(lblViewIp);
		lblViewIp.setFont(new Font("Monospaced", Font.BOLD, 14));
		
		JLabel lblSqlIp = new JLabel("SQL:");
		lblSqlIp.setBounds(10, 103, 51, 19);
		panel_1.add(lblSqlIp);
		lblSqlIp.setFont(new Font("Monospaced", Font.BOLD, 14));
		
		JLabel lblDevIp = new JLabel("Dev:");
		lblDevIp.setBounds(10, 133, 51, 19);
		panel_1.add(lblDevIp);
		lblDevIp.setFont(new Font("Monospaced", Font.BOLD, 14));
		
		JLabel lblIdracIp = new JLabel("iDrac:");
		lblIdracIp.setBounds(10, 163, 51, 19);
		panel_1.add(lblIdracIp);
		lblIdracIp.setFont(new Font("Monospaced", Font.BOLD, 14));
		
		JLabel lblHostIp = new JLabel("Host:");
		lblHostIp.setHorizontalAlignment(SwingConstants.LEFT);
		lblHostIp.setBounds(10, 43, 51, 19);
		panel_1.add(lblHostIp);
		lblHostIp.setFont(new Font("Monospaced", Font.BOLD, 14));
		
		txtHost = new JLabel();
		txtHost.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		txtHost.setBackground(new Color(240, 240, 240));			
		txtHost.setBounds(69, 43, 95, 22);
		panel_1.add(txtHost);
	
		txtView = new JLabel();
		txtView.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		txtView.setBounds(69, 73, 95, 22);
		panel_1.add(txtView);
		
		txtSQL = new JLabel();
		txtSQL.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		txtSQL.setBounds(69, 103, 95, 22);
		panel_1.add(txtSQL);
		
		txtDev = new JLabel();
		txtDev.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		txtDev.setBounds(69, 133, 95, 22);
		panel_1.add(txtDev);
		
		txtiDrac = new JLabel();
		txtiDrac.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		txtiDrac.setBounds(69, 163, 95, 22);
		panel_1.add(txtiDrac);
		
		
		panel_2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_2.setBounds(10, 212, 381, 262);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel label = new JLabel("Injection");
		label.setVerticalAlignment(SwingConstants.TOP);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Plantagenet Cherokee", Font.BOLD, 16));
		label.setBounds(0, 10, 381, 22);
		panel_2.add(label);
		
		JLabel lblNewLabel_3 = new JLabel("Injector");
		lblNewLabel_3.setBounds(10, 44, 75, 20);
		lblNewLabel_3.setFont(new Font("Monospaced", Font.BOLD, 14));
		panel_2.add(lblNewLabel_3);
		
		JLabel lblProductType = new JLabel("Type");
		lblProductType.setBounds(103, 44, 45, 20);
		lblProductType.setFont(new Font("Monospaced", Font.BOLD, 14));
		panel_2.add(lblProductType);
		
		JLabel lblInjectorSize = new JLabel("Size");
		lblInjectorSize.setBounds(240, 44, 45, 20);
		lblInjectorSize.setFont(new Font("Monospaced", Font.BOLD, 14));
		panel_2.add(lblInjectorSize);
		
		JLabel lblNotes = new JLabel("Notes");
		lblNotes.setBounds(305, 44, 51, 20);
		lblNotes.setFont(new Font("Monospaced", Font.BOLD, 14));
		panel_2.add(lblNotes);
		
		JLabel lblGas = new JLabel("Gas");
		lblGas.setFont(new Font("Monospaced", Font.BOLD, 14));
		lblGas.setBounds(175, 44, 45, 20);
		panel_2.add(lblGas);
		
				
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_3.setBounds(10, 11, 560, 190);
		panel.add(panel_3);
		panel_3.setLayout(null);
		
		lblAddress = new JLabel("3900 Singleton Blvd. Dallas, TX 75212");
		lblAddress.setBounds(10, 73, 376, 19);
		lblAddress.setHorizontalAlignment(SwingConstants.LEFT);
		lblAddress.setFont(new Font("Monospaced", Font.BOLD, 14));
		panel_3.add(lblAddress);
		
		lblPhone = new JLabel("Phone #: 336-299-0331");
		lblPhone.setHorizontalAlignment(SwingConstants.LEFT);
		lblPhone.setFont(new Font("Monospaced", Font.BOLD, 14));
		lblPhone.setBounds(10, 43, 170, 19);
		panel_3.add(lblPhone);
		
		lblSite = new JLabel("Motiva Greensboro (4)");
		lblSite.setFont(new Font("Plantagenet Cherokee", Font.BOLD, 18));
		lblSite.setBounds(10, 7, 201, 25);
		panel_3.add(lblSite);
		
		lblHMI = new JLabel("HMI: Archestra");
		lblHMI.setHorizontalAlignment(SwingConstants.LEFT);
		lblHMI.setFont(new Font("Monospaced", Font.BOLD, 14));
		lblHMI.setBounds(417, 50, 133, 19);
		panel_3.add(lblHMI);
		
		lblPLC = new JLabel("PLC: Gen 3");
		lblPLC.setHorizontalAlignment(SwingConstants.LEFT);
		lblPLC.setFont(new Font("Monospaced", Font.BOLD, 14));
		lblPLC.setBounds(417, 30, 133, 19);
		panel_3.add(lblPLC);
		
		lblClientSite = new JLabel("MTV-GNB");
		lblClientSite.setHorizontalAlignment(SwingConstants.LEFT);
		lblClientSite.setFont(new Font("Monospaced", Font.BOLD, 14));
		lblClientSite.setBounds(417, 7, 133, 19);
		panel_3.add(lblClientSite);
		
		lblTwic = new JLabel("TWIC: No");
		lblTwic.setHorizontalAlignment(SwingConstants.LEFT);
		lblTwic.setFont(new Font("Monospaced", Font.BOLD, 14));
		lblTwic.setBounds(417, 76, 133, 19);
		panel_3.add(lblTwic);
		
		lblFieldTech = new JLabel("Field Tech: Don Freeman");
		lblFieldTech.setHorizontalAlignment(SwingConstants.LEFT);
		lblFieldTech.setFont(new Font("Monospaced", Font.BOLD, 14));
		lblFieldTech.setBounds(10, 128, 376, 19);
		panel_3.add(lblFieldTech);
		
		lblFieldSupervisor = new JLabel("Supervisor: Joe Klems");
		lblFieldSupervisor.setHorizontalAlignment(SwingConstants.LEFT);
		lblFieldSupervisor.setFont(new Font("Monospaced", Font.BOLD, 14));
		lblFieldSupervisor.setBounds(10, 158, 376, 19);
		panel_3.add(lblFieldSupervisor);
		
		panel_4 = new JPanel();
		panel_4.setLayout(null);
		panel_4.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_4.setBounds(580, 212, 259, 577);
		panel.add(panel_4);
		
		JLabel lblSystemDevices = new JLabel("System Devices");
		lblSystemDevices.setHorizontalAlignment(SwingConstants.CENTER);
		lblSystemDevices.setFont(new Font("Plantagenet Cherokee", Font.BOLD, 16));
		lblSystemDevices.setBounds(10, 11, 239, 22);
		panel_4.add(lblSystemDevices);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 259, 587);
		panel_4.add(scrollPane);
		scrollPane.setViewportBorder(null);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setLayout(null);
		
		JPanel panel_5 = new JPanel();
		panel_5.setLayout(null);
		panel_5.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_5.setBounds(10, 486, 318, 262);
		panel.add(panel_5);
		
		JLabel lbl_Sampling = new JLabel("Sampling");
		lbl_Sampling.setVerticalAlignment(SwingConstants.TOP);
		lbl_Sampling.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_Sampling.setFont(new Font("Plantagenet Cherokee", Font.BOLD, 16));
		lbl_Sampling.setBounds(10, 10, 290, 22);
		panel_5.add(lbl_Sampling);
		
		JLabel lblLine = new JLabel("Line #");
		lblLine.setFont(new Font("Monospaced", Font.BOLD, 14));
		lblLine.setBounds(10, 44, 75, 20);
		panel_5.add(lblLine);
		
		JLabel label_3 = new JLabel("Type");
		label_3.setFont(new Font("Monospaced", Font.BOLD, 14));
		label_3.setBounds(103, 44, 45, 20);
		panel_5.add(label_3);
		
		JLabel lblProduct = new JLabel("Product");
		lblProduct.setFont(new Font("Monospaced", Font.BOLD, 14));
		lblProduct.setBounds(172, 44, 87, 20);
		panel_5.add(lblProduct);
		
		panel_6 = new JPanel();
		panel_6.setLayout(null);
		panel_6.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_6.setBounds(396, 212, 174, 262);
		panel.add(panel_6);
		
		JLabel lblTanks = new JLabel("Butane Tanks");
		lblTanks.setVerticalAlignment(SwingConstants.TOP);
		lblTanks.setHorizontalAlignment(SwingConstants.CENTER);
		lblTanks.setFont(new Font("Plantagenet Cherokee", Font.BOLD, 16));
		lblTanks.setBounds(10, 10, 154, 22);
		panel_6.add(lblTanks);
		
		JLabel lblTank = new JLabel("#");
		lblTank.setFont(new Font("Monospaced", Font.BOLD, 14));
		lblTank.setBounds(10, 44, 31, 20);
		panel_6.add(lblTank);
		
		JLabel lblCapacity = new JLabel("Capacity");
		lblCapacity.setFont(new Font("Monospaced", Font.BOLD, 14));
		lblCapacity.setBounds(99, 44, 83, 20);
		panel_6.add(lblCapacity);
		
		JLabel lblType = new JLabel("Type");
		lblType.setFont(new Font("Monospaced", Font.BOLD, 14));
		lblType.setBounds(36, 43, 72, 20);
		panel_6.add(lblType);
		
		
		
		 JPanel panel = new JPanel(new MigLayout());
		 JLabel label2 = new JLabel("Server Information");
		    panel.add(label2);
		   
		    
		    
	}
	
	
	public void PopulateSystemInfo()
	{
		int y = 0;
		String commandText = "SELECT [SiteID], [IO], [Device], [DeviceIP] FROM SiteDevices WHERE (SiteID = 79) ORDER BY DeviceIP";
		ResultSet rs = c_Query.ExecuteResultSet(commandText);
		int io = 0;
		int id = 0;
		int deviceip = 0;
		String label = "";
		String device = "";
		String hmi = "";
		try
		{
			while((rs!=null) && (rs.next()))
			{
				device = rs.getString("Device");
				id = rs.getInt("SiteID");
				deviceip = rs.getInt("DeviceIP");
				io = rs.getInt("IO");
				if(io == 1)
				{
					io = 218;
					hmi = " IO";
				}
				else
				{
					io = 219;
					hmi = " HMI";
				}
				label = device + hmi + ": " + "10." + io + "." + id + "." + deviceip;
				JLabel label_6 = new JLabel(label);
				label_6.setHorizontalAlignment(SwingConstants.LEFT);
				label_6.setFont(new Font("Monospaced", Font.BOLD, 14));
				label_6.setBounds(10, 43+y, 250, 20);
				scrollPane.add(label_6);
				y = y + 30;
				
			}
		}
		catch(Exception e)
		{
			
		}
		
	}
	
	public void PopulateTankInfo()
	{
		String commandText = "SELECT [Tank],[TankType],[TankVolume] From SiteTankDetails WHERE SiteID = 79 ORDER BY Tank";
		ResultSet rs = c_Query.ExecuteResultSet(commandText);
		int y = 0;
		try
		{
			while((rs!=null) && (rs.next()))
			{
				

				JLabel textField_1 = new JLabel();
				textField_1.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
				textField_1.setBounds(10, 72+y, 31, 22);
				textField_1.setFont(new Font("Monospaced", Font.PLAIN, 12));
				textField_1.setText(rs.getString("Tank"));
				panel_6.add(textField_1);
				
				JLabel textField_2 = new JLabel();
				textField_2.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
				textField_2.setBounds(36, 72+y, 72, 22);
				textField_2.setFont(new Font("Monospaced", Font.PLAIN, 12));
				textField_2.setText(rs.getString("TankType"));
				panel_6.add(textField_2);
				
				JLabel textField_3 = new JLabel();
				textField_3.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
				textField_3.setFont(new Font("Monospaced", Font.PLAIN, 12));
				textField_3.setBounds(99, 71+y, 83, 22);
				textField_3.setText(rs.getString("TankVolume"));
				panel_6.add(textField_3);
				
				y = y + 30;
				
			}
		
		}
		catch(Exception e)
		{
			
		}
		
	}
	
	public void PopulateServerInfo()
	{
		
		String commandText = "SELECT [Client],[Site],[State],[SiteID],[ClientAbbrv],[SiteAbbrv],[iDracIP],[HostIP],[ViewIP],[SQLIP],[DevIP],[Generation],[HMI],[Address],[Phone],[Field_Tech],[Field_Supervisor],[Twic] FROM Sites WHERE SiteID = 79";
		ResultSet rs = c_Query.ExecuteResultSet(commandText);		
		try
		{
			while((rs!=null) && (rs.next()))
			{
				txtHost.setText("10.219." + "79" + "." + rs.getString("HostIP"));
				txtView.setText("10.219." + "79" + "." + rs.getString("ViewIP"));
				txtSQL.setText("10.219." + "79" + "." + rs.getString("SQLIP"));
				txtDev.setText("10.219." + "79" + "." + rs.getString("DevIP"));
				txtiDrac.setText("10.219." + "79" + "." + rs.getString("iDracIP"));
				lblSite.setText(rs.getString("Client") + " " + rs.getString("Site") + " (" + rs.getInt("SiteID") + ")");
				lblPhone.setText(rs.getString("Phone"));
				lblAddress.setText(rs.getString("Address"));
				lblHMI.setText("HMI: " + rs.getString("HMI"));
				lblPLC.setText("PLC: " + rs.getString("Generation"));
				lblClientSite.setText(rs.getString("ClientAbbrv") + "-" + rs.getString("SiteAbbrv"));
				if(rs.getString("Twic").compareTo("0") == 0)
				{
					lblTwic.setText("TWIC: No");	
				}
				else
				{
					lblTwic.setText("TWIC: Yes");
				}
				
				lblFieldTech.setText("Field Tech: " + rs.getString("Field_Tech"));
				lblFieldSupervisor.setText("Supervisor: " + rs.getString("Field_Supervisor"));
			}
		}
		catch(Exception e)
		{
			System.out.println(e.toString());
		}
	}
	
	
	
	public void PopulateInjectorData()
	{
		int y = 0;
		String commandText = "SELECT [Injector], [InjectorType], [InjectorMMSize], [InjectorMisc], [GasType] FROM SiteInjectorDetails WHERE SiteID = 79";
		ResultSet rs = c_Query.ExecuteResultSet(commandText);		
		
		try
		{
			while((rs!=null) && (rs.next()))
			{
				String injector = "INJ0" + rs.getString("Injector");
				JLabel textField_1 = new JLabel();
				textField_1.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
				textField_1.setBounds(10, 72+y, 75, 22);
				textField_1.setFont(new Font("Monospaced", Font.PLAIN, 12));
				textField_1.setText(injector);
				panel_2.add(textField_1);
				
				JLabel textField_2 = new JLabel();
				textField_2.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
				textField_2.setBounds(95, 72+y, 59, 22);
				textField_2.setFont(new Font("Monospaced", Font.PLAIN, 12));
				textField_2.setText(rs.getString("InjectorType"));
				panel_2.add(textField_2);
				
				JLabel textField_3 = new JLabel();
				textField_3.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
				//textField_3.setBackground(SystemColor.menu);
				textField_3.setFont(new Font("Monospaced", Font.PLAIN, 12));
				textField_3.setBounds(172, 71+y, 45, 22);
				textField_3.setText(rs.getString("GasType"));
				panel_2.add(textField_3);
				
				JLabel textField_4 = new JLabel();
				textField_4.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
				//textField_3.setBackground(SystemColor.menu);
				textField_4.setFont(new Font("Monospaced", Font.PLAIN, 12));
				textField_4.setBounds(240, 71+y, 45, 22);
				textField_4.setText(rs.getString("InjectorMMSize") + "\"");
				panel_2.add(textField_4);
				
				final String injNotes = rs.getString(4);
				if(injNotes != null && !injNotes.isEmpty())
				{
					JButton btnNewButton_1 = new JButton("View");
					btnNewButton_1.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							JOptionPane.showMessageDialog(frmSiteData, injNotes);
						}
					});
					btnNewButton_1.setBounds(302, 68+y+5, 62, 22);
					panel_2.add(btnNewButton_1);	
				}
				y = y + 30;
				
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		
		
	}
	
	
	public static void GenerateSiteData()
	{
	
		
	}
}
