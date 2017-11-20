import java.sql.ResultSet;
import java.util.Map;

public class c_GetComputerName {
	
	public static String getComputerName()
	{
	    Map<String, String> env = System.getenv();
	    String ComputerName = "";
	    if (env.containsKey("COMPUTERNAME"))
	    {
	    	ComputerName =  env.get("COMPUTERNAME");
	    }
	    else if (env.containsKey("HOSTNAME"))
	    {
	    	ComputerName = env.get("HOSTNAME");
	    }	        
	    else
	    {
	    	ComputerName = "Unknown Computer";
	    }
	    
	    String commandText = "SELECT Name, Admin from EN_Employees WHERE Computer = '" + ComputerName + "'";
	    ResultSet rs = c_Query.ExecuteResultSet(commandText);
	    try{

			while ((rs!=null) && (rs.next()))
			{
				  ComputerName = rs.getString("Name");
				  g_MainMenu.adminMode = rs.getBoolean("Admin");
				  System.out.println(g_MainMenu.adminMode);
			}
		}
		catch(Exception e)
		{
			System.out.println("ERROR " + e.toString());
		}
	    
	    
	    return ComputerName;
	}

}
