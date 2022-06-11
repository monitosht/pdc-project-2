package Assignment2;

import static Assignment2.GUIUpdate.updateMainTextArea;

/**
 * @author Monitosh Thaker | 17000777
 * COMP603 Assignment 2
 */
public class StoryHandler 
{
    //Displays only when a new game is started.
    public static void displayIntro() 
    {
        updateMainTextArea("""
        Prologue
        -----------
        You are a retired adventurer that has found a home and built a life in a quiet yet cosy town.
        However, in recent times evil has become more prominent in the nearby lands, posing a great 
        threat to your peaceful life and those you care about.

        In order to protect what\u2019s important you must continue your adventure!""");
    }
    
    //Display the story for the current act when called.
    public static void progressStory(int act) 
    {
        switch(act)
        {
            case 1 -> 
            {
                updateMainTextArea("""
                Act 1: Explore the Outskirtrs
                ----------------------------------                  
                Your journey begins with reports of odd yet dangerous creatures circling the outskirts of town
                bothering and injuring innocent people. To keep your town safe, you choose to investigate these 
                incidents and slay the monsters that have been disrupting the peace.""");
            }
            case 2 -> 
            {
                updateMainTextArea("""
                Act 2: Find the Silver Key
                -------------------------------       
                After successfully getting rid of the creatures roaming the local area, news has broken out that 
                the town was soon to be raided by the neighbouring Imperial City.
                Tales describe the soldiers of the city are savage and ruthless, unafraid to kill nor loot anything inorder to expand their territory.

                You decide to make a stop to their attempt of barbarism before it even begins by invading their 
                castle to down their army from the roots.

                To gain access inside the castle you will need a Silver Key. One had supposedly been stolen by a 
                bandit who has taken refuge in the nearby cave known as the Dartshaw Hollow.
                                   
                You intend to seek him out and take the key…""");
            }
            case 3 -> 
            {
                updateMainTextArea("""
                Act 3: Monster Hunt
                ------------------------                   
                With the bandit taken down and the Silver Key now in your possession, you are almost ready to 
                execute your plan to protect your town from the upcoming raid.

                However, the Imperial City having learned of the whereabouts of the Silver Key has advanced 
                their plot to claim the town, joining forces with fearsome monsters from the Hissing Forest to 
                reinforce their power. 
                                   
                You must first address the closer threat and ensure these monsters do not cause any harm.""");
            }
            case 4 -> 
            {
                updateMainTextArea("""
                Act 4: Raid the Castle
                --------------------------
                With the monsters of the Hissing Forest being taken care of the Imperial City’s castle is finally 
                vulnerable enough for you to execute your plan.
                                   
                You go fourth and depart towards the city full of ambition and a stern focus.""");
            }
            case 5 -> 
            {
                updateMainTextArea("""
                Act 5: Defeat the Dragon!
                -------------------------------
                After a hard-fought battle with the forces of the Imperial City you have emerged victorious and 
                prevented any harm from reaching to your beloved home.
                                   
                On the path back to town you notice struggling villages, scorched crops and destroyed land. You are fully aware of what this must mean - the great terror has once again shown itself.

                The final yet most dangerous threat to these lands is none other than the Red Dragon! 
                For decades this creature has wreaked havoc on anything in its path, burning lives, homes and 
                cities to the ground.

                This final battle will be the most difficult yet most necessary one, have you progressed enough
                during your journey to conquer this challenge?""");
            }
            case 6 ->
            {
                updateMainTextArea("""
                You have completed the game - Thanks for playing!
                --------------------------------------------------------------""");
                displayOutro();
            }
        }
    }
    
    //Displays when the player completes the game and as a reminder, every time that same save data is loaded.
    public static void displayOutro() 
    {
        updateMainTextArea("""
        Epilogue
        ----------
        You have done the unthinkable have slain the mighty Red Dragon. This feat has not gone unnoticed,
        your town and nearby villages hail you as a true hero and protector.
                           
        have successfully cleansed your home of all the looming evil that had threatened it in the past,
        allowing you to put a stop your adventure once again and enjoy a peaceful life.
                                              
        ...                   
        For now.""");
    }
}
