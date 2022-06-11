package Assignment2;

import static Assignment2.GUIUpdate.updateMainTextArea;
import static Assignment2.GUIUpdate.updateGameButtonText;
import static Assignment2.GameManager.player;

/**
 * @author Monitosh Thaker | 17000777
 * COMP603 Assignment 2
 */
public class GUIModel 
{      
    //Variables
    static int currentMenu;
    static boolean mainMenuActive;
    
    static String position;    
    static int continueChoice;
    
    /*
    * Area methods
    */
    static void townArea(int choice)
    {
        position = "Town";    
        GUIUpdate.updatePlayerCard();
        
        switch(choice)
        {
            case 0: //arrive for the first time                
                if(GameManager.act == 0) StoryHandler.displayIntro(); //only display the prologue for new players
            case 1: //fall through and display the story corresponding to the current act
                GameManager.act = player.getLevel();
                StoryHandler.progressStory(GameManager.act);
                updateMainTextArea("You have arrived at Town...");
                break;
            case 2: //returrned to town
                updateMainTextArea("Returned to Town.");
                break;
            case 3: //no prompt
                break;
        }      
        updateGameButtonText();
    }
    
    //Handes the logic for the two choices in the inn position.
    static void innArea(int choice)
    {
        position = "Inn";
        updateGameButtonText();
        
        switch(choice)
        {
            case 1 -> { //yes
                restEvent();
                townArea(3);
            }
            case 2 -> { //no
                updateMainTextArea("You decline the offer and leave the Inn.");
                townArea(3);
            }
            default -> updateMainTextArea("You enter the Town Inn.\nWould you like to rest for the night? (25 Gold)");
        }
        updateGameButtonText();
    }
    
    //Handes the logic for the three choices in the shop position.
    static void shopArea(int choice)
    {
        position = "Shop";        
        updateGameButtonText();
        
        switch(choice) //buy / sell / exit
        {
            case 1 -> { //buy
                updateMainTextArea("Shopkeeper: Take a look at our wide selection of items!");
                continueChoice = 1;
                continueEvent(0);
            }
            case 2 -> { //sell
                updateMainTextArea("Shopkeeper: Lets see what you've got to offer...");
                continueChoice = 2;
                continueEvent(0);
            }
            case 3 -> { //exit
                updateMainTextArea("You leave the store.");
                townArea(3);
            }
            default -> updateMainTextArea("Shopkeeper: Welcome to the Town General Store.\nWhat would you like to do?");
        }
        updateGameButtonText();
    }
    
    //Handes the logic for the three choices in the adventure position.
    static void adventureArea(int choice)
    {        
        if(GameManager.checkGameCompleted()) return;
        
        position = "Adventure";
        
        updateGameButtonText();        
        GUIUpdate.updatePlayerCard();
        
        switch(choice) //explore / town / inventory
        {
            case 1 -> { //adventure -> explore
                updateMainTextArea("You progress further into the "+GameManager.getCurrentLocation()+"...");
                
                CombatHandler.setRandomEnemy();
                updateMainTextArea("...\nEnemy encoutered! <"+CombatHandler.currentEnemy.getName()+">");
                continueChoice = 3;
                continueEvent(0);
            }
            case 2 -> GameManager.gui.inventoryPrompt(); //inventory
            case 3 -> townArea(2); //adventure -> town
            case 4 -> updateMainTextArea("Returned to the "+GameManager.getCurrentLocation()+"."); //combat -> adventure
            default -> updateMainTextArea("Departed on an adventure to the "+GameManager.getCurrentLocation()+"!"); //town -> adventure
        }
        updateGameButtonText();
    }
    
