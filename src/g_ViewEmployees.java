import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
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

public class g_ViewEmployees {

	static JFrame frmViewEmployees;
	private static JList<String> list = new JList<String>();
	private static DefaultListModel<String> listModel = new DefaultListModel<String>();
	private JScrollPane scrollPane;
	JTextField lblTitle = new JTextField("");
	JTextField lblEmail = new JTextField("");
	JTextField lblOffice = new JTextField("");
	JTextField lblMobile = new JTextField("");
	private final JButton btnEdit = new JButton("");
	private JTextField lblName;
	private final JButton btnAccept = new JButton("");
	private final JButton btnCancel = new JButton("");
	final ArrayList <c_Employees> result = new ArrayList<c_Employees>();
	private final JButton btnNewEmployee = new JButton("");
	private final JButton btnNewButton = new JButton("Back");
	private final JButton button = new JButton("");
	

	/**
	 * Launch the application.
	 */
	
			public static void run() {
				try {
					g_ViewEmployees window = new g_ViewEmployees();
					window.frmViewEmployees.setVisible(true);
					window.frmViewEmployees.setLocationRelativeTo( g_MainMenu.frmMainMenu );
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
	

	/**
	 * Create the application.
	 */
	public g_ViewEmployees() {
		initialize();
		PopulateEmployees();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmViewEmployees = new JFrame();
		frmViewEmployees.setIconImage(Toolkit.getDefaultToolkit().getImage(g_ViewEmployees.class.getResource("/icon.png")));
		frmViewEmployees.setBounds(100, 100, 909, 441);
		frmViewEmployees.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmViewEmployees.getContentPane().setLayout(null);
		if(g_MainMenu.offlineMode)
		{
			frmViewEmployees.setTitle("Automated Support Program - OFFLINE");	
		}
		else
		{
			frmViewEmployees.setTitle("Automated Support Program");
		}
		
		frmViewEmployees.addWindowListener(new java.awt.event.WindowAdapter() {
			 @Override
			    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				 listModel.removeAllElements();
				 list.setModel(listModel);
				 frmViewEmployees.dispose(); 
			 }
		
		});
		scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		scrollPane.setBounds(36, 40, 194, 241);
		frmViewEmployees.getContentPane().add(scrollPane);
		
		JLabel lblNewLabel = new JLabel("Name:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(309, 44, 113, 14);
		frmViewEmployees.getContentPane().add(lblNewLabel);
		
		JLabel lblTitle2 = new JLabel("Title:");
		lblTitle2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTitle2.setBounds(309, 99, 113, 14);
		frmViewEmployees.getContentPane().add(lblTitle2);
		
		JLabel lblEmail2 = new JLabel("Email:");
		lblEmail2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblEmail2.setBounds(309, 143, 113, 14);
		frmViewEmployees.getContentPane().add(lblEmail2);
		
		JLabel lblOfficePhone2 = new JLabel("Office Phone:");
		lblOfficePhone2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblOfficePhone2.setBounds(309, 199, 113, 14);
		frmViewEmployees.getContentPane().add(lblOfficePhone2);
		
		JLabel lblMobilePhone2 = new JLabel("Mobile Phone:");
		lblMobilePhone2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblMobilePhone2.setBounds(309, 251, 113, 14);
		frmViewEmployees.getContentPane().add(lblMobilePhone2);
		lblTitle.setEditable(false);
		
		
		lblTitle.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblTitle.setBounds(442, 99, 310, 30);
		frmViewEmployees.getContentPane().add(lblTitle);
		lblEmail.setEditable(false);
		
		
		lblEmail.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblEmail.setBounds(442, 143, 310, 30);
		frmViewEmployees.getContentPane().add(lblEmail);
		lblOffice.setEditable(false);
		
		
		lblOffice.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblOffice.setBounds(442, 199, 310, 30);
		frmViewEmployees.getContentPane().add(lblOffice);
		
		lblName = new JTextField();
		lblName.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblName.setBackground(UIManager.getColor("Button.background"));
		lblName.setEditable(false);
		lblName.setBounds(442, 40, 310, 31);		
		frmViewEmployees.getContentPane().add(lblName);
		lblName.setColumns(10);
		lblMobile.setEditable(false);
		
		lblMobile.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblMobile.setBounds(442, 251, 310, 30);
		frmViewEmployees.getContentPane().add(lblMobile);
		btnEdit.setToolTipText("Edit");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {	
				if(g_MainMenu.offlineMode)
				{					
					String err = c_EasterEggs.EasterEggs();					
					JOptionPane.showMessageDialog(frmViewEmployees, err);	
					return;
				}
				{
					lblName.setEditable(true);
					lblTitle.setEditable(true);
					lblEmail.setEditable(true);
					lblOffice.setEditable(true);
					lblMobile.setEditable(true);								
					btnEdit.setEnabled(false);
					btnEdit.setVisible(false);
					btnCancel.setVisible(true);
					btnCancel.setEnabled(true);
					btnAccept.setVisible(true);
					btnAccept.setEnabled(true);
				}
			}
		});     
		btnEdit.setIcon(new ImageIcon(g_ViewEmployees.class.getResource("/edit.png")));
		btnEdit.setBounds(828, 11, 32, 32);
		
		frmViewEmployees.getContentPane().add(btnEdit);
		btnAccept.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AcceptEdits();
			}
		});
		btnAccept.setToolTipText("Accept Edits");
		btnAccept.setEnabled(false);
		btnAccept.setVisible(false);
		btnAccept.setIcon(new ImageIcon(g_ViewEmployees.class.getResource("/ok-icon.png")));
		btnAccept.setBounds(786, 11, 34, 34);
		
		frmViewEmployees.getContentPane().add(btnAccept);
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CancelEdits();
			}
		});
		btnCancel.setToolTipText("Cancel Edits");
		btnCancel.setEnabled(false);
		btnCancel.setIcon(new ImageIcon(g_ViewEmployees.class.getResource("/Actions-window-close-icon.png")));
		btnCancel.setBounds(828, 11, 32, 32);
		
		frmViewEmployees.getContentPane().add(btnCancel);
		
		JLabel lblEneEmployees = new JLabel("ENE Employees");
		lblEneEmployees.setForeground(Color.RED);
		lblEneEmployees.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblEneEmployees.setBounds(39, 15, 187, 18);
		frmViewEmployees.getContentPane().add(lblEneEmployees);
		
		JButton btnRemoveEmployee = new JButton("");
		btnRemoveEmployee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(g_MainMenu.offlineMode)
				{
					String err = c_EasterEggs.EasterEggs();					
					JOptionPane.showMessageDialog(frmViewEmployees, err);					
					return;
				}
				{
					int index = list.getSelectedIndex();
					String removalName = result.get(index).getName();				
					int reply = JOptionPane.showConfirmDialog(frmViewEmployees, "Really delete " + removalName + "?" , "Remove Employee", JOptionPane.YES_NO_OPTION);
			        if (reply == JOptionPane.YES_OPTION)
			        {
			          String commandText = "DELETE FROM EN_Employees WHERE Name = '" + removalName + "'";
			          c_Query.ExecuteQuery(commandText);
			          result.remove(index);
			          JOptionPane.showMessageDialog(frmViewEmployees, removalName + " has been deleted. \n You monster.");
			          RefreshList();
			        }
			        else
			        {
			        	//do nothing.
			        }
				}
			}
		});
		btnRemoveEmployee.setIcon(new ImageIcon(g_ViewEmployees.class.getResource("/Minus-48.png")));
		btnRemoveEmployee.setToolTipText("Remove Employee");
		btnRemoveEmployee.setBounds(198, 292, 32, 32);
		frmViewEmployees.getContentPane().add(btnRemoveEmployee);
		btnNewEmployee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(g_MainMenu.offlineMode)
				{
					String err = c_EasterEggs.EasterEggs();					
					JOptionPane.showMessageDialog(frmViewEmployees, err);
					
					return;
				}
				{
					g_NewEmployee.run();
					frmViewEmployees.dispose();
				}
			}
		});
		btnNewEmployee.setIcon(new ImageIcon(g_ViewEmployees.class.getResource("/Plus-48.png")));
		btnNewEmployee.setToolTipText("New Employee");
		btnNewEmployee.setBounds(36, 292, 32, 32);
		
		frmViewEmployees.getContentPane().add(btnNewEmployee);
		
		
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				try{
					if(!arg0.getValueIsAdjusting())
					{
						CancelEdits();
						lblName.setText(result.get(list.getSelectedIndex()).getName());
						lblTitle.setText(result.get(list.getSelectedIndex()).getTitle());
						lblEmail.setText(result.get(list.getSelectedIndex()).getEmail());
						lblOffice.setText(result.get(list.getSelectedIndex()).getOffice());
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
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				g_MainMenu.run(frmViewEmployees);
				frmViewEmployees.dispose();
			}
		});
		btnNewButton.setBounds(10, 360, 83, 32);
		
		frmViewEmployees.getContentPane().add(btnNewButton);
		
		JButton btnMobilePhone = new JButton("");
		btnMobilePhone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String getMobile = result.get(list.getSelectedIndex()).getMobile();				
				String getEmployee = result.get(list.getSelectedIndex()).getName();
				int reply = JOptionPane.showConfirmDialog(frmViewEmployees, "Call " + getEmployee + " Mobile?" , getEmployee + " Mobile Phone #", JOptionPane.YES_NO_OPTION);
		        if (reply == JOptionPane.YES_OPTION)
		        {
		        	try {

						Runtime.getRuntime().exec(new String[] {"C:\\Program Files (x86)\\Microsoft Office\\Office16\\lync.exe", "/C", "Callto:tel:+ 1"+getMobile});
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		        }
		        
			}
		});
		btnMobilePhone.setIcon(new ImageIcon(g_ViewEmployees.class.getResource("/telephone.png")));
		btnMobilePhone.setToolTipText("Mobile Phone");
		btnMobilePhone.setBounds(765, 247, 36, 36);
		frmViewEmployees.getContentPane().add(btnMobilePhone);
		
		JButton button_1 = new JButton("");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String getOffice = result.get(list.getSelectedIndex()).getOffice();				
				String getEmployee = result.get(list.getSelectedIndex()).getName();
				int reply = JOptionPane.showConfirmDialog(frmViewEmployees, "Call " + getEmployee + " Office Phone?" , getEmployee + " Office Phone #", JOptionPane.YES_NO_OPTION);
		        if (reply == JOptionPane.YES_OPTION)
		        {
		        	try {

						Runtime.getRuntime().exec(new String[] {"C:\\Program Files (x86)\\Microsoft Office\\Office16\\lync.exe", "/C", "Callto:tel:+ 1"+getOffice});
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		        }
		        
			}
			
		});
		button_1.setIcon(new ImageIcon(g_ViewEmployees.class.getResource("/telephone.png")));
		button_1.setToolTipText("Office Phone");
		button_1.setBounds(762, 196, 36, 36);
		frmViewEmployees.getContentPane().add(button_1);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String test = result.get(list.getSelectedIndex()).getEmail();		
				try {
					c_Email.mailto(Arrays.asList(test)," ", " ");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (URISyntaxException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		button.setIcon(new ImageIcon(g_ViewEmployees.class.getResource("/email.png")));
		button.setToolTipText("Email");
		button.setBounds(762, 141, 36, 36);
		
		frmViewEmployees.getContentPane().add(button);
	}
	
	private void CancelEdits()
	{
		lblName.setEditable(false);
		lblTitle.setEditable(false);
		lblEmail.setEditable(false);
		lblOffice.setEditable(false);
		lblMobile.setEditable(false);
		
		btnEdit.setEnabled(true);
		btnEdit.setVisible(true);
		btnAccept.setEnabled(false);
		btnAccept.setVisible(false);
	}
	
	private void PopulateEmployees()
	{	
		String commandText = "SELECT Name, Title, Email, Office_Phone, Mobile_Phone FROM EN_Employees WHERE Active = 1 ORDER BY Name asc";
		
		ResultSet rs = c_Query.ExecuteResultSet(commandText);
		try{

			while ((rs!=null) && (rs.next()))
			{
				  c_Employees employee = new c_Employees();
				  employee.setName(rs.getString(1));
				  employee.setTitle(rs.getString(2));
				  employee.setEmail(rs.getString(3));
				  employee.setOffice(rs.getString(4));
				  employee.setMobile(rs.getString(5));				 
				  result.add(employee);
				  listModel.addElement(employee.getName());				  
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
		listModel.removeAllElements();
		list.setModel(listModel);
		PopulateEmployees();
		list.setSelectedIndex(index);
	}
	
	private void AcceptEdits()
	{
		String originalName = result.get(list.getSelectedIndex()).getName();
		result.get(list.getSelectedIndex()).setEmployee(lblName.getText(), lblTitle.getText(), lblEmail.getText(), lblOffice.getText(), lblMobile.getText());
		String commandText = "Update EN_Employees SET Name = '" + lblName.getText() + "', Title = '" + lblTitle.getText() + "', Email = '" + lblEmail.getText() + "', Office_Phone = '" + lblOffice.getText() 
							 + "', Mobile_Phone = '" + lblMobile.getText() + "' WHERE Name = '" + originalName + "'";
		c_Query.ExecuteQuery(commandText);		
		result.set(list.getSelectedIndex(), result.get(list.getSelectedIndex()));
		RefreshList();
	}
}
