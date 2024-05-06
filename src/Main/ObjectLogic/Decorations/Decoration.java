package Main.ObjectLogic.Decorations;

import Main.Direction;
import Main.ObjectLogic.BodyLogic.Person;
import Main.ObjectLogic.ObjectTag;
import Main.ObjectLogic.Thing;
import Main.ObjectLogic.Wind;
import Main.RenderLogic.MapIcon;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class Decoration extends Thing
{
    private int pushedDownValue;
    private int returnToNormalCost;
    private Image defaultSprite;
    private char defaultIcon;
    private Color defaultColour;
    private char[] thingEntersNeightbour;
    private char[] thingLeaves;

    public Decoration(String name, String desc, boolean collision, ObjectTag[] tags, int renderPriority, Image defaultSprite, char defaultIcon,
                      Color defaultColour, char[] thingEntersNeighbour, char[] thingLeaves, int returnToNormalCost)
    {
        setName(name);
        setDescription(desc);
        setCollision(collision);
        setTags(tags);
        setRenderPriority(renderPriority);
        this.defaultSprite = defaultSprite;
        this.defaultIcon = defaultIcon;
        this.defaultColour = defaultColour;
        this.thingEntersNeightbour = thingEntersNeighbour;
        this.thingLeaves = thingLeaves;
        this.returnToNormalCost = returnToNormalCost;

        MapIcon mi = new MapIcon();
        mi.setSprite(defaultSprite);
        mi.setSymbol(defaultIcon);
        mi.setIconColour(defaultColour);
        setMapIcon(mi);
    }
    public Decoration(String filePath)
    {
        try
        {
            MapIcon mi = new MapIcon();

            Scanner fileIn = new Scanner(new File(filePath));
            //Set Name
            setName(fileIn.nextLine().split("§")[1]);
            //Set Description
            setDescription(fileIn.nextLine().split("§")[1]);
            //Set Collision (True if it can't be recognized)
            setCollision(!Objects.equals(fileIn.nextLine().split("§")[1], "false"));
            //Set Tags
            setTags(ObjectTag.translateStringToTag(fileIn.nextLine().split("§")[1].split(":")));
            //Set Render Priority
            setRenderPriority(Integer.parseInt(fileIn.nextLine().split("§")[1]));
            String[] spriteIcon = fileIn.nextLine().split("§")[1].split(":");
            //Set Default Icon
            defaultIcon = spriteIcon[0].toCharArray()[0];
            mi.setSymbol(defaultIcon);
            //Set Sprite
            if (spriteIcon.length>1)
            {
                defaultSprite = ImageIO.read(new File(spriteIcon[1]));
                mi.setSprite(defaultSprite);
            }
            //Set Default Colour
            String[] s = fileIn.nextLine().split("§")[1].split(":");
            defaultColour = new Color(Integer.parseInt(s[0]), Integer.parseInt(s[1]), Integer.parseInt(s[2]));
            mi.setIconColour(defaultColour);

            //Set Thing Enters Icons
            s = fileIn.nextLine().split("§");
            if (s.length > 1)
            {
                s = s[1].split(":");
                char[] c = new char[s.length];
                for (int i = 0; i < c.length; i++) {
                    c[i] = s[i].toCharArray()[0];
                }
                thingEntersNeightbour = Arrays.copyOf(c, c.length);
            }
            //Set Thing Leaves Icons
            s = fileIn.nextLine().split("§");
            if (s.length > 1)
            {
                s = s[1].split(":");
                char[] c = new char[s.length];
                for (int i = 0; i < c.length; i++) {
                    c[i] = s[i].toCharArray()[0];
                }
                thingLeaves = Arrays.copyOf(c, c.length);
            }
            //Set Action Cost To Return To Normal Icon
            returnToNormalCost = Integer.parseInt(fileIn.nextLine().split("§")[1]);

            setMapIcon(mi);
        }
        catch (Exception e)
        {
            throw new RuntimeException("Error when creating a decoration object");
        }
    }

    @Override
    public void newNeightbour(Thing t, Direction d)
    {
        if (thingEntersNeightbour == null) {return;}

        boolean changeDirection = false;
        if (t instanceof Person)
        {
            if (pushedDownValue <= 10)
            {
                changeDirection = true;
                pushedDownValue = 10;
            }
        }
        else if (t instanceof Wind)
        {
            if (pushedDownValue <= 1)
            {
                changeDirection = true;
                pushedDownValue = 1;
            }
        }
        if (changeDirection)
        {
            switch (d)
            {
                case NORTH:
                    getMapIcon().setSymbol(thingEntersNeightbour[0]);
                    break;
                case NORTH_EAST:
                    getMapIcon().setSymbol(thingEntersNeightbour[1]);
                    break;
                case EAST:
                    getMapIcon().setSymbol(thingEntersNeightbour[2]);
                    break;
                case SOUTH_EAST:
                    getMapIcon().setSymbol(thingEntersNeightbour[3]);
                    break;
                case SOUTH:
                    getMapIcon().setSymbol(thingEntersNeightbour[4]);
                    break;
                case SOUTH_WEST:
                    getMapIcon().setSymbol(thingEntersNeightbour[5]);
                    break;
                case WEST:
                    getMapIcon().setSymbol(thingEntersNeightbour[6]);
                    break;
                case NORTH_WEST:
                    getMapIcon().setSymbol(thingEntersNeightbour[7]);
                    break;
            }
        }
    }

    @Override
    public void thingLeftCell(Thing t, Direction d) {
        if (thingLeaves == null) {return;}
        boolean changeDirection = false;
        if (t instanceof Person)
        {
            if (pushedDownValue <= 10)
            {
                changeDirection = true;
                pushedDownValue = 10;
            }
        }
        else if (t instanceof Wind)
        {
            if (pushedDownValue <= 1)
            {
                changeDirection = true;
                pushedDownValue = 1;
            }
        }
        if (changeDirection)
        {
            switch (d)
            {
                case NORTH:
                    getMapIcon().setSymbol(thingLeaves[0]);
                    break;
                case NORTH_EAST:
                    getMapIcon().setSymbol(thingLeaves[1]);
                    break;
                case EAST:
                    getMapIcon().setSymbol(thingLeaves[2]);
                    break;
                case SOUTH_EAST:
                    getMapIcon().setSymbol(thingLeaves[3]);
                    break;
                case SOUTH:
                    getMapIcon().setSymbol(thingLeaves[4]);
                    break;
                case SOUTH_WEST:
                    getMapIcon().setSymbol(thingLeaves[5]);
                    break;
                case WEST:
                    getMapIcon().setSymbol(thingLeaves[6]);
                    break;
                case NORTH_WEST:
                    getMapIcon().setSymbol(thingLeaves[7]);
                    break;
            }
        }
    }

    @Override
    public void doAction() {
        if (pushedDownValue == 0)
        {
            getMapIcon().setSymbol(defaultIcon);
            setActionPoints(0);
            return;
        }

        int actionCost = returnToNormalCost;
        if (getActionPoints() < actionCost) {return;}

        changeActionPoints(-actionCost);

        if (pushedDownValue > 0 && Math.random() < 0.9f)
        {
            pushedDownValue -= 1;
        }
    }

    public Decoration copy()
    {
        return new Decoration(getName(),getDescription(),hasCollision(),getTags(),getRenderPriority(),
                defaultSprite, defaultIcon,defaultColour,thingEntersNeightbour,thingLeaves,returnToNormalCost);
    }
}