    //Handes the logic for the three choices in the combat position.
    static void combatArea(int choice)
    {
        position = "Combat";
        
        switch(choice) //fight / inventory / run
        {
            case 1 -> { //fight
                if(CombatHandler.combatEvent(CombatHandler.currentEnemy))
                {
                    if(GameManager.levelledUp)
                    {
                        GameManager.levelledUp = false;
                        continueChoice = 6; //continue button prompt
                        continueEvent(0); 
                    }
                    else
                    {
                        continueChoice = 5; //continue button prompt
                        continueEvent(0);
                    }                    
                }
                else 
                {
                    continueChoice = 4; //continue button prompt
                    continueEvent(0);
                }
            }
            case 2 -> { //inventory
                GameManager.gui.inventoryPrompt();
            }
            case 3 -> { //run
                if(CombatHandler.runFromCombat(CombatHandler.currentEnemy)) 
                {
                    continueChoice = 5; //continue button prompt
                    continueEvent(0);
                }
                else
                {
                    continueChoice = 4; //continue button prompt
                    continueEvent(0);
                }
            }
        }        
        updateGameButtonText();
    }
    
    /*
    * Event methods
    */
    static void restEvent()
    {
        if((player.getGold() >= 25) && (player.getCurrentHP() != player.getMaxHP())) //Check if the player can afford to rest.
        {
            player.setGold(player.getGold() - 25);
            player.setCurrentHP(player.getMaxHP());
            updateMainTextArea("You sleep soundly through the night and awaken well rested.\nYour health has been fully restored!");
            GUIUpdate.updatePlayerCombatCard();
        }
        else if(player.getCurrentHP() == player.getMaxHP())
        {
            // you're already at full health
            updateMainTextArea("Your energy is high and you do not feel the urge to rest (You are already at full health).");
        }
        else
        {
            //you cant afford to rent a room
            updateMainTextArea("You do not have enough gold to rent a room for the night... You leave the Inn.");
        }
    } 
    
    //If the player has sufficent gold, buy the item (add to inventory ArrayList).
    static void buyItemEvent(Item item)
    {
        if(player.getGold() >= item.getPrice()) 
        {
            player.setGold(player.getGold() - item.getPrice());
            GameManager.inventory.add(item);
            
            GameManager.gui.itemPrompt(item, 0);
            GUIUpdate.updatePlayerCard();
        }
        else
        {
            GameManager.gui.itemPrompt(item, 1);
        }
    }
    
    //Sell the item for its price (remove from inventory ArrayList).
    static void sellItemEvent(Item item) 
    {
        GameManager.inventory.remove(item);
        player.setGold(player.getGold() + item.getPrice());
        GameManager.gui.itemPrompt(item, 2);
        GUIUpdate.updatePlayerCard();
    }
    
    //Essenstially a "press any key to continue" method, pauses the game until the button is pressed.
    //Used specifically to return back to town from the buy/sell menus
    static void returnEvent(int choice) //choice = 0/default means enter the position and prompt to return
    {
        position = "Return";
        
        switch(choice)
        {
            case 1 -> { // exit
                GameManager.gui.exitBuyMenu();
                GameManager.gui.exitSellMenu();
                townArea(3);
            }
        } 
        updateGameButtonText();
    }
    
    //Essenstially a "press any key to continue" method, pauses the game until the button is pressed.
    //More flexible and handles calls from a number of different positions.
    static void continueEvent(int choice) //choice = 0/default means enter the position and prompt to continue
    {
        position = "Continue";
        
        switch(choice)
        {
            case 1 -> { //buy menu
                GameManager.gui.createBuyMenu();
                returnEvent(0);
            }
            case 2 -> { //sell menu
                GameManager.gui.createSellMenu();
                returnEvent(0);
            }
            case 3 -> { //entering combat
                GameManager.gui.createCombatScene();
                GUIUpdate.updatePlayerCombatCard();
                combatArea(0);
            }
            case 4 -> {//combat event recap
                combatArea(0);
            }
            case 5 -> { //return from combat
                GameManager.gui.exitCombatScene();                        
                adventureArea(4);
            }
            case 6 -> { //force return to town
                GameManager.gui.exitCombatScene();
                townArea(1);
            }
        }
        updateGameButtonText();
    }    
}
