package Assignment2;

import static Assignment2.GUIUpdate.updateCombatTextArea;
import static Assignment2.GUIUpdate.updatePlayerCombatCard;
import static Assignment2.GUIUpdate.updateEnemyCombatCard;
import static Assignment2.GameManager.player;

/**
 * @author Monitosh Thaker | 17000777
 * COMP603 Assignment 2
 */
public class CombatHandler 
{
    public static Enemy currentEnemy; //Reference to the enemy being fought during combat.  
    public static int turn; //The current turn of combat.
    
    //Methods
    public static void setRandomEnemy()
    {
        //Initialise the enemy list array by reading from the database.
        GameManager.gameDataDB.readEnemyList();    
        do
        {
            int index = (int)(Math.random() * GameManager.enemyList.size());
            currentEnemy = GameManager.enemyList.get(index);
            
        } while(currentEnemy.getLevel() != GameManager.player.getLevel()); //Repeat until the player encouters an enemy of the same level.
        
        turn = 0; //Initialise the turn as 0 as combat is about to beign.
    }    
    
    //Returns true if the combat ends, false if it continues.
    public static boolean combatEvent(Enemy enemy) 
    {
        updateCombatTextArea("TURN "+(++turn)); //Update the turn after each interation.
        boolean combatEnded;
        
        updateCombatTextArea(dealDamage(player, enemy)); //player attacks enemy
        
        if(enemy.getCurrentHP() <= 0) //Check if the enemy is alive.
        {
            enemy.setCurrentHP(0);
            
            player.setXP(player.getXP() + enemy.getXP());
            player.setGold(player.getGold() + enemy.getReward());
            
            updateCombatTextArea("The "+enemy.getName()+" has been defeated! \n[You earned "+enemy.getXP()+" EXP & "+enemy.getReward()+" Gold]");               
            GameManager.levelUp();  
            
            combatEnded = true;
        }
        else
        {
            updateCombatTextArea(dealDamage(enemy, player)); //enemy attacks player
            combatEnded = checkPlayerDead(); //Check if the player is dead.
        }        
        
        //Update the GUI elements variable values for both parties.
        updatePlayerCombatCard();
        updateEnemyCombatCard(currentEnemy);
        
        return(combatEnded);
    }
    
    public static boolean runFromCombat(Enemy enemy)
    {
        int runChance = GameManager.rand.nextInt(9);
        
        //Check for a 30% chance to run away.
        if(runChance <= 2)
        {
            updateCombatTextArea("You safely got away from the "+enemy.getName()+".");            
            return true;
        }
        else
        {
            updateCombatTextArea("You couldn't get away..."); 
            
            updateCombatTextArea(dealDamage(enemy, player)); //Damage the player as punishment.
            checkPlayerDead(); //Check if the player is dead.
            
            return false;
        }
    } 
    
    public static String dealDamage(Character attacking, Character defending)
    {
        String text;
        
        //Calculate the amount of damage to be dealt.
        int damageDealt = attacking.Attack() - defending.Defend();        
        if(damageDealt <= 0) damageDealt = 1;
        
        //Check for a 10% chance to miss the attack.
        int missCheck = GameManager.rand.nextInt(9);        
        if(missCheck == 0) text = attacking.getName()+"'s attack missed...";
        else
        {
            //If the attack does not miss, deal the damage.
            defending.setCurrentHP(defending.getCurrentHP() - damageDealt);
            text = attacking.getName()+" attacked "+defending.getName()+", for "+damageDealt+" damage!";
        }        
        return text;
    }
    
    public static boolean checkPlayerDead()
    { 
        if(player.getCurrentHP() <= 0)
        {
            player.setCurrentHP(0); 
            updatePlayerCombatCard();
            GameManager.gameOver(); 
            return true;
        }
        return false;
    }
}
