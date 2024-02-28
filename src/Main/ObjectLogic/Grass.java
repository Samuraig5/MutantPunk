package Main.ObjectLogic;

import Main.MathHelper;
import Main.ObjectLogic.BodyLogic.Person;
import Main.RenderLogic.MapIcon;
import Main.Direction;


import java.awt.*;
import java.util.Random;

public class Grass extends Thing
{
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
        if (t instanceof Person)
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
        if (Math.random()>0.9f)
            getMapIcon().setSymbol('.');
    }
}
