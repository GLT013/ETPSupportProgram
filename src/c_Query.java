import java.sql.*;

import javax.swing.JOptionPane;


public class c_Query {
	
public static java.sql.Connection connection;
public static Statement statement;

			
		public static ResultSet ExecuteResultSet(String query){
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
		
		
		public static void ExecuteQuery(String query){
			connection = c_ConnectToDatabase.con;
			statement = c_ConnectToDatabase.stmt;
			
			try {
				statement = (Statement) ((java.sql.Connection) connection).createStatement();	
				statement.executeUpdate(query);
				
			} catch (SQLException e) {
			
				e.printStackTrace();
			}
			
		
		}

		public static void UpdateResultSet(String query){
			
			connection = c_ConnectToDatabase.con;
			statement = c_ConnectToDatabase.stmt;
			try{
				statement = (Statement) ((java.sql.Connection) connection).createStatement();
				statement.executeUpdate(query);			
				
			}catch (SQLException e) {
				e.printStackTrace();
			}
			

			
	}
}
