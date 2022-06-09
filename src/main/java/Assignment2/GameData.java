package Assignment2;

import java.sql.*;
import static Assignment2.GameManager.player;
import java.util.ArrayList;

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
                        GameplayGUI.updateMainTextArea("Game save updated!");
                }
                else //if no return
                {
                    GameplayGUI.updateMainTextArea("Save data was not overwritten.");
                    System.out.println("did not save");
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
                GameplayGUI.updateMainTextArea("Game saved!");
            }
        }
        catch(SQLException e)
        {
            System.err.println("SQLException: " + e.getMessage());
        }
    }
    
    public void readPlayerSaveData()
    {
        try
        {
            GameManager.saves = new ArrayList<>();
            ResultSet rs = null;
            
            statement = conn.createStatement();
            
            String query = "SELECT * FROM player_data";
            rs = statement.executeQuery(query);
            
            while(rs.next())
            {
                String name = rs.getString("name");
                GameManager.saves.add(name);
            }
            
            GameManager.numSaveData = GameManager.saves.size();
            
            for(int i=0;i<GameManager.saves.size();i++)
            {
                System.out.println(GameManager.saves.get(i));                
            }
        }
        catch(SQLException e)
        {
            System.err.println("SQLException: " + e.getMessage());
        }
    }
    
    public void loadSaveData(String saveName)
    {
        try
        {
            ResultSet rs = null;
            
            statement = conn.createStatement();
            
            String query = "SELECT * FROM player_data WHERE name = '"+saveName+"'";
            rs = statement.executeQuery(query);
            
            if(rs.next()) //build loaded player 
            {
                Player loadedPlayer = new Player(rs.getString("name"));                
                
                loadedPlayer.setCurrentHP(rs.getInt("current_HP"));
                loadedPlayer.setMaxHP(rs.getInt("max_HP"));
                
                loadedPlayer.setStrength(rs.getInt("strength"));
                loadedPlayer.setIntellect(rs.getInt("intellect"));
                loadedPlayer.setDefence(rs.getInt("defence"));
                
                loadedPlayer.setXP(rs.getInt("xp"));
                loadedPlayer.setLevel(rs.getInt("level"));
                loadedPlayer.setGold(rs.getInt("gold"));
                
                GameManager.player = loadedPlayer;                
                System.out.println(GameManager.player.getName() +" "+GameManager.player.getStrength());
                
                SetupGUI.createGameScene(); 
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
