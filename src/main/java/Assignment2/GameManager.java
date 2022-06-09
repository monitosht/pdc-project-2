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
    
    public static GameData gameDataDB;
    
    public static ArrayList<String> saves;
    public static int numSaveData = 0;
    
    public static ArrayList<Item> inventory = new ArrayList<>();
    public static ArrayList<Item> items;
    public static ArrayList<Enemy> enemies;
    
    public static Random rand = new Random();
    
    public static int act = 1;
    
    static void levelUp()
    {
        if(player.getXP() > (player.getLevel()*4))
        {
            System.out.println("Checking for level up...");
            //level up
            //set xp to 0
            //print text
            //change act
        }
    }
    
    static void gameOver()
    {
        System.out.println("Game Over!");
        //prompt text
        //remove save from database
        //restart game
    }
}
