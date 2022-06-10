package Assignment2;

import java.sql.*;
import java.util.ArrayList;
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
            ResultSet rs;
            
            statement = conn.createStatement();
            
            String query = "SELECT * FROM player_data WHERE name = '"+player.getName()+"'";
            rs = statement.executeQuery(query);
            
            if(rs.next()) //player with same name already exists
            {
                //ask to overrite                
                if(GameManager.gui.savePrompt() == 0)
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
                        GUIUpdate.updateMainTextArea("Game save updated!");
                }
                else //if no return
                {
                    GUIUpdate.updateMainTextArea("Save data was not overwritten.");
                    System.out.println("did not save");
                }  
            }
            else //save data does not exist yet
            {
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
        }
        catch(SQLException e)
        {
            System.err.println("SQLException: " + e.getMessage());
        }
    }
    
    public void loadSaveData(String name)
    {
        try
        {
            ResultSet rs = null;
            
            statement = conn.createStatement();
            
            String query = "SELECT * FROM player_data WHERE name = '"+name+"'";
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
                GameManager.act = GameManager.player.getLevel();
                
                GameManager.gui.createGameScene(); 
            }
        }
        catch(SQLException e)
        {
            System.err.println("SQLException: " + e.getMessage());
        }            
    }
    
    public boolean checkSaveExists(String name)
    {
        try
        {
            ResultSet rs = null;
            
            statement = conn.createStatement();
            
            String query = "SELECT * FROM player_data WHERE name = '"+name+"'";
            rs = statement.executeQuery(query);
            
            if(rs.next())
            {
                System.out.println("save data with that name exists");
                return true;
            }
            else
            {
                System.out.println("save data with that name does NOT exist");
                return false;
            }
        }
        catch(SQLException e)
        {
            System.err.println("SQLException: " + e.getMessage());
        }      
        return false;
    }        
    
    public void removeSaveData()
    {        
        try
        {
            statement = conn.createStatement();
            
            String query = "DELETE FROM player_data WHERE name = '"+player.getName()+"'";
            statement.executeUpdate(query); 
        }
        catch(SQLException e)
        {
            System.err.println("SQLException: " + e.getMessage());
        }
    }
    
    public void readItemList()
    {
       GameManager.items = new ArrayList<>();
        
        try
        {
            ResultSet rs;
            
            statement = conn.createStatement();
            
            String query = "SELECT * FROM item_list";
            rs = statement.executeQuery(query);
            
            while(rs.next())
            {                             
                String name = rs.getString("item_name");
                String type = rs.getString("type");
                int value = rs.getInt("effect_value");
                int price = rs.getInt("buy_price");
                
                Item item = new Item(name, type, value, price);
                GameManager.items.add(item);
            }
        }
        catch(SQLException e)
        {
            System.err.println("SQLException: " + e.getMessage());
        } 
    }
    
    public void readEnemyList()
    {
        GameManager.enemies = new ArrayList<>();
        
        try
        {
            ResultSet rs;
            
            statement = conn.createStatement();
            
            String query = "SELECT * FROM enemy_list";
            rs = statement.executeQuery(query);
            
            while(rs.next())
            {                             
                String name = rs.getString("enemy_name");
                int maxHP = rs.getInt("max_HP");
                int strength = rs.getInt("strength");
                int intellect = rs.getInt("intellect");
                int defence = rs.getInt("defence");
                int xp = rs.getInt("xp");
                int level = rs.getInt("level");
                int reward = rs.getInt("reward");
                
                Enemy enemy = new Enemy(name, maxHP, strength, intellect, defence, xp, level, reward);
                GameManager.enemies.add(enemy);
            }
        }
        catch(SQLException e)
        {
            System.err.println("SQLException: " + e.getMessage());
        }
    }
    
    public void readInventory()
    {
        GameManager.inventory = new ArrayList<>();
        
        try
        {
            ResultSet rs;
            
            statement = conn.createStatement();
            
            String query = "SELECT * FROM inventory";
            rs = statement.executeQuery(query);
            
            while(rs.next())
            {
                String name = rs.getString("item_name");
                String type = rs.getString("type");
                int value = rs.getInt("effect_value");
                int price = rs.getInt("sell_price");
                
                Item item = new Item(name, type, value, price);
                GameManager.inventory.add(item);
            }
        }
        catch(SQLException e)
        {
            System.err.println("SQLException: " + e.getMessage());
        }
    }
    
    public void writeInventory()
    {
        try
        {
            statement = conn.createStatement();
            
            String query = "DELETE FROM inventory";            
            statement.executeUpdate(query);
            
            query = "";
            
            for(Item i : GameManager.inventory)
            {
                query += "INSERT INTO inventory VALUES ('"
                    +i.getName()+"','"
                    +i.getType()+"',"
                    +i.getValue()+","
                    +i.getPrice()+")";
                statement.executeUpdate(query);                
            }
        }
        catch(SQLException e)
        {
            System.err.println("SQLException: " + e.getMessage());
        }
    }
    
    public void saveGame()
    {
        writePlayerSaveData();
        writeInventory();
    }
    
    public void closeConnection() 
    {
        this.dbManager.closeConnections();
    }
}
