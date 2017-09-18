import java.util.Map;

public class c_GetComputerName {
	
	public static String getComputerName()
	{
	    Map<String, String> env = System.getenv();
	    
	    if (env.containsKey("COMPUTERNAME"))
	        return env.get("COMPUTERNAME");
	    else if (env.containsKey("HOSTNAME"))
	        return env.get("HOSTNAME");
	    else
	        return "Unknown Computer";
	}

}
