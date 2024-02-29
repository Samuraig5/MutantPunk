package Main.WorldLogic;

import Main.Direction;
import Main.ErrorHandler;
import Main.MathHelper;
import Main.ObjectLogic.BodyLogic.Person;
import Main.ObjectLogic.Thing;
import Main.ObjectLogic.Wind;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LocalMap
{
    /**
     * size[0] = x
     * size[1] = y
     */
    private final int[] size = new int[2];
    private final Cell[][] cells;

    private GameWorld myWorld;
    private String mapName;
    private List<Thing> localThings = new ArrayList<>();

    protected LocalMap(int[] xySize, Cell[][] mapCells, GameWorld gameWorld, String name)
    {
        size[0] = xySize[0];
        size[1] = xySize[1];
        cells = mapCells;
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                cells[j][i].setLocalMap(this);
                localThings.addAll(cells[j][i].getThings());
            }
        }

        myWorld = gameWorld;
        mapName = name;
    }

    public int[] getSize()
    {
        return size;
    }

    public Cell[][] getCells()
    {
        return cells;
    }

    public Cell getCell(int x, int y) {return cells[x][y];}

    public GameWorld getMyWorld() {
        return myWorld;
    }

    public String getMapName() {
        return mapName;
    }

    public void setMapName(String mapName) {
        this.mapName = mapName;
    }

    /**
     * Returns the list of all persons currently on the local map. DO NOT use this to add persons to this list. Use addThingToLocalMap instead.
     * @return the list of all persons
     */
    public List<Thing> getLocalThings() {
        ErrorHandler.LogData(false, "The local map: " + mapName + " has " + localThings.size() + " persons.");
        return localThings;
    }

    public void addLocalThing(Thing t)
    {
        localThings.add(t);
    }

    public List<Person> getLocalThing()
    {
        List<Thing> things = getLocalThings();
        List<Person> people = new ArrayList<>();

        for (int i = 0; i < things.size(); i++)
        {
            if (things.get(i) instanceof Person)
            {
                people.add((Person)things.get(i));
            }
        }

        return people;
    }

    public void addThingToLocalMap(Thing t)
    {
        localThings.add(t);
        if (t.getMyCell() == null) {
            Random r = new Random();
            int xCoord = r.nextInt(size[0]);
            int yCoord = r.nextInt(size[1]);
            cells[xCoord][yCoord].thingEnters(t, Direction.NONE);
        }
    }

    public void removeThingFromLocalMap(Thing t)
    {
        boolean removedThing = true;
        while (removedThing)
        {
            removedThing = localThings.remove(t);;
        }
    }

    public void updateTick()
    {
        if (Math.random() < 0.1)
        {
            int xOrigin = (int) (Math.random() * getMyWorld().getActiveLocalMap().getSize()[1]);
            new Wind(cells[xOrigin][0],Direction.SOUTH,1);
        }
    }
}
