import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;

import com.sun.glass.ui.Clipboard;

import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.border.EtchedBorder;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.AbstractListModel;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;

import java.awt.Font;
import javax.swing.JLabel;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.sql.ResultSet;
import java.util.List;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JCheckBox;
import javax.swing.JTabbedPane;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

public class g_Tools_CreateChecklist {

	private static JFrame frm_CreateChecklist;
	public static JList<c_ButaneSites> List_ChecklistSites;
	public static JList<c_ButaneSites> List_AllSites;
	public static DefaultListModel<c_ButaneSites> List_AllSitesModel;
	public static DefaultListModel<c_ButaneSites> List_ChecklistSitesModel;
	private JScrollPane scrollPane;
	private static JLabel lblAllSites;
	private static JLabel lblGeneratedSites;
	private static int siteNum;
	private static int checkNum;
	private static JTextArea textArea;
	JPanel adv_panel;
	private static JCheckBox chckbxAdvanced;
	private static JButton btnBack;
	private static JButton btnGenerate;

	/**
	 * Launch the application.
	 */
	
			public static void run(JFrame frame) {
				try {
					g_Tools_CreateChecklist window = new g_Tools_CreateChecklist();
					g_Tools_CreateChecklist.frm_CreateChecklist.setVisible(true);
					g_Tools_CreateChecklist.frm_CreateChecklist.setLocationRelativeTo(frame);
					frame.dispose();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}		
	

	/**
	 * Create the application.
	 */
	public g_Tools_CreateChecklist() {
		initialize();
		PopulateSites();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frm_CreateChecklist = new JFrame();
		frm_CreateChecklist.setResizable(false);
		frm_CreateChecklist.setTitle(g_MainMenu.TitleOnline);
		frm_CreateChecklist.setIconImage(Toolkit.getDefaultToolkit().getImage(g_Tools_CreateChecklist.class.getResource("/icon.png")));
		frm_CreateChecklist.setFont(new Font("Rockwell", Font.PLAIN, 13));
		frm_CreateChecklist.setBounds(100, 100, 758, 731);
		frm_CreateChecklist.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frm_CreateChecklist.getContentPane().setLayout(null);
		
		JButton btnAllSites = new JButton("All Sites");
		btnAllSites.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addAllSites();
			}
		});
		btnAllSites.setFont(new Font("Rockwell", Font.PLAIN, 13));
		btnAllSites.setBounds(20, 597, 89, 23);
		frm_CreateChecklist.getContentPane().add(btnAllSites);
		
		scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		scrollPane.setBounds(10, 117, 275, 469);
		frm_CreateChecklist.getContentPane().add(scrollPane);
		
		JButton button = new JButton(">>");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addSites();
			}
		});
		button.setFont(new Font("Rockwell", Font.PLAIN, 13));
		button.setBounds(307, 270, 89, 23);
		frm_CreateChecklist.getContentPane().add(button);
		
		JButton button_1 = new JButton("<<");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removeSites();
			}
		});
		button_1.setFont(new Font("Rockwell", Font.PLAIN, 13));
		button_1.setBounds(307, 332, 89, 23);
		frm_CreateChecklist.getContentPane().add(button_1);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setViewportBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		scrollPane_1.setBounds(436, 117, 275, 469);
		frm_CreateChecklist.getContentPane().add(scrollPane_1);
		
		List_ChecklistSites = new JList<c_ButaneSites>();
		List_ChecklistSites.setFont(new Font("Rockwell", Font.PLAIN, 13));
		List_ChecklistSites.setSelectedIndex(0);
		scrollPane_1.setViewportView(List_ChecklistSites);
		
		List_AllSites = new JList<c_ButaneSites>();
		List_AllSites.setFont(new Font("Rockwell", Font.PLAIN, 13));
		List_AllSites.setSelectedIndex(0);
		scrollPane.setViewportView(List_AllSites);
		
		lblAllSites = new JLabel("Available Sites (" + siteNum + ")");
		lblAllSites.setFont(new Font("Rockwell", Font.BOLD, 14));
		lblAllSites.setBounds(10, 101, 275, 17);
		frm_CreateChecklist.getContentPane().add(lblAllSites);
		
		lblGeneratedSites = new JLabel("Checklist Sites (" + checkNum + ")");
		lblGeneratedSites.setFont(new Font("Rockwell", Font.BOLD, 14));
		lblGeneratedSites.setBounds(436, 101, 275, 17);
		frm_CreateChecklist.getContentPane().add(lblGeneratedSites);
		
		btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				g_MainMenu.run(frm_CreateChecklist);
				frm_CreateChecklist.dispose();
			}
		});
		btnBack.setFont(new Font("Rockwell", Font.PLAIN, 13));
		btnBack.setBounds(10, 645, 89, 23);
		frm_CreateChecklist.getContentPane().add(btnBack);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ClearList();
			}
		});
		btnClear.setFont(new Font("Rockwell", Font.PLAIN, 13));
		btnClear.setBounds(446, 597, 89, 23);
		frm_CreateChecklist.getContentPane().add(btnClear);
		
		adv_panel = new JPanel();		
		Border empty = BorderFactory.createEmptyBorder(0, 0, 0, 0);
		Border dashed = BorderFactory.createDashedBorder(null, 5, 5);
		Border compound = new CompoundBorder(empty, dashed);
		adv_panel.setBorder(compound);
		adv_panel.setBounds(10, 671, 722, 97);
		frm_CreateChecklist.getContentPane().add(adv_panel);
		adv_panel.setLayout(null);
		adv_panel.setVisible(false);
		
		textArea = new JTextArea();
		textArea.setEnabled(false);
		textArea.setLineWrap(true);
		textArea.setBounds(10, 60, 702, 26);
		adv_panel.add(textArea);
		
		JLabel lblCreateACustom = new JLabel("Create a custom format using variables and text. Example: $Client - $Site ($SiteID)");
		lblCreateACustom.setFont(new Font("Rockwell", Font.PLAIN, 13));
		lblCreateACustom.setBounds(10, 0, 597, 23);
		adv_panel.add(lblCreateACustom);
		
		JLabel lblAvailableVariablesclient = new JLabel("Available variables: $Client, $Site, $ClientAbbrv, $SiteAbbrv, $SiteID, $ViewIP, $SQLIP, $DevIP, $Generation");
		lblAvailableVariablesclient.setFont(new Font("Rockwell", Font.PLAIN, 13));
		lblAvailableVariablesclient.setBounds(10, 23, 702, 23);
		adv_panel.add(lblAvailableVariablesclient);
		
		btnGenerate = new JButton("Copy");
		btnGenerate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Generate();
			}
		});
		btnGenerate.setBounds(643, 645, 89, 23);
		frm_CreateChecklist.getContentPane().add(btnGenerate);
		btnGenerate.setFont(new Font("Rockwell", Font.PLAIN, 13));
		
				
		chckbxAdvanced = new JCheckBox("Advanced");
		chckbxAdvanced.setBounds(105, 646, 97, 23);
		frm_CreateChecklist.getContentPane().add(chckbxAdvanced);
		chckbxAdvanced.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(chckbxAdvanced.isSelected())
				{
					frm_CreateChecklist.setSize(758, 865);
					btnBack.setBounds(10, 779, 89, 23);
					btnGenerate.setBounds(643, 779, 89, 23);
					textArea.setEnabled(true);
					adv_panel.setVisible(true); 
				}
				else
				{
					frm_CreateChecklist.setSize(758, 730);
					btnGenerate.setBounds(643, 645, 89, 23);
					btnBack.setBounds(10, 645, 89, 23);
					textArea.setEnabled(false);
					adv_panel.setVisible(false); 					
				}
			}
		});
		chckbxAdvanced.setFont(new Font("Rockwell", Font.PLAIN, 11));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBorder(null);
		tabbedPane.setFont(new Font("Rockwell", Font.PLAIN, 11));
		tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		tabbedPane.setBounds(10, 0, 732, 92);
		frm_CreateChecklist.getContentPane().add(tabbedPane);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Client", null, panel_1, null);
		tabbedPane.setBackgroundAt(0, Color.LIGHT_GRAY);
		panel_1.setLayout(null);
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		
		JButton btnExp = new JButton("PHL");
		btnExp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addPHLClient();
			}
		});
		btnExp.setFont(new Font("Rockwell", Font.PLAIN, 13));
		btnExp.setBounds(406, 11, 89, 23);
		panel_1.add(btnExp);
		
		JButton btnEtp = new JButton("MTV");
		btnEtp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addMTVClient();
			}
		});
		btnEtp.setFont(new Font("Rockwell", Font.PLAIN, 13));
		btnEtp.setBounds(307, 11, 89, 23);
		panel_1.add(btnEtp);
		
		JButton btnCaljet = new JButton("MPC");
		btnCaljet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addMPCClient();
			}
		});
		btnCaljet.setFont(new Font("Rockwell", Font.PLAIN, 13));
		btnCaljet.setBounds(208, 11, 89, 23);
		panel_1.add(btnCaljet);
		
		JButton btnBp = new JButton("KM");
		btnBp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addKMClient();
			}
		});
		btnBp.setFont(new Font("Rockwell", Font.PLAIN, 13));
		btnBp.setBounds(109, 11, 89, 23);
		panel_1.add(btnBp);
		
		JButton btnAmMidstream = new JButton("ETP");
		btnAmMidstream.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addETPClient();
				
			}
		});
		btnAmMidstream.setFont(new Font("Rockwell", Font.PLAIN, 13));
		btnAmMidstream.setBounds(10, 11, 89, 23);
		panel_1.add(btnAmMidstream);
		
		JButton btnOther = new JButton("Other");
		btnOther.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addOtherClient();
			}
		});
		btnOther.setFont(new Font("Rockwell", Font.PLAIN, 13));
		btnOther.setBounds(505, 11, 89, 23);
		panel_1.add(btnOther);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Generation", null, panel, null);
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel.setLayout(null);
		
		JButton btnGen_4 = new JButton("Gen 4");
		btnGen_4.setBounds(406, 11, 89, 23);
		panel.add(btnGen_4);
		btnGen_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addGen4Sites();
			}
		});
		btnGen_4.setFont(new Font("Rockwell", Font.PLAIN, 13));
		
		JButton btnGen_35 = new JButton("Gen 3.5");
		btnGen_35.setBounds(307, 11, 89, 23);
		panel.add(btnGen_35);
		btnGen_35.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addGen35Sites();
			}
		});
		btnGen_35.setFont(new Font("Rockwell", Font.PLAIN, 13));
		
		JButton btnGen_3 = new JButton("Gen 3");
		btnGen_3.setBounds(208, 11, 89, 23);
		panel.add(btnGen_3);
		btnGen_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addGen3Sites();
			}
		});
		btnGen_3.setFont(new Font("Rockwell", Font.PLAIN, 13));
		
		JButton btnGen_2 = new JButton("Gen 2");
		btnGen_2.setBounds(109, 11, 89, 23);
		panel.add(btnGen_2);
		btnGen_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addGen2Sites();
			}
		});
		btnGen_2.setFont(new Font("Rockwell", Font.PLAIN, 13));
		
		JButton btnGen_1 = new JButton("Gen 1");
		btnGen_1.setBounds(10, 11, 89, 23);
		panel.add(btnGen_1);
		btnGen_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addGen1Sites();
			}
		});
		btnGen_1.setFont(new Font("Rockwell", Font.PLAIN, 13));
		
		JPanel panel_State = new JPanel();
		panel_State.setLayout(null);
		panel_State.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		tabbedPane.addTab("State", null, panel_State, null);
		
		JButton btnAr = new JButton("AR");
		btnAr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addState("AR");
			}
		});
		btnAr.setHorizontalAlignment(SwingConstants.LEFT);
		btnAr.setFont(new Font("Rockwell", Font.PLAIN, 12));
		btnAr.setBounds(10, 11, 55, 20);
		panel_State.add(btnAr);
		
		JButton btnAz = new JButton("AZ");
		btnAz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addState("AZ");
			}
		});
		btnAz.setHorizontalAlignment(SwingConstants.LEFT);
		btnAz.setFont(new Font("Rockwell", Font.PLAIN, 12));
		btnAz.setBounds(60, 11, 55, 20);
		panel_State.add(btnAz);
		
		JButton btnCo = new JButton("CO");
		btnCo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addState("CO");
			}
		});
		btnCo.setHorizontalAlignment(SwingConstants.LEFT);
		btnCo.setFont(new Font("Rockwell", Font.PLAIN, 12));
		btnCo.setBounds(111, 11, 55, 20);
		panel_State.add(btnCo);
		
		JButton btnCt = new JButton("CT");
		btnCt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addState("CT");
			}
		});
		btnCt.setHorizontalAlignment(SwingConstants.LEFT);
		btnCt.setFont(new Font("Rockwell", Font.PLAIN, 12));
		btnCt.setBounds(161, 11, 55, 20);
		panel_State.add(btnCt);
		
		JButton btnFl = new JButton("FL");
		btnFl.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addState("FL");
			}
		});
		btnFl.setHorizontalAlignment(SwingConstants.LEFT);
		btnFl.setFont(new Font("Rockwell", Font.PLAIN, 12));
		btnFl.setBounds(211, 11, 55, 20);
		panel_State.add(btnFl);
		
		JButton btnGa = new JButton("GA");
		btnGa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addState("GA");
			}
		});
		btnGa.setHorizontalAlignment(SwingConstants.LEFT);
		btnGa.setFont(new Font("Rockwell", Font.PLAIN, 12));
		btnGa.setBounds(262, 11, 55, 20);
		panel_State.add(btnGa);
		
		JButton btnIl = new JButton("IL");
		btnIl.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addState("IL");
			}
		});
		btnIl.setHorizontalAlignment(SwingConstants.LEFT);
		btnIl.setFont(new Font("Rockwell", Font.PLAIN, 12));
		btnIl.setBounds(313, 11, 55, 20);
		panel_State.add(btnIl);
		
		JButton btnIn = new JButton("IN");
		btnIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addState("IN");
			}
		});
		btnIn.setHorizontalAlignment(SwingConstants.LEFT);
		btnIn.setFont(new Font("Rockwell", Font.PLAIN, 12));
		btnIn.setBounds(363, 11, 55, 20);
		panel_State.add(btnIn);
		
		JButton btnKs = new JButton("KS");
		btnKs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addState("KS");
			}
		});
		btnKs.setHorizontalAlignment(SwingConstants.LEFT);
		btnKs.setFont(new Font("Rockwell", Font.PLAIN, 12));
		btnKs.setBounds(414, 11, 55, 20);
		panel_State.add(btnKs);
		
		JButton btnMd = new JButton("MD");
		btnMd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addState("MD");
			}
		});
		btnMd.setHorizontalAlignment(SwingConstants.LEFT);
		btnMd.setFont(new Font("Rockwell", Font.PLAIN, 12));
		btnMd.setBounds(464, 11, 55, 20);
		panel_State.add(btnMd);
		
		JButton btnMi = new JButton("MI");
		btnMi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addState("MI");
			}
		});
		btnMi.setHorizontalAlignment(SwingConstants.LEFT);
		btnMi.setFont(new Font("Rockwell", Font.PLAIN, 12));
		btnMi.setBounds(514, 11, 55, 20);
		panel_State.add(btnMi);
		
		JButton btnMo = new JButton("MO");
		btnMo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addState("MO");
			}
		});
		btnMo.setHorizontalAlignment(SwingConstants.LEFT);
		btnMo.setFont(new Font("Rockwell", Font.PLAIN, 12));
		btnMo.setBounds(565, 11, 55, 20);
		panel_State.add(btnMo);
		
		JButton btnNc = new JButton("NC");
		btnNc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addState("NC");
			}
		});
		btnNc.setHorizontalAlignment(SwingConstants.LEFT);
		btnNc.setFont(new Font("Rockwell", Font.PLAIN, 12));
		btnNc.setBounds(10, 38, 55, 20);
		panel_State.add(btnNc);
		
		JButton btnNj = new JButton("NJ");
		btnNj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addState("NJ");
			}
		});
		btnNj.setHorizontalAlignment(SwingConstants.LEFT);
		btnNj.setFont(new Font("Rockwell", Font.PLAIN, 12));
		btnNj.setBounds(60, 38, 55, 20);
		panel_State.add(btnNj);
		
		JButton btnNv = new JButton("NV");
		btnNv.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addState("NV");
			}
		});
		btnNv.setHorizontalAlignment(SwingConstants.LEFT);
		btnNv.setFont(new Font("Rockwell", Font.PLAIN, 12));
		btnNv.setBounds(111, 38, 55, 20);
		panel_State.add(btnNv);
		
		JButton btnNy = new JButton("NY");
		btnNy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addState("NY");
			}
		});
		btnNy.setHorizontalAlignment(SwingConstants.LEFT);
		btnNy.setFont(new Font("Rockwell", Font.PLAIN, 12));
		btnNy.setBounds(161, 38, 55, 20);
		panel_State.add(btnNy);
		
		JButton btnOh = new JButton("OH");
		btnOh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addState("OH");
			}
		});
		btnOh.setHorizontalAlignment(SwingConstants.LEFT);
		btnOh.setFont(new Font("Rockwell", Font.PLAIN, 12));
		btnOh.setBounds(211, 38, 55, 20);
		panel_State.add(btnOh);
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addState("OK");
			}
		});
		btnOk.setHorizontalAlignment(SwingConstants.LEFT);
		btnOk.setFont(new Font("Rockwell", Font.PLAIN, 12));
		btnOk.setBounds(262, 38, 55, 20);
		panel_State.add(btnOk);
		
		JButton btnOr = new JButton("OR");
		btnOr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addState("OR");
			}
		});
		btnOr.setHorizontalAlignment(SwingConstants.LEFT);
		btnOr.setFont(new Font("Rockwell", Font.PLAIN, 12));
		btnOr.setBounds(313, 38, 55, 20);
		panel_State.add(btnOr);
		
		JButton btnPa = new JButton("PA");
		btnPa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addState("PA");
			}
		});
		btnPa.setHorizontalAlignment(SwingConstants.LEFT);
		btnPa.setFont(new Font("Rockwell", Font.PLAIN, 12));
		btnPa.setBounds(363, 38, 55, 20);
		panel_State.add(btnPa);
		
		JButton btnSc = new JButton("SC");
		btnSc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addState("SC");
			}
		});
		btnSc.setHorizontalAlignment(SwingConstants.LEFT);
		btnSc.setFont(new Font("Rockwell", Font.PLAIN, 12));
		btnSc.setBounds(414, 38, 55, 20);
		panel_State.add(btnSc);
		
		JButton btnTn = new JButton("TN");
		btnTn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addState("TN");
			}
		});
		btnTn.setHorizontalAlignment(SwingConstants.LEFT);
		btnTn.setFont(new Font("Rockwell", Font.PLAIN, 12));
		btnTn.setBounds(464, 38, 55, 20);
		panel_State.add(btnTn);
		
		JButton btnTx = new JButton("TX");
		btnTx.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addState("TX");
			}
		});
		btnTx.setHorizontalAlignment(SwingConstants.LEFT);
		btnTx.setFont(new Font("Rockwell", Font.PLAIN, 12));
		btnTx.setBounds(514, 38, 55, 20);
		panel_State.add(btnTx);
		
		JButton btnVa = new JButton("VA");
		btnVa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addState("VA");
			}
		});
		btnVa.setHorizontalAlignment(SwingConstants.LEFT);
		btnVa.setFont(new Font("Rockwell", Font.PLAIN, 12));
		btnVa.setBounds(565, 38, 55, 20);
		panel_State.add(btnVa);
		
		JButton btnMs = new JButton("MS");
		btnMs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addState("MS");
			}
		});
		btnMs.setHorizontalAlignment(SwingConstants.LEFT);
		btnMs.setFont(new Font("Rockwell", Font.PLAIN, 12));
		btnMs.setBounds(616, 11, 55, 20);
		panel_State.add(btnMs);
		
		JButton btnWa = new JButton("WA");
		btnWa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addState("WA");
			}
		});
		btnWa.setHorizontalAlignment(SwingConstants.LEFT);
		btnWa.setFont(new Font("Rockwell", Font.PLAIN, 12));
		btnWa.setBounds(616, 38, 55, 20);
		panel_State.add(btnWa);
		
		List_AllSitesModel = new DefaultListModel<c_ButaneSites>();
		List_ChecklistSitesModel = new DefaultListModel<c_ButaneSites>();		
		
		//Menubar
				JMenuBar menuBar = new JMenuBar();
				frm_CreateChecklist.setJMenuBar(menuBar);
									
				JMenu mnSupport = new JMenu("Support");
				menuBar.add(mnSupport);
				
				JMenuItem mntmNewTicket = new JMenuItem("New Ticket");
				mntmNewTicket.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						g_MainMenu.checkVersion();
						if(c_CheckOpenTickets.CheckTickets())
						{							
							g_TicketEntry.run(frm_CreateChecklist);
						}
						else
						{
							JOptionPane.showMessageDialog(frm_CreateChecklist, "Open Ticket Limit Exceeded. \n Please Close Old Tickets.");
						}
						
					}
				});
				mnSupport.add(mntmNewTicket);
				
				JMenuItem mntmCurrentTickets = new JMenuItem("Current Tickets");
				mntmCurrentTickets.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						g_CurrentTickets.run(frm_CreateChecklist);
					}
				});
				mnSupport.add(mntmCurrentTickets);
				
				JMenuItem mntmArchive = new JMenuItem("Archive");
				mntmArchive.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						g_ArchiveTickets.run(frm_CreateChecklist);
					}
				});
				mnSupport.add(mntmArchive);
				
				JMenu mnSites = new JMenu("Sites");
				menuBar.add(mnSites);
				
				JMenuItem mntmButaneSites = new JMenuItem("Butane Sites");
				mntmButaneSites.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						g_ViewSites.run(frm_CreateChecklist);
					}
				});
				mnSites.add(mntmButaneSites);
				
				JMenuItem mntmSiteChanges = new JMenuItem("Site Changes");
				mntmSiteChanges.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						g_SiteChanges.run(frm_CreateChecklist);
					}
				});
				mnSites.add(mntmSiteChanges);
				
				JMenu mnContacts = new JMenu("Contacts");
				menuBar.add(mnContacts);
				
				JMenuItem mntmENEmployees = new JMenuItem("EN Employees");
				mntmENEmployees.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						g_ViewEmployees.run(frm_CreateChecklist);
					}
				});
				mnContacts.add(mntmENEmployees);
				
				JMenuItem mntmEtpContacts = new JMenuItem("ETP Contacts");
				mntmEtpContacts.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						g_ViewSunoco.run(frm_CreateChecklist);
					}
				});
				mnContacts.add(mntmEtpContacts);
				
				JMenu mnTools = new JMenu("Tools");
				menuBar.add(mnTools);
				
				JMenuItem mntmCreateChecklist = new JMenuItem("Create Checklist");	
				mnTools.add(mntmCreateChecklist);
				mntmCreateChecklist.setEnabled(false);
	}
	
	private void PopulateSites()
	{			
		String client, site, clientabbrv, siteabbrv, stateabbrv;
		int siteid, view, sql, dev;
		float generation;
		boolean highperformance, centralSQL, centralView;
		c_ButaneSites newSite = null;
		String commandText = "SELECT Client, Site, SiteID, StateAbbrv,ClientAbbrv, SiteAbbrv, ViewIP, SQLIP, DevIP, Generation, HighPerformance, CentralSQL, CentralView from Sites where Generation > 0 order by Client, Site";
		
		ResultSet rs = c_Query.ExecuteResultSet(commandText);
		try{
			while ((rs!=null) && (rs.next()))
			{				
				client = rs.getString("Client"); 
				site = rs.getString("Site");				
				clientabbrv = rs.getString("ClientAbbrv");
				siteabbrv = rs.getString("SiteAbbrv");	
				stateabbrv = rs.getString("StateAbbrv");
				siteid = rs.getInt("SiteID");				
				view = rs.getInt("ViewIP");
				sql = rs.getInt("SQLIP");
				dev = rs.getInt("DevIP");
				generation = rs.getFloat("Generation");				
				highperformance = rs.getBoolean("HighPerformance");
				centralSQL = rs.getBoolean("CentralSQL");
				centralView = rs.getBoolean("CentralView");
				
				newSite = new c_ButaneSites(client, site, stateabbrv, clientabbrv, siteabbrv, siteid, view, sql, dev, generation, highperformance, centralSQL, centralView);
				List_AllSitesModel.addElement(newSite);
			}
		}
		catch(Exception e)
		{
			System.out.println("ERROR " + e.toString());
		}
		
		UpdateCounts();
		List_AllSites.setSelectedIndex(0);						
	}
	
	public static void addSite(int i)
	{		
		c_ButaneSites tmp = List_AllSites.getModel().getElementAt(i);		
		List_ChecklistSitesModel.addElement(tmp);
		List_AllSitesModel.removeElement(tmp);		
	}
	
	public static void addAllSites()
	{
		for(int i = 0; i < List_AllSites.getModel().getSize(); i++)
		{
			c_ButaneSites tmp = List_AllSites.getModel().getElementAt(i);		
			List_ChecklistSitesModel.addElement(tmp);
		}
		List_AllSitesModel.removeAllElements();
		UpdateCounts();
	}
	
	public static void addGen1Sites()
	{		
		for(int i = 0; i < List_AllSites.getModel().getSize(); i++)
		{
			if(List_AllSites.getModel().getElementAt(i).getGeneration() == 1)
			{
				addSite(i);
				i--;
			}
		}		
		UpdateCounts();
	}
	
	public static void addOtherClient()
	{		
		for(int i = 0; i < List_AllSites.getModel().getSize(); i++)
		{
			if((List_AllSites.getModel().getElementAt(i).getClient().compareTo("Energy Transfer") != 0 
				&& List_AllSites.getModel().getElementAt(i).getClient().compareTo("Marathon") != 0
				&& List_AllSites.getModel().getElementAt(i).getClient().compareTo("Motiva") != 0)
				&& List_AllSites.getModel().getElementAt(i).getClient().compareTo("Kinder Morgan") != 0
				&& List_AllSites.getModel().getElementAt(i).getClient().compareTo("Phillips 66") != 0)
			{
				addSite(i);
				i--;
			}
		}		
		UpdateCounts();
	}
	
	public static void addState(String state)
	{		
		for(int i = 0; i < List_AllSites.getModel().getSize(); i++)
		{
			if(List_AllSites.getModel().getElementAt(i).getStateAbbrv().compareTo(state) == 0)
			{
				addSite(i);
				i--;
			}
		}		
		UpdateCounts();
	}
	
	
	public static void addETPClient()
	{		
		for(int i = 0; i < List_AllSites.getModel().getSize(); i++)
		{
			if(List_AllSites.getModel().getElementAt(i).getClient().compareTo("Energy Transfer") == 0)
			{
				addSite(i);
				i--;
			}
		}		
		UpdateCounts();
	}
	
	public static void addKMClient()
	{		
		for(int i = 0; i < List_AllSites.getModel().getSize(); i++)
		{
			if(List_AllSites.getModel().getElementAt(i).getClient().compareTo("Kinder Morgan") == 0)
			{
				addSite(i);
				i--;
			}
		}		
		UpdateCounts();
	}
	
	public static void addMPCClient()
	{		
		for(int i = 0; i < List_AllSites.getModel().getSize(); i++)
		{
			if(List_AllSites.getModel().getElementAt(i).getClient().compareTo("Marathon") == 0)
			{
				addSite(i);
				i--;
			}
		}		
		UpdateCounts();
	}
	
	public static void addMTVClient()
	{		
		for(int i = 0; i < List_AllSites.getModel().getSize(); i++)
		{
			if(List_AllSites.getModel().getElementAt(i).getClient().compareTo("Motiva") == 0)
			{
				addSite(i);
				i--;
			}
		}		
		UpdateCounts();
	}

	public static void addPHLClient()
	{		
		for(int i = 0; i < List_AllSites.getModel().getSize(); i++)
		{
			if(List_AllSites.getModel().getElementAt(i).getClient().compareTo("Phillips 66") == 0)
			{
				addSite(i);
				i--;
			}
		}		
		UpdateCounts();
	}
	
	
	public static void addGen2Sites()
	{		
		for(int i = 0; i < List_AllSites.getModel().getSize(); i++)
		{
			if(List_AllSites.getModel().getElementAt(i).getGeneration() == 2)
			{
				addSite(i);
				i--;
			}
		}		
		UpdateCounts();
	}
	
	public static void addGen3Sites()
	{		
		for(int i = 0; i < List_AllSites.getModel().getSize(); i++)
		{
			if(List_AllSites.getModel().getElementAt(i).getGeneration() == 3)
			{
				addSite(i);
				i--;
			}
		}		
		UpdateCounts();
	}
	
	public static void addGen35Sites()
	{		
		for(int i = 0; i < List_AllSites.getModel().getSize(); i++)
		{
			if(List_AllSites.getModel().getElementAt(i).getGeneration() == 3.5)
			{
				addSite(i);
				i--;
			}
		}		
		UpdateCounts();
	}
	
	public static void addGen4Sites()
	{		
		for(int i = 0; i < List_AllSites.getModel().getSize(); i++)
		{
			if(List_AllSites.getModel().getElementAt(i).getGeneration() == 4)
			{
				addSite(i);
				i--;
			}
		}		
		UpdateCounts();
	}
	
	public static void addSites()
	{
		if(List_AllSites.getSelectedIndex() == -1)
		{
			JOptionPane.showMessageDialog(frm_CreateChecklist, "Pick a site dummy!");
		}
		else
		{
		int size = List_AllSites.getSelectedValuesList().size();		
		if(size > 1)
		{			
			int tmp2[] = List_AllSites.getSelectedIndices();
			int offset = 0;
			for(int i= 0; i < tmp2.length; i++)
			{						
				c_ButaneSites tmp = List_AllSites.getModel().getElementAt(tmp2[i]-offset);		
				List_ChecklistSitesModel.addElement(tmp);
				List_AllSitesModel.removeElement(tmp);	
				offset++;		
			}						
		}
		else
		{
			c_ButaneSites tmp = List_AllSites.getSelectedValue();		
			List_ChecklistSitesModel.addElement(tmp);
			List_AllSitesModel.removeElement(tmp);			
		}
		UpdateCounts();
		}
	}
	
	public static void removeSites()
	{
		int size = List_ChecklistSites.getSelectedValuesList().size();		
		if(size > 1)
		{									
				int tmp2[] = List_ChecklistSites.getSelectedIndices();
				int offset = 0;
				for(int i= 0; i < tmp2.length; i++)
				{						
					c_ButaneSites tmp = List_ChecklistSites.getModel().getElementAt(tmp2[i]-offset);		
					List_AllSitesModel.addElement(tmp);
					List_ChecklistSitesModel.removeElement(tmp);					
					offset++;
				}															
		}
		else
		{
			c_ButaneSites tmp = List_ChecklistSites.getSelectedValue();		
			List_AllSitesModel.addElement(tmp);
			List_ChecklistSitesModel.removeElement(tmp);							
		}
		UpdateCounts();
	}
	
	public static void ClearList()
	{
		for(int i = 0; i < List_ChecklistSites.getModel().getSize(); i++)
		{
			c_ButaneSites tmp = List_ChecklistSites.getModel().getElementAt(i);		
			List_AllSitesModel.addElement(tmp);
		}
		
		List_ChecklistSitesModel.removeAllElements();		
		UpdateCounts();				
	}
	
	public static void UpdateCounts()
	{
		List_AllSites.setModel(List_AllSitesModel);
		List_ChecklistSites.setModel(List_ChecklistSitesModel);
		siteNum = List_AllSitesModel.getSize();
		checkNum = List_ChecklistSitesModel.getSize();
		lblAllSites.setText("Available Sites (" + siteNum + ")");
		lblGeneratedSites.setText("Checklist Sites (" + checkNum + ")");
	}
	
	public static void Generate()
	{		
		String tmp = textArea.getText();
		String foo = "";
		String foo2 = "";
		for(int i = 0; i < List_ChecklistSites.getModel().getSize(); i++)
		{
			if(chckbxAdvanced.isSelected())
			{
				foo = tmp;	
				foo = foo.replace("$ClientAbbrv", List_ChecklistSitesModel.getElementAt(i).getClientAbbrv());
				foo = foo.replace("$SiteAbbrv", List_ChecklistSitesModel.getElementAt(i).getSiteAbbrv());
				foo = foo.replace("$Generation", Float.toString(List_ChecklistSitesModel.getElementAt(i).getGeneration()));
				foo = foo.replace("$ViewIP", Integer.toString(List_ChecklistSitesModel.getElementAt(i).getView()));
				foo = foo.replace("$SQLIP", Integer.toString(List_ChecklistSitesModel.getElementAt(i).getSQL()));
				foo = foo.replace("$DevIP", Integer.toString(List_ChecklistSitesModel.getElementAt(i).getDev()));			
				foo = foo.replace("$Client", List_ChecklistSitesModel.getElementAt(i).getClient());
				foo = foo.replace("$SiteID", Integer.toString(List_ChecklistSitesModel.getElementAt(i).getSiteID()));
				foo = foo.replace("$Site", List_ChecklistSitesModel.getElementAt(i).getSite());								
			}
			else
			{
				foo = List_ChecklistSitesModel.getElementAt(i).toString(); 
			}
			
			if(i == 0)
			{
				foo2 = foo2 + foo;
			}
			else
			{
				foo2 = foo2 + "\n" + foo;
			}	
		}
		
		StringSelection selection = new StringSelection(foo2);
		java.awt.datatransfer.Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboard.setContents(selection, selection);
		JOptionPane.showMessageDialog(frm_CreateChecklist, "Site List copied to clipboard.");
	}
}

