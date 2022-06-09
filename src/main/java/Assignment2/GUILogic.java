package Assignment2;

import static Assignment2.GUISetup.gameButton1;
import static Assignment2.GUISetup.gameButton2;
import static Assignment2.GUISetup.gameButton3;
import static Assignment2.GUISetup.gameButton4;

/**
 * @author Monitosh Thaker | 17000777
 * COMP603 Assignment 2
 */
public class GUILogic 
{      
    //New game variables
    public static Player newPlayer;
    
    public static int points;
    public static int startPoints;
    
    //Attribute variables
    public static int strValue ;
    public static int intValue;
    public static int defValue;
    
    //Location / text / button variables
    static String textAreaText;    
    static String combatText;
    static String position;
    
    static int continueChoice;
    
    /*
    * New game / player methods
    */
    public static void createPlayer(String name, int _startPoints)
    {
        newPlayer = new Player(name);
        System.out.println("Player name set to "+name);
        
        startPoints = _startPoints;
        points = startPoints;
        
        strValue = 0;
        intValue = 0;
        defValue = 0;
    }
    
    public static void updateAttribute(int choice, String attribute)
    {
        switch(choice)
        {
            case 0: //decrease attribute value
                switch(attribute)
                {
                    case "str":
                        if(strValue > 0 && strValue <= startPoints)
                        {
                            strValue--;
                            points++; 
                        }
                        break;
                    case "int":
                        if(intValue > 0 && intValue <= startPoints)
                        {
                            intValue--;
                            points++; 
                        }
                        break;
                    case "def":
                        if(defValue > 0 && defValue <= startPoints)
                        {
                            defValue--;
                            points++; 
                        }
                        break;
                }
                break;
            case 1: //increase attribute value
                if(points > 0)
                {
                    switch(attribute)
                    {
                        case "str":
                            strValue++;
                            points--;
                            break;
                        case "int":
                            intValue++;
                            points--;
                            break;
                        case "def":
                            defValue++;
                            points--;
                            break;
                    }
                }
                break;
        }        
        GUISetup.promptText.setText("Points Remaining: "+points);
        GUISetup.strValue.setText(""+strValue);
        GUISetup.intValue.setText(""+intValue);
        GUISetup.defValue.setText(""+defValue);
    }
    
    public static void setPlayer()
    {
        newPlayer.setStrength(strValue);
        newPlayer.setIntellect(intValue);
        newPlayer.setDefence(defValue);        
        
        GameManager.player = newPlayer;
    }
    
    /*
    * Location / Button methods
    */    
    static void townArea(int choice)
    {
        position = "Town";        
        
        switch(choice)
        {
            case 1: //arrived for the first time
                updateMainTextArea("You have arrived at Town...");
                break;
            case 2: //returrned to town
                updateMainTextArea("Returned to Town.");
                break;
            default: //no message prompt     
                //updateMainTextArea("<Town>");
                break;
        }        
        updateGameButtonText();
    }
    
    static void innArea(int choice)
    {
        position = "Inn";
        updateGameButtonText();
        
        switch(choice)
        {
            case 1: //yes
                updateMainTextArea("You sleep soundly through the night and awaken well rested.\nYour health has been fully restored!");
                returnEvent(0);
                break;
            case 2: //no
                updateMainTextArea("You decline the offer and leave the Inn.");
                position = "Town";
                break;
            default:
                updateMainTextArea("You enter the Town Inn.\nWould you like to rest for the night? (25 Gold)");
                break;
        }
        updateGameButtonText();
    }
    
    static void shopArea(int choice)
    {
        position = "Shop";        
        updateGameButtonText();
        
        switch(choice)
        {
            case 1: //buy
                updateMainTextArea("Shopkeeper: Take a look at our wide selection of items!");
                continueChoice = 1;
                continueEvent(0, 0);
                
                break;
            case 2: //sell
                updateMainTextArea("Shopkeeper: Lets see what you've got to offer...");
                //SetupGUI.createSellMenu();
                //returnEvent(0);
                continueChoice = 2;
                continueEvent(0, 0);
                break;
            case 3: //exit
                updateMainTextArea("You leave the store.");
                position = "Town";
                break;
            default:
                updateMainTextArea("Shopkeeper: Welcome to the Town General Store.\nWhat would you like to do?");
                break;
        }
        updateGameButtonText();
    }
    
    static void adventureArea(int choice)
    {
        position = "Adventure";
        updateGameButtonText();
        
        switch(choice)
        {
            case 1: //explore
                updateMainTextArea("You progress further into the (location name)");
                updateMainTextArea("...\nEnemy encoutered!");
                continueChoice = 3;
                continueEvent(0, 0);
                //updateCombatTextArea("What will you do?");
                //position = "Combat";
                //SetupGUI.createCombatScene();
                break;
            case 2: //inventory
                GUISetup.createInventoryBox();
                break;
            case 3: //town
                updateMainTextArea("Returned to Town.");
                position = "Town";
                break;
            default:
                updateMainTextArea("Departed on an adventure at (location name)!");
                break;
        }
        updateGameButtonText();
    }
    
