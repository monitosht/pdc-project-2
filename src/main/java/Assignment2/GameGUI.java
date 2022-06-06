package Assignment2;

/**
 * @author Monitosh Thaker | 17000777
 * COMP603 Assignment 2
 */

import java.io.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class GameGUI 
{ 
    //global
    static Font pixelFont = createCustomFont();
    static Font titleFont = new Font("Arial", Font.PLAIN, 70);
    static Font normalFont = new Font("Times New Roman", Font.PLAIN, 26);    
    
    //game window
    static int windowX = 1280; 
    static int windowY = 800;
    static JFrame gameWindow;
    
    //main menu    
    static int width, height;
    
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
    
    //gameplay scene
    static JPanel gQuitPanel;
    static JButton gQuitButton;
    
    static JPanel mainMenuPanel;
    static JButton mainMenuButton;
    
    //character creation 
    static JPanel ccTitlePanel;
    static JLabel ccTitleLabel;
    
    static JPanel namePanel;
    static JLabel nameText;
    static JTextField nameField;
    static JButton nameButton;
    
    public static void createWindow()
    {        
        //create JFrame
        gameWindow = new JFrame();
        gameWindow.setTitle("Moni's RPG Adventure");
        gameWindow.setSize(windowX,windowY);
        gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameWindow.getContentPane().setLayout(null);
        gameWindow.setLocationRelativeTo(null); //center window when opened
        gameWindow.setResizable(false);
        gameWindow.setVisible(true);
    }
    
    public static void mainMenu()
    {   
        //initialise size varibles
        width = 160;
        height = 40;
        
        //TITLE
        titlePanel = new JPanel(new GridBagLayout());
        titlePanel.setBounds((windowX/2) - 300,150,600,150);
        titlePanel.setBackground(Color.white);
        titlePanel.setBorder(BorderFactory.createLineBorder(Color.black));
        
        titleLabel = new JLabel("RPG Adventure");
        titleLabel.setForeground(Color.black);
        titleLabel.setFont(pixelFont.deriveFont(50f));
        
        titlePanel.add(titleLabel);
        
        //NEW GAME
        newGamePanel = new JPanel(new GridBagLayout());
        newGamePanel.setBounds((windowX/2) - (width/2),windowY/2,width,height);
        newGamePanel.setBackground(Color.white);
        
        newGameButton = new JButton("New Game");
        newGameButton.setBackground(Color.white);
        newGameButton.setForeground(Color.black);
        newGameButton.setFont(pixelFont);
        //to ensure all buttons are of equal size
        newGameButton.setPreferredSize(new Dimension(width,height));
        
        newGameButton.addActionListener((ActionEvent e) -> 
        {
            exitMainMenu();
            gameplayScene();
            characterCreation();
        });
        
        newGamePanel.add(newGameButton);
        
        //CONTINUE
        continuePanel = new JPanel(new GridBagLayout());
        continuePanel.setBounds((windowX/2) - (width/2),(windowY/2) + 75,width,height);
        
        continueButton = new JButton("Continue");
        continueButton.setBackground(Color.white);
        continueButton.setForeground(Color.black);
        continueButton.setFont(pixelFont);
        //to ensure all buttons are of equal size
        continueButton.setPreferredSize(new Dimension(width,height));
        
        continuePanel.add(continueButton);
        
        //CREDITS
        creditsPanel = new JPanel(new GridBagLayout());
        creditsPanel.setBounds((windowX/2) - (width/2),(windowY/2) + 150,width,height);
        
        creditsButton = new JButton("Credits");
        creditsButton.setBackground(Color.white);
        creditsButton.setForeground(Color.black);
        creditsButton.setFont(pixelFont);
        //to ensure all buttons are of equal size
        creditsButton.setPreferredSize(new Dimension(width,height));
        
        creditsPanel.add(creditsButton);      
        
        //QUIT
        quitPanel = new JPanel(new GridBagLayout());
        quitPanel.setBounds((windowX/2) - (width/2),(windowY/2) + 225,width,height);
        
        quitButton = new JButton("Quit");
        quitButton.setBackground(Color.white);
        quitButton.setForeground(Color.black);
        quitButton.setFont(pixelFont);
        //to ensure all buttons are of equal size
        quitButton.setPreferredSize(new Dimension(width,height));
        
        quitButton.addActionListener((ActionEvent e) -> 
        {
            System.exit(0);
        });
        
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
    
    static void exitMainMenu()
    {
        titlePanel.setVisible(false);
        newGamePanel.setVisible(false);
        continuePanel.setVisible(false);
        creditsPanel.setVisible(false);
        quitPanel.setVisible(false);
    }
    
    static void gameplayScene()
    {
        //initialise size varibles
        width = 160;
        height = 40;
        
        //MAIN MENU
        mainMenuPanel = new JPanel(new GridBagLayout());
        mainMenuPanel.setBounds(50,50,width,height);
        
        mainMenuButton = new JButton("Main Menu");
        mainMenuButton.setBackground(Color.white);
        mainMenuButton.setForeground(Color.black);
        mainMenuButton.setFont(pixelFont);
        mainMenuButton.setPreferredSize(new Dimension(width,height));
        
        mainMenuButton.addActionListener((ActionEvent e) -> 
        {
            exitGameplayScene();
            exitCharacterCreation();
            mainMenu();
        });
        
        mainMenuPanel.add(mainMenuButton);
        
        //QUIT
        gQuitPanel = new JPanel(new GridBagLayout());
        gQuitPanel.setBounds(windowX-width-50,50,width,height);
        
        gQuitButton = new JButton("Quit");
        gQuitButton.setBackground(Color.white);
        gQuitButton.setForeground(Color.black);
        gQuitButton.setFont(pixelFont);
        gQuitButton.setPreferredSize(new Dimension(width,height));
        
        gQuitButton.addActionListener((ActionEvent e) -> 
        {
            System.exit(0);
        });
        
        gQuitPanel.add(gQuitButton);
        
        gameWindow.add(mainMenuPanel);
        gameWindow.add(gQuitPanel);
    }
    
    static void exitGameplayScene()
    {
        gQuitPanel.setVisible(false);
        mainMenuPanel.setVisible(false);
    }
    
    static void characterCreation()
    {
        width = 200;
        height = 40;
        
        //TITLE
        ccTitlePanel = new JPanel(new GridBagLayout());
        ccTitlePanel.setBounds((windowX/2) - 300,150,600,150);
        ccTitlePanel.setBackground(Color.white);
        ccTitlePanel.setBorder(BorderFactory.createLineBorder(Color.black));
        
        ccTitleLabel = new JLabel("Character Creation");
        ccTitleLabel.setForeground(Color.black);
        ccTitleLabel.setFont(pixelFont.deriveFont(50f));
        
        ccTitlePanel.add(ccTitleLabel);
        
        namePanel = new JPanel(new GridLayout(3,1,0,25));
        namePanel.setBounds((windowX/2) - (width/2), windowY/2, width, height*4);
        
        nameText = new JLabel("Enter your name:");
        nameText.setForeground(Color.black);
        nameText.setFont(pixelFont.deriveFont(30f));        
        namePanel.add(nameText);
        
        nameField = new JTextField();
        //nameField.setBounds((windowX/2) - (width/2), (windowY/4) + 50, width, height);
        nameField.setHorizontalAlignment(JTextField.CENTER);
        nameField.setFont(pixelFont.deriveFont(2));
        namePanel.add(nameField);
        
        nameButton = new JButton("OK");
        nameButton.setBackground(Color.white);
        nameButton.setForeground(Color.black);
        nameButton.setFont(pixelFont);
        
        nameButton.addActionListener((ActionEvent e) -> 
        {
            System.exit(0);
        });
        
        namePanel.add(nameButton);
        
        gameWindow.add(ccTitlePanel);
        gameWindow.add(namePanel);
    }
    
    static void exitCharacterCreation()
    {
        ccTitlePanel.setVisible(false);
        namePanel.setVisible(false);
    }
    
    static Font createCustomFont()
    {
        try 
        {
            Font font = Font.createFont(Font.TRUETYPE_FONT, new File("./resources/pixel_font.ttf")).deriveFont(24f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(font);
            return font;
        } 
        catch (IOException|FontFormatException e) 
        {
            System.err.println("Error: "+e.getMessage());
        }        
        return null;
    }
}
