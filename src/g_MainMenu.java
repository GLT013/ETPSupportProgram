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
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
//icons https://icons8.com/web-app/for/all/return
//flaticon.com
public class g_MainMenu {
	public static JFrame frmMainMenu;
	public static double version = 3.12;
	public static int ticketMax;
	public static boolean firstrun = true;
	public static String TitleOnline = "EN Automation Support Program v" + version + "";	
	public static boolean CurrentTicketsNav;
	public static String CurrentUser = "";
	public static String CurrentUserTitle = "";
	private static JLabel lblHello;
	private static JLabel lbl_Title;
	private static JButton btnSupportArchive;
	public static boolean adminMode;
	private static JMenu mnAdmin;
	private static JButton btnNewTicket;
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
				
				if(!c_GetMacAddress.getMacAddress())
				{
					JOptionPane.showMessageDialog(frmMainMenu, " It is I, Arthur, son of Uther Pendragon, \n from the castle of Camelot. \n King of the Britons, defeater of the Saxons, \n Sovereign of all England!", "Halt! Who goes there?",							
					        JOptionPane.ERROR_MESSAGE);
					JOptionPane.showMessageDialog(frmMainMenu, "I am, and this is my trusty servant Patsy. \n We have ridden the length and breadth of the land in search of knights \n who will join me in my court at Camelot. \n I must speak with your lord and master.", "Pull the other one!",							
					        JOptionPane.ERROR_MESSAGE);
					JOptionPane.showMessageDialog(frmMainMenu, "Yes!", "What? Ridden on a horse?",							
					        JOptionPane.ERROR_MESSAGE);
					JOptionPane.showMessageDialog(frmMainMenu, "What?!", "You're using coconuts!",							
					        JOptionPane.ERROR_MESSAGE);
					JOptionPane.showMessageDialog(frmMainMenu, "So? We have ridden since the snows of winter covered this land, \n through the kingdom of Mercia, through...", " You've got two empty halves of coconut and you're bangin' 'em together.",							
					        JOptionPane.ERROR_MESSAGE);
					JOptionPane.showMessageDialog(frmMainMenu, "We found them.", "Where'd you get the coconuts?",							
					        JOptionPane.ERROR_MESSAGE);
					JOptionPane.showMessageDialog(frmMainMenu, "What do you mean?", "Found them? The coconuts tropical!",							
					        JOptionPane.ERROR_MESSAGE);
					JOptionPane.showMessageDialog(frmMainMenu, "The swallow may fly south with the sun or the house martin \n or the plover may seek warmer climes in winter, \n yet these are not strangers to our land?", "Well, this is a temperate zone.",							
					        JOptionPane.ERROR_MESSAGE);
					JOptionPane.showMessageDialog(frmMainMenu, "Not at all. They could be carried.", "Are you suggesting that coconuts migrate? ",							
					        JOptionPane.ERROR_MESSAGE);
					JOptionPane.showMessageDialog(frmMainMenu, "It could grip it by the husk!", "What? A swallow carrying a coconut?",							
					        JOptionPane.ERROR_MESSAGE);
					JOptionPane.showMessageDialog(frmMainMenu, "It's a simple question of weight ratios! A five ounce bird could not carry a one pound coconut. \n \n Well, it doesn't matter. Will you go and tell your master that Arthur \n from the Court of Camelot is here?", "It's not a question of where he grips it! ",							
					        JOptionPane.ERROR_MESSAGE);
					return;
					
				}
								
				if(!checkVersion())
				{
					JOptionPane.showMessageDialog(frmMainMenu, "Support Program Update Required!");
					return;
				}				
					@SuppressWarnings("unused")
					g_MainMenu window = new g_MainMenu();
					g_MainMenu.frmMainMenu.setVisible(true);					
					firstrun = false;			
					//String Greeting = c_EasterEggs.Greetings();
					//lblHello.setText(Greeting + " " + CurrentUser + "!");
					lblHello.setText(CurrentUser);
					lbl_Title.setText("" + CurrentUserTitle + "");
					
					
					JPanel panel = new JPanel();
					panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
					panel.setBounds(152, 65, 215, 295);
					frmMainMenu.getContentPane().add(panel);
					panel.setLayout(null);
					
