package Assignment2;

import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * @author Monitosh Thaker | 17000777
 * COMP603 Assignment 2
 */
public class GameManagerTest 
{    
    // <editor-fold defaultstate="collapsed" desc="Unused Methods"> 
    
    public GameManagerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    // </editor-fold>
    
    @Before
    public void setUp() 
    {
        //required to test the gameOver method as the user can return to the main menu
        GameManager.gui = new GUIHandler();
        
        GameManager.gameDataDB = new GameData(); //initialise database
        GameManager.player = new Player("Test Player"); //create a test player
        GameManager.player.setXP(10); //set enough xp to be able to level up     
        
        //to test the inventory array builder
       
    }
    
    @After
    public void tearDown()
    {      
        //to remove any unnecessary save data created through testing
        GameManager.gameDataDB.removeSaveData();
    }

    /**
     * Test of levelUp method, of class GameManager.
     */
    @Test
    public void testLevelUp() 
    {
        System.out.println("levelUp");
        
        //the XP threshold has been setup to trigger a level up when the method is called
        GameManager.levelUp();
        
        int expResult = 2; //the player should level up from 1 to 2 
        int result = GameManager.player.getLevel(); //check current level
        
        assertEquals(expResult, result);
    }

    /**
     * Test of gameCompleted method, of class GameManager.
     */
    @Test
    public void testGameCompleted() 
    {
        System.out.println("gameCompleted");
        GameManager.gameCompleted();
        
        //save data for the current player should exist in the DB        
        boolean expResult = true; //as the game should automatically save when it is completed / won
        
        //use checkSaveExists method to confirm if save data for the current player exists
        boolean result = GameManager.gameDataDB.checkSaveExists(GameManager.player.getName());
        
        assertEquals(expResult, result);
    }
    
    /**
     * Test of gameOver method, of class GameManager.
     */
    @Test
    public void testGameOver() 
    {
        System.out.println("gameOver");
        GameManager.gameOver();
        
        //save data for the current player should NOT exist in the DB        
        boolean expResult = false; //as the save data should be deleted if the game is over / lost
        
        //use checkSaveExists method to confirm if save data for the current player exists
        boolean result = GameManager.gameDataDB.checkSaveExists(GameManager.player.getName());
        
        assertEquals(expResult, result);
    }

    /**
     * Test of checkGameCompleted method, of class GameManager.
     */
    @Test
    public void testCheckGameCompleted() 
    {
        System.out.println("checkGameCompleted");
        
        //the current player should be level 2 at this stage
        boolean expResult = false; //as for the game to be completed the player must be level 6
        boolean result = GameManager.checkGameCompleted();
        
        assertEquals(expResult, result);
    }

    /**
     * Test of getCurrentLocation method, of class GameManager.
     */
    @Test
    public void testGetCurrentLocation() 
    {
        System.out.println("getCurrentLocation");
        
        //as the story act is equal to the player level, it should be 2 at this stage
        String expResult = "Dartshaw Hollow"; //the location for act 2 of the game
        
        //the method returns the location string at the index of the current act value
        String result = GameManager.getCurrentLocation();
        
        assertEquals(expResult, result);
    }    
}
