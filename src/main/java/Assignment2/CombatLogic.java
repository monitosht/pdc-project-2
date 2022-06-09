package Assignment2;

//import static Assignment2.GameManager.player;

/**
 * @author Monitosh Thaker | 17000777
 * COMP603 Assignment 2
 */
public class CombatLogic 
{
    public Enemy randomEnemy()
    {
        GameManager.gameDataDB.readEnemyList();
        Enemy enemy = null;
        
        do
        {
            int index = (int)(Math.random() * GameManager.enemies.size());
            enemy = GameManager.enemies.get(index);
            
        } while(enemy.getLevel() != GameManager.player.getLevel()); //Ensure that the play only encouters enemies of the same level       
        
        return enemy;
    }
}
