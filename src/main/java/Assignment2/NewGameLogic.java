package Assignment2;

/**
 * @author Monitosh Thaker | 17000777
 * COMP603 Assignment 2
 */
public class NewGameLogic 
{
    //Variables
    public static Player newPlayer;
    
    public static int points;
    public static int startPoints;
    
    public static int strValue;
    public static int intValue;
    public static int defValue;
    
    //Methods
    public static void createPlayer(String name, int _startPoints)
    {
        newPlayer = new Player(name);
        
        startPoints = _startPoints;
        points = startPoints;
        
        strValue = 0;
        intValue = 0;
        defValue = 0;
    }
    
    public static void updateAttribute(int choice, String attribute)
    {
        switch(choice)
        {
            case 0: //decrease attribute value
                switch(attribute)
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
            case 1: //increase attribute value
                if(points > 0)
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
        GUIHandler.promptText.setText("Points Remaining: "+points);
        GUIHandler.strValue.setText(""+strValue);
        GUIHandler.intValue.setText(""+intValue);
        GUIHandler.defValue.setText(""+defValue);
    }
    
    public static void setPlayer()
    {
        newPlayer.setStrength(strValue);
        newPlayer.setIntellect(intValue);
        newPlayer.setDefence(defValue);        
        
        GameManager.player = newPlayer;
        GameManager.act = 0;
    }
}
