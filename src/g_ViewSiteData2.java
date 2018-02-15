import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JList;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.DefaultListModel;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import javax.swing.border.EtchedBorder;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
/* http://stackoverflow.com/questions/16157529/how-do-i-pass-objects-between-classes */
public class g_ViewSiteData2 {

	public static JFrame frmArchiveTickets;
	public static DefaultListModel<c_Files> fileListModel;
	public static DefaultListModel<c_Files> ExistingfileListModel;
	private List<String> ViewSiteData = new ArrayList<String>();
	private int rowID;
	private String TicketNum;
	public String siteID;
	private String directory = "\\\\10.10.38.252\\C$\\SupportProgram\\Files\\";
	private int numFiles; 
	private int viewIP;
	private int SQLIP; 
	private int DevIP; 
	private int iDracIP;
	private int hostIP; 
	private String client;
	private String site;
	private int siteid;
	private boolean highperformance;	
	final ArrayList <c_Archive> archiveListarr = new ArrayList<c_Archive>();	
	private static DefaultListModel<String> listModel = new DefaultListModel<String>();
	private static JList<String> searchList = new JList<String>();
	
	public static void run(String siteID) {				
		try {
			g_ViewSiteData2 window = new g_ViewSiteData2();
			window.frmArchiveTickets.setVisible(true);
			window.frmArchiveTickets.setLocationRelativeTo( g_MainMenu.frmMainMenu );
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	/**
	 * Create the application.
	 */
	public g_ViewSiteData2() {
		initialize();		
		
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmArchiveTickets = new JFrame();
		frmArchiveTickets.setIconImage(Toolkit.getDefaultToolkit().getImage(g_ViewSiteData2.class.getResource("/icon.png")));						
		frmArchiveTickets.setTitle("Automated Support Program");		
		frmArchiveTickets.setBounds(100, 100, 801, 601);
		frmArchiveTickets.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmArchiveTickets.getContentPane().setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 948, 857);
		frmArchiveTickets.getContentPane().add(tabbedPane);
		
		JPanel pnl_General = new JPanel();
		tabbedPane.addTab("General", null, pnl_General, null);
		pnl_General.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setBounds(10, 11, 759, 194);
		pnl_General.add(panel);
		
		final JLabel lbl_Address = new JLabel("3900 Singleton Blvd. Dallas, TX 75212");
		lbl_Address.setHorizontalAlignment(SwingConstants.LEFT);
		lbl_Address.setFont(new Font("Monospaced", Font.BOLD, 14));
		lbl_Address.setBounds(10, 73, 307, 19);
		panel.add(lbl_Address);
		
		JLabel lblPhone = new JLabel("Phone #:");
		lblPhone.setHorizontalAlignment(SwingConstants.LEFT);
		lblPhone.setFont(new Font("Monospaced", Font.BOLD, 14));
		lblPhone.setBounds(10, 43, 68, 19);
		panel.add(lblPhone);
		
		JLabel label_2 = new JLabel("Motiva Greensboro (4)");
		label_2.setFont(new Font("Plantagenet Cherokee", Font.BOLD, 18));
		label_2.setBounds(10, 7, 201, 25);
		panel.add(label_2);
		
		JLabel label_7 = new JLabel("Field Tech: Don Freeman");
		label_7.setHorizontalAlignment(SwingConstants.LEFT);
		label_7.setFont(new Font("Monospaced", Font.BOLD, 14));
		label_7.setBounds(10, 128, 376, 19);
		panel.add(label_7);
		
		JLabel label_8 = new JLabel("Supervisor: Joe Klems");
		label_8.setHorizontalAlignment(SwingConstants.LEFT);
		label_8.setFont(new Font("Monospaced", Font.BOLD, 14));
		label_8.setBounds(10, 158, 376, 19);
		panel.add(label_8);
		
		JLabel lbl_Phone = new JLabel("336-299-0331");
		lbl_Phone.setHorizontalAlignment(SwingConstants.LEFT);
		lbl_Phone.setFont(new Font("Monospaced", Font.BOLD, 14));
		lbl_Phone.setBounds(84, 43, 104, 19);
		panel.add(lbl_Phone);
		
		JLabel lblAbbreviation = new JLabel("Abbreviation:");
		lblAbbreviation.setHorizontalAlignment(SwingConstants.LEFT);
		lblAbbreviation.setFont(new Font("Monospaced", Font.BOLD, 14));
		lblAbbreviation.setBounds(502, 7, 113, 19);
		panel.add(lblAbbreviation);
		
		JLabel lblMtvgnb = new JLabel("MTV-GNB");
		lblMtvgnb.setHorizontalAlignment(SwingConstants.LEFT);
		lblMtvgnb.setFont(new Font("Monospaced", Font.BOLD, 14));
		lblMtvgnb.setBounds(625, 7, 113, 19);
		panel.add(lblMtvgnb);
		
		JLabel lblGeneration = new JLabel("Generation:");
		lblGeneration.setHorizontalAlignment(SwingConstants.LEFT);
		lblGeneration.setFont(new Font("Monospaced", Font.BOLD, 14));
		lblGeneration.setBounds(502, 37, 113, 19);
		panel.add(lblGeneration);
		
		JLabel label_3 = new JLabel("3");
		label_3.setHorizontalAlignment(SwingConstants.LEFT);
		label_3.setFont(new Font("Monospaced", Font.BOLD, 14));
		label_3.setBounds(625, 37, 113, 19);
		panel.add(label_3);
		
		JLabel lblResolution = new JLabel("Resolution:");
		lblResolution.setHorizontalAlignment(SwingConstants.LEFT);
		lblResolution.setFont(new Font("Monospaced", Font.BOLD, 14));
		lblResolution.setBounds(502, 67, 113, 19);
		panel.add(lblResolution);
		
		JLabel lblx = new JLabel("1280x1024");
		lblx.setHorizontalAlignment(SwingConstants.LEFT);
		lblx.setFont(new Font("Monospaced", Font.BOLD, 14));
		lblx.setBounds(625, 67, 113, 19);
		panel.add(lblx);
		
		JLabel lblTwic = new JLabel("TWIC Required:");
		lblTwic.setHorizontalAlignment(SwingConstants.LEFT);
		lblTwic.setFont(new Font("Monospaced", Font.BOLD, 14));
		lblTwic.setBounds(502, 97, 113, 19);
		panel.add(lblTwic);
		
		JLabel lblNo = new JLabel("No");
		lblNo.setHorizontalAlignment(SwingConstants.LEFT);
		lblNo.setFont(new Font("Monospaced", Font.BOLD, 14));
		lblNo.setBounds(625, 97, 113, 19);
		panel.add(lblNo);
		
		JLabel lblTimezone = new JLabel("Timezone:");
		lblTimezone.setHorizontalAlignment(SwingConstants.LEFT);
		lblTimezone.setFont(new Font("Monospaced", Font.BOLD, 14));
		lblTimezone.setBounds(502, 127, 113, 19);
		panel.add(lblTimezone);
		
		JLabel lblEst = new JLabel("EST");
		lblEst.setHorizontalAlignment(SwingConstants.LEFT);
		lblEst.setFont(new Font("Monospaced", Font.BOLD, 14));
		lblEst.setBounds(625, 127, 113, 19);
		panel.add(lblEst);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
					try {					
						Runtime.getRuntime().exec("C:\\Program Files\\Internet Explorer\\iexplore.exe " + "https://google.com/maps/search/" + lbl_Address.getText() );
					} catch (IOException e2) {
						// TODO Auto-generated catch block
						System.out.println(e2.toString());
					}
							
			}
		});
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\tjohnston\\Downloads\\gps (2).png"));
		lblNewLabel.setBounds(317, 60, 32, 32);
		panel.add(lblNewLabel);
		
		JLabel label_10 = new JLabel("");
		label_10.setIcon(new ImageIcon("C:\\Users\\tjohnston\\Downloads\\telephone (1).png"));
		label_10.setBounds(190, 37, 32, 32);
		panel.add(label_10);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_1.setBounds(423, 216, 346, 231);
		pnl_General.add(panel_1);
		
		JLabel lblView = new JLabel("View:");
		lblView.setHorizontalAlignment(SwingConstants.LEFT);
		lblView.setFont(new Font("Monospaced", Font.BOLD, 14));
		lblView.setBounds(10, 43, 68, 19);
		panel_1.add(lblView);
		
		JLabel lblServerInformation = new JLabel("VM Information");
		lblServerInformation.setFont(new Font("Plantagenet Cherokee", Font.BOLD, 18));
		lblServerInformation.setBounds(10, 7, 201, 25);
		panel_1.add(lblServerInformation);
		
		JLabel button_1 = new JLabel("");
		button_1.setIcon(new ImageIcon("C:\\Users\\tjohnston\\Downloads\\24x24\\view.png"));
		button_1.setBounds(190, 41, 24, 24);
		panel_1.add(button_1);
		
		JLabel label_16 = new JLabel("10.219.4.10");
		label_16.setHorizontalAlignment(SwingConstants.LEFT);
		label_16.setFont(new Font("Monospaced", Font.BOLD, 14));
		label_16.setBounds(84, 43, 104, 19);
		panel_1.add(label_16);
		
		JLabel lblSql = new JLabel("SQL:");
		lblSql.setHorizontalAlignment(SwingConstants.LEFT);
		lblSql.setFont(new Font("Monospaced", Font.BOLD, 14));
		lblSql.setBounds(10, 73, 68, 19);
		panel_1.add(lblSql);
		
		JLabel label_1 = new JLabel("10.219.4.13");
		label_1.setHorizontalAlignment(SwingConstants.LEFT);
		label_1.setFont(new Font("Monospaced", Font.BOLD, 14));
		label_1.setBounds(84, 73, 104, 19);
		panel_1.add(label_1);
		
		JLabel lblDev = new JLabel("Dev:");
		lblDev.setHorizontalAlignment(SwingConstants.LEFT);
		lblDev.setFont(new Font("Monospaced", Font.BOLD, 14));
		lblDev.setBounds(10, 103, 68, 19);
		panel_1.add(lblDev);
		
		JLabel label_14 = new JLabel("10.219.4.15");
		label_14.setHorizontalAlignment(SwingConstants.LEFT);
		label_14.setFont(new Font("Monospaced", Font.BOLD, 14));
		label_14.setBounds(84, 103, 104, 19);
		panel_1.add(label_14);
		
		JLabel lblIdrac = new JLabel("iDrac:");
		lblIdrac.setHorizontalAlignment(SwingConstants.LEFT);
		lblIdrac.setFont(new Font("Monospaced", Font.BOLD, 14));
		lblIdrac.setBounds(10, 133, 68, 19);
		panel_1.add(lblIdrac);
		
		JLabel label_18 = new JLabel("10.219.4.9");
		label_18.setHorizontalAlignment(SwingConstants.LEFT);
		label_18.setFont(new Font("Monospaced", Font.BOLD, 14));
		label_18.setBounds(84, 133, 104, 19);
		panel_1.add(label_18);
		
		JLabel lblHost = new JLabel("Host:");
		lblHost.setHorizontalAlignment(SwingConstants.LEFT);
		lblHost.setFont(new Font("Monospaced", Font.BOLD, 14));
		lblHost.setBounds(10, 163, 68, 19);
		panel_1.add(lblHost);
		
		JLabel label_9 = new JLabel("10.219.4.14");
		label_9.setHorizontalAlignment(SwingConstants.LEFT);
		label_9.setFont(new Font("Monospaced", Font.BOLD, 14));
		label_9.setBounds(84, 163, 104, 19);
		panel_1.add(label_9);
		
		JLabel label_19 = new JLabel("");
		label_19.setIcon(new ImageIcon("C:\\Users\\tjohnston\\Downloads\\24x24\\sql.png"));
		label_19.setBounds(190, 70, 24, 24);
		panel_1.add(label_19);
		
		JLabel label_20 = new JLabel("");
		label_20.setIcon(new ImageIcon("C:\\Users\\tjohnston\\Downloads\\24x24\\dev.png"));
		label_20.setBounds(190, 100, 24, 24);
		panel_1.add(label_20);
		
		JLabel label_26 = new JLabel("");
		label_26.setIcon(new ImageIcon("C:\\Users\\tjohnston\\Downloads\\24x24\\idrac.png"));
		label_26.setBounds(190, 133, 24, 24);
		panel_1.add(label_26);
		
		JLabel label_27 = new JLabel("");
		label_27.setIcon(new ImageIcon("C:\\Users\\tjohnston\\Downloads\\24x24\\host.png"));
		label_27.setBounds(190, 163, 24, 24);
		panel_1.add(label_27);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_2.setBounds(10, 216, 369, 231);
		pnl_General.add(panel_2);
		
		JLabel lblServer = new JLabel("Server:");
		lblServer.setHorizontalAlignment(SwingConstants.LEFT);
		lblServer.setFont(new Font("Monospaced", Font.BOLD, 14));
		lblServer.setBounds(10, 43, 68, 19);
		panel_2.add(lblServer);
		
		JLabel lblServerInformation_1 = new JLabel("Server Information:");
		lblServerInformation_1.setFont(new Font("Plantagenet Cherokee", Font.BOLD, 18));
		lblServerInformation_1.setBounds(10, 7, 201, 25);
		panel_2.add(lblServerInformation_1);
		
		JLabel lblDellPoweredge = new JLabel("Dell Poweredge T430");
		lblDellPoweredge.setHorizontalAlignment(SwingConstants.LEFT);
		lblDellPoweredge.setFont(new Font("Monospaced", Font.BOLD, 14));
		lblDellPoweredge.setBounds(84, 43, 248, 19);
		panel_2.add(lblDellPoweredge);
		
		JLabel lblOs = new JLabel("OS:");
		lblOs.setHorizontalAlignment(SwingConstants.LEFT);
		lblOs.setFont(new Font("Monospaced", Font.BOLD, 14));
		lblOs.setBounds(10, 163, 68, 19);
		panel_2.add(lblOs);
		
		JLabel lblWindowsServer = new JLabel("Windows Server 2008 R2");
		lblWindowsServer.setHorizontalAlignment(SwingConstants.LEFT);
		lblWindowsServer.setFont(new Font("Monospaced", Font.BOLD, 14));
		lblWindowsServer.setBounds(84, 163, 288, 19);
		panel_2.add(lblWindowsServer);
		
		JLabel lblSqlVersion = new JLabel("SQL:");
		lblSqlVersion.setHorizontalAlignment(SwingConstants.LEFT);
		lblSqlVersion.setFont(new Font("Monospaced", Font.BOLD, 14));
		lblSqlVersion.setBounds(10, 193, 68, 19);
		panel_2.add(lblSqlVersion);
		
		JLabel lblSqlServer = new JLabel("SQL Server 2008 R2");
		lblSqlServer.setHorizontalAlignment(SwingConstants.LEFT);
		lblSqlServer.setFont(new Font("Monospaced", Font.BOLD, 14));
		lblSqlServer.setBounds(84, 193, 268, 19);
		panel_2.add(lblSqlServer);
		
		JLabel lblCpu = new JLabel("CPU:");
		lblCpu.setHorizontalAlignment(SwingConstants.LEFT);
		lblCpu.setFont(new Font("Monospaced", Font.BOLD, 14));
		lblCpu.setBounds(10, 73, 68, 19);
		panel_2.add(lblCpu);
		
		JLabel lblCppusX = new JLabel("6 CPUs x 2.397 GHz");
		lblCppusX.setHorizontalAlignment(SwingConstants.LEFT);
		lblCppusX.setFont(new Font("Monospaced", Font.BOLD, 14));
		lblCppusX.setBounds(84, 73, 248, 19);
		panel_2.add(lblCppusX);
		
		JLabel lblMemory = new JLabel("Memory:");
		lblMemory.setHorizontalAlignment(SwingConstants.LEFT);
		lblMemory.setFont(new Font("Monospaced", Font.BOLD, 14));
		lblMemory.setBounds(10, 103, 68, 19);
		panel_2.add(lblMemory);
		
		JLabel lblMb = new JLabel("49058.27 MB");
		lblMb.setHorizontalAlignment(SwingConstants.LEFT);
		lblMb.setFont(new Font("Monospaced", Font.BOLD, 14));
		lblMb.setBounds(84, 103, 248, 19);
		panel_2.add(lblMb);
		
		JLabel lblHdd = new JLabel("HDD:");
		lblHdd.setHorizontalAlignment(SwingConstants.LEFT);
		lblHdd.setFont(new Font("Monospaced", Font.BOLD, 14));
		lblHdd.setBounds(10, 133, 68, 19);
		panel_2.add(lblHdd);
		
		JLabel lblGb = new JLabel("550.25 GB");
		lblGb.setHorizontalAlignment(SwingConstants.LEFT);
		lblGb.setFont(new Font("Monospaced", Font.BOLD, 14));
		lblGb.setBounds(84, 133, 248, 19);
		panel_2.add(lblGb);
		
		JPanel pnl_Injection = new JPanel();
		tabbedPane.addTab("Injection", null, pnl_Injection, null);
		pnl_Injection.setLayout(null);
		
		JPanel panel_3 = new JPanel();
		panel_3.setLayout(null);
		panel_3.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_3.setBounds(10, 11, 759, 520);
		pnl_Injection.add(panel_3);
		
		JLabel lblInjectionDetails = new JLabel("Injection Details:");
		lblInjectionDetails.setHorizontalAlignment(SwingConstants.CENTER);
		lblInjectionDetails.setFont(new Font("Plantagenet Cherokee", Font.BOLD, 18));
		lblInjectionDetails.setBounds(10, 7, 739, 25);
		panel_3.add(lblInjectionDetails);
		
		JLabel lblInjector = new JLabel("Injector:");
		lblInjector.setHorizontalAlignment(SwingConstants.CENTER);
		lblInjector.setFont(new Font("Plantagenet Cherokee", Font.BOLD, 14));
		lblInjector.setBounds(10, 38, 75, 25);
		panel_3.add(lblInjector);
		
		JLabel lblProduct = new JLabel("Product:");
		lblProduct.setHorizontalAlignment(SwingConstants.CENTER);
		lblProduct.setFont(new Font("Plantagenet Cherokee", Font.BOLD, 14));
		lblProduct.setBounds(139, 38, 75, 25);
		panel_3.add(lblProduct);
		
		JLabel lblLineSize = new JLabel("Injector Size:");
		lblLineSize.setFont(new Font("Plantagenet Cherokee", Font.BOLD, 14));
		lblLineSize.setBounds(264, 38, 93, 25);
		panel_3.add(lblLineSize);
		
		JLabel lblMmSize = new JLabel("Header Size:");
		lblMmSize.setFont(new Font("Plantagenet Cherokee", Font.BOLD, 14));
		lblMmSize.setBounds(420, 38, 93, 25);
		panel_3.add(lblMmSize);
		
		JLabel lblFlowCap = new JLabel("Flow Cap:");
		lblFlowCap.setFont(new Font("Plantagenet Cherokee", Font.BOLD, 14));
		lblFlowCap.setBounds(559, 38, 75, 25);
		panel_3.add(lblFlowCap);
		
		JLabel lblNotes = new JLabel("Notes:");
		lblNotes.setFont(new Font("Plantagenet Cherokee", Font.BOLD, 14));
		lblNotes.setBounds(674, 38, 75, 25);
		panel_3.add(lblNotes);
		
		JLabel label = new JLabel("#1");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Plantagenet Cherokee", Font.PLAIN, 14));
		label.setBounds(10, 74, 75, 25);
		panel_3.add(label);
		
		JLabel lblRegular = new JLabel("Regular");
		lblRegular.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegular.setFont(new Font("Plantagenet Cherokee", Font.PLAIN, 14));
		lblRegular.setBounds(139, 74, 75, 25);
		panel_3.add(lblRegular);
		
		JLabel label_5 = new JLabel("2\"");
		label_5.setHorizontalAlignment(SwingConstants.CENTER);
		label_5.setFont(new Font("Plantagenet Cherokee", Font.PLAIN, 14));
		label_5.setBounds(264, 74, 93, 25);
		panel_3.add(label_5);
		
		JLabel label_6 = new JLabel("8\"");
		label_6.setHorizontalAlignment(SwingConstants.CENTER);
		label_6.setFont(new Font("Plantagenet Cherokee", Font.PLAIN, 14));
		label_6.setBounds(420, 74, 93, 25);
		panel_3.add(label_6);
		
		JLabel lblNa = new JLabel("N/A");
		lblNa.setHorizontalAlignment(SwingConstants.CENTER);
		lblNa.setFont(new Font("Plantagenet Cherokee", Font.PLAIN, 14));
		lblNa.setBounds(559, 74, 75, 25);
		panel_3.add(lblNa);
		
		JLabel label_11 = new JLabel("Notes:");
		label_11.setFont(new Font("Plantagenet Cherokee", Font.PLAIN, 14));
		label_11.setBounds(674, 74, 75, 25);
		panel_3.add(label_11);
		
		JLabel label_4 = new JLabel("#2");
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		label_4.setFont(new Font("Plantagenet Cherokee", Font.PLAIN, 14));
		label_4.setBounds(10, 110, 75, 25);
		panel_3.add(label_4);
		
		JLabel lblPremium = new JLabel("Premium");
		lblPremium.setHorizontalAlignment(SwingConstants.CENTER);
		lblPremium.setFont(new Font("Plantagenet Cherokee", Font.PLAIN, 14));
		lblPremium.setBounds(139, 110, 75, 25);
		panel_3.add(lblPremium);
		
		JLabel label_12 = new JLabel("2\"");
		label_12.setHorizontalAlignment(SwingConstants.CENTER);
		label_12.setFont(new Font("Plantagenet Cherokee", Font.PLAIN, 14));
		label_12.setBounds(264, 110, 93, 25);
		panel_3.add(label_12);
		
		JLabel label_13 = new JLabel("8\"");
		label_13.setHorizontalAlignment(SwingConstants.CENTER);
		label_13.setFont(new Font("Plantagenet Cherokee", Font.PLAIN, 14));
		label_13.setBounds(420, 110, 93, 25);
		panel_3.add(label_13);
		
		JLabel lblGpm = new JLabel("150 GPM");
		lblGpm.setHorizontalAlignment(SwingConstants.CENTER);
		lblGpm.setFont(new Font("Plantagenet Cherokee", Font.PLAIN, 14));
		lblGpm.setBounds(559, 110, 75, 25);
		panel_3.add(lblGpm);
		
		JLabel label_17 = new JLabel("Notes:");
		label_17.setFont(new Font("Plantagenet Cherokee", Font.PLAIN, 14));
		label_17.setBounds(674, 110, 75, 25);
		panel_3.add(label_17);
		
		JPanel pnl_Sampling = new JPanel();
		tabbedPane.addTab("Sampling", null, pnl_Sampling, null);
		pnl_Sampling.setLayout(null);
		
		JPanel panel_4 = new JPanel();
		panel_4.setLayout(null);
		panel_4.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_4.setBounds(10, 11, 759, 520);
		pnl_Sampling.add(panel_4);
		
		JLabel lblSamplingDetails = new JLabel("Sampling Details:");
		lblSamplingDetails.setHorizontalAlignment(SwingConstants.CENTER);
		lblSamplingDetails.setFont(new Font("Plantagenet Cherokee", Font.BOLD, 18));
		lblSamplingDetails.setBounds(10, 7, 739, 25);
		panel_4.add(lblSamplingDetails);
		
		JLabel lblSampleLine = new JLabel("GFL:");
		lblSampleLine.setHorizontalAlignment(SwingConstants.CENTER);
		lblSampleLine.setFont(new Font("Plantagenet Cherokee", Font.BOLD, 14));
		lblSampleLine.setBounds(171, 38, 48, 25);
		panel_4.add(lblSampleLine);
		
		JLabel lblLine = new JLabel("Line:");
		lblLine.setHorizontalAlignment(SwingConstants.CENTER);
		lblLine.setFont(new Font("Plantagenet Cherokee", Font.BOLD, 14));
		lblLine.setBounds(10, 38, 48, 25);
		panel_4.add(lblLine);
		
		JLabel lblProduct_1 = new JLabel("Product:");
		lblProduct_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblProduct_1.setFont(new Font("Plantagenet Cherokee", Font.BOLD, 14));
		lblProduct_1.setBounds(68, 38, 93, 25);
		panel_4.add(lblProduct_1);
		
		JLabel lblEthanol = new JLabel("Ethanol:");
		lblEthanol.setHorizontalAlignment(SwingConstants.CENTER);
		lblEthanol.setFont(new Font("Plantagenet Cherokee", Font.BOLD, 14));
		lblEthanol.setBounds(347, 38, 93, 25);
		panel_4.add(lblEthanol);
		
		JLabel lblEthanolBlend = new JLabel("EthBlnd Ratio:");
		lblEthanolBlend.setFont(new Font("Plantagenet Cherokee", Font.BOLD, 14));
		lblEthanolBlend.setBounds(553, 38, 111, 25);
		panel_4.add(lblEthanolBlend);
		
		JLabel label_23 = new JLabel("Notes:");
		label_23.setFont(new Font("Plantagenet Cherokee", Font.BOLD, 14));
		label_23.setBounds(674, 38, 75, 25);
		panel_4.add(label_23);
		
		JLabel label_24 = new JLabel("#1");
		label_24.setHorizontalAlignment(SwingConstants.CENTER);
		label_24.setFont(new Font("Plantagenet Cherokee", Font.PLAIN, 14));
		label_24.setBounds(10, 74, 48, 25);
		panel_4.add(label_24);
		
		JLabel label_25 = new JLabel("#1");
		label_25.setHorizontalAlignment(SwingConstants.CENTER);
		label_25.setFont(new Font("Plantagenet Cherokee", Font.PLAIN, 14));
		label_25.setBounds(171, 74, 48, 25);
		panel_4.add(label_25);
		
		JLabel lblRegular_1 = new JLabel("60 Seconds");
		lblRegular_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegular_1.setFont(new Font("Plantagenet Cherokee", Font.PLAIN, 14));
		lblRegular_1.setBounds(243, 74, 93, 25);
		panel_4.add(lblRegular_1);
		
		JLabel lblYes = new JLabel("Yes");
		lblYes.setHorizontalAlignment(SwingConstants.CENTER);
		lblYes.setFont(new Font("Plantagenet Cherokee", Font.PLAIN, 14));
		lblYes.setBounds(347, 74, 93, 25);
		panel_4.add(lblYes);
		
		JLabel label_28 = new JLabel("90 / 10");
		label_28.setHorizontalAlignment(SwingConstants.CENTER);
		label_28.setFont(new Font("Plantagenet Cherokee", Font.PLAIN, 14));
		label_28.setBounds(553, 74, 111, 25);
		panel_4.add(label_28);
		
		JLabel label_29 = new JLabel("Notes:");
		label_29.setFont(new Font("Plantagenet Cherokee", Font.PLAIN, 14));
		label_29.setBounds(674, 74, 75, 25);
		panel_4.add(label_29);
		
		JLabel label_30 = new JLabel("#1");
		label_30.setHorizontalAlignment(SwingConstants.CENTER);
		label_30.setFont(new Font("Plantagenet Cherokee", Font.PLAIN, 14));
		label_30.setBounds(10, 110, 48, 25);
		panel_4.add(label_30);
		
		JLabel label_31 = new JLabel("#2");
		label_31.setHorizontalAlignment(SwingConstants.CENTER);
		label_31.setFont(new Font("Plantagenet Cherokee", Font.PLAIN, 14));
		label_31.setBounds(171, 110, 48, 25);
		panel_4.add(label_31);
		
		JLabel lblPremium_1 = new JLabel("60 Seconds");
		lblPremium_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblPremium_1.setFont(new Font("Plantagenet Cherokee", Font.PLAIN, 14));
		lblPremium_1.setBounds(243, 110, 93, 25);
		panel_4.add(lblPremium_1);
		
		JLabel lblNo_1 = new JLabel("No");
		lblNo_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNo_1.setFont(new Font("Plantagenet Cherokee", Font.PLAIN, 14));
		lblNo_1.setBounds(347, 110, 93, 25);
		panel_4.add(lblNo_1);
		
		JLabel label_34 = new JLabel("100 / 0 ");
		label_34.setHorizontalAlignment(SwingConstants.CENTER);
		label_34.setFont(new Font("Plantagenet Cherokee", Font.PLAIN, 14));
		label_34.setBounds(553, 110, 111, 25);
		panel_4.add(label_34);
		
		JLabel label_35 = new JLabel("Notes:");
		label_35.setFont(new Font("Plantagenet Cherokee", Font.PLAIN, 14));
		label_35.setBounds(674, 110, 75, 25);
		panel_4.add(label_35);
		
		JLabel lblGflPurge = new JLabel("GFL Purge:");
		lblGflPurge.setHorizontalAlignment(SwingConstants.CENTER);
		lblGflPurge.setFont(new Font("Plantagenet Cherokee", Font.BOLD, 14));
		lblGflPurge.setBounds(243, 38, 101, 25);
		panel_4.add(lblGflPurge);
		
		JLabel lblSec_1 = new JLabel("Regular");
		lblSec_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblSec_1.setFont(new Font("Plantagenet Cherokee", Font.PLAIN, 14));
		lblSec_1.setBounds(68, 74, 93, 25);
		panel_4.add(lblSec_1);
		
		JLabel lblSec = new JLabel("Premium");
		lblSec.setHorizontalAlignment(SwingConstants.CENTER);
		lblSec.setFont(new Font("Plantagenet Cherokee", Font.PLAIN, 14));
		lblSec.setBounds(68, 110, 93, 25);
		panel_4.add(lblSec);
		
		JLabel lblBlendPurge = new JLabel("Blend Purge:");
		lblBlendPurge.setHorizontalAlignment(SwingConstants.CENTER);
		lblBlendPurge.setFont(new Font("Plantagenet Cherokee", Font.BOLD, 14));
		lblBlendPurge.setBounds(445, 38, 93, 25);
		panel_4.add(lblBlendPurge);
		
		JLabel lblSec_2 = new JLabel("300 Seconds");
		lblSec_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblSec_2.setFont(new Font("Plantagenet Cherokee", Font.PLAIN, 14));
		lblSec_2.setBounds(445, 74, 93, 25);
		panel_4.add(lblSec_2);
		
		JLabel lblSeconds = new JLabel("300 Seconds");
		lblSeconds.setHorizontalAlignment(SwingConstants.CENTER);
		lblSeconds.setFont(new Font("Plantagenet Cherokee", Font.PLAIN, 14));
		lblSeconds.setBounds(445, 110, 93, 25);
		panel_4.add(lblSeconds);
		
		JPanel pnl_Supply = new JPanel();
		tabbedPane.addTab("Supply", null, pnl_Supply, null);
		pnl_Supply.setLayout(null);
		
		JLabel lblTankDetails = new JLabel("Supply Details:");
		lblTankDetails.setHorizontalAlignment(SwingConstants.CENTER);
		lblTankDetails.setFont(new Font("Plantagenet Cherokee", Font.BOLD, 18));
		lblTankDetails.setBounds(10, 0, 739, 25);
		pnl_Supply.add(lblTankDetails);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));		
		panel_5.setBounds(20, 36, 255, 254);
		pnl_Supply.add(panel_5);
		panel_5.setLayout(null);
		
		JLabel lblTank = new JLabel("Tank:");
		lblTank.setBounds(10, 11, 48, 25);
		panel_5.add(lblTank);
		lblTank.setHorizontalAlignment(SwingConstants.CENTER);
		lblTank.setFont(new Font("Plantagenet Cherokee", Font.BOLD, 14));
		
		JLabel label_33 = new JLabel("#1");
		label_33.setBounds(10, 47, 48, 25);
		panel_5.add(label_33);
		label_33.setHorizontalAlignment(SwingConstants.CENTER);
		label_33.setFont(new Font("Plantagenet Cherokee", Font.PLAIN, 14));
		
		JLabel lblElliptical = new JLabel("Elliptical");
		lblElliptical.setBounds(68, 47, 93, 25);
		panel_5.add(lblElliptical);
		lblElliptical.setHorizontalAlignment(SwingConstants.CENTER);
		lblElliptical.setFont(new Font("Plantagenet Cherokee", Font.PLAIN, 14));
		
		JLabel lblTankType = new JLabel("Tank Type:");
		lblTankType.setBounds(68, 11, 93, 25);
		panel_5.add(lblTankType);
		lblTankType.setHorizontalAlignment(SwingConstants.CENTER);
		lblTankType.setFont(new Font("Plantagenet Cherokee", Font.BOLD, 14));
		
		JLabel lblCapacity = new JLabel("Capacity:");
		lblCapacity.setBounds(171, 11, 75, 25);
		panel_5.add(lblCapacity);
		lblCapacity.setHorizontalAlignment(SwingConstants.CENTER);
		lblCapacity.setFont(new Font("Plantagenet Cherokee", Font.BOLD, 14));
		
		JLabel label_37 = new JLabel("60,000");
		label_37.setBounds(171, 47, 75, 25);
		panel_5.add(label_37);
		label_37.setHorizontalAlignment(SwingConstants.CENTER);
		label_37.setFont(new Font("Plantagenet Cherokee", Font.PLAIN, 14));
		
		JPanel panel_6 = new JPanel();
		panel_6.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_6.setLayout(null);		
		panel_6.setBounds(458, 36, 291, 254);
		pnl_Supply.add(panel_6);
		
		JLabel lblOffload = new JLabel("# Offloads:");
		lblOffload.setFont(new Font("Plantagenet Cherokee", Font.BOLD, 14));
		lblOffload.setBounds(10, 11, 105, 25);
		panel_6.add(lblOffload);
		
		JLabel label_15 = new JLabel("#1");
		label_15.setFont(new Font("Plantagenet Cherokee", Font.PLAIN, 14));
		label_15.setBounds(156, 11, 125, 25);
		panel_6.add(label_15);
		
		JLabel lblVfdPump = new JLabel("VFD Type:");
		lblVfdPump.setFont(new Font("Plantagenet Cherokee", Font.BOLD, 14));
		lblVfdPump.setBounds(10, 44, 105, 25);
		panel_6.add(lblVfdPump);
		
		JLabel lblSeries = new JLabel("PowerFlex 70 EC-E");
		lblSeries.setFont(new Font("Plantagenet Cherokee", Font.PLAIN, 14));
		lblSeries.setBounds(156, 44, 125, 25);
		panel_6.add(lblSeries);
		
		JLabel lblDischargeSp = new JLabel("Discharge SP:");
		lblDischargeSp.setFont(new Font("Plantagenet Cherokee", Font.BOLD, 14));
		lblDischargeSp.setBounds(10, 80, 105, 25);
		panel_6.add(lblDischargeSp);
		
		JLabel lblPsi = new JLabel("125 PSI");
		lblPsi.setFont(new Font("Plantagenet Cherokee", Font.PLAIN, 14));
		lblPsi.setBounds(156, 80, 125, 25);
		panel_6.add(lblPsi);
		
		JLabel lblBypassSp = new JLabel("Bypass SP:");
		lblBypassSp.setFont(new Font("Plantagenet Cherokee", Font.BOLD, 14));
		lblBypassSp.setBounds(10, 119, 105, 25);
		panel_6.add(lblBypassSp);
		
		JLabel lblGpm_1 = new JLabel("175 GPM");
		lblGpm_1.setFont(new Font("Plantagenet Cherokee", Font.PLAIN, 14));
		lblGpm_1.setBounds(156, 119, 125, 25);
		panel_6.add(lblGpm_1);
		
		JPanel pnl_Devices = new JPanel();
		tabbedPane.addTab("Devices", null, pnl_Devices, null);
		pnl_Devices.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		scrollPane.setBounds(10, 11, 244, 505);
		pnl_Devices.add(scrollPane);
		
		JTree tree = new JTree();
		tree.setRootVisible(false);
		tree.setModel(new DefaultTreeModel(
			new DefaultMutableTreeNode("Devices ") {
				{
					DefaultMutableTreeNode node_1;
					DefaultMutableTreeNode node_2;
					node_1 = new DefaultMutableTreeNode("HMI Devices (10.219.89.X)");
						node_2 = new DefaultMutableTreeNode("Stratix");
							node_2.add(new DefaultMutableTreeNode("Control Room (3)"));
							node_2.add(new DefaultMutableTreeNode("PLC (4)"));
							node_2.add(new DefaultMutableTreeNode("VFD (5)\t"));
						node_1.add(node_2);
						node_2 = new DefaultMutableTreeNode("Server");
							node_2.add(new DefaultMutableTreeNode("iDrac (9)"));
							node_2.add(new DefaultMutableTreeNode("View (10)"));
							node_2.add(new DefaultMutableTreeNode("SQL (11)"));
							node_2.add(new DefaultMutableTreeNode("Host (14)"));
							node_2.add(new DefaultMutableTreeNode("Dev (15)"));
							node_2.add(new DefaultMutableTreeNode("VMA (16)"));
						node_1.add(node_2);
						node_2 = new DefaultMutableTreeNode("Office");
							node_2.add(new DefaultMutableTreeNode("ASA Firewall (1)\t"));
							node_2.add(new DefaultMutableTreeNode("Printer (20)"));
							node_2.add(new DefaultMutableTreeNode("Thin Client (21)"));
							node_2.add(new DefaultMutableTreeNode("NAS (22)"));
						node_1.add(node_2);
						node_2 = new DefaultMutableTreeNode("TManager");
							node_2.add(new DefaultMutableTreeNode("TManager (29)"));
						node_1.add(node_2);
						node_2 = new DefaultMutableTreeNode("EN2T");
							node_2.add(new DefaultMutableTreeNode("PLC EN2T (30)"));
						node_1.add(node_2);
						node_2 = new DefaultMutableTreeNode("ETAP");
							node_2.add(new DefaultMutableTreeNode("Main PLC (120)"));
							node_2.add(new DefaultMutableTreeNode("RVP (106)"));
							node_2.add(new DefaultMutableTreeNode("DPA (105)\t\t"));
						node_1.add(node_2);
					add(node_1);
					node_1 = new DefaultMutableTreeNode("IO Devices (10.218.89.X)");
						node_2 = new DefaultMutableTreeNode("Stratix");
							node_2.add(new DefaultMutableTreeNode("Control Room (3)"));
							node_2.add(new DefaultMutableTreeNode("PLC (4)"));
							node_2.add(new DefaultMutableTreeNode("VFD (5)\t"));
						node_1.add(node_2);
						node_2 = new DefaultMutableTreeNode("Server");
							node_2.add(new DefaultMutableTreeNode("View (10)"));
							node_2.add(new DefaultMutableTreeNode("SQL (11)"));
							node_2.add(new DefaultMutableTreeNode("Dev (15)"));
							node_2.add(new DefaultMutableTreeNode("VMA (16)"));
						node_1.add(node_2);
						node_2 = new DefaultMutableTreeNode("EN2T");
							node_2.add(new DefaultMutableTreeNode("Rack 1 (22)"));
							node_2.add(new DefaultMutableTreeNode("Rack 2 (23)"));
							node_2.add(new DefaultMutableTreeNode("Rack 3 (24)"));
							node_2.add(new DefaultMutableTreeNode("PLC (30)"));
						node_1.add(node_2);
						node_2 = new DefaultMutableTreeNode("TManager");
							node_2.add(new DefaultMutableTreeNode("TManager (29)"));
						node_1.add(node_2);
						node_2 = new DefaultMutableTreeNode("ETAP");
							node_2.add(new DefaultMutableTreeNode("DPA01 (105)"));
							node_2.add(new DefaultMutableTreeNode("RVP01 (106)"));
							node_2.add(new DefaultMutableTreeNode("Main PLC (120)"));
						node_1.add(node_2);
						node_2 = new DefaultMutableTreeNode("PLX");
							node_2.add(new DefaultMutableTreeNode("RVP01 (130)"));
							node_2.add(new DefaultMutableTreeNode("DPA01 (131)"));
						node_1.add(node_2);
						node_2 = new DefaultMutableTreeNode("EAFC");
							node_2.add(new DefaultMutableTreeNode("EAFC (145)"));
						node_1.add(node_2);
						node_2 = new DefaultMutableTreeNode("VLINX");
							node_2.add(new DefaultMutableTreeNode("Injection Skid (155)"));
							node_2.add(new DefaultMutableTreeNode("Ethanol Blend (161)"));
						node_1.add(node_2);
					add(node_1);
				}
			}
		));
		scrollPane.setViewportView(tree);
		
		JPanel panel_7 = new JPanel();
		panel_7.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_7.setBounds(264, 11, 507, 505);
		pnl_Devices.add(panel_7);
		panel_7.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Control Room Stratix");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(10, 11, 487, 14);
		panel_7.add(lblNewLabel_1);
		
		JLabel lblModel = new JLabel("Model:");
		lblModel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblModel.setBounds(10, 76, 85, 14);
		panel_7.add(lblModel);
		
		JLabel lblFirmware = new JLabel("Firmware:");
		lblFirmware.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblFirmware.setBounds(10, 101, 85, 14);
		panel_7.add(lblFirmware);
		
		JLabel lblStratix = new JLabel("Stratix 8000");
		lblStratix.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblStratix.setBounds(90, 76, 152, 14);
		panel_7.add(lblStratix);
		
		JLabel label_21 = new JLabel("15.2(5)");
		label_21.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_21.setBounds(90, 101, 152, 14);
		panel_7.add(label_21);
		
		JLabel lblIpAddress = new JLabel("Address:");
		lblIpAddress.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblIpAddress.setBounds(10, 51, 85, 14);
		panel_7.add(lblIpAddress);
		
		JLabel label_22 = new JLabel("10.219.89.3");
		label_22.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_22.setBounds(90, 51, 152, 14);
		panel_7.add(label_22);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon("C:\\Users\\tjohnston\\Desktop\\1783-ms06t_ph.png"));
		lblNewLabel_2.setBounds(97, 175, 300, 300);
		panel_7.add(lblNewLabel_2);
		
		JLabel lblLocation = new JLabel("Location:");
		lblLocation.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblLocation.setBounds(9, 128, 85, 14);
		panel_7.add(lblLocation);
		
		JLabel lblTerminalOffice = new JLabel("Terminal Office");
		lblTerminalOffice.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTerminalOffice.setBounds(89, 128, 152, 14);
		panel_7.add(lblTerminalOffice);
		
	}		
}

