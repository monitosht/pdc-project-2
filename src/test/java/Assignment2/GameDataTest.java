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
public class GameDataTest {
    
    public GameDataTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of main method, of class GameData.
     */
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        GameData.main(args);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of writePlayerSaveData method, of class GameData.
     */
    @Test
    public void testWritePlayerSaveData() {
        System.out.println("writePlayerSaveData");
        GameData instance = new GameData();
        instance.writePlayerSaveData();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of readPlayerSaveData method, of class GameData.
     */
    @Test
    public void testReadPlayerSaveData() {
        System.out.println("readPlayerSaveData");
        GameData instance = new GameData();
        instance.readPlayerSaveData();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of loadSaveData method, of class GameData.
     */
    @Test
    public void testLoadSaveData() {
        System.out.println("loadSaveData");
        String name = "";
        GameData instance = new GameData();
        instance.loadSaveData(name);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkSaveExists method, of class GameData.
     */
    @Test
    public void testCheckSaveExists() {
        System.out.println("checkSaveExists");
        String name = "";
        GameData instance = new GameData();
        boolean expResult = false;
        boolean result = instance.checkSaveExists(name);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeSaveData method, of class GameData.
     */
    @Test
    public void testRemoveSaveData() {
        System.out.println("removeSaveData");
        GameData instance = new GameData();
        instance.removeSaveData();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of readEnemyList method, of class GameData.
     */
    @Test
    public void testReadEnemyList() {
        System.out.println("readEnemyList");
        GameData instance = new GameData();
        instance.readEnemyList();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of closeConnection method, of class GameData.
     */
    @Test
    public void testCloseConnection() {
        System.out.println("closeConnection");
        GameData instance = new GameData();
        instance.closeConnection();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
