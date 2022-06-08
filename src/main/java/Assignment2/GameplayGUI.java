package Assignment2;

import static Assignment2.SetupGUI.gameButton1;
import static Assignment2.SetupGUI.gameButton2;
import static Assignment2.SetupGUI.gameButton3;
import static Assignment2.SetupGUI.gameButton4;

/**
 * @author Monitosh Thaker | 17000777
 * COMP603 Assignment 1
 */
public class GameplayGUI 
{      
    static String textAreaText;    
    static String position;
    
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
                position = "Return";
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
                position = "Return";
                break;
            case 2: //sell
                updateMainTextArea("Shopkeeper: Lets see what you've got to offer...");
                position = "Return";
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
                updateMainTextArea("What will you do?");
                position = "Combat";
                break;
            case 2: //inventory
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
                break;
            case 2: //inventory
                break;
            case 3: //run
                updateMainTextArea("You safetely got away from the (enemy name).");
                //updateMainTextArea("\nCurrently at: (location name).");
                position = "Adventure";
                break;
        }        
        updateGameButtonText();
    }
    
    static void updateMainTextArea(String text)
    {
        textAreaText += "\n\n"+text;
        SetupGUI.mainTextArea.setText(textAreaText);
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
        }
    }
}
