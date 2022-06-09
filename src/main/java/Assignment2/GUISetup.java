package Assignment2;

import java.io.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;

/**
 * @author Monitosh Thaker | 17000777
 * COMP603 Assignment 2
 */
public class GUISetup 
{ 
    /*
    * VARIABLES
    */
    // <editor-fold defaultstate="collapsed" desc="Font Variables">
    
    static Font pixelFont = createCustomFont();
    static Font titleFont = new Font("Arial", Font.PLAIN, 70);
    static Font normalFont = new Font("Times New Roman", Font.PLAIN, 26); 
    
    // </editor-fold>          
    
    // <editor-fold defaultstate="collapsed" desc="Game Window Variables">
    
    static int windowX = 1280; 
    static int windowY = 800;
    static JFrame gameWindow;
    static Container container;   
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Main Menu Variables">   
    
    static int width, height;
    
    static JPanel titlePanel;
    static JLabel titleLabel;
    
    static JPanel buttonPanel;
    static JButton newGameButton;
    static JButton continueButton;
    static JButton creditsButton;
    static JButton quitButton;  
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Continue Scene Variables">  
    
    static JPanel savePanel;
    static JScrollPane saveScrollPane;
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Constant Button Variables">  
    
    static JPanel mainMenuPanel;
    static JButton mainMenuButton;    
    
    static JPanel miniTitlePanel;
    static JLabel miniTitleText;
    
    static JPanel gQuitPanel;
    static JButton gQuitButton;
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Character Creation Variables">  
    
    static int stage;
    
    static JPanel confirmPanel;    
    static JButton confirmButton;
    
    //cc stage 0
    static JPanel namePanel;
    static JLabel nameText;
    static JTextField nameField;
    static JButton nameButton;
    
    //cc stage 1
    static JPanel promptPanel;
    static JLabel promptText;
    
    static JPanel statsPanel;    
    static JLabel strText, strValue;
    static JButton strMinus, strPlus;
    static JLabel intText, intValue;
    static JButton intMinus, intPlus;
    static JLabel defText, defValue;
    static JButton defMinus, defPlus; 
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Game Scene Variables">         
    
    static JPanel mainTextPanel;
    static JTextArea mainTextArea; 
    
    static JPanel gameButtonPanel;
    static JButton gameButton1, gameButton2, gameButton3, gameButton4;
    static GameButtonHandler gameButtonHandler = new GameButtonHandler();
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Player Card Variables">  
    
    static JPanel playerStatsCard;
    static JLabel playerNameLabel, hpLabel;
    static JLabel strLabel, intLabel, defLabel;
    static JLabel levelLabel, xpLabel, goldLabel;
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Shop/Item Variables">  
    
    static JPanel buyPanel;
    static JPanel sellPanel;
    static JScrollPane sellScrollPane;
    
    //INVENTORY POPUP
    static JOptionPane inventoryBox;
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Combat Scene Variables">  
    
    static boolean inCombat = false;
    static JPanel combatContainer;    
    static JPanel combatPanel;
    static JPanel playerPanel;
    static JLabel vsLabel;
    static JPanel enemyPanel;
    
    static JPanel combatTextPanel;
    static JTextArea combatTextArea;
    
    static JLabel playerName, playerHP, playerIcon;
    static JLabel enemyName, enemyHP, enemyIcon;
    
    // </editor-fold>
    
    /*
    * METHODS
    */    
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
    
