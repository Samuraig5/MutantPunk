package Main.RenderLogic;

import Main.ErrorHandler;
import Main.RenderLogic.Menus.MainMenu;
import Main.RenderLogic.Menus.WorldMenu;
import Main.WorldLogic.GameWorld;

import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static Main.RenderLogic.Console.console;
import static Main.RenderLogic.Console.styledDocument;

public class ConsoleCommands
{
    Console c;
    public ConsoleCommands(Console console)
    {
        c = console;
    }
    public void print(String s, boolean trace, Color c)
    {
        Style style = console.addStyle("Style", null);
        StyleConstants.setForeground(style, c);
        if (trace)
        {
            Throwable t = new Throwable();
            StackTraceElement[] elements = t.getStackTrace();
            String caller = elements[0].getClassName();
            s = caller + " -> " + s;
        }
        try
        {
            styledDocument.insertString(styledDocument.getLength(), s, style);
        }
        catch (Exception e)
        {
            System.out.println("Console.java cant insert String int styledDocument");
        }
    }
    public void print(String s, boolean trace)
    {
        print(s, trace, Color.lightGray);
    }
    public void print(String s)
    {
        print(s, false, Color.lightGray);
    }
    public void println(String s, boolean trace, Color c)
    {
        print(s+"\n",trace,c);
    }
    public void println(String s, boolean trace)
    {
        println(s,trace,Color.lightGray);
    }
    public void println(String s, Color c)
    {
        println(s,false,c);
    }
    public void println(List<String> sList)
    {
        for (String s:sList)
        {
            println(s,false,Color.lightGray);
        }
    }

    public void println(String s)
    {
        println(s,false,Color.lightGray);
    }
    public void printErrorln(String s) {println(s,true,c.errorColour);}

    public void clear()
    {
        try
        {
            styledDocument.remove(0, styledDocument.getLength());
        }
        catch (Exception e)
        {
            System.out.println("Console.java cant clear styledDocument");
        }
    }

    public void fullClear()
    {
        c.ckb.setCurrentMenu(null);
        c.clir.reset();
        clear();
    }

    public void openMainMenu()
    {
        List<String> mainMenuOptions = new ArrayList<>();

        fullClear();
        generateLogo();

        mainMenuOptions.add("a - Open new World");
        mainMenuOptions = centreStringsInScreen(mainMenuOptions);

        println(mainMenuOptions);

        c.ckb.setCurrentMenu(new MainMenu(c));

    }
    private void generateLogo()
    {
        List<String> logoList = new ArrayList<>();

        logoList.add("");
        logoList.add("");
        logoList.add("");
        logoList.add(" ███▄ ▄███▓ █    ██ ▄▄▄█████▓ ▄▄▄       ███▄    █ ▄▄▄█████▓    ██▓███   █    ██  ███▄    █  ██ ▄█▀");
        logoList.add("▓██▒▀█▀ ██▒ ██  ▓██▒▓  ██▒ ▓▒▒████▄     ██ ▀█   █ ▓  ██▒ ▓▒   ▓██░  ██▒ ██  ▓██▒ ██ ▀█   █  ██▄█▒ ");
        logoList.add("▓██    ▓██░▓██  ▒██░▒ ▓██░ ▒░▒██  ▀█▄  ▓██  ▀█ ██▒▒ ▓██░ ▒░   ▓██░ ██▓▒▓██  ▒██░▓██  ▀█ ██▒▓███▄░ ");
        logoList.add("▒██    ▒██ ▓▓█  ░██░░ ▓██▓ ░ ░██▄▄▄▄██ ▓██▒  ▐▌██▒░ ▓██▓ ░    ▒██▄█▓▒ ▒▓▓█  ░██░▓██▒  ▐▌██▒▓██ █▄ ");
        logoList.add("▒██▒   ░██▒▒▒█████▓   ▒██▒ ░  ▓█   ▓██▒▒██░   ▓██░  ▒██▒ ░    ▒██▒ ░  ░▒▒█████▓ ▒██░   ▓██░▒██▒ █▄");
        logoList.add("░ ▒░   ░  ░░▒▓▒ ▒ ▒   ▒ ░░    ▒▒   ▓▒█░░ ▒░   ▒ ▒   ▒ ░░      ▒▓▒░ ░  ░░▒▓▒ ▒ ▒ ░ ▒░   ▒ ▒ ▒ ▒▒ ▓▒");
        logoList.add("░  ░      ░░░▒░ ░ ░     ░      ▒   ▒▒ ░░ ░░   ░ ▒░    ░       ░▒ ░     ░░▒░ ░ ░ ░ ░░   ░ ▒░░ ░▒ ▒░");
        logoList.add("░      ░    ░░░ ░ ░   ░        ░   ▒      ░   ░ ░   ░         ░░        ░░░ ░ ░    ░   ░ ░ ░ ░░ ░ ");
        logoList.add("       ░      ░                    ░  ░         ░                         ░              ░ ░  ░   ");
        logoList.add("                 ░                                 ░          ░                    ░              ");
        logoList.add("       ░                           ░                                     ░                 ░      ");
        logoList.add("");

        logoList = centreStringsInScreen(logoList);

        for (String s:logoList)
        {
            println(s, Color.GREEN);
        }
    }

    private List<String> centreStringsInScreen(List<String> input)
    {
        List<String> centredStrings = new ArrayList<>();
        int characterWidth = 7;


        for (String s:input)
        {
            int stringLength = characterWidth*s.length();
            ErrorHandler.LogData(false, "String Length" + stringLength);

            int screenWidth = c.getScreenSize()[1];
            ErrorHandler.LogData(false, "Screen Width" + screenWidth);

            int emptySpace = screenWidth-stringLength;
            ErrorHandler.LogData(false, "empty Space" + emptySpace);

            int spacerNumber = (emptySpace/characterWidth)/2;
            ErrorHandler.LogData(false, "Spacer Number" + spacerNumber);

            String spacer = "";
            for (int i = 0; i < spacerNumber; i++)
            {
                spacer = spacer + " ";
            }
            s = spacer + s;
            centredStrings.add(s);
        }

        return centredStrings;
    }
}
