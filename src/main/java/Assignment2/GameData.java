package Assignment2;

import java.sql.*;
import static Assignment2.GameManager.player;

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
        try
        {
            ResultSet rs = null;
            
            statement = conn.createStatement();
            
            String query = "SELECT * FROM player_data WHERE name = '"+player.getName()+"'";
            rs = statement.executeQuery(query);
            
            if(rs.next()) //player with same name already exists
            {
                System.out.println("Save data \""+player.getName()+"\" exists.");
                
                //ask to overrite                
                if(SetupGUI.createSavePrompt() == 0)
                {
                    //if yes, update query
                    query = "UPDATE player_data SET "
                        +"current_HP =  "+player.getCurrentHP()+","
                        +"max_HP     =  "+player.getMaxHP()+","
                        +"strength   =  "+player.getStrength()+","
                        +"intellect  =  "+player.getIntellect()+","
                        +"defence    =  "+player.getDefence()+","
                        +"xp         =  "+player.getXP()+","
                        +"level      =  "+player.getLevel()+","
                        +"gold       =  "+player.getGold()+" "
                        +"WHERE name = '"+player.getName()+"'";
                
                        statement.executeUpdate(query);
                        GameplayGUI.updateMainTextArea("Game Saved! (Overwritten)");
                }
                else //if no return
                {
                    GameplayGUI.updateMainTextArea("The save data was not overwritten.");
                }  
            }
            else //save data does not exist yet
            {
                System.out.println("save data does not exist");
                
                query = "INSERT INTO player_data VALUES ('"
                        +player.getName()+"',"
                        +player.getCurrentHP()+","
                        +player.getMaxHP()+","
                        +player.getStrength()+","
                        +player.getIntellect()+","
                        +player.getDefence()+","
                        +player.getXP()+","
                        +player.getLevel()+","
                        +player.getGold()+")";
                
                statement.executeUpdate(query);
                GameplayGUI.updateMainTextArea("Game Saved!");
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
