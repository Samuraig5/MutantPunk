package Main.RenderLogic;

import Main.RenderLogic.Logic.ConsoleBodyInterface;
import Main.RenderLogic.Logic.ConsoleMapInterface;
import Main.RenderLogic.Logic.GameState;
import Main.RenderLogic.UI_Painter.ButtonHandler;
import Main.RenderLogic.UI_Painter.Logo.LogoScreen;
import Main.RenderLogic.Menus.*;
import Main.Settings;
import Main.WorldLogic.WorldClock;

import javax.swing.*;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Timer;

public class Console
{
    public ConsoleBodyInterface cb = new ConsoleBodyInterface(this);
    public ConsoleMapInterface cm = new ConsoleMapInterface(this);
    public ConsolePainter cp = new ConsolePainter(this);
    public WorldClock wc = new WorldClock(this);
    public LogoScreen ls = new LogoScreen(this);
    public ButtonHandler bh = new ButtonHandler(this);
    private GameState gameState;
    private GameState previousGameState;
    public final Color errorColour = new Color(255,155,155);

    protected final JFrame frame;
    public static JTextPane console;
    public static StyledDocument styledDocument;

    private File[] bodyPlans;

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

    public GameState getGameState() {return gameState;}
    public GameState getPreviousGameState() {return previousGameState;}
    public List<File> getSortedBodyPlans()
    {
        if (bodyPlans != null)
        {
            // Convert array to list for sorting
            java.util.List<File> fileList = Arrays.asList(bodyPlans);
            // Sort the list alphabetically by file names
            fileList.sort((file1, file2) -> file1.getName().compareToIgnoreCase(file2.getName()));
            return fileList;
        }
        else
        {
            throw new RuntimeException("BodyPlan file list is empty");
        }
    }

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
                MainMenu mm = new MainMenu(this);
                cp.newListener(mm, mm);
                break;
            case WORLD_MENU:
                WorldMenu wm = new WorldMenu(this);
                cp.newListener(wm, wm);
                break;
            case LOCAL_MAP_MENU:
                LocalMapMenu lmm = new LocalMapMenu(this);
                cp.newListener(lmm, lmm);
                File directory = new File("Resources/BodyPlans");
                bodyPlans = directory.listFiles();
                break;
            case LOCAL_MAP_VIEW:
                LocalMapView lmv = new LocalMapView(this);
                cp.newListener(lmv,lmv);
                break;
            case ALL_CHARACTERS_IN_LOCAL_MAP:
                AllCharactersInLocalMapMenu acilmm = new AllCharactersInLocalMapMenu(this);
                cp.newListener(acilmm,acilmm);
                break;
            case THING_INSPECTOR:
                ThingInspector ti = new ThingInspector(this);
                cp.newListener(ti, ti);
                break;
            case BODY_PART_MENU:
                BodyPartMenu bpm = new BodyPartMenu(this);
                cp.newListener(bpm, bpm);
                break;
            default:
                throw new RuntimeException("Console couldn't find a new KeyListener for game state: " + gs);
        }
        gameState = gs;
    }
}
