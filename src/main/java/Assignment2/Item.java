package Assignment2;

/**
 * @author Monitosh Thaker | 17000777
 * COMP603 Assignment 2
 */
public class Item 
{
    //Variables
    private final String name; //Read only variables declared as final
    private final String type; //Read only variables declared as final
    
    private int value; //Responsible for the potency of the effect when the item is used.  
    private int price; //Responsible for the buy/sell price at the Shop.
    
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
        
        switch(this.getType()) //Account for lowercase/capital using follow through.
        {
            case "C":
            case "c":
                player.setCurrentHP(player.getCurrentHP() + this.getValue()); //Heal the player's HP by value amount

                if(player.getCurrentHP() > player.getMaxHP()) //Ensure the item doesn't heal the player past their max HP
                    player.setCurrentHP(player.getMaxHP());
                
                text = "You used a "+this.getName()+" and restored "+this.getValue()+" HP!"; 
                break;
            case "H":
            case "h":
                player.setMaxHP(player.getMaxHP() + this.getValue()); //Incrase max HP by value amount
                text = "You equipped "+this.getName()+" and increased your MAX HP by "+this.getValue()+"!";
                break;
            case "S":
            case "s":
                player.setStrength(player.getStrength() + this.getValue()); //Increase strength by value amount
                text = "You equipped "+this.getName()+" and increased your STRENGTH by "+this.getValue()+"!";
                break;
            case "I":
            case "i":
                player.setStrength(player.getIntellect() + this.getValue()); //Increase intellect by value amount
                text = "You equipped "+this.getName()+" and increased your INTELLECT by "+this.getValue()+"!";
                break;
            case "D":
            case "d":
                player.setStrength(player.getDefence() + this.getValue()); //Increase defence by value amount
                text = "You equipped "+this.getName()+" and increased your DEFENCE by "+this.getValue()+"!";
                break;
            default:
                text = "Invalid item, removing from inventory.";
                break;
        }
        GameManager.inventory.remove(this); //Remove the item from the ArrayList as it has been used.        
        return text; //Return the correct UI prompt.
    }
    
    @Override
    public String toString()
    {
        return this.getName();
    }
}
