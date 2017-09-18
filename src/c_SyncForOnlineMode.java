import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;

public class c_SyncForOnlineMode {
	
	public static void run() {
		try {
				if(!g_MainMenu.SQLiteDB.exists())
				{
					JOptionPane.showMessageDialog(null, "Cannot locate offline database to sync with.");
					return;
				}
				else
				{
					SyncTickets();
				}
				
				
				
		} catch (Exception e) {
			//e.printStackTrace();
		}
	}
	
	private static void SyncTickets()
	{
		
		String commandText = "SELECT Client,Site,Category,Ticket,EnteredDate,Description,Assigned,Status,Resolution,Internal,Active,EmailSent,UpdateDate,TimeSpent,CCNotified FROM SupportTickets";
		ResultSet rs = c_Query.SQLiteExecuteResultSet(commandText);
		System.out.println(commandText);
		String commandText2 = "";
		ResultSet rs2 = null;
		String UpdateQuery = "";		
		String clean_res = "";
		String clean_internal = "";
		String ccNotified = "";
		try
		{
			
			while(rs.next() && rs != null)
			{
				commandText2 = "SELECT UpdateDate as NumRows From SupportTickets WHERE Ticket = '" + rs.getString("Ticket") + "' GROUP BY UpdateDate";
				rs2 = c_Query.ExecuteResultSet(commandText2);
				
					rs2.next();					
					Date OfflineDate = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(rs.getString("UpdateDate")); //Throws exception
					Date OnlineDate = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(rs2.getString("UpdateDate")); //Throws exception
					
					//if Online Date is Older Than Offline, Sync Changes
					if(OnlineDate.before(OfflineDate))
					{
						clean_res = c_CleanString.Clean_String(rs.getString("Resolution"));
						clean_internal = c_CleanString.Clean_String(rs.getString("Internal"));
						if(rs.getString("CCNotified").compareTo("null") == 0)
						{
							ccNotified = null;
						}
						else
						{
							ccNotified = "\'" + rs.getString("CCNotified") + "\'"; 
						}
						UpdateQuery = "UPDATE SupportTickets SET Assigned = '" + rs.getString("Assigned") + "', Status= '" + rs.getString("Status") + "', Resolution= '" + clean_res + "', Internal= '" + clean_internal + "', Active= '" + rs.getString("Active") + "', EmailSent = '" + rs.getString("EmailSent") + "', UpdateDate = '" + rs.getString("UpdateDate") + "', TimeSpent = '" + rs.getString("TimeSpent") + "', CCNotified = " + ccNotified + " WHERE Ticket = '" + rs.getString("Ticket") + "'";
						
						c_Query.ExecuteQuery(UpdateQuery);
					}
					else
					{
						//do nothing. Skip update.
					}
				}
			rs.close();	
			rs2.close();
			
		}
		catch (Exception e) //Ticket does not exist in online database.
		{
			
			//e.printStackTrace();
		}
		
	}

}
