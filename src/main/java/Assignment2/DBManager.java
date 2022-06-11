package Assignment2;

import java.sql.*;

/**
 * @author Monitosh Thaker | 17000777
 * COMP603 Assignment 2
 */
public final class DBManager 
{
    private static final String USER_NAME = "pdc"; //username
    private static final String PASSWORD = "pdc"; //password 
    private static final String URL = "jdbc:derby:GameDataDB_Edb; create=true"; //host url
    
    Connection conn;
    
    public DBManager()
    {
        establishConnection();
    }
    
    //Establish a connection to database.
    public void establishConnection() 
    {      
        try 
        {
            conn = DriverManager.getConnection(URL, USER_NAME, PASSWORD);            
            System.out.println("connected to "+URL);
        } 
        catch(SQLException ex) 
        {
            System.err.println("SQLException: "+ex.getMessage());
        }
    }
    
    public void closeConnections() 
    {
        if (conn != null) 
        {
            try 
            {
                conn.close();
            } 
            catch (SQLException e) 
            {
                System.err.println("SQLException: "+e.getMessage());
            }
        }
    }
    
    public Connection getConnection() 
    {
        return this.conn;
    }
}
