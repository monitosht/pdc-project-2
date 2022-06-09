package Assignment2;

import static Assignment2.GameManager.player;
import static Assignment2.GUILogic.updateMainTextArea;

/**
 * @author Monitosh Thaker | 17000777
 * COMP603 Assignment 2
 */
public class GameLogic 
{
    static void restEvent()
    {
        if((player.getGold() > 25) && (player.getCurrentHP() != player.getMaxHP()))
        {
            player.setGold(player.getGold() - 25);
            player.setCurrentHP(player.getMaxHP());
            updateMainTextArea("You sleep soundly through the night and awaken well rested.\nYour health has been fully restored!");
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
}
