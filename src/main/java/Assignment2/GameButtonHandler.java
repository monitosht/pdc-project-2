package Assignment2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Monitosh Thaker | 17000777
 * COMP603 Assignment 2
 */
public class GameButtonHandler implements ActionListener
{
    @Override
    public void actionPerformed(ActionEvent e)
    {
        String selection = e.getActionCommand();
        
        switch(GUILogic.position)
        {
            case "Town":
                switch(selection)
                {
                    case "A" -> GUILogic.adventureArea(0);
                    case "B" -> GUILogic.innArea(0);
                    case "C" -> GUILogic.shopArea(0);
                    case "D" -> GameManager.gameDataDB.writePlayerSaveData();
                }
                break;                
            case "Inn":
                switch(selection)
                {
                    case "A" -> GUILogic.innArea(1);
                    case "B" -> GUILogic.innArea(2);
                }
                break;
            case "Shop":
                switch(selection)
                {
                    case "A" -> GUILogic.shopArea(1);
                    case "B" -> GUILogic.shopArea(2);
                    case "C" -> GUILogic.shopArea(3);
                }
                break;
            case "Adventure":
                switch(selection)
                {
                    case "A" -> GUILogic.adventureArea(1);
                    case "B" -> GUILogic.adventureArea(2);
                    case "C" -> GUILogic.adventureArea(3);
                }
                break;
            case "Combat":
                switch(selection)
                {
                    case "A" -> GUILogic.combatArea(1); //fight
                    case "B" -> GUILogic.combatArea(2); //inventory 
                    case "C" -> GUILogic.combatArea(3); //run
                }
                break;
            case "Return":
                switch(selection)
                {
                    case "A" -> GUILogic.returnEvent(1);
                }
                break;
            case "Continue":
            {
                switch(selection)
                {
                    case "A" -> GUILogic.continueEvent(1, GUILogic.continueChoice);
                }
                break;
            }
        }
    }   
}
