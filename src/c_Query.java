import java.sql.*;

import javax.swing.JOptionPane;


public class c_Query {
	
public static java.sql.Connection connection;
public static Statement statement;
public static java.sql.Connection con_sqlite;
public static Statement stmt_sqlite;
		
	public static ResultSet ExecuteResultSet(String query)
	{
		try {
			if(!c_ConnectToDatabase.con.isValid(0))
			{
				try
				{
					c_ConnectToDatabase.Connect();
				}
				catch(Exception e)
				{
					JOptionPane.showMessageDialog(null, e.toString() + " " + JOptionPane.ERROR_MESSAGE);
				}
				
			}
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
			
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
				
				g_MainMenu.run(null);
				
			}		
			
			return rs;
	}
		
		
		public static void ExecuteQuery(String query)
		{		
			try {
				if(!c_ConnectToDatabase.con.isValid(0))
				{
					try
					{
						c_ConnectToDatabase.Connect();
					}
					catch(Exception e)
					{						
						JOptionPane.showMessageDialog(null, e.toString() + " " + JOptionPane.ERROR_MESSAGE);
					}
					
				}
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			connection = c_ConnectToDatabase.con;
			statement = c_ConnectToDatabase.stmt;
			
			try {
				statement = (Statement) ((java.sql.Connection) connection).createStatement();	
				statement.executeUpdate(query);
				
			} catch (SQLException e) {
			
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, e.toString() + " " + JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			
		}

		public static void UpdateResultSet(String query)
		{			
			try {
				if(!c_ConnectToDatabase.con.isValid(0))
				{
					try
					{
						c_ConnectToDatabase.Connect();
					}
					catch(Exception e)
					{
						System.out.println(e.toString());
					}
					
				}
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
				connection = c_ConnectToDatabase.con;
				statement = c_ConnectToDatabase.stmt;
				try{
					statement = (Statement) ((java.sql.Connection) connection).createStatement();
					statement.executeUpdate(query);			
					
				}catch (SQLException e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, e.toString() + " " + JOptionPane.ERROR_MESSAGE);
				}
				
				
		}
	
		
		public static ResultSet SQLiteExecuteResultSet(String query)
		{
			try {
				if(!c_ConnectToDatabase.con.isValid(0))
				{
					try
					{
						c_ConnectToDatabase.Connect();
					}
					catch(Exception e)
					{
						JOptionPane.showMessageDialog(null, e.toString() + " " + JOptionPane.ERROR_MESSAGE);
					}
					
				}
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
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
					
					
					g_MainMenu.run(null);
					
				}		
				
				
				return rs;						
			}	
		
		public static void CloseConnection()
		{
			try {
				c_ConnectToDatabase.con_sqlite.close();
				c_ConnectToDatabase.stmt_sqlite.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, e.toString() + " " + JOptionPane.ERROR_MESSAGE);
			}
			
		}
	
}
