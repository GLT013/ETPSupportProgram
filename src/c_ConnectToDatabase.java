import java.sql.DriverManager;
import java.sql.Statement;


public class c_ConnectToDatabase {
	public static java.sql.Connection con;
	public static Statement stmt;
	public static java.sql.Connection con_sqlite;
	public static Statement stmt_sqlite;
	
	public static boolean Connect()
	{
	 try
     {
         Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
         String connectionUrl = "jdbc:sqlserver://enesupport.database.windows.net;" +
                                 "database=Support;" +
                                 "user=travis;" +
                                 "password=Butane#Ops1";
         con = DriverManager.getConnection(connectionUrl);
         
         return true;
     }
     catch(Exception e)
     {    	          
         return false;
     }
	
	}
	
	


}
