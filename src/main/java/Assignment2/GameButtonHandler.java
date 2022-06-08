package Assignment2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Monitosh Thaker | 17000777
 * COMP603 Assignment 1
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
                    case "A":
                        System.out.println("Selected 'Adventure'");
                        GameplayGUI.position = "Adventure";
                        GameplayGUI.updateGameButtonText();
                        break;
                    case "B":
                        System.out.println("Selected 'Rest'");
                        GameplayGUI.innArea(0);
                        break;
                    case "C":
                        System.out.println("Selected 'Shop'");
                        GameplayGUI.shopArea(0);
                        break;
                    case "D":
                        System.out.println("Selected 'Save'");
                        GameplayGUI.updateGameButtonText();
                        break;
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
                    case "A":
                        System.out.println("Selected 'Explore'");
                        GameplayGUI.position = "Combat";
                        GameplayGUI.updateGameButtonText();
                        break;
                    case "B":
                        System.out.println("Selected 'Inventory'");
                        GameplayGUI.updateGameButtonText();
                        break;
                    case "C":
                        System.out.println("Selected 'Town'");
                        GameplayGUI.position = "Town";
                        GameplayGUI.updateGameButtonText();
                        break;
                }
                break;
            case "Combat":
                switch(selection)
                {
                    case "A":
                        System.out.println("Selected 'Fight'");
                        GameplayGUI.updateGameButtonText();
                        break;
                    case "B":
                        System.out.println("Selected 'Inventory'");
                        GameplayGUI.updateGameButtonText();
                        break;
                    case "C":
                        System.out.println("Selected 'Run'");
                        GameplayGUI.position = "Adventure";
                        GameplayGUI.updateGameButtonText();
                        break;
                }
                break;
            case "Return":
                switch(selection)
                {
                    case "A":
                        System.out.println("Selected 'Exit'");
                        GameplayGUI.position = "Town";
                        GameplayGUI.updateGameButtonText();
                        break;
                }
                break;
        }
    }   
}
