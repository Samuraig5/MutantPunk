package Main.RenderLogic;

import Main.RenderLogic.Menus.MainMenu;

import javax.swing.*;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.util.ArrayList;

public class Console
{
    public ConsoleCommands cc = new ConsoleCommands(this);
    public ConsoleBodyMenu cb = new ConsoleBodyMenu(this);
    public ConsoleListRenderer clir = new ConsoleListRenderer(this);
    public ConsoleKeyBinds ckb;

    public Color errorColour = new Color(255,155,155);

    public JFrame frame;
    public static JTextPane console;
    public JTextField input;
    public JScrollPane scrollPane;

    public static StyledDocument styledDocument;

    public Console()
    {
        try
        {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); //Make the window's look match the OS
        }
        catch (Exception e)
        {
            System.out.println("Console.java ant find SystemLookAndFeel");
        }

        frame = new JFrame();
        frame.setTitle("Mutant Punk");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Program stops when window is closed

        console = new JTextPane();
        console.setEditable(false);
        console.setFont(new Font("Courier New", Font.PLAIN, 12));
        console.setOpaque(false);

        styledDocument = console.getStyledDocument();

        ckb = new ConsoleKeyBinds(this);
        ckb.setCurrentMenu(new MainMenu(this));
        frame.add(ckb.label);

        scrollPane = new JScrollPane(console);
        scrollPane.setBorder(null);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);

        frame.add(scrollPane, BorderLayout.CENTER);
        frame.getContentPane().setBackground(new Color(50,50,50));

        frame.setSize(1000, 700);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        ckb.label.grabFocus();
        cc.openMainMenu();

        while (true)
        {
            ckb.label.grabFocus();
        }
    }
    public static void print(String s, boolean trace, Color c)
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
    public void println(String s)
    {
        println(s,false,Color.lightGray);
    }
    public void printWarningln(String s) {println(s,errorColour);}

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
}
