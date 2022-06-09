package Assignment2;

import static Assignment2.GameManager.player;
import static Assignment2.GUILogic.updateCombatTextArea;

/**
 * @author Monitosh Thaker | 17000777
 * COMP603 Assignment 2
 */
public class CombatLogic 
{
    //public static Enemy enemy = getRandomEnemy();
    public static Enemy testEnemy;
    static int turn = 0;
    
    public static void setRandomEnemy()
    {
        GameManager.gameDataDB.readEnemyList();
        turn = 0;
        do
        {
            int index = (int)(Math.random() * GameManager.enemies.size());
            testEnemy = GameManager.enemies.get(index);
            
        } while(testEnemy.getLevel() != GameManager.player.getLevel()); //Ensure that the player only encouters enemies of the same level  
        System.out.println(testEnemy.getName());
    }
    
    public static Enemy getRandomEnemy()
    {
        GameManager.gameDataDB.readEnemyList();
        Enemy randEnemy = null;
        
        do
        {
            int index = (int)(Math.random() * GameManager.enemies.size());
            randEnemy = GameManager.enemies.get(index);
            
        } while(randEnemy.getLevel() != GameManager.player.getLevel()); //Ensure that the player only encouters enemies of the same level       
        
        return randEnemy;
    }
    
    public static void combatEvent(Enemy enemy)
    {
        updateCombatTextArea("TURN "+(++turn));
            
        dealDamage(enemy);
        takeDamage(enemy); 

        updatePlayerCombatCard();
        updateEnemyCombatCard(testEnemy);
    }
    
    public static void dealDamage(Enemy enemy) //call when the Fight button is selected
    {
        int damageDealt = player.Attack() - enemy.Defend();        
        if(damageDealt <= 0) damageDealt = 1;
        
        int missCheck = GameManager.rand.nextInt(9);        
        if(missCheck == 0) updateCombatTextArea("Your attack missed the "+enemy.getName()+"...");
        else
        {
            enemy.setCurrentHP(enemy.getCurrentHP() - damageDealt);
            updateCombatTextArea("You attacked the "+enemy.getName()+", dealing "+damageDealt+" damage!");
        }
        
        if(enemy.getCurrentHP() <= 0)
        {
            enemy.setCurrentHP(0);
            
            player.setXP(player.getXP() + enemy.getXP());
            player.setGold(player.getGold() + enemy.getReward());
            GameManager.levelUp();  
            
            updateCombatTextArea("The "+enemy.getName()+" has been defeated! \n[You earned "+enemy.getXP()+" EXP & "+enemy.getReward()+" Gold]");        
            
            //end combat              
            //continueChoice = x;
            //continueEvent(0, 0);
            //return to adventure area
        }
    }
    
    public static void takeDamage(Enemy enemy)
    {
        int damageTaken = enemy.Attack() - player.Defend();        
        if(damageTaken <= 0) damageTaken = 1;
        
        int missCheck = GameManager.rand.nextInt(9);        
        if(missCheck == 0) updateCombatTextArea("The "+enemy.getName()+"'s attack missed!");
        else
        {
            player.setCurrentHP(player.getCurrentHP() - damageTaken);
            updateCombatTextArea("The "+enemy.getName()+" attacked you and dealt "+damageTaken+" damage...");
        }
        
        if(player.getCurrentHP() <= 0)
        {
            player.setCurrentHP(0);
            GameManager.gameOver();
            
            //end combat              
            //continueChoice = x;
            //continueEvent(0, 0);
            //return to adventure area
        }
    }
    
    public static boolean runFromCombat(Enemy enemy)
    {
        int runChance = GameManager.rand.nextInt(9);
        
        if(runChance <= 2)
        {
            updateCombatTextArea("You safely got away from the "+enemy.getName()+".");
            
            return true;
            //end combat
            //continueChoice = x;
            //continueEvent(0, 0);
            //return to adventure area
        }
        else
        {
            updateCombatTextArea("You couldn't get away...");
            
            int damageTaken = enemy.Attack() - player.Defend()/2;
            if(damageTaken <= 0) damageTaken = 1;
            
            player.setCurrentHP(player.getCurrentHP() - damageTaken);
            
            updateCombatTextArea("The "+enemy.getName()+" attacked during your escape attempt and dealt "+damageTaken+" damage!");
            
            if(player.getCurrentHP() <= 0)
            {
                player.setCurrentHP(0);
                GameManager.gameOver();

                //end combat              
                //continueChoice = x;
                //continueEvent(0, 0);
                //return to adventure area
            }
            return false;
        }
    }
        
    static void updatePlayerCombatCard()
    {
        GUISetup.playerHP.setText("[ HP ] "+GameManager.player.getCurrentHP()+" / "+GameManager.player.getMaxHP());
        GUILogic.updatePlayerCard();
    }
    
    static void updateEnemyCombatCard(Enemy enemy)
    {
        GUISetup.enemyName.setText(""+enemy.getName());
        GUISetup.enemyHP.setText("[ HP ] "+enemy.getCurrentHP()+" / "+enemy.getMaxHP());
    }
}
