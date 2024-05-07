package Main.ObjectLogic.Decorations;

import Main.Direction;
import Main.ObjectLogic.BodyLogic.Person;
import Main.ObjectLogic.ObjectTag;
import Main.ObjectLogic.Thing;
import Main.ObjectLogic.Wind;
import Main.RenderLogic.Logic.MapIcon;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.util.*;

public class Decoration extends Thing
{
    private int pushedDownValue;
    private int returnToNormalCost;
    private Image defaultSprite;
    private char defaultIcon;
    private Color defaultColour;
    private Image[] thingEntersNeighbourSprites;
    private char[] thingEntersNeighbourIcons;
    private Image[] thingLeavesSprites;
    private char[] thingLeavesIcons;

    public Decoration(String name, String desc, boolean collision, ObjectTag[] tags, int renderPriority,
                      Image defaultSprite, char defaultIcon, Color defaultColour, Image[] thingEntersNeighbourSprites,
                      char[] thingEntersNeighbour, Image[] thingLeavesSprites, char[] thingLeaves, int returnToNormalCost)
    {
        setName(name);
        setDescription(desc);
        setCollision(collision);
        setTags(tags);
        setRenderPriority(renderPriority);
        this.defaultSprite = defaultSprite;
        this.defaultIcon = defaultIcon;
        this.defaultColour = defaultColour;
        this.thingEntersNeighbourSprites = thingEntersNeighbourSprites;
        this.thingEntersNeighbourIcons = thingEntersNeighbour;
        this.thingLeavesSprites = thingLeavesSprites;
        this.thingLeavesIcons = thingLeaves;
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
            String[] spriteIcon = fileIn.nextLine().split("§");
            //Set Default Icon
            defaultIcon = spriteIcon[1].toCharArray()[0];
            mi.setSymbol(defaultIcon);
            //Set Sprite
            if (spriteIcon.length>2)
            {
                defaultSprite = ImageIO.read(new File(spriteIcon[2]));
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
                String[] icons = s[1].split(":");
                char[] c = new char[8];
                for (int i = 0; i < c.length; i++) {
                    c[i] = icons[i].toCharArray()[0];
                }
                thingEntersNeighbourIcons = Arrays.copyOf(c, c.length);

            }
            if (s.length>2)
            {
                String[] sprites = s[2].split(":");
                Image[] img = new Image[8];
                if (sprites.length == 1) //One Sprite for all directions
                {
                    Image singeSprite = ImageIO.read(new File(sprites[0]));
                    for (int i = 0; i < sprites.length; i++) {
                        img[i] = singeSprite;
                    }
                }
                else // One Sprite for each direction
                {
                    for (int i = 0; i < img.length; i++) {
                        img[i] = ImageIO.read(new File(sprites[i]));
                    }
                }
                thingEntersNeighbourSprites = Arrays.copyOf(img, img.length);
            }
            //Set Thing Leaves Icons
            s = fileIn.nextLine().split("§");
            if (s.length > 1)
            {
                String[] icons = s[1].split(":");
                char[] c = new char[8];
                for (int i = 0; i < c.length; i++) {
                    c[i] = icons[i].toCharArray()[0];
                }
                thingLeavesIcons = Arrays.copyOf(c, c.length);
            }
            if (s.length>2)
            {
                String[] sprites = s[2].split(":");
                Image[] img = new Image[8];
                if (sprites.length == 1) //One Sprite for all directions
                {
                    Image singeSprite = ImageIO.read(new File(sprites[0]));
                    Arrays.fill(img, singeSprite);
                }
                else // One Sprite for each direction
                {
                    for (int i = 0; i < sprites.length; i++) {
                        img[i] = ImageIO.read(new File(sprites[i]));
                    }
                }
                thingLeavesSprites = Arrays.copyOf(img, img.length);
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
        if (thingEntersNeighbourIcons == null) {return;}

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
                    getMapIcon().setSprite(thingEntersNeighbourSprites[0]);
                    getMapIcon().setSymbol(thingEntersNeighbourIcons[0]);
                    break;
                case NORTH_EAST:
                    getMapIcon().setSprite(thingEntersNeighbourSprites[1]);
                    getMapIcon().setSymbol(thingEntersNeighbourIcons[1]);
                    break;
                case EAST:
                    getMapIcon().setSprite(thingEntersNeighbourSprites[2]);
                    getMapIcon().setSymbol(thingEntersNeighbourIcons[2]);
                    break;
                case SOUTH_EAST:
                    getMapIcon().setSprite(thingEntersNeighbourSprites[3]);
                    getMapIcon().setSymbol(thingEntersNeighbourIcons[3]);
                    break;
                case SOUTH:
                    getMapIcon().setSprite(thingEntersNeighbourSprites[4]);
                    getMapIcon().setSymbol(thingEntersNeighbourIcons[4]);
                    break;
                case SOUTH_WEST:
                    getMapIcon().setSprite(thingEntersNeighbourSprites[5]);
                    getMapIcon().setSymbol(thingEntersNeighbourIcons[5]);
                    break;
                case WEST:
                    getMapIcon().setSprite(thingEntersNeighbourSprites[6]);
                    getMapIcon().setSymbol(thingEntersNeighbourIcons[6]);
                    break;
                case NORTH_WEST:
                    getMapIcon().setSprite(thingEntersNeighbourSprites[7]);
                    getMapIcon().setSymbol(thingEntersNeighbourIcons[7]);
                    break;
            }
        }
    }

    @Override
    public void thingLeftCell(Thing t, Direction d) {
        if (thingLeavesIcons == null) {return;}
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
                    getMapIcon().setSprite(thingLeavesSprites[0]);
                    getMapIcon().setSymbol(thingLeavesIcons[0]);
                    break;
                case NORTH_EAST:
                    getMapIcon().setSprite(thingLeavesSprites[1]);
                    getMapIcon().setSymbol(thingLeavesIcons[1]);
                    break;
                case EAST:
                    getMapIcon().setSprite(thingLeavesSprites[2]);
                    getMapIcon().setSymbol(thingLeavesIcons[2]);
                    break;
                case SOUTH_EAST:
                    getMapIcon().setSprite(thingLeavesSprites[3]);
                    getMapIcon().setSymbol(thingLeavesIcons[3]);
                    break;
                case SOUTH:
                    getMapIcon().setSprite(thingLeavesSprites[4]);
                    getMapIcon().setSymbol(thingLeavesIcons[4]);
                    break;
                case SOUTH_WEST:
                    getMapIcon().setSprite(thingLeavesSprites[5]);
                    getMapIcon().setSymbol(thingLeavesIcons[5]);
                    break;
                case WEST:
                    getMapIcon().setSprite(thingLeavesSprites[6]);
                    getMapIcon().setSymbol(thingLeavesIcons[6]);
                    break;
                case NORTH_WEST:
                    getMapIcon().setSprite(thingLeavesSprites[7]);
                    getMapIcon().setSymbol(thingLeavesIcons[7]);
                    break;
            }
        }
    }

    @Override
    public void doAction() {
        if (pushedDownValue == 0)
        {
            getMapIcon().setSprite(defaultSprite);
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
                defaultSprite, defaultIcon,defaultColour, thingEntersNeighbourSprites, thingEntersNeighbourIcons, thingLeavesSprites, thingLeavesIcons,returnToNormalCost);
    }
}
