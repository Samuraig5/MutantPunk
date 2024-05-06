package Main.RenderLogic;

import Main.Direction;
import Main.MathHelper;
import Main.ObjectLogic.BodyLogic.BodyPart;
import Main.ObjectLogic.BodyLogic.BodyPartAbility;
import Main.ObjectLogic.BodyLogic.BodyPartStat;
import Main.ObjectLogic.BodyLogic.Person;
import Main.ObjectLogic.ObjectTag;
import Main.ObjectLogic.Thing;
import Main.Settings;
import Main.WorldLogic.GameWorld;
import Main.WorldLogic.LocalMap;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.awt.MouseInfo.getPointerInfo;

public class ConsolePainter extends JPanel implements ImageObserver
{
    public Console c;
    private Graphics g;
    private Color backgroundColour = new Color(50,50,50);
    private KeyListener activeKeyListener;
    private MouseListener activeMouseListener;
    private Thing inspectedThing;
    private BodyPart inspectedBodyPart;
    private Thing focusedThing;
    private int[] cursorPosition = {0,0};
    private Point mouseCursorPosition = new Point();
    private boolean cursorEnabled = false;
    private int listSelector = 0;

    public ConsolePainter(Console c)
    {
        this.c = c;
    }

    public void newListener(KeyListener keyListener, MouseListener mouseListener)
    {
        newListener(keyListener);
        newListener(mouseListener);
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
    public void newListener(MouseListener mouseListener)
    {
        if (activeMouseListener != null)
        {
            removeMouseListener(activeMouseListener);
        }
        addMouseListener(mouseListener);
        this.setFocusable(true);
        activeMouseListener = mouseListener;
    }

    public void setInspectedThing(Thing newThing) {
        inspectedThing = newThing;}
    public Thing getInspectedThing() {return inspectedThing;}
    public void setInspectedBodyPart(BodyPart newBodyPart) {
        inspectedBodyPart = newBodyPart;}
    public BodyPart getInspectedBodyPart() {return inspectedBodyPart;}

    public int getListSelector() {return listSelector;}
    public void setListSelector(int i) {listSelector = i;}

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        repaint(); revalidate();

        this.g = g;

        g.setColor(Color.LIGHT_GRAY);
        g.setFont(new Font("Courier New", Font.PLAIN, Settings.gridScale));

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

        Point p = getPointerInfo().getLocation();
        Point pa = c.frame.getLocationOnScreen();
        mouseCursorPosition.x = p.x-pa.x-8;
        mouseCursorPosition.y = p.y-pa.y-25;

        //printString(mouseCursorPosition.x, mouseCursorPosition.y, Color.yellow, "O");
    }

    private int printThing(int xPos, int yPos, MapIcon mi)
    {
        if (mi.hasSprite())
        {
            return printSprite(xPos, yPos, mi.getSprite());
        }
        else
        {
            return printString(Math.round(xPos+Settings.gridScale*(0.1f)), Math.round(yPos+Settings.gridScale*0.6f), mi.getIconColour(),mi.getSymbol()+"");
        }
    }

    private int printSprite(int xPos, int yPos, Image image)
    {
        g.drawImage(image, xPos, yPos, Settings.gridScale, Settings.gridScale, this);
        return yPos + image.getWidth(this);
    }

    private int printColouredString(int xPos, int yPos, ColouredString[] content)
    {
        int x = xPos;
        int y = yPos;
        for (int i = 0; i < content.length; i++)
        {
            printString(x,y,content[i].getColor(), content[i].getString());
            y += Settings.gridScale;
        }
        return y;
    }
    private int printColouredStringHorizontal(int xPos, int yPos, ColouredString[] content)
    {
        int x = xPos;
        int y = yPos;
        for (int i = 0; i < content.length; i++)
        {
            printString(x,y,content[i].getColor(), content[i].getString());
            x += Math.round(Settings.gridScale*content[i].getString().length());
        }
        return x;
    }

