import java.sql.*;

import javax.swing.JOptionPane;


public class c_Query {
	
public static java.sql.Connection connection;
public static Statement statement;
public static java.sql.Connection con_sqlite;
public static Statement stmt_sqlite;

			
	public static ResultSet ExecuteResultSet(String query)
	{
		//if statement to determine if offline mode???
			if(!g_MainMenu.offlineMode)
			{
			ResultSet rs = null;	
			connection = c_ConnectToDatabase.con;
			statement = c_ConnectToDatabase.stmt;
	
			//java.sql.Connection con = null;					
			try{
				
				statement = connection.createStatement(); 
		         //rs1 = statement.executeQuery(query);
				//statement = connection.createStatement();
				rs = statement.executeQuery(query);			
				
			}catch (SQLException e) {
				e.printStackTrace();
				String tmp = e.toString();			
				JOptionPane.showMessageDialog(null, "Could not execute query or no data was found \n " + "Error Message: " + tmp,"Query error",JOptionPane.ERROR_MESSAGE);
				try{
					g_SupportArchive.frmSupportArchive.dispose();
				}
				catch (Exception e1)
				{
					
				}
				
				g_MainMenu.run(null);
				
			}		
			return rs;
			}
			else
			{
				ResultSet rs = null;	
				connection = c_ConnectToDatabase.con_sqlite;
				statement = c_ConnectToDatabase.stmt_sqlite;
				//java.sql.Connection con = null;					
				try{
					
					statement = connection.createStatement(); 
			         //rs1 = statement.executeQuery(query);
					//statement = connection.createStatement();
					rs = statement.executeQuery(query);			
					
				}catch (SQLException e) {
					e.printStackTrace();
					String tmp = e.toString();			
					JOptionPane.showMessageDialog(null, "Could not execute query or no data was found \n " + "Error Message: " + tmp,"Query error",JOptionPane.ERROR_MESSAGE);
					try{
						g_SupportArchive.frmSupportArchive.dispose();
					}
					catch (Exception e1)
					{
						
					}
					
					g_MainMenu.run(null);
					
				}		
				return rs;	
			}
		}
		
		
		public static void ExecuteQuery(String query){
			if(!g_MainMenu.offlineMode)
			{
			connection = c_ConnectToDatabase.con;
			statement = c_ConnectToDatabase.stmt;
			
			try {
				statement = (Statement) ((java.sql.Connection) connection).createStatement();	
				statement.executeUpdate(query);
				
			} catch (SQLException e) {
			
				e.printStackTrace();
			}
			}
			else
			{
				connection = c_ConnectToDatabase.con_sqlite;
				statement = c_ConnectToDatabase.stmt_sqlite;
				
				try {
					statement = (Statement) ((java.sql.Connection) connection).createStatement();	
					statement.executeUpdate(query);
					
				} catch (SQLException e) {
				
					e.printStackTrace();
				}
			}
		
		}

		public static void UpdateResultSet(String query){
			if(!g_MainMenu.offlineMode)
			{
				connection = c_ConnectToDatabase.con;
				statement = c_ConnectToDatabase.stmt;
				try{
					statement = (Statement) ((java.sql.Connection) connection).createStatement();
					statement.executeUpdate(query);			
					
				}catch (SQLException e) {
					e.printStackTrace();
				}
			}
			else
			{
				connection = c_ConnectToDatabase.con_sqlite;
				statement = c_ConnectToDatabase.stmt_sqlite;
				try{
					statement = (Statement) ((java.sql.Connection) connection).createStatement();
					statement.executeUpdate(query);			
					
				}catch (SQLException e) {
					e.printStackTrace();
				}
			}
	}
		
		public static ResultSet SQLiteExecuteResultSet(String query)
		{
				ResultSet rs = null;	
				connection = c_ConnectToDatabase.con_sqlite;
				statement = c_ConnectToDatabase.stmt_sqlite;
				//java.sql.Connection con = null;					
				try{
					
					statement = connection.createStatement(); 
			         //rs1 = statement.executeQuery(query);
					//statement = connection.createStatement();
					rs = statement.executeQuery(query);			
					
				}catch (SQLException e) {
					e.printStackTrace();
					String tmp = e.toString();			
					JOptionPane.showMessageDialog(null, "Could not execute query or no data was found \n " + "Error Message: " + tmp,"Query error",JOptionPane.ERROR_MESSAGE);
					try{
						g_SupportArchive.frmSupportArchive.dispose();
					}
					catch (Exception e1)
					{
						
					}
					
					g_MainMenu.run(null);
					
				}		
				return rs;						
			}
		
		
		public static void SQLiteExecuteQuery(String query){
			connection = c_ConnectToDatabase.con_sqlite;
			statement = c_ConnectToDatabase.stmt_sqlite;
			
			try {
				statement = (Statement) ((java.sql.Connection) connection).createStatement();	
				statement.executeUpdate(query);
				
			} catch (SQLException e) {
			
				e.printStackTrace();
			}
			
		
		}

		public static void SQLiteUpdateResultSet(String query){
			
			connection = c_ConnectToDatabase.con_sqlite;
			statement = c_ConnectToDatabase.stmt_sqlite;
			try{
				statement = (Statement) ((java.sql.Connection) connection).createStatement();
				statement.executeUpdate(query);			
				
			}catch (SQLException e) {
				e.printStackTrace();
			}

	}
}
