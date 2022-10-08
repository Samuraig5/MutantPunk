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
    public JFrame frame;
    public static JTextPane console;
    public JTextField input;
    public JScrollPane scrollPane;

    public static StyledDocument styledDocument;

    static boolean trace = false;

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

                ConsoleCommands.doCommand(text);
                scrollBottom();
                input.selectAll();
            }
        });

        input.addKeyListener(new KeyListener()
        {
            @Override
            public void keyTyped(KeyEvent e)
            {

            }
            @Override
            public void keyPressed(KeyEvent e)
            {
                if(e.getKeyCode() == KeyEvent.VK_UP)
                {
                    if(recentUsedID < (recentUsedMax-1) && recentUsedID < (recentUsed.size()-1))
                    {
                        recentUsedID++;
                    }
                    input.setText(recentUsed.get(recentUsed.size()-1-recentUsedID));
                }
                else if(e.getKeyCode() == KeyEvent.VK_DOWN)
                {
                    if(recentUsedID > 0)
                    {
                        recentUsedID--;
                    }
                    input.setText(recentUsed.get(recentUsed.size()-1-recentUsedID));
                }
            }
            @Override
            public void keyReleased(KeyEvent e)
            {

            }
        });

        scrollPane = new JScrollPane(console);
        scrollPane.setBorder(null);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);

        frame.add(input, BorderLayout.SOUTH);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.getContentPane().setBackground(new Color(50,50,50));

        //Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        //frame.setSize(screenSize.width, screenSize.height);
        frame.setSize(1000, 700);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
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

    public static void println(String s, boolean trace, Color c)
    {
        print(s+"\n",trace,c);
    }
    public void println(String s, boolean trace)
    {
        println(s,trace,Color.lightGray);
    }
}
