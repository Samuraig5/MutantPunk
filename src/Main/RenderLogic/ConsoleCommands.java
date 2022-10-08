package Main.RenderLogic;

import java.awt.*;

public class ConsoleCommands
{
    public static void doCommand(String s)
    {
        final String[] commands = s.split(" ");
        try
        {
            if(commands[0].equalsIgnoreCase("/help"))
            {
                Console.println("'/help'  -> lists all commands", false, Color.PINK);
                Console.println("'/clear' -> clears all messages of the window", false, Color.PINK);
            }
            else if(commands[0].equalsIgnoreCase("/clear"))
            {
                clear();
            }
            else
            {
                Console.println(s, false, Color.lightGray);
            }
        }
        catch (Exception e)
        {
            Console.println("Error ->" + e.getMessage(), Console.trace, new Color(255,155,155));
        }
    }

    public static void clear()
    {
        try
        {
            Console.styledDocument.remove(0,Console.styledDocument.getLength());
        }
        catch (Exception e)
        {
            System.out.println("Console.java cant clear styledDocument");
        }
    }
}
