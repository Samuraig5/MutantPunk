package Main.RenderLogic;

import Main.BodyLogic.BodyFileDecoder;
import Main.BodyLogic.Person;
import Main.RenderLogic.Menus.MainMenu;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

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
                exitMenuIfInMenu();
                help();
            }
            else if(commands[0].equalsIgnoreCase("/clear"))
            {
                exitMenuIfInMenu();
                fullClear();
            }
            else if(commands[0].equalsIgnoreCase("/spawnHuman"))
            {
                exitMenuIfInMenu();
                if(commands.length == 1)
                {
                    spawnHuman("Human", "0", "0");
                }
                else if(commands.length == 2)
                {
                    spawnHuman(commands[1], "0", "0");
                }
                else if(commands.length == 3)
                {
                    spawnHuman(commands[1], commands[2], "0");
                }
                else if(commands.length == 4)
                {
                    spawnHuman(commands[1], commands[2], commands[3]);
                }
                else
                {
                    c.printWarningln("Too many Parameters!");
                }
            }
        }
        catch (Exception e)
        {
            c.printWarningln("Error ->" + e.getMessage());
        }
    }

    public void help()
    {
        c.println("'/help'       -> lists all commands", Color.PINK);
        c.println("'/clear'      -> clears all messages of the window", Color.PINK);
        c.println("'/spawnHuman' -> spawns a new human with the following parameters: name, bias, randomness", Color.PINK);
    }
    public void exitMenuIfInMenu()
    {
        if(inAMenu)
        {
            inAMenu = false;
            fullClear();
        }
    }

    public void enteringAMenu()
    {
        fullClear();
        inAMenu = true;
    }

    public void fullClear()
    {
        c.clir.reset();
        c.clear();
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
        }
        catch (Exception e)
        {
            c.println("WARNING: The second and third parameters of /spawnHuman must be integer numbers", c.errorColour);
        }
    }

    public void openMainMenu()
    {
        List<String> mainMenuOptions = new ArrayList<>();
        mainMenuOptions.add("Spawn a Human");
        mainMenuOptions.add("Spawn 10 Humans");
        mainMenuOptions.add("Spawn 100 Humans");
        c.clir.renderList(mainMenuOptions, "MainMenu", new MainMenu(c));
    }
}
