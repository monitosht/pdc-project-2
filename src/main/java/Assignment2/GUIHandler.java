package Assignment2;

import java.io.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;

/**
 * @author Monitosh Thaker | 17000777
 * COMP603 Assignment 2
 */
public class GUIHandler 
{ 
    // <editor-fold defaultstate="collapsed" desc="Game Window Variables">
    
    int windowX = 1280; 
    int windowY = 800;
    JFrame gameWindow;
    Container container;   
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Main Menu Variables">   
    
    int width, height;
    
    JPanel titlePanel;
    JLabel titleLabel;
    
    JPanel buttonPanel;
    JButton newGameButton;
    JButton continueButton;
    JButton creditsButton;
    JButton quitButton;  
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Continue Scene Variables">  
    
    JPanel savePanel;
    JScrollPane saveScrollPane;
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Credits Scene Variables"> 
    
    JPanel creditsPanel; 
    JTextArea creditsText;
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Constant Buttons Variables">  
    
    JPanel constMenuPanel;
    JButton constMenuButton;    
    
    JPanel constTitlePanel;
    JLabel constTitleText;
    
    JPanel constQuitPanel;
    JButton constQuitButton;
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Character Creation Variables">  
    
    int stage;    
    JPanel confirmPanel;    
    JButton confirmButton;
    
    //cc stage 0
    JPanel namePanel;
    JLabel nameText;
    JTextField nameField;
    JButton nameButton;
    
    //cc stage 1
    JPanel promptPanel;
    JLabel promptText;
    
    JPanel statsPanel;    
    JLabel strText, strValue;
    JButton strMinus, strPlus;
    JLabel intText, intValue;
    JButton intMinus, intPlus;
    JLabel defText, defValue;
    JButton defMinus, defPlus; 
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Game Scene Variables">         
    
    JPanel mainTextPanel;
    JTextArea mainTextArea; 
    
    JPanel gameButtonPanel;
    JButton gameButton1, gameButton2, gameButton3, gameButton4;
    GameButtonHandler gameButtonHandler = new GameButtonHandler();
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Combat Scene Variables">  
    
    boolean inCombat = false;
    JPanel combatContainer;    
    JPanel combatPanel;
    JPanel playerPanel;
    JLabel vsLabel;
    JPanel enemyPanel;
    
    JPanel combatTextPanel;
    JTextArea combatTextArea;
    
    JLabel playerName, playerHP, playerIcon;
    JLabel enemyName, enemyHP, enemyIcon;
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Shop/Item Variables">  
    
    JPanel buyPanel;
    JPanel sellPanel;
    JScrollPane sellScrollPane;
    
    //INVENTORY POPUP
    JOptionPane inventoryBox;
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Player Card Variables">  
    
    JPanel playerStatsCard;
    JLabel playerNameLabel, hpLabel;
    JLabel strLabel, intLabel, defLabel;
    JLabel levelLabel, xpLabel, goldLabel;
    JLabel locationLabel;
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Font Variables">
    
    Font pixelFont = createCustomFont();
    Font titleFont = new Font("Arial", Font.PLAIN, 70);
    Font normalFont = new Font("Times New Roman", Font.PLAIN, 26); 
    
    // </editor-fold>
    
    //Initialise JFrame / Game Window 
    GUIHandler()
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
        
