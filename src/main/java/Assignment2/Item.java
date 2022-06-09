package Assignment2;

/**
 * @author Monitosh Thaker | 17000777
 * COMP603 Assignment 2
 */
public class Item 
{
    private String name;
    private String type;
    
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
    
    //Getters and setterrs
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
    
    //Methods
    public void Use()
    {
        Player player = GameManager.player;
        
        if(this.getType().equalsIgnoreCase("c")) //Consumable item
        {
            player.setCurrentHP(player.getCurrentHP() + this.getValue()); //Heal the player's HP by value amount

            if(player.getCurrentHP() > player.getMaxHP())
            {
                //Ensure the item doesn't heal the player past their max HP
                player.setCurrentHP(player.getMaxHP());
            }        

            System.out.println("You used a "+this.getName()+" and restored "+this.getValue()+" HP");            
        }
        else if(this.getType().equalsIgnoreCase("h")) //Health buff item
        {
            player.setMaxHP(player.getMaxHP() + this.getValue()); //Incrase max HP by value amount
            System.out.println("You equipped "+this.getName()+" and increased your MAX HP by "+this.getValue()+"!");
        }
        else if(this.getType().equalsIgnoreCase("s")) //Strength buff item
        {
            player.setStrength(player.getStrength() + this.getValue()); //Increase strength by value amount
            System.out.println("You equipped "+this.getName()+" and increased your STRENGTH by "+this.getValue()+"!");
        }
        else if(this.getType().equalsIgnoreCase("i")) //Intellect buff item
        {
            player.setStrength(player.getIntellect() + this.getValue()); //Increase intellect by value amount
            System.out.println("You equipped "+this.getName()+" and increased your INTELLECT by "+this.getValue()+"!");
        }
        else if(this.getType().equalsIgnoreCase("d")) //Defence buff item
        {
            player.setStrength(player.getDefence() + this.getValue()); //Increase defence by value amount
            System.out.println("You equipped "+this.getName()+" and increased your DEFENCE by "+this.getValue()+"!");
        }
        else //Invalid item
        {
            System.out.println("Invalid item, removing from inventory.");
        }
        
        GameManager.inventory.remove(this); //Remove the item from the ArrayList as it has been used
    }
}
