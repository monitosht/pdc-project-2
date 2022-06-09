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
        
        switch(GameplayGUI.position)
        {
            case "Town":
                switch(selection)
                {
                    case "A" -> GameplayGUI.adventureArea(0);
                    case "B" -> GameplayGUI.innArea(0);
                    case "C" -> GameplayGUI.shopArea(0);
                    case "D" -> System.out.println("Selected 'Save'");
                }
                break;                
            case "Inn":
                switch(selection)
                {
                    case "A" -> GameplayGUI.innArea(1);
                    case "B" -> GameplayGUI.innArea(2);
                }
                break;
            case "Shop":
                switch(selection)
                {
                    case "A" -> GameplayGUI.shopArea(1);
                    case "B" -> GameplayGUI.shopArea(2);
                    case "C" -> GameplayGUI.shopArea(3);
                }
                break;
            case "Adventure":
                switch(selection)
                {
                    case "A" -> GameplayGUI.adventureArea(1);
                    case "B" -> GameplayGUI.adventureArea(2);
                    case "C" -> GameplayGUI.adventureArea(3);
                }
                break;
            case "Combat":
                switch(selection)
                {
                    case "A" -> GameplayGUI.combatArea(1);
                    case "B" -> GameplayGUI.combatArea(2);
                    case "C" -> GameplayGUI.combatArea(3);
                }
                break;
            case "Return":
                switch(selection)
                {
                    case "A" -> GameplayGUI.returnEvent(1);
                }
                break;
            case "Continue":
            {
                switch(selection)
                {
                    case "A" -> GameplayGUI.continueEvent(1, GameplayGUI.continueChoice);
                }
                break;
            }
        }
    }   
}
