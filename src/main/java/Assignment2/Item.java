package Assignment2;

/**
 * @author Monitosh Thaker | 17000777
 * COMP603 Assignment 2
 */
public class Item 
{
    private final String name;
    private final String type;
    
    private int value;    
    private int price;
    
    //Constructor 
    public Item(String name, String type, int value, int price)
    {
        this.name = name;
        this.type = type;
        
        this.value = value;        
        this.price = price;
    }   
    
    //<editor-fold defaultstate="collapsed" desc="Getters and Setters">
    

    public String getName() //Item name is a read only variable
    {
        return name;
    }

    public String getType() //Item type is a read only variable
    {
        return type;
    }
    
    public int getValue() 
    {
        return value;
    }    
    public void setValue(int value) 
    {
        this.value = value;
    }
    
    public int getPrice() 
    {
        return price;
    }
    public void setPrice(int price) 
    {
        this.price = price;
    }
    
    //</editor-fold>
    
    //Methods
    public String useItem()
    {
        Player player = GameManager.player;
        String text;
        
        if(this.getType().equalsIgnoreCase("c")) //Consumable item
        {
            player.setCurrentHP(player.getCurrentHP() + this.getValue()); //Heal the player's HP by value amount

            if(player.getCurrentHP() > player.getMaxHP())
            {
                //Ensure the item doesn't heal the player past their max HP
                player.setCurrentHP(player.getMaxHP());
            }        

            text = "You used a "+this.getName()+" and restored "+this.getValue()+" HP!";            
        }
        else if(this.getType().equalsIgnoreCase("h")) //Health buff item
        {
            player.setMaxHP(player.getMaxHP() + this.getValue()); //Incrase max HP by value amount
            text = "You equipped "+this.getName()+" and increased your MAX HP by "+this.getValue()+"!";
        }
        else if(this.getType().equalsIgnoreCase("s")) //Strength buff item
        {
            player.setStrength(player.getStrength() + this.getValue()); //Increase strength by value amount
            text = "You equipped "+this.getName()+" and increased your STRENGTH by "+this.getValue()+"!";
        }
        else if(this.getType().equalsIgnoreCase("i")) //Intellect buff item
        {
            player.setStrength(player.getIntellect() + this.getValue()); //Increase intellect by value amount
            text = "You equipped "+this.getName()+" and increased your INTELLECT by "+this.getValue()+"!";
        }
        else if(this.getType().equalsIgnoreCase("d")) //Defence buff item
        {
            player.setStrength(player.getDefence() + this.getValue()); //Increase defence by value amount
            text = "You equipped "+this.getName()+" and increased your DEFENCE by "+this.getValue()+"!";
        }
        else //Invalid item
        {
            text = "Invalid item, removing from inventory.";
        }
        
        GameManager.inventory.remove(this); //Remove the item from the ArrayList as it has been used
        return text;
    }
    
    @Override
    public String toString()
    {
        return this.getName();
    }
}
