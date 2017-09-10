import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
//icons https://icons8.com/web-app/for/all/return
//flaticon.com
public class g_MainMenu {

	public static JFrame frmMainMenu;
	public static String version = "v 1.0.6";
	public static boolean firstrun = true; 

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
			public void run() {
				if(c_ConnectToDatabase.Connect() == false)
				{
					JOptionPane.showMessageDialog(null, "Unable to connect to Support Database.");					
					return;
				}
				try {
					@SuppressWarnings("unused")
					g_MainMenu window = new g_MainMenu();
					g_MainMenu.frmMainMenu.setVisible(true);
					firstrun = false;
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	/**
	 * Run the Main Menu GUI
	 */
	
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
		frmMainMenu.setTitle("Main Menu");
		frmMainMenu.setBounds(0,0,410, 610);
		frmMainMenu.setResizable(false);
		frmMainMenu.setLocationRelativeTo(null);
		frmMainMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMainMenu.getContentPane().setLayout(null);
		
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
		lblV.setText("v 1.11");
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
		
		//Menubar for Main Menu
		JMenuBar menuBar = new JMenuBar();
		frmMainMenu.setJMenuBar(menuBar);		
		JMenu mnSettings = new JMenu("Settings");
		menuBar.add(mnSettings);		
		JMenuItem mntmDatabase = new JMenuItem("Database");
		mntmDatabase.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {				
				c_DatabaseSettings.run();
			}
		});
		mnSettings.add(mntmDatabase);
	}
}
