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
public class NewGameLogicTest 
{
    //<editor-fold defaultstate="collapsed" desc="Unused Methods">
        
    public NewGameLogicTest(){
    }
    
    @BeforeClass
    public static void setUpClass(){
    }
    
    @AfterClass
    public static void tearDownClass(){
    }    
    
    @After
    public void tearDown(){      
    }
    
    //</editor-fold>
    
    @Before
    public void setUp() 
    {
        //initialise GUI for the methods that require it
        GUIHandler.createWindow(); 
        GUIHandler.createMainMenu(); 
        GUIHandler.characterCreation(0); 
        GUIHandler.characterCreation(1); 
    }

    /**
     * Test of createPlayer method, of class NewGameLogic.
     */
    @Test
    public void testCreatePlayer() 
    {
        System.out.println("createPlayer");
        
        String name = "Test Player";
        int points = 10;
        
        NewGameLogic.newPlayer = null; //ensure the player is initially empty
        
        //the method should initialise the player using the test variables
        NewGameLogic.createPlayer(name, points);
        
        boolean expResult = true; //the newly created player should match the test variables
        boolean result = (NewGameLogic.newPlayer.getName().equals(name)) && (NewGameLogic.points == points);
        
        assertEquals(expResult, result);
    }

    /**
     * Test of updateAttribute method, of class NewGameLogic.
     */
    @Test
    public void testUpdateAttribute() 
    {
        System.out.println("updateAttribute");
        
        for(int i =0; i < 5; i++) //increment the str 5 times (+5)
            NewGameLogic.updateAttribute(1, "str");
        
        NewGameLogic.updateAttribute(0, "str"); //decrease the str once (-1)
        
        for(int i =0; i < 10; i++) //try to increment the def 10 times (+10)
            NewGameLogic.updateAttribute(1, "def");
         
        //the method should not allow the def to exceed 6 as all of the points will have been used up        
        
        boolean expResult = true; //as the multiple method calls should result in the str being 4 and the def being 6
        boolean result = (NewGameLogic.strValue == 4) && (NewGameLogic.defValue == 6);
        
        assertEquals(expResult, result);
    }

    /**
     * Test of setPlayer method, of class NewGameLogic.
     */
    @Test
    public void testSetPlayer() 
    {
        System.out.println("setPlayer");
        
        GameManager.player = null; //ensure the main player is empty
        //the method should initialise the main player as the new player created using this class (same name and attributes)
        NewGameLogic.setPlayer();
        
        boolean expResult = true; //as the main player should have the same values that were passed into the new player during the previous two tests
        boolean result = GameManager.player.getName().equals("Test Player") && GameManager.player.getStrength() == 4 && GameManager.player.getDefence() == 6;
        
        assertEquals(expResult, result);
    }
}
