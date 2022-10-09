package Main.RenderLogic;

import Main.BodyLogic.BodyFileDecoder;
import Main.BodyLogic.Person;

import java.awt.*;
import java.security.spec.ECField;

public class ConsoleCommands
{
    Console c;
    private boolean inAMenu = false;
    public ConsoleCommands(Console console)
    {
        c = console;
    }
    public void doCommand(String s)
    {
        final String[] commands = s.split(" ");
        try
        {
            if(commands[0].equalsIgnoreCase("/help"))
            {
                checkIfInMenu();
                c.println("'/help'       -> lists all commands", Color.PINK);
                c.println("'/clear'      -> clears all messages of the window", Color.PINK);
                c.println("'/spawnHuman' -> spawns a new human with the following parameters: name, bias, randomness", Color.PINK);
            }
            else if(commands[0].equalsIgnoreCase("/clear"))
            {
                checkIfInMenu();
                clear();
            }
            else if(commands[0].equalsIgnoreCase("/spawnHuman"))
            {
                checkIfInMenu();
                if(commands.length < 4)
                {
                    c.println("Too few parameters", c.errorColour);
                }
                else if(commands.length > 4)
                {
                    c.println("Too many parameters", c.errorColour);
                }
                else
                {
                    spawnHuman(commands[1], commands[2], commands[3]);
                }
            }
        }
        catch (Exception e)
        {
            c.println("Error ->" + e.getMessage(), c.trace, c.errorColour);
        }
    }

    public void checkIfInMenu()
    {
        if(inAMenu)
        {
            inAMenu = false;
            clear();
        }
    }

    public void enteringAMenu()
    {
        clear();
        inAMenu = true;
    }

    public void clear()
    {
        try
        {
            Console.styledDocument.remove(0, Console.styledDocument.getLength());
        }
        catch (Exception e)
        {
            System.out.println("Console.java cant clear styledDocument");
        }
    }

    public void spawnHuman(String name, String bias, String randomness)
    {
        try
        {
            int b = Integer.parseInt(bias);
            int r = Integer.parseInt(randomness);
            Person guy = BodyFileDecoder.getBodyPlanData("Resources/BodyPlans/Human", b, r);
            guy.changeName(name);
            c.cb.allCharacters.add(guy);
            c.cb.listAllPersons();
        }
        catch (Exception e)
        {
            c.println("WARNING: The second and third parameters of /spawnHuman must be integer numbers", c.errorColour);
        }
    }
}
