import java.awt.EventQueue;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.border.EtchedBorder;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.AbstractListModel;

public class g_SiteChanges {

	private JFrame frmSiteChanges;
	private JComboBox cb_Sites;
	private JPanel panel_1;

	/**
	 * Launch the application.
	 */

			public static void run() {
				try {
					g_SiteChanges window = new g_SiteChanges();
					window.frmSiteChanges.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}


	/**
	 * Create the application.
	 */
	public g_SiteChanges() {
		initialize();
		PopulateSiteList();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmSiteChanges = new JFrame();
		frmSiteChanges.setBounds(100, 100, 932, 591);
		frmSiteChanges.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSiteChanges.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setBounds(10, 11, 896, 49);
		frmSiteChanges.getContentPane().add(panel);
		
		cb_Sites = new JComboBox();
		panel.add(cb_Sites);
		
		panel_1 = new JPanel();
		panel_1.setBounds(10, 73, 181, 469);
		frmSiteChanges.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 161, 458);
		panel_1.add(scrollPane);
		
		JList list = new JList();
		list.setModel(new AbstractListModel() {
			String[] values = new String[] {"2/28/2018", "2/27/2018", "2/24/2018", "2/16/2018", "2/04/2018"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		scrollPane.setViewportView(list);
	}
	
	private void PopulateSiteList()
	{
		 String commandText = "SELECT Client, Site FROM Sites ORDER BY Client, Site Asc";        
	        ResultSet rs = c_Query.ExecuteResultSet(commandText);
	        	     
	        try {
				while((rs!=null) && (rs.next()))
				{					
					String client = rs.getString("Client") + " - " + rs.getString("Site");			
					
					cb_Sites.addItem(client);	
				}
			} catch (SQLException e) {
			}
	        try {
				rs.close();
			} catch (SQLException e) {
			
			}
	        
		
	}
}
