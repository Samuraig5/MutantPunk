package Main.RenderLogic;

import Main.Direction;
import Main.MathHelper;
import Main.ObjectLogic.BodyLogic.BodyPart;
import Main.ObjectLogic.BodyLogic.BodyPartAbility;
import Main.ObjectLogic.BodyLogic.Person;
import Main.ObjectLogic.ObjectTag;
import Main.ObjectLogic.Thing;
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
    private Color backgroundColour = new Color(50,50,50);
    private KeyListener activeKeyListener;
    private Thing focusedThing;
    private BodyPart focusedBodyPart;
    private int[] cursorPosition = {0,0};
    private boolean cursorEnabled = false;
    private int listSelector = 0;

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

    public void setFocusedThing(Thing newThing) {
        focusedThing = newThing;}
    public Thing getFocusedThing() {return focusedThing;}
    public void setFocusedBodyPart(BodyPart newBodyPart) {focusedBodyPart = newBodyPart;}
    public BodyPart getFocusedBodyPart() {return focusedBodyPart;}

    public int getListSelector() {return listSelector;}
    public void setListSelector(int i) {listSelector = i;}

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        repaint(); revalidate();

        this.g = g;

        g.setColor(Color.LIGHT_GRAY);
        g.setFont(new Font("Courier New", Font.PLAIN, Settings.fontSize));

        drawBackground(backgroundColour);

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
            case THING_INSPECTOR:
                drawThingInspector();
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

    private void printString(int xPos, int yPos, Color c, String s, int fontSize)
    {
        Color currentColour = g.getColor();
        g.setColor(c);

        Font currentFont = g.getFont();
        g.setFont(new Font("Courier New", Font.PLAIN, fontSize));
        g.drawString(s, xPos, yPos);
        g.setFont(currentFont);

        g.setColor(currentColour);
    }

    private int printString(int xPos, int yPos, Color c, String[] s)
    {
        for (int i = 0; i < s.length; i++)
        {
            printString(xPos,yPos+Math.round(i*Settings.fontSize*Settings.relativeFontHeight), c,s[i]);
        }
        return yPos+Math.round((s.length-1)*Settings.fontSize*Settings.relativeFontHeight);
    }

    private void printString(int xPos, int yPos, Color c, List<String> s)
    {
        for (int i = 0; i < s.size(); i++)
        {
            printString(xPos,yPos+Math.round(i*Settings.fontSize*Settings.relativeFontHeight), c,s.get(i));
        }
    }

    private void printCentredString(int yPos, Color c, String s)
    {
        float stringWidth = ((s.length()-1)* (Settings.fontSize)*Settings.relativeFontWidth);
        int xPos = Math.round((Settings.windowWidth-stringWidth)/2);

        printString(xPos,yPos,c,s);
    }

    private void printCentredString(int yPos, Color c, String[] s)
    {
        for (int i = 0; i < s.length; i++) {
            float stringWidth = ((s[i].length()-1)* (Settings.fontSize)*Settings.relativeFontWidth);
            int xPos = Math.round((Settings.windowWidth-stringWidth)/2);

            printString(xPos,yPos+Math.round(i*Settings.fontSize*Settings.relativeFontHeight), c,s[i]);
        }
    }

    private void printCentredString(int yPos, Color c, List<String> s)
    {
        for (int i = 0; i < s.size(); i++) {
            float stringWidth = ((s.get(i).length()-1)* (Settings.fontSize)*Settings.relativeFontWidth);
            int xPos = Math.round((Settings.windowWidth-stringWidth)/2);

            printString(xPos,yPos+Math.round(i*Settings.fontSize*Settings.relativeFontHeight), c,s.get(i));
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
        printString(10, Math.round(70+(3*Settings.fontHeight)), Color.LIGHT_GRAY, "d: Spawn a Slime");
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
                int xBase = Math.round(x*Settings.fontHeight);
                int yBase = Math.round((y+1)*Settings.fontHeight);

                int xCursorOffset = Math.round(cursorPosition[0]*Settings.fontHeight);
                int yCursorOffset = Math.round(cursorPosition[1]*Settings.fontHeight);

                int xScreenCenter = Math.round((float) Settings.windowWidth /2);
                int yScreenCenter = Math.round((float) Settings.windowHeight /2);

                int xPos = xBase-xCursorOffset+xScreenCenter;
                int yPos = yBase-yCursorOffset+yScreenCenter;

                if (cursorEnabled && cursorPosition[0] == x && cursorPosition[1] == y)
                {
                    printString(xPos, yPos, Color.yellow,"X");
                }
                else
                {
                    MapIcon mi = mapIcons[x][y];
                    String s = String.valueOf(mi.getSymbol());
                    Color c =  mi.getIconColour();

                    printString(xPos, yPos, mi.getIconColour(),mi.getSymbol()+"");
                }
            }
        }
        drawCursorUI();
        drawPausedButton(xy);
    }
    private void drawCursorUI()
    {
        if (cursorEnabled)
        {
            int width = 250;
            int heigth =  Settings.windowHeight-50;
            int startPointX = Settings.windowWidth-250;
            int startPointY = 5;

            Color current = g.getColor();
            g.setColor(backgroundColour);
            g.fillRoundRect(startPointX-20, startPointY,
                    width, heigth, 5, 5);
            g.setColor(Color.LIGHT_GRAY);
            g.drawRoundRect(startPointX-20, startPointY,
                    width, heigth, 5, 5);
            g.setColor(current);

            List<Thing> things = c.wc.getActiveWorld().getActiveLocalMap().getCell(cursorPosition[0], cursorPosition[1]).getThings();
            listSelector = MathHelper.clamp(listSelector, 0, things.size()-1);
            for (int i = 0; i < things.size(); i++)
            {
                if (listSelector == i)
                {
                    printString(startPointX-10, 20+Math.round((i+1)*Settings.fontHeight),
                            Color.yellow, ">");
                }
                printString(startPointX, 20+Math.round((i+1)*Settings.fontHeight),
                        things.get(i).getMapIcon().getIconColour(), things.get(i).getName());
            }
        }
    }

    private void drawPausedButton(int[] mapSize)
    {
        if (!c.wc.isClockRunning())
        {
            int width;
            int height;
            int startPointX;
            int startPointY;
            if (cursorEnabled)
            {
                width = 100;
                height =  Math.round(Settings.fontHeight*2);
                startPointX = Settings.windowWidth-270-width-2;
                startPointY = 5;
            }
            else
            {
                width = 100;
                height =  Math.round(Settings.fontHeight*2);
                startPointX = Settings.windowWidth-width-20;
                startPointY = 5;
            }

            Color current = g.getColor();
            g.setColor(backgroundColour);
            g.fillRoundRect(startPointX,startPointY, width, height, 5,5);
            g.setColor(Color.LIGHT_GRAY);
            g.drawRoundRect(startPointX,startPointY, width, height, 5,5);
            g.setColor(current);

            String s = "PAUSED";
            int stringLength = Math.round(Settings.fontWidth*s.length());
            int stringHeight = Math.round(Settings.fontHeight);
            printString(startPointX+(width/2)-(stringLength/2),
                    startPointY+(height/2)+(stringHeight/3),
                    c.errorColour,s);
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

    private void drawThingInspector()
    {
        if (focusedThing == null) {throw new RuntimeException("The ConsolePainter is trying to draw a body but no person is focused");}

        int nameFontSize = Settings.fontSize*2;
        int nameOffset = 10 + Math.round(nameFontSize*Settings.relativeFontHeight);
        printString(10, nameOffset, focusedThing.getMapIcon().getIconColour(), focusedThing.getName() + " (" + focusedThing.getMapIcon().getSymbol() + ")" , nameFontSize);

        if (focusedThing.getDescription() != null)
        {
            printString(10, nameOffset+20, Color.lightGray, focusedThing.getDescription());
        }

        int offset = 10;
        for (int i = 0; i < focusedThing.getTags().length; i++)
        {
            String tag = "[" + focusedThing.getTags()[i].name() + "] ";
            printString(offset, nameOffset+35, Color.lightGray, tag);

            int stringLength = Math.round(Settings.fontWidth*tag.length());
            offset += stringLength;
        }

        if (focusedThing instanceof Person)
        {
            List<String> stats = c.cb.openPersonView((Person) focusedThing);
            printString(10, 70+nameOffset, Color.lightGray, stats);

            List<String> body = c.cb.openBodyView((Person) focusedThing);
            for (int i = 0; i < body.size(); i++)
            {
                printString(10, 270+nameOffset+Math.round((i)*Settings.fontHeight), Color.LIGHT_GRAY,
                        MathHelper.indexToLetter(i) + ": " + body.get(i));
            }
        }
    }

    private void drawBodyPartMenu()
    {
        int endPos = printString(10,130, Color.LIGHT_GRAY, c.cb.displayBodyPartStats(focusedBodyPart));
        endPos += 20;

        for (int i = 0; i < focusedBodyPart.getAbilities().size(); i++)
        {
            BodyPartAbility bpa = focusedBodyPart.getAbilities().get(i);
            ObjectTag[] obt = bpa.getRelatedObjectTags();

            printString(10, endPos+20, Color.LIGHT_GRAY, "["+bpa.getAbilityTag()+"]:");

            int xOffset = Math.round(10+((bpa.getAbilityTag().toString().length()+3)*Settings.fontWidth));

            printString(xOffset, endPos+20, Color.LIGHT_GRAY, "<");
            xOffset += Math.round(Settings.fontWidth);
            for (int j = 0; j < obt.length; j++)
            {
                printString(xOffset, endPos+20, Color.LIGHT_GRAY, "["+obt[j]+"]");
                xOffset = xOffset + Math.round((obt[j].toString().length()+2)*Settings.fontWidth);
            }
            printString(xOffset, endPos+20, Color.LIGHT_GRAY, ">");
        }
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
