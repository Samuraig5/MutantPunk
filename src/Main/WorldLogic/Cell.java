package Main.WorldLogic;

import Main.MathHelper;
import Main.ObjectLogic.Thing;
import Main.Direction;


import java.util.List;

public class Cell
{
    private final int[] coordinates = new int[2];
    private final List<Thing> things;

    private LocalMap lm;

    public Cell(int[] xy, LocalMap lm, List<Thing> thingsList)
    {
        coordinates[0] = xy[0];
        coordinates[1] = xy[1];
        setLocalMap(lm);
        things = thingsList;
    }

    public void setLocalMap(LocalMap lm)
    {
        this.lm = lm;
    }

    public LocalMap getLocalMap() {return lm;}

    public int[] getCoordinates()
    {
        return coordinates;
    }
    public List<Thing> getThings()
    {
        return things;
    }

    public Thing getThingWithHighestRenderPriority()
    {
        if (things.isEmpty())
        {
            throw new RuntimeException("Trying to get render priority of things in an empty cell");
        }
        Thing target = things.get(0);
        for (int i = 0; i < things.size(); i++) {
            if (things.get(i).getRenderPriority() > target.getRenderPriority())
            {
                target = things.get(i);
            }
        }
        return target;
    }

    public boolean isThereAThingWithCollision()
    {
        for (int i = 0; i < things.size(); i++) {
            if (things.get(i).hasCollision())
            {
                return true;
            }
        }
        return false;
    }
    public void thingEnters(Thing t, Direction directionOfSource)
    {
        if(!things.contains(t))
        {
            things.add(t);
        }
        if(t.getMyCell() != this)
        {
            t.setMyCell(this, Direction.invertDirection(directionOfSource));
        }

        if (lm == null) {return;}

        Cell[] n = getNeighbours();

        n[0].newThingInNeighbour(t, Direction.SOUTH);
        n[1].newThingInNeighbour(t, Direction.SOUTH_WEST);
        n[2].newThingInNeighbour(t, Direction.WEST);
        n[3].newThingInNeighbour(t, Direction.NORTH_WEST);
        n[4].newThingInNeighbour(t, Direction.NORTH);
        n[5].newThingInNeighbour(t, Direction.NORTH_EAST);
        n[6].newThingInNeighbour(t, Direction.EAST);
        n[7].newThingInNeighbour(t, Direction.SOUTH_EAST);
    }
    public void thingLeaves(Thing t, Direction directionOfTravel)
    {
        things.remove(t);
        for (int i = 0; i < things.size(); i++) {
            things.get(i).thingLeftCell(t, directionOfTravel);
        }
    }
    public boolean isEmpty()
    {
        if (things.size() == 0)
        {
            return true;
        }
        return false;
    }

    private Cell[] getNeighbours()
    {
        Cell[][] c = lm.getCells();
        Cell[] neighbours = new Cell[8];
        int x = coordinates[0];
        int y = coordinates[1];
        int xp1 = MathHelper.boundedInteger(x,+1,lm.getSize()[0]);
        int xm1 = MathHelper.boundedInteger(x,-1,lm.getSize()[0]);
        int yp1 = MathHelper.boundedInteger(y,+1,lm.getSize()[1]);
        int ym1 = MathHelper.boundedInteger(y,-1,lm.getSize()[1]);
        neighbours[0] = c[x][ym1];
        neighbours[1] = c[xp1][ym1];
        neighbours[2] = c[xp1][y];
        neighbours[3] = c[xp1][yp1];
        neighbours[4] = c[x][yp1];
        neighbours[5] = c[xm1][yp1];
        neighbours[6] = c[xm1][y];
        neighbours[7] = c[xm1][ym1];

        return neighbours;
    }

    public void newThingInNeighbour(Thing newThingInNeighbour, Direction directionToSource)
    {
        for (Thing thing : things) {
            thing.newNeightbour(newThingInNeighbour, directionToSource);
        }
    }
}
