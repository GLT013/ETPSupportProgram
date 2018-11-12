import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;

import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.DefaultComboBoxModel;
import javax.swing.border.BevelBorder;
import java.awt.SystemColor;
import javax.swing.SwingConstants;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class g_Tools_VBSMain {

	private JFrame frmVolumeBasedSampling;
	private JTextField txtLoadHrs01;
	private JTextField txtShutdownHrs01;
	private JTextField txtAvgVol01;
	private JTextField txtLoadHrs02;
	private JTextField txtShutdownHrs02;
	private JTextField txtAvgVol02;
	private JTextField txtLoadHrs03;
	private JTextField txtShutdownHrs03;
	private JTextField txtAvgVol03;
	private JTextField txtLoadHrs04;
	private JTextField txtShutdownHrs04;
	private JTextField txtAvgVol04;
	private byte NumInj;

	/**
	 * Launch the application.
	 */
	public static void run(JFrame frame) {
		try {
			g_Tools_VBSMain window = new g_Tools_VBSMain();
			window.frmVolumeBasedSampling.setVisible(true);
			window.frmVolumeBasedSampling.setLocationRelativeTo(frame);
			frame.dispose();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the application.
	 */
	public g_Tools_VBSMain() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		final c_Injector INJ01 = new c_Injector();
		final c_Injector INJ02 = new c_Injector();
		final c_Injector INJ03 = new c_Injector();
		final c_Injector INJ04 = new c_Injector();
		
		frmVolumeBasedSampling = new JFrame();
		frmVolumeBasedSampling.setResizable(false);
		frmVolumeBasedSampling.setTitle("Volume Based Sampling Setpoints Calculator");
		frmVolumeBasedSampling.setBounds(100, 100, 669, 613);
		frmVolumeBasedSampling.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmVolumeBasedSampling.getContentPane().setLayout(null);
		
		//Injector 01
		JPanel panel_Inj01 = new JPanel();
		panel_Inj01.setBackground(SystemColor.activeCaption);
		panel_Inj01.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_Inj01.setBounds(10, 52, 312, 229);
		frmVolumeBasedSampling.getContentPane().add(panel_Inj01);
		panel_Inj01.setLayout(null);
		
		JLabel lblAvgVol01 = new JLabel("Avg. Daily Volume");
		JLabel lblLoadHrs01 = new JLabel("<html>Load Hours <br/>between Idle & Initial</html>");	
		JLabel lblShutdownHrs01 = new JLabel("<html>Load Hours <br/>until Shutdown</html>");
		JLabel lblProduct01 = new JLabel("Product Type");		
		JLabel lblInitialSP01 = new JLabel("<html><center>Initial <br/>Setpoint</center></html>");	
		JLabel lblPrioritySP01 = new JLabel("<html><center>Priority <br/>Setpoint</html>");		
		JLabel lblShutdownSP01 = new JLabel("<html><center>Shutdown <br/>Setpoint</html>");		
		JLabel lblTimeoutSP01 = new JLabel("<html><center>Timeout <br/>Setpoint</html>");		
		
		final JComboBox<String> cbxProduct01 = new JComboBox<String>();
		cbxProduct01.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				switch(cbxProduct01.getSelectedIndex()) {
				case 0:
					txtLoadHrs01.setText("1");
					break;
				case 1:
					txtLoadHrs01.setText("6");
				}
			}
		});
		
		txtLoadHrs01 = new JTextField();	
		txtShutdownHrs01 = new JTextField();	
		txtAvgVol01 = new JTextField();
				
		final JLabel lblInitialValue01 = new JLabel("###,###");		
		final JLabel lblPriorityValue01 = new JLabel("###,###");		
		final JLabel lblShutdownValue01 = new JLabel("###,###");		
		final JLabel lblTimeoutValue01 = new JLabel("##");	
		final JLabel lblInjector01 = new JLabel("Injector 1");
		
		InitializeFormat(lblAvgVol01,lblLoadHrs01, lblShutdownHrs01, lblProduct01, lblInitialSP01, lblPrioritySP01, lblShutdownSP01, lblTimeoutSP01, 
				cbxProduct01, txtLoadHrs01, txtShutdownHrs01, txtAvgVol01, lblInitialValue01, lblPriorityValue01, lblShutdownValue01, 
				lblTimeoutValue01, lblInjector01, panel_Inj01);
		txtLoadHrs01.setText(Float.toString(INJ01.getLoadHrs()));
		txtShutdownHrs01.setText(Float.toString(INJ01.getShutdownHrs()));
		txtAvgVol01.setText(Integer.toString(INJ01.getAvgVol()));
		lblInitialValue01.setText(Integer.toString(INJ01.getInitialSP()));
		lblPriorityValue01.setText(Integer.toString(INJ01.getPrioritySP()));
		lblShutdownValue01.setText(Integer.toString(INJ01.getShutdownSP()));
		lblTimeoutValue01.setText(Integer.toString(INJ01.getTimeoutSP()));
		
		//Injector 02
		final JPanel panel_Inj02 = new JPanel();
		panel_Inj02.setLayout(null);
		panel_Inj02.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_Inj02.setBackground(SystemColor.activeCaption);
		panel_Inj02.setBounds(332, 52, 312, 229);
		frmVolumeBasedSampling.getContentPane().add(panel_Inj02);
		
		JLabel lblAvgVol02 = new JLabel("Avg. Daily Volume");
		JLabel lblLoadHrs02 = new JLabel("<html>Load Hours <br/>between Idle & Initial</html>");
		JLabel lblShutdownHrs02 = new JLabel("<html>Load Hours <br/>until Shutdown</html>");
		JLabel lblProduct02 = new JLabel("Product Type");	
		JLabel lblInitialSP02 = new JLabel("<html><center>Initial <br/>Setpoint</center></html>");		
		JLabel lblPrioritySP02 = new JLabel("<html><center>Priority <br/>Setpoint</html>");
		JLabel lblShutdownSP02 = new JLabel("<html><center>Shutdown <br/>Setpoint</html>");		
		JLabel lblTimeoutSP02 = new JLabel("<html><center>Timeout <br/>Setpoint</html>");
		
		final JComboBox<String> cbxProduct02 = new JComboBox<String>();
		cbxProduct02.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				switch(cbxProduct02.getSelectedIndex()) {
				case 0:
					txtLoadHrs02.setText("1");
					break;
				case 1:
					txtLoadHrs02.setText("6");
				}
			}
		});
		
		txtLoadHrs02 = new JTextField();
		txtShutdownHrs02 = new JTextField();
		txtAvgVol02 = new JTextField();
				
		final JLabel lblInitialValue02 = new JLabel("0");
		final JLabel lblPriorityValue02 = new JLabel("0");		
		final JLabel lblShutdownValue02 = new JLabel("0");		
		final JLabel lblTimeoutValue02 = new JLabel("0");		
		final JLabel lblInjector02 = new JLabel("Injector 2");

		InitializeFormat(lblAvgVol02,lblLoadHrs02,lblShutdownHrs02,lblProduct02,lblInitialSP02,lblPrioritySP02,lblShutdownSP02,lblTimeoutSP02, 
				cbxProduct02,txtLoadHrs02,txtShutdownHrs02,txtAvgVol02, lblInitialValue02, lblPriorityValue02,lblShutdownValue02, 
				lblTimeoutValue02,lblInjector02,panel_Inj02);
		txtLoadHrs02.setText(Float.toString(INJ02.getLoadHrs()));
		txtShutdownHrs02.setText(Float.toString(INJ02.getShutdownHrs()));
		txtAvgVol02.setText(Integer.toString(INJ02.getAvgVol()));
		lblInitialValue02.setText(Integer.toString(INJ02.getInitialSP()));
		lblPriorityValue02.setText(Integer.toString(INJ02.getPrioritySP()));
		lblShutdownValue02.setText(Integer.toString(INJ02.getShutdownSP()));
		lblTimeoutValue02.setText(Integer.toString(INJ02.getTimeoutSP()));
		
		//Injector 03
		final JPanel panel_Inj03 = new JPanel();
		panel_Inj03.setLayout(null);
		panel_Inj03.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_Inj03.setBackground(SystemColor.activeCaption);
		panel_Inj03.setBounds(10, 292, 312, 229);
		frmVolumeBasedSampling.getContentPane().add(panel_Inj03);

		JLabel lblAvgVol03 = new JLabel("Avg. Daily Volume");
		JLabel lblLoadHrs03 = new JLabel("<html>Load Hours <br/>between Idle & Initial</html>");
		JLabel lblShutdownHrs03 = new JLabel("<html>Load Hours <br/>until Shutdown</html>");
		JLabel lblProduct03 = new JLabel("Product Type");
		JLabel lblInitialSP03 = new JLabel("<html><center>Initial <br/>Setpoint</center></html>");
		JLabel lblPrioritySP03 = new JLabel("<html><center>Priority <br/>Setpoint</html>");
		JLabel lblShutdownSP03 = new JLabel("<html><center>Shutdown <br/>Setpoint</html>");
		JLabel lblTimeoutSP03 = new JLabel("<html><center>Timeout <br/>Setpoint</html>");

		final JComboBox<String> cbxProduct03 = new JComboBox<String>();
		cbxProduct03.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				switch(cbxProduct03.getSelectedIndex()) {
				case 0:
					txtLoadHrs03.setText("1");
					break;
				case 1:
					txtLoadHrs03.setText("6");
				}
			}
		});

		txtLoadHrs03 = new JTextField();
		txtShutdownHrs03 = new JTextField();
		txtAvgVol03 = new JTextField();

		final JLabel lblInitialValue03 = new JLabel("0");
		final JLabel lblPriorityValue03 = new JLabel("0");
		final JLabel lblShutdownValue03 = new JLabel("0");
		final JLabel lblTimeoutValue03 = new JLabel("0");
		final JLabel lblInjector03 = new JLabel("Injector 3");
		
		InitializeFormat(lblAvgVol03,lblLoadHrs03,lblShutdownHrs03,lblProduct03,lblInitialSP03,lblPrioritySP03,lblShutdownSP03,lblTimeoutSP03, 
				cbxProduct03,txtLoadHrs03,txtShutdownHrs03,txtAvgVol03, lblInitialValue03, lblPriorityValue03,lblShutdownValue03, 
				lblTimeoutValue03,lblInjector03,panel_Inj03);
		txtLoadHrs03.setText(Float.toString(INJ03.getLoadHrs()));
		txtShutdownHrs03.setText(Float.toString(INJ03.getShutdownHrs()));
		txtAvgVol03.setText(Integer.toString(INJ03.getAvgVol()));
		lblInitialValue03.setText(Integer.toString(INJ03.getInitialSP()));
		lblPriorityValue03.setText(Integer.toString(INJ03.getPrioritySP()));
		lblShutdownValue03.setText(Integer.toString(INJ03.getShutdownSP()));
		lblTimeoutValue03.setText(Integer.toString(INJ03.getTimeoutSP()));
				
		//Injector 04
		final JPanel panel_Inj04 = new JPanel();
		panel_Inj04.setLayout(null);
		panel_Inj04.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_Inj04.setBackground(SystemColor.activeCaption);
		panel_Inj04.setBounds(332, 292, 312, 229);
		frmVolumeBasedSampling.getContentPane().add(panel_Inj04);

		JLabel lblAvgVol04 = new JLabel("Avg. Daily Volume");
		JLabel lblLoadHrs04 = new JLabel("<html>Load Hours <br/>between Idle & Initial</html>");
		JLabel lblShutdownHrs04 = new JLabel("<html>Load Hours <br/>until Shutdown</html>");
		JLabel lblProduct04 = new JLabel("Product Type");
		JLabel lblInitialSP04 = new JLabel("<html><center>Initial <br/>Setpoint</center></html>");
		JLabel lblPrioritySP04 = new JLabel("<html><center>Priority <br/>Setpoint</html>");
		JLabel lblShutdownSP04 = new JLabel("<html><center>Shutdown <br/>Setpoint</html>");
		JLabel lblTimeoutSP04 = new JLabel("<html><center>Timeout <br/>Setpoint</html>");

		final JComboBox<String> cbxProduct04 = new JComboBox<String>();
		cbxProduct04.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				switch(cbxProduct04.getSelectedIndex()) {
				case 0:
					txtLoadHrs04.setText("1");
					break;
				case 1:
					txtLoadHrs04.setText("6");
				}
			}
		});

		txtLoadHrs04 = new JTextField();
		txtShutdownHrs04 = new JTextField();
		txtAvgVol04 = new JTextField();

		final JLabel lblInitialValue04 = new JLabel("0");
		final JLabel lblPriorityValue04 = new JLabel("0");
		final JLabel lblShutdownValue04 = new JLabel("0");
		final JLabel lblTimeoutValue04 = new JLabel("0");
		final JLabel lblInjector04 = new JLabel("Injector 4");
		
		InitializeFormat(lblAvgVol04,lblLoadHrs04,lblShutdownHrs04,lblProduct04,lblInitialSP04,lblPrioritySP04,lblShutdownSP04,lblTimeoutSP04, 
				cbxProduct04,txtLoadHrs04,txtShutdownHrs04,txtAvgVol04, lblInitialValue04, lblPriorityValue04,lblShutdownValue04, 
				lblTimeoutValue04,lblInjector04,panel_Inj04);
		txtLoadHrs04.setText(Float.toString(INJ04.getLoadHrs()));
		txtShutdownHrs04.setText(Float.toString(INJ04.getShutdownHrs()));
		txtAvgVol04.setText(Integer.toString(INJ04.getAvgVol()));
		lblInitialValue04.setText(Integer.toString(INJ04.getInitialSP()));
		lblPriorityValue04.setText(Integer.toString(INJ04.getPrioritySP()));
		lblShutdownValue04.setText(Integer.toString(INJ04.getShutdownSP()));
		lblTimeoutValue04.setText(Integer.toString(INJ04.getTimeoutSP()));
		
		//General Selections
		JLabel lblNumInj = new JLabel("Number of Injectors:");
		lblNumInj.setBounds(10, 11, 113, 14);
		frmVolumeBasedSampling.getContentPane().add(lblNumInj);
		
		final JComboBox<String> cbxNumInj = new JComboBox<String>();
		cbxNumInj.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				NumInj = Byte.parseByte((String) cbxNumInj.getSelectedItem());
				switch(NumInj){
				case 1:
					txtShutdownHrs01.setText("3");
					panel_Inj02.setVisible(false);
					panel_Inj03.setVisible(false);
					panel_Inj04.setVisible(false);
					break;
				case 2:
					txtShutdownHrs01.setText("6");
					txtShutdownHrs02.setText("6");
					panel_Inj02.setVisible(true);
					panel_Inj03.setVisible(false);
					panel_Inj04.setVisible(false);
					break;
				case 3:
					txtShutdownHrs01.setText("9");
					txtShutdownHrs02.setText("9");
					txtShutdownHrs03.setText("9");
					panel_Inj02.setVisible(true);
					panel_Inj03.setVisible(true);
					panel_Inj04.setVisible(false);
					break;
				case 4:
					txtShutdownHrs01.setText("12");
					txtShutdownHrs02.setText("12");
					txtShutdownHrs03.setText("12");
					txtShutdownHrs04.setText("12");
					panel_Inj02.setVisible(true);
					panel_Inj03.setVisible(true);
					panel_Inj04.setVisible(true);
					break;
				}
			}
		});
		cbxNumInj.setModel(new DefaultComboBoxModel<String>(new String[] {"1", "2", "3", "4"}));
		cbxNumInj.setBounds(121, 8, 40, 20);
		frmVolumeBasedSampling.getContentPane().add(cbxNumInj);
		cbxNumInj.setSelectedIndex(3);
		NumInj = 4;
		
		JButton btnCalculate = new JButton("Calculate");
		btnCalculate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int x;		//Product Selected
				float y;	//Load Hours
				float z;	//Shutdown Hours
				//Injector 01
				x = cbxProduct01.getSelectedIndex();
				y = Float.parseFloat(txtLoadHrs01.getText());
				z = Float.parseFloat((txtShutdownHrs01.getText()));
				InjectorCalc(INJ01,NumInj,x,y,z,txtAvgVol01,lblInitialValue01,lblPriorityValue01,lblShutdownValue01,lblTimeoutValue01);
				
				//Injector 02
				if(NumInj > 1) {
					x = cbxProduct02.getSelectedIndex();
					y = Float.parseFloat(txtLoadHrs02.getText());
					z = Float.parseFloat((txtShutdownHrs02.getText()));
					InjectorCalc(INJ02,NumInj,x,y,z,txtAvgVol02,lblInitialValue02,lblPriorityValue02,lblShutdownValue02,lblTimeoutValue02);
				}
				
				//Injector 03
				if(NumInj > 2) {
					x = cbxProduct03.getSelectedIndex();
					y = Float.parseFloat(txtLoadHrs03.getText());
					z = Float.parseFloat((txtShutdownHrs03.getText()));
					InjectorCalc(INJ03,NumInj,x,y,z,txtAvgVol03,lblInitialValue03,lblPriorityValue03,lblShutdownValue03,lblTimeoutValue03);
				}
				
				//Injector 04
				if(NumInj > 3) {
					x = cbxProduct04.getSelectedIndex();
					y = Float.parseFloat(txtLoadHrs04.getText());
					z = Float.parseFloat((txtShutdownHrs04.getText()));
					InjectorCalc(INJ04,NumInj,x,y,z,txtAvgVol04,lblInitialValue04,lblPriorityValue04,lblShutdownValue04,lblTimeoutValue04);
				}
			}
		});
		btnCalculate.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnCalculate.setBounds(177, 7, 89, 23);
		frmVolumeBasedSampling.getContentPane().add(btnCalculate);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				g_MainMenu.run(frmVolumeBasedSampling);
			}
		});
		btnBack.setBounds(10, 551, 89, 23);
		frmVolumeBasedSampling.getContentPane().add(btnBack);
	}
	private void InitializeFormat(JLabel AvgVol, JLabel LoadHrs, JLabel ShutdownHrs, JLabel Product, JLabel InitialSP, JLabel PrioritySP, JLabel ShutdownSP, 
			JLabel TimeoutSP, JComboBox<String> Productbx, JTextField LoadHrstxt, JTextField ShutdownHrstxt, JTextField AvgVoltxt, JLabel InitialValue,
			JLabel PriorityValue, JLabel ShutdownValue, JLabel TimeoutValue, JLabel Injector, JPanel Inj) {
		AvgVol.setFont(new Font("Tahoma", Font.PLAIN, 12));
		AvgVol.setBounds(13, 125, 120, 14);
		Inj.add(AvgVol);
		
		LoadHrs.setFont(new Font("Tahoma", Font.PLAIN,12));
		LoadHrs.setBounds(13, 44, 120, 44);
		Inj.add(LoadHrs);
		
		ShutdownHrs.setFont(new Font("Tahoma", Font.PLAIN, 12));
		ShutdownHrs.setBounds(13, 85, 107, 31);
		Inj.add(ShutdownHrs);
		
		Product.setFont(new Font("Tahoma", Font.PLAIN, 12));
		Product.setBounds(13, 31, 79, 14);
		Inj.add(Product);
		
		InitialSP.setFont(new Font("Tahoma", Font.PLAIN, 12));
		InitialSP.setBounds(18, 160, 54, 31);
		Inj.add(InitialSP);
		
		PrioritySP.setFont(new Font("Tahoma", Font.PLAIN, 12));
		PrioritySP.setBounds(90, 160, 52, 31);
		Inj.add(PrioritySP);
		
		ShutdownSP.setFont(new Font("Tahoma", Font.PLAIN, 12));
		ShutdownSP.setBounds(160, 158, 61, 35);
		Inj.add(ShutdownSP);
		
		TimeoutSP.setFont(new Font("Tahoma", Font.PLAIN, 12));
		TimeoutSP.setBounds(239, 160, 54, 31);
		Inj.add(TimeoutSP);
		
		Productbx.setBounds(143, 28, 101, 20);
		Productbx.setFont(new Font("Tahoma", Font.PLAIN, 12));
		Productbx.setModel(new DefaultComboBoxModel<String>(new String[] {"Regular", "Premium"}));
		Inj.add(Productbx);
		
		LoadHrstxt.setFont(new Font("Tahoma", Font.PLAIN, 12));
		LoadHrstxt.setBounds(143, 54, 49, 25);
		LoadHrstxt.setColumns(10);
		Inj.add(LoadHrstxt);
				
		ShutdownHrstxt.setFont(new Font("Tahoma", Font.PLAIN, 12));
		ShutdownHrstxt.setBounds(143,88,49,25);
		ShutdownHrstxt.setColumns(10);
		Inj.add(ShutdownHrstxt);
		
		AvgVoltxt.setFont(new Font("Tahoma", Font.PLAIN, 12));
		AvgVoltxt.setBounds(143, 120, 101, 25);
		AvgVoltxt.setColumns(10);
		Inj.add(AvgVoltxt);
		
		InitialValue.setHorizontalAlignment(SwingConstants.CENTER);
		InitialValue.setBounds(15, 198, 61, 14);
		InitialValue.setFont(new Font("Tahoma", Font.BOLD, 13));
		Inj.add(InitialValue);
		
		PriorityValue.setHorizontalAlignment(SwingConstants.CENTER);
		PriorityValue.setBounds(86, 198, 61, 14);
		PriorityValue.setFont(new Font("Tahoma", Font.BOLD, 13));
		Inj.add(PriorityValue);
		
		ShutdownValue.setHorizontalAlignment(SwingConstants.CENTER);
		ShutdownValue.setBounds(160, 198, 61, 14);
		ShutdownValue.setFont(new Font("Tahoma", Font.BOLD, 13));
		Inj.add(ShutdownValue);
		
		TimeoutValue.setHorizontalAlignment(SwingConstants.CENTER);
		TimeoutValue.setBounds(252, 198, 28, 14);
		TimeoutValue.setFont(new Font("Tahoma", Font.BOLD, 13));
		Inj.add(TimeoutValue);
		
		Injector.setFont(new Font("Tahoma", Font.BOLD, 13));
		Injector.setBounds(113, 0, 79, 20);
		Inj.add(Injector);
	}
	private void InjectorCalc(c_Injector Inj, byte i, int Product, Float LoadHrs, Float ShutdownHrs, JTextField AvgVol, JLabel InitialValue, JLabel PriorityValue, 
			JLabel ShutdownValue, JLabel TimeoutValue) {
		String str;
		DecimalFormat formatter = new DecimalFormat("#,###");
		switch (Product) {
		case 0:
			Inj.setProduct(true);
			break;
		case 1:
			Inj.setProduct(false);
		}
		Inj.setLoadHrs(LoadHrs);
		Inj.setShutdownHrs(ShutdownHrs);
		str = AvgVol.getText();
		str = str.replace(",","");
		Inj.setAvgVol(Integer.parseInt(str));
		AvgVol.setText(formatter.format(Inj.getAvgVol()));
		Inj.CalcInitial();
		Inj.CalcPriority(i);
		Inj.CalcShutdown();
		Inj.CalcTimeout();
		InitialValue.setText(formatter.format(Inj.getInitialSP()));
		PriorityValue.setText(formatter.format(Inj.getPrioritySP()));
		ShutdownValue.setText(formatter.format(Inj.getShutdownSP()));
		TimeoutValue.setText(formatter.format(Inj.getTimeoutSP()));
	}
}