					btnNewTicket = new JButton("New Ticket");
					btnNewTicket.setBounds(10, 43, 187, 54);
					panel.add(btnNewTicket);
					
					JButton btnCurrentTickets = new JButton("Current Tickets");
					btnCurrentTickets.setBounds(10, 122, 187, 54);
					panel.add(btnCurrentTickets);
					
					
					//Quick Lookup Button
					btnSupportArchive = new JButton("Support Archive");
					btnSupportArchive.setBounds(10, 204, 187, 54);
					panel.add(btnSupportArchive);
					
					JLabel lblSupport = new JLabel("Support");
					lblSupport.setBounds(0, 5, 215, 25);
					panel.add(lblSupport);
					lblSupport.setHorizontalAlignment(SwingConstants.CENTER);
					lblSupport.setFont(new Font("Tahoma", Font.BOLD, 14));
					
					JPanel panel_1 = new JPanel();
					panel_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
					panel_1.setBounds(10, 387, 215, 192);
					frmMainMenu.getContentPane().add(panel_1);
					panel_1.setLayout(null);
					
					JButton btnViewEmployees = new JButton("EN Employees");
					btnViewEmployees.setBounds(10, 37, 187, 54);
					panel_1.add(btnViewEmployees);
					
					JButton btnSunocoContacts = new JButton("ETP Contacts");
					btnSunocoContacts.setBounds(10, 115, 187, 54);
					panel_1.add(btnSunocoContacts);
					
					JLabel lblContacts = new JLabel("Contacts");
					lblContacts.setHorizontalAlignment(SwingConstants.CENTER);
					lblContacts.setFont(new Font("Tahoma", Font.BOLD, 14));
					lblContacts.setBounds(0, 5, 215, 25);
					panel_1.add(lblContacts);
					
					JPanel panel_2 = new JPanel();
					panel_2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
					panel_2.setBounds(270, 387, 215, 192);
					frmMainMenu.getContentPane().add(panel_2);
					panel_2.setLayout(null);
					
					JButton btnSites = new JButton("Butane Sites");
					btnSites.setBounds(10, 37, 187, 54);
					panel_2.add(btnSites);
					
					JButton btnSiteChanges = new JButton("Site Changes");
					btnSiteChanges.setBounds(10, 115, 187, 54);
					panel_2.add(btnSiteChanges);
					