    public static void createMainMenu()
    {      
        //disable uneeded GUI elements
        exitBuyMenu();
        exitSellMenu();
        exitCombatScene();
        exitGameScene();
        exitCharacterCreation();   
        exitContinueScene();
        disableConstantButtons();
        
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
            characterCreation(0);            
            constantButtons();
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
            createContinueScene();
            constantButtons();
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
    
    static void exitMainMenu()
    {
        titlePanel.setVisible(false);
        buttonPanel.setVisible(false);
    }    
    
    public static void createContinueScene()
    {
        //reinitialise save data
        GameManager.gameDataDB.readPlayerSaveData();
        
        //disable unneeded GUI elements
        exitMainMenu();
        
        //TITLE        
        titlePanel.setVisible(true); //reset the title panel with a new heading
        titleLabel.setText("Select Save Data");
        
        width = 800;
        height = 450;
        
        if(GameManager.numSaveData != 0)
        {
            if(GameManager.numSaveData > 4) savePanel = new JPanel(new GridLayout(GameManager.numSaveData, 1));
            else savePanel = new JPanel(new GridLayout(4, 1));
            
            savePanel.setPreferredSize(new Dimension(width-25, (height/4)*(GameManager.numSaveData)-25));
            savePanel.setBorder(BorderFactory.createLineBorder(Color.black));
            //savePanel.setBackground(Color.white); 

            saveScrollPane = new JScrollPane(savePanel);
            saveScrollPane.setBounds((windowX/2 - width/2), (windowY/2) - (height/2) + 50, width, height);
            container.add(saveScrollPane);
            
            createContinueButtons();
        }
        else
        {
            savePanel = new JPanel(new GridLayout(1, 1));
            savePanel.setBounds((windowX/2 - width/2), (windowY/2) - (height/2) + 50, width, height);
            savePanel.setBorder(BorderFactory.createLineBorder(Color.black));
            //savePanel.setBackground(Color.white);
            container.add(savePanel);            
            
            JLabel empty = new JLabel("No Save Data Exists");
            empty.setForeground(Color.black);
            empty.setFont(pixelFont.deriveFont(30f));
            empty.setHorizontalAlignment(JTextField.CENTER);
            empty.setVerticalAlignment(JTextField.CENTER);
            savePanel.add(empty);
        }
    }
    
    public static void createContinueButtons()
    {
        savePanel.removeAll();
        
        for(int i = 0; i < GameManager.saves.size(); i++)
        {
            final int final_i = i;
            
            JButton button = new JButton(GameManager.saves.get(i));
            button.setBackground(Color.white);
            button.setForeground(Color.black);
            button.setFont(pixelFont.deriveFont(30f));
            button.setHorizontalAlignment(JTextField.CENTER);
            button.setVerticalAlignment(JTextField.CENTER);
            button.setFocusPainted(false);
            
            button.addActionListener((ActionEvent e) -> 
            {           
                GameManager.gameDataDB.loadSaveData(GameManager.saves.get(final_i));
            });
            savePanel.add(button);
        }
        
        if(GameManager.numSaveData < 4)
        {
            int emptyLabels = 4 - GameManager.numSaveData;
            
            for(int i = 0; i < emptyLabels; i++)
            {
                JLabel empty = new JLabel();
                savePanel.add(empty);
            }
        }
    }
    
    public static void exitContinueScene()
    {
        if(titlePanel     != null) titlePanel.setVisible(false);  
        if(saveScrollPane != null) saveScrollPane.setVisible(false);  
        if(savePanel      != null) savePanel.setVisible(false);  
    }
    
    public static void constantButtons()
    {
        //initialise size varibles
        width = 120;
        height = 30;
        
        //MAIN MENU
        mainMenuPanel = new JPanel(new GridBagLayout());
        mainMenuPanel.setBounds(25,windowY-height*2-25,width,height);
        container.add(mainMenuPanel);
        
        mainMenuButton = new JButton("Main Menu");
        mainMenuButton.setBackground(Color.white);
        mainMenuButton.setForeground(Color.black);
        mainMenuButton.setFont(pixelFont.deriveFont(20f));
        mainMenuButton.setFocusPainted(false);
        mainMenuPanel.add(mainMenuButton);
        
        mainMenuButton.addActionListener((ActionEvent e) -> 
        {     
            createMainMenu();
        });
        
        //QUIT
        gQuitPanel = new JPanel(new GridBagLayout());
        gQuitPanel.setBounds(windowX-width-25,windowY-height*2-25,width,height);
        container.add(gQuitPanel);
        
        gQuitButton = new JButton("Quit");
        gQuitButton.setBackground(Color.white);
        gQuitButton.setForeground(Color.black);
        gQuitButton.setFont(pixelFont.deriveFont(20f));
        gQuitButton.setFocusPainted(false);
        gQuitPanel.add(gQuitButton);
        
        gQuitButton.addActionListener((ActionEvent e) -> 
        {
            System.exit(0);
        }); 
        
        //reset size variables
        width = 240;
        height = 30;
        
        //MINI TITLE
        miniTitlePanel = new JPanel(new GridBagLayout());
        miniTitlePanel.setBounds((windowX/2) - (width/2), windowY-height*2-25, width, height);
        container.add(miniTitlePanel);
        
        miniTitleText = new JLabel("Moni's RPG Adventure v0.1");
        miniTitleText.setForeground(Color.black);
        miniTitleText.setFont(pixelFont.deriveFont(2, 20f));
        miniTitlePanel.add(miniTitleText);
    }
    
    public static void disableConstantButtons()
    {
        if(mainMenuPanel  != null) mainMenuPanel.setVisible(false);
        if(miniTitlePanel != null) miniTitlePanel.setVisible(false);
        if(gQuitPanel     != null) gQuitPanel.setVisible(false);
    }    
    
    public static void characterCreation(int _stage)
    {
        //disable unneeded GUI elements
        exitMainMenu();
        
        //initialise stage       
        stage = _stage;       
        
        //TITLE        
        titlePanel.setVisible(true); //reset the title panel with a new heading
        titleLabel.setText("Character Creation");
        
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
                
                promptText = new JLabel("Points Remaining: "+GUILogic.points);
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
                strMinus.addActionListener((ActionEvent e) -> GUILogic.updateAttribute(0, "str")); 
                    
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
                strPlus.addActionListener((ActionEvent e) -> GUILogic.updateAttribute(1, "str")); 
                        
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
                intMinus.addActionListener((ActionEvent e) -> GUILogic.updateAttribute(0, "int")); 
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
                intPlus.addActionListener((ActionEvent e) -> GUILogic.updateAttribute(1, "int")); 
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
                defMinus.addActionListener((ActionEvent e) -> GUILogic.updateAttribute(0, "def")); 
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
                defPlus.addActionListener((ActionEvent e) -> GUILogic.updateAttribute(1, "def")); 
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
                
                //initialise stats panel
                playerStatsCard = new JPanel(new GridLayout(8,1));
                playerStatsCard.setBounds((windowX/2) - (width/2), (windowY/2) - 100, width, height);
                playerStatsCard.setBackground(Color.white);
                playerStatsCard.setBorder(BorderFactory.createLineBorder(Color.black));
                container.add(playerStatsCard);
                createPlayerStatsCard(playerStatsCard);
            }
        }       
        
