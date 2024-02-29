package Main.RenderLogic;

import Main.ErrorHandler;
import Main.RenderLogic.Menus.MainMenu;
import Main.RenderLogic.Menus.WorldMenu;
import Main.WorldLogic.WorldClock;

import javax.swing.*;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.Timer;

public class Console
{
    public ConsoleCommands cc = new ConsoleCommands(this);
    public ConsoleBodyInterface cb = new ConsoleBodyInterface(this);
    public ConsoleListRenderer clir = new ConsoleListRenderer(this);
    public ConsoleKeyBinds ckb;
    public ConsoleTopMenuRenderer ctmr = new ConsoleTopMenuRenderer(this);
    public ConsoleMapInterface cm = new ConsoleMapInterface(this);

    public WorldClock wc = new WorldClock(this);

    public final Color errorColour = new Color(255,155,155);

    private final JFrame frame;
    public static JTextPane console;
    public static StyledDocument styledDocument;
    private final JTextField input;
    private final JScrollPane scrollPane;
    private boolean inMainMenu;

    public Console()
    {
        try
        {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); //Make the window's look match the OS
        }
        catch (Exception e)
        {
            System.out.println("Console.java cant find SystemLookAndFeel");
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

        frame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e)
            {
                if (inMainMenu)
                {
                    cc.openMainMenu();
                }
            }
        });

        frame.setVisible(true);

        ckb.label.grabFocus();
        cc.openMainMenu();

        java.util.Timer timer = new Timer();
        timer.schedule(wc, 0, 333);

        while (true)
        {
            ckb.label.grabFocus();
            ErrorHandler.LogData(false, getScreenSize()[0] + "/" + getScreenSize()[1]);
        }
    }

    /**
     * Returns the current screenSize in an array
     * @return array of the current screenSize. First element is the height, second is the width.
     */
    public int[] getScreenSize()
    {
        int[] screenSize = new int[2];
        screenSize[0] = frame.getBounds().height;
        screenSize[1] = frame.getBounds().width;
        return screenSize;
    }

    public void setInMainMenu(boolean inMainMenu) {
        this.inMainMenu = inMainMenu;
    }
}
