package Assignment2;

/**
 * @author Monitosh Thaker | 17000777
 * COMP603 Assignment 2
 */

import java.sql.*;

public class GameData 
{
    private final DBManager dbManager;
    private final Connection conn;
    private Statement statement;
    
    GameData()
    {
        dbManager = new DBManager();
        conn = dbManager.getConnection();
    }
    
    public void getAllStats()
    {
        try 
        {
            statement = conn.createStatement();        
            String query = "SELECT * FROM PLAYER_STATS";
            statement.executeUpdate(query);
        } 
        catch(SQLException ex) 
        {
            System.err.println("SQLException: "+ex.getMessage());
        }        
    }
}
