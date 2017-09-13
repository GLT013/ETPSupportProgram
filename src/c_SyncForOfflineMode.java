import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import javax.swing.JOptionPane;

public class c_SyncForOfflineMode {
	
	public static void run() {
		try {
				if(!g_MainMenu.SQLiteDB.exists())
				{
					CreateDatabase();
					ConnectSQLite();
					CreateTables();
				}
				else
				{
					ConnectSQLite();
				}
				
				CopyData();
				JOptionPane.showMessageDialog(null, "Database Synchronization Completed.");
		} catch (Exception e) {
			//e.printStackTrace();
		}
	}
	
	
	public static void CreateDatabase()
	{
		try {
			
			c_CreateSQLiteDB.createNewDatabase();
		}
		catch(Exception e)
		{
			System.out.println(e.toString());
		}
	}
	
	public static void ConnectSQLite()
	{
		if(!c_ConnectToDatabase.ConnectSQLite())
		{
			JOptionPane.showMessageDialog(null, "Unable to connect to local Database.");	
		}
		
		
	}
	
	public static void CreateTables()
	{
		String ENEmployees = "CREATE TABLE IF NOT EXISTS \"EN_Employees\" ( `Name` [text] NOT NULL UNIQUE, `Title` [text] NOT NULL, `Email` [text] NOT NULL, `Office_Phone` [text], `Mobile_Phone` [text], `Active` [bit] )";
		
		String SunocoContacts = "CREATE TABLE IF NOT EXISTS \"Sunoco_Contacts\" ( `Name` [text] UNIQUE, `Company` [text], `Mobile_Phone` [text], `Email` [text] )";
		
		String SiteDevices = "CREATE TABLE [SiteDevices](\r\n" + 
				"	[SiteID] [int] NOT NULL,\r\n" + 
				"	[IO] [bit] NULL,\r\n" + 
				"	[Device] [text] NULL,\r\n" + 
				"	[DeviceIP] [int] NULL,\r\n" + 
				"	[Category] [text] NULL\r\n" + 
				")";
		
		String SiteInjectorDetails = "CREATE TABLE IF NOT EXISTS [SiteInjectorDetails](\r\n" + 
				"	[SiteID] [int] NOT NULL UNIQUE,\r\n" + 
				"	[Injector] [int] NULL,\r\n" + 
				"	[InjectorType] [text] NULL,\r\n" + 
				"	[InjectorMMSize] [float] NULL,\r\n" + 
				"	[InjectorMisc] [text] NULL,\r\n" + 
				"	[GasType] [text] NULL\r\n" + 
				")";
		
		String Sites = "CREATE TABLE IF NOT EXISTS [Sites](\r\n" + 
				"	[Client] [text] NULL,\r\n" + 
				"	[Site] [text] NULL,\r\n" + 
				"	[State] [text] NULL,\r\n" + 
				"	[SiteID] [int] NULL UNIQUE,\r\n" + 
				"	[ClientAbbrv] [text] NULL,\r\n" + 
				"	[SiteAbbrv] [varchar] NULL,\r\n" + 
				"	[iDracIP] [int] NULL,\r\n" + 
				"	[HostIP] [int] NULL,\r\n" + 
				"	[ViewIP] [int] NULL,\r\n" + 
				"	[SQLIP] [int] NULL,\r\n" + 
				"	[DevIP] [int] NULL,\r\n" + 
				"	[Generation] [float] NULL,\r\n" + 
				"	[HMI] [text] NULL,\r\n" + 
				"	[Address] [text] NULL,\r\n" + 
				"	[Phone] [text] NULL,\r\n" + 
				"	[Field_Tech] [text] NULL,\r\n" + 
				"	[Field_Supervisor] [text] NULL,\r\n" + 
				"	[Twic] [bit] NULL,\r\n" + 
				"	[Timezone] [text] NULL,\r\n" + 
				"	[HighPerformance] [bit] NULL\r\n" + 
				")";
		
		String SiteSamplingDetails = "CREATE TABLE IF NOT EXISTS [SiteSamplingDetails](\r\n" + 
				"	[SiteID] [int] NOT NULL UNIQUE,\r\n" + 
				"	[SampleLine] [int] NULL,\r\n" + 
				"	[SampleType] [text] NULL,\r\n" + 
				"	[ReturnLine] [int] NULL,\r\n" + 
				"	[ReturnType] [nvarchar](50) NULL\r\n" + 
				")";
		
		String SiteTankDetails = "CREATE TABLE IF NOT EXISTS [SiteTankDetails](\r\n" + 
				"	[SiteID] [int] NOT NULL UNIQUE,\r\n" + 
				"	[Tank] [int] NULL,\r\n" + 
				"	[TankType] [text] NULL,\r\n" + 
				"	[TankVolume] [float] NULL\r\n" + 
				")";
		
		String SupportTickets = "CREATE TABLE IF NOT EXISTS [SupportTickets](\r\n" + 
				"	[Client] [text] NULL,\r\n" + 
				"	[Site] [text] NULL,\r\n" + 
				"	[Category] [text] NULL,\r\n" + 
				"	[SubCategory] [text] NULL,\r\n" + 
				"	[Ticket] [text] NOT NULL UNIQUE,\r\n" + 
				"	[EnteredDate] [datetime] NULL,\r\n" + 
				"	[Description] [text] NULL,\r\n" + 
				"	[Assigned] [text] NULL,\r\n" + 
				"	[Status] [text] NULL,\r\n" + 
				"	[Resolution] [text] NULL,\r\n" + 
				"	[Internal] [text] NULL,\r\n" + 
				"	[Active] [bit] NULL,\r\n" + 
				"	[EmailSent] [bit] NULL,\r\n" + 
				"	[UpdateDate] [text] NULL,\r\n" + 
				"	[TimeSpent] [text] NULL,\r\n" + 
				"	[CCNotified] [text] NULL)";
		
		String Version = "CREATE TABLE IF NOT EXISTS [Version](\r\n" + 
				"	[ID] [nchar](10) NOT NULL UNIQUE,\r\n" + 
				"	[Version] [float] NULL)";
		
		String Files = "CREATE TABLE [Files](\r\n" + 
				"	[rowID] [int] IDENTITY(1,1) NOT NULL,\r\n" + 
				"	[TicketNum] [text] NULL,\r\n" + 
				"	[Filename] [text] NULL,\r\n" + 
				"	[UploadDate] [datetime] NULL\r\n)"; 
				
		try
		{
			c_Query.SQLiteExecuteQuery(SunocoContacts);		
			c_Query.SQLiteExecuteQuery(ENEmployees);
			c_Query.SQLiteExecuteQuery(SiteDevices);
			c_Query.SQLiteExecuteQuery(SiteInjectorDetails);
			c_Query.SQLiteExecuteQuery(Sites);
			c_Query.SQLiteExecuteQuery(SiteSamplingDetails);
			c_Query.SQLiteExecuteQuery(SiteTankDetails);
			c_Query.SQLiteExecuteQuery(SupportTickets);
			c_Query.SQLiteExecuteQuery(Version);		
			c_Query.SQLiteExecuteQuery(Files);
		}
		catch (Exception e)
		{
			System.out.println(e.toString());
			//e.printStackTrace();
		}
		
	}
	
	
	public static void CopyData()
	{
		CopyENEmployees();
		CopySunocoContacts();
		CopySites();
	}
	
