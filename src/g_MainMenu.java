import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
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
import java.awt.Font;
import java.awt.Toolkit;
//icons https://icons8.com/web-app/for/all/return
//flaticon.com
public class g_MainMenu {

	public static JFrame frmMainMenu;
	public static double version = 3.3;
	public static boolean firstrun = true;
	public static String TitleOnline = "Automated Support Program v" + version + "";	
	public static boolean CurrentTicketsNav;
	public static String CurrentUser = "";
	private static JLabel lblHello;
	private static JButton btnSupportArchive;
	public static boolean adminMode;
	private static JMenu mnAdmin;

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
					JOptionPane.showMessageDialog(frmMainMenu, "Cannot connect to database. Please verify internet connection and try again.");					
			        	return;
			    }
								
				if(!checkVersion())
				{
					JOptionPane.showMessageDialog(frmMainMenu, "There is a newer version of the program located on the I Drive!");
					//return;
				}				
					@SuppressWarnings("unused")
					g_MainMenu window = new g_MainMenu();
					g_MainMenu.frmMainMenu.setVisible(true);					
					firstrun = false;
					CurrentUser = c_GetComputerName.getComputerName();
					String Greeting = c_EasterEggs.Greetings();
					lblHello.setText(Greeting + " " + CurrentUser + "!");
					
					if(adminMode)
					{							
						mnAdmin.setVisible(true);
						JMenuItem mntmTicketCleanup = new JMenuItem("Ticket Cleanup");
						mntmTicketCleanup.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent arg0) {
								g_AutoCloseTickets.run();
							}
						});
						mnAdmin.add(mntmTicketCleanup);													
					}
					else
					{
						mnAdmin.setVisible(false);
					}													
			}
		});
	}
	/**
	 * Run the Main Menu GUI
	 */
	
	public static boolean checkVersion()
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
		frmMainMenu.setBounds(0,0,410, 599);
		frmMainMenu.setResizable(false);
		frmMainMenu.setLocationRelativeTo(null);
		frmMainMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMainMenu.getContentPane().setLayout(null);
		frmMainMenu.setTitle(TitleOnline);
		
		
		//Quick Lookup Button
		btnSupportArchive = new JButton("Support Archive");
		btnSupportArchive.setBounds(106, 208, 187, 54);
		frmMainMenu.getContentPane().add(btnSupportArchive);
		btnSupportArchive.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				g_ArchiveTickets.run();
				frmMainMenu.dispose();
								
			}
		});
		
	
		//Version Number
		JLabel lblV = new JLabel("");
		lblV.setText("v " + version);
		lblV.setHorizontalAlignment(SwingConstants.CENTER);
		lblV.setBounds(340, 527, 54, 14);
		frmMainMenu.getContentPane().add(lblV);
		
		JButton btnCurrentTickets = new JButton("Current Tickets");
		btnCurrentTickets.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				g_CurrentTickets.run();
				frmMainMenu.dispose();
			}
		});
		btnCurrentTickets.setBounds(106, 126, 187, 54);
		frmMainMenu.getContentPane().add(btnCurrentTickets);
		
		JButton btnNewTicket = new JButton("New Ticket");
		btnNewTicket.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CurrentTicketsNav = false;
				g_TicketEntry.run();
				frmMainMenu.dispose();
			}
		});
		btnNewTicket.setBounds(106, 47, 187, 54);
		frmMainMenu.getContentPane().add(btnNewTicket);
		
		JButton btnViewEmployees = new JButton("EN Employees");
		btnViewEmployees.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				g_ViewEmployees.run();
				frmMainMenu.dispose();
			}
		});
		btnViewEmployees.setBounds(106, 283, 187, 54);
		frmMainMenu.getContentPane().add(btnViewEmployees);
		
		JButton btnSunocoContacts = new JButton("ETP Contacts");
		btnSunocoContacts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				g_ViewSunoco.run();
				frmMainMenu.dispose();
			}
		});
		btnSunocoContacts.setBounds(106, 362, 187, 54);
		frmMainMenu.getContentPane().add(btnSunocoContacts);
		
		JButton btnSites = new JButton("Butane Sites");
		btnSites.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				g_ViewSites.run();
				frmMainMenu.dispose();
			}
		});
		btnSites.setBounds(106, 443, 187, 54);
		frmMainMenu.getContentPane().add(btnSites);
		
		lblHello = new JLabel("Hello Travis Johnston!");
		lblHello.setHorizontalAlignment(SwingConstants.CENTER);
		lblHello.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblHello.setBounds(10, 11, 384, 25);
		frmMainMenu.getContentPane().add(lblHello);
	
		
		//Menubar for Main Menu
		JMenuBar menuBar = new JMenuBar();
		frmMainMenu.setJMenuBar(menuBar);
		
		
		mnAdmin = new JMenu("Admin");
		menuBar.add(mnAdmin);
	
		
	}
}
