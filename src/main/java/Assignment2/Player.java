package Assignment2;

/**
 * @author Monitosh Thaker | 17000777
 * COMP603 Assignment 2
 */
public class Player extends Character
{
    private int gold;
    
    //Constructor used to initialize the player when a new game is started.
    public Player(String name) 
    {
        //Starting player stats initialised to 0 to be set later.
        super(name, 20, 0, 0, 0, 0, 1);
        this.gold = 0;
    }    
    
    //Constructor used to load an existing player when a game is continued.
    public Player(String name, int currentHP, int maxHP, int strength, int intellect, int defence, int xp, int level, int gold) 
    {
        super(name, maxHP, strength, intellect, defence, xp, level);        
        this.setCurrentHP(currentHP);
        this.gold = gold;
    }

    //Getters and setters
    public int getGold() 
    {
        return gold;
    }
    public void setGold(int gold) 
    {
        this.gold = gold;
    }
        
    //Override Methods
    @Override
    public int Attack()
    {        
        //Add variance to the damage dealt by this character.
        int min = this.getStrength() - 2;
        int max = this.getStrength() + 2;
        return (GameManager.rand.nextInt(max - min) + min);
    }    
    
    @Override //Method exists as an override for future development potential.
    public int Defend() 
    {
        return this.getDefence(); 
    }    
    
    //Formats the player's stats as a String for saving purposes.
    public String statsToString() 
    {
        return this.getCurrentHP() + " " + this.getMaxHP() + " " + this.getStrength()  + " " + this.getIntellect()  + " " + this.getDefence()  + " " + this.getXP()  + " " + this.getLevel()  + " " + this.getGold();
    }
}
