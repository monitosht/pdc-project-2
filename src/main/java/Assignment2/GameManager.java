package Assignment2;

import java.util.ArrayList;
import java.util.Random;

/**
 * @author Monitosh Thaker | 17000777
 * COMP603 Assignment 2
 */
public class GameManager 
{
    public static Player player;    
    public static GUIView gui;
    public static GameData gameDataDB;
    
    public static ArrayList<String> saves;
    public static int numSaveData = 0;
    
    public static ArrayList<Item> inventory = new ArrayList<>();
    public static ArrayList<Item> items;
    public static ArrayList<Enemy> enemies;
    
    public static Random rand = new Random();
    
    public static int act;
    public static String[] locations = {"Town Outskirts", "Dartshaw Hollow", "Hissing Forest", "City Castle", "Final Battle"};
    public static boolean levelledUp;
    
    public static Item[] buildInventoryArray()
    {
        Item[] array = new Item[inventory.size()];
        
        for(int i = 0; i < inventory.size(); i++)
            array[i] = inventory.get(i);
        
        return array;
    }
    
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
            
            if(player.getLevel() > 5) gameCompleted();
        }
    }
    
    public static void gameCompleted()
    {
        StoryHandler.displayOutro();        
        
        gui.gameCompletedPrompt();
        gameDataDB.saveGame();
    }
    
    public static void gameOver()
    {
        gameDataDB.removeSaveData();        
        
        if(gui.gameOverPrompt() == 0)
        {
            gui.createMainMenu();
        }
        else
        {
            System.exit(0);
        }
    }
    
    public static boolean checkGameCompleted()
    {
        if(act > 5)
        {
            gui.gameCompletedPrompt();
            return true;
        }
        return false;
    }
    
    public static String getCurrentLocation()
    {
        String location;
        
        if(act == 0 || act == 6)
            location = "<Town>";
        else 
            location = locations[act-1];
        
        return location;
    }
}
