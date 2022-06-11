package Assignment2;

import java.util.ArrayList;
import java.util.Random;

/**
 * @author Monitosh Thaker | 17000777
 * COMP603 Assignment 2
 */
public class GameManager 
{
    //Global reference to random.
    public static Random rand = new Random();
    
    //Global variables mandatory for gameplay.
    public static Player player;    
    public static GUIView gui;
    public static GameData gameDataDB;
    
    //ArrayLists for database reading/writing purposes.
    public static ArrayList<String> saves;
    public static int numSaveData = 0;
    
    public static ArrayList<Item> inventory = new ArrayList<>();
    public static ArrayList<Item> itemList;
    public static ArrayList<Enemy> enemyList;
    
    //Story manager variables.
    public static int act;
    public static String[] locations = {"Town Outskirts", "Dartshaw Hollow", "Hissing Forest", "City Castle", "Final Battle"};
    public static boolean levelledUp;
    
    //Formats the inventory ArrayList to be read as options by a GUI dialog prompt.
    public static Item[] buildInventoryArray() 
    {
        Item[] array = new Item[inventory.size()];
        
        for(int i = 0; i < inventory.size(); i++)
            array[i] = inventory.get(i);
        
        return array;
    }
    
    //If the player has enough XP to level up, increase all stats and progress the story.
    public static void levelUp() 
    {
        if(player.getXP() >= (player.getLevel()*10))
        {       
            player.setLevel(player.getLevel()+1);
            player.setMaxHP(player.getMaxHP() + 5);
            player.setStrength(player.getStrength() + 2);
            player.setIntellect(player.getIntellect() + 2);
            player.setDefence(player.getDefence() + 2);            
            player.setXP(0);
            
            GUIUpdate.updateCombatTextArea("You levelled up!");
            
            GUIUpdate.updateCombatTextArea(
                "[ LEVEL ]      "+(player.getLevel() - 1)+" >> "+player.getLevel()
             +"\n[ MAX HP ]   "+(player.getMaxHP() - 5)+" >> "+player.getMaxHP()
             +"\n[ STRENGTH ] "+(player.getStrength() - 2)+" >> "+player.getStrength()
             +"\n[ INTELLECT ] "+(player.getIntellect() - 2)+" >> "+player.getIntellect()
             +"\n[ DEFENCE ]   "+(player.getDefence() - 2)+" >> "+player.getDefence());
            
            levelledUp = true;
            act = player.getLevel();
            
            if(player.getLevel() > 5) gameCompleted(); //If the player has reached the max level, the game is completed.
        }
    }
    
    public static void gameCompleted()
    {
        gameDataDB.saveGame(); //Automatically save to the database.
        
        StoryHandler.displayOutro();                
        gui.gameCompletedPrompt(); 
        
    }
    
    public static void gameOver()
    {
        gameDataDB.removeSaveData(); //Delete save file from the database. 
        
        if(gui.gameOverPrompt() == 0) //Read the choice from the dialog prompt.
        {
            gui.createMainMenu();
        }
        else
        {
            System.exit(0);
        }
    }
    
    public static boolean checkGameCompleted() //Stops the player from progressing when the game is already complete.
    {
        if(act > 5)
        {
            gui.gameCompletedPrompt();
            return true;
        }
        return false;
    }
    
    public static String getCurrentLocation() //Calculates and returns the current location from locations[] using the current act.
    {
        String location;
        
        if(act == 0 || act == 6)
            location = "<Town>";
        else 
            location = locations[act-1];
        
        return location;
    }
}
