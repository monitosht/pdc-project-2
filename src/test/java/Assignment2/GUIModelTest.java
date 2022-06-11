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
public class GUIModelTest
{    
    //<editor-fold defaultstate="collapsed" desc="Unused Methods">    
    
    public GUIModelTest(){
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
        
        //initialise test player with some missing HP to be restored in the restEvent test
        GameManager.player = new Player("Test Player");
        GameManager.player.setCurrentHP(15);
        
        //initialise a random enemy 
        CombatHandler.setRandomEnemy();
        
        //required to test methods that require GUI elements
        GameManager.gui = new GUIView(); 
        GameManager.gui.createGameScene();
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
        
        GUIModel.position = null; //empty the current position string  
        GUIModel.innArea(choice); 
        
        //choce 2 is the exit button, the method should set the position to "Town"
        String expResult = "Town";
        String result = GUIModel.position;
        
        assertEquals(expResult, result);
    }
    @Test
    public void testShopArea() 
    {
        System.out.println("shopArea");
        int choice = 3;
        
        GUIModel.position = null; //empty the current position string          
        GUIModel.shopArea(choice);
        
        //choce 3 is the exit button, the method should set the position to "Town"
        String expResult = "Town";
        String result = GUIModel.position;
        
        assertEquals(expResult, result);
    }
    @Test
    public void testAdventureArea() 
    {
        System.out.println("adventureArea");
        int choice = 3;
        
        GUIModel.position = null; //empty the current position string
        GUIModel.adventureArea(choice);
        
        //choce 3 is the exit button, the method should set the position to "Town"
        String expResult = "Town";
        String result = GUIModel.position;
        
        assertEquals(expResult, result);
    }
    @Test
    public void testReturnEvent() 
    {
        System.out.println("returnEvent");
        int choice = 1;
        
        GUIModel.position = null; //empty the current position string       
        GUIModel.returnEvent(choice);
        
        //choice 1 confirms the return button and sends the player back to town,
        //the method should set the position as "Town"
        String expResult = "Town";
        String result = GUIModel.position;
        
        assertEquals(expResult, result);
    }

    /**
     * Test of combatArea method, of class GUIModel.
     */
    @Test
    public void testCombatArea() 
    {
        System.out.println("combatArea");
        int choice = 3;
        
        GUIModel.position = null; //empty the current position string      
        GUIModel.combatArea(choice);

        //choice 3 is the run button, which should set the position to "Continue",
        //to prompt the player with a continue button to progress
        String expResult = "Continue";
        String result = GUIModel.position;
        
        assertEquals(expResult, result);        
    }

    /**
     * Test of restEvent method, of class GUIModel.
     */
    @Test
    public void testRestEvent() 
    {
        System.out.println("restEvent");
        
        GameManager.player.setGold(25); //set enough gold to afford the rest
        GUIModel.restEvent();
        
        boolean expResult = true; //as the player should be restored to full health
        boolean result = (GameManager.player.getCurrentHP() == GameManager.player.getMaxHP());
        
        assertEquals(expResult, result);
    }

    /**
     * Test of continueEvent method, of class GUIModel.
     */
    @Test
    public void testContinueEvent() 
    {
        System.out.println("continueEvent");
        int choice = 5;
        
        GUIModel.position = null; //empty the current position string                 
        
        //if choice is 0 then the next button press will continue the game
        //a non-0 choice determines where to progress/return to
        GUIModel.continueEvent(5);
        
        //with these test choices, the method should set the position to "Adventure"
        String expResult = "Adventure";
        String result = GUIModel.position;
        
        assertEquals(expResult, result);
    }

    /**
     * Test of buyItemEvent method, of class GUIModel.
     */
    @Test
    public void testBuyItemEvent() 
    {
        System.out.println("buyItemEvent");
        
        GameManager.player.setGold(15); //set enough gold to purcahse item
        Item item = new Item("Test Item", "c", 5, 10); //initialise a test item
        
        GUIModel.buyItemEvent(item);
        
        int expResult = 5; //as 10 gold should be subtracted from the initial 15 to account for the item cost
        int result = GameManager.player.getGold();
        
        assertEquals(expResult, result);
    }

    /**
     * Test of sellItemEvent method, of class GUIModel.
     */
    @Test
    public void testSellItemEvent() 
    {
        System.out.println("sellItemEvent");
        
        GameManager.player.setGold(0); //initialise gold to 0
        Item item = new Item("Test Item", "c", 5, 10); //initialise test item
        
        GUIModel.sellItemEvent(item);
        
        int expResult = 10; //as the player shoulve gained gold equal to the test item sell price (10)
        int result = GameManager.player.getGold();
        
        assertEquals(expResult, result);
    }
}