    private int printString(int xPos, int yPos, Color c, String s)
    {
        Color current = g.getColor();
        g.setColor(c);
        g.drawString(s, xPos, yPos);
        g.setColor(current);
        return xPos + Math.round(s.length()*Settings.gridScale);
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

    private void printCentredString(int yPos, Color c, String s)
    {
        float stringWidth = ((s.length()-1)*Settings.gridScale);
        int xPos = Math.round((Settings.windowWidth-stringWidth)/2);

        printString(xPos,yPos,c,s);
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
        //String[] logo = c.ls.getStaticLogo();
        //printCentredString(50, Color.green, logo);

        char[][] mat = c.ls.getLogoScreen();

        Font currentFont = g.getFont();
        Color current = g.getColor();
        g.setColor(Color.green);
        g.setFont(new Font("Courier New", Font.PLAIN, Settings.menuFontSize+1));
        for (int x = 0; x < mat.length; x++) {
            for (int y = 0; y < mat[x].length; y++) {
                g.drawString(mat[x][y]+"", Math.round(x*Settings.menuFontWidth), Math.round(y*Settings.menuFontHeight));

            }
        }
        g.setFont(currentFont);


        //TODO: Add some easter eggs in light gray so they are only visible when the slime fills up

        printString(Math.round(Settings.menuFontWidth*4) ,Math.round(Settings.menuFontHeight*24), backgroundColour, "Thank Memk!");

        String s1 = "Strange creatures in a strange land";
        String s2 = "a: Generate a new World";

        int width = Math.round(s1.length() * Settings.menuFontWidth);
        int height = Math.round(3.5f * Settings.menuFontHeight);
        int centered = Math.round((Settings.windowWidth-width) / 2f) - Math.round(Settings.menuFontWidth*1.5f);

        g.setColor(backgroundColour);
        g.fillRoundRect(centered,
                Math.round(Settings.menuFontWidth*(24)),
                width, height, 5, 5);
        g.setColor(Color.LIGHT_GRAY);
        g.drawRoundRect(centered,
                Math.round(Settings.menuFontHeight*(24)),
                width, height, 5, 5);

        g.setColor(current);

        printCentredString(Math.round(Settings.menuFontHeight*(25)), Color.green, s1);
        printCentredString(Math.round(Settings.menuFontHeight*(27)), Color.lightGray, s2);
    }

    private void drawWorldMenu()
    {
        GameWorld gw = c.wc.getActiveWorld();

        MapIcon[][] mapIcons = new MapIcon[Settings.worldMapSizeX][Settings.worldMapSizeY];
        for (int x = 0; x < Settings.worldMapSizeX; x++)
        {
            for (int y = 0; y < Settings.worldMapSizeY; y++)
            {
                mapIcons[x][y] = gw.getLocalMaps()[x][y].getMapIcon();
            }
        }

        for (int y = 0; y < Settings.worldMapSizeY; y++)
        {
            for (int x = 0; x < Settings.worldMapSizeX; x++)
            {
                int xBase = Math.round(x*Settings.gridScale);
                int yBase = Math.round((y+1)*Settings.gridScale);

                int xCursorOffset = Math.round(cursorPosition[0]*Settings.gridScale);
                int yCursorOffset = Math.round(cursorPosition[1]*Settings.gridScale);

                int xScreenCenter = Math.round((float) Settings.windowWidth /2);
                int yScreenCenter = Math.round((float) Settings.windowHeight /2);

                int xPos = xBase-xCursorOffset+xScreenCenter;
                int yPos = yBase-yCursorOffset+yScreenCenter;

                if (cursorEnabled && focusedThing == null && cursorPosition[0] == x && cursorPosition[1] == y)
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
    }

    private void drawLocalMapMenu()
    {
        printCentredString(10, Color.lightGray, c.wc.getActiveWorld().getActiveLocalMap().getMapName());

        printString(10, 70, Color.LIGHT_GRAY, "a: Open map view");
        printString(10, Math.round(70+(1*Settings.menuFontHeight)), Color.LIGHT_GRAY, "b: List all local characters");
        List<File> bodyPlans = c.getSortedBodyPlans();

        for (int i = 0; i < bodyPlans.size(); i++)
        {
            String name = MathHelper.indexToLetter(i+2) + ": " + bodyPlans.get(i).getName();
            printString(10, Math.round(70+((i+2)*Settings.menuFontHeight)), Color.LIGHT_GRAY, name);
        }
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
                int xBase = Math.round(x*Settings.gridScale);
                int yBase = Math.round((y+1)*Settings.gridScale);

                int xCursorOffset = Math.round(cursorPosition[0]*Settings.gridScale);
                int yCursorOffset = Math.round(cursorPosition[1]*Settings.gridScale);

                int xScreenCenter = Math.round((float) Settings.windowWidth /2);
                int yScreenCenter = Math.round((float) Settings.windowHeight /2);

                int xPos = xBase-xCursorOffset+xScreenCenter;
                int yPos = yBase-yCursorOffset+yScreenCenter;

                if (cursorEnabled && focusedThing == null && cursorPosition[0] == x && cursorPosition[1] == y)
                {
                    MapIcon mi = new MapIcon('X', Color.yellow);
                    printThing(xPos, yPos, mi);
                }
                else
                {
                    MapIcon mi = mapIcons[x][y];
                    printThing(xPos, yPos, mi);
                }
            }
        }
        drawCursorUI();
        drawPausedButton(xy);
    }
    private void drawCursorUI()
    {
        if (focusedThing != null && focusedThing.getMyCell() != null)
        {
            cursorPosition = focusedThing.getMyCell().getCoordinates();
        }

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
                    printString(startPointX-10, 20+Math.round((i+1)*Settings.menuFontHeight),
                            Color.yellow, ">");
                }
                printString(startPointX, 20+Math.round((i+1)*Settings.menuFontHeight),
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
                height =  Math.round(Settings.menuFontHeight*2);
                startPointX = Settings.windowWidth-270-width-2;
                startPointY = 5;
            }
            else
            {
                width = 100;
                height =  Math.round(Settings.menuFontHeight*2);
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
            int stringLength = Math.round(Settings.menuFontWidth*s.length());
            int stringHeight = Math.round(Settings.menuFontHeight);
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
            printString(10, 80+Math.round((i)*Settings.menuFontHeight), Color.LIGHT_GRAY,
                    MathHelper.indexToLetter(i) + ": " + people.get(i).getName());
        }
    }

