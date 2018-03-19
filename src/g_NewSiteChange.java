import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import java.awt.Font;
import java.util.Properties;

import javax.swing.JTextField;
import javax.swing.JEditorPane;
import javax.swing.JButton;
import javax.swing.JScrollPane;

public class g_NewSiteChange {

	private JFrame frm_NewSiteChange;
	private JTextField txt_MOC;
	private JTextField textField;
	private JDatePanelImpl datePanel; 
	private JDatePickerImpl datePicker;

	/**
	 * Launch the application.
	 */
			public static void run() {
				try {
					g_NewSiteChange window = new g_NewSiteChange();
					window.frm_NewSiteChange.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}


	/**
	 * Create the application.
	 */
	public g_NewSiteChange() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frm_NewSiteChange = new JFrame();
		frm_NewSiteChange.setResizable(false);
		frm_NewSiteChange.setBounds(100, 100, 776, 582);
		frm_NewSiteChange.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frm_NewSiteChange.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(20, 11, 730, 49);
		frm_NewSiteChange.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblClientSite = new JLabel("Motiva Dallas (68) ");
		lblClientSite.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblClientSite.setHorizontalAlignment(SwingConstants.CENTER);
		lblClientSite.setBounds(10, 11, 710, 27);
		panel.add(lblClientSite);
		
		UtilDateModel model = new UtilDateModel();
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		datePanel = new JDatePanelImpl(model,p);
		datePicker = new JDatePickerImpl(datePanel, new c_DateLabelFormatter());
		datePicker.getJFormattedTextField().setBackground(Color.WHITE);
		datePicker.setBounds(539, 84, 165, 28);
		frm_NewSiteChange.getContentPane().add(datePicker);
		
		txt_MOC = new JTextField();
		txt_MOC.setBounds(95, 85, 149, 20);
		frm_NewSiteChange.getContentPane().add(txt_MOC);
		txt_MOC.setColumns(10);
		
		JLabel lbl_MOC = new JLabel("MOC #:");
		lbl_MOC.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbl_MOC.setBounds(95, 71, 149, 14);
		frm_NewSiteChange.getContentPane().add(lbl_MOC);
		
		JLabel lblRequestedBy = new JLabel("Requested By:");
		lblRequestedBy.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblRequestedBy.setBounds(314, 71, 149, 14);
		frm_NewSiteChange.getContentPane().add(lblRequestedBy);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(314, 85, 149, 20);
		frm_NewSiteChange.getContentPane().add(textField);
		
		JLabel lblChange = new JLabel("Change:");
		lblChange.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblChange.setBounds(95, 126, 62, 14);
		frm_NewSiteChange.getContentPane().add(lblChange);
		
		JButton btnNewButton = new JButton("Submit");
		btnNewButton.setBounds(635, 510, 89, 23);
		frm_NewSiteChange.getContentPane().add(btnNewButton);
		
		JButton btnBack = new JButton("Back");
		btnBack.setBounds(20, 510, 89, 23);
		frm_NewSiteChange.getContentPane().add(btnBack);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(95, 141, 629, 351);
		frm_NewSiteChange.getContentPane().add(scrollPane);
		
		JEditorPane editorPane = new JEditorPane();
		editorPane.setFont(new Font("Tahoma", Font.PLAIN, 14));
		scrollPane.setViewportView(editorPane);
		
		JLabel lblDate = new JLabel("Date:");
		lblDate.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblDate.setBounds(539, 71, 149, 14);
		frm_NewSiteChange.getContentPane().add(lblDate);
	}
}
