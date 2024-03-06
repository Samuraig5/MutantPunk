package Main.ObjectLogic.Decorations;

import Main.MathHelper;
import Main.ObjectLogic.BodyLogic.Person;
import Main.ObjectLogic.ObjectTag;
import Main.ObjectLogic.Thing;
import Main.ObjectLogic.Wind;
import Main.RenderLogic.MapIcon;
import Main.Direction;


import java.awt.*;
import java.util.Random;

public class Grass extends Thing
{
    private int pushedDownValue;
    public Grass()
    {
        super();
        setCollision(false);

        MapIcon mi = new MapIcon();
        mi.setSymbol('.');
        mi.setIconColour(Color.green);
        setMapIcon(mi);

        setRenderPriority(0);

        setName("Grass");
        setDescription("This is a cluster of lush green grass");
        setTags(new ObjectTag[] {ObjectTag.SOLID, ObjectTag.LIVING, ObjectTag.PLANT});
    }

    @Override
    public void newNeightbour(Thing t, Direction d) {

    }

    @Override
    public void thingLeftCell(Thing t, Direction d) {
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
                case SOUTH:
                    getMapIcon().setSymbol('|');
                    break;
                case NORTH_EAST:
                case SOUTH_WEST:
                    getMapIcon().setSymbol('/');
                    break;
                case EAST:
                case WEST:
                    getMapIcon().setSymbol('-');
                    break;
                case SOUTH_EAST:
                case NORTH_WEST:
                    getMapIcon().setSymbol('\\');
                    break;
            }
        }
    }

    @Override
    public void doAction() {
        if (pushedDownValue == 0)
        {
            getMapIcon().setSymbol('.');
            setActionPoints(0);
            return;
        }

        int actionCost = 333;
        if (getActionPoints() < actionCost) {return;}

        changeActionPoints(-actionCost);

        if (pushedDownValue > 0 && Math.random() < 0.9f)
        {
            pushedDownValue -= 1;
        }
    }
}
