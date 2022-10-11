package Main.RenderLogic;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class Console
{
    public ConsoleCommands cc = new ConsoleCommands(this);
    public ConsoleBodyMenu cb = new ConsoleBodyMenu(this);
    public ConsoleListRenderer clir = new ConsoleListRenderer(this);
    public ConsoleKeyPressListener ckpl = new ConsoleKeyPressListener(this);

    public Color errorColour = new Color(255,155,155);

    public JFrame frame;
    public static JTextPane console;
    public JTextField input;
    public JScrollPane scrollPane;

    public static StyledDocument styledDocument;

    boolean trace = false;

    ArrayList<String> recentUsed = new ArrayList<>();
    int recentUsedID = 0;
    int recentUsedMax = 10;

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

        input = new JTextField();
        input.setEditable(true);
        input.setPreferredSize(new Dimension(100,20));
        input.setFont(new Font("Courier New", Font.PLAIN, 12));
        input.setBorder(new LineBorder(Color.gray,1));
        input.setForeground(Color.lightGray);
        input.setCaretColor(Color.lightGray);
        input.setOpaque(false);

        input.addActionListener(e ->
        {
            String text = input.getText();
            if(text.length() > 0)
            {
                recentUsed.add(text);
                recentUsedID = 0;

                cc.doCommand(text);
                scrollBottom();
                input.selectAll();
            }
        });

        ckpl.initialize();

        ConsoleKeyBinds ckb = new ConsoleKeyBinds(this);
        frame.add(ckb.label);

        scrollPane = new JScrollPane(console);
        scrollPane.setBorder(null);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);

        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(input, BorderLayout.SOUTH);
        frame.getContentPane().setBackground(new Color(50,50,50));

        frame.setSize(1000, 700);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        ckb.label.grabFocus();
        cc.help();

        while (true)
        {
            ckb.label.grabFocus();
        }
    }

    public void scrollTop()
    {
        console.setCaretPosition(0);
    }
    public void scrollBottom()
    {
        console.setCaretPosition(console.getDocument().getLength());
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

    public void clearInputField()
    {
        input.setText("");
    }
}
