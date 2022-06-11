package Assignment2;

import static Assignment2.GUIModel.position;
        
/**
 * @author Monitosh Thaker | 17000777
 * COMP603 Assignment 2
 */
public class GUIUpdate 
{
    //Variables for the GUI TextAreas.
    static String textAreaText;
    static String combatText;
    
    //Update the text in the main game TextArea.
    static void updateMainTextArea(String text)
    {
        if(GameManager.gui.mainTextArea == null) 
            return; 
        
        textAreaText += "\n\n"+text;
        GameManager.gui.mainTextArea.setText(textAreaText);
    }
    
    //Update the text on the main game buttons.
    static void updateGameButtonText()
    {
        switch(position)
        {
            case "Town" -> {
                GameManager.gui.gameButton1.setText("< Adventure >");
                GameManager.gui.gameButton2.setText("< Rest >");
                GameManager.gui.gameButton3.setText("< Shop >");
                GameManager.gui.gameButton4.setText("< Save >");
            }
            case "Inn" -> {
                GameManager.gui.gameButton1.setText("< Yes >");
                GameManager.gui.gameButton2.setText("< No >");
                GameManager.gui.gameButton3.setText("");
                GameManager.gui.gameButton4.setText("");
            }
            case "Shop" -> {
                GameManager.gui.gameButton1.setText("< Buy >");
                GameManager.gui.gameButton2.setText("< Sell >");
                GameManager.gui.gameButton3.setText("< Exit >");
                GameManager.gui.gameButton4.setText("");
            }
            case "Adventure" -> {
                GameManager.gui.gameButton1.setText("< Explore >");
                GameManager.gui.gameButton2.setText("< Inventory >");
                GameManager.gui.gameButton3.setText("< Town >");
                GameManager.gui.gameButton4.setText("");
            }
            case "Combat" -> {
                GameManager.gui.gameButton1.setText("< Fight >");
                GameManager.gui.gameButton2.setText("< Inventory >");
                GameManager.gui.gameButton3.setText("< Run >");
                GameManager.gui.gameButton4.setText("");
            }
            case "Return" -> {
                GameManager.gui.gameButton1.setText("< Exit >");
                GameManager.gui.gameButton2.setText("");
                GameManager.gui.gameButton3.setText("");
                GameManager.gui.gameButton4.setText("");
            }
            case "Continue" -> {
                GameManager.gui.gameButton1.setText("< Continue >");
                GameManager.gui.gameButton2.setText("");
                GameManager.gui.gameButton3.setText("");
                GameManager.gui.gameButton4.setText("");
            }
        }
    }
    
    //Update the text on the player card.
    static void updatePlayerCard()
    {
        if(GameManager.gui.playerStatsCard == null)
            return;
        
        GameManager.gui.playerNameLabel.setText(""+GameManager.player.getName());  
        
        if(position == null || position.equals("Town") || position.equals("Inn") || position.equals("Return"))
        {
            GameManager.gui.locationLabel.setText("<Town>");
        }
        else
        {
            GameManager.gui.locationLabel.setText("<"+GameManager.getCurrentLocation()+">");
            
        }
        
        GameManager.gui.hpLabel.setText("[ HP ] "+GameManager.player.getCurrentHP()+" / "+GameManager.player.getMaxHP());
        GameManager.gui.strLabel.setText("[ STRENGTH ] "+GameManager.player.getStrength());
        GameManager.gui.intLabel.setText("[ INTELLECT ] "+GameManager.player.getIntellect());
        GameManager.gui.defLabel.setText("[ DEFENCE ] "+GameManager.player.getDefence());
        GameManager.gui.levelLabel.setText("[ LEVEL ] "+GameManager.player.getLevel());
        GameManager.gui.xpLabel.setText("[ XP ] "+GameManager.player.getXP());
        GameManager.gui.goldLabel.setText("[ GOLD ] "+GameManager.player.getGold());
    }
    
    //Update the text in the combat specific TextArea.
    static void updateCombatTextArea(String text)
    {
        if(GameManager.gui.combatTextArea == null) 
            return;
        
        combatText += "\n\n"+text;
        GameManager.gui.combatTextArea.setText(combatText);
    }
        
    //Update the combat specific player and enemy cards
    static void updatePlayerCombatCard()
    {
        if(GameManager.gui.playerHP != null) 
            GameManager.gui.playerHP.setText("[ HP ] "+GameManager.player.getCurrentHP()+" / "+GameManager.player.getMaxHP());
        
        updatePlayerCard();
    }
    
    static void updateEnemyCombatCard(Enemy enemy)
    {
        if(GameManager.gui.enemyHP == null)
            return;
        
        GameManager.gui.enemyName.setText("[ "+enemy.getName()+" ]");
        GameManager.gui.enemyHP.setText("[ HP ] "+enemy.getCurrentHP()+" / "+enemy.getMaxHP());
    }
}
