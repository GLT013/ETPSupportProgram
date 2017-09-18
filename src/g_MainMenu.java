import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
//icons https://icons8.com/web-app/for/all/return
//flaticon.com
public class g_MainMenu {

	public static JFrame frmMainMenu;
	public static double version = 2.00;
	public static boolean firstrun = true;
	public static boolean offlineMode = false;
	public static File SQLiteDB = new File("References/ETPSupport.db");
	private static JButton btnSyncForOffline;
	private static JMenuItem drop_Offline;
	private static JMenuItem drop_Online;
	private static JLabel lbl_Offline;
	public static String TitleOnline = "Automated Support Program v" + version + "";
	public static String TitleOffline = "Automated Support Program v" + version + " - OFFLINE";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        if ("Nimbus".equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }
		    }
		} catch (Exception e) {
		    // If Nimbus is not available, you can set the GUI to another look and feel.
		}
		EventQueue.invokeLater(new Runnable() {			
			@Override
			public void run() 
			{
				if(c_ConnectToDatabase.Connect() == false)
				{
					int reply = JOptionPane.showConfirmDialog(null, "Cannot connect to database. \n Continue in Offline Mode?" , "Offline Mode", JOptionPane.YES_NO_OPTION);
			        if (reply == JOptionPane.YES_OPTION)
			        {
			        	offlineMode = true;
			        	if(SQLiteDB.exists())
			        	{
			        		c_ConnectToDatabase.ConnectSQLite();
			        	}
			        	else
			        	{
			        		c_SyncForOfflineMode.CreateDatabase();
			        		c_ConnectToDatabase.ConnectSQLite();
			        		c_SyncForOfflineMode.CreateTables();
			        	}
			        }
			        else
			        {
			        	return;
			        }
				}
				
		
				if(!checkVersion())
				{
					JOptionPane.showMessageDialog(null, "There is a newer version of the program located on the I Drive!");
					return;
				}
				else
				{
					@SuppressWarnings("unused")
					g_MainMenu window = new g_MainMenu();
					g_MainMenu.frmMainMenu.setVisible(true);
					firstrun = false;
				}
				
			}
		});
	}
	/**
	 * Run the Main Menu GUI
	 */
	
	public static boolean checkVersion()
	{
		if(!offlineMode)
		{
			String _version = "SELECT Version from Version";
			ResultSet rs = c_Query.ExecuteResultSet(_version);
			
			try {
				rs.next();
				double _dbVersion = rs.getDouble("Version");			
				if(_dbVersion == version)
				{
					return true;
				}
				else
				{
					return false;
				}
				
			} catch (SQLException e) {		
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
			return true;	
		}
		else
		{
			return true;
		}
	}
	
	public static void run(JFrame frame) {
		try {
			
			if ((firstrun == false))
			{
				frmMainMenu.setVisible(true);				
				frmMainMenu.setLocationRelativeTo(frame);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * Create the application.
	 */
	public g_MainMenu() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		//Main Frame
		frmMainMenu = new JFrame();
		frmMainMenu.setIconImage(Toolkit.getDefaultToolkit().getImage(g_MainMenu.class.getResource("/icon.png")));
		frmMainMenu.setTitle("Main Menu");
		frmMainMenu.setBounds(0,0,410, 610);
		frmMainMenu.setResizable(false);
		frmMainMenu.setLocationRelativeTo(null);
		frmMainMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMainMenu.getContentPane().setLayout(null);
		if(offlineMode)
		{
			frmMainMenu.setTitle(TitleOffline);	
		}
		else
		{
			frmMainMenu.setTitle(TitleOnline);
		}

		//Quick Lookup Button
		JButton btnQuickLookup = new JButton("Support Archive");
		btnQuickLookup.setBounds(106, 172, 187, 54);
		frmMainMenu.getContentPane().add(btnQuickLookup);
		btnQuickLookup.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				g_SupportArchive.run();
				frmMainMenu.dispose();
								
			}
		});
		
	
		//Version Number
		JLabel lblV = new JLabel("");
		lblV.setText("v " + version);
		lblV.setHorizontalAlignment(SwingConstants.CENTER);
		lblV.setBounds(340, 536, 54, 14);
		frmMainMenu.getContentPane().add(lblV);
		
		JButton btnCurrentTickets = new JButton("Current Tickets");
		btnCurrentTickets.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				g_CurrentTickets.run();
				frmMainMenu.dispose();
			}
		});
		btnCurrentTickets.setBounds(106, 90, 187, 54);
		frmMainMenu.getContentPane().add(btnCurrentTickets);
		
		JButton btnNewTicket = new JButton("New Ticket");
		btnNewTicket.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				g_TicketEntry.run();
				frmMainMenu.dispose();
			}
		});
		btnNewTicket.setBounds(106, 11, 187, 54);
		frmMainMenu.getContentPane().add(btnNewTicket);
		
		JButton btnViewEmployees = new JButton("EN Employees");
		btnViewEmployees.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				g_ViewEmployees.run();
				frmMainMenu.dispose();
			}
		});
		btnViewEmployees.setBounds(106, 247, 187, 54);
		frmMainMenu.getContentPane().add(btnViewEmployees);
		
		JButton btnSunocoContacts = new JButton("Sunoco Contacts");
		btnSunocoContacts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				g_ViewSunoco.run();
				frmMainMenu.dispose();
			}
		});
		btnSunocoContacts.setBounds(106, 326, 187, 54);
		frmMainMenu.getContentPane().add(btnSunocoContacts);
		
		JButton btnSites = new JButton("Butane Sites");
		btnSites.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				g_ViewSites.run();
				frmMainMenu.dispose();
			}
		});
		btnSites.setBounds(106, 407, 187, 54);
		frmMainMenu.getContentPane().add(btnSites);
		
		btnSyncForOffline = new JButton("Sync For Offline Mode");
		btnSyncForOffline.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				c_SyncForOfflineMode.run();
			}
		});
		btnSyncForOffline.setBounds(106, 496, 187, 54);
		frmMainMenu.getContentPane().add(btnSyncForOffline);
		
		lbl_Offline = new JLabel("Offline!");
		lbl_Offline.setFont(new Font("Tahoma", Font.BOLD, 14));
		lbl_Offline.setForeground(Color.RED);
		lbl_Offline.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_Offline.setBounds(10, 536, 54, 14);
		lbl_Offline.setVisible(false);
		frmMainMenu.getContentPane().add(lbl_Offline);
		
		JButton btnOnline = new JButton("Online");
		btnOnline.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				c_ConnectToDatabase.ConnectSQLite();
				c_SyncForOnlineMode.run();
			}
		});
		btnOnline.setBounds(330, 454, 64, 54);
		frmMainMenu.getContentPane().add(btnOnline);
		if(offlineMode)
		{
			btnSyncForOffline.setEnabled(false);
			lbl_Offline.setVisible(true);
		}
		
		//Menubar for Main Menu
		JMenuBar menuBar = new JMenuBar();
		frmMainMenu.setJMenuBar(menuBar);		
		JMenu mnSettings = new JMenu("Settings");
		menuBar.add(mnSettings);		
		drop_Offline = new JMenuItem("Go Offline");		
		drop_Offline.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {				
			
				GoOffline();
				
			}
		});
		if(offlineMode)
		{
			drop_Offline.setEnabled(false);
		}
		else
		{
			drop_Offline.setEnabled(true);
		}
		
		drop_Online = new JMenuItem("Go Online");
		drop_Online.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				GoOnline();
			}
		});
		mnSettings.add(drop_Online);
		mnSettings.add(drop_Offline);
		if(offlineMode)
		{
			drop_Online.setEnabled(true);
		}
		else
		{
			drop_Online.setEnabled(false);
		}
		
	}
	
	private void GoOffline()
	{		
			offlineMode = true;
			btnSyncForOffline.setEnabled(false);
			drop_Offline.setEnabled(false);
			drop_Online.setEnabled(true);
			lbl_Offline.setVisible(true);
			if(SQLiteDB.exists())
        	{
        		c_ConnectToDatabase.ConnectSQLite();
        	}
        	else
        	{
        		c_SyncForOfflineMode.CreateDatabase();
        		c_ConnectToDatabase.ConnectSQLite();
        		c_SyncForOfflineMode.CreateTables();
        	}
			
	}
	
	private void GoOnline()
	{
		if(c_ConnectToDatabase.Connect() == false)
		{
			JOptionPane.showMessageDialog(null, "Unable to connect to online database.");
			return;
			
		}
		else
		{
			offlineMode = false;
			btnSyncForOffline.setEnabled(true);
			drop_Offline.setEnabled(true);
			drop_Online.setEnabled(false);
			lbl_Offline.setVisible(false);
		}
	}
}
