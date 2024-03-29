package Main.ObjectLogic.Walls;

import Main.ObjectLogic.ObjectTag;
import Main.ObjectLogic.Thing;
import Main.RenderLogic.MapIcon;

import java.awt.*;
import Main.Direction;


public class Wall extends Thing
{
    public Wall()
    {
        super();
        setCollision(true);

        MapIcon mi = new MapIcon();
        mi.setSymbol('■');
        mi.setIconColour(Color.lightGray);
        setMapIcon(mi);

        setRenderPriority(10);

        setName("Wall");
        setDescription("This is a solid wall");
        setTags(new ObjectTag[] {ObjectTag.SOLID});

    }

    @Override
    public void newNeightbour(Thing t, Direction directionToSource) {

    }

    @Override
    public void thingLeftCell(Thing t, Direction directionToNewCell) {

    }

    @Override
    public void doAction() {

    }

}
