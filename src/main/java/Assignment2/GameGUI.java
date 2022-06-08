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
    static Font pixelFont = createCustomFont();
    static Font titleFont = new Font("Arial", Font.PLAIN, 70);
    static Font normalFont = new Font("Times New Roman", Font.PLAIN, 26);        
    
    //GAME WINDOW
    static int windowX = 1280; 
    static int windowY = 800;
    static JFrame gameWindow;
    static Container container;    
    
    //MAIN MENU   
    static int width, height;
    
    static JPanel titlePanel;
    static JLabel titleLabel;
    
    static JPanel buttonPanel;
    static JButton newGameButton;
    static JButton continueButton;
    static JButton creditsButton;
    static JButton quitButton;       
    
    //BUTTONS
    static JPanel gQuitPanel;
    static JButton gQuitButton;
    
    static JPanel mainMenuPanel;
    static JButton mainMenuButton;    
    
    //CHARACTER CREATION
    static int stage;
    
    //stage 0
    static JPanel namePanel;
    static JLabel nameText;
    static JTextField nameField;
    static JButton nameButton;
    
    //stage 1
    static JPanel promptPanel;
    static JLabel promptText;
    
    static JPanel statsPanel;    
    static JLabel strText, strValue;
    static JButton strMinus, strPlus;
    static JLabel intText, intValue;
    static JButton intMinus, intPlus;
    static JLabel defText, defValue;
    static JButton defMinus, defPlus;   
    
    static JPanel confirmPanel;
    
    static JButton confirmButton;
    
    //stage 2
    static JPanel fPromptPanel;
    static JLabel fPromptText;
    
    static JPanel playerCard;
    static JLabel nameLabel, hpLabel;
    static JLabel strLabel, intLabel, defLabel;
    static JLabel goldLabel, levelLabel, xpLabel;
    
    //GAMEPLAY
    static JPanel mainTextPanel;
    static JTextArea mainTextArea;    
    
    public static void createWindow()
    {        
        //create main JFrame
        gameWindow = new JFrame();
        gameWindow.setTitle("Moni's RPG Adventure");
        gameWindow.setSize(windowX,windowY);
        gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameWindow.getContentPane().setLayout(null);
        gameWindow.setLocationRelativeTo(null); //center window when opened
        gameWindow.setResizable(false);
        gameWindow.setVisible(true);        
        //assign container variable to the windows content pane
        container = gameWindow.getContentPane();
    }
    
    public static void startGame()
    {
        //disable uneeded GUI elements
        //if(confirmPanel != null) { confirmPanel.setVisible(false); System.out.println("disabled confirm panel"); }
        
        //initialise size varibles for title panel
        width = 600;
        height = 150;
        
        //TITLE
        titlePanel = new JPanel(new GridBagLayout());
        titlePanel.setBounds((windowX/2) - (width/2), 50, width, height);        
        titlePanel.setBorder(BorderFactory.createLineBorder(Color.black));
        titlePanel.setBackground(Color.white);
        container.add(titlePanel);
        
        titleLabel = new JLabel("RPG Adventure");
        titleLabel.setForeground(Color.black);
        titleLabel.setFont(pixelFont.deriveFont(50f));        
        titlePanel.add(titleLabel);                
        
        //reset size varibles for button panel
        width = 160;
        height = 300;
        
        //initialise button panel
        buttonPanel = new JPanel(new GridLayout(4,1,0,40));
        buttonPanel.setBounds((windowX/2) - (width/2), (windowY/2) - 50, width, height);
        container.add(buttonPanel);
        
        //NEW GAME
        newGameButton = new JButton("New Game");
        newGameButton.setBackground(Color.white);
        newGameButton.setForeground(Color.black);
        newGameButton.setFont(pixelFont);
        newGameButton.setFocusPainted(false);
        buttonPanel.add(newGameButton);
        
        newGameButton.addActionListener((ActionEvent e) -> 
        {
            System.out.println("'New Game' button pressed.");
            characterCreation(0);
        });        
        
        //CONTINUE
        continueButton = new JButton("Continue");
        continueButton.setBackground(Color.white);
        continueButton.setForeground(Color.black);
        continueButton.setFont(pixelFont);     
        continueButton.setFocusPainted(false);
        buttonPanel.add(continueButton);
        
        continueButton.addActionListener((ActionEvent e) -> 
        {
            System.out.println("'Continue' button pressed.");
        });
        
        //CREDITS
        creditsButton = new JButton("Credits");
        creditsButton.setBackground(Color.white);
        creditsButton.setForeground(Color.black);
        creditsButton.setFont(pixelFont);  
        creditsButton.setFocusPainted(false);
        buttonPanel.add(creditsButton);
        
        creditsButton.addActionListener((ActionEvent e) -> 
        {
            System.out.println("'Credits' button pressed.");
        });
        
        //QUIT
        quitButton = new JButton("Quit");
        quitButton.setBackground(Color.white);
        quitButton.setForeground(Color.black);
        quitButton.setFont(pixelFont);
        quitButton.setFocusPainted(false);
        buttonPanel.add(quitButton);
        
        quitButton.addActionListener((ActionEvent e) -> 
        {
            System.exit(0);
        });     
        
        //repaint the JFrame content
        container.revalidate();
        container.repaint();
    }
    
    static void characterCreation(int _stage)
    {
        //disable unneeded GUI elements
        titlePanel.setVisible(false);
        buttonPanel.setVisible(false);
        
        //initialise stage       
        stage = _stage;       
        
        switch(stage)
        {
            case 0 -> //set player name
            {                
                width = 200;
                height = 100;
                
                namePanel = new JPanel(new GridLayout(2,1,0,25));
                namePanel.setBounds((windowX/2) - (width/2), (windowY/2), width, height);
                container.add(namePanel);

                nameText = new JLabel("Enter your name:");
                nameText.setForeground(Color.black);
                nameText.setFont(pixelFont.deriveFont(30f));        
                namePanel.add(nameText);

                nameField = new JTextField();
                nameField.setHorizontalAlignment(JTextField.CENTER);
                nameField.setFont(pixelFont.deriveFont(2));
                namePanel.add(nameField);
            }
            case 1 -> //set player attributes
            {                
                namePanel.setVisible(false);
                
                width = 240;
                height = 40;
                
                //PROMPT
                promptPanel = new JPanel(new GridLayout(1,1));
                promptPanel.setBounds((windowX/2) - (width/2), (windowY/2) - 100, width, height);
                container.add(promptPanel);
                
                promptText = new JLabel("Points Remaining: 10");
                promptText.setForeground(Color.black);
                promptText.setFont(pixelFont.deriveFont(30f));
                promptText.setHorizontalAlignment(JLabel.CENTER);
                promptPanel.add(promptText);
                
                //reset size variables
                width = 400;
                height = 180;
                
                //initialise attributes panel
                statsPanel = new JPanel(new GridLayout(3,4,5,25));
                statsPanel.setBounds((windowX/2) - (width/2), windowY/2, width, height);
                container.add(statsPanel);
                
                //STRENGTH
                strText = new JLabel("Strength:");
                strText.setForeground(Color.black);
                strText.setFont(pixelFont);
                statsPanel.add(strText);
                
                strMinus = new JButton("-");
                strMinus.setBackground(Color.white);
                strMinus.setForeground(Color.black);
                strMinus.setFont(pixelFont);
                strMinus.setFocusPainted(false);
                statsPanel.add(strMinus);
                
                strValue = new JLabel("0");
                strValue.setBackground(Color.white);
                strValue.setForeground(Color.black);
                strValue.setFont(pixelFont);                
                strValue.setHorizontalAlignment(JLabel.CENTER);
                strValue.setBorder(BorderFactory.createLineBorder(Color.black));
                statsPanel.add(strValue);                
                
                strPlus = new JButton("+");
                strPlus.setBackground(Color.white);
                strPlus.setForeground(Color.black);
                strPlus.setFont(pixelFont);
                strPlus.setFocusPainted(false);
                statsPanel.add(strPlus);
                
                //INTELLECT
                intText = new JLabel("Intellect:");
                intText.setForeground(Color.black);
                intText.setFont(pixelFont);
                statsPanel.add(intText);
                
                intMinus = new JButton("-");
                intMinus.setBackground(Color.white);
                intMinus.setForeground(Color.black);
                intMinus.setFont(pixelFont);
                intMinus.setFocusPainted(false);
                statsPanel.add(intMinus);
                
                intValue = new JLabel("0");
                intValue.setBackground(Color.white);
                intValue.setForeground(Color.black);
                intValue.setFont(pixelFont);                
                intValue.setHorizontalAlignment(JLabel.CENTER);
                intValue.setBorder(BorderFactory.createLineBorder(Color.black));
                statsPanel.add(intValue);                
                
                intPlus = new JButton("+");
                intPlus.setBackground(Color.white);
                intPlus.setForeground(Color.black);
                intPlus.setFont(pixelFont);
                intPlus.setFocusPainted(false);
                statsPanel.add(intPlus);
                
                //DEFENSE
                defText = new JLabel("Defense:");
                defText.setForeground(Color.black);
                defText.setFont(pixelFont);
                statsPanel.add(defText);
                
                defMinus = new JButton("-");
                defMinus.setBackground(Color.white);
                defMinus.setForeground(Color.black);
                defMinus.setFont(pixelFont);
                defMinus.setFocusPainted(false);
                statsPanel.add(defMinus);
                
                defValue = new JLabel("0");
                defValue.setBackground(Color.white);
                defValue.setForeground(Color.black);
                defValue.setFont(pixelFont);                
                defValue.setHorizontalAlignment(JLabel.CENTER);
                defValue.setBorder(BorderFactory.createLineBorder(Color.black));
                statsPanel.add(defValue);                
                
                defPlus = new JButton("+");
                defPlus.setBackground(Color.white);
                defPlus.setForeground(Color.black);
                defPlus.setFont(pixelFont);
                defPlus.setFocusPainted(false);
                statsPanel.add(defPlus);
            }
            case 2 -> //confirm and start game
            {                
                statsPanel.setVisible(false);
                
                width = 240;
                height = 40;
                
                //PROMPT
                promptPanel.setBounds((windowX/2) - (width/2), (windowY/2) - 150, width, height);               
                promptText.setText("Starting game as...");
                
                //reset size variables
                width = 300;
                height = 320;
                
                //initialise container panel
                playerCard = new JPanel(new GridLayout(8,1));
                playerCard.setBounds((windowX/2) - (width/2), (windowY/2) - 100, width, height);
                playerCard.setBackground(Color.white);
                playerCard.setBorder(BorderFactory.createLineBorder(Color.black));
                container.add(playerCard);
                
                //STATS SUMMARY
                nameLabel = new JLabel("[ name ]");
                nameLabel.setHorizontalAlignment(JLabel.CENTER);
                nameLabel.setForeground(Color.black);
                nameLabel.setFont(pixelFont);
                playerCard.add(nameLabel);
                
                hpLabel = new JLabel("[ HP ] : 0");
                hpLabel.setHorizontalAlignment(JLabel.CENTER);
                hpLabel.setForeground(Color.black);
                hpLabel.setFont(pixelFont);
                playerCard.add(hpLabel);
                
                strLabel = new JLabel("[ STR ] : 0");
                strLabel.setHorizontalAlignment(JLabel.CENTER);
                strLabel.setForeground(Color.black);
                strLabel.setFont(pixelFont);
                playerCard.add(strLabel);
                
                intLabel = new JLabel("[ INT ] : 0");
                intLabel.setHorizontalAlignment(JLabel.CENTER);
                intLabel.setForeground(Color.black);
                intLabel.setFont(pixelFont);
                playerCard.add(intLabel);
                
                defLabel = new JLabel("[ DEF ] : 0");
                defLabel.setHorizontalAlignment(JLabel.CENTER);
                defLabel.setForeground(Color.black);
                defLabel.setFont(pixelFont);
                playerCard.add(defLabel);
                
                goldLabel = new JLabel("[ GOLD ] : 0");
                goldLabel.setHorizontalAlignment(JLabel.CENTER);
                goldLabel.setForeground(Color.black);
                goldLabel.setFont(pixelFont);
                playerCard.add(goldLabel);
                
                levelLabel = new JLabel("[ LEVEL ] : 0");
                levelLabel.setHorizontalAlignment(JLabel.CENTER);
                levelLabel.setForeground(Color.black);
                levelLabel.setFont(pixelFont);
                playerCard.add(levelLabel);
                
                xpLabel = new JLabel("[ XP ] : 0");
                xpLabel.setHorizontalAlignment(JLabel.CENTER);
                xpLabel.setForeground(Color.black);
                xpLabel.setFont(pixelFont);
                playerCard.add(xpLabel);
            }
        }
        
        //reset size variables
        width = 600;
        height = 150;
        
        //TITLE        
        titlePanel.setVisible(true); //reset the title panel with a new heading
        titleLabel.setText("Character Creation");
                
        //reset size variables
        width = 120;
        height = 40;
        
        //CONFIRM
        confirmPanel = new JPanel(new GridLayout(1,1));
        confirmPanel.setBounds((windowX/2)-(width/2),((3*windowY)/4)+height,width,height);      
        container.add(confirmPanel);
                
        confirmButton = new JButton("OK");
        confirmButton.setBackground(Color.white);
        confirmButton.setForeground(Color.black);
        confirmButton.setFont(pixelFont);
        confirmButton.setFocusPainted(false);
        confirmPanel.add(confirmButton); 
              
        confirmButton.addActionListener((ActionEvent e) -> 
        {
            switch(stage)
            {
                case 0 -> 
                {
                    characterCreation(1);
                }
                case 1 -> 
                {
                    characterCreation(2);
                }
                case 2 -> 
                {                    
                    gameScene();
                }
            }               
        }); 
    }
    
    public static void gameScene()
    {
        //disable unneeded GUI elements
        promptPanel.setVisible(false);
        playerCard.setVisible(false);
        titlePanel.setVisible(false);
        confirmPanel.setVisible(false);
        
        //initialise size variables
        width = 800;
        height = 600;
        
        //initialise main text panel
        mainTextPanel = new JPanel();
        mainTextPanel.setBounds(100, 100, width, height);
        mainTextPanel.setBackground(Color.white);
        container.add(mainTextPanel);
    }
    
    public static void mainMenu()
    {   
        //initialise size varibles
        width = 600;
        height = 150;
        
        //TITLE
        titlePanel = new JPanel(new GridBagLayout());
        titlePanel.setBounds((windowX/2) - (width/2), 50, width, height);        
        titlePanel.setBorder(BorderFactory.createLineBorder(Color.black));
        titlePanel.setBackground(Color.white);
        
        titleLabel = new JLabel("RPG Adventure");
        titleLabel.setForeground(Color.black);
        titleLabel.setFont(pixelFont.deriveFont(50f));        
        titlePanel.add(titleLabel);        
        titlePanel.setVisible(true);
        
        //initialise size varibles
        width = 160;
        height = 300;
        
        //button container panel
        buttonPanel = new JPanel(new GridLayout(4,1,0,40));
        buttonPanel.setBounds((windowX/2) - (width/2), (windowY/2) - 50, width, height);
        
        //NEW GAME
        newGameButton = new JButton("New Game");
        newGameButton.setBackground(Color.white);
        newGameButton.setForeground(Color.black);
        newGameButton.setFont(pixelFont);
        
        newGameButton.addActionListener((ActionEvent e) -> 
        {
            exitMainMenu();
            gameplayScene();
            characterCreation(0);
        });        
        buttonPanel.add(newGameButton);
        
        //CONTINUE
        continueButton = new JButton("Continue");
        continueButton.setBackground(Color.white);
        continueButton.setForeground(Color.black);
        continueButton.setFont(pixelFont);        
        buttonPanel.add(continueButton);
        
        //CREDITS
        creditsButton = new JButton("Credits");
        creditsButton.setBackground(Color.white);
        creditsButton.setForeground(Color.black);
        creditsButton.setFont(pixelFont);        
        buttonPanel.add(creditsButton);
        
        //QUIT
        quitButton = new JButton("Quit");
        quitButton.setBackground(Color.white);
        quitButton.setForeground(Color.black);
        quitButton.setFont(pixelFont);
        
        quitButton.addActionListener((ActionEvent e) -> 
        {
            System.exit(0);
        });        
        buttonPanel.add(quitButton);
        
        //add to game window
        gameWindow.add(titlePanel);   
        gameWindow.add(buttonPanel);  
        
        gameWindow.revalidate();
        gameWindow.repaint();
    }
    
    static void exitMainMenu()
    {
        titlePanel.setVisible(false);
        buttonPanel.setVisible(false);
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
            mainMenu();
            exitCharacterCreation();
            exitGameplayScene();  
            confirmPanel.setVisible(false);
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
    
    static void characterCreationOld(int _stage)
    {
        //initialise variables        
        stage = _stage;
        width = 600;
        height = 150;
        
        //TITLE        
        titlePanel.setVisible(true);
        titleLabel.setText("Character Creation");
        
        //CONFIRM
        //reset size variables
        width = 120;
        height = 40;
               
        confirmPanel = new JPanel(new GridLayout(1,1));
        confirmPanel.setBounds((windowX/2)-(width/2),((3*windowY)/4)+height,width,height);        
                
        confirmButton = new JButton("OK");
        confirmButton.setBackground(Color.white);
        confirmButton.setForeground(Color.black);
        confirmButton.setFont(pixelFont);
              
        confirmButton.addActionListener((ActionEvent e) -> 
        {
            switch(stage)
            {
                case 0:
                    namePanel.setVisible(false);
                    characterCreation(1);
                    break;
                case 1:
                    promptPanel.setVisible(false);
                    statsPanel.setVisible(false);
                    characterCreation(2);
                    break;
                case 2:
                    exitCharacterCreation();
                    break;
            }                    
        }); 
        
        confirmPanel.add(confirmButton);            
        gameWindow.add(confirmPanel);
        
        switch(stage)
        {
            case 0: //set player name
                //initialise size variables
                width = 200;
                height = 100;
                
                namePanel = new JPanel(new GridLayout(2,1,0,25));
                namePanel.setBounds((windowX/2) - (width/2), (windowY/2), width, height);

                nameText = new JLabel("Enter your name:");
                nameText.setForeground(Color.black);
                nameText.setFont(pixelFont.deriveFont(30f));        
                namePanel.add(nameText);

                nameField = new JTextField();
                nameField.setHorizontalAlignment(JTextField.CENTER);
                nameField.setFont(pixelFont.deriveFont(2));
                namePanel.add(nameField);
                
                gameWindow.add(namePanel);                
                break;
            case 1: //set player stats
                //reset size variables
                width = 240;
                height = 40;
                
                //PROMPT
                promptPanel = new JPanel(new GridLayout(1,1));
                promptPanel.setBounds((windowX/2) - (width/2), (windowY/2) - 100, width, height);
                
                promptText = new JLabel("Points Remaining: 10");
                promptText.setForeground(Color.black);
                promptText.setFont(pixelFont.deriveFont(30f));
                promptText.setHorizontalAlignment(JLabel.CENTER);
                promptPanel.add(promptText);
                
                //reset size variables
                width = 400;
                height = 180;
                
                //initialise container panel
                statsPanel = new JPanel(new GridLayout(3,4,5,25));
                statsPanel.setBounds((windowX/2) - (width/2), windowY/2, width, height);
                
                //STRENGTH
                strText = new JLabel("Strength:");
                strText.setForeground(Color.black);
                strText.setFont(pixelFont);
                statsPanel.add(strText);
                
                strMinus = new JButton("-");
                strMinus.setBackground(Color.white);
                strMinus.setForeground(Color.black);
                strMinus.setFont(pixelFont);
                statsPanel.add(strMinus);
                
                strValue = new JLabel("0");
                strValue.setBackground(Color.white);
                strValue.setForeground(Color.black);
                strValue.setFont(pixelFont);                
                strValue.setHorizontalAlignment(JLabel.CENTER);
                strValue.setBorder(BorderFactory.createLineBorder(Color.black));
                statsPanel.add(strValue);                
                
                strPlus = new JButton("+");
                strPlus.setBackground(Color.white);
                strPlus.setForeground(Color.black);
                strPlus.setFont(pixelFont);
                statsPanel.add(strPlus);
                
                //INTELLECT
                intText = new JLabel("Intellect:");
                intText.setForeground(Color.black);
                intText.setFont(pixelFont);
                statsPanel.add(intText);
                
                intMinus = new JButton("-");
                intMinus.setBackground(Color.white);
                intMinus.setForeground(Color.black);
                intMinus.setFont(pixelFont);
                statsPanel.add(intMinus);
                
                intValue = new JLabel("0");
                intValue.setBackground(Color.white);
                intValue.setForeground(Color.black);
                intValue.setFont(pixelFont);                
                intValue.setHorizontalAlignment(JLabel.CENTER);
                intValue.setBorder(BorderFactory.createLineBorder(Color.black));
                statsPanel.add(intValue);                
                
                intPlus = new JButton("+");
                intPlus.setBackground(Color.white);
                intPlus.setForeground(Color.black);
                intPlus.setFont(pixelFont);
                statsPanel.add(intPlus);
                
                //DEFENSE
                defText = new JLabel("Defense:");
                defText.setForeground(Color.black);
                defText.setFont(pixelFont);
                statsPanel.add(defText);
                
                defMinus = new JButton("-");
                defMinus.setBackground(Color.white);
                defMinus.setForeground(Color.black);
                defMinus.setFont(pixelFont);
                statsPanel.add(defMinus);
                
                defValue = new JLabel("0");
                defValue.setBackground(Color.white);
                defValue.setForeground(Color.black);
                defValue.setFont(pixelFont);                
                defValue.setHorizontalAlignment(JLabel.CENTER);
                defValue.setBorder(BorderFactory.createLineBorder(Color.black));
                statsPanel.add(defValue);                
                
                defPlus = new JButton("+");
                defPlus.setBackground(Color.white);
                defPlus.setForeground(Color.black);
                defPlus.setFont(pixelFont);
                statsPanel.add(defPlus);
                
                gameWindow.add(promptPanel);
                gameWindow.add(statsPanel);
                break;
            case 2: //confirm and start game
                //reset size variables
                width = 240;
                height = 40;
                
                //PROMPT
                fPromptPanel = new JPanel(new GridLayout(1,1));
                fPromptPanel.setBounds((windowX/2) - (width/2), (windowY/2) - 150, width, height);
                
                fPromptText = new JLabel("Starting game as...");
                fPromptText.setForeground(Color.black);
                fPromptText.setFont(pixelFont.deriveFont(30f));
                fPromptText.setHorizontalAlignment(JLabel.CENTER);
                fPromptPanel.add(fPromptText);
                
                //reset size variables
                width = 300;
                height = 320;
                
                //initialise container panel
                playerCard = new JPanel(new GridLayout(8,1));
                playerCard.setBounds((windowX/2) - (width/2), (windowY/2) - 100, width, height);
                playerCard.setBackground(Color.white);
                playerCard.setBorder(BorderFactory.createLineBorder(Color.black));
                
                //STATS SUMMARY
                nameLabel = new JLabel("[ name ]");
                nameLabel.setHorizontalAlignment(JLabel.CENTER);
                nameLabel.setForeground(Color.black);
                nameLabel.setFont(pixelFont);
                playerCard.add(nameLabel);
                
                hpLabel = new JLabel("[ HP ] : 0");
                hpLabel.setHorizontalAlignment(JLabel.CENTER);
                hpLabel.setForeground(Color.black);
                hpLabel.setFont(pixelFont);
                playerCard.add(hpLabel);
                
                strLabel = new JLabel("[ STR ] : 0");
                strLabel.setHorizontalAlignment(JLabel.CENTER);
                strLabel.setForeground(Color.black);
                strLabel.setFont(pixelFont);
                playerCard.add(strLabel);
                
                intLabel = new JLabel("[ INT ] : 0");
                intLabel.setHorizontalAlignment(JLabel.CENTER);
                intLabel.setForeground(Color.black);
                intLabel.setFont(pixelFont);
                playerCard.add(intLabel);
                
                defLabel = new JLabel("[ DEF ] : 0");
                defLabel.setHorizontalAlignment(JLabel.CENTER);
                defLabel.setForeground(Color.black);
                defLabel.setFont(pixelFont);
                playerCard.add(defLabel);
                
                goldLabel = new JLabel("[ GOLD ] : 0");
                goldLabel.setHorizontalAlignment(JLabel.CENTER);
                goldLabel.setForeground(Color.black);
                goldLabel.setFont(pixelFont);
                playerCard.add(goldLabel);
                
                levelLabel = new JLabel("[ LEVEL ] : 0");
                levelLabel.setHorizontalAlignment(JLabel.CENTER);
                levelLabel.setForeground(Color.black);
                levelLabel.setFont(pixelFont);
                playerCard.add(levelLabel);
                
                xpLabel = new JLabel("[ XP ] : 0");
                xpLabel.setHorizontalAlignment(JLabel.CENTER);
                xpLabel.setForeground(Color.black);
                xpLabel.setFont(pixelFont);
                playerCard.add(xpLabel);
                
                gameWindow.add(fPromptPanel);
                gameWindow.add(playerCard);
                break;
        }        
    }
    
    static void exitCharacterCreation()
    {        
        titlePanel.setVisible(false);
        confirmPanel.setVisible(false);
        
        if(namePanel     != null) { namePanel.setVisible(false);     }
        if(promptPanel   != null) { promptPanel.setVisible(false);   }
        if(statsPanel    != null) { statsPanel.setVisible(false);    }
        if(fPromptPanel  != null) { fPromptPanel.setVisible(false);  }
        if(playerCard    != null) { playerCard.setVisible(false);    }
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
