import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
import java.net.URI;
import java.net.URISyntaxException;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Toolkit;

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
	
			public static void run() {
				try {
					g_ViewSunoco window = new g_ViewSunoco();
					window.frmSunocoContacts.setVisible(true);
					window.frmSunocoContacts.setLocationRelativeTo( g_MainMenu.frmMainMenu );
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
		frmSunocoContacts.setIconImage(Toolkit.getDefaultToolkit().getImage(g_ViewSunoco.class.getResource("/icon.png")));
		frmSunocoContacts.setBounds(100, 100, 909, 441);
		frmSunocoContacts.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frmSunocoContacts.getContentPane().setLayout(null);
		if(g_MainMenu.offlineMode)
		{
			frmSunocoContacts.setTitle("Automated Support Program - OFFLINE");	
		}
		else
		{
			frmSunocoContacts.setTitle("Automated Support Program");
		}
		
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
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(309, 44, 113, 14);
		frmSunocoContacts.getContentPane().add(lblNewLabel);
		
		JLabel lblTitle2 = new JLabel("Company");
		lblTitle2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTitle2.setBounds(309, 104, 113, 25);
		frmSunocoContacts.getContentPane().add(lblTitle2);
		
		JLabel lblEmail2 = new JLabel("Email:");
		lblEmail2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblEmail2.setBounds(309, 163, 113, 14);
		frmSunocoContacts.getContentPane().add(lblEmail2);
		
		JLabel lblMobilePhone2 = new JLabel("Mobile Phone:");
		lblMobilePhone2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblMobilePhone2.setBounds(309, 222, 113, 14);
		frmSunocoContacts.getContentPane().add(lblMobilePhone2);
		lblCompany.setEditable(false);
		
		
		lblCompany.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblCompany.setBounds(442, 99, 310, 30);
		frmSunocoContacts.getContentPane().add(lblCompany);
		lblEmail.setEditable(false);
		
		
		lblEmail.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblEmail.setBounds(442, 158, 310, 30);
		frmSunocoContacts.getContentPane().add(lblEmail);
		
		lblName = new JTextField();
		lblName.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblName.setBackground(UIManager.getColor("Button.background"));
		lblName.setEditable(false);
		lblName.setBounds(442, 40, 310, 31);		
		frmSunocoContacts.getContentPane().add(lblName);
		lblName.setColumns(10);
		lblMobile.setEditable(false);
		
		lblMobile.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblMobile.setBounds(442, 217, 310, 30);
		frmSunocoContacts.getContentPane().add(lblMobile);
		btnEdit.setToolTipText("Edit");	
		
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {		
				if(g_MainMenu.offlineMode)
				{
					String err = c_EasterEggs.EasterEggs();					
					JOptionPane.showMessageDialog(frmSunocoContacts, err);
					return;
				}
				else
				{
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
			}
		});   
		
		btnEdit.setIcon(new ImageIcon(g_ViewSunoco.class.getResource("/edit.png")));
		btnEdit.setBounds(828, 11, 32, 32);
		
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
		btnAccept.setBounds(786, 11, 34, 34);
		
		frmSunocoContacts.getContentPane().add(btnAccept);
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CancelEdits();
			}
		});
		btnCancel.setToolTipText("Cancel Edits");
		btnCancel.setEnabled(false);
		btnCancel.setIcon(new ImageIcon(g_ViewSunoco.class.getResource("/Actions-window-close-icon.png")));
		btnCancel.setBounds(828, 11, 32, 32);
		
		frmSunocoContacts.getContentPane().add(btnCancel);
		
		JLabel lblSunocoContacts = new JLabel("ETP Contacts");
		lblSunocoContacts.setForeground(Color.BLUE);
		lblSunocoContacts.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblSunocoContacts.setBounds(39, 15, 187, 18);
		frmSunocoContacts.getContentPane().add(lblSunocoContacts);
		
		JButton btnRemoveContact = new JButton("");
		btnRemoveContact.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(g_MainMenu.offlineMode)
				{
					String err = c_EasterEggs.EasterEggs();					
					JOptionPane.showMessageDialog(frmSunocoContacts, err);
					return;
				}
				else
				{
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
			}
		});
		btnRemoveContact.setIcon(new ImageIcon(g_ViewSunoco.class.getResource("/Minus-48.png")));
		btnRemoveContact.setToolTipText("Remove Contact");
		btnRemoveContact.setBounds(198, 292, 32, 32);
		frmSunocoContacts.getContentPane().add(btnRemoveContact);		
			btnNewContact.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(g_MainMenu.offlineMode)
					{
						
						String err = c_EasterEggs.EasterEggs();					
						JOptionPane.showMessageDialog(frmSunocoContacts, err);
						return;
					}
					else
					{
					g_NewSunocoContact.run();
					frmSunocoContacts.dispose();
					}
				}
			});
		
		btnNewContact.setIcon(new ImageIcon(g_ViewSunoco.class.getResource("/Plus-48.png")));
		btnNewContact.setToolTipText("New Contact");
		btnNewContact.setBounds(36, 292, 32, 32);
		
		frmSunocoContacts.getContentPane().add(btnNewContact);
		
		
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
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		        }
		        
			}
		});
		btnMobile.setIcon(new ImageIcon(g_ViewSunoco.class.getResource("/telephone.png")));
		btnMobile.setToolTipText("Mobile Phone");
		btnMobile.setBounds(762, 215, 36, 36);
		frmSunocoContacts.getContentPane().add(btnMobile);
		btnEmail.setIcon(new ImageIcon(g_ViewSunoco.class.getResource("/email.png")));
		btnEmail.addActionListener(new ActionListener() {
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
		btnEmail.setToolTipText("Email");
		btnEmail.setBounds(762, 156, 36, 36);
		
		frmSunocoContacts.getContentPane().add(btnEmail);
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
