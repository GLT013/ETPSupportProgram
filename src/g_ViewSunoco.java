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
import java.util.Arrays;
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
import java.net.URISyntaxException;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;
import java.awt.Color;
import java.awt.Toolkit;
import javax.swing.ListSelectionModel;

public class g_ViewSunoco {

	static JFrame frmSunocoContacts;
	private static JList<String> list = new JList<String>();
	private static DefaultListModel<String> listModel = new DefaultListModel<String>();
	private JScrollPane scrollPane;
	JTextField lblCompany = new JTextField("");
	JTextField lblEmail = new JTextField("");
	JTextField lblMobile = new JTextField("");
	private final JButton btnEdit = new JButton("");
	private JTextField lblName;
	private final JButton btnAccept = new JButton("");
	private final JButton btnCancel = new JButton("");
	final ArrayList <c_Employees> result = new ArrayList<c_Employees>();
	private final JButton btnNewContact = new JButton("");
	private final JButton btnNewButton = new JButton("Back");
	private final JButton btnEmail = new JButton("");
	

	/**
	 * Launch the application.
	 */
	
			public static void run(JFrame frame) {
				try {
					@SuppressWarnings("unused")
					g_ViewSunoco window = new g_ViewSunoco();
					g_ViewSunoco.frmSunocoContacts.setVisible(true);
					g_ViewSunoco.frmSunocoContacts.setLocationRelativeTo(frame);
					frame.dispose();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
	

	/**
	 * Create the application.
	 */
	public g_ViewSunoco() {
		initialize();
		PopulateEmployees();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmSunocoContacts = new JFrame();
		frmSunocoContacts.setResizable(false);
		frmSunocoContacts.setIconImage(Toolkit.getDefaultToolkit().getImage(g_ViewSunoco.class.getResource("/icon.png")));
		frmSunocoContacts.setBounds(100, 100, 768, 461);
		frmSunocoContacts.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frmSunocoContacts.getContentPane().setLayout(null);		
		frmSunocoContacts.setTitle("Automated Support Program v." + g_MainMenu.version);
		
		
		frmSunocoContacts.addWindowListener(new java.awt.event.WindowAdapter() {
			 @Override
			    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				 listModel.removeAllElements();
				 list.setModel(listModel);
				 frmSunocoContacts.dispose(); 
			 }
		
		});
		
		scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		scrollPane.setBounds(36, 40, 194, 241);
		frmSunocoContacts.getContentPane().add(scrollPane);
		
		JLabel lblNewLabel = new JLabel("Name:");
		lblNewLabel.setFont(new Font("Rockwell", Font.PLAIN, 18));
		lblNewLabel.setBounds(275, 55, 113, 14);
		frmSunocoContacts.getContentPane().add(lblNewLabel);
		
		JLabel lblTitle2 = new JLabel("Company:");
		lblTitle2.setFont(new Font("Rockwell", Font.PLAIN, 18));
		lblTitle2.setBounds(275, 115, 113, 25);
		frmSunocoContacts.getContentPane().add(lblTitle2);
		
		JLabel lblEmail2 = new JLabel("Email:");
		lblEmail2.setFont(new Font("Rockwell", Font.PLAIN, 18));
		lblEmail2.setBounds(275, 174, 113, 14);
		frmSunocoContacts.getContentPane().add(lblEmail2);
		
		JLabel lblMobilePhone2 = new JLabel("Mobile Phone:");
		lblMobilePhone2.setFont(new Font("Rockwell", Font.PLAIN, 18));
		lblMobilePhone2.setBounds(275, 233, 125, 14);
		frmSunocoContacts.getContentPane().add(lblMobilePhone2);
		lblCompany.setEditable(false);
		
		
		lblCompany.setFont(new Font("Rockwell", Font.PLAIN, 12));
		lblCompany.setBounds(410, 110, 275, 30);
		frmSunocoContacts.getContentPane().add(lblCompany);
		lblEmail.setEditable(false);
		
		
		lblEmail.setFont(new Font("Rockwell", Font.PLAIN, 12));
		lblEmail.setBounds(410, 169, 275, 30);
		frmSunocoContacts.getContentPane().add(lblEmail);
		
		lblName = new JTextField();
		lblName.setFont(new Font("Rockwell", Font.PLAIN, 14));
		lblName.setBackground(UIManager.getColor("Button.background"));
		lblName.setEditable(false);
		lblName.setBounds(410, 51, 275, 31);		
		frmSunocoContacts.getContentPane().add(lblName);
		lblName.setColumns(10);
		lblMobile.setEditable(false);
		
		lblMobile.setFont(new Font("Rockwell", Font.PLAIN, 14));
		lblMobile.setBounds(410, 228, 275, 30);
		frmSunocoContacts.getContentPane().add(lblMobile);
		btnEdit.setToolTipText("Edit");	
		
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {		
				
					lblName.setEditable(true);
					lblCompany.setEditable(true);
					lblEmail.setEditable(true);				
					lblMobile.setEditable(true);				
				
					btnEdit.setEnabled(false);
					btnEdit.setVisible(false);
					btnCancel.setVisible(true);
					btnCancel.setEnabled(true);
					btnAccept.setVisible(true);
					btnAccept.setEnabled(true);
				}
			
		});   
		