    static void combatArea(int choice)
    {
        position = "Combat";
        updateGameButtonText();
        
        switch(choice)
        {
            case 1: //fight
                updateCombatTextArea("The (enemy name) has been defeated!\n[ You earned 0 XP & 0 Gold ]");                  
                continueChoice = 4;
                continueEvent(0, 0);
                break;
            case 2: //inventory
                GUISetup.createInventoryBox();
                break;
            case 3: //run
                updateCombatTextArea("You safetely got away from the (enemy name).");             
                continueChoice = 5;
                continueEvent(0, 0);
                break;
        }        
        updateGameButtonText();
    }
    
    static void returnEvent(int choice)
    {
        position = "Return";
        updateGameButtonText();
        
        switch(choice)
        {
            case 1: //return
                GUISetup.exitBuyMenu();
                GUISetup.exitSellMenu();
                townArea(0);
                break;
            default:
                break;
        } 
    }
    
    static void continueEvent(int choice, int choice2)
    {
        position = "Continue";
        
        switch(choice)
        {
            case 1: //continue
                switch(choice2)
                {
                    case 1: //buy menu
                        GUISetup.createBuyMenu();
                        returnEvent(0);
                        break;
                    case 2: //sell menu
                        GUISetup.createSellMenu();
                        returnEvent(0);
                        break;
                    case 3: //entering combat
                        GUISetup.createCombatScene();
                        position = "Combat";                        
                        break;
                    case 4: //return from won combat                        
                        GUISetup.exitCombatScene();
                        position = "Adventure";
                        break;
                    case 5: //run from combat
                        GUISetup.exitCombatScene();
                        position = "Adventure";                        
                        break;
                }
                break;                
        }
        updateGameButtonText();
    }
    
    static void updateMainTextArea(String text)
    {
        if(GUISetup.mainTextArea == null) 
            return;
        
        textAreaText += "\n\n"+text;
        GUISetup.mainTextArea.setText(textAreaText);
    }
    
    static void updateCombatTextArea(String text)
    {
        if(GUISetup.combatTextArea == null) 
            return;
        
        combatText += "\n\n"+text;
        GUISetup.combatTextArea.setText(combatText);
    }
    
    static void updateGameButtonText()
    {
        switch(position)
        {
            case "Town":
                gameButton1.setText("( Adventure )");
                gameButton2.setText("( Rest )");
                gameButton3.setText("( Shop )");
                gameButton4.setText("( Save )");
                break;
            case "Inn":
                gameButton1.setText("( Yes )");
                gameButton2.setText("( No )");
                gameButton3.setText("");
                gameButton4.setText("");
                break;
            case "Shop":
                gameButton1.setText("( Buy )");
                gameButton2.setText("( Sell )");
                gameButton3.setText("( Exit )");
                gameButton4.setText("");
                break;
            case "Adventure":
                gameButton1.setText("( Explore )");
                gameButton2.setText("( Inventory )");
                gameButton3.setText("( Town )");
                gameButton4.setText("");
                break;
            case "Combat":
                gameButton1.setText("( Fight )");
                gameButton2.setText("( Inventory )");
                gameButton3.setText("( Run )");
                gameButton4.setText("");
                break;
            case "Return":
                gameButton1.setText("( Exit )");
                gameButton2.setText("");
                gameButton3.setText("");
                gameButton4.setText("");
                break;
            case "Continue":
                gameButton1.setText("( Continue )");
                gameButton2.setText("");
                gameButton3.setText("");
                gameButton4.setText("");
        }
    }
    
    static void updatePlayerCard()
    {
        GUISetup.playerNameLabel.setText(""+GameManager.player.getName());
        GUISetup.hpLabel.setText("[ HP ] "+GameManager.player.getCurrentHP()+" / "+GameManager.player.getMaxHP());
        GUISetup.strLabel.setText("[ STRENGTH ] "+GameManager.player.getStrength());
        GUISetup.intLabel.setText("[ INTELLECT ] "+GameManager.player.getIntellect());
        GUISetup.defLabel.setText("[ DEFENCE ] "+GameManager.player.getDefence());
        GUISetup.levelLabel.setText("[ LEVEL ] "+GameManager.player.getLevel());
        GUISetup.xpLabel.setText("[ XP ] "+GameManager.player.getXP());
        GUISetup.goldLabel.setText("[ GOLD ] "+GameManager.player.getGold());
    }
}
