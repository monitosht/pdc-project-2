package Assignment2;

/**
 * @author Monitosh Thaker | 17000777
 * COMP603 Assignment 2
 */
public class Main 
{
    public static void main(String[] args) 
    {
        GameManager.gameDataDB = new GameData();    
        //GameManager.setup.createMainMenu();
        
        GUIHandler.createWindow();
        GUIHandler.createMainMenu();
    }
}
