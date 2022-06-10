package Assignment2;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author User
 */
public class CombatLogicTest {
    
    Enemy enemy;
            
    public CombatLogicTest()     {
        
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        GameManager.gameDataDB = new GameData(); 
        GameManager.player = new Player("test");
        enemy = CombatLogic.currentEnemy;
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of setRandomEnemy method, of class CombatLogic.
     */
    @Test
    public void testSetRandomEnemy() 
    {
        System.out.println("setRandomEnemy");
        
        CombatLogic.setRandomEnemy();
        //if combatlogic.currentEnemy != null // successful 
    }

    /**
     * Test of combatEvent method, of class CombatLogic.
     */
    @Test
    public void testCombatEvent() {
        System.out.println("combatEvent");
        
        boolean expResult = false;
        boolean result = CombatLogic.combatEvent(enemy);
        
        assertEquals(expResult, result);
    }

    /**
     * Test of runFromCombat method, of class CombatLogic.
     */
    /*
    @Test
    public void testRunFromCombat() {
        
        System.out.println("runFromCombat");
        Enemy enemy = CombatLogic.currentEnemy;
        
        
        
        boolean expResult = false;
        boolean result = CombatLogic.runFromCombat(enemy);
        
        assertEquals(expResult, result);
    }
    */

    /**
     * Test of updateCombatTextArea method, of class CombatLogic.
     */
    @Test
    public void testUpdateCombatTextArea() {
        System.out.println("updateCombatTextArea");
        String text = "test";
        CombatLogic.updateCombatTextArea(text);
    }

    /**
     * Test of updatePlayerCombatCard method, of class CombatLogic.
     */
    @Test
    public void testUpdatePlayerCombatCard() {
        System.out.println("updatePlayerCombatCard");
        CombatLogic.updatePlayerCombatCard();
    }

    /**
     * Test of updateEnemyCombatCard method, of class CombatLogic.
     */
    @Test
    public void testUpdateEnemyCombatCard() {
        System.out.println("updateEnemyCombatCard");        
        CombatLogic.updateEnemyCombatCard(enemy);
    }
    
}
