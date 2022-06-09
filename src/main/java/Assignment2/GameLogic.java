package Assignment2;

/**
 * @author Monitosh Thaker | 17000777
 * COMP603 Assignment 2
 */
public class GameLogic 
{
    //Attribute variables
    public static Player newPlayer;
    
    public static int points;
    public static int startPoints;
    
    public static int strValue ;
    public static int intValue;
    public static int defValue;    
    
    public static void createPlayer(String name, int _startPoints)
    {
        newPlayer = new Player(name);
        System.out.println("Player name set to "+name);
        
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
        SetupGUI.promptText.setText("Points Remaining: "+points);
        SetupGUI.strValue.setText(""+strValue);
        SetupGUI.intValue.setText(""+intValue);
        SetupGUI.defValue.setText(""+defValue);
    }
    
    public static void confirmAttributes()
    {
        newPlayer.setStrength(strValue);
        newPlayer.setIntellect(intValue);
        newPlayer.setDefence(defValue);        
        
        GameManager.player = newPlayer;
    }
    
    public static void updatePlayerCard()
    {
        SetupGUI.playerNameLabel.setText(""+GameManager.player.getName());
        SetupGUI.hpLabel.setText("[ HP ] "+GameManager.player.getCurrentHP()+" / "+GameManager.player.getMaxHP());
        SetupGUI.strLabel.setText("[ STRENGTH ] "+GameManager.player.getStrength());
        SetupGUI.intLabel.setText("[ INTELLECT ] "+GameManager.player.getIntellect());
        SetupGUI.defLabel.setText("[ DEFENCE ] "+GameManager.player.getDefence());
        SetupGUI.levelLabel.setText("[ LEVEL ] "+GameManager.player.getLevel());
        SetupGUI.xpLabel.setText("[ XP ] "+GameManager.player.getXP());
        SetupGUI.goldLabel.setText("[ GOLD ] "+GameManager.player.getGold());
    }
    
    public static void characterCreation()
    {
        //Player player;        
        
        /*        
        
        do //Prompt the user to enter a chosen name until they are statisfied 
        {
            HelperMethods.printHeading("What is your name?");
            name = HelperMethods.readInput();
            System.out.println();
            
            //Check if save data with the chosen name already exists
            if(SaveLoad.checkSaveOverwrite(name))
            {
                //skip name check if the player wishes to overwrite the save 
                break;
            }
            
            HelperMethods.printHeading("Continue as "+name+"?");
            System.out.println("[1] Yes");
            System.out.println("[2] No");
            
            if(HelperMethods.readSelection(2) == 1)
            {
                nameSet = true;
            }            
        }while(!nameSet);   
        
        //Initialize a player object with the chosen name
        player = new Player(name); 

        int points = 10;
        int startPoints = points;
        int value;                
        boolean attributesSet = false;
        
        do //Prompt the player to allocate points into attributes until they are satisfied 
        {         
            points = startPoints;
            
            value = GameManager.setAttribute("STRENGTH", points);           
            player.setStrength(value); //Assign the points to the attribute
            points -= value; //Deduct the points used from the total number of points

            value = GameManager.setAttribute("INTELLECT", points);          
            player.setIntellect(value);
            points -= value;
            
            value = GameManager.setAttribute("DEFENCE", points);          
            player.setDefence(value);
            points -= value;
            
            System.out.println();
            
            HelperMethods.printHeading("Continue with these attributes?");
            System.out.println("[STRENGTH]: "+player.getStrength());
            System.out.println("[INTELLECT]: "+player.getIntellect());
            System.out.println("[DEFENCE]: "+player.getDefence());
            
            HelperMethods.printSeparator(15);
            
            System.out.println("[1] Yes");
            System.out.println("[2] No");
            
            if(HelperMethods.readSelection(2) == 1)
            {
                attributesSet = true;
            }            
        }while(!attributesSet);    
        
        //Set the newly created player as the unverisal player object in the GameManager class and begin the game
        System.out.println("Starting game as:");
        GameManager.player = player;
        GameManager.playerInformation();        
        
        HelperMethods.anyKeyToContinue();

        */
    }
}
