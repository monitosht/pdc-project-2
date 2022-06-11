package Assignment2;

/**
 * @author Monitosh Thaker | 17000777
 * COMP603 Assignment 2
 */
public class Main 
{
    public static void main(String[] args) 
    {
        //Initialise the embedded derby database when the program starts.
        GameManager.gameDataDB = new GameData();        
        
        //Initialise a new GUIView object, creating the main JFRame window.
        GameManager.gui = new GUIView();  
        //Start the game by creating the main menu scene.
        GameManager.gui.createMainMenu();
    }
}
