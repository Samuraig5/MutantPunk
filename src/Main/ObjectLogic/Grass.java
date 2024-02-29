package Main.ObjectLogic;

import Main.MathHelper;
import Main.ObjectLogic.BodyLogic.Person;
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
    public void updateTick() {
        if (pushedDownValue == 0)
        {
            if (Math.random() < 0.9f)
            {
                getMapIcon().setSymbol('.');
            }
        }
        else if (Math.random() < 0.9f)
        {
            pushedDownValue -= 1;
        }
    }
}
