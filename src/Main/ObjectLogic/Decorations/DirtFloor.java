package Main.ObjectLogic.Decorations;

import Main.Direction;
import Main.ObjectLogic.ObjectTag;
import Main.ObjectLogic.Thing;
import Main.RenderLogic.MapIcon;

import java.awt.*;

public class DirtFloor extends Thing
{
    public DirtFloor()
    {
        super();
        setCollision(false);

        MapIcon mi = new MapIcon();
        mi.setSymbol('_');
        mi.setIconColour(Color.orange);
        setMapIcon(mi);
        setRenderPriority(0);

        setName("Dirt Floor");
        setDescription("This is healthy dirt floor, full of nutrients and mutant bugs.");
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
