package Main.RenderLogic;

import Main.RenderLogic.Logo.LogoScreen;
import Main.RenderLogic.Menus.*;
import Main.Settings;
import Main.WorldLogic.WorldClock;

import javax.swing.*;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.util.Timer;

public class Console
{
    public ConsoleBodyInterface cb = new ConsoleBodyInterface(this);
    public ConsoleListRenderer clir = new ConsoleListRenderer(this);
    public ConsoleMapInterface cm = new ConsoleMapInterface(this);
    public ConsolePainter cp = new ConsolePainter(this);
    public WorldClock wc = new WorldClock(this);
    public LogoScreen ls = new LogoScreen(this);
    private GameState gameState;
    private GameState previousGameState;
    public final Color errorColour = new Color(255,155,155);

    protected final JFrame frame;
    public static JTextPane console;
    public static StyledDocument styledDocument;

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
        frame.setSize(Settings.windowWidth,Settings.windowHeight);
        frame.setTitle("Mutant Punk");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Program stops when window is closed
        frame.setResizable(false);
        frame.setBounds(200, 80, Settings.windowWidth, Settings.windowHeight);

        frame.add(cp);
        cp.requestFocusInWindow();
        setGameState(GameState.MAIN_MENU);

        cp.repaint();
        cp.revalidate();
        frame.setVisible(true);

        java.util.Timer timer = new Timer();
        timer.schedule(wc, 0, Settings.tickSpeed);
        wc.startClock();
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

    public GameState getGameState() {return gameState;}
    public GameState getPreviousGameState() {return previousGameState;}
    public void setGameState(GameState gs)
    {
        if (gameState == GameState.LOCAL_MAP_VIEW || gameState == GameState.LOCAL_MAP_MENU)
        {
            previousGameState = gameState;
        }
        switch (gs)
        {
            case MAIN_MENU:
                ls.initializeScreen();
                cp.newListener(new MainMenu(this));
                break;
            case WORLD_MENU:
                cp.newListener(new WorldMenu(this));
                break;
            case LOCAL_MAP_MENU:
                cp.newListener(new LocalMapMenu(this));
                break;
            case LOCAL_MAP_VIEW:
                cp.newListener(new LocalMapView(this));
                break;
            case ALL_CHARACTERS_IN_LOCAL_MAP:
                cp.newListener(new AllCharactersInLocalMapMenu(this));
                break;
            case THING_INSPECTOR:
                cp.newListener(new ThingInspector(this));
                break;
            case BODY_PART_MENU:
                cp.newListener(new BodyPartMenu(this));
                break;
            default:
                throw new RuntimeException("Console couldn't find a new KeyListener for game state: " + gs);
        }
        gameState = gs;
    }
}
