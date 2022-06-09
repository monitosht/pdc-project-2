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
    
    public static ArrayList<Item> inventory = new ArrayList<>();
    public static ArrayList<Item> items;
    public static ArrayList<Enemy> enemies;
    
    public static Random rand = new Random();
    
    public static int act = 1;
}
