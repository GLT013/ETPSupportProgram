import javax.swing.JFrame;
import java.awt.HeadlessException;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class g_NewEmployee {

	private JFrame frmNewEmployee;
	private JTextField txt_Name;
	private JTextField txt_Email;
	private JTextField txt_Phone;
	private JTextField txt_Mobile;
	private JComboBox<String> cb_Title;

	/**
	 * Launch the application.
	 */
	
			public static void run(JFrame frame) {
				try {
					g_NewEmployee window = new g_NewEmployee();
					window.frmNewEmployee.setVisible(true);
					window.frmNewEmployee.setLocationRelativeTo(frame);
					frame.dispose();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
	/**
	 * Create the application.
	 */
	public g_NewEmployee() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmNewEmployee = new JFrame();
		frmNewEmployee.setResizable(false);
		frmNewEmployee.setIconImage(Toolkit.getDefaultToolkit().getImage(g_NewEmployee.class.getResource("/icon.png")));
		frmNewEmployee.setBounds(100, 100, 545, 507);
		frmNewEmployee.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmNewEmployee.getContentPane().setLayout(null);		
		frmNewEmployee.setTitle("Automated Support Program v." + g_MainMenu.version);
				
		JLabel lblAddNewEmployee = new JLabel("Add New Employee");
		lblAddNewEmployee.setBounds(214, 11, 189, 19);
		lblAddNewEmployee.setFont(new Font("Rockwell", Font.BOLD, 18));
		frmNewEmployee.getContentPane().add(lblAddNewEmployee);
		
		JLabel lblNamefirst = new JLabel("Name (First & Last)");
		lblNamefirst.setFont(new Font("Rockwell", Font.BOLD, 14));
		lblNamefirst.setBounds(10, 67, 179, 14);
		frmNewEmployee.getContentPane().add(lblNamefirst);
		
		txt_Name = new JTextField();
		txt_Name.setFont(new Font("Rockwell", Font.PLAIN, 12));
		txt_Name.setBounds(199, 58, 242, 35);
		frmNewEmployee.getContentPane().add(txt_Name);
		txt_Name.setColumns(10);
		
		JLabel lblTitle = new JLabel("Title");
		lblTitle.setFont(new Font("Rockwell", Font.BOLD, 14));
		lblTitle.setBounds(10, 117, 179, 14);
		frmNewEmployee.getContentPane().add(lblTitle);
		
		JLabel lblEmailAddress = new JLabel("Email Address");
		lblEmailAddress.setFont(new Font("Rockwell", Font.BOLD, 14));
		lblEmailAddress.setBounds(10, 177, 179, 14);
		frmNewEmployee.getContentPane().add(lblEmailAddress);
		
		txt_Email = new JTextField();
		txt_Email.setFont(new Font("Rockwell", Font.PLAIN, 12));
		txt_Email.setColumns(10);
		txt_Email.setBounds(199, 168, 242, 35);
		frmNewEmployee.getContentPane().add(txt_Email);
		
		JLabel lblOfficePhone = new JLabel("Office Phone");
		lblOfficePhone.setFont(new Font("Rockwell", Font.BOLD, 14));
		lblOfficePhone.setBounds(10, 234, 179, 14);
		frmNewEmployee.getContentPane().add(lblOfficePhone);
		
		txt_Phone = new JTextField();
		txt_Phone.setFont(new Font("Rockwell", Font.PLAIN, 12));
		txt_Phone.setColumns(10);
		txt_Phone.setBounds(199, 225, 242, 35);
		frmNewEmployee.getContentPane().add(txt_Phone);
		
		JLabel lblMobilePhone = new JLabel("Mobile Phone");
		lblMobilePhone.setFont(new Font("Rockwell", Font.BOLD, 14));
		lblMobilePhone.setBounds(10, 294, 179, 14);
		frmNewEmployee.getContentPane().add(lblMobilePhone);
		
		txt_Mobile = new JTextField();
		txt_Mobile.setFont(new Font("Rockwell", Font.PLAIN, 12));
		txt_Mobile.setColumns(10);
		txt_Mobile.setBounds(199, 285, 242, 35);
		frmNewEmployee.getContentPane().add(txt_Mobile);
		
		cb_Title = new JComboBox<String>();
		cb_Title.setFont(new Font("Rockwell", Font.PLAIN, 12));
		cb_Title.setModel(new DefaultComboBoxModel<String>(new String[] {"Design Engineer", "Design Specialist", "Sr. Design Engineer", "Sr. Design Specialist", "Project Engineer", "Project Specialist", "Sr. Project Engineer", "Sr. Project Specialist", "Project Manager", "Sr. Project Manager"}));
		cb_Title.setBounds(199, 108, 242, 35);
		frmNewEmployee.getContentPane().add(cb_Title);
		cb_Title.setSelectedIndex(-1);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setFont(new Font("Rockwell", Font.PLAIN, 12));
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InsertEmployee();
			}
		});
		btnSubmit.setBounds(261, 349, 101, 35);
		frmNewEmployee.getContentPane().add(btnSubmit);
		
		JButton btnBack = new JButton("Back");
		btnBack.setFont(new Font("Rockwell", Font.PLAIN, 12));
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				g_ViewEmployees.run(frmNewEmployee);
			}
		});
		btnBack.setBounds(10, 403, 89, 23);
		frmNewEmployee.getContentPane().add(btnBack);
		
		//Menubar
		JMenuBar menuBar = new JMenuBar();
		frmNewEmployee.setJMenuBar(menuBar);

		JMenu mnSupport = new JMenu("Support");
		menuBar.add(mnSupport);
		
		JMenuItem mntmNewTicket = new JMenuItem("New Ticket");
		mntmNewTicket.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				g_MainMenu.checkVersion();
				if(c_CheckOpenTickets.CheckTickets())
				{							
					g_TicketEntry.run(frmNewEmployee);
				}
				else
				{
					JOptionPane.showMessageDialog(frmNewEmployee, "Open Ticket Limit Exceeded. \n Please Close Old Tickets.");
				}
				
			}
		});
		mnSupport.add(mntmNewTicket);
		
		JMenuItem mntmCurrentTickets = new JMenuItem("Current Tickets");
		mntmCurrentTickets.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				g_CurrentTickets.run(frmNewEmployee);
			}
		});
		mnSupport.add(mntmCurrentTickets);
		
		JMenuItem mntmArchive = new JMenuItem("Archive");
		mntmArchive.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				g_ArchiveTickets.run(frmNewEmployee);
			}
		});
		mnSupport.add(mntmArchive);
		
		JMenu mnSites = new JMenu("Sites");
		menuBar.add(mnSites);
		
		JMenuItem mntmButaneSites = new JMenuItem("Butane Sites");
		mntmButaneSites.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				g_ViewSites.run(frmNewEmployee);
			}
		});
		mnSites.add(mntmButaneSites);
		
		JMenuItem mntmSiteChanges = new JMenuItem("Site Changes");
		mntmSiteChanges.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				g_SiteChanges.run(frmNewEmployee);
			}
		});
		mnSites.add(mntmSiteChanges);
		
		JMenu mnContacts = new JMenu("Contacts");
		menuBar.add(mnContacts);
		
		JMenuItem mntmENEmployees = new JMenuItem("EN Employees");
		mntmENEmployees.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				g_ViewEmployees.run(frmNewEmployee);
			}
		});
		mnContacts.add(mntmENEmployees);
		
		JMenuItem mntmEtpContacts = new JMenuItem("ETP Contacts");
		mntmEtpContacts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				g_ViewSunoco.run(frmNewEmployee);
			}
		});
		mnContacts.add(mntmEtpContacts);
		
		JMenu mnTools = new JMenu("Tools");
		menuBar.add(mnTools);
		
		JMenuItem mntmCreateChecklist = new JMenuItem("Create Checklist");
		mntmCreateChecklist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				g_Tools_CreateChecklist.run(frmNewEmployee);
			}
		});		
		mnTools.add(mntmCreateChecklist);

	}
	
	public void InsertEmployee(){
		
		String name = txt_Name.getText();
		String  title = cb_Title.getSelectedItem().toString();
		String email = txt_Email.getText();
		String phone = txt_Phone.getText();
		String mobile = txt_Mobile.getText();
		
		if (name.compareTo("") == 0 || title.compareTo("") == 0 || email.compareTo("") == 0)
		{
			JOptionPane.showMessageDialog(frmNewEmployee, "Name, Title, and Email are required fields.");
			return;
		}
		
		String commandText = "SELECT COUNT(*) as total From EN_Employees WHERE Name = '" + name + "'";
	
		ResultSet rs = c_Query.ExecuteResultSet(commandText);
		try {
			rs.next();			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			if(rs.getInt("total") > 0)
			{
			//employee already exists.
				JOptionPane.showMessageDialog(frmNewEmployee, "Employee " + name + " already exists in the database.");
				
			}
			else
			{
			
			commandText = "INSERT INTO EN_Employees(Name,Title,Email,Office_Phone, Mobile_Phone, Active) "
						+ "VALUES ( '" + name + "','" + title + "','" + email + "','" + phone + "','" + mobile + "', 'True')";
				c_Query.UpdateResultSet(commandText);
				JOptionPane.showMessageDialog(frmNewEmployee, "Employee " + name + " was successfully added.");
				ClearFields();
				
				g_ViewEmployees.run(frmNewEmployee);								
			}
		} catch (HeadlessException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}	

	}
	
	public void ClearFields()
	{
		txt_Name.setText("");
		txt_Email.setText("");
		txt_Phone.setText("");
		txt_Mobile.setText("");
		cb_Title.setSelectedIndex(-1);
	}
}
