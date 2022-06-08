package Assignment2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author User
 */
public class GameButtonHandler implements ActionListener
{
    @Override
    public void actionPerformed(ActionEvent e)
    {
        String selection = e.getActionCommand();
        
        switch(SetupGUI.position)
        {
            case "Town":
                switch(selection)
                {
                    case "A":
                        System.out.println("Selected 'Adventure'");
                        SetupGUI.position = "Adventure";
                        SetupGUI.updateGameButtonText();
                        break;
                    case "B":
                        System.out.println("Selected 'Rest'");
                        SetupGUI.position = "Inn";                            
                        SetupGUI.updateGameButtonText();
                        break;
                    case "C":
                        System.out.println("Selected 'Shop'");
                        SetupGUI.position = "Shop";                            
                        SetupGUI.updateGameButtonText();
                        break;
                    case "D":
                        System.out.println("Selected 'Save'");
                        SetupGUI.updateGameButtonText();
                        break;
                }
                break;
                
            case "Inn":
                switch(selection)
                {
                    case "A":
                        System.out.println("Selected 'Yes'");
                        SetupGUI.position = "Menu";
                        SetupGUI.updateGameButtonText();
                        break;
                    case "B":
                        System.out.println("Selected 'No'");                            
                        SetupGUI.position = "Town";
                        SetupGUI.updateGameButtonText();
                        break;
                }
                break;
            case "Shop":
                switch(selection)
                {
                    case "A":
                        System.out.println("Selected 'Buy'");
                        SetupGUI.position = "Menu";
                        SetupGUI.updateGameButtonText();
                        break;
                    case "B":
                        System.out.println("Selected 'Sell'");
                        SetupGUI.position = "Menu";
                        SetupGUI.updateGameButtonText();
                        break;
                    case "C":
                        System.out.println("Selected 'Exit'");
                        SetupGUI.position = "Town";
                        SetupGUI.updateGameButtonText();
                        break;
                }
                break;
            case "Adventure":
                switch(selection)
                {
                    case "A":
                        System.out.println("Selected 'Explore'");
                        SetupGUI.position = "Combat";
                        SetupGUI.updateGameButtonText();
                        break;
                    case "B":
                        System.out.println("Selected 'Inventory'");
                        SetupGUI.updateGameButtonText();
                        break;
                    case "C":
                        System.out.println("Selected 'Town'");
                        SetupGUI.position = "Town";
                        SetupGUI.updateGameButtonText();
                        break;
                }
                break;
            case "Combat":
                switch(selection)
                {
                    case "A":
                        System.out.println("Selected 'Fight'");
                        SetupGUI.updateGameButtonText();
                        break;
                    case "B":
                        System.out.println("Selected 'Inventory'");
                        SetupGUI.updateGameButtonText();
                        break;
                    case "C":
                        System.out.println("Selected 'Run'");
                        SetupGUI.position = "Adventure";
                        SetupGUI.updateGameButtonText();
                        break;
                }
                break;
            case "Menu":
                switch(selection)
                {
                    case "A":
                        System.out.println("Selected 'Exit'");
                        SetupGUI.position = "Town";
                        SetupGUI.updateGameButtonText();
                        break;
                }
                break;
        }
    }   
}
