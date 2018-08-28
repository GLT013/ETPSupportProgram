import javax.swing.JFrame;
import java.awt.HeadlessException;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;
import javax.swing.SwingConstants;
import java.awt.Toolkit;
import javax.swing.JTextArea;

public class g_NewServer {

	private JFrame frmNewServer;
	private JTextField txtServerName;
	private JTextField txtServerIP;
	private JTextArea txtServerDesc;
	private JCheckBox chk_Internal;
	
	/**
	 * Launch the application.
	 */
	
			public static void run() {
				try {
					g_NewServer window = new g_NewServer();
					window.frmNewServer.setVisible(true);
					window.frmNewServer.setLocationRelativeTo( g_ViewSites.frmButaneSites );
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		
	

	/**
	 * Create the application.
	 */
	public g_NewServer() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmNewServer = new JFrame();
		frmNewServer.setResizable(false);
		frmNewServer.setIconImage(Toolkit.getDefaultToolkit().getImage(g_NewServer.class.getResource("/icon.png")));
		frmNewServer.setBounds(100, 100, 679, 514);
		frmNewServer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmNewServer.getContentPane().setLayout(null);
		frmNewServer.setTitle("Automated Support Program v." + g_MainMenu.version);
		
		
		JLabel lblAddNewEmployee = new JLabel("Add New Server");
		lblAddNewEmployee.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddNewEmployee.setBounds(199, 11, 242, 19);
		lblAddNewEmployee.setFont(new Font("Plantagenet Cherokee", Font.BOLD, 18));
		frmNewServer.getContentPane().add(lblAddNewEmployee);
		
		JLabel lblNamefirst = new JLabel("Server Name");
		lblNamefirst.setFont(new Font("Plantagenet Cherokee", Font.BOLD, 14));
		lblNamefirst.setBounds(10, 67, 179, 14);
		frmNewServer.getContentPane().add(lblNamefirst);
		
		txtServerName = new JTextField();
		txtServerName.setBounds(199, 58, 242, 35);
		frmNewServer.getContentPane().add(txtServerName);
		txtServerName.setColumns(10);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InsertSite();
			}
		});
		btnSubmit.setBounds(269, 385, 101, 35);
		frmNewServer.getContentPane().add(btnSubmit);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				g_ViewSites.run();
				frmNewServer.dispose();
			}
		});
		btnBack.setBounds(10, 431, 89, 23);
		frmNewServer.getContentPane().add(btnBack);
		
		JLabel lblSite = new JLabel("Server IP");
		lblSite.setFont(new Font("Plantagenet Cherokee", Font.BOLD, 14));
		lblSite.setBounds(10, 113, 179, 14);
		frmNewServer.getContentPane().add(lblSite);
		
		txtServerIP = new JTextField();
		txtServerIP.setColumns(10);
		txtServerIP.setBounds(199, 104, 242, 35);
		frmNewServer.getContentPane().add(txtServerIP);
		
		JLabel lblSiteId = new JLabel("Description");
		lblSiteId.setFont(new Font("Plantagenet Cherokee", Font.BOLD, 14));
		lblSiteId.setBounds(10, 164, 179, 14);
		frmNewServer.getContentPane().add(lblSiteId);
		
		
		
		txtServerDesc = new JTextArea();
		txtServerDesc.setLineWrap(true);
		txtServerDesc.setBounds(199, 157, 379, 194);
		frmNewServer.getContentPane().add(txtServerDesc);
		
		chk_Internal = new JCheckBox("Internal Server");
		chk_Internal.setFont(new Font("Tahoma", Font.BOLD, 14));
		chk_Internal.setBounds(447, 62, 194, 23);
		frmNewServer.getContentPane().add(chk_Internal);
		
		//Menubar
		JMenuBar menuBar = new JMenuBar();
		frmNewServer.setJMenuBar(menuBar);
		
		
				
		JMenu mnSupport = new JMenu("Support");
		menuBar.add(mnSupport);
		
		JMenuItem mntmNewTicket = new JMenuItem("New Ticket");
		mntmNewTicket.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				g_MainMenu.checkVersion();
				if(c_CheckOpenTickets.CheckTickets())
				{							
					g_TicketEntry.run();
					frmNewServer.dispose();
				}
				else
				{
					JOptionPane.showMessageDialog(frmNewServer, "Open Ticket Limit Exceeded. \n Please Close Old Tickets.");
				}
				
			}
		});
		mnSupport.add(mntmNewTicket);
		
		JMenuItem mntmCurrentTickets = new JMenuItem("Current Tickets");
		mntmCurrentTickets.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				g_CurrentTickets.run();
				frmNewServer.dispose();
			}
		});
		mnSupport.add(mntmCurrentTickets);
		
		JMenuItem mntmArchive = new JMenuItem("Archive");
		mntmArchive.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				g_ArchiveTickets.run();
				frmNewServer.dispose();
			}
		});
		mnSupport.add(mntmArchive);
		
		JMenu mnSites = new JMenu("Sites");
		menuBar.add(mnSites);
		
		JMenuItem mntmButaneSites = new JMenuItem("Butane Sites");
		mntmButaneSites.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				g_ViewSites.run();
				frmNewServer.dispose();
			}
		});
		mnSites.add(mntmButaneSites);
		
		JMenuItem mntmSiteChanges = new JMenuItem("Site Changes");
		mntmSiteChanges.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				g_SiteChanges.run();
				frmNewServer.dispose();
			}
		});
		mnSites.add(mntmSiteChanges);
		
		JMenu mnContacts = new JMenu("Contacts");
		menuBar.add(mnContacts);
		
		JMenuItem mntmENEmployees = new JMenuItem("EN Employees");
		mntmENEmployees.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				g_ViewEmployees.run();
				frmNewServer.dispose();
			}
		});
		mnContacts.add(mntmENEmployees);
		
		JMenuItem mntmEtpContacts = new JMenuItem("ETP Contacts");
		mntmEtpContacts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				g_ViewSunoco.run();
				frmNewServer.dispose();
			}
		});
		mnContacts.add(mntmEtpContacts);
	}
	
	public void InsertSite(){
		
		String Server = txtServerName.getText();
		String ServerIP = txtServerIP.getText();
		String ServerDesc = txtServerDesc.getText();
		boolean internal = chk_Internal.isSelected();
		System.out.println(ServerDesc);
		System.out.println(Server);
		System.out.println(ServerIP);
				
		if (Server.compareTo("") == 0 || ServerIP.compareTo("") == 0)
		{
			JOptionPane.showMessageDialog(frmNewServer, "Server and Server IP are required fields.");
			return;
		}
		
		String commandText = "SELECT COUNT(*) as total From Servers WHERE ServerName = '" + Server + "'";
		
		ResultSet rs = c_Query.ExecuteResultSet(commandText);
		try {
			rs.next();			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			if(rs.getInt("total") > 0)
			{
			//employee already exists.
				JOptionPane.showMessageDialog(frmNewServer, "Server " + Server + " already exists in the database.");
				
			}
			else
			{
			
			commandText = "INSERT INTO Servers(ServerName,ServerIP,Description,Internal) "
						+ "VALUES ( '" + Server + "','" + ServerIP + "','" + ServerDesc + "','" + internal + "')";
			
			c_Query.UpdateResultSet(commandText);
				JOptionPane.showMessageDialog(frmNewServer, "Server " + Server + " was successfully added.");
				ClearFields();
				
				g_ViewSites.run();
				frmNewServer.dispose();
				
				
			}
		} catch (HeadlessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	

	}
	
	public void ClearFields()
	{
		txtServerName.setText("");
		txtServerIP.setText("");
		chk_Internal.setSelected(false);
		txtServerDesc.setText("");		
	}
}
