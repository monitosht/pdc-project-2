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
        
        if(GUILogic.mainMenu == true)
        {
            switch(selection) //Main menu buttons
            {
                case "New Game Button" -> GameManager.gui.characterCreation(0);
                case "Continue Button" -> GameManager.gui.createContinueScene();
                case "Credits Button"  -> GameManager.gui.createCreditsScene();
                case "Quit Button"     -> System.exit(0);
            }
            GameManager.gui.constantButtons();
            GUILogic.mainMenu = false;
        }
        else
        {
            switch(selection)
            {
                case "Global Main Menu Button" -> GameManager.gui.createMainMenu();
                case "Global Quit Button"      -> System.exit(0);
            }
            
            if(GUILogic.ccMenu == true)
            {
                switch(selection) //Chracter creation buttons
                {
                    case "Strength -"  -> CreatePlayer.updateAttribute(0, "str");
                    case "Strength +"  -> CreatePlayer.updateAttribute(1, "str");
                    case "Intellect -" -> CreatePlayer.updateAttribute(0, "int");
                    case "Intellect +" -> CreatePlayer.updateAttribute(1, "int");
                    case "Defence -"   -> CreatePlayer.updateAttribute(1, "def");
                    case "Defence +"   -> CreatePlayer.updateAttribute(1, "def");

                    case "Confirm Button 1" -> 
                    {
                        if(!GameManager.gui.nameField.getText().equals(""))
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
                            int check = GameManager.gui.unspentPointsPrompt();
                            
                            if(check == 0)
                            {
                                CreatePlayer.setPlayer();
                                GameManager.gui.characterCreation(2);
                            }
                        }
                        else
                        {   
                            CreatePlayer.setPlayer();
                            GameManager.gui.characterCreation(2);
                        }
                        
                    }
                    case "Confirm Button 3" -> 
                    {
                        GameManager.gameDataDB.saveGame(); //save new player data if confirmed
                        GameManager.gui.createGameScene(); //continue to the main game scene
                    }
                }
            }
            
            if(GUILogic.position != null)
            {                
                switch(GUILogic.position)
                {
                    case "Town":
                        switch(selection)
                        {
                            case "A" -> GUILogic.adventureArea(0);
                            case "B" -> GUILogic.innArea(0);
                            case "C" -> GUILogic.shopArea(0);
                            case "D" -> GameManager.gameDataDB.saveGame();
                        }
                        break;                
                    case "Inn":
                        switch(selection)
                        {
                            case "A" -> GUILogic.innArea(1); //yes
                            case "B" -> GUILogic.innArea(2); //no
                        }
                        break;
                    case "Shop":
                        switch(selection)
                        {
                            case "A" -> GUILogic.shopArea(1); //buy
                            case "B" -> GUILogic.shopArea(2); //sell
                            case "C" -> GUILogic.shopArea(3); //exit
                        }
                        break;
                    case "Adventure":
                        switch(selection)
                        {
                            case "A" -> GUILogic.adventureArea(1); //explore
                            case "B" -> GUILogic.adventureArea(2); //inventory
                            case "C" -> GUILogic.adventureArea(3); //town
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
    }   
}
