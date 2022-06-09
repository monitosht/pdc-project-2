package Assignment2;

import java.sql.*;

/**
 * @author Monitosh Thaker | 17000777
 * COMP603 Assignment 2
 */
public class GameData 
{
    private final DBManager dbManager;
    private final Connection conn;
    private Statement statement;
    
    GameData()
    {
        dbManager = new DBManager();
        conn = dbManager.getConnection();
        
        try 
        {
            statement = conn.createStatement();
        } 
        catch (SQLException e) 
        {
            System.err.println("SQLException: " + e.getMessage());
        }
    }
    
    public static void main(String[] args) 
    {
        GameData data = new GameData();

        try 
        {
            data.statement.addBatch("CREATE TABLE player_data (name VARCHAR(50), current_HP INT, max_HP INT, strength INT, intellect INT, defence INT, xp INT, level INT, gold INT)");
            data.statement.addBatch("INSERT INTO player_data VALUES ('Moni',15,20,7,0,3,3,1,25)");
            data.statement.executeBatch();
        } 
        catch (SQLException e) 
        {
            System.err.println("SQLException: " + e.getMessage());
        }
        
        data.closeConnection();
    }
    
    public void writePlayerSaveData()
    {
        ResultSet rs = null;
        
        try
        {
            statement = conn.createStatement();
            
            //DatabaseMetaData md = conn.getMetaData();
            //ResultSet rs = md.getColumns(null, null, "player_data", "");
            
            String query = "SELECT * FROM player_data WHERE name = "+GameManager.player.getName();
            rs = statement.executeQuery(query);
            
            if(rs.next()) //player with same name already exists
            {
                System.out.println("Save data \""+GameManager.player.getName()+"\" exists.");
            }
            else //save data does not exist yet
            {
                System.out.println("save data does not exist");
                //query = "INSERT INTO player_data VALUES (";
                //statement.executeUpdate(query);
            }
        }
        catch(SQLException e)
        {
            System.err.println("SQLException: " + e.getMessage());
        }
    }
    
    public void closeConnection() 
    {
        this.dbManager.closeConnections();
    }
}