        createMainMenu();
    }
    
    //Main Menu methods
    public void createMainMenu()
    {      
        //disable uneeded GUI elements
        exitBuyMenu();
        exitSellMenu();
        exitCombatScene();
        exitGameScene();
        exitCharacterCreation();   
        exitCreditsScene();
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
            createCreditsScene();
            constantButtons();
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
    
    public void exitMainMenu()
    {
        titlePanel.setVisible(false);
        buttonPanel.setVisible(false);
    }    
    
    //Continue Scene methods
    public void createContinueScene()
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
    
    public void createContinueButtons()
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
            
            button.addActionListener((ActionEvent e) -> GameManager.gameDataDB.loadSaveData(GameManager.saves.get(final_i)));
            
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
    
    public void exitContinueScene()
    {
        if(titlePanel     != null) titlePanel.setVisible(false);  
        if(saveScrollPane != null) saveScrollPane.setVisible(false);  
        if(savePanel      != null) savePanel.setVisible(false);  
    }
    
    //Credits Scene methods
    public void createCreditsScene()
    {
        //disable unneeded GUI elements
        exitMainMenu();
        
        //TITLE        
        titlePanel.setVisible(true); //reset the title panel with a new heading
        titleLabel.setText("Credits");
        
        width = 800;
        height = 450;
        
        creditsPanel = new JPanel(new GridLayout(1,1));
        creditsPanel.setBounds((windowX/2 - width/2), (windowY/2) - (height/2) + 50, width, height);
        creditsPanel.setBackground(Color.white);
        creditsPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        container.add(creditsPanel);
        
        creditsText = new JTextArea("Test");
        creditsText.setForeground(Color.black);
        creditsText.setFont(pixelFont.deriveFont(22f));
        creditsText.setMargin(new Insets(10,10,10,10));
        creditsText.setLineWrap(true);
        creditsText.setEditable(false);        
        creditsPanel.add(creditsText);
        
        String text =
                """
                Welcome to Moni's RPG Adventure!
                --------------------------------------------
                
                Instructions:
                [1] Press the correct (button) that corresponds to the action you would like to take.
                [2] When in battle, you will take turns to damage the enemy, use items or try and 
                escape. You can defeat your enemy by reducing their health to 0.
                [3] If your health is reduced to 0 at any point, the game will be over, your player will
                permanently die and your save file will be deleted.
                [4] Any items that you find or purchase will go into your inventory from which 
                they can only be used (consumed/equipped) during an adventure or battle.
                [5] The item inventory is global and can be accessed by any player save data.
                [6] The game must be saved manually whenever the player wishes to. 
                You can find the (save) button in the main <Town> area.
                [7] The goal of the game is to make your way through the world, leveling up through 
                adventuring until you can face and defeat the final boss!
                
                Credits: Program & art made by Monitosh Thaker | 17000777 | COMP603 Assignment 2
                """;
        
        creditsText.setText(text);
    }
    
    public void exitCreditsScene()
    {
        if(creditsPanel != null) creditsPanel.setVisible(false);
    }
    
    //Constant Buttons methods
    public void constantButtons()
    {
        //initialise size varibles
        width = 120;
        height = 30;
        
        //MAIN MENU
        constMenuPanel = new JPanel(new GridBagLayout());
        constMenuPanel.setBounds(25,windowY-height*2-25,width,height);
        container.add(constMenuPanel);
        
        constMenuButton = new JButton("Main Menu");
        constMenuButton.setBackground(Color.white);
        constMenuButton.setForeground(Color.black);
        constMenuButton.setFont(pixelFont.deriveFont(20f));
        constMenuButton.setFocusPainted(false);
        constMenuPanel.add(constMenuButton);
        
        constMenuButton.addActionListener((ActionEvent e) -> 
        {
            if(confirmQuitPrompt() == 0)
                createMainMenu();      
        });
        
        //QUIT
        constQuitPanel = new JPanel(new GridBagLayout());
        constQuitPanel.setBounds(windowX-width-25,windowY-height*2-25,width,height);
        container.add(constQuitPanel);
        
        constQuitButton = new JButton("Quit");
        constQuitButton.setBackground(Color.white);
        constQuitButton.setForeground(Color.black);
        constQuitButton.setFont(pixelFont.deriveFont(20f));
        constQuitButton.setFocusPainted(false);
        constQuitPanel.add(constQuitButton);
        
        constQuitButton.addActionListener((ActionEvent e) ->
        {
            if(confirmQuitPrompt() == 0)
                System.exit(0);                
        });
        
        //reset size variables
        width = 240;
        height = 30;
        
        //MINI TITLE
        constTitlePanel = new JPanel(new GridBagLayout());
        constTitlePanel.setBounds((windowX/2) - (width/2), windowY-height*2-25, width, height);
        container.add(constTitlePanel);
        
        constTitleText = new JLabel("Moni's RPG Adventure v0.1");
        constTitleText.setForeground(Color.black);
        constTitleText.setFont(pixelFont.deriveFont(2, 20f));
        constTitlePanel.add(constTitleText);
    }
    
    public void disableConstantButtons()
    {
        if(constMenuPanel  != null) constMenuPanel.setVisible(false);
        if(constTitlePanel != null) constTitlePanel.setVisible(false);
        if(constQuitPanel  != null) constQuitPanel.setVisible(false);
    }    
    
    //Character Creation methods
    public void characterCreation(int _stage)
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
                
                promptText = new JLabel("Points Remaining: "+NewGameLogic.points);
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
                strMinus.addActionListener((ActionEvent e) -> NewGameLogic.updateAttribute(0, "str")); 
                    
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
                strPlus.addActionListener((ActionEvent e) -> NewGameLogic.updateAttribute(1, "str")); 
                        
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
                intMinus.addActionListener((ActionEvent e) -> NewGameLogic.updateAttribute(0, "int")); 
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
                intPlus.addActionListener((ActionEvent e) -> NewGameLogic.updateAttribute(1, "int")); 
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
                defMinus.addActionListener((ActionEvent e) -> NewGameLogic.updateAttribute(0, "def")); 
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
                defPlus.addActionListener((ActionEvent e) -> NewGameLogic.updateAttribute(1, "def")); 
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
                playerStatsCard = new JPanel(new GridLayout(9,1));
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
                            NewGameLogic.createPlayer(nameField.getText(), 10);
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
                        if(NewGameLogic.points > 0)
                        {
                            JLabel boxText = new JLabel("You have unspent attribute points, continue anyway?");
                            boxText.setFont(pixelFont.deriveFont(20f));
                            
                            int choice = JOptionPane.showConfirmDialog(null, boxText, "Unspent Attribute Points", JOptionPane.YES_NO_OPTION);
                            
                            if(choice == 0)
                            {
                                NewGameLogic.setPlayer();
                                characterCreation(2); 
                            }
                        }
                        else
                        {
                            NewGameLogic.setPlayer();
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
    
    public void exitCharacterCreation()
    {        
        if(titlePanel      != null) titlePanel.setVisible(false);    
        if(confirmPanel    != null) confirmPanel.setVisible(false);          
        if(namePanel       != null) namePanel.setVisible(false);     
        if(promptPanel     != null) promptPanel.setVisible(false);   
        if(statsPanel      != null) statsPanel.setVisible(false);    
        if(playerStatsCard != null) playerStatsCard.setVisible(false);    
    }   
     
    //Main Game Scene methods
    public void createGameScene()
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
        
        GUIUpdate.textAreaText = "<game started>";
        
        mainTextArea = new JTextArea();
        mainTextArea.setText(GUIUpdate.textAreaText);
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
            playerStatsCard = new JPanel(new GridLayout(9,1));
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
        
        GUILogic.townArea(0);
    }
    
    public void exitGameScene()
    {
        if(mainTextPanel   != null) mainTextPanel.setVisible(false);
        if(gameButtonPanel != null) gameButtonPanel.setVisible(false);
        if(playerStatsCard != null) playerStatsCard.setVisible(false);
        if(buyPanel        != null) buyPanel.setVisible(false);
        if(sellPanel       != null) sellPanel.setVisible(false);
    }       
    
    //Combat Scene methods
    public void createCombatScene()
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
        
        GUIUpdate.combatText = "What will you do?";
        
        if(combatTextPanel == null)
        {     
            combatTextPanel = new JPanel(new GridLayout(1,1));
            combatTextPanel.setBounds(25, 25 + (2*height)/3, width, height/3);
            combatTextPanel.setBorder(BorderFactory.createLineBorder(Color.black));
            combatTextPanel.setBackground(Color.red);
            container.add(combatTextPanel);
            
            combatTextArea = new JTextArea();
            combatTextArea.setForeground(Color.black);
            combatTextArea.setFont(pixelFont);
            combatTextArea.setText(GUIUpdate.combatText);
            combatTextArea.setMargin(new Insets(10,10,10,10));
            combatTextArea.setLineWrap(true);
            combatTextArea.setEditable(false); 
            
            JScrollPane scrollTextArea = new JScrollPane(combatTextArea);
            combatTextPanel.add(scrollTextArea);
        }
        else
        {
            combatTextPanel.setVisible(true);
            combatTextArea.setText(GUIUpdate.combatText);
        }
    }   
    
    public void createPlayerCombatCard(JPanel parentPanel)
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
    
    public void createEnemyCombatCard(Enemy enemy, JPanel parentPanel)
    {
        enemyName = new JLabel("[ "+enemy.getName()+" ]");
        enemyName.setForeground(Color.red);
        enemyName.setFont(pixelFont.deriveFont(1, 28f));
        enemyName.setHorizontalAlignment(JLabel.CENTER);
        parentPanel.add(enemyName);
        
        enemyHP = new JLabel(" [ HP ] "+enemy.getCurrentHP()+" / "+enemy.getMaxHP());
        enemyHP.setForeground(Color.black);
        enemyHP.setFont(pixelFont.deriveFont(40f));
        enemyHP.setHorizontalAlignment(JLabel.CENTER);
        parentPanel.add(enemyHP);
        
        enemyIcon = new JLabel();
        enemyIcon.setIcon(new javax.swing.ImageIcon("./resources/enemy_icon.png"));
        enemyIcon.setHorizontalAlignment(JLabel.CENTER);        
        parentPanel.add(enemyIcon);
    }
    
    public void exitCombatScene()
    {
        inCombat = false;
        //if(playerStatsCard != null) GUILogic.updatePlayerCard();
        
        if(combatContainer != null) combatContainer.setVisible(false);
        if(combatTextPanel != null) combatTextPanel.setVisible(false);
        if(mainTextPanel   != null) mainTextPanel.setVisible(true);    
    }
    
    //Shop methods
    public void createBuyMenu()
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
    
    public void exitBuyMenu()
    {
        if(buyPanel      != null) buyPanel.setVisible(false);
        if(mainTextPanel != null) mainTextPanel.setVisible(true);
    }
    
    public void createSellMenu()
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
    
    public void exitSellMenu()
    {        
        if(sellScrollPane != null) sellScrollPane.setVisible(false);
        if(mainTextPanel  != null) mainTextPanel.setVisible(true);
    }
    
    //Dialog Prompt methods
    public int savePrompt()
    {            
        JLabel boxText = new JLabel("Save data with this name already exists. Overwrite with this save data?");
        boxText.setFont(pixelFont.deriveFont(20f));
        
        return JOptionPane.showConfirmDialog(null, boxText, "Save Overwrite Warning", JOptionPane.YES_NO_OPTION);
    }
    
    public int confirmQuitPrompt()
    {
        JLabel boxText = new JLabel("Unsaved data will be lost. Continue?");
        boxText.setFont(pixelFont.deriveFont(20f));
        
        return JOptionPane.showConfirmDialog(null, boxText, "Confirm Quit", JOptionPane.ERROR_MESSAGE, JOptionPane.YES_NO_OPTION);
    }
    
    public void inventoryPrompt()
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
                GUIUpdate.updateCombatTextArea("Used "+playerInput+"!");
            else
                GUIUpdate.updateMainTextArea("Used "+playerInput+"!");
        }
    }
    
    public int gameOverPrompt()
    {
        Object[] options = {"Main Menu", "Quit!"};
        
        JLabel boxText = new JLabel("You Died... Thanks for playing!");
        boxText.setFont(pixelFont.deriveFont(20f));
        
        return JOptionPane.showOptionDialog(null, boxText, "Game Over", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE, null, options, options[0]);
    }
    
    public void gameCompletedPrompt()
    {
        JLabel boxText = new JLabel("You have rid the town of all evil - the game has been completed!");
        boxText.setFont(pixelFont.deriveFont(20f));
        
        UIManager.put("OptionPane.okButtonText", "Ok");        
        JOptionPane.showMessageDialog(null, boxText, "Game Completed", JOptionPane.INFORMATION_MESSAGE);
    }  
        
    //Reuseability methods
    public void createPlayerStatsCard(JPanel parentPanel)
    {
        playerNameLabel = new JLabel("[ Name ]");
        playerNameLabel.setForeground(Color.black);
        playerNameLabel.setFont(pixelFont.deriveFont(1));
        playerNameLabel.setHorizontalAlignment(JTextField.CENTER);
        playerNameLabel.setVerticalAlignment(JTextField.CENTER);
        parentPanel.add(playerNameLabel);
        
        locationLabel = new JLabel("[ Location ]");
        locationLabel.setForeground(Color.black);
        locationLabel.setFont(pixelFont);
        locationLabel.setHorizontalAlignment(JTextField.CENTER);
        locationLabel.setVerticalAlignment(JTextField.CENTER);
        parentPanel.add(locationLabel);
        
        hpLabel = new JLabel("[ HP ]");
        hpLabel.setForeground(Color.black);
        hpLabel.setFont(pixelFont);
        hpLabel.setHorizontalAlignment(JTextField.CENTER);
        hpLabel.setVerticalAlignment(JTextField.CENTER);
        parentPanel.add(hpLabel);
        
        strLabel = new JLabel("[ Strength ]");
        strLabel.setForeground(Color.black);
        strLabel.setFont(pixelFont);
        strLabel.setHorizontalAlignment(JTextField.CENTER);
        strLabel.setVerticalAlignment(JTextField.CENTER);
        parentPanel.add(strLabel);
        
        intLabel = new JLabel("[ Intellect ]");
        intLabel.setForeground(Color.black);
        intLabel.setFont(pixelFont);
        intLabel.setHorizontalAlignment(JTextField.CENTER);
        intLabel.setVerticalAlignment(JTextField.CENTER);
        parentPanel.add(intLabel);
        
        defLabel = new JLabel("[ Defence ]");
        defLabel.setForeground(Color.black);
        defLabel.setFont(pixelFont);
        defLabel.setHorizontalAlignment(JTextField.CENTER);
        defLabel.setVerticalAlignment(JTextField.CENTER);
        parentPanel.add(defLabel);
        
        levelLabel = new JLabel("[ Level ]");
        levelLabel.setForeground(Color.black);
        levelLabel.setFont(pixelFont);
        levelLabel.setHorizontalAlignment(JTextField.CENTER);
        levelLabel.setVerticalAlignment(JTextField.CENTER);
        parentPanel.add(levelLabel);
        
        xpLabel = new JLabel("[ XP ]");
        xpLabel.setForeground(Color.black);
        xpLabel.setFont(pixelFont);
        xpLabel.setHorizontalAlignment(JTextField.CENTER);
        xpLabel.setVerticalAlignment(JTextField.CENTER);
        parentPanel.add(xpLabel);
        
        goldLabel = new JLabel("[ Gold ]");
        goldLabel.setForeground(Color.black);
        goldLabel.setFont(pixelFont);
        goldLabel.setHorizontalAlignment(JTextField.CENTER);
        goldLabel.setVerticalAlignment(JTextField.CENTER);
        parentPanel.add(goldLabel);
        
        GUIUpdate.updatePlayerCard();
    } 
    
    //Graphics methods
    Font createCustomFont()
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