        //CONFIRM
        if(confirmPanel == null)
        {
            //reset size variables
            width = 120;
            height = 40;
        
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
                        if(!nameField.getText().equals(""))
                        {                            
                            GUILogic.createPlayer(nameField.getText(), 10);
                            characterCreation(1); 
                        }
                        else
                        {
                            JLabel boxText = new JLabel("You cannot leave your name blank.");
                            boxText.setFont(pixelFont.deriveFont(20f));
                            
                            JOptionPane.showMessageDialog(null, boxText, "Name Input Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    case 1 -> 
                    {
                        if(GUILogic.points > 0)
                        {
                            JLabel boxText = new JLabel("You have unspent attribute points, continue anyway?");
                            boxText.setFont(pixelFont.deriveFont(20f));
                            
                            int choice = JOptionPane.showConfirmDialog(null, boxText, "Unspent Attribute Points", JOptionPane.YES_NO_OPTION);
                            
                            if(choice == 0)
                            {
                                GUILogic.setPlayer();
                                characterCreation(2); 
                            }
                        }
                        else
                        {
                            GUILogic.setPlayer();
                            characterCreation(2); 
                        }
                    }
                    case 2 -> 
                    {                                            
                        GameManager.gameDataDB.writePlayerSaveData(); //save new player data if confirmed
                        createGameScene(); //continue to the main game scene
                    }
                }               
            }); 
        }    
        else
        {
            confirmPanel.setVisible(true);
        }
    }
    
    public static void exitCharacterCreation()
    {        
        if(titlePanel      != null) titlePanel.setVisible(false);    
        if(confirmPanel    != null) confirmPanel.setVisible(false);          
        if(namePanel       != null) namePanel.setVisible(false);     
        if(promptPanel     != null) promptPanel.setVisible(false);   
        if(statsPanel      != null) statsPanel.setVisible(false);    
        if(playerStatsCard != null) playerStatsCard.setVisible(false);    
    }   
     
    public static void createGameScene()
    {
        //disable unneeded GUI elements
        exitContinueScene();
        exitCharacterCreation();
        
        //initialise size variables
        width = 1000;
        height = 600;
        
        //initialise main text panel
        mainTextPanel = new JPanel(new GridLayout(1,1));
        mainTextPanel.setBounds(25, 25, width, height);        
        mainTextPanel.setBackground(Color.white);
        container.add(mainTextPanel);
        
        GUILogic.textAreaText = "<game started>";
        
        mainTextArea = new JTextArea();
        mainTextArea.setText(GUILogic.textAreaText);
        mainTextArea.setBackground(new Color(250,250,250)); //slightly off-white
        mainTextArea.setForeground(Color.black);
        mainTextArea.setFont(pixelFont);
        mainTextArea.setMargin(new Insets(10,10,10,10));
        mainTextArea.setLineWrap(true);
        mainTextArea.setEditable(false);        
        
        JScrollPane scrollTextArea = new JScrollPane(mainTextArea);
        mainTextPanel.add(scrollTextArea);     
        
        //GAME BUTTONS
        gameButtonPanel = new JPanel(new GridLayout(1, 4, 25, 0));
        gameButtonPanel.setBounds(25, 645, width, 50);
        container.add(gameButtonPanel);
        
        gameButton1 = new JButton("[ A ]");
        gameButton1.setBackground(Color.white);
        gameButton1.setForeground(Color.black);
        gameButton1.setFont(pixelFont);
        gameButton1.setFocusPainted(false);
        gameButtonPanel.add(gameButton1);        
        gameButton1.addActionListener(gameButtonHandler);
        gameButton1.setActionCommand("A");
        
        gameButton2 = new JButton("[ B ]");
        gameButton2.setBackground(Color.white);
        gameButton2.setForeground(Color.black);
        gameButton2.setFont(pixelFont);
        gameButton2.setFocusPainted(false);
        gameButtonPanel.add(gameButton2);
        gameButton2.addActionListener(gameButtonHandler);
        gameButton2.setActionCommand("B");
        
        gameButton3 = new JButton("[ C ]");
        gameButton3.setBackground(Color.white);
        gameButton3.setForeground(Color.black);
        gameButton3.setFont(pixelFont);
        gameButton3.setFocusPainted(false);
        gameButtonPanel.add(gameButton3);
        gameButton3.addActionListener(gameButtonHandler);
        gameButton3.setActionCommand("C");
        
        gameButton4 = new JButton("[ D ]");
        gameButton4.setBackground(Color.white);
        gameButton4.setForeground(Color.black);
        gameButton4.setFont(pixelFont);
        gameButton4.setFocusPainted(false);
        gameButtonPanel.add(gameButton4);
        gameButton4.addActionListener(gameButtonHandler);
        gameButton4.setActionCommand("D");
        
        //PLAYER STATS
        if(playerStatsCard == null)
        {
            playerStatsCard = new JPanel(new GridLayout(8,1));
            playerStatsCard.setBounds(width+50, 25, (width/5) - 20, height+70);
            playerStatsCard.setBackground(Color.white);
            playerStatsCard.setBorder(BorderFactory.createLineBorder(Color.black));
            container.add(playerStatsCard);
            createPlayerStatsCard(playerStatsCard);            
        }
        else
        {
            playerStatsCard.setBounds(width+50, 25, (width/5) - 20, height+70);
            playerStatsCard.setVisible(true);
            playerStatsCard.removeAll();
            createPlayerStatsCard(playerStatsCard);
        }
        
        GUILogic.townArea(1);
    }
    
    public static void exitGameScene()
    {
        if(mainTextPanel   != null) mainTextPanel.setVisible(false);
        if(gameButtonPanel != null) gameButtonPanel.setVisible(false);
        if(playerStatsCard != null) playerStatsCard.setVisible(false);
        if(buyPanel        != null) buyPanel.setVisible(false);
        if(sellPanel       != null) sellPanel.setVisible(false);
    }       
    
    public static void createCombatScene()
    {
        mainTextPanel.setVisible(false);
        
        //initialise variables
        inCombat = true;
        width = 1000;
        height = 600;
        
        if(combatPanel == null)
        {
            combatContainer = new JPanel();
            combatContainer.setBounds(25, 25, width, (2*height)/3);
            combatContainer.setBackground(Color.white);
            container.add(combatContainer);
            
            combatPanel = new JPanel(new GridLayout(1,3,30,0));
            combatPanel.setPreferredSize(new Dimension(width-200, (2*height)/3));
            combatPanel.setBackground(Color.white);
            combatContainer.add(combatPanel);
            
            playerPanel = new JPanel(new GridLayout(3,1));
            playerPanel.setBackground(Color.white);
            createPlayerCombatCard(playerPanel);
            combatPanel.add(playerPanel);            
            
            vsLabel = new JLabel("VERSUS");
            vsLabel.setForeground(Color.orange);
            vsLabel.setFont(pixelFont.deriveFont(3, 40f));
            vsLabel.setHorizontalAlignment(JLabel.CENTER);
            combatPanel.add(vsLabel);
            
            enemyPanel = new JPanel(new GridLayout(3,1));
            enemyPanel.setBackground(Color.white);
            combatPanel.add(enemyPanel);
            
            Enemy enemy = CombatLogic.currentEnemy;
            createEnemyCombatCard(enemy, enemyPanel);
        }
        else
        {
            combatContainer.setVisible(true);
            enemyPanel.removeAll();
            
            Enemy enemy = CombatLogic.currentEnemy;
            createEnemyCombatCard(enemy, enemyPanel);
        }
        
        if(combatTextPanel == null)
        {     
            combatTextPanel = new JPanel(new GridLayout(1,1));
            combatTextPanel.setBounds(25, 25 + (2*height)/3, width, height/3);
            combatTextPanel.setBorder(BorderFactory.createLineBorder(Color.black));
            combatTextPanel.setBackground(Color.red);
            container.add(combatTextPanel);
            
            CombatLogic.combatText = "What will you do?";
            
            combatTextArea = new JTextArea();
            combatTextArea.setForeground(Color.black);
            combatTextArea.setFont(pixelFont);
            combatTextArea.setText(CombatLogic.combatText);
            combatTextArea.setMargin(new Insets(10,10,10,10));
            combatTextArea.setLineWrap(true);
            combatTextArea.setEditable(false); 
            
            JScrollPane scrollTextArea = new JScrollPane(combatTextArea);
            combatTextPanel.add(scrollTextArea);
        }
        else
        {
            combatTextPanel.setVisible(true);
            CombatLogic.combatText = "What will you do?";
            combatTextArea.setText(CombatLogic.combatText);
        }
    }   
    
    public static void createPlayerCombatCard(JPanel parentPanel)
    {
        playerName = new JLabel("[ "+GameManager.player.getName()+" ]");
        playerName.setForeground(Color.blue);
        playerName.setFont(pixelFont.deriveFont(1, 28f));
        playerName.setHorizontalAlignment(JLabel.CENTER);
        parentPanel.add(playerName);
        
        playerHP = new JLabel(GameManager.player.getCurrentHP()+" / "+GameManager.player.getMaxHP());
        playerHP.setForeground(Color.black);
        playerHP.setFont(pixelFont.deriveFont(40f));
        playerHP.setHorizontalAlignment(JLabel.CENTER);
        parentPanel.add(playerHP);
        
        playerIcon = new JLabel();
        playerIcon.setIcon(new javax.swing.ImageIcon("./resources/player_icon.png"));
        playerIcon.setHorizontalAlignment(JLabel.CENTER);        
        parentPanel.add(playerIcon);
    }
    
    public static void createEnemyCombatCard(Enemy enemy, JPanel parentPanel)
    {
        enemyName = new JLabel("[ "+enemy.getName()+" ]");
        enemyName.setForeground(Color.red);
        enemyName.setFont(pixelFont.deriveFont(1, 28f));
        enemyName.setHorizontalAlignment(JLabel.CENTER);
        parentPanel.add(enemyName);
        
        enemyHP = new JLabel(enemy.getCurrentHP()+" / "+enemy.getMaxHP());
        enemyHP.setForeground(Color.black);
        enemyHP.setFont(pixelFont.deriveFont(40f));
        enemyHP.setHorizontalAlignment(JLabel.CENTER);
        parentPanel.add(enemyHP);
        
        enemyIcon = new JLabel();
        enemyIcon.setIcon(new javax.swing.ImageIcon("./resources/enemy_icon.png"));
        enemyIcon.setHorizontalAlignment(JLabel.CENTER);        
        parentPanel.add(enemyIcon);
    }
    
    public static void exitCombatScene()
    {
        inCombat = false;
        //if(playerStatsCard != null) GUILogic.updatePlayerCard();
        
        if(combatContainer != null) combatContainer.setVisible(false);
        if(combatTextPanel != null) combatTextPanel.setVisible(false);
        if(mainTextPanel   != null) mainTextPanel.setVisible(true);    
    }
    
    public static void createInventoryBox()
    {
        Object[] items = {"potion", "sword", "potion"};
        Object defaultSelection = items[0];
        
        JLabel boxText = new JLabel("Select an Item to use:");
        boxText.setFont(pixelFont.deriveFont(20f));
        
        UIManager.put("OptionPane.okButtonText", "Use");
        UIManager.put("OptionPane.cancelButtonText", "Exit");
        String playerInput = (String)JOptionPane.showInputDialog(null, boxText, "Inventory", JOptionPane.PLAIN_MESSAGE, null, items, defaultSelection);
        if(playerInput != null)
        { 
            if(inCombat == true)
            {
                CombatLogic.updateCombatTextArea("Used "+playerInput+"!");
            }            
            else
            {
                GUILogic.updateMainTextArea("Used "+playerInput+"!");
            }
        }
    }
    
    public static int createSavePrompt()
    {            
        JLabel boxText = new JLabel("Save data with this name already exists. Overwrite with this save data?");
        boxText.setFont(pixelFont.deriveFont(20f));
        
        return JOptionPane.showConfirmDialog(null, boxText, "Save Overwrite Warning", JOptionPane.YES_NO_OPTION);
    }
    
    public static void createBuyMenu()
    {
        mainTextPanel.setVisible(false);
        
        //initialise size variables
        width = 1000;
        height = 600;        
        
        if(buyPanel == null) //initialise buy panel if it doesnt exist
        {
            buyPanel = new JPanel(new GridLayout(3,3,10,10));
            buyPanel.setBounds(25, 25, width, height);
            buyPanel.setBackground(Color.white);
            buyPanel.setBorder(BorderFactory.createLineBorder(Color.black));
            container.add(buyPanel);
            
            JButton b1, b2, b3, b4, b5;
            
            b1 = new JButton("Item");
            b1.setBackground(Color.blue);
            b1.setForeground(Color.white);
            b1.setFont(pixelFont);
            buyPanel.add(b1);
            b2 = new JButton("Item [ BUY ]");
            b2.setBackground(Color.blue);
            b2.setForeground(Color.white);
            b2.setFont(pixelFont);
            buyPanel.add(b2);
            b3 = new JButton("Item");
            b3.setBackground(Color.blue);
            b3.setForeground(Color.white);
            b3.setFont(pixelFont);
            buyPanel.add(b3);
            b4 = new JButton("Item");
            b4.setBackground(Color.blue);
            b4.setForeground(Color.white);
            b4.setFont(pixelFont);
            buyPanel.add(b4);
            b5 = new JButton("Item");
            b5.setBackground(Color.blue);
            b5.setForeground(Color.white);
            b5.setFont(pixelFont);
            buyPanel.add(b5);
        }
        else //if the buy panel already exists, enable it
        {
            buyPanel.setVisible(true);
        }
    }
    
    public static void exitBuyMenu()
    {
        if(buyPanel      != null) buyPanel.setVisible(false);
        if(mainTextPanel != null) mainTextPanel.setVisible(true);
    }
    
    public static void createSellMenu()
    {
        mainTextPanel.setVisible(false);
        
        //initialise size variables
        width = 1000;
        height = 600;
        
        int sellItemsSize = 12;
        
        if(sellPanel == null || sellScrollPane == null) //initialise sell panel if it doesnt exist
        {
            sellPanel = new JPanel(new GridLayout(sellItemsSize/3,3,10,10));
            sellPanel.setPreferredSize(new Dimension(width, height));
            //sellPanel.setBounds(25, 25, width, height*3);
            sellPanel.setBackground(Color.white);
            sellPanel.setBorder(BorderFactory.createLineBorder(Color.black));
            
            sellScrollPane = new JScrollPane(sellPanel);
            sellPanel.setAutoscrolls(true);
            sellScrollPane.setBounds(25, 25, width, height);
            container.add(sellScrollPane);
            
            for(int i = 0; i < sellItemsSize; i++)
            {
                JButton b = new JButton("Button"+i);
                b.setBackground(Color.red);
                b.setForeground(Color.white);
                b.setFont(pixelFont);
                sellPanel.add(b);
            }
        }
        else //if the sell panel already exists, enable it
        {
            sellScrollPane.setVisible(true);
        }
    }
    
    public static void exitSellMenu()
    {        
        if(sellScrollPane != null) sellScrollPane.setVisible(false);
        if(mainTextPanel  != null) mainTextPanel.setVisible(true);
        //if(sellPanel != null) sellPanel.setVisible(false);
    }
    
    public static int gameOverPrompt()
    {
        Object[] options = {"Main Menu", "Quit!"};
        
        JLabel boxText = new JLabel("You Died... Thanks for playing!");
        boxText.setFont(pixelFont.deriveFont(20f));
        
        return JOptionPane.showOptionDialog(null, boxText, "GAME OVER", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE, null, options, options[0]);
    }
        
    static void createPlayerStatsCard(JPanel parentPanel)
    {
        playerNameLabel = new JLabel("[ Name ] Player");
        playerNameLabel.setForeground(Color.black);
        playerNameLabel.setFont(pixelFont);
        playerNameLabel.setHorizontalAlignment(JTextField.CENTER);
        playerNameLabel.setVerticalAlignment(JTextField.CENTER);
        parentPanel.add(playerNameLabel);
        
        hpLabel = new JLabel("[ HP ] 10 / 10");
        hpLabel.setForeground(Color.black);
        hpLabel.setFont(pixelFont);
        hpLabel.setHorizontalAlignment(JTextField.CENTER);
        hpLabel.setVerticalAlignment(JTextField.CENTER);
        parentPanel.add(hpLabel);
        
        strLabel = new JLabel("[ Strength ] 0");
        strLabel.setForeground(Color.black);
        strLabel.setFont(pixelFont);
        strLabel.setHorizontalAlignment(JTextField.CENTER);
        strLabel.setVerticalAlignment(JTextField.CENTER);
        parentPanel.add(strLabel);
        
        intLabel = new JLabel("[ Intellect ] 0");
        intLabel.setForeground(Color.black);
        intLabel.setFont(pixelFont);
        intLabel.setHorizontalAlignment(JTextField.CENTER);
        intLabel.setVerticalAlignment(JTextField.CENTER);
        parentPanel.add(intLabel);
        
        defLabel = new JLabel("[ Defence ] 0");
        defLabel.setForeground(Color.black);
        defLabel.setFont(pixelFont);
        defLabel.setHorizontalAlignment(JTextField.CENTER);
        defLabel.setVerticalAlignment(JTextField.CENTER);
        parentPanel.add(defLabel);
        
        levelLabel = new JLabel("[ Level ] 1");
        levelLabel.setForeground(Color.black);
        levelLabel.setFont(pixelFont);
        levelLabel.setHorizontalAlignment(JTextField.CENTER);
        levelLabel.setVerticalAlignment(JTextField.CENTER);
        parentPanel.add(levelLabel);
        
        xpLabel = new JLabel("[ XP ] 0");
        xpLabel.setForeground(Color.black);
        xpLabel.setFont(pixelFont);
        xpLabel.setHorizontalAlignment(JTextField.CENTER);
        xpLabel.setVerticalAlignment(JTextField.CENTER);
        parentPanel.add(xpLabel);
        
        goldLabel = new JLabel("[ Gold ] 0");
        goldLabel.setForeground(Color.black);
        goldLabel.setFont(pixelFont);
        goldLabel.setHorizontalAlignment(JTextField.CENTER);
        goldLabel.setVerticalAlignment(JTextField.CENTER);
        parentPanel.add(goldLabel);
        
        /*
        areaLabel = new JLabel("[ Area ] Town");
        areaLabel.setForeground(Color.black);
        areaLabel.setFont(pixelFont);
        areaLabel.setHorizontalAlignment(JTextField.CENTER);
        areaLabel.setVerticalAlignment(JTextField.CENTER);
        parentPanel.add(areaLabel);
        */
        
        GUILogic.updatePlayerCard();
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
