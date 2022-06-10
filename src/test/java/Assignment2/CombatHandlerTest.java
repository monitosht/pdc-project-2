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
public class CombatHandlerTest 
{           
    //<editor-fold defaultstate="collapsed" desc="Unused Methods">
    
    public CombatHandlerTest(){        
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
        GameManager.gui = new GUIHandler();
        GameManager.gameDataDB = new GameData(); //initialise database
        GameManager.player = new Player("Test Player"); //create test player
        CombatHandler.setRandomEnemy(); //set the curentEnemy to a viable random enemy
    }
    
    /**
     * Test of setRandomEnemy method, of class CombatHandler.
     */
    @Test
    public void testSetRandomEnemy() 
    {
        System.out.println("setRandomEnemy");
        
        CombatHandler.currentEnemy = null; //reset the currentEnemy for this test case
        CombatHandler.setRandomEnemy(); //reset the currentEnemy to a new enemy using this method
        
        boolean expResult = true; //as currentEnemy should now contain an enemy object
        boolean result = (CombatHandler.currentEnemy != null); //check if currentEnemy is null
        
        assertEquals(expResult, result);
    }

    /**
     * Test of combatEvent method, of class CombatHandler.
     */
    @Test
    public void testCombatEvent() 
    {
        System.out.println("combatEvent");
        
        //for this method, true return indicates that the player or enemy has died in this turn of combat
        //a false return indicates that both parties have attacked and the combat will continue
        boolean expResult = false; //as this would mean that neither the player or enemy have died
        
        //it's not possible for a combatEvent (called for the first time) between a new player and a level 1 enemy to return true
        boolean result = CombatHandler.combatEvent(CombatHandler.currentEnemy);
        
        assertEquals(expResult, result);
    }

    /**
     * Test of runFromCombat method, of class CombatHandler.
     */    
    @Test
    public void testRunFromCombat() 
    {        
        System.out.println("runFromCombat");
        
        boolean temp;        
        
        //ensure the method returns false
        do{            
            //false means that the player was unsuccessful in escaping combat
            //when this happens, the player will have taken at least 1 damage
            temp = CombatHandler.runFromCombat(CombatHandler.currentEnemy);
        }while(temp);
        
        
        boolean expResult = true; //as the player would have taken damage if the method worked correctly
        //check if the player's health has decreased, indicating that they could not escape (returned false)
        
        boolean result = (GameManager.player.getCurrentHP() < GameManager.player.getMaxHP());
        
        assertEquals(expResult, result);
    }
}
