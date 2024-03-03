package Main.RenderLogic;

import Main.ErrorHandler;
import Main.RenderLogic.Menus.LEGACY_MainMenu;

import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
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

    /**
     * This prints the given string to the console
     * @param s String to be printed
     * @param trace Should the print be traced?
     * @param c The colour the String should be printed in
     */
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
    public void printStrings(List<String> s, boolean trace, List<Color> c)
    {
        if (s.size() != c.size()) {throw new RuntimeException("String List and Colour List are not the same length");}

        Color currentColor = c.get(0);
        StringBuilder colourBlock = new StringBuilder();
        for (int i = 0; i < s.size(); i++)
        {
            if (currentColor != c.get(i))
            {
                print(colourBlock.toString(), trace, currentColor);
                colourBlock = new StringBuilder();
                currentColor = c.get(i);
            }
            colourBlock.append(s.get(i));
        }
        print(colourBlock.toString(), trace, currentColor);
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
        LEGACY_generateLogo();

        mainMenuOptions.add("a - Open new World");
        mainMenuOptions = centreStringsInScreen(mainMenuOptions);

        println(mainMenuOptions);

        c.ckb.setCurrentMenu(new LEGACY_MainMenu(c));

    }
    public String[] generateLogo()
    {
        String[] logo = new String[11];

        logo[0] = (" ███▄ ▄███▓ █    ██ ▄▄▄█████▓ ▄▄▄       ███▄    █ ▄▄▄█████▓    ██▓███   █    ██  ███▄    █  ██ ▄█▀\n");
        logo[1] =("▓██▒▀█▀ ██▒ ██  ▓██▒▓  ██▒ ▓▒▒████▄     ██ ▀█   █ ▓  ██▒ ▓▒   ▓██░  ██▒ ██  ▓██▒ ██ ▀█   █  ██▄█▒ \n");
        logo[2] =("▓██    ▓██░▓██  ▒██░▒ ▓██░ ▒░▒██  ▀█▄  ▓██  ▀█ ██▒▒ ▓██░ ▒░   ▓██░ ██▓▒▓██  ▒██░▓██  ▀█ ██▒▓███▄░ \n");
        logo[3] =("▒██    ▒██ ▓▓█  ░██░░ ▓██▓ ░ ░██▄▄▄▄██ ▓██▒  ▐▌██▒░ ▓██▓ ░    ▒██▄█▓▒ ▒▓▓█  ░██░▓██▒  ▐▌██▒▓██ █▄ \n");
        logo[4] =("▒██▒   ░██▒▒▒█████▓   ▒██▒ ░  ▓█   ▓██▒▒██░   ▓██░  ▒██▒ ░    ▒██▒ ░  ░▒▒█████▓ ▒██░   ▓██░▒██▒ █▄\n");
        logo[5] =("░ ▒░   ░  ░░▒▓▒ ▒ ▒   ▒ ░░    ▒▒   ▓▒█░░ ▒░   ▒ ▒   ▒ ░░      ▒▓▒░ ░  ░░▒▓▒ ▒ ▒ ░ ▒░   ▒ ▒ ▒ ▒▒ ▓▒\n");
        logo[6] =("░  ░      ░░░▒░ ░ ░     ░      ▒   ▒▒ ░░ ░░   ░ ▒░    ░       ░▒ ░     ░░▒░ ░ ░ ░ ░░   ░ ▒░░ ░▒ ▒░\n");
        logo[7] =("░      ░    ░░░ ░ ░   ░        ░   ▒      ░   ░ ░   ░         ░░        ░░░ ░ ░    ░   ░ ░ ░ ░░ ░ \n");
        logo[8] =("       ░      ░                    ░  ░         ░                         ░              ░ ░  ░   \n");
        logo[9] =("                 ░                                 ░          ░                    ░              \n");
        logo[10] =("       ░                           ░                                     ░                 ░      \n");

        return logo;
    }

    private void LEGACY_generateLogo()
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
