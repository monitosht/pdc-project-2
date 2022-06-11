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
    
    //Establish a new connection to the database and create a statement.
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
        //Create the player_data table, only used to initialise the derby embedded database for the first time.
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
            
            if(rs.next()) //Player with the same name exists.
            {
                //Check if the user wants to overwrite the save data.               
                if(GameManager.gui.savePrompt() == 0)
                {
                    //If yes, update the entry in the database with the current player's data.
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
                else //If no, return.
                {
                    GUIUpdate.updateMainTextArea("Save data was not overwritten.");
                    System.out.println("did not save");
                }  
            }
            else //Save data does not yet exist.
            {
                //Save the data into the database using an insert query.
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
            
            ResultSet rs;            
            statement = conn.createStatement();
            
            String query = "SELECT * FROM player_data";
            rs = statement.executeQuery(query);
            
            while(rs.next()) //Add the names of all players currently saved in the database to the ArrayList.
            {
                String name = rs.getString("name");
                GameManager.saves.add(name);
            }
            
            GameManager.numSaveData = GameManager.saves.size(); //Record the total number of save files for GUI purposes.
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
            ResultSet rs;            
            statement = conn.createStatement();
            
            String query = "SELECT * FROM player_data WHERE name = '"+name+"'";
            rs = statement.executeQuery(query);
            
            if(rs.next()) //Build the loaded data into a Player object.
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
                
                GameManager.player = loadedPlayer; //Set the loaded player as the main/current player for this session.
                GameManager.act = GameManager.player.getLevel(); //Start the correct story act for the newly loaded player.
                
                GameManager.gui.createGameScene(); //Begin gameplay with the newly loaded player.
            }
        }
        catch(SQLException e)
        {
            System.err.println("SQLException: " + e.getMessage());
        }            
    }    
           
    //Deletes save data from the database corresponding to the current player.
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
       GameManager.itemList = new ArrayList<>();
        
        try
        {
            ResultSet rs;            
            statement = conn.createStatement();
            
            String query = "SELECT * FROM item_list";
            rs = statement.executeQuery(query);
            
            while(rs.next()) //Add each item in the item_list table to the ArrayList.
            {                             
                String name = rs.getString("item_name");
                String type = rs.getString("type");
                int value = rs.getInt("effect_value");
                int price = rs.getInt("buy_price");
                
                Item item = new Item(name, type, value, price); //Format the table entry to an Item object.
                GameManager.itemList.add(item);
            }
        }
        catch(SQLException e)
        {
            System.err.println("SQLException: " + e.getMessage());
        } 
    }
    
    public void readEnemyList()
    {
        GameManager.enemyList = new ArrayList<>(); //Initialise ArrayList.
        
        try
        {
            ResultSet rs;            
            statement = conn.createStatement();
            
            String query = "SELECT * FROM enemy_list";
            rs = statement.executeQuery(query);
            
            while(rs.next()) //Add each enemy in the enemy_data table to the ArrayList.
            {                             
                String name = rs.getString("enemy_name");
                int maxHP = rs.getInt("max_HP");
                int strength = rs.getInt("strength");
                int intellect = rs.getInt("intellect");
                int defence = rs.getInt("defence");
                int xp = rs.getInt("xp");
                int level = rs.getInt("level");
                int reward = rs.getInt("reward");
                
                Enemy enemy = new Enemy(name, maxHP, strength, intellect, defence, xp, level, reward); //Format the table entry to an Enemy object.
                GameManager.enemyList.add(enemy);
            }
        }
        catch(SQLException e)
        {
            System.err.println("SQLException: " + e.getMessage());
        }
    }
    
    public void readInventory()
    {
        GameManager.inventory = new ArrayList<>(); //Initialise ArrayList.
        
        try
        {
            ResultSet rs;            
            statement = conn.createStatement();
            
            String query = "SELECT * FROM inventory";
            rs = statement.executeQuery(query);
            
            while(rs.next()) //Add all items in the inventory table to the ArrayList.
            {
                String name = rs.getString("item_name");
                String type = rs.getString("type");
                int value = rs.getInt("effect_value");
                int price = rs.getInt("sell_price");
                
                Item item = new Item(name, type, value, price); //Format the table entry to an Item object.
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
            clearInventory(); //Empty the inventory to avoid repeat entries.
            
            for(Item i : GameManager.inventory) //Insert data into the table for each item currently in the inventory.
            {
                String query = "";
                
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
    
    public void closeConnection() 
    {
        this.dbManager.closeConnections();
    }
    
    //Convinience methods
    public boolean checkSaveExists(String name)
    {
        try
        {
            ResultSet rs;            
            statement = conn.createStatement();
            
            String query = "SELECT * FROM player_data WHERE name = '"+name+"'";
            rs = statement.executeQuery(query);
            
            if(rs.next())
            {
                //System.out.println("save data with that name exists");
                return true;
            }
            else
            {
                //System.out.println("save data with that name does NOT exist");
                return false;
            }
        }
        catch(SQLException e)
        {
            System.err.println("SQLException: " + e.getMessage());
        }      
        return false;
    } 
    
    public void clearInventory()
    {
        try
        {
            statement = conn.createStatement();
            
            String query = "DELETE FROM inventory";            
            statement.executeUpdate(query);
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
}
