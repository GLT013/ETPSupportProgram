import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class c_DatabaseSettings {

	private JFrame frmDatabaseSettings;
	private static JTextField textField;
	public static String filepath = "(local)";
	/**
	 * Launch the application.
	 */
	
			public static void run() {
				try {
					c_DatabaseSettings window = new c_DatabaseSettings();
					window.frmDatabaseSettings.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
	}

	/**
	 * Create the application.
	 */
	public c_DatabaseSettings() {
		initialize();
	}
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmDatabaseSettings = new JFrame();
		frmDatabaseSettings.setTitle("Database Settings");
		frmDatabaseSettings.setBounds(0, 0, 290, 138);
		frmDatabaseSettings.setResizable(false);
		frmDatabaseSettings.setLocationRelativeTo(null);
		frmDatabaseSettings.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmDatabaseSettings.getContentPane().setLayout(null);
		frmDatabaseSettings.setAlwaysOnTop(true);
		
		textField = new JTextField();
		textField.setBounds(12, 34, 232, 20);
		frmDatabaseSettings.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblAccessDatabaseLocation = new JLabel("Database Server (i.e. SUN-TEST-SQL)");
		lblAccessDatabaseLocation.setHorizontalAlignment(SwingConstants.CENTER);
		lblAccessDatabaseLocation.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblAccessDatabaseLocation.setBounds(12, 9, 259, 14);
		frmDatabaseSettings.getContentPane().add(lblAccessDatabaseLocation);
		
		JButton btnSaveSettings = new JButton("Save Settings");
		btnSaveSettings.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frmDatabaseSettings.dispose();				
				//ConnectToDatabase.Connect(filepath);
				//MainMenuGUI.run();
			}
		});
		btnSaveSettings.setBounds(125, 73, 119, 31);
		frmDatabaseSettings.getContentPane().add(btnSaveSettings);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frmDatabaseSettings.dispose();
				//MainMenuGUI.run();
			}
		});
		btnCancel.setBounds(12, 73, 103, 31);
		frmDatabaseSettings.getContentPane().add(btnCancel);
	}
}