	public static void CopyENEmployees()
	{
		
		String commandText = "SELECT Name,Title,Email,Office_Phone,Mobile_Phone,Active FROM EN_Employees";
		ResultSet rs = c_Query.ExecuteResultSet(commandText);
		String SQLite_Delete = "DELETE FROM EN_Employees";
		c_Query.SQLiteExecuteQuery(SQLite_Delete);
		String SQLite_Insert = "INSERT INTO EN_Employees(Name,Title,Email,Office_Phone,Mobile_Phone,Active) VALUES";
		String tmp = "";
		try{
			rs.next();
			tmp = "('" + rs.getString("Name") + "','" + rs.getString("Title") + "','" + rs.getString("Email") + "','" + rs.getString("Office_Phone") + "','" + rs.getString("Mobile_Phone") + "','" + rs.getString("Active") + "')";
			while ((rs!=null) && (rs.next()))
			{
				tmp = tmp + ",('" + rs.getString("Name") + "','" + rs.getString("Title") + "','" + rs.getString("Email") + "','" + rs.getString("Office_Phone") + "','" + rs.getString("Mobile_Phone") + "','" + rs.getString("Active") + "')"; 				
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		SQLite_Insert = SQLite_Insert + tmp;
		
		
		try
		{
			c_Query.SQLiteExecuteQuery(SQLite_Insert);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	public static void CopySunocoContacts()
	{
		String commandText = "SELECT Name, Company, Email, Mobile_Phone FROM Sunoco_Contacts ORDER BY Name asc";
		ResultSet rs = c_Query.ExecuteResultSet(commandText);
		String SQLite_Delete = "DELETE FROM Sunoco_Contacts";
		c_Query.SQLiteExecuteQuery(SQLite_Delete);
		String SQLite_Insert = "INSERT INTO Sunoco_Contacts(Name,Company,Email,Mobile_Phone) VALUES";
		String tmp = "";
		try{
			rs.next();
			tmp = "('" + rs.getString("Name") + "','" + rs.getString("Company") + "','" + rs.getString("Email") + "','" + rs.getString("Mobile_Phone") + "')";
			while ((rs!=null) && (rs.next()))
			{
				tmp = tmp + ",('" + rs.getString("Name") + "','" + rs.getString("Company") + "','" + rs.getString("Email") + "','" + rs.getString("Mobile_Phone") + "')"; 				
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		SQLite_Insert = SQLite_Insert + tmp;
		
		
		try
		{
			c_Query.SQLiteExecuteQuery(SQLite_Insert);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static void CopySites()
	{
		String commandText = "SELECT * FROM Sites";
		ResultSet rs = c_Query.ExecuteResultSet(commandText);
		String SQLite_Insert = "INSERT INTO Sites(Client,Site,State,SiteID,ClientAbbrv,SiteAbbrv,iDracIP,HostIP,ViewIP,SQLIP,DevIP,Generation,HMI,Address,Phone,Field_Tech,Field_Supervisor,Twic,Timezone,HighPerformance) VALUES";
		String tmp = "";
		try{
			rs.next();
			
			
			tmp = "('" + rs.getString("Client") + "','" + rs.getString("Site") + "','" + rs.getString("State") + "','" + rs.getString("SiteID") + "','" + rs.getString("ClientAbbrv") + "','" + rs.getString("SiteAbbrv") + "','" + rs.getString("iDracIP") + "','" + rs.getString("HostIP") + "','" + rs.getString("ViewIP") + "','" + rs.getString("SQLIP") + "','" + rs.getString("DevIP") + "','" + rs.getString("Generation") + "','" + rs.getString("HMI") + "','" + rs.getString("Address") + "','" + rs.getString("Phone") + "','" + rs.getString("Field_Tech") + "','" + rs.getString("Field_Supervisor") + "','" + rs.getString("Twic") + "','" + rs.getString("Timezone")  + "','" + rs.getString("HighPerformance") + "')";
			while ((rs!=null) && (rs.next()))
			{
				tmp = tmp + ",('" + rs.getString("Client") + "','" + rs.getString("Site") + "','" + rs.getString("State") + "','" + rs.getString("SiteID") + "','" + rs.getString("ClientAbbrv") + "','" + rs.getString("SiteAbbrv") + "','" + rs.getString("iDracIP") + "','" + rs.getString("HostIP") + "','" + rs.getString("ViewIP") + "','" + rs.getString("SQLIP") + "','" + rs.getString("DevIP") + "','" + rs.getString("Generation") + "','" + rs.getString("HMI") + "','" + rs.getString("Address") + "','" + rs.getString("Phone") + "','" + rs.getString("Field_Tech") + "','" + rs.getString("Field_Supervisor") + "','" + rs.getString("Twic") + "','" + rs.getString("Timezone")  + "','" + rs.getString("HighPerformance") + "')";
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		SQLite_Insert = SQLite_Insert + tmp;
		
		try
		{
			c_Query.SQLiteExecuteQuery(SQLite_Insert);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
	}
}