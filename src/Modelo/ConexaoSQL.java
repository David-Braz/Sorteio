package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoSQL {
	public static Connection getConnection() throws SQLException{ 
		String url = "jdbc:sqlserver://localhost:1433;databaseName=Sorteio;user=sa";
        Connection c = null; 
      try 
      { 
         Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");		         
         c = DriverManager.getConnection(url); 
      } 
      catch(ClassNotFoundException err) { 
         err.printStackTrace(); 
      } 
      return c; 
   } 
}



