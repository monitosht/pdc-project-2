package Assignment2;

/**
 * @author Monitosh Thaker | 17000777
 * COMP603 Assignment 2
 */
import javax.swing.*;

public class GameGUI 
{
    //Main game window
    public static JFrame gameWindow;
    
    public static void startScene()
    {
        gameWindow = new JFrame();
        gameWindow.setTitle("Moni's RPG Adventure");
        gameWindow.setSize(1280,720);
        gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameWindow.setResizable(false);
        gameWindow.setVisible(true);
    }    
}
