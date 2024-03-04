package Main.RenderLogic;

import Main.Direction;
import Main.MathHelper;
import Main.ObjectLogic.BodyLogic.BodyPart;
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
    private Person focusedPerson;
    private BodyPart focusedBodyPart;

    private int[] cursorPosition = {0,0};
    private boolean cursorEnabled = false;

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

    public void setFocusedPerson(Person newPerson) {focusedPerson = newPerson;}
    public Person getFocusedPerson() {return focusedPerson;}
    public void setFocusedBodyPart(BodyPart newBodyPart) {focusedBodyPart = newBodyPart;}
    public BodyPart getFocusedBodyPart() {return focusedBodyPart;}

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        repaint(); revalidate();

        this.g = g;

        g.setColor(Color.LIGHT_GRAY);
        g.setFont(new Font("Courier New", Font.PLAIN, Settings.fontSize));

        drawBackground(new Color(50,50,50));

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
            case PERSON_VIEW:
                drawPersonView();
                break;
            case BODY_PART_MENU:
                drawBodyPartMenu();
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
            printString(xPos,yPos+Math.round((50+(i*Settings.fontSize*Settings.relativeFontHeight))), c,s[i]);
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
                if (cursorEnabled && cursorPosition[0] == x && cursorPosition[1] == y)
                {
                    printString(Math.round(x*Settings.fontHeight),
                            Math.round((y+1)*Settings.fontHeight),
                            Color.yellow,"X");
                }
                else
                {
                    MapIcon mi = mapIcons[x][y];
                    String s = String.valueOf(mi.getSymbol());
                    Color c =  mi.getIconColour();

                    printString(Math.round(x*Settings.fontHeight),
                            Math.round((y+1)*Settings.fontHeight),
                            mi.getIconColour(),mi.getSymbol()+"");
                }
            }
        }

        g.drawRoundRect(Math.round(xy[0]*Settings.fontHeight), 5,
                250, Math.round((xy[1]-1)*Settings.fontHeight),
                5, 5);
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

    private void drawPersonView()
    {
        if (focusedPerson == null) {throw new RuntimeException("The ConsolePainter is trying to draw a body but no person is focused");}
        List<String> list = c.cb.openBodyView(focusedPerson);
        for (int i = 0; i < list.size(); i++)
        {
            printString(10, 80+Math.round((i)*Settings.fontHeight), Color.LIGHT_GRAY,
                    MathHelper.indexToLetter(i) + ": " + list.get(i));
        }
    }

    private void drawBodyPartMenu()
    {
        printString(10,80, Color.LIGHT_GRAY, c.cb.displayBodyPartStats(focusedBodyPart));
    }

    public void setCursorEnabled(boolean b) {cursorEnabled = b;}
    public boolean isCursorEnabled() {return cursorEnabled;}

    public int[] getCursorPosition() {return cursorPosition;}
    public void setCursorPosition(int[] i) {cursorPosition = i;}

    public void moveCursor(Direction direction, int amount)
    {
        int[] xy = getCursorPosition();
        switch (direction)
        {
            case NORTH:
                xy[1] -= amount;
                break;
            case EAST:
                xy[0] += amount;
                break;
            case SOUTH:
                xy[1] += amount;
                break;
            case WEST:
                xy[0] -= amount;
                break;
            default:
                throw new RuntimeException("The cursor can't move in that direction");
        }
        int[] xyMax = c.wc.getActiveWorld().getActiveLocalMap().getSize();

        xy[0] = MathHelper.clamp(xy[0],0, xyMax[0]-1);
        xy[1] = MathHelper.clamp(xy[1],0, xyMax[1]-1);

        setCursorPosition(xy);
    }
}
