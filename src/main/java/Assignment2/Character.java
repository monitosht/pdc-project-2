package Assignment2;

/**
 * @author Monitosh Thaker | 17000777
 * COMP603 Assignment 2
 */
public abstract class Character 
{  
    //Attributes
    private String name;    
    
    private int currentHP;
    private int maxHP;
    
    private int strength;
    private int intellect; 
    private int defence;
    
    private int xp;
    private int level;
    
    //Constructor
    public Character(String name, int maxHP, int strength, int intellect, int defence, int xp, int level)
    {
        this.name = name;
        
        this.currentHP = maxHP;
        this.maxHP = maxHP;
        
        this.strength = strength;
        this.intellect = intellect;
        this.defence = defence;
        
        this.xp = xp;
        this.level = level;
    }    

    //Getters and setters
    public String getName() 
    {
        return name;
    }    
    public void setName(String name)
    {
        this.name = name;
    }

    public int getCurrentHP() 
    {
        return currentHP;
    }    
    public void setCurrentHP(int currentHP) 
    {
        this.currentHP = currentHP;
    }

    public int getMaxHP() 
    {
        return maxHP;
    }
    public void setMaxHP(int maxHP) 
    {
        this.maxHP = maxHP;
    }

    public int getStrength() 
    {
        return strength;
    }
    public void setStrength(int strength) 
    {
        this.strength = strength;
    }

    public int getIntellect() 
    {
        return intellect;
    }
    public void setIntellect(int intellect) 
    {
        this.intellect = intellect;
    }
    
    public int getDefence() 
    {
        return defence;
    }
    public void setDefence(int defence) 
    {
        this.defence = defence;
    }

    public int getXP() 
    {
        return xp;
    }
    public void setXP(int xp) 
    {
        this.xp = xp;
    }

    public int getLevel() 
    {
        return level;
    }
    public void setLevel(int level) 
    {
        this.level = level;
    }    
        
    //Abstract methods
    public abstract int Attack();
    public abstract int Defend();
}
