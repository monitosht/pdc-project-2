/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
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
public class GUILogicTest{
    
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
        
        GameManager.player = new Player("Test Player");
        GameManager.player.setCurrentHP(15);
        GameManager.player.setGold(25);
        
        CombatLogic.setRandomEnemy();
        
        GUIHandler.createWindow();
        GUIHandler.createGameScene();
    }
    
    @After
    public void tearDown()
    {
        //to remove any unnecessary save data created through testing
        GameManager.gameDataDB.removeSaveData();
    }

    /**
     * Test of innArea method, of class GUILogic.
     */
    @Test
    public void testInnArea() 
    {
        System.out.println("innArea");
        
        GUILogic.position = null;        
        GUILogic.innArea(1);
        
        String expResult = "Return";
        String result = GUILogic.position;
        
        assertEquals(expResult, result);
    }

    /**
     * Test of shopArea method, of class GUILogic.
     */
    @Test
    public void testShopArea() 
    {
        System.out.println("shopArea");
        
        GUILogic.position = null;        
        GUILogic.shopArea(3);
        
        String expResult = "Town";
        String result = GUILogic.position;
        
        assertEquals(expResult, result);
    }

    /**
     * Test of adventureArea method, of class GUILogic.
     */
    @Test
    public void testAdventureArea() 
    {
        System.out.println("adventureArea");
        
        GUILogic.position = null;
        GUILogic.adventureArea(3);
        
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
        
        GUILogic.position = null;        
        GUILogic.combatArea(3);

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
     * Test of returnEvent method, of class GUILogic.
     */
    @Test
    public void testReturnEvent() 
    {
        System.out.println("returnEvent");
        
        GUILogic.position = null;        
        GUILogic.returnEvent(1);
        
        String expResult = "Town";
        String result = GUILogic.position;
        
        assertEquals(expResult, result);
    }

    /**
     * Test of continueEvent method, of class GUILogic.
     */
    @Test
    public void testContinueEvent() 
    {
        System.out.println("continueEvent");
        
        GUILogic.position = null;        
        GUILogic.continueEvent(1, 5);
        
        String expResult = "Adventure";
        String result = GUILogic.position;
        
        assertEquals(expResult, result);
    }
}
