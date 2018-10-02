import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EtchedBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;

public class g_AutoCloseTickets {

	private JFrame frame;
	private JTextArea textArea;

	/**
	 * Launch the application.
	 */

			public static void run(JFrame frame) {
				try {
					g_AutoCloseTickets window = new g_AutoCloseTickets();
					window.frame.setVisible(true);
					window.frame.setLocationRelativeTo(frame);
					frame.dispose();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
	
	

	/**
	 * Create the application.
	 */
	public g_AutoCloseTickets() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 384, 576);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setTitle("Automated Support Program v." + g_MainMenu.version);
		
		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setBounds(10, 50, 173, 477);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		textArea = new JTextArea();
		textArea.setBounds(0, 0, 173, 477);
		panel.add(textArea);
		
		JButton btnRun = new JButton("Clean up!");
		btnRun.setBounds(269, 504, 89, 23);
		btnRun.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int reply = JOptionPane.showConfirmDialog(frame, "Warning: Verify ALL tickets from the maintenance sheet are present. \n Any ticket not found will be closed. \n Continue? ", "Ticket Verification", JOptionPane.YES_NO_OPTION);
		        if (reply == JOptionPane.YES_OPTION)
		        {
		        	CleanTickets();
		        }
			}
		});
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(btnRun);
		
		JLabel lblPleaseEnterOne = new JLabel("Please enter one ticket number per line.");
		lblPleaseEnterOne.setBounds(10, 11, 448, 14);
		lblPleaseEnterOne.setFont(new Font("Tahoma", Font.PLAIN, 13));
		frame.getContentPane().add(lblPleaseEnterOne);
		
		JLabel lblTheseCanBe = new JLabel("These can be copied directly off of the maintenance sheet.");
		lblTheseCanBe.setBounds(10, 25, 448, 14);
		lblTheseCanBe.setFont(new Font("Tahoma", Font.PLAIN, 13));
		frame.getContentPane().add(lblTheseCanBe);		
	}
	
	private void CleanTickets()
	{
		String s[] = textArea.getText().split("\\r?\\n");
	    ArrayList<String>arrList = new ArrayList<>(Arrays.asList(s));
	    
	    String commandText = "SELECT Ticket from SupportTickets WHERE Active = 1 and EmailSent = 1";
	    ResultSet rs = c_Query.ExecuteResultSet(commandText);
	    String activeTicket = "";
	    boolean found = false;
	    try
		{
			while((rs!=null) && (rs.next()))
			{
				found = false;
				activeTicket = rs.getString("Ticket");
				 for(int i = 0; i< arrList.size(); i++)
					{
				    	String TicketNum = arrList.get(i);
				    	if (TicketNum.length() > 11)
						{
							
							String temp = "";
							if (TicketNum.startsWith("1"))
							{
								temp = TicketNum.replace("1000000", "1-");
								TicketNum = temp;
								
							}
							else if (TicketNum.startsWith("2"))
							{
								temp = TicketNum.replace("2000000", "2-");
								
							}
							
							TicketNum = temp;																
						}
				    	
				    	//if ticket is found then it is on the maintenance sheet
				    	if(TicketNum.compareTo(activeTicket) == 0)
				    	{
				    		System.out.println(activeTicket);
				    		found = true;
				    		break;
				    	}
				    					    	    					    	
					}
					 
					 if(!found)
					 {
					 commandText = "UPDATE SupportTickets SET Active = 0, Status = 'Closed By ETP' WHERE Ticket = '" + activeTicket + "'";
					 c_Query.UpdateResultSet(commandText);				
					 }
			}
			
		}
	    catch(Exception e)
	    {
	    	
	    }
	    
	 	
	}
}
