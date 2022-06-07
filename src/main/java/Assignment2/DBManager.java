package Assignment2;

/**
 * @author Monitosh Thaker | 17000777
 * COMP603 Assignment 2
 */

import java.sql.*;

public final class DBManager 
{
    private static final String USER_NAME = "pdc"; //your DB username
    private static final String PASSWORD = "pdc"; //your DB password
    private static final String URL = "jdbc:derby:PlayerStatsDB_Ebd; create=true";  //url of the DB host
    
    Connection conn;
    
    public DBManager()
    {
        establishConnection();
    }
    
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
    
    public Connection getConnection() 
    {
        return this.conn;
    }
}