					JLabel lblSites = new JLabel("Sites");
					lblSites.setHorizontalAlignment(SwingConstants.CENTER);
					lblSites.setFont(new Font("Tahoma", Font.BOLD, 14));
					lblSites.setBounds(0, 5, 215, 25);
					panel_2.add(lblSites);
					btnSiteChanges.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							g_SiteChanges.run();
							frmMainMenu.dispose();
						}
					});
					btnSites.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							g_ViewSites.run();
							frmMainMenu.dispose();
						}
					});
					btnSunocoContacts.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							g_ViewSunoco.run();
							frmMainMenu.dispose();
						}
					});
					btnViewEmployees.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							g_ViewEmployees.run();
							frmMainMenu.dispose();
						}
					});
					btnSupportArchive.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent arg0) {
							g_ArchiveTickets.run();
							frmMainMenu.dispose();
											
						}
					});
					btnCurrentTickets.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							g_CurrentTickets.run();
							frmMainMenu.dispose();
						}
					});
					btnNewTicket.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							
							checkVersion();
							if(c_CheckOpenTickets.CheckTickets())
							{
								CurrentTicketsNav = false;
								g_TicketEntry.run();
								frmMainMenu.dispose();
							}
							else
							{
								JOptionPane.showMessageDialog(frmMainMenu, "Open Ticket Limit Exceeded. \n Please Close Old Tickets.");
							}
						}
					});
					
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
		
			String _version = "SELECT Version, MaxOpenTickets from Version";
			ResultSet rs = c_Query.ExecuteResultSet(_version);
			
			try {
				rs.next();
				ticketMax = rs.getInt("MaxOpenTickets");
				double _dbVersion = rs.getDouble("Version");	
				double abs = Math.abs(version - _dbVersion);				
				if(_dbVersion == version)
				{
					return true;
				}
				else if ((abs) < .2)
				{
					JOptionPane.showMessageDialog(frmMainMenu, "There is a newer version of the program located on subversion!");
					return true;
				}
				else
				{
					return false;
				}
				
			} catch (SQLException e) {		
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
		frmMainMenu.setTitle("EN Automation Support Program v." + g_MainMenu.version);
		frmMainMenu.setBounds(0,0,524, 665);
		frmMainMenu.setResizable(false);
		frmMainMenu.setLocationRelativeTo(null);
		frmMainMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMainMenu.getContentPane().setLayout(null);
		frmMainMenu.setTitle(TitleOnline);
		
	
		//Version Number
		JLabel lblV = new JLabel("");
		lblV.setText("v " + version);
		lblV.setHorizontalAlignment(SwingConstants.CENTER);
		lblV.setBounds(462, 585, 54, 14);
		frmMainMenu.getContentPane().add(lblV);
		
		lblHello = new JLabel("Hello Travis Johnston!");
		lblHello.setHorizontalAlignment(SwingConstants.CENTER);
		lblHello.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblHello.setBounds(10, 11, 498, 25);
		frmMainMenu.getContentPane().add(lblHello);
		
		lbl_Title = new JLabel("");
		lbl_Title.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_Title.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lbl_Title.setBounds(10, 28, 498, 25);
		frmMainMenu.getContentPane().add(lbl_Title);
	
		
		//Menubar for Main Menu
		JMenuBar menuBar = new JMenuBar();
		frmMainMenu.setJMenuBar(menuBar);
		
		
		mnAdmin = new JMenu("Admin");
		menuBar.add(mnAdmin);
		
		JMenu mnSupport = new JMenu("Support");
		menuBar.add(mnSupport);
		
		JMenuItem mntmNewTicket = new JMenuItem("New Ticket");
		mntmNewTicket.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkVersion();
				if(c_CheckOpenTickets.CheckTickets())
				{
					CurrentTicketsNav = false;
					g_TicketEntry.run();
					frmMainMenu.dispose();
				}
				else
				{
					JOptionPane.showMessageDialog(frmMainMenu, "Open Ticket Limit Exceeded. \n Please Close Old Tickets.");
				}
				
			}
		});
		mnSupport.add(mntmNewTicket);
		
		JMenuItem mntmCurrentTickets = new JMenuItem("Current Tickets");
		mntmCurrentTickets.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				g_CurrentTickets.run();
				frmMainMenu.dispose();
			}
		});
		mnSupport.add(mntmCurrentTickets);
		
		JMenuItem mntmArchive = new JMenuItem("Archive");
		mntmArchive.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				g_ArchiveTickets.run();
				frmMainMenu.dispose();
			}
		});
		mnSupport.add(mntmArchive);
		
		JMenu mnSites = new JMenu("Sites");
		menuBar.add(mnSites);
		
		JMenuItem mntmButaneSites = new JMenuItem("Butane Sites");
		mntmButaneSites.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				g_ViewSites.run();
				frmMainMenu.dispose();
			}
		});
		mnSites.add(mntmButaneSites);
		
		JMenuItem mntmSiteChanges = new JMenuItem("Site Changes");
		mntmSiteChanges.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				g_SiteChanges.run();
				frmMainMenu.dispose();
			}
		});
		mnSites.add(mntmSiteChanges);
		
		JMenu mnContacts = new JMenu("Contacts");
		menuBar.add(mnContacts);
		
		JMenuItem mntmENEmployees = new JMenuItem("EN Employees");
		mntmENEmployees.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				g_ViewEmployees.run();
				frmMainMenu.dispose();
			}
		});
		mnContacts.add(mntmENEmployees);
		
		JMenuItem mntmEtpContacts = new JMenuItem("ETP Contacts");
		mntmEtpContacts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				g_ViewSunoco.run();
				frmMainMenu.dispose();
			}
		});
		mnContacts.add(mntmEtpContacts);
	
		
	}
}
