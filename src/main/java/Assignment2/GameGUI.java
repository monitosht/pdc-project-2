package Assignment2;

/**
 * @author Monitosh Thaker | 17000777
 * COMP603 Assignment 2
 */
import java.awt.*;
import javax.swing.*;

public class GameGUI 
{ 
    //global
    static Font titleFont = new Font("Times New Roman", Font.PLAIN, 90);
    static Font normalFont = new Font("Times New Roman", Font.PLAIN, 26);
    
    
    //game window
    static JFrame gameWindow;
    
    //main menu
    static JPanel titlePanel;
    static JLabel titleLabel;
    
    static JPanel newGamePanel;
    static JButton newGameButton;
    
    static JPanel continuePanel;
    static JButton continueButton;
    
    static JPanel creditsPanel;
    static JButton creditsButton;
    
    static JPanel quitPanel;
    static JButton quitButton;
    
    
    public static void frameSetup()
    {
        gameWindow = new JFrame();
        gameWindow.setTitle("Moni's RPG Adventure");
        gameWindow.setSize(1280,800);
        gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameWindow.setLocationRelativeTo(null); //center window when opened
        gameWindow.setResizable(false);
        gameWindow.setVisible(true);
    }
    
    public static void mainMenu()
    {   
        //TITLE
        titlePanel = new JPanel();
        titlePanel.setBounds(340,150,600,150);
        titlePanel.setBackground(Color.white);
        
        titleLabel = new JLabel("RPG Adventure");
        titleLabel.setForeground(Color.black);
        titleLabel.setFont(titleFont);
        
        titlePanel.add(titleLabel);
        
        //NEW GAME
        newGamePanel = new JPanel();
        newGamePanel.setBounds(560,375,160,40);
        newGamePanel.setBackground(Color.white);
        
        newGameButton = new JButton("New Game");
        newGameButton.setBackground(Color.white);
        newGameButton.setForeground(Color.black);
        newGameButton.setFont(normalFont);
        
        newGamePanel.add(newGameButton);
        
        //CONTINUE
        continuePanel = new JPanel();
        continuePanel.setBounds(560,450,160,40);
        continuePanel.setBackground(Color.white);
        
        continueButton = new JButton("Continue");
        continueButton.setBackground(Color.white);
        continueButton.setForeground(Color.black);
        continueButton.setFont(normalFont);
        
        continuePanel.add(continueButton);
        
        //CREDITS
        creditsPanel = new JPanel();
        creditsPanel.setBounds(560,525,160,40);
        creditsPanel.setBackground(Color.white);
        
        creditsButton = new JButton("Credits");
        creditsButton.setBackground(Color.white);
        creditsButton.setForeground(Color.black);
        creditsButton.setFont(normalFont);
        
        creditsPanel.add(creditsButton);      
        
        //QUIT
        quitPanel = new JPanel();
        quitPanel.setBounds(560,600,160,40);
        quitPanel.setBackground(Color.white);
        
        quitButton = new JButton("123");
        quitButton.setBackground(Color.white);
        quitButton.setForeground(Color.black);
        quitButton.setFont(normalFont);
        
        quitPanel.add(quitButton);
        
        //add to game window
        gameWindow.add(titlePanel);        
        gameWindow.add(newGamePanel);
        gameWindow.add(continuePanel);
        gameWindow.add(creditsPanel);
        gameWindow.add(quitPanel);
        gameWindow.revalidate();
        gameWindow.repaint();
    }
}
