package Main.RenderLogic;

import Main.MathHelper;
import Main.ObjectLogic.BodyLogic.Person;
import Main.Settings;
import Main.WorldLogic.LocalMap;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

public class ConsolePainter extends JPanel
{
    public Console c;

    private Graphics g;

    private KeyListener activeKeyListener;

    public ConsolePainter(Console c)
    {
        this.c = c;
    }

    public void newListener(KeyListener keyListener)
    {
        if (activeKeyListener != null)
        {
            removeKeyListener(activeKeyListener);
        }
        addKeyListener(keyListener);
        this.setFocusable(true);
        activeKeyListener = keyListener;
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        repaint(); revalidate();

        this.g = g;

        g.setColor(Color.LIGHT_GRAY);
        g.setFont(new Font("Courier New", Font.PLAIN, Settings.fontSize));

        drawBackground(new Color(50,50,50));

        GameState gameState = c.getGameState();

        switch (c.getGameState())
        {
            case MAIN_MENU:
                drawMainMenu();
                break;
            case WORLD_MENU:
                drawWorldMenu();
                break;
            case LOCAL_MAP_MENU:
                drawLocalMapMenu();
                break;
            case LOCAL_MAP_VIEW:
                drawLocalMapView();
                break;
            case ALL_CHARACTERS_IN_LOCAL_MAP:
                drawListOfLocalPeople();
                break;
            default:
                printCentredString(50,c.errorColour,"THIS GAME STATE DOESN'T EXIST");
        }
    }

    private void printString(int xPos, int yPos, Color c, String s)
    {
        Color current = g.getColor();
        g.setColor(c);
        g.drawString(s, xPos, yPos);
        g.setColor(current);
    }

    private void printString(int xPos, int yPos, Color c, String[] s)
    {
        for (int i = 0; i < s.length; i++)
        {
            printString(xPos,yPos+Math.round((50+(i*Settings.fontSize*Settings.relativeFontHeight))), c,s);
        }
    }

    private void printCentredString(int yPos, Color c, String s)
    {
        float stringWidth = ((s.length()-1)* (Settings.fontSize)*Settings.relativeFontWidth);
        int xPos = Math.round((Settings.windowWidth-stringWidth)/2);

        printString(xPos,yPos,c,s);
    }

    private void printCentredString(int yPos, Color c, String s[])
    {
        for (int i = 0; i < s.length; i++) {
            float stringWidth = ((s[i].length()-1)* (Settings.fontSize)*Settings.relativeFontWidth);
            int xPos = Math.round((Settings.windowWidth-stringWidth)/2);

            printString(xPos,yPos+Math.round((i*Settings.fontSize*Settings.relativeFontHeight)), c,s[i]);
        }
    }

    private void drawBackground(Color backColor)
    {
        Color current = g.getColor();
        g.setColor(backColor);
        g.fillRect(0, 0, Settings.windowWidth, Settings.windowHeight);
        g.setColor(current);
    }

    private void drawMainMenu()
    {
        String[] logo =c.cc.generateLogo();
        printCentredString(50,Color.green,logo);

        printCentredString(Math.round(Settings.fontHeight*(logo.length+1))+50, Color.green, "Strange creatures in a strange land");
        printCentredString(Math.round(Settings.fontHeight*(logo.length+3))+50, Color.lightGray, "a: Generate a new World");
    }

    private void drawWorldMenu()
    {
        printCentredString(10, Color.lightGray, "Welcome to a new world");

        printString(10, 70, Color.LIGHT_GRAY, "a: Generate a new Local Map");
        List<LocalMap> localMaps = c.wc.getActiveWorld().getLocalMaps();
        List<String> localMapsNames = new ArrayList<>();

        Color current = g.getColor();
        g.setColor(Color.lightGray);
        g.fillRect(10, 75, 200, 1);
        g.setColor(current);

        for (int i = 0; i < localMaps.size(); i++) {
            printString(10, 80+Math.round((i+1)*Settings.fontHeight), Color.LIGHT_GRAY,
                    MathHelper.indexToLetter(i+1) + ": " + localMaps.get(i).getMapName());
        }
    }

    private void drawLocalMapMenu()
    {
        printCentredString(10, Color.lightGray, c.wc.getActiveWorld().getActiveLocalMap().getMapName());

        printString(10, 70, Color.LIGHT_GRAY, "a: Open map view");
        printString(10, Math.round(70+(1*Settings.fontHeight)), Color.LIGHT_GRAY, "b: List all local characters");
        printString(10, Math.round(70+(2*Settings.fontHeight)), Color.LIGHT_GRAY, "c: Spawn a Human");

    }

    private void drawLocalMapView()
    {
        LocalMap lm = c.wc.getActiveWorld().getActiveLocalMap();

        int[] xy = lm.getSize();
        MapIcon[][] mapIcons = c.cm.TranslateCellsToSymbols(lm.getCells(),xy);

        for (int y = 0; y < xy[1]; y++)
        {
            for (int x = 0; x < xy[0]; x++)
            {
                MapIcon mi = mapIcons[x][y];
                String s = String.valueOf(mi.getSymbol());
                Color c =  mi.getIconColour();

                printString(Math.round(x*Settings.fontHeight),
                        Math.round(y*Settings.fontHeight),
                        mi.getIconColour(),mi.getSymbol()+"");
            }
        }
    }

    private void drawListOfLocalPeople()
    {
        List<Person> people = c.wc.getActiveWorld().getActiveLocalMap().getLocalPeople();

        for (int i = 0; i < people.size(); i++)
        {
            printString(10, 80+Math.round((i)*Settings.fontHeight), Color.LIGHT_GRAY,
                    MathHelper.indexToLetter(i) + ": " + people.get(i).getName());
        }
    }
}
