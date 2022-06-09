package Assignment2;

/**
 * @author Monitosh Thaker | 17000777
 * COMP603 Assignment 2
 */
public class Enemy extends Character
{
    private int reward;
    
    //Constructor
    public Enemy(String name, int maxHP, int strength, int intellect, int defence, int xp, int level, int reward)
    {
        super(name, maxHP, strength, intellect, defence, xp, level);
        //Adding variance to the reward value
        this.reward = (GameManager.rand.nextInt((reward + 2) - (reward - 2)) + (reward - 2));
    }
    
    //Getters and setters
    public int getReward() 
    {
        return reward;
    }
    public void setReward(int reward) 
    {
        this.reward = reward;
    }    
        
    //Override methods
    @Override
    public int Attack()
    {
        //Adding variance to the damage done by the attack method
        int min = this.getStrength() - 2;
        int max = this.getStrength() + 2;
        return (GameManager.rand.nextInt(max - min) + min);
    }
    @Override
    public int Defend() 
    {
        return this.getDefence();
    }
}
