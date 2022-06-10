package Assignment2;

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
public class GameDataTest 
{
    //<editor-fold defaultstate="collapsed" desc="Unused Methods">
    
    public GameDataTest(){
    }
    
    @BeforeClass
    public static void setUpClass(){
    }
    
    @AfterClass
    public static void tearDownClass(){
    }
    
    //</editor-fold>    
    
    @Before
    public void setUp() 
    {
        //required variable to test the loadPlayerData method as it creates the game scene
        GameManager.gui = new GUIHandler(); 
        
        GameManager.gameDataDB = new GameData(); //initialise database
        GameManager.player = new Player("Test Player"); //create a test player
    }
    
    @After
    public void tearDown() 
    {
        //to remove any unnecessary save data created through testing
        GameManager.gameDataDB.removeSaveData();
    }

    /**
     * Test of writePlayerSaveData method, of class GameData.
     */
    @Test
    public void testWritePlayerSaveData() 
    {
        System.out.println("writePlayerSaveData");
      
        //this method should insert the current player data into the player_data table in the database
        GameManager.gameDataDB.writePlayerSaveData();
        
        boolean expResult = true; //as data for the current player should now exist in the database
        
        //this expression checks if data for the current player exists in the database
        boolean result = GameManager.gameDataDB.checkSaveExists(GameManager.player.getName());
        
        assertEquals(expResult, result);
    }

    /**
     * Test of readPlayerSaveData method, of class GameData.
     */
    @Test
    public void testReadPlayerSaveData() 
    {
        System.out.println("readPlayerSaveData");
        
        GameManager.saves = null; //empty the arraylist
        
        //save the current player data into the database
        GameManager.gameDataDB.writePlayerSaveData();
        
        //this method should be able to read all the save data curently in the database
        //and re-populate the saves arraylist with that data
        GameManager.gameDataDB.readPlayerSaveData();
        
        boolean expResult = true; //as the method should have re-populated the arraylist
        
        //even though the arraylist was emptied, there should now be at least 1 entry        
        boolean result = (GameManager.saves.size() >= 1);
        
        assertEquals(expResult, result);
    }

    /**
     * Test of loadSaveData method, of class GameData.
     */
    @Test
    public void testLoadSaveData() 
    {
        System.out.println("loadSaveData");
        
        //initialise a new "Test Player" in case the data was already removed
        GameManager.player = new Player("Test Player"); 
        
        //save the new "Test Player" data into database
        GameManager.gameDataDB.writePlayerSaveData();
        
        //reset the player to a different player than the one previously saved
        GameManager.player = new Player("not Test Player");        
        String name = "Test Player";        
        
        //this method should now be able to load the old "Test Player" object as the currently player
        GameManager.gameDataDB.loadSaveData(name);
        
        boolean expResult = true; //as the current player should now be "Test Player" instead of "not Test Player"
        boolean result = (GameManager.player.getName().equals(name));
        
        assertEquals(expResult, result);
    }

    /**
     * Test of checkSaveExists method, of class GameData.
     */
    @Test
    public void testCheckSaveExists() 
    {
        System.out.println("checkSaveExists");
        
        //save the current player data into the database
        GameManager.gameDataDB.writePlayerSaveData();
        
        boolean expResult = true; //as save data for the current player should now exist
        boolean result = GameManager.gameDataDB.checkSaveExists(GameManager.player.getName());
        
        assertEquals(expResult, result);
    }

    /**
     * Test of removeSaveData method, of class GameData.
     */
    @Test
    public void testRemoveSaveData() 
    {
        System.out.println("removeSaveData");
        
        //this method should remove the save data of the current player from the database
        GameManager.gameDataDB.removeSaveData();
        
        boolean expResult = false; //as save data with the same name as the current player should no longer exist
        boolean result = GameManager.gameDataDB.checkSaveExists(GameManager.player.getName());
        
        assertEquals(expResult, result);
    }

    /**
     * Test of readEnemyList method, of class GameData.
     */
    @Test
    public void testReadEnemyList() 
    {
        System.out.println("readEnemyList");
        
        GameManager.enemies = null; //empty the arraylist
        
        //this method should re-populate the arraylist with the preset enemy data in the database
        GameManager.gameDataDB.readEnemyList(); 
        
        boolean expResult = true; //as the arraylist should now have a size of at least 1 enemy object
        boolean result = (GameManager.enemies.size() >= 1);
        
        assertEquals(expResult, result);
    }
}
