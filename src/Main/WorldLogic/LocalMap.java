package Main.WorldLogic;

import Main.ErrorHandler;
import Main.ObjectLogic.BodyLogic.Person;
import Main.ObjectLogic.Thing;

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
     * Returns the list of all persons currently on the local map. DO NOT use this to add persons to this list. Use addPersonToLocalMap instead.
     * @return the list of all persons
     */
    public List<Thing> getLocalThings() {
        ErrorHandler.LogData(true, "The local map: " + mapName + " has " + localThings.size() + " persons.");
        return localThings;
    }

    public List<Person> getLocalPeople()
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

    public void addPersonToLocalMap(Thing t)
    {
        localThings.add(t);
        Random r = new Random();
        int xCoord = r.nextInt(size[0]);
        int yCoord = r.nextInt(size[1]);
        cells[xCoord][yCoord].thingEnters(t);
    }
}