    private void drawThingInspector()
    {
        if (inspectedThing == null) {throw new RuntimeException("The ConsolePainter is trying to draw a body but no person is focused");}

        int nameFontSize = Settings.menuFontSize*2;
        int nameOffset = 10 + Math.round(Settings.menuFontHeight);
        printString(10, nameOffset, inspectedThing.getMapIcon().getIconColour(), inspectedThing.getName() + " (" + inspectedThing.getMapIcon().getSymbol() + ")" , nameFontSize);

        if (inspectedThing.getDescription() != null)
        {
            printString(10, nameOffset+20, Color.lightGray, inspectedThing.getDescription());
        }

        int offset = 10;
        for (int i = 0; i < inspectedThing.getTags().length; i++)
        {
            String tag = "[" + inspectedThing.getTags()[i].name() + "] ";
            printString(offset, nameOffset+35, Color.lightGray, tag);

            int stringLength = Math.round(Settings.menuFontWidth*tag.length());
            offset += stringLength;
        }

        if (inspectedThing instanceof Person)
        {
            int paintX = 10;
            int paintY = 70+nameOffset;
            int cellWidth = 10;

            String[][] stats = c.cb.openPersonView((Person) inspectedThing);

            for (int i = 0; i < stats.length; i++) {
                printString(paintX,paintY, Color.LIGHT_GRAY, stats[i][0]);
                for (int j = 1; j < stats[i].length; j++) {
                    int x = paintX + Math.round(Settings.menuFontWidth*j*cellWidth)+Math.round(Settings.menuFontWidth*cellWidth);
                    printString(x,paintY, Color.LIGHT_GRAY, stats[i][j]);
                }
                paintY += Math.round(Settings.menuFontWidth);
            }

            ColouredString[][] body = c.cb.getBodyView((Person) inspectedThing);
            for (int i = 0; i < body.length; i++)
            {
                printColouredStringHorizontal(10,paintY+nameOffset+Math.round((i)*Settings.menuFontHeight), body[i]);
            }
        }
    }

