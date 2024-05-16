package Main.RenderLogic;

import Main.Direction;
import Main.MathHelper;
import Main.ObjectLogic.BodyLogic.BodyPart;
import Main.ObjectLogic.BodyLogic.Person;
import Main.ObjectLogic.Thing;
import Main.RenderLogic.Logic.GameState;
import Main.RenderLogic.Map_Painter.MapPainter;
import Main.RenderLogic.UI_Painter.ButtonHandler;
import Main.RenderLogic.UI_Painter.UIPainter;
import Main.Settings;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.image.ImageObserver;

import static java.awt.MouseInfo.getPointerInfo;

public class ConsolePainter extends JPanel implements ImageObserver
{
    public Console c;
    public Graphics g;
    public Color backgroundColour = new Color(50,50,50);
    private KeyListener activeKeyListener;
    private MouseListener activeMouseListener;
    private Person playerCharacter;
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
    public Thing getFocusedThing() {return focusedThing;}
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
                UIPainter.drawMainMenu(this);
                break;
            case WORLD_MENU:
                MapPainter.drawWorldView(this);
                break;
            case LOCAL_MAP_MENU:
                UIPainter.drawLocalMapMenu(this);
                break;
            case LOCAL_MAP_VIEW:
                MapPainter.drawLocalMapView(this);
                break;
            case ALL_CHARACTERS_IN_LOCAL_MAP:
                UIPainter.drawListOfLocalPeople(this);
                break;
            case THING_INSPECTOR:
                UIPainter.drawThingInspector(this);
                break;
            case BODY_PART_MENU:
                UIPainter.drawBodyPartMenu(this);
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

    private int printString(int xPos, int yPos, Color c, String s)
    {
        Color current = g.getColor();
        g.setColor(c);
        g.drawString(s, xPos, yPos);
        g.setColor(current);
        return xPos + Math.round(s.length()*Settings.gridScale);
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

    /**
     * Returns the coordinates of the cell the mouse cursor is hovering over right now.
     * @return Coordinates of target cell.
     */
    public int[] getMouseCellCoordsOnLocalMap()
    {
        int[] xy = getMouseCellScaleCoords();

        float xScreenCenter = ((float) Settings.windowWidth/Settings.gridScale/2);
        float yScreenCenter = ((float) Settings.windowHeight/Settings.gridScale/2);

        //System.out.println(((float) Settings.windowWidth/Settings.gridScale/2) + 0.75f);

        xy[0] = Math.round(xy[0] + cursorPosition[0] - xScreenCenter);
        xy[1] = Math.round(xy[1] + cursorPosition[1] - yScreenCenter) -2;

        return xy;
    }

    /**
     * Transforms the mouse screen coordinates to the size of cell grid coordinates.
     * Doesn't return the cell coordinates of the cell that was clicked on. Simply a different view of the mouse coords.
     * @return Cell grid sized screen coordinates of the mouse
     */
    private int[] getMouseCellScaleCoords()
    {
        //TODO: Actually Implement this logic
        int x = Math.round((float) mouseCursorPosition.x / Settings.gridScale);
        int y = Math.round((float) mouseCursorPosition.y / Settings.gridScale);

        return new int[] {x,y};
    }

    public void setPlayerCharacter(Person p)
    {
        if (playerCharacter != null)
        {
            playerCharacter.isPlayer(false);
        }
        playerCharacter = p;
        playerCharacter.isPlayer(true);
    }
    public Person getPlayerCharacter()
    {
        return playerCharacter;
    }
}
