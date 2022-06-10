package Assignment2;

import static Assignment2.GUIHandler.gameButton1;
import static Assignment2.GUIHandler.gameButton2;
import static Assignment2.GUIHandler.gameButton3;
import static Assignment2.GUIHandler.gameButton4;
import static Assignment2.GUILogic.position;
        
/**
 * @author Monitosh Thaker | 17000777
 * COMP603 Assignment 2
 */
public class GUIUpdate 
{
    static String textAreaText;
    static String combatText;
    
    //Main Game update methods
    static void updateMainTextArea(String text)
    {
        if(GUIHandler.mainTextArea == null) 
            return; 
        
        textAreaText += "\n\n"+text;
        GUIHandler.mainTextArea.setText(textAreaText);
    }
    
    static void updateGameButtonText()
    {
        switch(position)
        {
            case "Town" -> {
                gameButton1.setText("( Adventure )");
                gameButton2.setText("( Rest )");
                gameButton3.setText("( Shop )");
                gameButton4.setText("( Save )");
            }
            case "Inn" -> {
                gameButton1.setText("( Yes )");
                gameButton2.setText("( No )");
                gameButton3.setText("");
                gameButton4.setText("");
            }
            case "Shop" -> {
                gameButton1.setText("( Buy )");
                gameButton2.setText("( Sell )");
                gameButton3.setText("( Exit )");
                gameButton4.setText("");
            }
            case "Adventure" -> {
                gameButton1.setText("( Explore )");
                gameButton2.setText("( Inventory )");
                gameButton3.setText("( Town )");
                gameButton4.setText("");
            }
            case "Combat" -> {
                gameButton1.setText("( Fight )");
                gameButton2.setText("( Inventory )");
                gameButton3.setText("( Run )");
                gameButton4.setText("");
            }
            case "Return" -> {
                gameButton1.setText("( Exit )");
                gameButton2.setText("");
                gameButton3.setText("");
                gameButton4.setText("");
            }
            case "Continue" -> {
                gameButton1.setText("( Continue )");
                gameButton2.setText("");
                gameButton3.setText("");
                gameButton4.setText("");
            }
        }
    }
    
    static void updatePlayerCard()
    {
        if(GUIHandler.playerStatsCard == null) return;
        
        GUIHandler.playerNameLabel.setText(""+GameManager.player.getName());  
        
        if(position == null || position.equals("Town") || position.equals("Inn") || position.equals("Shop")) GUIHandler.locationLabel.setText("<Town>");
        else GUIHandler.locationLabel.setText("<"+GameManager.getCurrentLocation()+">");
        
        GUIHandler.hpLabel.setText("[ HP ] "+GameManager.player.getCurrentHP()+" / "+GameManager.player.getMaxHP());
        GUIHandler.strLabel.setText("[ STRENGTH ] "+GameManager.player.getStrength());
        GUIHandler.intLabel.setText("[ INTELLECT ] "+GameManager.player.getIntellect());
        GUIHandler.defLabel.setText("[ DEFENCE ] "+GameManager.player.getDefence());
        GUIHandler.levelLabel.setText("[ LEVEL ] "+GameManager.player.getLevel());
        GUIHandler.xpLabel.setText("[ XP ] "+GameManager.player.getXP());
        GUIHandler.goldLabel.setText("[ GOLD ] "+GameManager.player.getGold());
    }
    
    //Combat Elements update methods
    static void updateCombatTextArea(String text)
    {
        if(GUIHandler.combatTextArea == null) 
            return;
        
        combatText += "\n\n"+text;
        GUIHandler.combatTextArea.setText(combatText);
    }
        
    static void updatePlayerCombatCard()
    {
        if(GUIHandler.playerHP != null) 
            GUIHandler.playerHP.setText("[ HP ] "+GameManager.player.getCurrentHP()+" / "+GameManager.player.getMaxHP());
        
        updatePlayerCard();
    }
    
    static void updateEnemyCombatCard(Enemy enemy)
    {
        if(GUIHandler.enemyHP == null)
            return;
        
        GUIHandler.enemyName.setText("[ "+enemy.getName()+" ]");
        GUIHandler.enemyHP.setText("[ HP ] "+enemy.getCurrentHP()+" / "+enemy.getMaxHP());
    }
}
