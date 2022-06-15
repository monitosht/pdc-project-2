package Assignment2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Monitosh Thaker | 17000777
 * COMP603 Assignment 2
 */
public class GUIController implements ActionListener
{
    @Override
    public void actionPerformed(ActionEvent e)
    {
        String selection = e.getActionCommand();
        
        switch(GUIModel.currentMenu)
        {
            case 0: //Main menu button outcomes.
                switch(selection)
                {
                    case "New Game Button" -> GameManager.gui.characterCreation(0);
                    case "Continue Button" -> GameManager.gui.createContinueScene();
                    case "Credits Button"  -> GameManager.gui.createCreditsScene();
                    case "Quit Button"     -> System.exit(0);
                }                
                break;
            case 1: //Character creation button outcomes.
                switch(selection)
                {
                    case "Strength -"  -> CreatePlayer.updateAttribute(0, "str");
                    case "Strength +"  -> CreatePlayer.updateAttribute(1, "str");
                    case "Intellect -" -> CreatePlayer.updateAttribute(0, "int");
                    case "Intellect +" -> CreatePlayer.updateAttribute(1, "int");
                    case "Defence -"   -> CreatePlayer.updateAttribute(0, "def");
                    case "Defence +"   -> CreatePlayer.updateAttribute(1, "def");

                    case "Confirm Button 1" -> 
                    {
                        if(!GameManager.gui.nameField.getText().equals("")) //Check if the player name was left blank.
                        {      
                            CreatePlayer.createPlayer(GameManager.gui.nameField.getText(), 10);
                            GameManager.gui.characterCreation(1);
                        }                  
                        else
                        {
                            GameManager.gui.invalidNamePrompt();
                        }
                    }
                    case "Confirm Button 2" -> 
                    {
                        if(CreatePlayer.points > 0)
                        {          
                            int choice = GameManager.gui.unspentPointsPrompt(); //Check if there are unspent attribute points.
                            
                            if(choice != 0)
                            {
                                return;
                            }
                        }
                        CreatePlayer.setPlayer();
                        GameManager.gui.characterCreation(2);
                    }
                    case "Confirm Button 3" -> 
                    {
                        GameManager.gameDataDB.saveGame(); //Save new player data if confirmed.
                        GameManager.gui.createGameScene(); //Continue to the main game scene.
                    }
                }
                break;
            case 2: //Game scene button outcomes depending on the current position of the player.
                switch(GUIModel.position)
                {
                    case "Town":
                        switch(selection)
                        {
                            case "A" -> GUIModel.adventureArea(0);         //adventure
                            case "B" -> GUIModel.innArea(0);               //rest
                            case "C" -> GUIModel.shopArea(0);              //shop
                            case "D" -> GameManager.gameDataDB.saveGame(); //save
                        }
                        break;                
                    case "Inn":
                        switch(selection)
                        {
                            case "A" -> GUIModel.innArea(1); //yes
                            case "B" -> GUIModel.innArea(2); //no
                        }
                        break;
                    case "Shop":
                        switch(selection)
                        {
                            case "A" -> GUIModel.shopArea(1); //buy
                            case "B" -> GUIModel.shopArea(2); //sell
                            case "C" -> GUIModel.shopArea(3); //exit
                        }
                        break;
                    case "Adventure":
                        switch(selection)
                        {
                            case "A" -> GUIModel.adventureArea(1); //explore
                            case "B" -> GUIModel.adventureArea(2); //inventory
                            case "C" -> GUIModel.adventureArea(3); //town
                        }
                        break;
                    case "Combat":
                        switch(selection)
                        {
                            case "A" -> GUIModel.combatArea(1); //fight
                            case "B" -> GUIModel.combatArea(2); //inventory 
                            case "C" -> GUIModel.combatArea(3); //run
                        }
                        break;
                    case "Return":
                        switch(selection)
                        {
                            case "A" -> GUIModel.returnEvent(1); //exit
                        }
                        break;
                    case "Continue":
                    {
                        switch(selection)
                        {
                            case "A" -> GUIModel.continueEvent(GUIModel.continueChoice); //continue
                        }
                        break;
                    }
                }
                break;
        }
        
        if(!GUIModel.mainMenuActive) //Constant/global buttons to return to the main menu or quit the game at any point during gameplay.
        {
            switch(selection)
            {
                case "Global Main Menu Button" -> 
                {
                    if(GameManager.gui.confirmQuitPrompt() == 0)
                        GameManager.gui.createMainMenu(); 
                }
                case "Global Quit Button" -> 
                {
                    if(GameManager.gui.confirmQuitPrompt() == 0)
                        System.exit(0);
                }
            }  
        }      
    }   
}
