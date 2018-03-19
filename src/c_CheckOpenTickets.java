import java.sql.ResultSet;

public class c_CheckOpenTickets {
	
	
	public static boolean CheckTickets()
	{
		String commandText = "select count(*) from SupportTickets where active = 1";
		int rowCount = 0;
	    ResultSet rs = c_Query.ExecuteResultSet(commandText);
	    try{

			while ((rs!=null) && (rs.next()))
			{
				  rowCount = rs.getInt(1);				  
			}
		}
		catch(Exception e)
		{
			System.out.println("ERROR " + e.toString());
		}
	    
	    if (rowCount > g_MainMenu.ticketMax)
	    {
	    	return false;
	    }
	    else
	    {
	    	return true;
	    }
	}

}