		btnEdit.setIcon(new ImageIcon(g_ViewSunoco.class.getResource("/edit.png")));
		btnEdit.setBounds(703, 11, 32, 32);
		
		frmSunocoContacts.getContentPane().add(btnEdit);
		btnAccept.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AcceptEdits();
			}
		});
		btnAccept.setToolTipText("Accept Edits");
		btnAccept.setEnabled(false);
		btnAccept.setVisible(false);
		btnAccept.setIcon(new ImageIcon(g_ViewSunoco.class.getResource("/ok-icon.png")));
		btnAccept.setBounds(661, 11, 34, 34);
		
		frmSunocoContacts.getContentPane().add(btnAccept);
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CancelEdits();
			}
		});
		btnCancel.setToolTipText("Cancel Edits");
		btnCancel.setEnabled(false);
		btnCancel.setIcon(new ImageIcon(g_ViewSunoco.class.getResource("/Actions-window-close-icon.png")));
		btnCancel.setBounds(703, 11, 32, 32);
		
		frmSunocoContacts.getContentPane().add(btnCancel);
		
		JLabel lblSunocoContacts = new JLabel("ETP Contacts");
		lblSunocoContacts.setForeground(Color.BLUE);
		lblSunocoContacts.setFont(new Font("Rockwell", Font.PLAIN, 18));
		lblSunocoContacts.setBounds(39, 15, 187, 18);
		frmSunocoContacts.getContentPane().add(lblSunocoContacts);
		
		JButton btnRemoveContact = new JButton("");
		btnRemoveContact.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
					int index = list.getSelectedIndex();
					String removalName = result.get(index).getName();				
					int reply = JOptionPane.showConfirmDialog(frmSunocoContacts, "Really delete " + removalName + "?" , "Remove Contact?", JOptionPane.YES_NO_OPTION);
			        if (reply == JOptionPane.YES_OPTION)
			        {
			          String commandText = "DELETE FROM Sunoco_Contacts WHERE Name = '" + removalName + "'";
			          c_Query.ExecuteQuery(commandText);
			          result.remove(index);
			          JOptionPane.showMessageDialog(frmSunocoContacts, removalName + " has been deleted. \n You monster.");
			          RefreshList();
			        }
			        else
			        {
			        	//do nothing.
			        }
				
			}
		});
		btnRemoveContact.setIcon(new ImageIcon(g_ViewSunoco.class.getResource("/Minus-48.png")));
		btnRemoveContact.setToolTipText("Remove Contact");
		btnRemoveContact.setBounds(198, 292, 32, 32);
		frmSunocoContacts.getContentPane().add(btnRemoveContact);		
			btnNewContact.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) 
				{					
					g_NewSunocoContact.run(frmSunocoContacts);
				}
				
			});
		
		btnNewContact.setIcon(new ImageIcon(g_ViewSunoco.class.getResource("/Plus-48.png")));
		btnNewContact.setToolTipText("New Contact");
		btnNewContact.setBounds(36, 292, 32, 32);
		
		frmSunocoContacts.getContentPane().add(btnNewContact);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setFont(new Font("Rockwell", Font.PLAIN, 13));
		
		
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				try{
					if(!arg0.getValueIsAdjusting())
					{
						CancelEdits();
						lblName.setText(result.get(list.getSelectedIndex()).getName());
						lblCompany.setText(result.get(list.getSelectedIndex()).getTitle());
						lblEmail.setText(result.get(list.getSelectedIndex()).getEmail());					
						lblMobile.setText(result.get(list.getSelectedIndex()).getMobile());
					}
				}
				catch(Exception e)
				{
					
				}
			}
		});
		
		listModel.removeAllElements();
		list.setModel(listModel);
		btnNewButton.setFont(new Font("Rockwell", Font.PLAIN, 11));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				g_MainMenu.run(frmSunocoContacts);
				frmSunocoContacts.dispose();
			}
		});
		btnNewButton.setBounds(10, 360, 83, 32);
		
		frmSunocoContacts.getContentPane().add(btnNewButton);
		
		JButton btnMobile = new JButton("");
		btnMobile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String getMobile = result.get(list.getSelectedIndex()).getMobile();				
				String getEmployee = result.get(list.getSelectedIndex()).getName();
				int reply = JOptionPane.showConfirmDialog(frmSunocoContacts, "Call " + getEmployee + " Mobile?" , getEmployee + " Mobile Phone #", JOptionPane.YES_NO_OPTION);
		        if (reply == JOptionPane.YES_OPTION)
		        {
		        	try {

						Runtime.getRuntime().exec(new String[] {"C:\\Program Files (x86)\\Microsoft Office\\Office16\\lync.exe", "/C", "Callto:tel:+ 1"+getMobile});
					} catch (IOException e) {
						e.printStackTrace();
					}
		        }
		        
			}
		});
		btnMobile.setIcon(new ImageIcon(g_ViewSunoco.class.getResource("/telephone.png")));
		btnMobile.setToolTipText("Mobile Phone");
		btnMobile.setBounds(699, 228, 36, 36);
		frmSunocoContacts.getContentPane().add(btnMobile);
		btnEmail.setIcon(new ImageIcon(g_ViewSunoco.class.getResource("/email.png")));
		btnEmail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String test = result.get(list.getSelectedIndex()).getEmail();		
				try {
					c_Email.mailto(Arrays.asList(test)," ", " ");
				} catch (IOException e1) {
					e1.printStackTrace();
				} catch (URISyntaxException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnEmail.setToolTipText("Email");
		btnEmail.setBounds(699, 169, 36, 36);
		
		frmSunocoContacts.getContentPane().add(btnEmail);
		
		//Menubar
		JMenuBar menuBar = new JMenuBar();
		frmSunocoContacts.setJMenuBar(menuBar);
		
		
				
		JMenu mnSupport = new JMenu("Support");
		menuBar.add(mnSupport);
		
		JMenuItem mntmNewTicket = new JMenuItem("New Ticket");
		mntmNewTicket.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				g_MainMenu.checkVersion();
				if(c_CheckOpenTickets.CheckTickets())
				{							
					g_TicketEntry.run(frmSunocoContacts);
				}
				else
				{
					JOptionPane.showMessageDialog(frmSunocoContacts, "Open Ticket Limit Exceeded. \n Please Close Old Tickets.");
				}
				
			}
		});
		mnSupport.add(mntmNewTicket);
		
		JMenuItem mntmCurrentTickets = new JMenuItem("Current Tickets");
		mntmCurrentTickets.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				g_CurrentTickets.run(frmSunocoContacts);
			}
		});
		mnSupport.add(mntmCurrentTickets);
		
		JMenuItem mntmArchive = new JMenuItem("Archive");
		mntmArchive.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				g_ArchiveTickets.run(frmSunocoContacts);
			}
		});
		mnSupport.add(mntmArchive);
		
		JMenu mnSites = new JMenu("Sites");
		menuBar.add(mnSites);
		
		JMenuItem mntmButaneSites = new JMenuItem("Butane Sites");
		mntmButaneSites.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				g_ViewSites.run(frmSunocoContacts);
			}
		});
		mnSites.add(mntmButaneSites);
		
		JMenuItem mntmSiteChanges = new JMenuItem("Site Changes");
		mntmSiteChanges.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				g_SiteChanges.run(frmSunocoContacts);
			}
		});
		mnSites.add(mntmSiteChanges);
		
		JMenu mnContacts = new JMenu("Contacts");
		menuBar.add(mnContacts);
		
		JMenuItem mntmENEmployees = new JMenuItem("EN Employees");
		mntmENEmployees.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				g_ViewEmployees.run(frmSunocoContacts);
			}
		});
		mnContacts.add(mntmENEmployees);
		
		JMenuItem mntmEtpContacts = new JMenuItem("ETP Contacts");		
		mnContacts.add(mntmEtpContacts);
		mntmEtpContacts.setEnabled(false);
		
		JMenu mnTools = new JMenu("Tools");
		menuBar.add(mnTools);
		
		JMenuItem mntmCreateChecklist = new JMenuItem("Create Checklist");
		mntmCreateChecklist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				g_Tools_CreateChecklist.run(frmSunocoContacts);
			}
		});		
		mnTools.add(mntmCreateChecklist);

	}
	
	
	
	private void CancelEdits()
	{
		lblName.setEditable(false);
		lblCompany.setEditable(false);
		lblEmail.setEditable(false);		
		lblMobile.setEditable(false);
		
		btnEdit.setEnabled(true);
		btnEdit.setVisible(true);
		btnAccept.setEnabled(false);
		btnAccept.setVisible(false);
	}
	
	private void PopulateEmployees()
	{	
		
		String commandText = "SELECT Name, Company, Email, Mobile_Phone FROM Sunoco_Contacts ORDER BY Name asc";
		ResultSet rs = c_Query.ExecuteResultSet(commandText);
		try{

			while ((rs!=null) && (rs.next()))
			{
				  c_Employees sunoco = new c_Employees();
				  sunoco.setName(rs.getString(1));
				  sunoco.setTitle(rs.getString(2));
				  sunoco.setEmail(rs.getString(3));				  
				  sunoco.setMobile(rs.getString(4));				 
				  result.add(sunoco);
				  listModel.addElement(sunoco.getName());				  
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
		result.removeAll(result);
		listModel.removeAllElements();
		list.setModel(listModel);
		PopulateEmployees();
		list.setSelectedIndex(index);
	}
	
	private void AcceptEdits()
	{
		String originalName = result.get(list.getSelectedIndex()).getName();
		result.get(list.getSelectedIndex()).setSunocoContact(lblName.getText(), lblCompany.getText(), lblEmail.getText(), lblMobile.getText());
		String commandText = "Update Sunoco_Contacts SET Name = '" + lblName.getText() + "', Company = '" + lblCompany.getText() + "', Email = '" + lblEmail.getText() +   
							 "', Mobile_Phone = '" + lblMobile.getText() + "' WHERE Name = '" + originalName + "'";
		c_Query.ExecuteQuery(commandText);
		RefreshList();
	}
}
