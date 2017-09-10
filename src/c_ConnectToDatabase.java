import java.sql.DriverManager;
import java.sql.Statement;


public class c_ConnectToDatabase {
	public static java.sql.Connection con;
	public static Statement stmt;
	
	public static boolean Connect()
	{
	 try
     {
		 // Load the SQLServerDriver class, build the
         // connection string, and get a connection
         Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
         String connectionUrl = "jdbc:sqlserver://10.10.38.252;" +
                                 "database=Support;" +
                                 "user=sa;" +
                                 "password=Mce!2195";
         con = DriverManager.getConnection(connectionUrl);
        
         return true;
     }
     catch(Exception ex)
     {
    	 
         ex.printStackTrace();
         
         return false;
     }
	
	}
}
