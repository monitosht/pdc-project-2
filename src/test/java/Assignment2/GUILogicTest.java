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
public class GUILogicTest
{    
    //<editor-fold defaultstate="collapsed" desc="Unused Methods">    
    
    public GUILogicTest(){
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
        GameManager.gameDataDB = new GameData(); //initialise database
        
        //initialise test player with enough gold to afford a rest (and missing health to restore)
        GameManager.player = new Player("Test Player");
        GameManager.player.setCurrentHP(15);
        GameManager.player.setGold(25);
        
        //initialise a random enemy 
        CombatHandler.setRandomEnemy();
        
        //required to test methods that require GUI elements
        GameManager.gui = new GUIHandler(); 
        GameManager.gui.createMainMenu(); 
    }
    
    @After
    public void tearDown()
    {
        //to remove any unnecessary save data created through testing
        GameManager.gameDataDB.removeSaveData();
    }

    /**
     * Tests for the innArea, shopArea, adventureArea and returnEvent methods, of class GUILogic.
     * All of these methods can only be tested in a singular, very similar way due to the outcomes of their choice parameters.
     */
    @Test
    public void testInnArea() 
    {
        System.out.println("innArea");
        int choice = 2;
        
        GUILogic.position = null; //empty the current position string  
        GUILogic.innArea(choice); 
        
        //choce 2 is the exit button, the method should set the position to "Town"
        String expResult = "Town";
        String result = GUILogic.position;
        
        assertEquals(expResult, result);
    }
    @Test
    public void testShopArea() 
    {
        System.out.println("shopArea");
        int choice = 3;
        
        GUILogic.position = null; //empty the current position string          
        GUILogic.shopArea(choice);
        
        //choce 3 is the exit button, the method should set the position to "Town"
        String expResult = "Town";
        String result = GUILogic.position;
        
        assertEquals(expResult, result);
    }
    @Test
    public void testAdventureArea() 
    {
        System.out.println("adventureArea");
        int choice = 3;
        
        GUILogic.position = null; //empty the current position string
        GUILogic.adventureArea(choice);
        
        //choce 3 is the exit button, the method should set the position to "Town"
        String expResult = "Town";
        String result = GUILogic.position;
        
        assertEquals(expResult, result);
    }
    @Test
    public void testReturnEvent() 
    {
        System.out.println("returnEvent");
        int choice = 1;
        
        GUILogic.position = null; //empty the current position string       
        GUILogic.returnEvent(choice);
        
        //choice 1 confirms the return button and sends the player back to town,
        //the method should set the position as "Town"
        String expResult = "Town";
        String result = GUILogic.position;
        
        assertEquals(expResult, result);
    }

    /**
     * Test of combatArea method, of class GUILogic.
     */
    @Test
    public void testCombatArea() 
    {
        System.out.println("combatArea");
        int choice = 3;
        
        GUILogic.position = null; //empty the current position string      
        GUILogic.combatArea(choice);

        //choice 3 is the run button, which should set the position to "Continue",
        //to prompt the player with a continue button to progress
        String expResult = "Continue";
        String result = GUILogic.position;
        
        assertEquals(expResult, result);        
    }

    /**
     * Test of restEvent method, of class GUILogic.
     */
    @Test
    public void testRestEvent() 
    {
        System.out.println("restEvent");
        
        GUILogic.restEvent();
        
        boolean expResult = true;
        boolean result = (GameManager.player.getCurrentHP() == GameManager.player.getMaxHP());
        
        assertEquals(expResult, result);
    }

    /**
     * Test of continueEvent method, of class GUILogic.
     */
    @Test
    public void testContinueEvent() 
    {
        System.out.println("continueEvent");
        int choice1 = 1;
        int choice2 = 5;
        
        GUILogic.position = null; //empty the current position string                 
        
        //this method takes in two choice integers, if choice1 is 0, the continue button is enabled
        //if choice1 is 1 then the next button press will continue the game
        //the second choice determines where to progress/return to
        GUILogic.continueEvent(choice1, choice2);
        
        //with these test choices, the method should set the position to "Adventure"
        String expResult = "Adventure";
        String result = GUILogic.position;
        
        assertEquals(expResult, result);
    }
}