    private void drawBodyPartMenu()
    {
        int nameFontSize = Settings.menuFontSize*2;
        int y = 10 + Math.round(Settings.menuFontHeight);
        printString(10,y,Color.LIGHT_GRAY, inspectedBodyPart.getName(),nameFontSize);
        y += Settings.menuFontHeight;

        ColouredString[] cs = new ColouredString[4];
        cs[0] = new ColouredString("Health", Color.LIGHT_GRAY);
        float fCurr = inspectedBodyPart.getCurrentHealth();
        float fMax = inspectedBodyPart.getStats()[BodyPartStat.MAX_HEALTH];
        cs[1] = new ColouredString(fCurr+"", Color.lightGray);
        cs[2] = new ColouredString(" / ", Color.lightGray);
        cs[3] = new ColouredString(fMax+"", Color.lightGray);

        if (fCurr > (fMax/2))
        {
            cs[1].setColor(Color.green);
        }
        else if (fCurr > (fMax/4))
        {
            cs[1].setColor(Color.yellow);
        }
        else
        {
            cs[1].setColor(Color.red);
        }

        printColouredStringHorizontal(10, y, cs);

        y += Math.round(Settings.menuFontHeight);

        printString(10,y,Color.LIGHT_GRAY,"Attachment Capacity: " +
                inspectedBodyPart.getRemainingAttachmentCapacity() + " / " +
                inspectedBodyPart.getStats()[BodyPartStat.ATTACHMENT_CAPACITY]);
        y += Math.round(Settings.menuFontHeight)*2;

        y = drawBodyPartStats(y);
        drawBodyPartAbilities(y);
    }

    private int drawBodyPartStats(int startPos)
    {
        int paintX = 10;
        int paintY = startPos;
        int cellWidth = 10;

        String[][] stats = c.cb.displayBodyPartStats(inspectedBodyPart);
        String[][] statDesc = {{"","Final", "Gross", "Modifier", "Upstream", "Upstream", "Person"}, {"","", "", "", "Gross", "Modifier", "Modifier"}};
        for (int j = 0; j < stats[0].length; j++)
        {
            int x = paintX + Math.round(Settings.menuFontWidth*j*cellWidth)+Math.round(Settings.menuFontWidth*cellWidth);
            printString(x,paintY, Color.LIGHT_GRAY, statDesc[0][j]);
            printString(x,paintY+Math.round(Settings.menuFontHeight), Color.LIGHT_GRAY, statDesc[1][j]);
        }
        paintY += Math.round(Settings.menuFontHeight*2.5f);

        for (int i = 0; i < stats.length; i++) {
            printString(paintX,paintY, Color.LIGHT_GRAY, stats[i][0]);
            for (int j = 1; j < stats[i].length; j++) {
                int x = paintX + Math.round(Settings.menuFontWidth*j*cellWidth)+Math.round(Settings.menuFontWidth*cellWidth);
                printString(x,paintY, Color.LIGHT_GRAY, stats[i][j]);
            }
            paintY += Math.round(Settings.menuFontHeight);
        }

        return paintY + Math.round(Settings.menuFontHeight*stats[0].length);
    }

