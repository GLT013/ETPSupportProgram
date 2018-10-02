import java.net.NetworkInterface;
import java.net.SocketException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Enumeration;

public class c_GetMacAddress{
	
	public static boolean getMacAddress()
	{
		
	String macAddr = "";
	boolean mac_found = false;
	String commandText = "";
	ResultSet rs = null;	
    NetworkInterface inter;
    
	try {					
		 StringBuilder sb = new StringBuilder();			 		 
		 Enumeration<NetworkInterface> networks = NetworkInterface.getNetworkInterfaces();	       
	        while (networks.hasMoreElements() && mac_found == false) 
	        {
	            inter = networks.nextElement();
	            byte[] mac = inter.getHardwareAddress();
	            if (!isVMMac(mac))
	            {
		            if (mac != null) 
		            {
		                for (int i = 0; i < mac.length; i++) 
		                {	                    
		                    sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));		                	
		                }	         
		            		            
		                commandText = "SELECT a.EmployeeID, a.ComputerName, b.Name, b.Title, b.Admin from MacAddress a, EN_Employees b WHERE a.MacAddress= '" + sb.toString() + "' AND b.Active = 1 and a.EmployeeID = b.rowID";
		                rs = c_Query.ExecuteResultSet(commandText);
		                
		    	    try{
	
		    			while ((rs!=null) && (rs.next()))
		    			{		    				
		    				g_MainMenu.CurrentUser = rs.getString("Name");
		    				g_MainMenu.CurrentUserTitle = rs.getString("Title");
		    				 g_MainMenu.adminMode = rs.getBoolean("Admin");
		    				mac_found = true;
		    			}
		    		}
		    		catch(Exception e)
		    		{
		    			System.out.println("ERROR " + e.toString());
		    		}
		            
		           }		           
		            sb.setLength(0); 	            
		        }	
	        }
	} catch (SocketException e){			
		e.printStackTrace();			
	}
	if (mac_found)
	{
		return true;
	}
	else
	{
		return false;
	}
	    
   }
	
	 private static boolean isVMMac(byte[] mac) 
	 {
	        if(null == mac) return false;
	        byte invalidMacs[][] = {
	                {0x00, 0x05, 0x69},             //VMWare
	                {0x00, 0x1C, 0x14},             //VMWare
	                {0x00, 0x0C, 0x29},             //VMWare
	                {0x00, 0x50, 0x56},             //VMWare
	                {0x08, 0x00, 0x27},             //Virtualbox
	                {0x0A, 0x00, 0x27},             //Virtualbox
	                {0x00, 0x03, (byte)0xFF},       //Virtual-PC
	                {0x00, 0x15, 0x5D}              //Hyper-V
	        };

	        for (byte[] invalid: invalidMacs)
	        {
	            if (invalid[0] == mac[0] && invalid[1] == mac[1] && invalid[2] == mac[2]) return true;
	        }
	        return false;
	}
	
	

}