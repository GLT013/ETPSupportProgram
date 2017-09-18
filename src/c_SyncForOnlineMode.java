import java.sql.ResultSet;
import java.sql.SQLException;
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
	
	private static void SyncTickets() throws SQLException
	{
		
		String commandText = "SELECT Client,Site,Category,Ticket,EnteredDate,Description,Assigned,Status,Resolution,Internal,Active,EmailSent,UpdateDate,TimeSpent,CCNotified FROM SupportTickets";
		ResultSet rs = c_Query.SQLiteExecuteResultSet(commandText);
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
				String ticketNum = rs.getString("Ticket");
				commandText2 = "SELECT UpdateDate, COUNT(*) as NumRows From SupportTickets WHERE Ticket = '" + ticketNum + "' GROUP BY UpdateDate";
				rs2 = c_Query.ExecuteResultSet(commandText2);
			
				int count = 0;
				String tmp = "";
				Date OnlineDate = null;
				Date OfflineDate = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(rs.getString("UpdateDate")); //Throws exception
				while (rs2.next()) 
				{
				    ++count;
				    tmp = rs2.getString("UpdateDate");
				    OnlineDate = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(rs2.getString("UpdateDate")); //Throws exception
				}
				if(count >0)
				{
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
				else //Ticket does not exist
				{
					try
					{
						
						ccNotified = null;
						try
						{
							if(rs.getString("CCNotified").compareTo("null") == 0)
							{
								ccNotified = null;
							}
							else
							{
								ccNotified = "\'" + rs.getString("CCNotified") + "\'"; 
							}
						}
						catch(Exception e3)
						{
							//do nothing
						}
						
						String EnteredDate = rs.getString("EnteredDate");
						clean_res = c_CleanString.Clean_String(rs.getString("Resolution"));
						clean_internal = c_CleanString.Clean_String(rs.getString("Internal"));
						commandText2 = "INSERT INTO SupportTickets(Client,Site,Category,Ticket,EnteredDate,Description,Assigned,Status,Resolution,Internal,Active,EmailSent,UpdateDate,TimeSpent,CCNotified) VALUES"
							+ "('" + rs.getString("Client") + "','" + rs.getString("Site") + "','" + rs.getString("Category") + "','" + rs.getString("Ticket") + "','" + EnteredDate + "','"  + rs.getString("Description") + "','" + rs.getString("Assigned") + "','" + rs.getString("Status") + "','" + rs.getString("Resolution") + "','" + rs.getString("Internal") + "','" + rs.getString("Active") + "','" + rs.getString("EmailSent") + "','" + rs.getString("UpdateDate") + "','" + rs.getString("TimeSpent") + "'," + ccNotified + ")";
						c_Query.ExecuteQuery(commandText2);
						System.out.println(commandText2);
					}
					catch(Exception e2)
					{
						e2.printStackTrace();
						
					}
				}
						
			}
			
		}
		catch (Exception e) //Ticket does not exist in online database.
		{
			e.printStackTrace();

	}

	}
}
