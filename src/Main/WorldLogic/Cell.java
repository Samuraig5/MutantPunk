package Main.WorldLogic;

import Main.ObjectLogic.BodyLogic.Person;
import Main.ObjectLogic.Thing;

import java.util.List;

public class Cell
{
    private final int[] coordinates = new int[2];
    private final List<Thing> things;

    public Cell(int[] xy, List<Thing> thingsList)
    {
        coordinates[0] = xy[0];
        coordinates[1] = xy[1];
        things = thingsList;
    }

    public int[] getCoordinates()
    {
        return coordinates;
    }
    public List<Thing> getThings()
    {
        return things;
    }
    public void thingEnters(Thing t)
    {
        if(!things.contains(t))
        {
            things.add(t);
        }
        if(t.getMyCell() != this)
        {
            t.setMyCell(this);
        }
    }
    public void thingLeaves(Thing t)
    {
        things.remove(t);
    }
    public boolean isEmpty()
    {
        if (things.size() == 0)
        {
            return true;
        }
        return false;
    }
}