    private void drawBodyPartAbilities(int startPos)
    {
        for (int i = 0; i < inspectedBodyPart.getAbilities().size(); i++)
        {
            BodyPartAbility bpa = inspectedBodyPart.getAbilities().get(i);
            ObjectTag[] obt = bpa.getRelatedObjectTags();

            int xOffset = 10;

            xOffset = printString(xOffset, startPos, Color.LIGHT_GRAY, "["+bpa.getAbilityName()+"]");

            xOffset = printString(xOffset, startPos, Color.LIGHT_GRAY, "("+bpa.getAbilityTag()+"):");


            xOffset = printString(xOffset, startPos, Color.LIGHT_GRAY, "<");
            for (int j = 0; j < obt.length; j++)
            {
                xOffset = printString(xOffset, startPos, Color.LIGHT_GRAY, "["+obt[j]+"]");
            }
            xOffset = printString(xOffset, startPos, Color.LIGHT_GRAY, ">, ");

            if (bpa.getCapacity() != 0)
            {
                xOffset = printString(xOffset, startPos, Color.LIGHT_GRAY, "Capacity:");
                xOffset = printString(xOffset, startPos, Color.LIGHT_GRAY, "(");
                xOffset = printString(xOffset, startPos, Color.LIGHT_GRAY, String.valueOf(bpa.getCurrentFillLevel()));
                xOffset = printString(xOffset, startPos, Color.LIGHT_GRAY, "/");
                xOffset = printString(xOffset, startPos, Color.LIGHT_GRAY, String.valueOf(bpa.getCapacity()));
                xOffset = printString(xOffset, startPos, Color.LIGHT_GRAY, ")");
                if (bpa.getEfficiency() != 0)
                {
                    xOffset = printString(xOffset, startPos, Color.LIGHT_GRAY, ", ");
                }
            }
            if (bpa.getEfficiency() != 0)
            {
                xOffset = printString(xOffset, startPos, Color.LIGHT_GRAY, "Efficiency:");
                xOffset = printString(xOffset, startPos, Color.LIGHT_GRAY, String.valueOf(bpa.getEfficiency()));
            }
        }
    }

    public void setCursorEnabled(boolean b) {cursorEnabled = b;}
    public boolean isCursorEnabled() {return cursorEnabled;}

    public int[] getCursorPosition() {return cursorPosition;}
    public void setCursorPosition(int[] i) {cursorPosition = i;}
    public void setFocusedThing(Thing t){focusedThing = t;}

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
        if (c.getGameState() == GameState.WORLD_MENU)
        {
            xy[0] = MathHelper.clamp(xy[0],0, Settings.worldMapSizeX-1);
            xy[1] = MathHelper.clamp(xy[1],0, Settings.worldMapSizeY-1);
        }
        else if (c.getGameState() == GameState.LOCAL_MAP_VIEW)
        {
            xy[0] = MathHelper.clamp(xy[0],0, Settings.localMapSizeX-1);
            xy[1] = MathHelper.clamp(xy[1],0, Settings.localMapSizeY-1);
        }


        setCursorPosition(xy);
    }

    public int[] getMouseCellCoordsOnLocalMap()
    {
        int[] xy = getMouseCellCoords();

        float xScreenCenter = ((float) Settings.windowWidth/Settings.gridScale/2);
        float yScreenCenter = ((float) Settings.windowHeight/Settings.gridScale/2) + 1;

        System.out.println(((float) Settings.windowWidth/Settings.gridScale/2) + 0.75f);

        xy[0] = Math.round(xy[0] + cursorPosition[0] - xScreenCenter);
        xy[1] = Math.round(xy[1] + cursorPosition[1] - yScreenCenter);

        return xy;
    }

    public int[] getMouseCellCoords()
    {
        //TODO: Actually Implement this logic
        int x = Math.round((float) mouseCursorPosition.x / Settings.gridScale);
        int y = Math.round((float) mouseCursorPosition.y / Settings.gridScale);

        return new int[] {x,y};
    }
}
