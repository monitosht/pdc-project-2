package Assignment2;

/**
 * @author Monitosh Thaker | 17000777
 * COMP603 Assignment 2
 */
public class CreatePlayer 
{
    //Variables
    public static Player newPlayer;
    
    private static int startPoints;
    public static int points;
    
    public static int strValue;
    public static int intValue;
    public static int defValue;
    
    //Methods
    public static void createPlayer(String name, int _startPoints)
    {
        newPlayer = new Player(name); //Store data for a new player based on a name input.
        
        startPoints = _startPoints; //Initialise the starting number of points.
        points = startPoints; //Save the starting number of ppomts
        
        //Initialise attribute values as 0
        strValue = 0;
        intValue = 0;
        defValue = 0;
    }
    
    public static void updateAttribute(int choice, String attribute)
    {
        switch(choice)
        {
            case 0: //Decrease the attribute value.
                switch(attribute) //Ensure there are allocated points to minus & dont refund move points than given.
                {
                    case "str":
                        if(strValue > 0 && strValue <= startPoints) 
                        {
                            strValue--;
                            points++; 
                        }
                        break;
                    case "int":
                        if(intValue > 0 && intValue <= startPoints)
                        {
                            intValue--;
                            points++; 
                        }
                        break;
                    case "def":
                        if(defValue > 0 && defValue <= startPoints)
                        {
                            defValue--;
                            points++; 
                        }
                        break;
                }
                break;
            case 1: //Increase attribute value.
                if(points > 0) //Ensure there are points still available to spend
                {
                    switch(attribute)
                    {
                        case "str":
                            strValue++;
                            points--;
                            break;
                        case "int":
                            intValue++;
                            points--;
                            break;
                        case "def":
                            defValue++;
                            points--;
                            break;
                    }
                }
                break;
        }        
        
        //Update the GUI to communicate the value changes to the player.
        GameManager.gui.promptText.setText("Points Remaining: "+points);
        GameManager.gui.strValue.setText(""+strValue);
        GameManager.gui.intValue.setText(""+intValue);
        GameManager.gui.defValue.setText(""+defValue);
    }
    
    public static void setPlayer()
    {
        //Assign the missing attributes to the newPlayer object 
        //since it was initialised using only a name parameter.
        newPlayer.setStrength(strValue);
        newPlayer.setIntellect(intValue);
        newPlayer.setDefence(defValue);        
        
        //Initialise the main/current player stored in the GameManager using the newPlayer
        GameManager.player = newPlayer;
        GameManager.act = 0;
    }
}
