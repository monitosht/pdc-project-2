package Assignment2;

import static Assignment2.GUIUpdate.updateMainTextArea;
import static Assignment2.GUIUpdate.updateGameButtonText;
import static Assignment2.GameManager.player;

/**
 * @author Monitosh Thaker | 17000777
 * COMP603 Assignment 2
 */
public class GUILogic 
{      
    //Variables
    static String position;    
    static int continueChoice;
    
    //Area methods
    static void townArea(int choice)
    {
        position = "Town";    
        GUIUpdate.updatePlayerCard();
        
        switch(choice)
        {
            case 0://arrive for the first time                
                if(GameManager.act == 0) StoryHandler.displayIntro(); //only display the prologue for new players
            case 1: 
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
    
    static void innArea(int choice)
    {
        position = "Inn";
        updateGameButtonText();
        
        switch(choice)
        {
            case 1: //yes
                restEvent();
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
        if(GameManager.checkGameCompleted()) return;
        
        position = "Adventure";
        
        updateGameButtonText();        
        GUIUpdate.updatePlayerCard();
        
        switch(choice)
        {
            case 1: //explore
                updateMainTextArea("You progress further into the "+GameManager.getCurrentLocation()+"...");
                
                CombatLogic.setRandomEnemy();
                updateMainTextArea("...\nEnemy encoutered! <"+CombatLogic.currentEnemy.getName()+">");
                continueChoice = 3;
                continueEvent(0, 0);
                break;
            case 2: //inventory
                GameManager.gui.inventoryPrompt();
                break;
            case 3: //town
                townArea(2);                
                break;
            case 4: //adventure
                updateMainTextArea("Returned to the "+GameManager.getCurrentLocation()+".");
                break;
            default:
                updateMainTextArea("Departed on an adventure to the "+GameManager.getCurrentLocation()+"!");
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
                if(CombatLogic.combatEvent(CombatLogic.currentEnemy))
                {
                    if(GameManager.levelledUp)
                    {
                        GameManager.levelledUp = false;
                        continueChoice = 6;
                        continueEvent(0,0); 
                    }
                    else
                    {
                        continueChoice = 5;
                        continueEvent(0,0);                        
                    }                    
                }
                else 
                {
                    continueChoice = 4;
                    continueEvent(0,0);
                }        
                break;
            case 2: //inventory
                GameManager.gui.inventoryPrompt();
                break;
            case 3: //run
                if(CombatLogic.runFromCombat(CombatLogic.currentEnemy)) 
                {
                    continueChoice = 5;
                    continueEvent(0,0);
                }
                else
                {
                    continueChoice = 4;
                    continueEvent(0,0);
                }
                break;
        }        
        updateGameButtonText();
    }
    
    //Event methods
    static void restEvent()
    {
        if((player.getGold() >= 25) && (player.getCurrentHP() != player.getMaxHP()))
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
    
    static void returnEvent(int choice)
    {
        position = "Return";
        updateGameButtonText();
        
        switch(choice)
        {
            case 1: //return
                GameManager.gui.exitBuyMenu();
                GameManager.gui.exitSellMenu();
                townArea(3);
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
                        GameManager.gui.createBuyMenu();
                        returnEvent(0);
                        break;
                    case 2: //sell menu
                        GameManager.gui.createSellMenu();
                        returnEvent(0);
                        break;
                    case 3: //entering combat
                        GameManager.gui.createCombatScene();
                        GUIUpdate.updatePlayerCombatCard();
                        combatArea(0);                       
                        break;
                    case 4: //combat event recap
                        combatArea(0); 
                        break;
                    case 5: //return from combat                        
                        GameManager.gui.exitCombatScene();                        
                        adventureArea(4);
                        break;
                    case 6: //force return to town
                        GameManager.gui.exitCombatScene();
                        //adventureArea(3);
                        townArea(1);
                        break;
                }
                break;                
        }
        updateGameButtonText();
    }    
}
