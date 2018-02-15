import javax.swing.JFrame;
import java.awt.HeadlessException;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class g_NewSunocoContact {

	private JFrame frmNewSunocoContact;
	private JTextField txt_Name;
	private JTextField txt_Email;
	private JTextField txt_Company;
	private JTextField txt_Mobile;

	/**
	 * Launch the application.
	 */
	
			public static void run() {
				try {
					g_NewSunocoContact window = new g_NewSunocoContact();
					window.frmNewSunocoContact.setVisible(true);
					window.frmNewSunocoContact.setLocationRelativeTo( g_ViewSunoco.frmSunocoContacts );
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		
	

	/**
	 * Create the application.
	 */
	public g_NewSunocoContact() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmNewSunocoContact = new JFrame();
		frmNewSunocoContact.setIconImage(Toolkit.getDefaultToolkit().getImage(g_NewSunocoContact.class.getResource("/icon.png")));
		frmNewSunocoContact.setBounds(100, 100, 626, 421);
		frmNewSunocoContact.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmNewSunocoContact.getContentPane().setLayout(null);		
		frmNewSunocoContact.setTitle("Automated Support Program");
		
		
		JLabel lblAddNewSunocoContact = new JLabel("Add New Contact");
		lblAddNewSunocoContact.setBounds(228, 11, 189, 19);
		lblAddNewSunocoContact.setFont(new Font("Plantagenet Cherokee", Font.BOLD, 18));
		frmNewSunocoContact.getContentPane().add(lblAddNewSunocoContact);
		
		JLabel lblNamefirst = new JLabel("Name (First & Last)");
		lblNamefirst.setFont(new Font("Plantagenet Cherokee", Font.BOLD, 14));
		lblNamefirst.setBounds(10, 67, 179, 14);
		frmNewSunocoContact.getContentPane().add(lblNamefirst);
		
		txt_Name = new JTextField();
		txt_Name.setBounds(199, 58, 242, 35);
		frmNewSunocoContact.getContentPane().add(txt_Name);
		txt_Name.setColumns(10);
		
		JLabel lblEmailAddress = new JLabel("Email Address");
		lblEmailAddress.setFont(new Font("Plantagenet Cherokee", Font.BOLD, 14));
		lblEmailAddress.setBounds(10, 177, 179, 14);
		frmNewSunocoContact.getContentPane().add(lblEmailAddress);
		
		txt_Email = new JTextField();
		txt_Email.setColumns(10);
		txt_Email.setBounds(199, 168, 242, 35);
		frmNewSunocoContact.getContentPane().add(txt_Email);
		
		JLabel lblCompany = new JLabel("Company");
		lblCompany.setFont(new Font("Plantagenet Cherokee", Font.BOLD, 14));
		lblCompany.setBounds(10, 124, 179, 14);
		frmNewSunocoContact.getContentPane().add(lblCompany);
		
		txt_Company = new JTextField();
		txt_Company.setColumns(10);
		txt_Company.setBounds(199, 115, 242, 35);
		frmNewSunocoContact.getContentPane().add(txt_Company);
		
		JLabel lblMobilePhone = new JLabel("Mobile Phone");
		lblMobilePhone.setFont(new Font("Plantagenet Cherokee", Font.BOLD, 14));
		lblMobilePhone.setBounds(10, 232, 179, 14);
		frmNewSunocoContact.getContentPane().add(lblMobilePhone);
		
		txt_Mobile = new JTextField();
		txt_Mobile.setColumns(10);
		txt_Mobile.setBounds(199, 223, 242, 35);
		frmNewSunocoContact.getContentPane().add(txt_Mobile);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InsertEmployee();
			}
		});
		btnSubmit.setBounds(270, 284, 101, 35);
		frmNewSunocoContact.getContentPane().add(btnSubmit);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				g_ViewSunoco.run();
				frmNewSunocoContact.dispose();
			}
		});
		btnBack.setBounds(10, 355, 89, 23);
		frmNewSunocoContact.getContentPane().add(btnBack);
	}
	
	public void InsertEmployee(){
		
		String name = txt_Name.getText();
		String company = txt_Company.getText();
		String email = txt_Email.getText();		
		String mobile = txt_Mobile.getText();
		
		if (name.compareTo("") == 0 || company.compareTo("") == 0 || email.compareTo("") == 0)
		{
			JOptionPane.showMessageDialog(frmNewSunocoContact, "Name, Company, and Email are required fields.");
			return;
		}
		
		String commandText = "SELECT COUNT(*) as total From Sunoco_Contacts WHERE Name = '" + name + "'";
	
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
				JOptionPane.showMessageDialog(frmNewSunocoContact, "Contact " + name + " already exists in the database.");
				
			}
			else
			{
			
			commandText = "INSERT INTO Sunoco_Contacts(Name,Company,Email,Mobile_Phone) "
						+ "VALUES ( '" + name + "','" + company + "','" + email + "','" + mobile + "')";
				c_Query.UpdateResultSet(commandText);
				JOptionPane.showMessageDialog(frmNewSunocoContact, "Contact " + name + " was successfully added.");
				ClearFields();
				
				g_ViewSunoco.run();
				frmNewSunocoContact.dispose();
				
				
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
		txt_Name.setText("");
		txt_Email.setText("");
		txt_Company.setText("");
		txt_Mobile.setText("");
		txt_Company.setText("");
		
		
	}
}
