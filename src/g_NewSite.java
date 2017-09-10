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
import javax.swing.JCheckBox;
import javax.swing.SwingConstants;

public class g_NewSite {

	private JFrame frmNewEmployee;
	private JTextField txtClient;
	private JTextField txtSite;
	private JTextField txtSiteID;
	private JTextField txtView;
	private JTextField txtSiteAbbrv;
	private JTextField txtClientAbbrv;
	private JTextField txtHost;
	private JTextField txtDev;
	private JTextField txtSQL;
	private JTextField txtiDrac;
	private JTextField txtAddress;
	private JTextField txtPhone;
	private JTextField txtFieldTech;
	private JTextField txtFieldSupervisor;
	private JTextField txtGeneration;
	private JTextField txtHMI;
	private JCheckBox txtTwic;
	private JTextField txtState;
	private JTextField txtTimezone;

	/**
	 * Launch the application.
	 */
	
			public static void run() {
				try {
					g_NewSite window = new g_NewSite();
					window.frmNewEmployee.setVisible(true);
					window.frmNewEmployee.setLocationRelativeTo( g_ViewSites.frmButaneSites );
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		
	

	/**
	 * Create the application.
	 */
	public g_NewSite() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmNewEmployee = new JFrame();
		frmNewEmployee.setBounds(100, 100, 679, 661);
		frmNewEmployee.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmNewEmployee.getContentPane().setLayout(null);
		
		JLabel lblAddNewEmployee = new JLabel("Add New Site");
		lblAddNewEmployee.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddNewEmployee.setBounds(199, 11, 242, 19);
		lblAddNewEmployee.setFont(new Font("Plantagenet Cherokee", Font.BOLD, 18));
		frmNewEmployee.getContentPane().add(lblAddNewEmployee);
		
		JLabel lblNamefirst = new JLabel("Client");
		lblNamefirst.setFont(new Font("Plantagenet Cherokee", Font.BOLD, 14));
		lblNamefirst.setBounds(10, 67, 179, 14);
		frmNewEmployee.getContentPane().add(lblNamefirst);
		
		txtClient = new JTextField();
		txtClient.setBounds(199, 58, 242, 35);
		frmNewEmployee.getContentPane().add(txtClient);
		txtClient.setColumns(10);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InsertSite();
			}
		});
		btnSubmit.setBounds(269, 546, 101, 35);
		frmNewEmployee.getContentPane().add(btnSubmit);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				g_ViewSites.run();
				frmNewEmployee.dispose();
			}
		});
		btnBack.setBounds(10, 592, 89, 23);
		frmNewEmployee.getContentPane().add(btnBack);
		
		JLabel lblSite = new JLabel("Site");
		lblSite.setFont(new Font("Plantagenet Cherokee", Font.BOLD, 14));
		lblSite.setBounds(10, 113, 179, 14);
		frmNewEmployee.getContentPane().add(lblSite);
		
		txtSite = new JTextField();
		txtSite.setColumns(10);
		txtSite.setBounds(199, 104, 242, 35);
		frmNewEmployee.getContentPane().add(txtSite);
		
		JLabel lblSiteId = new JLabel("Site ID");
		lblSiteId.setFont(new Font("Plantagenet Cherokee", Font.BOLD, 14));
		lblSiteId.setBounds(10, 164, 179, 14);
		frmNewEmployee.getContentPane().add(lblSiteId);
		
		txtSiteID = new JTextField();
		txtSiteID.setColumns(10);
		txtSiteID.setBounds(199, 155, 242, 35);
		frmNewEmployee.getContentPane().add(txtSiteID);
		
		JLabel lblViewIp = new JLabel("View IP");
		lblViewIp.setFont(new Font("Plantagenet Cherokee", Font.BOLD, 14));
		lblViewIp.setBounds(502, 67, 75, 14);
		frmNewEmployee.getContentPane().add(lblViewIp);
		
		txtView = new JTextField();
		txtView.setColumns(10);
		txtView.setBounds(600, 58, 40, 35);
		frmNewEmployee.getContentPane().add(txtView);
		
		JLabel lblSiteAbbrv = new JLabel("Site Abbrv");
		lblSiteAbbrv.setFont(new Font("Plantagenet Cherokee", Font.BOLD, 14));
		lblSiteAbbrv.setBounds(10, 256, 179, 14);
		frmNewEmployee.getContentPane().add(lblSiteAbbrv);
		
		txtSiteAbbrv = new JTextField();
		txtSiteAbbrv.setColumns(10);
		txtSiteAbbrv.setBounds(199, 247, 242, 35);
		frmNewEmployee.getContentPane().add(txtSiteAbbrv);
		
		JLabel lblClientAbbrv = new JLabel("Client Abbrv");
		lblClientAbbrv.setFont(new Font("Plantagenet Cherokee", Font.BOLD, 14));
		lblClientAbbrv.setBounds(10, 210, 179, 14);
		frmNewEmployee.getContentPane().add(lblClientAbbrv);
		
		txtClientAbbrv = new JTextField();
		txtClientAbbrv.setColumns(10);
		txtClientAbbrv.setBounds(199, 201, 242, 35);
		frmNewEmployee.getContentPane().add(txtClientAbbrv);
		
		JLabel lblHostIp = new JLabel("Host IP");
		lblHostIp.setFont(new Font("Plantagenet Cherokee", Font.BOLD, 14));
		lblHostIp.setBounds(502, 212, 75, 14);
		frmNewEmployee.getContentPane().add(lblHostIp);
		
		txtHost = new JTextField();
		txtHost.setColumns(10);
		txtHost.setBounds(600, 201, 40, 35);
		frmNewEmployee.getContentPane().add(txtHost);
		
		JLabel lblDevIp = new JLabel("Dev IP");
		lblDevIp.setFont(new Font("Plantagenet Cherokee", Font.BOLD, 14));
		lblDevIp.setBounds(502, 161, 75, 14);
		frmNewEmployee.getContentPane().add(lblDevIp);
		
		txtDev = new JTextField();
		txtDev.setColumns(10);
		txtDev.setBounds(600, 155, 40, 35);
		frmNewEmployee.getContentPane().add(txtDev);
		
		JLabel lblSqlIp = new JLabel("SQL IP");
		lblSqlIp.setFont(new Font("Plantagenet Cherokee", Font.BOLD, 14));
		lblSqlIp.setBounds(502, 113, 75, 14);
		frmNewEmployee.getContentPane().add(lblSqlIp);
		
		txtSQL = new JTextField();
		txtSQL.setColumns(10);
		txtSQL.setBounds(600, 104, 40, 35);
		frmNewEmployee.getContentPane().add(txtSQL);
		
		JLabel lblIdracIp = new JLabel("iDrac IP");
		lblIdracIp.setFont(new Font("Plantagenet Cherokee", Font.BOLD, 14));
		lblIdracIp.setBounds(502, 256, 75, 14);
		frmNewEmployee.getContentPane().add(lblIdracIp);
		
		txtiDrac = new JTextField();
		txtiDrac.setColumns(10);
		txtiDrac.setBounds(600, 247, 40, 35);
		frmNewEmployee.getContentPane().add(txtiDrac);
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setFont(new Font("Plantagenet Cherokee", Font.BOLD, 14));
		lblAddress.setBounds(10, 311, 179, 14);
		frmNewEmployee.getContentPane().add(lblAddress);
		
		txtAddress = new JTextField();
		txtAddress.setColumns(10);
		txtAddress.setBounds(199, 302, 242, 35);
		frmNewEmployee.getContentPane().add(txtAddress);
		
		JLabel lblPhone = new JLabel("Phone");
		lblPhone.setFont(new Font("Plantagenet Cherokee", Font.BOLD, 14));
		lblPhone.setBounds(10, 407, 179, 14);
		frmNewEmployee.getContentPane().add(lblPhone);
		
		txtPhone = new JTextField();
		txtPhone.setColumns(10);
		txtPhone.setBounds(199, 398, 242, 35);
		frmNewEmployee.getContentPane().add(txtPhone);
		
		JLabel lblFieldTech = new JLabel("Field Tech");
		lblFieldTech.setFont(new Font("Plantagenet Cherokee", Font.BOLD, 14));
		lblFieldTech.setBounds(10, 453, 179, 14);
		frmNewEmployee.getContentPane().add(lblFieldTech);
		
		txtFieldTech = new JTextField();
		txtFieldTech.setColumns(10);
		txtFieldTech.setBounds(199, 444, 242, 35);
		frmNewEmployee.getContentPane().add(txtFieldTech);
		
		JLabel lblFieldSupervisor = new JLabel("Field Supervisor");
		lblFieldSupervisor.setFont(new Font("Plantagenet Cherokee", Font.BOLD, 14));
		lblFieldSupervisor.setBounds(10, 496, 179, 14);
		frmNewEmployee.getContentPane().add(lblFieldSupervisor);
		
		txtFieldSupervisor = new JTextField();
		txtFieldSupervisor.setColumns(10);
		txtFieldSupervisor.setBounds(199, 487, 242, 35);
		frmNewEmployee.getContentPane().add(txtFieldSupervisor);
		
		JLabel lblGeneration = new JLabel("Generation");
		lblGeneration.setFont(new Font("Plantagenet Cherokee", Font.BOLD, 14));
		lblGeneration.setBounds(502, 311, 88, 14);
		frmNewEmployee.getContentPane().add(lblGeneration);
		
		txtGeneration = new JTextField();
		txtGeneration.setColumns(10);
		txtGeneration.setBounds(600, 302, 40, 35);
		frmNewEmployee.getContentPane().add(txtGeneration);
		
		JLabel lblHmi = new JLabel("HMI");
		lblHmi.setFont(new Font("Plantagenet Cherokee", Font.BOLD, 14));
		lblHmi.setBounds(502, 359, 75, 14);
		frmNewEmployee.getContentPane().add(lblHmi);
		
		txtHMI = new JTextField();
		txtHMI.setColumns(10);
		txtHMI.setBounds(600, 350, 40, 35);
		frmNewEmployee.getContentPane().add(txtHMI);
		
		JLabel lblTwic = new JLabel("TWIC");
		lblTwic.setFont(new Font("Plantagenet Cherokee", Font.BOLD, 14));
		lblTwic.setBounds(502, 450, 75, 14);
		frmNewEmployee.getContentPane().add(lblTwic);
		
		txtTwic = new JCheckBox("\r\n");
		txtTwic.setBounds(602, 444, 38, 23);
		frmNewEmployee.getContentPane().add(txtTwic);
		
		JLabel lblState = new JLabel("State");
		lblState.setFont(new Font("Plantagenet Cherokee", Font.BOLD, 14));
		lblState.setBounds(10, 357, 179, 14);
		frmNewEmployee.getContentPane().add(lblState);
		
		txtState = new JTextField();
		txtState.setColumns(10);
		txtState.setBounds(199, 348, 242, 35);
		frmNewEmployee.getContentPane().add(txtState);
		
		JLabel lblTimezone = new JLabel("Timezone");
		lblTimezone.setFont(new Font("Plantagenet Cherokee", Font.BOLD, 14));
		lblTimezone.setBounds(502, 407, 75, 14);
		frmNewEmployee.getContentPane().add(lblTimezone);
		
		txtTimezone = new JTextField();
		txtTimezone.setColumns(10);
		txtTimezone.setBounds(600, 398, 40, 35);
		frmNewEmployee.getContentPane().add(txtTimezone);
	}
	
	public void InsertSite(){
		
		String Client = txtClient.getText();
		String Site = txtSite.getText();
		String SiteID = txtSiteID.getText();
		String ClientAbbrv = txtClientAbbrv.getText();
		String SiteAbbrv = txtSiteAbbrv.getText();
		String iDrac = txtiDrac.getText();
		String State = txtState.getText();
		String Host = txtHost.getText();
		String View = txtView.getText();
		String SQL = txtSQL.getText();
		String Dev = txtDev.getText();
		String Generation = txtGeneration.getText();
		String HMI = txtHMI.getText();
		String Address = txtAddress.getText();
		String Phone = txtPhone.getText();
		String FieldTech = txtFieldTech.getText();
		String FieldSupervisor = txtFieldSupervisor.getText();
		String Timezone = txtTimezone.getText();
		boolean TWIC = false;
		if(txtTwic.isSelected())
		{
			TWIC = true;
		}
		
		
		if (Client.compareTo("") == 0 || Site.compareTo("") == 0 || SiteID.compareTo("") == 0)
		{
			JOptionPane.showMessageDialog(null, "Client, Site, and SiteID are required fields.");
			return;
		}
		
		String commandText = "SELECT COUNT(*) as total From Sites WHERE SiteID = " + SiteID + "";
	
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
				JOptionPane.showMessageDialog(null, "Site ID " + SiteID + " already exists in the database.");
				
			}
			else
			{
			
			commandText = "INSERT INTO Sites(Client,Site,State,SiteID,ClientAbbrv,SiteAbbrv,iDracIP,HostIP,ViewIP,SQLIP,DevIP,Generation,HMI,Address,Phone,Field_Tech,Field_Supervisor,Twic,Timezone) "
						+ "VALUES ( '" + Client + "','" + Site + "','" + State + "','" + SiteID + "','" + ClientAbbrv + "','" + SiteAbbrv + "','" + iDrac + "','" + Host + "','" + View + "','" + SQL + "','" + 
						 Dev + "','" + Generation + "','" + HMI + "','" + Address + "','" + Phone + "','" + FieldTech + "','" + FieldSupervisor + "','" + TWIC + "','" + Timezone + "')";
			System.out.println(commandText);	
			c_Query.UpdateResultSet(commandText);
				JOptionPane.showMessageDialog(null, "Site " + Client + " " + Site + " was successfully added.");
				ClearFields();
				
				g_ViewSites.run();
				frmNewEmployee.dispose();
				
				
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
		txtClient.setText("");
		txtSite.setText("");
		txtSiteID.setText("");
		txtClientAbbrv.setText("");
		txtSiteAbbrv.setText("");
		txtiDrac.setText("");
		txtState.setText("");
		txtHost.setText("");
		txtView.setText("");
		txtSQL.setText("");
		txtDev.setText("");
		txtGeneration.setText("");
		txtHMI.setText("");
		txtAddress.setText("");
		txtPhone.setText("");
		txtFieldTech.setText("");
		txtFieldSupervisor.setText("");
		txtTimezone.setText("");
		txtTwic.setSelected(false);
		
		
	}
}